package com.example.crud.repository;

import com.example.crud.model.Contact;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContactRepository extends JpaRepository<Contact, Long>{

    Optional<Contact> findContactByEmail(String email);
    
}
