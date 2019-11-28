package com.demo.model.requests;

import com.demo.model.Roles;
import com.demo.model.db.FormField;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FormCreate implements Serializable {

    private String formTitle;
    //@NotNull
    private List<FormField> fieldList;

    private List<Roles> rolesAllowed;
}
