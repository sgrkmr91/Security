package com.example.security.controller;

import com.example.security.config.JwtHelper;
import com.example.security.model.Student;
import com.example.security.repository.StudentRepository;
import io.jsonwebtoken.Jwt;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/base")
@AllArgsConstructor
public class BaseController {

    private final StudentRepository stdRepo;
    private final UserDetailsService userDetailsService;
    private final JwtHelper jwtHelper;

    @GetMapping("/token")
    public String generateToken(){
        return jwtHelper.generateToken(userDetailsService.loadUserByUsername("Sagar"));
    }

    @GetMapping("/get")
    public ResponseEntity<List<com.example.security.entity.Student>> getStatus(){
        return new ResponseEntity<>(stdRepo.findAll(),HttpStatus.OK);
    }

    @PostMapping("/post")
    public ResponseEntity<Student> add(@RequestBody @Valid Student student){

        return new ResponseEntity<>
                (com.example.security.entity.Student.entityToModel
                        (stdRepo.save(Student.modelToEntity(student))),HttpStatus.OK);
    }
}
