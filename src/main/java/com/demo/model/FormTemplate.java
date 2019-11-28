package com.demo.model;

import com.demo.model.db.FormField;
import com.demo.model.db.FormState;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FormTemplate {

    private String id;
    private String formTitle;
    private List<FormField> fieldList;
    private FormState formState;
    private List<Roles> allowedRoles;
}
