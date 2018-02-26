/**
 * Trieda Mapa vytvára celú hrateľnú oblasť pomocou Objektov a stien.
 */
package strielackadavo.prostredie;

import java.util.ArrayList;
import strielackadavo.prostredie.objekty.IObjekt;
import strielackadavo.prostredie.objekty.Lekarnicka;
import strielackadavo.prostredie.objekty.Mina;
import strielackadavo.prostredie.objekty.Naboje;
import strielackadavo.prostredie.objekty.Teleport;

/**
 *
 * @author Pavličko.D
 */
public class Mapa {
    private final ArrayList<Stena> zoznamStien;
    private final ZoznamObjektov zoznamObjektov;
    

    /**
     * Konštruktor vytvára finálny vzhľad bojiska, vytvára objekty a steny, ktoré
     * budú mať vplyv na hráčov.
     */
    public Mapa() {
        HraciaPlocha hraciaPlocha = new HraciaPlocha();
        
        // Steny
        this.zoznamStien = new ArrayList<>();
        
        Stena stenaHorna = new Stena(0, 0, 1000, 50);
        this.zoznamStien.add(stenaHorna);
        Stena stenaLava = new Stena(2, 0, 50, 850);
        this.zoznamStien.add(stenaLava);
        Stena stenaPrava = new Stena(1, 19, 50, 850);
        this.zoznamStien.add(stenaPrava);
        Stena stenaDolna = new Stena(19, 0, 1000, 50);
        this.zoznamStien.add(stenaDolna);
        Stena stenaA = new Stena(4, 4, 50, 500);
        this.zoznamStien.add(stenaA);
        Stena stenaB = new Stena(4, 6, 400, 50);
        this.zoznamStien.add(stenaB);
        Stena stenaC = new Stena(4, 15, 50, 500);
        this.zoznamStien.add(stenaC);
        Stena stenaBunkra1 = new Stena(7, 8, 50, 250);
        this.zoznamStien.add(stenaBunkra1);
        Stena stenaBunkra2 = new Stena(7, 11, 50, 250);
        this.zoznamStien.add(stenaBunkra2);
        
        
        // Objekty 
        this.zoznamObjektov = new ZoznamObjektov();
        
        // Míny
        Mina mina1 = new Mina("mina1", 2, 2);
        this.zoznamObjektov.pridajObjekt(mina1);
        Mina mina2 = new Mina("mina2", 1, 8);
        this.zoznamObjektov.pridajObjekt(mina2);
        Mina mina3 = new Mina("mina3", 3, 11);
        this.zoznamObjektov.pridajObjekt(mina3);
        Mina mina4 = new Mina("mina4", 5, 14);
        this.zoznamObjektov.pridajObjekt(mina4);
        Mina mina5 = new Mina("mina5", 7, 6);
        this.zoznamObjektov.pridajObjekt(mina5);
        Mina mina6 = new Mina("mina6", 8, 16);
        this.zoznamObjektov.pridajObjekt(mina6);
        Mina mina7 = new Mina("mina7", 11, 18);
        this.zoznamObjektov.pridajObjekt(mina7);
        Mina mina8 = new Mina("mina8", 13, 13);
        this.zoznamObjektov.pridajObjekt(mina8);
        Mina mina9 = new Mina("mina9", 14, 10);
        this.zoznamObjektov.pridajObjekt(mina9);
        Mina mina10 = new Mina("mina10", 16, 1);
        this.zoznamObjektov.pridajObjekt(mina10);
        Mina mina11 = new Mina("mina11", 18, 17);
        this.zoznamObjektov.pridajObjekt(mina11);
        
        
        // Lekárničky
        Lekarnicka lekarnicka1 = new Lekarnicka("lekarnicka1", 1, 18);
        this.zoznamObjektov.pridajObjekt(lekarnicka1);
        Lekarnicka lekarnicka2 = new Lekarnicka("lekarnicka2", 9, 9);
        this.zoznamObjektov.pridajObjekt(lekarnicka2);
        Lekarnicka lekarnicka3 = new Lekarnicka("lekarnicka3", 9, 10);
        this.zoznamObjektov.pridajObjekt(lekarnicka3);
        Lekarnicka lekarnicka4 = new Lekarnicka("lekarnicka4", 18, 1);
        this.zoznamObjektov.pridajObjekt(lekarnicka4);
        
        
        // Náboje
        Naboje naboje1 = new Naboje("naboje1", 1, 1);
        this.zoznamObjektov.pridajObjekt(naboje1);
        Naboje naboje2 = new Naboje("naboje2", 4, 5);
        this.zoznamObjektov.pridajObjekt(naboje2);
        Naboje naboje3 = new Naboje("naboje3", 4, 14);
        this.zoznamObjektov.pridajObjekt(naboje3);
        Naboje naboje4 = new Naboje("naboje4", 8, 9);
        this.zoznamObjektov.pridajObjekt(naboje4);
        Naboje naboje5 = new Naboje("naboje5", 8, 10);
        this.zoznamObjektov.pridajObjekt(naboje5);
        Naboje naboje6 = new Naboje("naboje6", 10, 9);
        this.zoznamObjektov.pridajObjekt(naboje6);
        Naboje naboje7 = new Naboje("naboje7", 10, 10);
        this.zoznamObjektov.pridajObjekt(naboje7);
        Naboje naboje8 = new Naboje("naboje8", 14, 3);
        this.zoznamObjektov.pridajObjekt(naboje8);
        Naboje naboje9 = new Naboje("naboje9", 14, 16);
        this.zoznamObjektov.pridajObjekt(naboje9);
        Naboje naboje10 = new Naboje("naboje10", 18, 18);
        this.zoznamObjektov.pridajObjekt(naboje10);
        
        
        // Teleporty
        Teleport teleport1 = new Teleport("teleport1", 17, 2);
        this.zoznamObjektov.pridajObjekt(teleport1);
        Teleport teleport2 = new Teleport("teleport2", 2, 17);
        this.zoznamObjektov.pridajObjekt(teleport2);
        
    }
    
    /**
     * Metóda kontroluje výskyt steny na danom riadku a stĺpci.
     * @param riadok
     * @param stlpec
     * @return 
     */
    public boolean isStena(int riadok, int stlpec) {
        for (int i = 0; i < this.zoznamStien.size(); i++) {
            if (this.zoznamStien.get(i).isStena(riadok, stlpec)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Metóda vráti objekt zo zoznamu objektov podľa jeho názvu.
     * @param nazovObjektu
     * @return 
     */
    public IObjekt getObjektZNazvu(String nazovObjektu) {
        return this.zoznamObjektov.getObjekt(nazovObjektu);
    }
    
    /**
     * Metóda vráti objekt na danej pozícií x a y, ak sa na tomto mieste nachádza.
     * @param riadok
     * @param stlpec
     * @return 
     */
    public IObjekt getObjektNaPozicii(int riadok, int stlpec) {
        IObjekt objekt = this.zoznamObjektov.getObjekt(riadok, stlpec);
        return objekt;
    }
    
    /**
     * Metóda posiela správu zoznamuObjektov na riadenie objektov.
     */
    public void spravanieObjektov() {
        this.zoznamObjektov.doEfekt();
    }
    
    
}
