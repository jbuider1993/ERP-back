package com.kunlun.system.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.kunlun.common.annotation.OperatorLogger;
import com.kunlun.common.model.Page;
import com.kunlun.common.model.enums.OperatorLogType;
import com.kunlun.common.utils.ResponseUtil;
import com.kunlun.system.config.dataSource.DataSourceType;
import com.kunlun.system.config.dataSource.DbContextHolder;
import com.kunlun.system.model.ModelModel;
import com.kunlun.system.service.IModelService;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping(value = "/service")
public class ActivitiModeController implements ModelDataJsonConstants {

    protected static final Logger log = LoggerFactory.getLogger(ActivitiModeController.class);

    @Autowired
    ProcessEngine processEngine;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    @Qualifier("modelService")
    private IModelService modelService;

    /**
     * 新建空模型
     */
    @RequestMapping("/create")
    @OperatorLogger(type = OperatorLogType.SYSTEM, description = "创建模型")
    public void newModel(HttpServletResponse response) {
        try {
            RepositoryService repositoryService = processEngine.getRepositoryService();
            //初始化一个空模型
            Model model = repositoryService.newModel();

            //设置一些默认信息
            String name = "";
            String description = "";
            int revision = 1;
            String key = "defalutCreatedprocess";

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
            repositoryService.addModelEditorSource(id, editorNode.toString().getBytes("utf-8"));
            response.sendRedirect("/static/modeler.html?modelId=" + id);
        } catch (IOException e) {
            log.error("", e);
        }
    }

    /**
     * 保存定义的流程模型
     */
    @RequestMapping(value = "/model/{modelId}/save", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @OperatorLogger(type = OperatorLogType.SYSTEM, description = "保存模型")
    public void saveModel(@PathVariable String modelId,
                          @RequestParam("name") String name,
                          @RequestParam("json_xml") String json_xml,
                          @RequestParam("svg_xml") String svg_xml,
                          @RequestParam("description") String description) {
        try {
            DbContextHolder.setDbType(DataSourceType.ACTIVITI.getKey());

            Model model = repositoryService.getModel(modelId);

            ObjectNode modelJson = (ObjectNode) objectMapper.readTree(model.getMetaInfo());

            modelJson.put(MODEL_NAME, name);
            modelJson.put(MODEL_DESCRIPTION, description);
            model.setMetaInfo(modelJson.toString());
            model.setName(name);

            repositoryService.saveModel(model);

            repositoryService.addModelEditorSource(model.getId(), json_xml.getBytes("utf-8"));

            InputStream svgStream = new ByteArrayInputStream(svg_xml.getBytes("utf-8"));
            TranscoderInput input = new TranscoderInput(svgStream);

            PNGTranscoder transcoder = new PNGTranscoder();
            // Setup output
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            TranscoderOutput output = new TranscoderOutput(outStream);

            // Do the transformation
            transcoder.transcode(input, output);
            final byte[] result = outStream.toByteArray();
            repositoryService.addModelEditorSourceExtra(model.getId(), result);
            outStream.close();
        } catch (Exception e) {
            log.error("Error saving model", e);
            throw new ActivitiException("Error saving model", e);
        } finally {
            DbContextHolder.setDbType(DataSourceType.ACTIVITI.getKey());
        }
    }

    /**
     * 获取定义的流程模型
     */
    @RequestMapping(value = "/model/{modelId}/json", method = RequestMethod.GET, produces = "application/json")
    public ObjectNode getEditorJson(@PathVariable String modelId) {
        ObjectNode modelNode = null;

        Model model = repositoryService.getModel(modelId);

        if (model != null) {
            try {
                if (StringUtils.isNotEmpty(model.getMetaInfo())) {
                    modelNode = (ObjectNode) objectMapper.readTree(model.getMetaInfo());
                } else {
                    modelNode = objectMapper.createObjectNode();
                    modelNode.put(MODEL_NAME, model.getName());
                }
                modelNode.put(MODEL_ID, model.getId());
                ObjectNode editorJsonNode = (ObjectNode) objectMapper.readTree(
                        new String(repositoryService.getModelEditorSource(model.getId()), "utf-8"));
                modelNode.put("model", editorJsonNode);
            } catch (Exception e) {
                log.error("Error creating model JSON", e);
                throw new ActivitiException("Error creating model JSON", e);
            }
        }
        return modelNode;
    }

    /**
     * 获取编辑器中节点定义的静态信息
     */
    @RequestMapping(value = "/editor/stencilset", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody
    String getStencilset() {
        InputStream stencilsetStream = this.getClass().getClassLoader().getResourceAsStream("static/stencilset.json");
        try {
            return IOUtils.toString(stencilsetStream, "utf-8");
        } catch (Exception e) {
            throw new ActivitiException("Error while loading stencil set", e);
        }
    }

    /**
     * 获取所有模型
     */
    @RequestMapping("/modelList")
    @ResponseBody
    public Object modelList(ModelModel model, int currentPage, int pageSize) {
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

    @RequestMapping(value = "/batchDelete", method = RequestMethod.POST)
    @OperatorLogger(type = OperatorLogType.SYSTEM, description = "删除模型")
    public Object batchDelete(String modelIds) {
        try {
            String[] ids = modelIds.split(",");
            return ResponseUtil.successResponse(modelService.batchDeleteModel(ids));
        } catch (Exception e) {
            log.error("ActivitiModelController batchDelete Error: ", e);
            return ResponseUtil.failedResponse("删除模型失败！", e.getMessage());
        }
    }
}
