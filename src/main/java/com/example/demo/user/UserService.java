package com.example.demo.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public ResponseEntity<String> AddNewUser(User user){
       Optional<User> userByEmail =
               userRepository.findUserByEmail(user.getEmail());
       if(userByEmail.isPresent()) {
           throw new IllegalStateException("Email already taken");
       }
       userRepository.save(user);
        return ResponseEntity.status(HttpStatus.OK).body("User data saved!!");
   }

    public ResponseEntity <Map<String,String>> findByEmail(User user) {
        Map<String,String> responseData = new HashMap<>();
        Optional<User> userByEmail =
                userRepository.findUserByEmail(user.getEmail());

        if(userByEmail.isEmpty() || user.getEmail().isEmpty()) {
            throw new IllegalStateException("Invalid email address");

        }else if(!(userByEmail.get().getPassword().equals(user.getPassword()))){
            throw new IllegalStateException("Invalid password");
        }else {
            responseData.put("id",Long.toString(userByEmail.get().getId()));
            responseData.put("name",userByEmail.get().getName());
            responseData.put("email",userByEmail.get().getEmail());

            return ResponseEntity.status(HttpStatus.OK).body(responseData);
        }
    }
}
