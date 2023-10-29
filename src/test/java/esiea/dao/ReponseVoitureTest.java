package esiea.dao;

import esiea.metier.Voiture;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReponseVoitureTest
{

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
        reponse.setData(voiture, 0);
        Voiture[] data = reponse.getData();
        assertNotNull(data);
        assertEquals(1, data.length);
        assertSame(voiture, data[0]);
    }

    @Test
    public void setDataNullTest() {
        ReponseVoiture reponse = new ReponseVoiture();
        Voiture voiture = new Voiture();


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
