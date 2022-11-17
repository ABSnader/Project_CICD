package tn.esprit.rh.achat;


import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.anyLong;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;




import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.ProduitRepository;
import tn.esprit.rh.achat.repositories.StockRepository;
import tn.esprit.rh.achat.services.ProduitServiceImpl;
import tn.esprit.rh.achat.services.StockServiceImpl;
import static org.mockito.Mockito.times;



@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ProduitServiceMockTest {

   @Mock
   ProduitRepository produitRepository;
   @InjectMocks
   ProduitServiceImpl produitService;

   Produit p = Produit.builder().idProduit((long) 7).libelleProduit("javel").codeProduit("adf8").build();

   @Test
   public void AddProduit() {
   Produit p_add = new Produit();
   p_add.setLibelleProduit("javel add");
   p_add.setCodeProduit("adf8 add");

   Mockito.when(produitRepository.save(ArgumentMatchers.any(Produit.class))).thenReturn(p_add);

   Produit p_added = produitService.addProduit(p_add);

   assertEquals(p_add.getLibelleProduit(), p_added.getLibelleProduit());
   assertEquals(p_add.getCodeProduit(), p_added.getCodeProduit());
   verify(produitRepository).save(p_add);
   }

   @Test
   public void RetrieveProduitById() {

   Mockito.when(produitRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(p));
   Produit p_get = produitService.retrieveProduit((long) 7);
   assertNotNull(p_get);
   verify(produitRepository).findById(Mockito.anyLong());
   }

   @Test
   public void RetrieveAll() {
   List<Produit> produits = new ArrayList<>();
   produits.add(new Produit());

   when(produitRepository.findAll()).thenReturn(produits);

   List<Produit> expected = produitService.retrieveAllProduits();

   assertEquals(expected, produits);
   verify(produitRepository).findAll();
   }

   @Test
   public void DeleteProduit_ifFound() {
   Produit p = new Produit();
   p.setLibelleProduit("javel delete");
   p.setIdProduit(1L);

   when(produitRepository.findById(p.getIdProduit())).thenReturn(Optional.of(p));

   produitService.deleteProduit(p.getIdProduit());
   verify(produitRepository).deleteById(p.getIdProduit());
   }

   @Test
   public void DeleteException_ifnotFound() {
   try {
   Produit p = new Produit();
   p.setIdProduit(2L);
   p.setLibelleProduit("javeeel");

   when(produitRepository.findById(anyLong())).thenReturn(Optional.ofNullable(null));
   produitService.deleteProduit(p.getIdProduit());
   } catch (Exception e) {
   String expectedMessage = "entity with id";
   String actualMessage = e.getMessage();

   assertTrue(actualMessage.contains(expectedMessage));
   }
   }

   @Test
   public void EditProduit_ifFound() {
   	Produit p_edit = new Produit();
   p_edit.setIdProduit(3L);
   	p_edit.setLibelleProduit("javel edit");
   	Produit new_p_edit = new Produit();
   new_p_edit.setLibelleProduit("new javel edit");

   when(produitRepository.findById(p_edit.getIdProduit())).thenReturn(Optional.of(p_edit));
   p_edit = produitService.updateProduit(new_p_edit);

   verify(produitRepository).save(p_edit);
   }

   @Test
   public void EditException_ifnotFound() {
   try {
   	Produit p_edit = new Produit();
   	p_edit.setIdProduit(4L);
   	p_edit.setLibelleProduit("javel edit");

   Produit new_p_edit = new Produit();
   new_p_edit.setIdProduit(5L);
   new_p_edit.setLibelleProduit("new javel edit");

   when(produitRepository.findById(anyLong())).thenReturn(Optional.ofNullable(null));
   produitService.updateProduit(new_p_edit);

   } catch (Exception e) {
   String expectedMessage = "entity with id";
   String actualMessage = e.getMessage();

   assertTrue(actualMessage.contains(expectedMessage));
   }
   }
   }