package tn.esprit.rh.achat;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.entities.SecteurActivite;
import tn.esprit.rh.achat.repositories.SecteurActiviteRepository;
import tn.esprit.rh.achat.services.SecteurActiviteServiceImpl;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class secteurActiviteTest {
	
	@Mock
	private SecteurActiviteRepository sa;
	
	private SecteurActivite s1 = new SecteurActivite ();

	
	@InjectMocks
	SecteurActiviteServiceImpl SecteurService;
	
	@Test
	public void testAddSecteur() {
	    when(sa.save(s1)).thenReturn(s1);
	    assertNotNull(s1);
	    assertEquals(s1, SecteurService.addSecteurActivite(s1)); 
	    System.out.println("the add function works!");
	}
	
	@Test 
    public void testupdateSecteur() {
    	when(sa.save(s1)).thenReturn(s1);
    	assertNotNull(s1);
    	assertEquals(s1, SecteurService.updateSecteurActivite(s1));
    	System.out.println("Update works !");
    }}
