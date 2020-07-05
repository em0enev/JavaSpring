package com.botronsoft.internship.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Service {
    private  UUID id;
    private  String name;
    private  String description;

    public Service(@JsonProperty("name") String name,
                   @JsonProperty("description") String description) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setId(){
        this.id = UUID.randomUUID();
    }
}
