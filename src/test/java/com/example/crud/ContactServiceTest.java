package com.example.crud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.crud.exceptions.ResourceAlreadyExistsException;
import com.example.crud.exceptions.ResourceNotFoundException;
import com.example.crud.model.Contact;
import com.example.crud.repository.ContactRepository;
import com.example.crud.service.ContactService;

class ContactServiceTest {

    @Mock
    private ContactRepository contactRepository;

    @InjectMocks
    private ContactService contactService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnAllUsers(){

        Contact contact1 = new Contact(1L, "testeName", "teste@email.com", "888888");
        Contact contact2 = new Contact(1L, "testeName", "teste@email.com", "888888");

        List<Contact> contactList = new ArrayList<Contact>();
        contactList.add(contact1);
        contactList.add(contact2);
        
        doReturn(contactList).when(this.contactRepository).findAll();

        List<Contact> returnedContactServices = this.contactService.listAllContact();

        assertEquals(contactList.size(), returnedContactServices.size());
        assertEquals(contactList, returnedContactServices);
        verify(contactRepository, times(1)).findAll();

    }

    @Test
    void shouldReturnContactByExistsId(){
        Contact contact = new Contact(1L, "testeName", "teste@email.com", "888888");
        doReturn(Optional.of(contact)).when(this.contactRepository).findById(contact.getId());

        Contact returnedContact = this.contactService.findContactById(contact.getId());

        assertEquals(contact, returnedContact);
        assertEquals(contact.getId(), returnedContact.getId());
        verify(this.contactRepository, times(1)).findById(contact.getId());

    }

    @Test
    void shouldReturnContactByNotExistsId(){
        when(contactRepository.findById(2L)).thenReturn(Optional.empty());

        ResourceNotFoundException returnedException = assertThrows(ResourceNotFoundException.class, () -> this.contactService.findContactById(2L));
        assertEquals("Contact Not Found", returnedException.getMessage());
        verify(this.contactRepository, times(1)).findById(2L);
    }

    @Test
    void shouldReturnContactWhenItIsSave(){
        Contact contact = new Contact(1L, "testeName", "teste@email.com", "888888");
        when(this.contactRepository.save(contact)).thenReturn(contact);

        Contact returnedContact = this.contactService.saveContact(contact);

        assertEquals(contact, returnedContact);
        assertEquals(contact.getId(), returnedContact.getId());
        verify(this.contactRepository, times(1)).save(contact);

    }

    @Test
    void shouldReturnResourceAlreadyExistsExceptionWhenAContactAlreadyExists(){
        Contact contact = new Contact(1L, "testeName", "teste@email.com", "888888");
        when(this.contactRepository.findContactByEmail(contact.getEmail())).thenReturn(Optional.of(contact));

        ResourceAlreadyExistsException returnedException = assertThrows(
                ResourceAlreadyExistsException.class, 
                () -> this.contactService.saveContact(contact)
                );

        assertEquals("Contact Already exists", returnedException.getMessage());
        verify(this.contactRepository, times(1)).findContactByEmail(contact.getEmail());
    }


    @Test
    void shouldUpdateContact(){
        Long contactId = 1L;
        Contact contact = new Contact(1L, "testeName", "teste@email.com", "888888");
        Contact contactToBeUpdate = new Contact(1L, "testeNameToUpdade", "testeToUpdate@email.com", "888888");
        when(this.contactRepository.findById(contactId)).thenReturn(Optional.of(contact));
        when(this.contactRepository.save(contactToBeUpdate)).thenReturn(contactToBeUpdate);

        Contact returnedFoundContact = this.contactService.updateContact(contactId, contactToBeUpdate);

        assertEquals(contactToBeUpdate.getId(), returnedFoundContact.getId());
        assertEquals(contactToBeUpdate.getName(), returnedFoundContact.getName());
        assertEquals(contactToBeUpdate.getEmail(), returnedFoundContact.getEmail());
        assertEquals(contactToBeUpdate.getPhone(), returnedFoundContact.getPhone());

        verify(this.contactRepository, times(1)).findById(contactId);
        verify(this.contactRepository, times(1)).save(contactToBeUpdate);

    }

    @Test
    void shouldReturnExceptionWhenToUpdateContact(){
        Contact contact = new Contact(1L, "testeName", "teste@email.com", "888888");
        when(this.contactRepository.findById(2L)).thenReturn(Optional.empty());

        ResourceNotFoundException returnedException = assertThrows(
            ResourceNotFoundException.class,
         () -> this.contactService.updateContact(2L, contact));

         assertEquals("Contact Not Found", returnedException.getMessage());
         verify(this.contactRepository, times(1)).findById(2L);
         verify(this.contactRepository, times(0)).save(contact);
    }

    @Test
    void shouldDeleteContactWhenIdIsPassing(){
        Long contactId = 1L;
        Contact contact = new Contact(contactId, "testeName", "teste@email.com", "888888");
        when(this.contactRepository.findById(contactId)).thenReturn(Optional.of(contact));
        
        this.contactService.deleteContact(contactId);

        verify(this.contactRepository, times(1)).findById(contactId);
        verify(this.contactRepository, times(1)).deleteById(contactId);
    }

    @Test
    void shouldReturnExceptionContactWhenItNotExists(){
        Long contactId = 1L;
        Contact contact = new Contact(contactId, "testeName", "teste@email.com", "888888");
        when(this.contactRepository.findById(contactId)).thenReturn(Optional.empty());
        
        ResourceNotFoundException returnedException = assertThrows(
            ResourceNotFoundException.class, 
            () -> this.contactService.deleteContact(contactId)
            );

        assertEquals("Contact Not Found", returnedException.getMessage());

        verify(this.contactRepository, times(1)).findById(contactId);
        verify(this.contactRepository, times(0)).deleteById(contactId);
    }



}
