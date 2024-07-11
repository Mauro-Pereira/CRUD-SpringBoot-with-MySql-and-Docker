package com.example.crud.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.example.crud.dto.ContactDTO;
import com.example.crud.dto.ContactResponseDTO;
import com.example.crud.model.Contact;
import com.example.crud.repository.ContactRepository;

import com.example.crud.service.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/contact"})
public class ContactController {

    private final ContactService contactService;

    ContactController(ContactRepository repository, ContactService contactService){
        this.contactService = contactService;
    }

    @GetMapping
    public ResponseEntity<List<ContactResponseDTO>> findAll(){
        List<ContactResponseDTO> contactResponseDTOList = this.contactService.listAllContact().stream().map(ContactResponseDTO::transformToDTO).collect(Collectors.toList());
        return new ResponseEntity<>(contactResponseDTOList, HttpStatus.OK);

    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<ContactResponseDTO> findById(@PathVariable Long id){
        Contact contact = this.contactService.findContactById(id);
        return new ResponseEntity<>(ContactResponseDTO.transformToDTO(contact), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Contact>save(@RequestBody ContactDTO contactDTO){
        Contact contact = this.contactService.saveContact(contactDTO.convertToContactObject());
        return new ResponseEntity<>(contact, HttpStatus.CREATED);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Contact> update(@PathVariable("id") long id,
                                        @RequestBody ContactDTO contactDTO){
        Contact contact = this.contactService.updateContact(id, contactDTO.convertToContactObject());
        return new ResponseEntity<>(contact, HttpStatus.OK);
  }


    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        this.contactService.deleteContact(id);
        return new ResponseEntity<>("Deleted with successfully", HttpStatus.OK);
  }

    
}
