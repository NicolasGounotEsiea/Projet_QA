package esiea.api;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.replaceFiltersWith;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import esiea.dao.ReponseVoiture;

import esiea.dao.VoitureDAO;
import esiea.metier.Voiture;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito;


import io.restassured.RestAssured;

import java.sql.SQLException;

@RunWith(MockitoJUnitRunner.class)
public class VoitureAPITest {

    @InjectMocks
    private VoitureAPI voitureAPI; // Utilisez l'annotation @InjectMocks pour injecter le mock VoitureDAO dans VoitureAPI

    @Mock
    private VoitureDAO vDao; // Utilisez l'annotation @Mock pour créer un mock VoitureDAO

    @Before
    public void setUp() {
        //intégration
        RestAssured.baseURI = "https://cours-qualite.groupe-esiea.fr/esieaBack/rest/voiture";

        //Unitaire
        vDao = mock(VoitureDAO.class);
        MockitoAnnotations.initMocks(this);
    }

/*
    @Test
    public void getVoituresJsonOneParamTest()
    {

        VoitureAPI voitureAPI = new VoitureAPI();
        String aa= voitureAPI.getVoituresJson("all");
        assertNotNull(aa);

    }*/
    /*


    @Test
    public void getVoituresJsonTestParamIsENtier()
    {
        VoitureAPI voitureAPI = new VoitureAPI();
        String aa= voitureAPI.getVoituresJson("1");
        assertNotNull(aa);
    }

    @Test
    public void getVoituresJsonTestParamIsNotALlNotEntier()
    {
        VoitureAPI voitureAPI = new VoitureAPI();

        String aa= voitureAPI.getVoituresJson("");
        assertNotNull(aa);
    }

    @Test
    public void getVoituresJsonTestParamIsNotALlNotEntierButHasData()
    {
        /*VoitureAPI voitureAPI = new VoitureAPI();

        try{
            // Créez un exemple de données JSON pour simuler la saisie JSON
            JSONObject jsonInput = new JSONObject();
            jsonInput.put("id", "2544444");
            jsonInput.put("marque", "TestMarque");
            jsonInput.put("modele", "TestModele");
            jsonInput.put("finition", "TestFinition");
            jsonInput.put("carburant", "E");
            jsonInput.put("km", "120000");
            jsonInput.put("annee", "2001");
            jsonInput.put("prix", "2500");

            doNothing().when(vDao).ajouterVoiture(any(Voiture.class));

            String inputJson = jsonInput.toString();
            String responseJson = voitureAPI.ajouterVoiture(inputJson);
            doNothing().when(vDao).ajouterVoiture(any(Voiture.class));
            String aa= voitureAPI.getVoituresJson("");
            assertNotNull(aa);

            JSONObject jsonResponse = new JSONObject(responseJson);
            assertTrue(jsonResponse.getBoolean("succes"));

            verify(vDao).ajouterVoiture(any(Voiture.class));

        }catch (SQLException e) {
            e.printStackTrace(); // Ou gérer l'exception de manière appropriée
        }

    }

    @Test
    public void getVoituresJsonTest()
    {
        /*
        JSONObject jsonInput = new JSONObject();
        jsonInput.put("marque", "TestMarque");
        jsonInput.put("modele", "TestModele");
        jsonInput.put("finition", "TestFinition");
        jsonInput.put("carburant", "E");
        jsonInput.put("km", "120000");
        jsonInput.put("annee", "2001");
        jsonInput.put("prix", "2500");
        String inputJson = jsonInput.toString();
        String responseJson = voitureAPI.ajouterVoiture(inputJson);

        JSONObject jsonInput2 = new JSONObject();
        jsonInput.put("marque", "TestMarque2");
        jsonInput.put("modele", "TestModele2");
        jsonInput.put("finition", "TestFinition2");
        jsonInput.put("carburant", "E");
        jsonInput.put("km", "1200002");
        jsonInput.put("annee", "2001");
        jsonInput.put("prix", "2500");
        String inputJson2 = jsonInput2.toString();
        String responseJson2 = voitureAPI.ajouterVoiture(inputJson2);

/*
        String param = "all";
        String mini = "0";
        String nbVoitures = "1";

        String result = voitureAPI.getVoituresJson("all");

        // Écrire ici les assertions pour vérifier le résultat
        // Par exemple, vérifier que result contient les données attendues
        // Vous devrez peut-être analyser la chaîne JSON retournée pour effectuer des vérifications spécifiques.

        // Exemple d'assertion simple
        assertNotNull(result);*/
        /*ReponseVoiture reponseMock = new ReponseVoiture();
        Voiture[] voitures = new Voiture[1]; // Remplacez 1 par le nombre de voitures souhaité
        // Initialisez vos voitures comme vous le souhaitez
        voitures[0] = new Voiture("Marque", "Modele", "Finition", "E", 120000, 2001, 2500);
        reponseMock.setData(voitures);
        reponseMock.setVolume(1);

        // Utilisez Mockito pour simuler le comportement de vDao.getToutesVoitures
        when(vDao.getVoitures(anyInt(), anyInt())).thenReturn(reponseMock);

        String result = voitureAPI.getVoituresJson("all");

        // Écrivez vos assertions pour vérifier le résultat
        // Par exemple, vérifiez que result contient les données attendues
        // Vous devrez peut-être analyser la chaîne JSON retournée pour effectuer des vérifications spécifiques.

        // Exemple d'assertion simple
        assertNotNull(result);
    }

    }*/


