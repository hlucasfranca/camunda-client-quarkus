package com.demo.model;

import com.demo.model.db.ValidationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class RulesValidationResultBean {
    private Boolean valid;
    private ValidationType validationType;
    private String originalValue;
    private String comparatorValue;

    public RulesValidationResultBean(Boolean valid){
        this.valid = valid;
    }
}
