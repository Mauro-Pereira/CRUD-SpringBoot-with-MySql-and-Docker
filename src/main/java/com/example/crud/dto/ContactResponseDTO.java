package com.example.crud.dto;

import com.example.crud.model.Contact;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ContactResponseDTO {

    private Long id;
    private String name;
    private String email;
    private String phone;

    public static ContactResponseDTO transformToDTO(Contact contact) {
        return new ContactResponseDTO(contact.getId(), contact.getName(), contact.getEmail(), contact.getPhone());
    }
}
