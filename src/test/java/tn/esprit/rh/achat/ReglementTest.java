package tn.esprit.rh.achat;
import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.rh.achat.entities.Reglement;
import tn.esprit.rh.achat.repositories.ReglementRepository;
import tn.esprit.rh.achat.services.ReglementServiceImpl;

import static org.mockito.Mockito.times;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;


@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ReglementTest{
	
	@Mock
	private ReglementRepository reg;
	
	private Reglement r1 = new Reglement ();
	private Reglement r2 = new Reglement ();

	@InjectMocks
	ReglementServiceImpl ReglementService;
	
	@Test
	public void testAjoutReglement() {
	    when(reg.save(r1)).thenReturn(r1);
	    assertNotNull(r1);
	    assertEquals(r1, ReglementService.addReglement(r1)); 
	    System.out.println("ajout done!");
	}
	
	@Test
    public void testRetrieveReglement(){
	    	when(reg.findAll()).thenReturn(Stream
	    			.of(r1,r2)
	    			.collect(Collectors.toList()));
	    	assertEquals(2, ReglementService.retrieveAllReglements().size());
	    	System.out.println("Retrieve reglements works !");
	    }
    }


