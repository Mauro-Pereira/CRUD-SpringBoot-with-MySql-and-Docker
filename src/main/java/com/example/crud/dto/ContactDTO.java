package com.example.crud.dto;

import com.example.crud.model.Contact;
import lombok.Getter;

@Getter
public class ContactDTO {

    private String name;
    private String email;
    private String phone;

    public Contact convertToContactObject(){
        return new Contact(name, email, phone);
    }
}
