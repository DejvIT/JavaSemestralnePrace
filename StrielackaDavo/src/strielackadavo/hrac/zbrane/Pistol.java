/**
 * Potomok triedy Zbran, slúži na vykreslenie a použitie základnej zbrane na diaĺku. Pištol...
 */
package strielackadavo.hrac.zbrane;

import strielackadavo.grafika.Obdlznik;
import strielackadavo.hrac.Panacik;
import strielackadavo.prostredie.Mapa;

/**
 *
 * @author Pavličko.D
 */
public class Pistol extends Zbran {
    private final Obdlznik[] casti;
    private int poloha;
    private Naboj naboj;
    private final Panacik panacik;
    private final Mapa mapa;
    
    /**
     * Konštruktor inicializuje pištol.
     * @param nazov
     * @param zasobnik
     * @param riadok
     * @param stlpec
     * @param panacik
     * @param mapa 
     */
    public Pistol(String nazov, int zasobnik, int riadok, int stlpec, Panacik panacik, Mapa mapa) {
        super(nazov, zasobnik, riadok, stlpec, true);
        
        this.poloha = 0;
        this.casti = new Obdlznik[2];
        for (int i = 0; i < 2; i++) {
            this.casti[i] = new Obdlznik();
            this.casti[i].zmenFarbu("black");
            this.casti[i].zmenStrany(5 + (i * 5), 10 + (i * -5));
            this.casti[i].posunVodorovne(-23 + (stlpec * 50) + (i * 2));
            this.casti[i].posunZvisle(-27 + (riadok * 50) - (i * 2));
            this.casti[i].zobraz();
        }
        
        this.panacik = panacik;
        this.mapa = mapa;
    }
    
    /**
     * Metóda riadi vykresľovanie pištole, v ktorej sa má zobrazovať.
     * @param poloha 
     */
    public void setPolohuPistole(int poloha) {
        switch (poloha) {
            case 0:
                if (this.poloha != 0) {
                    for (int i = 0; i < 2; i++) {
                        this.casti[i].posunVodorovne(22 + (i * 10));
                        this.poloha = 0;
                    }
                }
                break;
            case 1:
                if (this.poloha != 1) {
                    for (int i = 0; i < 2; i++) {
                        this.casti[i].posunVodorovne(-22 + (i * -10));
                        this.poloha = 1;
                    }
                }
                break;
        }
    }
    
    /**
     * Metóda posúva piśtol spolu s hráćom v smere jeho chôdzy.
     * @param smer 
     */
    public void posunPistol(String smer) {
        switch (smer) {
            case "vpravo":
                for (int i = 0; i < 2; i++) {
                    this.casti[i].posunVodorovne(50);
                }
                break;
            case "vlavo":
                for (int i = 0; i < 2; i++) {
                    this.casti[i].posunVodorovne(-50);
                }
                break;
            case "hore":
                for (int i = 0; i < 2; i++) {
                    this.casti[i].posunZvisle(-50);
                }
                break;
            case "dole":
                for (int i = 0; i < 2; i++) {
                    this.casti[i].posunZvisle(50);
                }
                break;
        }
    }

    /**
     * Metóda pre útok v zadanom smere vzhľadom na nepriateľa.
     * @param smerom
     * @param nepriatel 
     */
    public void vystrel(String smerom, Panacik nepriatel) {
        if (this.isDrzana() && this.getZasobnik() > 0) {
            this.naboj = new Naboj(this.panacik, this.mapa);
            this.naboj.vystrel(smerom, nepriatel);
            this.uberMuniciuZoZasobnika();
        }
    }
    
    /**
     * Metóda pridá zadaný poćet nábojov do zásobnika.
     * @param pocetNabojov 
     */
    public void pridajMuniciuDoZasobnika(int pocetNabojov) {
        super.pridajDoZasobnikaMuniciu(pocetNabojov);
    }
    
    public int getAktualnyRiadokStrely() {
        return this.naboj.getAktualnyRiadok();
    }
    
    public int getAktualnyStlpecStrely() {
        return this.naboj.getAktualnyStlpec();
    }

    /**
     * Metóda vráti vystrelený náboj z pištole.
     * @return 
     */
    public Naboj getNaboj() {
        if (this.naboj != null) {
            return this.naboj;
        }
        return null;
    }

    /**
     * Metóda skryje pištol.
     */
    public void skry() {
        this.casti[0].skry();
        this.casti[1].skry();
    }

    /**
     * Metóda zobrazí piśtol.
     */
    public void zobraz() {
        this.casti[0].zobraz();
        this.casti[1].zobraz();
    }
    
    
    
    
    
    
    
}
