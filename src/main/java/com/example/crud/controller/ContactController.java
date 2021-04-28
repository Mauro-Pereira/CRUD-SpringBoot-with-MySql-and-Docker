package com.example.crud.controller;

import java.util.List;

import com.example.crud.model.Contact;
import com.example.crud.repository.ContactRepository;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/contact"})
public class ContactController {

    private ContactRepository repository;

    ContactController(ContactRepository repository){
        this.repository = repository;
    }

    public List<Contact> findAll(){
        return this.repository.findAll();
    }
    
}
