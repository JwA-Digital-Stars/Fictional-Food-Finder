package net.digitalstars.service;

import net.digitalstars.model.Owner;
import java.util.List;
import java.util.Optional;
import net.digitalstars.model.Truck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.digitalstars.repository.OwnerRepository;

@Service("ownerService")
public class OwnerService{
    
    @Autowired
    private TruckService truckService;
    @Autowired
    private OwnerRepository ownerRepository;
    
    private Owner currentOwner;
    
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
    
    public boolean login(String email, String password){
        if (!isLoggedIn()){
            currentOwner = findById(email);
            if (currentOwner == null)
                return false;
            if (currentOwner.getPassword().equals(password))
                return true;
            currentOwner = null;
        }
        return false;
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
    
    public boolean addTruck(String truckName){
        if (isLoggedIn()){
            Truck truck = new Truck(truckName, currentOwner);
            truckService.create(truck);
            ownerRepository.save(currentOwner);
            return true;
        } else
            return false;
    }
    
    public boolean isLoggedIn(){
        return currentOwner != null;
    }
}
