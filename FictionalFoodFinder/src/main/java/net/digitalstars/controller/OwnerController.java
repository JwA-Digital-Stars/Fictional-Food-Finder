package net.digitalstars.controller;

import net.digitalstars.model.Owner;
import net.digitalstars.service.OwnerService;
import java.util.List;
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
import org.springframework.web.servlet.ModelAndView;

@CrossOrigin(origins="http://localhost:4200")
@RestController("ownerController") @RequestMapping("/owner")
public class OwnerController {

    private final OwnerService ownerService;
            
    @Autowired
    public OwnerController(OwnerService ownerService){
        super();
        this.ownerService = ownerService;
    }
    
    @PostMapping(path="/create", consumes=MediaType.APPLICATION_JSON_VALUE)
    public String create(@RequestBody Owner owner){
        boolean result = ownerService.create(owner);
        
        if (result)
            return "Owner account successfully created";
        else
            return "This email already has an account"; 
    }
    
    @PostMapping(path="/create/all", consumes=MediaType.APPLICATION_JSON_VALUE)
    public String[] create(@RequestBody Owner[] owners){
        String[] response = new String[owners.length];
        
        for (int i = 0; i < owners.length; i++){
            boolean result = ownerService.create(owners[i]);
            
            if (result)
                response[i] = "Owner account successfully created";
            else
                response[i] = "This email already has an account";
        }
        
        return response;
    }
    
    @GetMapping(path="/all", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Owner>> findAll(){
        return new ResponseEntity<>(this.ownerService.findAll(), HttpStatus.OK); 
    }
    
    @GetMapping(path="/{email}", produces=MediaType.APPLICATION_JSON_VALUE)
    public String findById(@RequestParam String email){
        Owner owner = ownerService.findById(email);
        System.out.println(owner);
        return owner.toString();
    }
    
    @CrossOrigin(origins="http://localhost:4200")
    @RequestMapping("/login")
    public String login(@RequestBody Owner owner){
        Owner result = ownerService.login(owner);
        if (result != null)
            return result.toString();
        return "Invalid login";
    }
    
    @RequestMapping("/logout")
    public String logout(){
        boolean result = ownerService.logout();
        
        if (result)
            return "Successful logout";
        else
            return "Not logged in";
    }
    
    @RequestMapping("/addTruck")
    public boolean addTruck(@RequestParam("name") String name, @RequestParam("email") String email){
        boolean result = ownerService.addTruck(name, email);

        return result;
    }
    
    @RequestMapping("/loggedIn")
    public boolean loggedIn(){
        return ownerService.isLoggedIn();
    }
    
    @PostMapping(path="/truck/addItem", consumes=MediaType.APPLICATION_JSON_VALUE)
    public String addItem(@RequestBody String[] itemInfo){
        String name = itemInfo[0];
        float cost = Float.parseFloat(itemInfo[1]);
        boolean result = ownerService.addItem(name, cost);
        
        if (result)
            return String.format("%s added to truck.", name);
        else
            return "Could not add item.";
    }
    
    @PostMapping(path="/truck/remove", consumes=MediaType.APPLICATION_JSON_VALUE)
    public String removeItem(@RequestParam int id){

        boolean result = ownerService.removeItem(id);
        
        if (result)
            return "Removed item from truck.";
        else
            return "Could not remove item.";
    }
}//OwnerController
