package com.kunlun.system.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.kunlun.common.model.Page;
import com.kunlun.common.utils.ListPageUtil;
import com.kunlun.system.dao.IProcessDao;
import com.kunlun.system.model.ProcessModel;
import com.kunlun.system.model.activiti.ActInstModel;
import com.kunlun.system.model.activiti.ProcDefModel;
import com.kunlun.system.model.activiti.TaskInstModel;
import com.kunlun.system.model.enums.ProcessStatusEnum;
import com.kunlun.system.utils.CommonUtil;
import com.kunlun.system.config.dataSource.DataSourceType;
import com.kunlun.system.config.dataSource.DbContextHolder;
import com.kunlun.system.service.IProcessService;
import org.activiti.engine.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service("processService")
@Transactional
public class ProcessService implements IProcessService {

    @Autowired
    private IProcessDao processDao;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RepositoryService repositoryService;

    @Override
    public Page getAllProcess(ProcessModel model, int currentPage, int pageSize) throws Exception {
        int startIndex = (currentPage - 1) * pageSize;
        Map<String, Object> queryMap = CommonUtil.packageQueryMap(model, startIndex, pageSize);

        DbContextHolder.setDbType(DataSourceType.ACTIVITI.getKey());

        List<ProcessModel> processModels = new ArrayList<>();
        List<ActInstModel> actInstModels = processDao.getActInsts(queryMap);
        List<ProcDefModel> procDefModels = processDao.getProcDefs(queryMap);
        Map<String, ProcDefModel> procDefMap = procDefModels.stream().collect(Collectors.toMap(ProcDefModel::getId, Function.identity()));
        if (!ObjectUtils.isEmpty(actInstModels) && actInstModels.size() > 0) {
            Map<String, List<ActInstModel>> actInstMap = actInstModels.stream().collect(Collectors.groupingBy(ActInstModel::getProcInstId));
            for (Map.Entry map : actInstMap.entrySet()) {
                List<ActInstModel> actInsts = (List<ActInstModel>) map.getValue();
                ActInstModel startActInst = actInsts.get(0);
                ActInstModel middleActInst = actInsts.get(actInsts.size() - 1);

                ProcDefModel procDefModel = procDefMap.get(startActInst.getProcDefId());
                ObjectNode editorJsonNode = (ObjectNode) objectMapper.readTree(new String(repositoryService.getModelEditorSource(procDefModel.getModelId()), "utf-8"));
                JsonNode jsonNode = editorJsonNode.get("childShapes");
                JSONArray jsonArray = (JSONArray) JSONArray.parse(jsonNode.toString());
                Map<String, String> nodeMap = new HashMap<>();
                for (Object object : jsonArray) {
                    JSONObject jsonObject = (JSONObject) object;
                    JSONObject obj = (JSONObject) jsonObject.get("properties");
                    String targetId = (String) obj.get("overrideid");
                    if (ObjectUtils.isEmpty(targetId)) continue;
                    String targetValue = (String) obj.get("name");
                    nodeMap.put(targetId, targetValue);
                }

                Date endTime = null;
                String status = ProcessStatusEnum.UNSUBMIT.getKey();
                if (actInsts.size() == (nodeMap.size() - 1)) {
                    status = ProcessStatusEnum.FINISH.getKey();
                    endTime = middleActInst.getStartTime();
                } else if (!startActInst.getActId().equals(middleActInst.getActId())) {
                    status = ProcessStatusEnum.AUDITING.getKey();
                }

                ProcessModel processModel = new ProcessModel();
                processModel.setId(CommonUtil.generateUUID());
                processModel.setKey(procDefModel.getKey());
                processModel.setModelId(procDefModel.getModelId());
                processModel.setModelName(procDefModel.getName());
                processModel.setDeploymentId(procDefModel.getDeploymentId());
                processModel.setProcessInstanceId(middleActInst.getProcInstId());
                processModel.setProcessDefineId(middleActInst.getProcDefId());
                processModel.setProcessStatus(status);
                processModel.setStartTime(startActInst.getStartTime());
                processModel.setEndTime(endTime);
                processModel.setCurrentExecuteKey(middleActInst.getActId());
                processModel.setCurrentExecuteName(middleActInst.getActName());
                processModel.setNextExecuteKey(middleActInst.getAssignee());
                processModel.setNextExecuteName(nodeMap.get(middleActInst.getAssignee()));
                processModels.add(processModel);
            }
        } else {
            for (ProcDefModel defModel : procDefModels) {
                ProcessModel processModel = new ProcessModel();
                processModel.setId(defModel.getId());
                processModel.setKey(defModel.getKey());
                processModel.setModelName(defModel.getName());
                processModel.setDeploymentId(defModel.getDeploymentId());
                processModel.setProcessStatus(ProcessStatusEnum.UNSUBMIT.getKey());
                processModels.add(processModel);
            }
        }

        Page page = ListPageUtil.limitPages(processModels, startIndex, pageSize);
        return page;
    }

    @Override
    public TaskInstModel getCurrentProcessNode(String procDefId, String procInstId) throws Exception {
        DbContextHolder.setDbType(DataSourceType.ACTIVITI.getKey());
        return processDao.getTaskInsts(procDefId, procInstId);
    }
}
