package net.digitalstars.service;

import net.digitalstars.model.Customer;
import net.digitalstars.model.Truck;
import net.digitalstars.model.TruckOwner;
import net.digitalstars.model.Users;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.digitalstars.repository.UsersRepository;

@Service("usersService")
public class TruckOwnerService{
    
    @Autowired
    private UsersRepository usersRepository;
    
    @Autowired
    public TruckOwnerService(){
        super();
    }
    
//    public void create(String email, String password, String name, String type){
//        Users user;
//        if (type.equals("customer"))
//            user = usersRepository.save(new Customer(email, password, name));
//        if (type.equals("owner"))
//      
//          user = usersRepository.save(new TruckOwner(email, password, name));
//    }
    
    public void create(Users users){
        if (users.getType().equals("customer"))
            usersRepository.save(new Customer(users.getEmail(), users.getPassword(), users.getName()));
        if (users.getType().equals("owner"))
            usersRepository.save(new TruckOwner(users.getEmail(), users.getPassword(), users.getName()));
    }
    
    public List<Users> findAll(){
        return usersRepository.findAll();
    }
    
    public Users getUser(int id){
        Optional<Users> userOp = usersRepository.findById(id);
        return userOp.orElse(null);
    }
    
    public boolean login(String email, String password, String type){
        List<Users> users = usersRepository.findAllByEmail(email);        
        return users.stream().anyMatch(u -> (u.getPassword().equals(password) && u.getType().equals(type)));
    }
    
    public boolean addFavorite(Customer customer, Truck truck){
        if (customer.getFavorites().contains(truck))
            return false;
        
        customer.getFavorites().add(truck);
        
        usersRepository.save(customer);
        return true;
    }
    
    public boolean removeFavorite(Customer customer, Truck truck){        
        if (!customer.getFavorites().contains(truck))
            return false;
            
        customer.getFavorites().remove(truck);
        
        usersRepository.save(customer);
        return true;
    }
    
    public void delete(Users user){
        usersRepository.delete(user);
    }

    public List<Truck> getFavorites(int id) {
        return null;
    }
}
