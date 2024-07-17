package com.example.crud.service;

import com.example.crud.exceptions.ResourceAlreadyExistsException;
import com.example.crud.exceptions.ResourceNotFoundException;
import com.example.crud.model.Contact;
import com.example.crud.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<Contact> listAllContact(){
        return this.contactRepository.findAll();
    }

    public Contact findContactById(Long id){
        Optional<Contact> contact = this.contactRepository.findById(id);

        if(contact.isEmpty()){
            throw new ResourceNotFoundException("Contact Not Found");
        }

        return contact.get();
    }

    public Contact saveContact(Contact contact){
        Optional<Contact> foundContact = this.contactRepository.findContactByEmail(contact.getEmail());

        if(foundContact.isPresent()){
            throw new ResourceAlreadyExistsException("Contact Already exists");
        }

        return this.contactRepository.save(contact);
    }

    public Contact updateContact(long id, Contact contact){

        Optional<Contact> foundContact = this.contactRepository.findById(id);

        if(foundContact.isEmpty()){
            throw new ResourceNotFoundException("Contact Not Found");
        }

        Contact presentContact;

        presentContact = foundContact.get();

        presentContact.setName(contact.getName());
        presentContact.setEmail(contact.getEmail());
        presentContact.setPhone(contact.getPhone());
        return this.contactRepository.save(presentContact);
    }

    public void deleteContact(Long id){
        Optional<Contact> foundContact = this.contactRepository.findById(id);

        if(foundContact.isEmpty()){
            throw new ResourceNotFoundException("Contact Not Found");
        }

        this.contactRepository.deleteById(id);
    }


}
