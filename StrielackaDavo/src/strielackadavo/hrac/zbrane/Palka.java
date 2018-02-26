/**
 * Trieda Palka je potomkom triedy Zbran, je tvorená pomocou tvarov v3 a používa sa v prípade
 * prázdneho zásobnika v pištoli.
 */
package strielackadavo.hrac.zbrane;

import strielackadavo.grafika.Obdlznik;
import strielackadavo.hrac.Panacik;

/**
 *
 * @author Pavličko.D
 */
public class Palka extends Zbran{
    private final Obdlznik baseballka;
    private int poloha;
    private final Panacik panacik;
    
    /**
     * Konštruktor inicializuje palku - zbraň na blízko.
     * @param nazov
     * @param zasobnik
     * @param riadok
     * @param stlpec
     * @param panacik
     */
    public Palka(String nazov, int zasobnik, int riadok, int stlpec, Panacik panacik) {
        super(nazov, zasobnik, riadok, stlpec, true);
        
        this.poloha = 0;
        this.baseballka = new Obdlznik();
        this.baseballka.zmenFarbu("brown");
        this.baseballka.zmenStrany(10, 50);
        this.baseballka.posunVodorovne(-40 + stlpec * 50);
        this.baseballka.posunZvisle(-80 + riadok * 50);
        
        this.panacik = panacik;
    }
    
    /**
     * Metóda riadi pozíciu zbrane v ktorom sa má vykresľovať.
     * @param poloha 
     */
    public void setPolohuPalky(int poloha) {
        switch (poloha) {
            case 0:
                if (this.poloha == 3) {
                    this.baseballka.posunVodorovne(50);
                }
                
                if (this.poloha != 0) {
                    this.baseballka.posunZvisle(-50);
                    this.baseballka.zmenStrany(10, 50);
                }
                this.poloha = 0;
                break;
            case 1:
                if (this.poloha == 3) {
                    this.baseballka.posunVodorovne(50);
                }
                
                if (this.poloha == 0) {
                    this.baseballka.posunZvisle(50);
                }
                this.baseballka.zmenStrany(50, 10);
                this.poloha = 1;
                break;
            case 2:
                if (this.poloha == 3) {
                    this.baseballka.posunVodorovne(50);
                }
                
                if (this.poloha == 0) {
                    this.baseballka.posunZvisle(50);
                }
                this.baseballka.zmenStrany(10, 50);
                this.poloha = 2;
                break;
            case 3:
                if (this.poloha != 3) {
                    this.baseballka.posunVodorovne(-50);
                }
                
                if (this.poloha == 0) {
                    this.baseballka.posunZvisle(50);
                }
                this.baseballka.zmenStrany(50, 10);
                this.poloha = 3;
                break;
        }
    }

    /**
     * Metóda posúva zbrań v smere chôdzy panáčika.
     * @param smer 
     */
    public void posunPalku(String smer) {
        switch (smer) {
            case "vpravo":
                this.baseballka.posunVodorovne(50);
                break;
            case "vlavo":
                this.baseballka.posunVodorovne(-50);
                break;
            case "hore":
                this.baseballka.posunZvisle(-50);
                break;
            case "dole":
                this.baseballka.posunZvisle(50);
                break;
        }
    }

    /**
     * Metóda pre útok palkou na blízko.
     * @param smerom
     * @param nepriatel 
     */
    public void udriPalkou(String smerom, Panacik nepriatel) {
        if (this.isDrzana() && this.getZasobnik() > 0) {
            this.baseballka.zobraz();
            if (this.zasahDoNepriatela(smerom, nepriatel)) {
                this.uberMuniciuZoZasobnika();
                nepriatel.uberZivotOKolko(1);
            }
        }
    }

    /**
     * Metóda skryje pálku.
     */
    public void skry() {
        this.baseballka.skry();
    }
    
    /**
     * Metóda riadi zásah do nepriateľa v smere, v ktorom je hráč otočený.
     * @param smerom
     * @param nepriatel
     * @return 
     */
    public boolean zasahDoNepriatela(String smerom, Panacik nepriatel) {
        if (smerom.equals("vpravo")) {
            if (this.panacik.getAktualnyRiadok() == nepriatel.getAktualnyRiadok() && this.panacik.getAktualnyStlpec() + 1 == nepriatel.getAktualnyStlpec()) {
                return true;
            }
        } else if (smerom.equals("vlavo")) {
            if (this.panacik.getAktualnyRiadok() == nepriatel.getAktualnyRiadok() && this.panacik.getAktualnyStlpec() - 1 == nepriatel.getAktualnyStlpec()) {
                return true;
            }
        } else if (smerom.equals("hore")) {
            if (this.panacik.getAktualnyRiadok() - 1 == nepriatel.getAktualnyRiadok() && this.panacik.getAktualnyStlpec() == nepriatel.getAktualnyStlpec()) {
                return true;
            }
        } else if (smerom.equals("dole")) {
            if (this.panacik.getAktualnyRiadok() + 1 == nepriatel.getAktualnyRiadok() && this.panacik.getAktualnyStlpec() == nepriatel.getAktualnyStlpec()) {
                return true;
            }
        }
        return false;
    }
}
