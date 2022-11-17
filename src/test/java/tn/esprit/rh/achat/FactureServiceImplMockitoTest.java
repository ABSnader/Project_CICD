package tn.esprit.rh.achat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.rh.achat.entities.Facture;
import tn.esprit.rh.achat.repositories.FactureRepository;
import tn.esprit.rh.achat.services.FactureServiceImpl;

import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest

public class FactureServiceImplMockitoTest {

    @Mock
    FactureRepository cp;
    @InjectMocks
    FactureServiceImpl rs;
    @Test
    public void testAddCategorieProduit() {
        //given
        Facture fac = new Facture(10, 10 );

        //when
        Mockito.when(this.cp.save(Mockito.any())).thenReturn(fac);
        Facture savedfac = rs.addFacture(fac);


        //then
        assertNull(savedfac.getIdFacture());


    }

}
