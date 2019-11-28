package com.demo.repository;

import com.demo.model.FormTemplate;
import com.demo.model.db.FormState;
import com.demo.model.requests.FormCreate;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ApplicationScoped
public class BpmRepository {
    private HashMap<String, FormTemplate> inMemoryDataSource = new HashMap<>();

    public void insert(String pk, FormCreate formCreate){
        FormTemplate formTemplate = new FormTemplate(pk, formCreate.getFormTitle(), formCreate.getFieldList(),
                FormState.NOT_READY, formCreate.getRolesAllowed());
        inMemoryDataSource.put(pk,formTemplate);
    }

    public FormTemplate getOne(String pk){
        return inMemoryDataSource.get(pk);
    }

    public List<FormTemplate> getAll(){
        List<FormTemplate> templateList = new ArrayList<>();
        inMemoryDataSource.forEach((s, formTemplate) -> templateList.add(formTemplate));
        return templateList;
    }

    public void update(FormTemplate single){
        inMemoryDataSource.put(single.getId(), single);
    }

}
