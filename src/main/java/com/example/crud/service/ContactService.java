package com.example.crud.service;

import com.example.crud.model.Contact;
import com.example.crud.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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

    public Contact saveContact(Contact contact){
        Optional<Contact> fondContact = this.contactRepository.findById(contact.getId());

        if(fondContact.isPresent()){
            System.out.println("Lançar execessão");
        }

        return this.contactRepository.save(contact);
    }

    public Contact updateContact(long id, Contact contact){

        Optional<Contact> fondContact = this.contactRepository.findById(id);

        if(fondContact.isEmpty()){
            System.out.println("Lançar execessão");
        }

        Contact presentContact;

        presentContact = fondContact.get();

        presentContact.setName(contact.getName());
        presentContact.setEmail(contact.getEmail());
        presentContact.setPhone(contact.getPhone());
        return this.contactRepository.save(presentContact);
    }

    public void deleteContact(Long id){
        Optional<Contact> fondContact = this.contactRepository.findById(id);

        if(fondContact.isEmpty()){
            System.out.println("Lançar execessão");
        }

        this.contactRepository.deleteById(id);
    }


}
