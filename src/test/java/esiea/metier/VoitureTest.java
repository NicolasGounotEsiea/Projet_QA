package esiea.metier;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.bytebuddy.description.annotation.AnnotationList;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class VoitureTest {


    @Test
    public void getIdTest()
    {
        // INVALID ID
        Voiture voiture = new Voiture();
        voiture.setId(-1);
        assertFalse(voiture.check());

        //VALID ID
        Voiture voiture2 = new Voiture();
        voiture2.setId(1);
        assertEquals(1, voiture2.getId());

    }

    /*@Test
    public void setKmTest() {
        //Invalid KM entry
        Voiture voiture = new Voiture();
        try
        {
            voiture.setKm(-1);
            fail("Should throw exception when getting a negative number");
        }
        catch(IllegalArgumentException aExp){
            assert(aExp.getMessage().contains("negative number"));
        }


    }*/
    /*@Test(expected = IllegalArgumentException.class)
    public void setKmTest() {
        Voiture voiture = new Voiture();
        voiture.setKm(-1);
    }*/


    @Test
    public void getKmTest()
    {
        //VALID KM
        Voiture voiture2 = new Voiture();
        voiture2.setKm(1);
        assertEquals(1, voiture2.getKm());

    }

    @Test
    public void getMarqueTest()
    {
        //VALID MARQUE
        Voiture voiture = new Voiture();
        voiture.setMarque("Toyota");
        assertEquals("Toyota",voiture.getMarque());

    }

    @Test
    public void getModeleTest()
    {
        //VALID MODELE
        Voiture voiture = new Voiture();
        voiture.setModele("Serie E");
        assertEquals("Serie E",voiture.getModele());
    }

    @Test
    public void getFinition()
    {
        //VALID FINITION
        Voiture voiture = new Voiture();
        voiture.setFinition("Bleu");
        assertEquals("Bleu",voiture.getFinition());
    }

    @Test
    public void getCarburantTest()
    {
        //VALID CARUBRANT
        Voiture voiture = new Voiture();
        voiture.setCarburant(Voiture.Carburant.ESSENCE);
        assertEquals(Voiture.Carburant.ESSENCE,voiture.getCarburant());
    }


    @Test
    public void getAnneeTest()
    {
        //VALID ANNEE
        Voiture voiture = new Voiture();
        voiture.setAnnee(2000);
        assertEquals(2000,voiture.getAnnee());

    }

    @Test
    public void getPrixTest()
    {
        //VALID PRIX
        Voiture voiture = new Voiture();
        voiture.setPrix(1000);
        assertEquals(1000,voiture.getPrix());
    }

    @Test
    public void toStringWorkTest()
    {
        //Valide
        Voiture voiture = new Voiture();
        voiture.setId(0);
        voiture.setMarque("Toyota");
        voiture.setModele("Camry");
        voiture.setFinition("SE");
        voiture.setCarburant(Voiture.Carburant.ESSENCE);
        voiture.setKm(50000);
        voiture.setAnnee(2020);
        voiture.setPrix(25000);

        String voitureString = voiture.toString();

        assertEquals("{\"id\":0,\"marque\":\"Toyota\",\"modele\":\"Camry\",\"finition\":\"SE\",\"carburant\":\"ESSENCE\",\"km\":50000,\"annee\":2020,\"prix\":25000}", voitureString);


    }

    @Test
    public void toStringFailedTest() throws JsonProcessingException {
        Voiture voiture = new Voiture();
        voiture.toString();
    }

    @Test
    public void getCharTest() {
        assertEquals('E', Voiture.Carburant.ESSENCE.getChar());
        assertEquals('D', Voiture.Carburant.DIESEL.getChar());
        assertEquals('H', Voiture.Carburant.HYBRIDE.getChar());
        assertEquals('W', Voiture.Carburant.ELECTRIQUE.getChar());
    }

    @Test
    public void toStringCaruburantTest() {
        assertEquals("E", Voiture.Carburant.ESSENCE.toString());
        assertEquals("D", Voiture.Carburant.DIESEL.toString());
        assertEquals("H", Voiture.Carburant.HYBRIDE.toString());
        assertEquals("W", Voiture.Carburant.ELECTRIQUE.toString());
    }

    @Test
    public void CaruburantgetTest() {

        Voiture.Carburant carb = Voiture.Carburant.get("D");
        assertEquals(Voiture.Carburant.DIESEL,carb );

        Voiture.Carburant ESSENCE = Voiture.Carburant.get("E");
        assertEquals(Voiture.Carburant.ESSENCE,ESSENCE );

        Voiture.Carburant HYBRIDE = Voiture.Carburant.get("H");
        assertEquals(Voiture.Carburant.HYBRIDE,HYBRIDE );

        Voiture.Carburant ELECTRIQUE = Voiture.Carburant.get("W");
        assertEquals(Voiture.Carburant.ELECTRIQUE,ELECTRIQUE );

    }

    @Test
    public void CaruburantgetTestFalse() {


        assertEquals(null,Voiture.Carburant.get("00") );

    }








    @Test
    public void testCheckValidVoiture() {
        Voiture voiture = new Voiture();
        voiture.setId(1);
        voiture.setMarque("Toyota");
        voiture.setModele("Modele e");
        voiture.setFinition("euh");
        voiture.setCarburant(Voiture.Carburant.ELECTRIQUE);
        voiture.setKm(10);
        voiture.setAnnee(2000);
        voiture.setPrix(63);

        assertEquals(true, voiture.check());
    }

    @Test
    public void testCheckInvalidId() {
        Voiture voiture = new Voiture();
        voiture.setId(-1);
        assertFalse(voiture.check());
    }

    @Test
    public void testCheckInvalidMarque() {
        Voiture voiture = new Voiture();
        voiture.setMarque(null);
        assertFalse(voiture.check());
    }
    @Test
    public void testCheckInvalidMarqueEmpty() {
        Voiture voiture = new Voiture();
        voiture.setMarque("");
        assertFalse(voiture.check());
    }

    @Test
    public void testCheckInvalidModele() {
        Voiture voiture = new Voiture();
        voiture.setMarque("Toyaota");
        voiture.setModele(null);
        assertFalse(voiture.check());
    }
    @Test
    public void testCheckInvalidModeleEmpty() {
        Voiture voiture = new Voiture();
        voiture.setMarque("Toyaota");
        voiture.setModele("");
        assertFalse(voiture.check());
    }

    @Test
    public void testCheckInvalidFinition() {
        Voiture voiture = new Voiture();
        voiture.setMarque("Toyaota");
        voiture.setModele("Serie E");
        voiture.setFinition(null);
        assertFalse(voiture.check());
    }

    @Test
    public void testCheckInvalidFinitionEmpty() {
        Voiture voiture = new Voiture();
        voiture.setMarque("Toyaota");
        voiture.setModele("Serie E");
        voiture.setFinition("");
        assertFalse(voiture.check());
    }

    @Test
    public void testCheckInvalidCarburant() {
        Voiture voiture = new Voiture();
        voiture.setMarque("Toyaota");
        voiture.setModele("Serie E");
        voiture.setFinition("FIni");
        voiture.setCarburant(null);
        assertFalse(voiture.check());
    }

    @Test
    public void testCheckInvalidKM() {
        Voiture voiture = new Voiture();
        voiture.setMarque("Toyaota");
        voiture.setModele("Serie E");
        voiture.setFinition("Finin");
        voiture.setCarburant(Voiture.Carburant.ELECTRIQUE);
        voiture.setKm(-10);
        assertFalse(voiture.check());
    }

    @Test
    public void testCheckInvalidAnneeOld() {
        Voiture voiture = new Voiture();
        voiture.setMarque("Toyaota");
        voiture.setModele("Serie E");
        voiture.setFinition("Finin");
        voiture.setCarburant(Voiture.Carburant.ELECTRIQUE);
        voiture.setKm(10);
        voiture.setAnnee(1800);
        assertFalse(voiture.check());
    }



    @Test
    public void testCheckInvalidAnneeFuture() {
        Voiture voiture = new Voiture();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        voiture.setMarque("Toyaota");
        voiture.setModele("Serie E");
        voiture.setFinition("Finin");
        voiture.setCarburant(Voiture.Carburant.ELECTRIQUE);
        voiture.setKm(10);
        voiture.setAnnee(calendar.get(Calendar.YEAR) + 100);
        assertFalse(voiture.check());
    }


    @Test
    public void testCheckInvalidPrix() {
        Voiture voiture = new Voiture();
        voiture.setMarque("Toyaota");
        voiture.setModele("Serie E");
        voiture.setFinition("Finin");
        voiture.setCarburant(Voiture.Carburant.ELECTRIQUE);
        voiture.setKm(10);
        voiture.setAnnee(2000);
        voiture.setPrix(-10);
        assertFalse(voiture.check());
    }

    @Test
    public void testCheckInvalidPrixDeux() {
        Voiture voiture = new Voiture();
        voiture.setPrix(0);
        assertFalse(voiture.check());
    }


    @Test
    public void getTypeDonneesTest()
    {
        //Valid string
        String marque = Voiture.getTypeDonnee("marque");
        assertEquals("string", marque);

        //Int valid
        String id = Voiture.getTypeDonnee("id");
        assertEquals("entier", id);

        //String pas valid
        String non = Voiture.getTypeDonnee("inconnu");
        assertEquals("", non);

        //Int pas valid
        String idnon = Voiture.getTypeDonnee("123"); // Une chaîne numérique
        assertEquals("", idnon);

        //données vide
        String nul = Voiture.getTypeDonnee(null);
        assertEquals("", nul);

    }








}
