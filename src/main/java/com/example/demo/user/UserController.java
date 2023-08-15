package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path="api/")
public class UserController {

   private final UserService userService;

   @Autowired
   public UserController(UserService userService){
       this.userService = userService;
   }

   @PostMapping("/Signup")
   public ResponseEntity<String> registerUser(@RequestBody User user){
        return userService.AddNewUser(user);
   }

   @PostMapping("/Login")
   public ResponseEntity<Map<String,String>> loginUser(@RequestBody User user){
       return userService.findByEmail(user);
   }
}
