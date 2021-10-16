package net.digitalstars.servicetests;

import java.util.List;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import net.digitalstars.model.Owner;
import net.digitalstars.repository.OwnerRepository;
import net.digitalstars.service.OwnerService;

public class OwnerServiceTests {
	
	@InjectMocks
	private OwnerService ownerService;
	
	@Mock
	private OwnerRepository ownerRepository;
	
	{
		MockitoAnnotations.openMocks(this);
	}
	
	@BeforeEach
	public void setUp() {
		ownerService = new OwnerService(ownerRepository);
	}
	
	@Test
	public void getAllOwners() {
		Owner owner1 = new Owner("Owner1@test.net","678", "Uncle Dane");
		Owner owner2 = new Owner("Owner2@test.net","456", "John Smith");
		
		Mockito.when(ownerRepository.findAll()).thenReturn(
				Arrays.asList(owner1, owner2)
				);
		
		List<Owner> listOfOwners = ownerService.findAll();
		assertEquals(2, listOfOwners.size());
	}
	
	@Test
	public void SuccessfulFindOwner() {
		Owner testOwner = new Owner("Owner1@test.net","678", "Uncle Dane");
		Optional<Owner> OptionalOwner = Optional.of(testOwner);
		
		Mockito.when(ownerRepository.findById(testOwner.getEmail())).thenReturn(OptionalOwner);
		
		assertEquals(testOwner, ownerService.findById(testOwner.getEmail()));
	}
	
	
	// This is testing the get by ID function to make sure it doesnt return the proper object when the fucntion is given an improper email.
	
	@Test
	public void FailedToFindOwner() {
		Owner testOwner = new Owner("Owner1@test.net","678", "Uncle Dane");
		Optional<Owner> OptionalOwner = Optional.of(testOwner);
		
		Mockito.when(ownerRepository.findById(testOwner.getEmail())).thenReturn(OptionalOwner);
		
		assertNotEquals(testOwner, ownerService.findById("NotExistingOwner@test.net"));
	}
	
	@Test
	public void NullLogInTest() {
		Owner testOwner = new Owner("Owner1@test.net","678", "Uncle Dane");
		Optional<Owner> OptionalOwner = Optional.of(testOwner);
		
		Mockito.when(ownerRepository.findById(testOwner.getEmail())).thenReturn(OptionalOwner);
		
		//assertEquals(true, false);
		
		assertFalse(ownerService.login("NotExistingOwner@test.net", "678"));
		
	}
	
	@Test
	public void SuccessfulLogInTest() {
		Owner testOwner = new Owner("Owner1@test.net","678", "Uncle Dane");
		Optional<Owner> OptionalOwner = Optional.of(testOwner);
		
		Mockito.when(ownerRepository.findById(testOwner.getEmail())).thenReturn(OptionalOwner);
		
		//assertEquals(true, false);
		
		assertTrue(ownerService.login("Owner1@test.net", "678"));
		
	}
	
	@Test
	public void WrongPasswordLogInTest() {
		Owner testOwner = new Owner("Owner1@test.net","678", "Uncle Dane");
		Optional<Owner> OptionalOwner = Optional.of(testOwner);
		
		Mockito.when(ownerRepository.findById(testOwner.getEmail())).thenReturn(OptionalOwner);
		
		//assertEquals(true, false);
		
		assertFalse(ownerService.login("Owner1@test.net", "wrongPassword"));
		
	}
	
	
	
}
