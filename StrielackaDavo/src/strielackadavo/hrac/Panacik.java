/**
 * Trieda Panacik vytvára postavu, prideľuje jej zbrane a základnu logiku ovládania a boju.
 */
package strielackadavo.hrac;

import strielackadavo.hrac.zbrane.Palka;
import strielackadavo.hrac.zbrane.Pistol;
import strielackadavo.prostredie.Mapa;
import strielackadavo.prostredie.objekty.IObjekt;

/**
 *
 * @author Pavličko.D
 */
public class Panacik {
    private final Mapa mapa;
    private final PanacikGrafika panacik;
    private final String farba;
    private Pistol pistol;
    private Palka baseballka;
    private String smerPreUtok;

    /**
     * Konštruktor vytvára postavu implementovaním triedy PanacikGrafika na danej pozícií, ďalej implementuje
     * mapu, objekty v nej a zbrane pre hráča.
     * @param mapa
     * @param farba
     * @param riadok
     * @param stlpec 
     */
    public Panacik(Mapa mapa, String farba, int riadok, int stlpec) {
        this.mapa = mapa;
        this.panacik = new PanacikGrafika(riadok, stlpec);
        this.farba = farba;
        this.panacik.setFarbaPanacika(this.farba);
        this.panacik.setZivoty();
        
        this.pistol = new Pistol("pistol", 10, this.panacik.getAktualnyRiadok(), this.panacik.getAktualnyStlpec(), this, this.mapa);
        this.baseballka = new Palka("baseballka", 100, this.panacik.getAktualnyRiadok(), this.panacik.getAktualnyStlpec(), this);
        this.smerPreUtok = "hore";
    }
    
    /**
     * Metóda posúva panačika vpravo a skenuje či sa nenachádza nejaký typ objektu 
     * na jeho aktuálnej pozícií.
     */
    public void posunVpravo() {
        this.panacik.posunVpravo();
        this.posunZbrane();
            
        if (this.panacik.getAktualnyRiadok() == 18 && this.panacik.getAktualnyStlpec() == 20) {
            this.cestaPozaOkrajMapy(1, 0);
        }
            
        this.getObjektNaPoziciiPanacika().doAkcia(this);
    }
    
    /**
     * Metóda posúva panačika vľavo a skenuje či sa nenachádza nejaký typ objektu 
     * na jeho aktuálnej pozícií.
     */
    public void posunVlavo() {
        this.panacik.posunVlavo();
        this.posunZbrane();
            
        if (this.panacik.getAktualnyRiadok() == 1 && this.panacik.getAktualnyStlpec() == -1) {
            this.cestaPozaOkrajMapy(18, 19);
        }
            
        this.getObjektNaPoziciiPanacika().doAkcia(this);
    }
    
    /**
     * Metóda posúva panačika hore a skenuje či sa nenachádza nejaký typ objektu 
     * na jeho aktuálnej pozícií.
     */
    public void posunHore() {
        this.panacik.posunHore();
        this.posunZbrane();

        this.getObjektNaPoziciiPanacika().doAkcia(this);
    }
    
    /**
     * Metóda posúva panačika dole a skenuje či sa nenachádza nejaký typ objektu 
     * na jeho aktuálnej pozícií.
     */
    public void posunDole() {
        this.panacik.posunDole();
        this.posunZbrane();

        this.getObjektNaPoziciiPanacika().doAkcia(this);
    }
    
    /**
     * Metóda útoku vzhľadom na nepriteľa v danom smere.
     * @param nepriatel 
     */
    public void zautoc(Panacik nepriatel) {
        if (this.pistol.getZasobnik() > 0) {
            this.pistol.vystrel(this.smerPreUtok, nepriatel);
        } else {
            this.baseballka.udriPalkou(this.smerPreUtok, nepriatel);
        }
    }
    
    /**
     * Metóda presúva hráča po skratke mimo mapy.
     * @param riadok
     * @param stlpec 
     */
    public void cestaPozaOkrajMapy(int riadok, int stlpec) {
        if (this.panacik.getAktualnyRiadok() == 1 && this.panacik.getAktualnyStlpec() == -1) {
            this.panacik.posunVpravo();
            this.teleport(18, 19);
        } else if (this.panacik.getAktualnyRiadok() == 18 && this.panacik.getAktualnyStlpec() == 20) {
            this.teleport(1, 0);
        }
    }

    public String getFarba() {
        return this.farba;
    }
    
    /**
     * Metóda kontroluje stav hráča.
     * @return 
     */
    public boolean isDead() {
        if (this.panacik.getZivot() <= 0) {
            this.panacik.skryPanacika();
            this.pistol.skry();
            return true;
        }
        return false;
    }

    public int getAktualnyRiadok() {
        return this.panacik.getAktualnyRiadok();
    }

    public int getAktualnyStlpec() {
        return this.panacik.getAktualnyStlpec();
    }

    /**
     * Metóda odoberá život hráčovi na zaklade zadanej požiadavky.
     * @param oKolko 
     */
    public void uberZivotOKolko(int oKolko) {
        for (int i = 0; i < oKolko; i++) {
            this.panacik.odoberZivot();
        }
    }