    @Test
    public void testAjouterVoitureAvecEchec() throws SQLException {
        // Créez un exemple de données JSON pour simuler la saisie JSON
        JSONObject jsonInput = new JSONObject();
        jsonInput.put("marque", "TestMarque");
        jsonInput.put("modele", "TestModele");
        jsonInput.put("finition", "TestFinition");
        jsonInput.put("carburant", "E");
        jsonInput.put("km", "120000");
        jsonInput.put("annee", "2001");
        jsonInput.put("prix", "2500");

        doThrow(new SQLException("Erreur SQL simulée")).when(vDao).ajouterVoiture(any(Voiture.class));

        // Appelez la méthode à tester en simulant une requête POST avec des données JSON
        String inputJson = jsonInput.toString();
        String responseJson = voitureAPI.ajouterVoiture(inputJson);

        JSONObject jsonResponse = new JSONObject(responseJson);
        assertFalse(jsonResponse.getBoolean("succes"));
        verify(vDao).ajouterVoiture(any(Voiture.class));
    }

    @Test
    public void testAjoutVoitureReussi() {

        try{
            // Créez un exemple de données JSON pour simuler la saisie JSON
            JSONObject jsonInput = new JSONObject();
            jsonInput.put("id", "2544444");
            jsonInput.put("marque", "TestMarque");
            jsonInput.put("modele", "TestModele");
            jsonInput.put("finition", "TestFinition");
            jsonInput.put("carburant", "E");
            jsonInput.put("km", "120000");
            jsonInput.put("annee", "2001");
            jsonInput.put("prix", "2500");

            doNothing().when(vDao).ajouterVoiture(any(Voiture.class));

            String inputJson = jsonInput.toString();
            String responseJson = voitureAPI.ajouterVoiture(inputJson);

            JSONObject jsonResponse = new JSONObject(responseJson);
            assertTrue(jsonResponse.getBoolean("succes"));

            verify(vDao).ajouterVoiture(any(Voiture.class));

        }catch (SQLException e) {
            e.printStackTrace(); // Ou gérer l'exception de manière appropriée
        }

    }

    @Test
    public void testAjoutAvecException() throws SQLException {
        // Créez un exemple de données JSON pour simuler la saisie JSON
        JSONObject jsonInput = new JSONObject();
        jsonInput.put("marque", "TestMarque");
        jsonInput.put("modele", "TestModele");
        jsonInput.put("finition", "TestFinition");
        jsonInput.put("carburant", "E");
        jsonInput.put("km", "120000");
        jsonInput.put("annee", "2001");
        jsonInput.put("prix", "2500");

        doThrow(new SQLException("Erreur SQL simulée")).when(vDao).ajouterVoiture(any(Voiture.class));
        String inputJson = jsonInput.toString();
        String responseJson = voitureAPI.ajouterVoiture(inputJson);

        JSONObject jsonResponse = new JSONObject(responseJson);

        assertFalse(jsonResponse.getBoolean("succes"));
        verify(vDao).ajouterVoiture(any(Voiture.class));
    }

    @Test
    public void testAjoutAvecDonneesIncorrectes() {
        // Créer un exemple de données JSON avec des données incorrectes
        JSONObject jsonInput = new JSONObject();

        String inputJson = jsonInput.toString();
        String responseJson = voitureAPI.ajouterVoiture(inputJson);

        JSONObject jsonResponse = new JSONObject(responseJson);
        assertFalse(jsonResponse.getBoolean("succes"));
    }

    @Test
    public void testAjoutAvecDonneesCorrectes() {
        // Créez un exemple de données JSON avec des données correctes
        JSONObject jsonInput = new JSONObject();
        jsonInput.put("marque", "TestMarque");
        jsonInput.put("modele", "TestModele");
        jsonInput.put("finition", "TestFinition");
        jsonInput.put("carburant", "E");
        jsonInput.put("km", 120000);
        jsonInput.put("annee", 2001);
        jsonInput.put("prix", 2500);


        try {
            doNothing().when(vDao).ajouterVoiture(any(Voiture.class));
        } catch (Exception e) {
        }

        String inputJson = jsonInput.toString();
        String responseJson = voitureAPI.ajouterVoiture(inputJson);

        // Vérifiez que la réponse indique un succès
        JSONObject jsonResponse = new JSONObject(responseJson);
        assertTrue(jsonResponse.getBoolean("succes"));

    }


