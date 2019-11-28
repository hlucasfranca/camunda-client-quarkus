package com.demo.model.camunda;

import lombok.*;

import java.util.HashMap;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Message {
    private String messageName;
    private String businessKey;
    private HashMap<String,MessageVariable> processVariables;
}
