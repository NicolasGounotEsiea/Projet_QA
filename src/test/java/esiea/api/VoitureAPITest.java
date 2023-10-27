package esiea.api;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import esiea.dao.VoitureDAO;
import esiea.metier.Voiture;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;


import java.sql.SQLException;

@RunWith(MockitoJUnitRunner.class)
public class VoitureAPITest {

    @InjectMocks
    private VoitureAPI voitureAPI; // Utilisez l'annotation @InjectMocks pour injecter le mock VoitureDAO dans VoitureAPI

    @Mock
    private VoitureDAO vDao; // Utilisez l'annotation @Mock pour créer un mock VoitureDAO

    @Before
    public void setUp() {
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












}
