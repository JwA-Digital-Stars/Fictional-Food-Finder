package net.digitalstars.controller;

import net.digitalstars.model.Customer;
import net.digitalstars.service.CustomerService;
import java.util.List;
import net.digitalstars.model.Truck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("customerController") @RequestMapping("/customer")
public class CustomerController {
    
    private final CustomerService customerService;
        
    @Autowired
    public CustomerController(CustomerService customerService){
        super();
        this.customerService = customerService;
    }
    
    @CrossOrigin(origins="http://localhost:4200")
    @RequestMapping("/create")
    public void create(@RequestBody Customer customer){
        customerService.create(customer);
    }
    
    @GetMapping(path="/all", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customer>> findAll(){
        return new ResponseEntity<>(this.customerService.findAll(), HttpStatus.OK); 
    }
    
    @GetMapping(path="/id", produces=MediaType.APPLICATION_JSON_VALUE)
    public String findById(@RequestParam String email){
        Customer customer = customerService.findById(email);
        System.out.println(customer);
        return customer.toString();
    }
    
    @RequestMapping("/login")
    public String login(@RequestBody Customer customer){
        boolean result = customerService.login(customer);
        
        if(result)
            return "Successful login";
        else
            return "Invalid login";
    }
    
    @RequestMapping("/logout")
    public String logout(){
        boolean result = customerService.logout();
        
        if (result)
            return "Successful logout";
        else
            return "Not logged in";
    }
    
    @PostMapping(path="/addFavorite", consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public List<Truck> addFavorite(@RequestParam String truckName){
        customerService.addFavorite(truckName);
        
        return customerService.getCurrentCustomer().getFavorites();
    }
    
    @PostMapping(path="/removeFavorite", consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public List<Truck> removeFavorite(@RequestParam String truckName){
        
        customerService.removeFavorite(truckName);
        
        return customerService.getCurrentCustomer().getFavorites();
    }
        
}//CustomerController
