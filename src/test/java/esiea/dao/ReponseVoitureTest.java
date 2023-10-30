package esiea.dao;

import esiea.metier.Voiture;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReponseVoitureTest
{

    @Test
    public void setDataNullTest() {
        ReponseVoiture reponse = new ReponseVoiture();
        Voiture voiture = new Voiture();
        voiture.setMarque("Toyaota");
        voiture.setModele("Serie E");
        voiture.setFinition("Finin");
        voiture.setCarburant(Voiture.Carburant.ELECTRIQUE);
        voiture.setKm(10);
        voiture.setAnnee(2000);
        voiture.setPrix(10);
        //Voiture[] data = null;
        //reponse.setData(data);
        assertNull(reponse.getData());
        reponse.setData(voiture,3);
        //System.out.println("Data : \n");
        //System.out.println(reponse.getData()[3]);
        assertEquals(4,reponse.getData().length);
        assertEquals(voiture, reponse.getData()[3]);


    }

    @Test
    public void getDataTest()
    {
        ReponseVoiture data = new ReponseVoiture();
        Voiture voit1 = new Voiture();
        Voiture voit2 = new Voiture();
        Voiture[] liste = new Voiture[] {voit1, voit2};


        data.setData(liste);
        assertEquals(data.getData(),liste);

    }

    @Test
    public void setData2Test() {
        ReponseVoiture reponse = new ReponseVoiture();
        Voiture voiture = new Voiture();
        Voiture dataVoit = new Voiture();
        Voiture[] tab = {dataVoit,null,null,null};
        reponse.setData(tab);
        Voiture[] data = reponse.getData();
        assertNotNull(data);
        reponse.setData(voiture, 2);

        assertSame(voiture, data[2]);
    }



    @Test
    public void getVolumeTest()
    {
        ReponseVoiture data = new ReponseVoiture();
        Voiture voiture = new Voiture();
        data.setVolume(0);
        int volume = data.getVolume();
        assertEquals(volume,0);
        data.setData(voiture, 0);

        assertNotNull(data.getData());
        assertEquals(1, data.getData().length);
        Voiture[] donnes = data.getData();
        assertEquals(voiture, donnes[0]);

    }






}
