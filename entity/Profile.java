package com.example.profileservice.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "profile_details")
public class Profile {

    public static final String SEQUENCE_NAME = "profile_sequence";
    @Id
    private int profile_id;


    @NotBlank(message = "FirstName must be entered")
    @Size(min = 3, max = 20)
    private String firstName;

    @NotBlank(message = "LastName must be entered")
    @Size(min = 3, max = 20)
    private String lastName;

    @NotBlank(message = "Email cannot be blank or null")
    @Size(min = 3, max = 40)
    @Email(message = "invalid email")
    private String email;

    @NotBlank(message = "Password cannot be blank or null")
    @Size(min = 3, max = 40)
    private String password;

    @NotBlank(message = "Address cannot be blank or null")
    @Size(min = 3, max = 40)
    private String address;

    @NotBlank(message = "Contact cannot be blank or null")
    @Size(min = 9, max = 10)
    private String contactNumber;
}
