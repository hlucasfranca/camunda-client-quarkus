package com.demo.model.requests;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FormGetSingle {

    private String formId;
    private String myRole;
}
