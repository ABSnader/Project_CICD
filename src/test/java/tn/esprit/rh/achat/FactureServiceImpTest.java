package tn.esprit.rh.achat;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.rh.achat.entities.Facture;
import tn.esprit.rh.achat.repositories.FactureRepository;
import tn.esprit.rh.achat.services.IFactureService;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest

public class FactureServiceImpTest {
@Autowired
IFactureService factureservice;
    @Autowired
    FactureRepository facturerepository;

    @Test
    public void retrieveAllStocksTest() {
        List<Facture> facs = (List<Facture>) facturerepository.findAll();
        assertNotNull(facs);
    }
      @Test
    public void deletefactureTest() {
        Facture s = new Facture(301, 60);
        Facture fac = factureservice.addFacture(s);
        factureservice.cancelFacture(fac.getIdFacture());
    }



}