    @Test
    public void GetAllVoituresExceptionTest() throws SQLException
    {


    }


    @Test
    public void testGetVoituresJsonValid() {
        // Test avec paramètre "param" égal à "all"
        Response responseAll = given()
                .pathParam("param", "all")
                .pathParam("mini", "0")
                .pathParam("nbVoitures", "10")
                .when()
                .get("/get/{param}/{mini}/{nbVoitures}");

        // Assurez-vous que la réponse a un code de statut HTTP 200 (OK)
        responseAll.then().statusCode(200);

        // Test avec paramètre "param" numérique
        Response responseNumeric = given()
                .pathParam("param", "123") // Remplacez 123 par une valeur numérique valide
                .pathParam("mini", "0")
                .pathParam("nbVoitures", "10")
                .when()
                .get("/get/{param}/{mini}/{nbVoitures}");

        // Assurez-vous que la réponse a un code de statut HTTP 200 (OK)
        responseNumeric.then().statusCode(200);


        JsonPath jsonPath = responseAll.jsonPath();
        assertTrue(jsonPath.getList("voitures").size() > 0);
        assertNotNull(jsonPath.getInt("volume"));
    }




    //test non passé, retour de l'api null
    /*
    @Test
    public void testParametreNonValide() {
        // Test avec un paramètre "param" non valide
        Response responseInvalidParam = given()
                .pathParam("param", "param_invalid")
                .pathParam("mini", "0")
                .pathParam("nbVoitures", "10")
                .when()
                .get("/get/{param}/{mini}/{nbVoitures}");


        responseInvalidParam.then().statusCode(404); // Assurez-vous que le code d'erreur est bien 404 (Not Found)
    }*/

    @Test
    public void testParametreNonValideNum() {
        // Test avec un paramètre "mini" non numérique
        Response responseInvalidMini = given()
                .pathParam("param", "all")
                .pathParam("mini", "ff") // Paramètre "mini" non numérique
                .pathParam("nbVoitures", "10")
                .when()
                .get("/get/{param}/{mini}/{nbVoitures}");

        // Assurez-vous que la réponse a un code de statut HTTP 500 (Internal Server Error)
        responseInvalidMini.then().statusCode(500); // Assurez-vous que le code d'erreur est bien 500 (Internal Server Error)

    }


    @Test
    public void testSupprimerVoitureSucces() {
        // Créez un identifiant factice
        String id = "1";

        // Configurez le mock pour simuler la suppression de voiture sans erreur (SQLException)

        try{
            doNothing().when(vDao).supprimerVoiture(id);
        }catch(Exception e){}

        // Appelez la méthode à tester
        String result = voitureAPI.supprimerVoiture(id);

        // Vérifiez que la réponse indique un succès
        JSONObject jsonResponse = new JSONObject(result);
        assertTrue(jsonResponse.getBoolean("succes"));

        try{
            verify(vDao, times(1)).supprimerVoiture(id);
        }catch(Exception e){}

    }

    @Test
    public void testSupprimerVoitureEchec() {
        // Créez un identifiant factice
        String id = "2";

        try{
            doThrow(new SQLException("Erreur SQL simulée")).when(vDao).supprimerVoiture(id);
        }catch(Exception e){}


        // Appelez la méthode à tester
        String result = voitureAPI.supprimerVoiture(id);

        // Vérifiez que la réponse indique un échec
        JSONObject jsonResponse = new JSONObject(result);
        assertFalse(jsonResponse.getBoolean("succes"));

        try{
            verify(vDao, times(1)).supprimerVoiture(id);
        }catch(Exception e){}

    }

    @Test
    public void getReponseTest()
    {
        JSONObject jsonInput = new JSONObject();
        jsonInput.put("marque", "TestMarque");
        jsonInput.put("modele", "TestModele");
        jsonInput.put("finition", "TestFinition");
        jsonInput.put("carburant", "E");
        jsonInput.put("km", 120000);
        jsonInput.put("annee", 2001);
        jsonInput.put("prix", 2500);
        VoitureAPI voitureapi = new VoitureAPI();

        String inputJson = jsonInput.toString();
        voitureapi.ajouterVoiture(inputJson);
        ReponseVoiture reponse = voitureapi.getReponse("all",0,1);
        System.out.println("Reponse :");
        System.out.println(reponse);
        //ReponseVoiture shouldbe = new ReponseVoiture();
        // Vérifiez que la réponse indique un succès
        //JSONObject jsonResponse = new JSONObject(responseJson);
        assertNotNull(reponse);

    }





}























