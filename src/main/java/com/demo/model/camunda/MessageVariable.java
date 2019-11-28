package com.demo.model.camunda;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MessageVariable {
    private String value;
    private String type;
}
