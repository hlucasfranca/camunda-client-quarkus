package com.demo.external;

import com.demo.model.requests.FormCreate;
import com.demo.repository.BpmRepository;
import com.demo.service.BpmFormService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.client.ExternalTaskClient;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.Map;

@Slf4j
public class CreateTemplate {

    final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    final static BpmFormService BPM_FORM_SERVICE = new BpmFormService();


    public static void main(String[] args) {
        ExternalTaskClient externalTaskClient = ExternalTaskClient.create()
                .baseUrl("http://localhost:8080/engine-rest")
                .asyncResponseTimeout(1000) // long polling timeout
                .build();

        // Subscribe to topic
        externalTaskClient.subscribe("create-template")
                .handler((externalTask, externalTaskService) -> {
                    String createFormTemplateStr = externalTask.getVariable("formCreate");
                    if(createFormTemplateStr != null) {
                        try {
                            FormCreate formCreate = OBJECT_MAPPER.readValue(createFormTemplateStr, FormCreate.class);
                            String formId = BPM_FORM_SERVICE.createFormTemplate(formCreate);
                            System.out.println("Form id generated: " + formId);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    // Complete task
                    externalTaskService.complete(externalTask, Map.ofEntries(
                            new AbstractMap.SimpleEntry<>("retry", "false"),
                            new AbstractMap.SimpleEntry<>("success", "true")
                            ));
                })
                .open();
    }
}
