package net.digitalstars.servicetests;

import java.util.List;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//import org.junit.Assert;
//import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
//import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest;

import net.digitalstars.model.Customer;
import net.digitalstars.repository.CustomerRepository;
import net.digitalstars.service.CustomerService;


@SpringBootTest
public class CustomerServiceTests {
	
	@InjectMocks
	private CustomerService customerService;
	
	@Mock
	private CustomerRepository customerRepository;
	
	{
		MockitoAnnotations.openMocks(this);
	}
	
	@BeforeEach
	public void setUp() {
		customerService = new CustomerService(customerRepository);
	}
	
	@Test
	public void FindAllCustomers() {
		Customer customer1 = new Customer("test@gmail.com","123","John Smith");
		Customer customer2 = new Customer("test2@gmail.com","1234","John Adam");
		
		Mockito.when(customerRepository.findAll()).thenReturn(
				Arrays.asList(customer1,
						customer2)
				);
		List<Customer> listOfCustomers = customerService.findAll();
		assertEquals(2, listOfCustomers.size());
	}
	
	
	@Test
	public void FindSpecificCustomer() {
		Customer TestCustomer = new Customer("test@gmail.com","123","John Smith");
		Optional<Customer> OptionalCustomer = Optional.of(TestCustomer);
		
		Mockito.when(customerRepository.findById(TestCustomer.getEmail())).thenReturn(OptionalCustomer);
		
		assertEquals(TestCustomer, customerService.findById("test@gmail.com"));
	}
	
	@Test
	public void FalseFindSpecificCustomer() {
		Customer TestCustomer = new Customer("test@gmail.com","123","John Smith");
		Optional<Customer> OptionalCustomer = Optional.of(TestCustomer);
		
		Mockito.when(customerRepository.findById(TestCustomer.getEmail())).thenReturn(OptionalCustomer);
		
		assertNotEquals(TestCustomer, customerService.findById("NotExistingCustomer@test.net"));
	}
	
	@Test
	public void NullLogInTest() {
		Customer TestCustomer = new Customer("test@gmail.com","123","John Smith");
		Optional<Customer> OptionalCustomer = Optional.of(TestCustomer);
		
		Mockito.when(customerRepository.findById(TestCustomer.getEmail())).thenReturn(OptionalCustomer);
		
		assertFalse(customerService.login("test2@gmail.com", "123"));
	}
	
	@Test
	public void WrongPasswordLogInTest() {
		Customer TestCustomer = new Customer("test@gmail.com","123","John Smith");
		Optional<Customer> OptionalCustomer = Optional.of(TestCustomer);
		
		Mockito.when(customerRepository.findById(TestCustomer.getEmail())).thenReturn(OptionalCustomer);
		
		assertFalse(customerService.login("test@gmail.com", "1234"));
	}
	
	@Test
	public void SuccessfulLogInTest() {
		Customer TestCustomer = new Customer("test@gmail.com","123","John Smith");
		Optional<Customer> OptionalCustomer = Optional.of(TestCustomer);
		
		Mockito.when(customerRepository.findById(TestCustomer.getEmail())).thenReturn(OptionalCustomer);
		
		assertTrue(customerService.login("test@gmail.com", "123"));
	}

}
