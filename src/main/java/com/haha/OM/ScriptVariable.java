package com.haha.OM;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@AllArgsConstructor
@Getter
@ToString
public class ScriptVariable {
    private String name;
    private DataType dataType;
    @Setter
    private Object value;
    private Map<String, String> additionalParams;
}
