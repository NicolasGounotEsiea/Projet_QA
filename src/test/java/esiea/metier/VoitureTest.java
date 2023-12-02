package esiea.metier;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

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


    /**
     * Test vérifiant si la fonction getKm() renvoie la bonne valeur
     * voiture2 est une voiture crée pour le test à laquelle on assigne 1 km
     * getKm() doit renvoyer 1
     */
    @Test
    public void getKmTest()
    {
        //VALID KM
        Voiture voiture2 = new Voiture();
        voiture2.setKm(1);
        assertEquals(1, voiture2.getKm());

    }

    /**
     * Test vérifiant si la fonction getMarque() renvoie la bonne valeur
     * voiture est une voiture crée pour le test à laquelle on assigne la marque "Toyota"
     * getMarque() doit renvoyer Toyota
     */
    @Test
    public void getMarqueTest()
    {
        //VALID MARQUE
        Voiture voiture = new Voiture();
        voiture.setMarque("Toyota");
        assertEquals("Toyota",voiture.getMarque());

    }

    /**
     * Test vérifiant si la fonction getModele() renvoie la bonne valeur
     * voiture est une voiture crée pour le test à laquelle on assigne le modèle "Serie E"
     * getModele() doit renvoyer Serie E
     */
    @Test
    public void getModeleTest()
    {
        //VALID MODELE
        Voiture voiture = new Voiture();
        voiture.setModele("Serie E");
        assertEquals("Serie E",voiture.getModele());
    }


    /**
     * Test vérifiant si la fonction getFinition() renvoie la bonne valeur
     * voiture est une voiture crée pour le test à laquelle on assigne la finition "Bleu"
     * getFinition() doit renvoyer Bleu
     */
    @Test
    public void getFinitionTest()
    {
        //VALID FINITION
        Voiture voiture = new Voiture();
        voiture.setFinition("Bleu");
        assertEquals("Bleu",voiture.getFinition());
    }


    /**
     * Test vérifiant si la fonction getCarburant() renvoie la bonne valeur
     * voiture est une voiture crée pour le test à laquelle on assigne le carburant "ESSENCE"
     * getCarburant() doit renvoyer ESSENCE
     */
    @Test
    public void getCarburantTest()
    {
        //VALID CARUBRANT
        Voiture voiture = new Voiture();
        voiture.setCarburant(Voiture.Carburant.ESSENCE);
        assertEquals(Voiture.Carburant.ESSENCE,voiture.getCarburant());
    }

    /**
     * Test vérifiant si la fonction getAnneeTest() renvoie la bonne valeur
     * voiture est une voiture crée pour le test à laquelle on assigne l'année "2000"
     * getAnneeTest() doit renvoyer 2000
     */
    @Test
    public void getAnneeTest()
    {
        //VALID ANNEE
        Voiture voiture = new Voiture();
        voiture.setAnnee(2000);
        assertEquals(2000,voiture.getAnnee());

    }

    /**
     * Test vérifiant si la fonction getPrixTest() renvoie la bonne valeur
     * voiture est une voiture crée pour le test à laquelle on assigne le prix "1000"
     * getPrixTest() doit renvoyer 1000
     */
    @Test
    public void getPrixTest()
    {
        //VALID PRIX
        Voiture voiture = new Voiture();
        voiture.setPrix(1000);
        assertEquals(1000,voiture.getPrix());
    }

    /**
     * Test vérifiant si la fonction getChar() de l'énum Carburant renvoie la bonne valeur
     * Dans ce test, il n'est pas necessaire de créer une voiture,  on peut simplement vérifier que pour chaques type de carburant le bon "char" est renvoyé
     * E pour l'essence | D pour du diesel | H pour une hybride | W pour une électrique
     */
    @Test
    public void getCharTest() {
        assertEquals('E', Voiture.Carburant.ESSENCE.getChar());
        assertEquals('D', Voiture.Carburant.DIESEL.getChar());
        assertEquals('H', Voiture.Carburant.HYBRIDE.getChar());
        assertEquals('W', Voiture.Carburant.ELECTRIQUE.getChar());
    }


    /**
     * Test vérifiant si la fonction toString() renvoie la bonne valeur
     * voiture est une voiture crée pour le test à laquelle on assigne un Id, un prix,une marque,un modèle,une finition,un carburant, des km et uen année
     * On s'attends à ce que la fonction toString() renvoie une chaine de caractères qui détaille les caractéristiques de la voiture
     */
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

    /*@Test()
    public void toStringFailTest()
    {
        //Invalide
        ObjectMapper objectMapper = new ObjectMapper() {
            public String writeValueAsString(Object value) throws JsonProcessingException {
                throw new JsonProcessingException("Test exception") {
                };
            }
        };
        String erreur = objectMapper.toString();
        //JsonProcessingException json = JsonProcessingException();
        // Utiliser l'ObjectMapper modifié pour provoquer l'exception
        assertEquals("erreur",erreur);
       // assertEquals("", voitureString);

    }*/


    /**
     * Test vérifiant si la fonction toString() crée une exception si des caractéristiques ne sont pas assignés à une voiture
     * voiture est une voiture crée pour le test à laquelle on n'assigne aucune caractéristiques
     * On s'attends à ce que la fonction toString() renvoie une exception
     * **/
    @Test
    public void toStringFailedTest() throws JsonProcessingException {
        Voiture voiture = new Voiture();
        voiture.toString();
    }


    /**
     * Test vérifiant si la fonction toString() de l'énumération "Carburant" renvoie la bonne valeur
     * Dans ce test, il n'est pas necessaire de créer une voiture,  on peut simplement vérifier que pour chaques type de carburant le bon "string" est renvoyé
     * "E" pour l'essence | "D" pour du diesel | "H" pour une hybride | "W" pour une électrique
     */
    @Test
    public void toStringCaruburantTest() {
        assertEquals("E", Voiture.Carburant.ESSENCE.toString());
        assertEquals("D", Voiture.Carburant.DIESEL.toString());
        assertEquals("H", Voiture.Carburant.HYBRIDE.toString());
        assertEquals("W", Voiture.Carburant.ELECTRIQUE.toString());
    }


    /**
     * Test vérifiant si la fonction get() de l'énumération "Carburant" renvoie la bonne valeur
     * carb,essence,hybride et électrique sont des Carburants crée pour le test à laquelle on n'assigne la valeure "D","E","H" ou "W"
     *On s'attends à ce que les valeurs des varibles soient égalent aux différents carburants
     */
    @Test
    public void CaruburantgetTest() {

        Voiture.Carburant carb = Voiture.Carburant.get("D");
        assertEquals(Voiture.Carburant.DIESEL,carb);

        Voiture.Carburant ESSENCE = Voiture.Carburant.get("E");
        assertEquals(Voiture.Carburant.ESSENCE,ESSENCE );

        Voiture.Carburant HYBRIDE = Voiture.Carburant.get("H");
        assertEquals(Voiture.Carburant.HYBRIDE,HYBRIDE );

        Voiture.Carburant ELECTRIQUE = Voiture.Carburant.get("W");
        assertEquals(Voiture.Carburant.ELECTRIQUE,ELECTRIQUE );

    }

    /**
     * Test vérifiant si la fonction get() de l'énumération "Carburant" soit bien null si le carburant n'existe pas
     * on cherche le carburant "00" qui n'existe pas
     *On s'attends à ce que le résultat de la recherche soit null
     */
    @Test
    public void CaruburantgetTestFalse() {


        assertEquals(null,Voiture.Carburant.get("00") );

    }


    /**
     * Test vérifiant si la fonction check() de la classe "Voiture" renvoie la bonne information
     * voiture est une voiture crée pour le test à laquelle on assigne un id,un id,une marque,un modele,une finition,un carburant,des km ,une année et un prix valide
     * on s'attends à ce que la fonction check() renvoie true et valide bien la présence de toute les informations valides
     */
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

    /**
     * Test vérifiant si la fonction check() de la classe "Voiture" renvoie la bonne information
     * voiture est une voiture crée pour le test à laquelle on assigne un id invalide
     * on s'attends à ce que la fonction check() renvoie false
     */
    @Test
    public void testCheckInvalidId() {
        Voiture voiture = new Voiture();
        voiture.setId(-1);
        assertFalse(voiture.check());
    }

    /**
     * Test vérifiant si la fonction check() de la classe "Voiture" renvoie la bonne information
     * voiture est une voiture crée pour le test à laquelle on assigne une marque invalide
     * on s'attends à ce que la fonction check() renvoie false
     */
    @Test
    public void testCheckInvalidMarque() {
        Voiture voiture = new Voiture();
        voiture.setMarque(null);
        assertFalse(voiture.check());
    }

    /**
     * Test vérifiant si la fonction check() de la classe "Voiture" renvoie la bonne information
     * voiture est une voiture crée pour le test à laquelle on assigne une marque vide
     * on s'attends à ce que la fonction check() renvoie false
     */
    @Test
    public void testCheckInvalidMarqueEmpty() {
        Voiture voiture = new Voiture();
        voiture.setMarque("");
        assertFalse(voiture.check());
    }

    /**
     * Test vérifiant si la fonction check() de la classe "Voiture" renvoie la bonne information
     * voiture est une voiture crée pour le test à laquelle on assigne un modele invalide
     * on s'attends à ce que la fonction check() renvoie false
     */
    @Test
    public void testCheckInvalidModele() {
        Voiture voiture = new Voiture();
        voiture.setMarque("Toyaota");
        voiture.setModele(null);
        assertFalse(voiture.check());
    }

    /**
     * Test vérifiant si la fonction check() de la classe "Voiture" renvoie la bonne information
     * voiture est une voiture crée pour le test à laquelle on assigne un modele vide
     * on s'attends à ce que la fonction check() renvoie false
     */
    @Test
    public void testCheckInvalidModeleEmpty() {
        Voiture voiture = new Voiture();
        voiture.setMarque("Toyaota");
        voiture.setModele("");
        assertFalse(voiture.check());
    }

    /**
     * Test vérifiant si la fonction check() de la classe "Voiture" renvoie la bonne information
     * voiture est une voiture crée pour le test à laquelle on assigne une finition invalide
     * on s'attends à ce que la fonction check() renvoie false
     */
    @Test
    public void testCheckInvalidFinition() {
        Voiture voiture = new Voiture();
        voiture.setMarque("Toyaota");
        voiture.setModele("Serie E");
        voiture.setFinition(null);
        assertFalse(voiture.check());
    }

    /**
     * Test vérifiant si la fonction check() de la classe "Voiture" renvoie la bonne information
     * voiture est une voiture crée pour le test à laquelle on assigne une finition vide
     * on s'attends à ce que la fonction check() renvoie false
     */
    @Test
    public void testCheckInvalidFinitionEmpty() {
        Voiture voiture = new Voiture();
        voiture.setMarque("Toyaota");
        voiture.setModele("Serie E");
        voiture.setFinition("");
        assertFalse(voiture.check());
    }

    /**
     * Test vérifiant si la fonction check() de la classe "Voiture" renvoie la bonne information
     * voiture est une voiture crée pour le test à laquelle on assigne un Carburant invalide
     * on s'attends à ce que la fonction check() renvoie false
     */
    @Test
    public void testCheckInvalidCarburant() {
        Voiture voiture = new Voiture();
        voiture.setMarque("Toyaota");
        voiture.setModele("Serie E");
        voiture.setFinition("FIni");
        voiture.setCarburant(null);
        assertFalse(voiture.check());
    }

    /**
     * Test vérifiant si la fonction check() de la classe "Voiture" renvoie la bonne information
     * voiture est une voiture crée pour le test à laquelle on assigne un km invalide
     * on s'attends à ce que la fonction check() renvoie false
     */
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

    /**
     * Test vérifiant si la fonction check() de la classe "Voiture" renvoie la bonne information
     * voiture est une voiture crée pour le test à laquelle on assigne une année invalide (trop ancienne)
     * on s'attends à ce que la fonction check() renvoie false
     */
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



    /**
     * Test vérifiant si la fonction check() de la classe "Voiture" renvoie la bonne information
     * voiture est une voiture crée pour le test à laquelle on assigne une année invalide (dans le futur)
     * on s'attends à ce que la fonction check() renvoie false
     */
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


    /**
     * Test vérifiant si la fonction check() de la classe "Voiture" renvoie la bonne information
     * voiture est une voiture crée pour le test à laquelle on assigne un prix invalide (prix négatif)
     * on s'attends à ce que la fonction check() renvoie false
     */
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

    /**
     * Test vérifiant si la fonction check() de la classe "Voiture" renvoie la bonne information
     * voiture est une voiture crée pour le test à laquelle on assigne un prix invalide (0)
     * on s'attends à ce que la fonction check() renvoie false
     */
    @Test
    public void testCheckInvalidPrixDeux() {
        Voiture voiture = new Voiture();
        voiture.setPrix(0);
        assertFalse(voiture.check());
    }

    /**Test vérifiant si la fonction getTypeDonnee() renvoie la bonne valeur
     * Dans chaques cas on crée une variable string à laquelle on assigne chaques différents types de données possibles pour une voiture
     * on s'attend à ce que le bon type de données soit assigné à chaques variables
     */
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
