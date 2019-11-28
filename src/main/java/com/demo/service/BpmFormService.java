package com.demo.service;

import com.demo.model.FormTemplate;
import com.demo.model.db.FormState;
import com.demo.model.requests.FormCreate;
import com.demo.model.requests.FormGetSingle;
import com.demo.repository.BpmRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.util.List;

@ApplicationScoped
public class BpmFormService {

    @Inject
    BpmRepository bpmRepository = new BpmRepository();
    private int invokedTimes = 0;

    private ObjectMapper objectMapper = new ObjectMapper();

    public String createFormTemplateAsString(String formCreateStr) {
        invokedTimes++;
        System.out.println("Creating a form template from a simple string for the " + invokedTimes);
        FormCreate formCreate = null;
        try {
            formCreate = objectMapper.readValue("", FormCreate.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return createFormTemplate(formCreate);
    }

    public String createFormTemplate(FormCreate formCreate){
        String uuid = "1";
        bpmRepository.insert(uuid, formCreate);
        return uuid;
    }

    public FormTemplate getOne(FormGetSingle single){
        return bpmRepository.getOne(single.getFormId());
    }

    public FormTemplate authorizeTemplate(FormGetSingle getSingle){
        System.out.println("The repository is authorizing the form");
        FormTemplate one = bpmRepository.getOne(getSingle.getFormId());
        one.setFormState(FormState.READY);
        bpmRepository.update(one);
        return one;
    }

    public List<FormTemplate> getAllFormTemplates(){
        return bpmRepository.getAll();
    }
}
