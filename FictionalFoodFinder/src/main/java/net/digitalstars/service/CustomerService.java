package net.digitalstars.service;

import net.digitalstars.model.Customer;
import java.util.List;
import java.util.Optional;
import net.digitalstars.model.Truck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.digitalstars.repository.CustomerRepository;

@Service("customerService")
public class CustomerService{
    
    private final CustomerRepository customerRepository;
    @Autowired
    private TruckService truckService;
    
    private Customer currentCustomer;
    
    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        super();
        this.customerRepository = customerRepository;
    }

    public Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public void setCurrentCustomer(Customer customer) {
        currentCustomer = customer;
    }
    
    public String create(Customer customer){
        List<Customer> customers = findAll();
        
        for (Customer c : customers){
            if (c.getEmail().equals(customer.getEmail()))
                return "This email already has an account";
        }
        customerRepository.save(customer);
        return "Account successfully created!";
    }
    
    public void save(Customer customer){
        customerRepository.save(customer);
    }
    
    public List<Customer> findAll(){
        return customerRepository.findAll();
    }
    
    public Customer findById(String email){
        Optional<Customer> customerOp = customerRepository.findById(email);
        return customerOp.orElse(null);
    }
    
    public boolean login(String email, String password){
        if (!isLoggedIn()){
            currentCustomer = findById(email);
            if (currentCustomer == null)
                return false;
            if (currentCustomer.getPassword().equals(password))
                return true;
            currentCustomer = null;
        }
        return false;
    }
    
    public boolean logout(){
        if (isLoggedIn())
            currentCustomer = null;
        else
            return false;
        
        return true;
    }
    
    public boolean addFavorite(String truckName){
        if (isLoggedIn()){
            Truck truck = truckService.findById(truckName);
            if (currentCustomer.getFavorites().contains(truck))
                return false;

            currentCustomer.getFavorites().add(truck);

            customerRepository.save(currentCustomer);
            return true;
        }
        return false;
    }
    
    public boolean removeFavorite(String truckName){
        if (isLoggedIn()){
            Truck truck = truckService.findById(truckName);
            if (!currentCustomer.getFavorites().contains(truck))
                return false;

            currentCustomer.getFavorites().remove(truck);

            customerRepository.save(currentCustomer);
            return true;
        }
        return false;
    }
    
    public void delete(Customer customer){
        customerRepository.delete(customer);
    }
    
    public boolean isLoggedIn(){
        return currentCustomer != null;
    }

}
