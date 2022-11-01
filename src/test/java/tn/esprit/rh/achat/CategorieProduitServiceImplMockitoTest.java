package tn.esprit.rh.achat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.repositories.CategorieProduitRepository;
import tn.esprit.rh.achat.services.CategorieProduitServiceImpl;

import java.util.*;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest

public class CategorieProduitServiceImplMockitoTest {

    @Mock
    CategorieProduitRepository cp;
    @InjectMocks
    CategorieProduitServiceImpl rs;
    @Test
    public void testAddCategorieProduit() {
        //given
        CategorieProduit produitCateg = new CategorieProduit("test", "test" );

        //when
        Mockito.when(this.cp.save(Mockito.any())).thenReturn(produitCateg);
        CategorieProduit savedSecteurActivite = rs.addCategorieProduit(produitCateg);


        //then
        assertNotNull(savedSecteurActivite.getLibelleCategorie());


    }

}
