package net.digitalstars.service;

import net.digitalstars.model.Owner;
import java.util.List;
import java.util.Optional;
import net.digitalstars.model.Item;
import net.digitalstars.model.Truck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.digitalstars.repository.OwnerRepository;

@Service("ownerService")
public class OwnerService{
    @Autowired
    private TruckService truckService;
    @Autowired
    private ItemService itemService;
    private final OwnerRepository ownerRepository;
    
    private Owner currentOwner;
    private Truck truck;
    
    @Autowired
    public OwnerService(OwnerRepository ownerRepository){
        super();
        this.ownerRepository = ownerRepository;
    }
    
    public Owner getCurrentOwner(){
        return currentOwner;
    }
    
    public void setCurrentOwner(Owner owner){
        currentOwner = owner;
    }
    
    public boolean create(Owner owner){
        List<Owner> owners = findAll();
        
        if (!owners.stream().noneMatch(o -> (o.getEmail().equals(owner.getEmail())))) {
            return false;
        }
        
        ownerRepository.save(owner);
        return true;
    }
    
    public Owner save(Owner owner){
        return ownerRepository.save(owner);
    }
    
    public List<Owner> findAll(){
        return ownerRepository.findAll();
    }
    
    public Owner findById(String email){
        Optional<Owner> ownerOp = ownerRepository.findById(email);
        return ownerOp.orElse(null);
    }
    
    public Owner login(Owner owner){
        //if (!isLoggedIn()){
            currentOwner = findById(owner.getEmail());
            if (currentOwner == null)
                return null;
            if (currentOwner.getPassword().equals(owner.getPassword())){
                return currentOwner;
            }
            return null;
            //currentOwner = null;
        //}
        //return false;
    }
    
    public boolean logout(){
        if (isLoggedIn())
            currentOwner = null;
        else
            return false;
        return true;
    }
    
    public void delete(Owner owner){
        ownerRepository.delete(owner);
    }
    
    public boolean addTruck(String truckName, String email){
        Owner owner = ownerRepository.findById(email).get();
        Truck temp = new Truck(truckName, owner);
        truck = truckService.create(temp);
        ownerRepository.save(owner);
        return true;
    }
    
    public boolean addItem(String name, float cost){        
        if (truck == null)
            return false;
        Item item = new Item(name, cost, truck);
        
        itemService.create(item);
        return true;
    }
    
    public boolean removeItem(int id){
        
        if (truck == null)
            return false;
        
        Item item = itemService.findById(id);
        
        if (item == null)
            return false;
        
        itemService.delete(item);
        truckService.save(truck);
        return true;
    }
    
    public boolean isLoggedIn(){
        return currentOwner != null;
    }
    
}
