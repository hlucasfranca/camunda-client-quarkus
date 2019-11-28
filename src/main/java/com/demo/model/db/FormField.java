package com.demo.model.db;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FormField implements Serializable {

    private String name;
    private FieldType fieldType;
    private List<FieldValidation> validationList;
    private String value;
}
