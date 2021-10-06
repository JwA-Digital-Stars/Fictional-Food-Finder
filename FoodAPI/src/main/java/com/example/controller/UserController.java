package com.example.controller;

import com.example.model.User;
import com.example.service.TruckService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired 
    private TruckService truckService;
    
    @RequestMapping(path = "/createUser", method = RequestMethod.POST)
    public String create(@RequestParam String email, @RequestParam String password, @RequestParam String name, @RequestParam String type){
        User user = userService.create(email, password, name, "customer");
        return user.toString();
    }
    
    @RequestMapping("/user/{id}")
    public String getUser(@RequestParam String email, @RequestParam String type){
        User user = userService.getUser(email, type);
        return user.toString();
    }
    
    @RequestMapping("/addFavorite")
    
    
    
    
}//UserController
