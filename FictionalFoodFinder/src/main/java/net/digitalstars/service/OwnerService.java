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
    
    private final OwnerRepository ownerRepository;
    
    @Autowired
    public OwnerService(OwnerRepository ownerRepository){
        super();
        this.ownerRepository = ownerRepository;
    }
    
    public void create(Owner owner){
        ownerRepository.save(owner);
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
    
    public void addTruck(Owner owner, Truck truck){
        owner.setTruck(truck);
        ownerRepository.save(owner);
    }
}