    /**
     * Metóda doplní život hráčovi na základe uvedenej požiadavky.
     * @param oKolko 
     */
    public void pridajZivotOKolko(int oKolko) {
        for (int i = 0; i < oKolko; i++) {
            this.panacik.pridajZivot();
        }
    }

    public int getZivot() {
        return this.panacik.getZivot();
    }
    
    /**
     * Metóda kontroluje objekty, ktoré sa môžu nachádzať na jeho aktualnej pozícií,
     * ak sa tak stane vratí daný typ objektu.
     * @return 
     */
    public IObjekt getObjektNaPoziciiPanacika() {
        IObjekt objekt = this.mapa.getObjektNaPozicii(this.getAktualnyRiadok(), this.getAktualnyStlpec());
        return objekt;
    }

    /**
     * Metóda teleportuje hráča na danú pozíciu v mape.
     * @param riadok
     * @param stlpec 
     */
    public void teleport(int riadok, int stlpec) {
        this.panacik.setPozicia00();
        
        for (int i = 0; i < riadok; i++) {
            this.panacik.posunDole();
        }
        
        for (int i = 0; i < stlpec; i++) {
            this.panacik.posunVpravo();
        }
        
        Pistol presunutaPistol = this.pistol;
        this.pistol.skry();
        this.pistol = new Pistol("pistol", presunutaPistol.getZasobnik(), this.panacik.getAktualnyRiadok(), this.panacik.getAktualnyStlpec(), this, this.mapa);
        
        Palka presunutaBaseballka = this.baseballka;
        this.baseballka.skry();
        this.baseballka = new Palka("baseballka", presunutaBaseballka.getZasobnik(), this.panacik.getAktualnyRiadok(), this.panacik.getAktualnyStlpec(), this);
    }

    /**
     * Metóda pridá zadaný počet nábojov do pištole.
     * @param pocetNabojov 
     */
    public void pridajMuniciuDoPistole(int pocetNabojov) {
        this.pistol.pridajMuniciuDoZasobnika(pocetNabojov);
    }
    
    /**
     * Metóda otáča hráča v smere aktivity.
     * @param smer 
     */
    public void setPostojHraca(String smer) {
        this.smerPreUtok = smer;
        if (smer.equals("vpravo")) {
            this.panacik.setPolohuTelaDoStrany();
            this.pistol.setPolohuPistole(0);
            this.baseballka.setPolohuPalky(1);
        } else if (smer.equals("vlavo")) {
            this.panacik.setPolohuTelaDoStrany();
            this.pistol.setPolohuPistole(1);
            this.baseballka.setPolohuPalky(3);
        } else if (smer.equals("hore")) {
            this.panacik.setPolohuTelaRovno();
            this.pistol.setPolohuPistole(0);
            this.baseballka.setPolohuPalky(0);
        } else if (smer.equals("dole")) {
            this.panacik.setPolohuTelaRovno();
            this.pistol.setPolohuPistole(0);
            this.baseballka.setPolohuPalky(2);
        }
    }
    
    /**
     * Metóda posunie zbrane spolu s hráčom v akom sa pohybuje.
     */
    public void posunZbrane() {
        if (this.pistol.getZasobnik() == 0) {
            this.pistol.skry();
        } else {
            this.pistol.zobraz();
        }
        
        this.baseballka.skry();
        
        this.pistol.posunPistol(this.smerPreUtok);
        this.baseballka.posunPalku(this.smerPreUtok);
    }
    
    /**
     * Metóda kontroluje pohyb pre hráča, kontroluje steny a nepriateľa v smere,
     * v ktorom sa chce pohybovať.
     * @param smer
     * @param nepriatel
     * @return 
     */
    public boolean koliziaPrePohyb(String smer, Panacik nepriatel) {
        switch (smer) {
            case "vpravo":
                if (this.mapa.isStena(this.panacik.getAktualnyRiadok(), this.panacik.getAktualnyStlpec() + 1) 
                        || (this.panacik.getAktualnyRiadok() == nepriatel.getAktualnyRiadok() 
                            && this.panacik.getAktualnyStlpec() + 1 == nepriatel.getAktualnyStlpec())) {
                    return true;
                }
                break;
            case "vlavo":
                if (this.mapa.isStena(this.panacik.getAktualnyRiadok(), this.panacik.getAktualnyStlpec() - 1) 
                        || (this.panacik.getAktualnyRiadok() == nepriatel.getAktualnyRiadok() 
                            && this.panacik.getAktualnyStlpec() - 1 == nepriatel.getAktualnyStlpec())) {
                    return true;
                }
                break;
            case "hore":
                if (this.mapa.isStena(this.panacik.getAktualnyRiadok() - 1, this.panacik.getAktualnyStlpec()) 
                        || (this.panacik.getAktualnyRiadok() - 1 == nepriatel.getAktualnyRiadok() 
                            && this.panacik.getAktualnyStlpec() == nepriatel.getAktualnyStlpec())) {
                    return true;
                }
                break;
            case "dole":
                if (this.mapa.isStena(this.panacik.getAktualnyRiadok() + 1, this.panacik.getAktualnyStlpec()) 
                        || (this.panacik.getAktualnyRiadok() + 1 == nepriatel.getAktualnyRiadok() 
                            && this.panacik.getAktualnyStlpec() == nepriatel.getAktualnyStlpec())) {
                    return true;
                }
                break;    
        }
        return false;
    }
    
}
