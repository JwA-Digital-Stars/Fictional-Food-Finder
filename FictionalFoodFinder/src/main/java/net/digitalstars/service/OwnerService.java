package net.digitalstars.service;

import net.digitalstars.model.Owner;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.digitalstars.repository.OwnerRepository;

@Service("ownerService")
public class OwnerService{
    
    @Autowired
    private OwnerRepository ownersRepository;
    
    @Autowired
    public OwnerService(){
        super();
    }
    
    public void create(Owner owner){
        ownersRepository.save(owner);
    }
    
    public List<Owner> findAll(){
        return ownersRepository.findAll();
    }
    
    public Owner findById(String email){
        Optional<Owner> ownerOp = ownersRepository.findById(email);
        return ownerOp.orElse(null);
    }
    
    public boolean login(String email, String password){
        Owner owner = findById(email);
        return owner != null;
    }
    
    public void delete(Owner owner){
        ownersRepository.delete(owner);
    }

}
