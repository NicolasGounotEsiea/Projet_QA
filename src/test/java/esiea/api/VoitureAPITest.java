package esiea.api;

import static io.restassured.RestAssured.given;
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
        RestAssured.baseURI = "http://localhost:8080/esieaBack/rest/voiture";
        RestAssured.port = 8080;
        //Unitaire
        vDao = mock(VoitureDAO.class);
        MockitoAnnotations.initMocks(this);
    }


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
    public void testGetVoituresJson() {
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
        try {
            doThrow(new SQLException("Erreur SQL simulée")).when(vDao).ajouterVoiture(org.mockito.ArgumentMatchers.any(Voiture.class));

        } catch (Exception e) {
        }
    }


    //test non passé, retour de l'api null
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
    }

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


}























