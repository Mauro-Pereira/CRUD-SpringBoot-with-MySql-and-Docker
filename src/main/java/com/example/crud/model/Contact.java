package com.example.crud.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Contact {

    @Id
    @Column(name="contact_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name="contact_name")
    private String name;

    @NonNull
    @Column(name="contact_email")
    private String email;

    @NonNull
    @Column(name="contact_phone")
    private String phone;

    public Contact(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    
    
}
