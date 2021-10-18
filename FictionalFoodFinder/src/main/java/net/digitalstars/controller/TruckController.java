package net.digitalstars.controller;

import java.util.List;
import net.digitalstars.model.Truck;
import net.digitalstars.service.TruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@CrossOrigin(origins="http://localhost:4200")
@RestController("truckController") @RequestMapping("/truck")
public class TruckController {
    
    private final TruckService truckService;
    
    @Autowired
    public TruckController(TruckService truckService){
        super();
        this.truckService = truckService;
    }
    
    @RequestMapping("/create")
    public Truck create(@RequestBody Truck truck){
        return truckService.create(truck);
    }
    
    @GetMapping(path="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public Truck findById(@RequestParam String id){
        return truckService.findById(id);
    }
    
    @GetMapping(path="/all", produces=MediaType.APPLICATION_JSON_VALUE)
    public List<Truck> findAll(){
        return truckService.findAll();
    }
    
//    @GetMapping(path="/{truckName}/menu", produces=MediaType.APPLICATION_JSON_VALUE)
//    public List<Item> getMenu(@RequestParam String truckName){
//        Truck truck = truckService.findById(truckName);
//        
//        if (truck == null)
//            return null;
//        
//        return truckService.getMenu(truck);
//    }
}//TruckController
