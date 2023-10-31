package esiea.dao;

import esiea.metier.Voiture;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.exceptions.base.MockitoException;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import utils.Configuration;
import java.lang.reflect.Method;
import esiea.dao.VoitureDAO;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Configuration.class, VoitureDAO.class})
public class VoitureDaoTest {
    private VoitureDAO vdao ;
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;
    private Connection connectionMock;
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        vdao = new VoitureDAO();
        //vdao = mock(VoitureDAO.class);

        connectionMock = mock(Connection.class); // Initialisez connectionMock

        // Utilisez PowerMock pour simuler la classe Configuration.
        mockStatic(Configuration.class);

        when(Configuration.getConfig("bdd.serveur")).thenReturn("localhost");
        when(Configuration.getConfig("bdd.port")).thenReturn("3306");
        when(Configuration.getConfig("bdd.nom")).thenReturn("mydb");


    }



    @Test
    public void testGetUrlBaseWithDefaultConfiguration() {
        when(Configuration.getConfig("bdd.serveur")).thenReturn("localhost");
        when(Configuration.getConfig("bdd.port")).thenReturn("3306");
        when(Configuration.getConfig("bdd.nom")).thenReturn("mydb");

        String urlBase = vdao.getUrlBase();
        assertEquals("jdbc:mysql://localhost:3306/mydb", urlBase);
    }

   @Test
    public void testGetUrlBaseWithCustomConfiguration() {
        when(Configuration.getConfig("bdd.serveur")).thenReturn("customserver");
        when(Configuration.getConfig("bdd.port")).thenReturn("5432");
        when(Configuration.getConfig("bdd.nom")).thenReturn("customdb");

        String urlBase = vdao.getUrlBase();
        assertEquals("jdbc:mysql://customserver:5432/customdb", urlBase);
    }

    @Test
    public void testGetUrlBaseWithEmptyConfiguration() {
        when(Configuration.getConfig("bdd.serveur")).thenReturn("");
        when(Configuration.getConfig("bdd.port")).thenReturn("");
        when(Configuration.getConfig("bdd.nom")).thenReturn("");

        String urlBase = vdao.getUrlBase();

        assertEquals("jdbc:mysql://:/", urlBase);
    }

    @Test
    public void testAjouterVoiture() throws Exception {
        // Créez une instance de Voiture à ajouter
        Voiture voiture = new Voiture();
        voiture.setMarque("Toyota");
        voiture.setModele("Camry");
        voiture.setFinition("LE");
        voiture.setCarburant(Voiture.Carburant.ESSENCE);
        voiture.setKm(50000);
        voiture.setAnnee(2020);
        voiture.setPrix(25000);

        // Créez un mock de la classe VoitureDAO
        VoitureDAO spy = PowerMockito.spy(vdao);

        // Utilisez PowerMockito pour accéder à la méthode privée getConnexion
        Connection connectionMock = mock(Connection.class);
        PowerMockito.doReturn(connectionMock).when(spy, "getConnexion");

        // Utilisez Mockito pour simuler la préparation de la requête
        PreparedStatement statementMock = mock(PreparedStatement.class);
        when(connectionMock.prepareStatement(anyString())).thenReturn(statementMock);

        // Utilisez doReturn pour stubber la méthode getUrlBase() de VoitureDAO
        doReturn("jdbc:mysql://${{ secrets.BDD_SERVEUR_1 }}:${{ secrets.BDD_PORT_1 }}/${{ secrets.BDD_NOM_1 }}").when(spy, "getUrlBase");

        // Appelez la méthode à tester
        spy.ajouterVoiture(voiture);

        // Vérifiez que la requête préparée a été appelée avec les bonnes valeurs
        verify(statementMock).setString(1, "Toyota");
        verify(statementMock).setString(2, "Camry");
        verify(statementMock).setString(3, "LE");
        verify(statementMock).setString(4, Voiture.Carburant.ESSENCE.toString());
        verify(statementMock).setInt(5, 50000);
        verify(statementMock).setInt(6, 2020);
        verify(statementMock).setInt(7, 25000);

        // Vérifiez que la requête préparée a été exécutée
        verify(statementMock).executeUpdate();
    }

    /*@Test
    public void testGetVoitures() throws Exception {
        // Créez une instance de VoitureDAO
        VoitureDAO spy = PowerMockito.spy(vdao);

        // Utilisez PowerMockito pour espionner la méthode privée getConnexion
        PowerMockito.doReturn(connectionMock).when(spy, "getConnexion");

        // Utilisez PowerMockito pour simuler la méthode privée getUrlBase
        PowerMockito.when(spy, PowerMockito.method(VoitureDAO.class, "getUrlBase")).withNoArguments().thenReturn("jdbc:h2:mem:test;MODE=MySQL");

        // Créez des critères factices pour le test
        HashMap<String, String> criteres = new HashMap<>();
        criteres.put("marque", "Toyota");
        criteres.put("annee", "2020");
        criteres.put("finition", "tamere");
        criteres.put("carburant", "E");
        criteres.put("km", "2020");
        criteres.put("prix", "2020");
        criteres.put("modele", "sd");

        int mini = 0;
        int nbVoitures = 10;

        // Créez des mocks pour la préparation de la requête
        PreparedStatement statementMock = mock(PreparedStatement.class);
        ResultSet resultSetMock = mock(ResultSet.class);

        // Stubbez la préparation de la requête
        when(connectionMock.prepareStatement(anyString())).thenReturn(statementMock);

        // Stubbez l'exécution de la requête pour retourner un ensemble de résultats simulés
        when(statementMock.executeQuery()).thenReturn(resultSetMock);
        when(resultSetMock.next()).thenReturn(true, false); // Indique qu'il y a au moins une ligne
        when(resultSetMock.getInt(1)).thenReturn(5); // Exemple de nombre total de résultats

        // Appelez la méthode à tester
        ReponseVoiture reponse = spy.getVoitures(criteres, mini, nbVoitures);

        // Vérifiez que la requête SQL a été correctement générée
        verify(statementMock).setString(1, "Toyota");
        verify(statementMock).setString(2, "2020");
        verify(statementMock).setString(3, "tamere");
        verify(statementMock).setString(4, "E");
        verify(statementMock).setString(5, "2020");
        verify(statementMock).setString(6, "2020");
        verify(statementMock).setString(7, "sd");
        verify(statementMock).setInt(8, nbVoitures);
        verify(statementMock).setInt(9, mini);

        // Vérifiez que les données ont été correctement extraites de ResultSet
        Voiture[] data = reponse.getData();
        assertEquals(1, data.length); // Une ligne de données simulées
        assertEquals(5, reponse.getVolume()); // Le nombre total simulé

        // Assurez-vous de fermer la connexion (vous pouvez le vérifier en utilisant Mockito.verify)
        verify(connectionMock).close();
    }*/





}