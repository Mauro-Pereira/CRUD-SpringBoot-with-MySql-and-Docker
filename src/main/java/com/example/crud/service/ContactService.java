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
            throw new ResourceNotFoundException("Contact not found");
        }

        return contact.get();
    }

    public Contact saveContact(Contact contact){
        Optional<Contact> fondContact = this.contactRepository.findContactByEmail(contact.getEmail());

        if(fondContact.isPresent()){
            throw new ResourceAlreadyExistsException("Contact Already exists");
        }

        return this.contactRepository.save(contact);
    }

    public Contact updateContact(long id, Contact contact){

        Optional<Contact> fondContact = this.contactRepository.findById(id);

        if(fondContact.isEmpty()){
            throw new ResourceNotFoundException("Contact not found");
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
            throw new ResourceNotFoundException("Contact not found");
        }

        this.contactRepository.deleteById(id);
    }


}
