package com.demo;

import com.demo.model.camunda.Message;
import com.demo.model.camunda.MessageVariable;
import com.demo.model.requests.FormCreate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;

@Path("form")
@Slf4j
public class Endpoints {

    private ObjectMapper objectMapper = new ObjectMapper();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createTemplate(FormCreate formCreate) throws JsonProcessingException {
        final HashMap<String, MessageVariable> businessProcessVariables = new HashMap<>();
        businessProcessVariables.put("formCreate", new MessageVariable(objectMapper.writeValueAsString(formCreate), "json"));
        businessProcessVariables.put("retry", new MessageVariable("false", "Boolean"));
        businessProcessVariables.put("success", new MessageVariable("false", "Boolean"));

        final String correlationKey = String.valueOf(Math.random());

        final Message startCreateTemplateProcess = Message.builder()
                .messageName("CreateFormTemplate")
                .businessKey(correlationKey)
                .processVariables(businessProcessVariables)
                .build();

        final String procVars = objectMapper.writeValueAsString(startCreateTemplateProcess);
        log.info("Process Vars: {}", procVars );

        Response post = ClientBuilder.newClient()
                .target("http://localhost:8080")
                .path("engine-rest/message")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(procVars, MediaType.APPLICATION_JSON));

        log.info("Created Process ID: {}",  correlationKey);

        return Response.status(post.getStatus()).entity(post.getEntity()).build();
    }
}
