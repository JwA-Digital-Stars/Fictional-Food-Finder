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
    
    private final OwnerRepository ownerRepository;
    
    @Autowired
    public OwnerService(OwnerRepository ownerRepository){
        super();
        this.ownerRepository = ownerRepository;
    }
    
    public String create(Owner owner){
        List<Owner> owners = findAll();
        
        for (Owner o : owners){
            if (o.getEmail().equals(owner.getEmail()))
                return "This email already has an account";
        }
        ownerRepository.save(owner);
        return "Account successfully created!";
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
        Owner owner = findById(email);
        if (owner == null)
            return false;
        return owner.getPassword().equals(password);
    }
    
    public void delete(Owner owner){
        ownerRepository.delete(owner);
    }
    
    public String addTruck(Owner owner, Truck truck){
        truckService.save(truck);
        owner.setTruck(truck);
        ownerRepository.save(owner);
        
        return "Truck added!";
    }
}
