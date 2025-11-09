package com.example.security.entity;

import com.example.security.validator.PasswordValidation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student {
    @JsonIgnore
//    @PasswordValidation
    private String password;
    private String name;
    private String lastName;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int rollNo;

    public static com.example.security.model.Student  entityToModel(Student student){
        com.example.security.model.Student  std = new com.example.security.model.Student(student.getName(), student.getLastName(),student.getPassword());
        return std;
    }
}
