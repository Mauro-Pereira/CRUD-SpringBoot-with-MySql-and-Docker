package com.example.crud.service;

import com.example.crud.model.Contact;
import com.example.crud.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public List<Contact> listAllContact(){
        return this.contactRepository.findAll();
    }

    public Optional<Contact> findContactById(Long id){
        Optional<Contact> contact = this.contactRepository.findById(id);

        if(contact.isEmpty()){
            System.out.println("Lançar execessão");
        }

        return contact;
    }
}
