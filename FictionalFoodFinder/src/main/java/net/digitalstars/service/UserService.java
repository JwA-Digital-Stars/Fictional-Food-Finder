package net.digitalstars.service;

import com.digitalstars.model.Customer;
import com.digitalstars.model.Favorite;
import com.digitalstars.model.Truck;
import com.digitalstars.model.TruckOwner;
import com.digitalstars.model.User;
import com.digitalstars.repository.FavoritesRepository;
import com.digitalstars.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService{
    
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private FavoritesRepository favoriteRepo;
    
    public User create(String email, String password, String name, String type){
        if (type.equalsIgnoreCase("customer"))
            return userRepo.save(new Customer(email, password, name));
        else if (type.equalsIgnoreCase("truck owner"))
            return userRepo.save(new TruckOwner(email, password, name));
        else
            return null;
    }
    
    public List<User> getAll(){
        return userRepo.findAll();
    }
    
    public User getUser(int id){
        Optional<User> userOp = userRepo.findById(id);
        return userOp.orElse(null);
    }
    
    public boolean login(String email, String password, String type){
        List<User> users = userRepo.findAllByEmail(email);        
        return users.stream().anyMatch(u -> (u.getPassword().equals(password) && u.getType().equals(type)));
    }
    
    public boolean addFavorite(Customer customer, Truck truck){
        List<Favorite> favorites = favoriteRepo.findAllByCustomer(customer);
        if (customer.getFavorites().contains(truck))
            return false;
        
        customer.getFavorites().add(truck);
        
        userRepo.save(customer);
        return true;
    }
    
    public boolean removeFavorite(Customer customer, Truck truck){        
        if (!customer.getFavorites().contains(truck))
            return false;
            
        customer.getFavorites().remove(truck);
        
        userRepo.save(customer);
        return true;
    }
    
    public String addTruck(TruckOwner owner, Truck truck){
        owner.setTruck(truck);
        userRepo.save(owner);
        return truck.toString();
    }
    
    public void delete(User user){
        userRepo.delete(user);
    }

    public List<Truck> getFavorites(int id) {
        return null;
    }
}
