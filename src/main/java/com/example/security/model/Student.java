package com.example.security.model;


import com.example.security.validator.PasswordValidation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Validated
public class Student {
    @NotNull
    @NotEmpty(message = "name should not empty")
    @Size(min = 1,max = 10,message = "name should not too short")
    private String name;
    @NotNull
    @NotEmpty
    @Size(min = 0,max = 5,message = "last name should not be  empty")
    private String lastName;

   // @JsonIgnore
    @PasswordValidation
    private String password;

    public static com.example.security.entity.Student modelToEntity(Student student){
        com.example.security.entity.Student studentEntity = new com.example.security.entity.Student();
        studentEntity.setName(student.getName());
        studentEntity.setLastName(student.getLastName());
        studentEntity.setPassword(student.getPassword());
        return studentEntity;
    }
}
