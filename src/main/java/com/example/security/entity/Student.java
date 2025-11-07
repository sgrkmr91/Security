package com.example.security.entity;

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
    private String name;
    private String lastName;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int rollNo;

    public static com.example.security.model.Student  entityToModel(Student student){
        com.example.security.model.Student  std = new com.example.security.model.Student(student.getName(), student.getLastName());
        return std;
    }
}
