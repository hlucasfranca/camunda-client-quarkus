package com.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ResultBean {
    private Boolean authorized;
    private FormTemplate formTemplate;

    public ResultBean(Boolean authorized){
        this.authorized = authorized;
    }
}
