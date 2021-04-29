package com.example.crud.controller;

import java.util.List;

import com.example.crud.model.Contact;
import com.example.crud.repository.ContactRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/contact"})
public class ContactController {

    private ContactRepository repository;

    ContactController(ContactRepository repository){
        this.repository = repository;
    }

    @GetMapping
    public List<Contact> findAll(){
        return this.repository.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Contact> findById(@PathVariable Long id){
        return this.repository.findById(id).map(record -> ResponseEntity.ok().body(record))
        .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Contact save(@RequestBody Contact contact){
        return this.repository.save(contact);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Contact> update(@PathVariable("id") long id,
                                        @RequestBody Contact contact){
    return repository.findById(id)
        .map(record -> {
            record.setName(contact.getName());
            record.setEmail(contact.getEmail());
            record.setPhone(contact.getPhone());
            Contact updated = repository.save(record);
            return ResponseEntity.ok().body(updated);
        }).orElse(ResponseEntity.notFound().build());
  }


    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        return repository.findById(id)
        .map(record -> {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
  }

    
}
