package com.kunlun.system.controller.activiti;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.kunlun.common.annotation.OperatorLogger;
import com.kunlun.common.model.Page;
import com.kunlun.common.model.enums.OperatorLogType;
import com.kunlun.common.utils.ResponseUtil;
import com.kunlun.system.model.ModelModel;
import com.kunlun.system.model.ProcessModel;
import com.kunlun.system.service.IModelService;
import com.kunlun.system.service.IProcessService;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ActivitiProcessController {

    private static final Logger log = LogManager.getLogger(ActivitiProcessController.class);

    @Autowired
    ProcessEngine processEngine;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    @Qualifier("modelService")
    private IModelService modelService;

    @Autowired
    @Qualifier("processService")
    private IProcessService processService;

    /**
     * 新建一个空模型
     */
    @RequestMapping("/create")
    @OperatorLogger(type = OperatorLogType.SYSTEM, description = "创建模型")
    public void newModel(HttpServletResponse response) {
        try {
            RepositoryService repositoryService = processEngine.getRepositoryService();
            //初始化一个空模型
            Model model = repositoryService.newModel();

            //设置一些默认信息
            String name = "new-process";
            String description = "";
            int revision = 1;
            String key = "process";

            ObjectNode modelNode = objectMapper.createObjectNode();
            modelNode.put(ModelDataJsonConstants.MODEL_NAME, name);
            modelNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
            modelNode.put(ModelDataJsonConstants.MODEL_REVISION, revision);

            model.setName(name);
            model.setKey(key);
            model.setMetaInfo(modelNode.toString());

            repositoryService.saveModel(model);
            String id = model.getId();

            //完善ModelEditorSource
            ObjectNode editorNode = objectMapper.createObjectNode();
            editorNode.put("id", "canvas");
            editorNode.put("resourceId", "canvas");
            ObjectNode stencilSetNode = objectMapper.createObjectNode();
            stencilSetNode.put("namespace",
                    "http://b3mn.org/stencilset/bpmn2.0#");
            editorNode.put("stencilset", stencilSetNode);
            repositoryService.addModelEditorSource(id,editorNode.toString().getBytes("utf-8"));
            response.sendRedirect("/static/modeler.html?modelId="+id);
        } catch (IOException e) {
            log.error("", e);
        }
    }

    /**
     * 获取所有模型
     */
    @RequestMapping("/modelList")
    @ResponseBody
    public Object modelList(ModelModel model, int currentPage, int pageSize){
        try {
//            RepositoryService repositoryService = processEngine.getRepositoryService();
//            return ResponseUtil.successResponse(repositoryService.createModelQuery().list());

            Page pages = modelService.getAllProcess(model, currentPage, pageSize);
            return ResponseUtil.successResponse(pages);
        } catch (Exception e) {
            log.error("ActivitiModelController modelList Error: ", e);
            return ResponseUtil.failedResponse("查询所有模型失败！", e.getMessage());
        }
    }

    /**
     * 发布模型为流程定义
     */
    @RequestMapping("/deploy")
    @ResponseBody
    @OperatorLogger(type = OperatorLogType.SYSTEM, description = "部署模型")
    public Object deploy(String modelId) {

        try {
            //获取模型
            RepositoryService repositoryService = processEngine.getRepositoryService();
            Model modelData = repositoryService.getModel(modelId);
            byte[] bytes = repositoryService.getModelEditorSource(modelData.getId());

            if (bytes == null) {
                return "模型数据为空，请先设计流程并成功保存，再进行发布。";
            }

            JsonNode modelNode = new ObjectMapper().readTree(bytes);

            BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
            if(model.getProcesses().size()==0){
                return "数据模型不符要求，请至少设计一条主线流程。";
            }
            byte[] bpmnBytes = new BpmnXMLConverter().convertToXML(model);

            //发布流程
            String processName = modelData.getName() + ".bpmn20.xml";
            Deployment deployment = repositoryService.createDeployment()
                    .name(modelData.getName())
                    .addString(processName, new String(bpmnBytes, "UTF-8"))
                    .deploy();
            modelData.setDeploymentId(deployment.getId());
            repositoryService.saveModel(modelData);

            return ResponseUtil.successResponse("SUCCESS");
        } catch (IOException e) {
            log.error("ActivitiProcessController deploy Error: ", e);
            return ResponseUtil.failedResponse("流程部署失败！", e.getMessage());
        }
    }

    /**
     *  启动流程
     */
    @RequestMapping("/start")
    @ResponseBody
    @OperatorLogger(type = OperatorLogType.SYSTEM, description = "提交模型")
    public Object startProcess(String keyName) {
        try {
            ProcessInstance process = processEngine.getRuntimeService().startProcessInstanceByKey(keyName);
            return ResponseUtil.successResponse(process.getId() + " : " + process.getProcessDefinitionId());
        } catch (Exception e) {
            log.error("ActivitiProcessController startProcess Error: ", e);
            return ResponseUtil.failedResponse("流程启动失败！", e.getMessage());
        }
    }

    /**
     *  提交任务
     */
    @RequestMapping("/run")
    @ResponseBody
    @OperatorLogger(type = OperatorLogType.SYSTEM, description = "运行模型")
    public Object run(String processInstanceId) {
        try {
            Task task = processEngine.getTaskService().createTaskQuery().processInstanceId(processInstanceId).singleResult();

            log.info("task {} find ", task.getId());
            processEngine.getTaskService().complete(task.getId());
            return ResponseUtil.successResponse("SUCCESS");
        } catch (Exception e) {
            log.error("ActivitiProcessController run Error: ", e);
            return ResponseUtil.failedResponse("任务提交失败！", e.getMessage());
        }
    }

    @RequestMapping("/processList")
    @ResponseBody
    public Object processList(ProcessModel model, int currentPage, int pageSize){
        try {
            Page pages = processService.getAllProcess(model, currentPage, pageSize);
            return ResponseUtil.successResponse(pages);
        } catch (Exception e) {
            log.error("ActivitiModelController modelList Error: ", e);
            return ResponseUtil.failedResponse("查询所有流程失败！", e.getMessage());
        }
    }
}