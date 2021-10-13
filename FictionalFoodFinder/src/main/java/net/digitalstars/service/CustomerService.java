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
    
    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        super();
        this.customerRepository = customerRepository;
    }
    
    public void create(Customer customer){
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
        Customer customer = findById(email);        
        return customer != null;
    }
    
    public boolean addFavorite(Customer customer, String truckName){
        Truck truck = truckService.findById(truckName);
        if (customer.getFavorites().contains(truck))
            return false;
        
        customer.getFavorites().add(truck);
        
        customerRepository.save(customer);
        return true;
    }
    
    public boolean removeFavorite(Customer customer, String truckName){
        Truck truck = truckService.findById(truckName);
        if (!customer.getFavorites().contains(truck))
            return false;
            
        customer.getFavorites().remove(truck);
        
        customerRepository.save(customer);
        return true;
    }
    
    public void delete(Customer customer){
        customerRepository.delete(customer);
    }

}
