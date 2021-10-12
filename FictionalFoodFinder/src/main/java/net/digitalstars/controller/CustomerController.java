package net.digitalstars.controller;

import net.digitalstars.model.Customer;
import net.digitalstars.service.CustomerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("customerController")
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;
    
    //private final TruckService truckService;
    
    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
        //this.truckService = truckService;
    }
    
    @PostMapping(path="/create", consumes=MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody Customer customer){
        customerService.create(customer);
    }
    
    @GetMapping(path="/all", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customer>> findAll(){
        return new ResponseEntity<>(this.customerService.findAll(), HttpStatus.OK); 
    }
    
    @RequestMapping("/id")
    public String findById(@RequestParam String email){
        Customer customer = customerService.findById(email);
        System.out.println(customer);
        return customer.toString();
    }
    
    @RequestMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password){
        boolean result = customerService.login(email, password);
        
        if(result)
            return "Successful login!";
        else
            return "Invalid login";
    }
    
//    @RequestMapping("/customer/addFavorite")
//    public List<Truck> addFavorite(@RequestParam int id, @RequestParam String truckId){
//        Customers customer = customerService.getCustomer(id);
//        Truck truck = truckService.getTruck(truckId);
//        customerService.addFavorite((Customer) customer, truck);
//        
//        return customerService.getFavorites(id);
//    }
//    
//    @RequestMapping("/customer/removeFavorite")
//    public List<Truck> removeFavorite(@RequestParam int id, @RequestParam String truckId){
//        Customers customer = customerService.getCustomer(id);
//        Truck truck = truckService.getTruck(truckId);
//        
//        customerService.removeFavorite((Customer) customer, truck);
//        
//        return customerService.getFavorites(id);
//    }
//    
//    @RequestMapping("/customer/addTruck")
//    public void addTruck(@RequestParam int id, @RequestParam String truckName){
//        Customer customer = (Customer) customerService.getCustomer(id);
//        
//        if (customer != null){
//            Truck truck = truckService.create(truckName, customer);
//            customerService.addTruck(customer, truck);
//        }
//    }
    
}//CustomerController
