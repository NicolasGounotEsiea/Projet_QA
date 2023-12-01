package esiea.integration;


import esiea.metier.Voiture;
import io.restassured.http.ContentType;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class VoitureIntegrationTest {

    private static final String BASE_URL = "http://localhost:8080/esieaBack/rest"; // Update with your actual base URL

    @Test
    public void testGetVoituresJson() {

        given()
                .pathParam("param", "all")
                .when()
                .get(BASE_URL + "/voiture/get/{param}")
                .then()
                .log().all()  // Print all details of the response
                .statusCode(200)
                .body("voitures", not(empty()))  // Validate that "voitures" is not empty
                .body("voitures.size()", greaterThan(0));

    }

    @Test
    public void testAjouterVoiture() {

        Voiture voiture = new Voiture();
        voiture.setMarque("Testsdd");
        voiture.setModele("Camry");
        voiture.setFinition("Standard");
        voiture.setCarburant(Voiture.Carburant.ESSENCE);
        voiture.setKm(10000);
        voiture.setAnnee(2020);
        voiture.setPrix(25000);

        String requestBody = voiture.toString();

        given()
                .body(requestBody)
                .post(BASE_URL + "/voiture/add")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void testSupprimerVoiture() {
        given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .body("1")
                .when()
                .post("/voiture/del")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("succes", equalTo(true));
    }
    @Test
    public void testGetVoitureById() {
        given()
                .pathParam("param", '2') // Update with an existing ID in your database
                .when()
                .get(BASE_URL + "/voiture/get/{param}")
                .then()
                .log().all()
                .statusCode(200)
                .body("voiture", notNullValue())
                .body("volume", equalTo(1));
    }

    @Test
    public void testInvalidAjouterVoiture() {

        String invalidRequestBody = "{\"marque\":\"Testsinvalid\",\"modele\":\"Camry\",\"finition\":\"\",\"carburant\":\"ESSENCE\",\"km\":10000,\"annee\":2020,\"prix\":25000}";

        given()
                .body(invalidRequestBody)
                .post(BASE_URL + "/voiture/add")
                .then()
                .statusCode(200)
                .log().all()
                .body("succes", equalTo(false));
    }
}