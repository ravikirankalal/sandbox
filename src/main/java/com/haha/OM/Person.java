package com.haha.OM;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class Person {
    @JsonProperty
    private long id;

    @JsonProperty
    private String name;
}
