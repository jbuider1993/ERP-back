/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.scmp.system.controller.activiti;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.scmp.common.annotation.OperatorLogger;
import com.scmp.common.model.enums.OperatorLogType;
import com.scmp.common.utils.ResponseUtil;
import com.scmp.system.config.dataSource.DataSourceType;
import com.scmp.system.config.dataSource.DbContextHolder;
import com.scmp.system.service.IModelService;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * @author Tijs Rademakers
 */
@RestController
@RequestMapping(value = "/service")
public class ActivitiModelController implements ModelDataJsonConstants {
  
  protected static final Logger log = LoggerFactory.getLogger(ActivitiModelController.class);
  
  @Autowired
  private RepositoryService repositoryService;
  
  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  @Qualifier("modelService")
  private IModelService modelService;

  @RequestMapping(value="/model/{modelId}/save", method = RequestMethod.POST)
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
  
  @RequestMapping(value="/model/{modelId}/json", method = RequestMethod.GET, produces = "application/json")
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

  @RequestMapping(value="/batchDelete", method = RequestMethod.GET)
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
