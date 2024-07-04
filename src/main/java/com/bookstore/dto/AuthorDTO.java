package com.bookstore.dto;

import com.bookstore.enums.Role;
import lombok.Data;

@Data
public class AuthorDTO {
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private String email;
    private String dob;
    private String phoneNumber;
    private String password;
    private String address;
    private Role role;
}
