package com.agenciatorus.api.Controller.dashboard;

import com.agenciatorus.api.Entities.Users;
import com.agenciatorus.api.Services.UserServices;
import com.agenciatorus.api.Services.UserServicesImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dashboard/user")
public class UserController {

    @Autowired
    private UserServices userServices;

    @GetMapping
    public List<Users> List(){
        return userServices.findAll();
    }

    @PostMapping
    public ResponseEntity<?> create (@Valid @RequestBody Users users, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userServices.save(users));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register (@Valid @RequestBody Users users, BindingResult result){
       users.setAdmin(false);
        return create(users, result);
    }


    //validacion
    private  ResponseEntity<?> validation (BindingResult result){
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo" + err.getField() + " " + err.getDefaultMessage());
        });
        return  ResponseEntity.badRequest().body(errors);
    }
}
