package com.example.G_Books.request;

import lombok.Data;
import lombok.Getter;

@Getter
public enum EmailTemplateName {

    ACTIVATE_ACCOUNT("activate_account");

    private final String name;

    EmailTemplateName(String name){
        this.name = name;
    }


}
