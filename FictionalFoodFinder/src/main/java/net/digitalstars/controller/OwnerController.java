package net.digitalstars.controller;

import net.digitalstars.model.Owner;
import net.digitalstars.service.OwnerService;
import java.util.List;
import net.digitalstars.model.Truck;
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
import org.springframework.web.servlet.ModelAndView;

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
        return ownerService.create(owner);
    }
    
    @GetMapping(path="/all", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Owner>> findAll(){
        return new ResponseEntity<>(this.ownerService.findAll(), HttpStatus.OK); 
    }
    
    @GetMapping(path="/id", produces=MediaType.APPLICATION_JSON_VALUE)
    public String findById(@RequestParam String email){
        Owner owner = ownerService.findById(email);
        System.out.println(owner);
        return owner.toString();
    }
    
    @GetMapping(path="/login", produces=MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView login(@RequestParam String email, @RequestParam String password){
        boolean result = ownerService.login(email, password);
        
        if(result)
            return new ModelAndView("/id");
        else
            return new ModelAndView("/login");
    }
    
    @PostMapping(path="/addTruck", consumes=MediaType.APPLICATION_JSON_VALUE)
    public String addTruck(@RequestParam String email, @RequestBody String truckName){
        Owner owner = ownerService.findById(email);
        String result;
        if (owner != null){
            Truck truck = new Truck(truckName, owner);
            result = ownerService.addTruck(owner, truck);
        } else
            result = "Owner cannot be found";
        
        return result;
    }
    
}//OwnerController
