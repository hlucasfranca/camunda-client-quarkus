package com.demo.model.db;


import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FieldValidation  implements Serializable {
    private ValidationType validationType;
    private String value;
}
