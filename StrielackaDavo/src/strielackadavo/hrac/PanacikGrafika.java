/**
 * Trieda PanacikGrafika pracuje prevažne z tvarmi v3, vytvára panačika a health bar.
 * 
 */
package strielackadavo.hrac;


import strielackadavo.grafika.Kruh;
import strielackadavo.grafika.Obdlznik;


/**
 * @author Pavličko.D
 */
public class PanacikGrafika {
    
    private final Obdlznik telo;
    private final Kruh hlava;
    private final Obdlznik[] ruky;
    private final Obdlznik[] nohy;
    private static final int VZDIALENOST_POSUNU = 50;
    
    private int riadok;
    private int stlpec;
    private boolean polohaTela;
    
    private final Obdlznik[] zivoty;
    private int zivot;
    
    /**
     * Konštruktor vytvára jednotlivé časti: hlava, telo, ruky, nohy pomocou tvarov v3
     * na zadanej pozícií.
     * @param riadok
     * @param stlpec 
     */
    public PanacikGrafika(int riadok, int stlpec) {
        this.polohaTela = true;
        
        this.riadok = riadok;
        this.stlpec = stlpec;
        
        
        /** Telo */
        this.telo = new Obdlznik();
        this.telo.zmenStrany(20, 20);
        this.telo.zmenFarbu("black");
        this.telo.posunVodorovne(-45 + this.stlpec * 50);
        this.telo.posunZvisle(-42 + this.riadok * 50);
        this.telo.zobraz();
        
        /** Nohy */
        this.nohy = new Obdlznik[2];
        for (int i = 0; i < 2; i++) {
            this.nohy[i] = new Obdlznik();
            this.nohy[i].zmenStrany(11, 22);
            this.nohy[i].zmenFarbu("black");
            this.nohy[i].posunVodorovne((-47 + i * 12) + this.stlpec * 50);
            this.nohy[i].posunZvisle(-22 + this.riadok * 50);
            this.nohy[i].zobraz();
        }
        
        /** Ruky */
        this.ruky = new Obdlznik[2];
        for (int i = 0; i < 2; i++) {
            this.ruky[i] = new Obdlznik();
            this.ruky[i].zmenStrany(9, 25);
            this.ruky[i].zmenFarbu("magenta");
            this.ruky[i].posunVodorovne((-51 + i * 22) + this.stlpec * 50);
            this.ruky[i].posunZvisle(-41 + this.riadok * 50);
            this.ruky[i].zobraz();
        }
        
        /** Hlava */
        this.hlava = new Kruh();
        this.hlava.zmenPriemer(15);
        this.hlava.zmenFarbu("blue");
        this.hlava.posunVodorovne(-3 + this.stlpec * 50);
        this.hlava.posunZvisle(-61 + this.riadok * 50);
        this.hlava.zobraz();
        
        /** Ukazovateľ životov */
        this.zivoty = new Obdlznik[3];
    }
    
    /**
     * Metóda mení farbu panáčika.
     * @param farba
     */
    public void setFarbaPanacika(String farba) {
        switch (farba) {
            case "red":
                this.ruky[0].zmenFarbu("red");
                this.ruky[1].zmenFarbu("red");
                this.hlava.zmenFarbu("red");
                break;
            case "yellow":
                this.ruky[0].zmenFarbu("yellow");
                this.ruky[1].zmenFarbu("yellow");
                this.hlava.zmenFarbu("yellow");
                break;
            case "blue":
                this.ruky[0].zmenFarbu("blue");
                this.ruky[1].zmenFarbu("blue");
                this.hlava.zmenFarbu("blue");
                break;
            case "magenta":
                this.ruky[0].zmenFarbu("magenta");
                this.ruky[1].zmenFarbu("magenta");
                this.hlava.zmenFarbu("magenta");
                break;
            case "black":
                this.telo.zmenFarbu("white");
                this.ruky[0].zmenFarbu("black");
                this.ruky[1].zmenFarbu("black");
                this.hlava.zmenFarbu("black");
                break;
                
            default:
                this.telo.zmenFarbu("white");
                this.ruky[0].zmenFarbu("black");
                this.ruky[1].zmenFarbu("black");
                this.hlava.zmenFarbu("black");
                break;
        }
    }
    
    /**
     * Metóda posúva všetky časti panáčika smerom vpravo.
     */
    public void posunVpravo() {
        this.telo.posunVodorovne(PanacikGrafika.VZDIALENOST_POSUNU);
        for (int i = 0; i < 2; i++) {
            this.nohy[i].posunVodorovne(PanacikGrafika.VZDIALENOST_POSUNU);
            this.ruky[i].posunVodorovne(PanacikGrafika.VZDIALENOST_POSUNU);
        }
        this.hlava.posunVodorovne(PanacikGrafika.VZDIALENOST_POSUNU);
        for (int i = 0; i < 3; i++) {
            this.zivoty[i].posunVodorovne(PanacikGrafika.VZDIALENOST_POSUNU);
        }
        
        this.stlpec = (this.stlpec * 50 + PanacikGrafika.VZDIALENOST_POSUNU) / 50;
    } 
    
    /**
     * Metóda posúva všetky časti panáčika smerom vľavo.
     */
    public void posunVlavo() {
        this.telo.posunVodorovne(-PanacikGrafika.VZDIALENOST_POSUNU);
        for (int i = 0; i < 2; i++) {
            this.nohy[i].posunVodorovne(-PanacikGrafika.VZDIALENOST_POSUNU);
            this.ruky[i].posunVodorovne(-PanacikGrafika.VZDIALENOST_POSUNU);
        }
        this.hlava.posunVodorovne(-PanacikGrafika.VZDIALENOST_POSUNU);
        for (int i = 0; i < 3; i++) {
            this.zivoty[i].posunVodorovne(-PanacikGrafika.VZDIALENOST_POSUNU);
        }
        
        this.stlpec = (this.stlpec * 50 - PanacikGrafika.VZDIALENOST_POSUNU) / 50;
    }
    
    /**
     * Metóda posúva všetky časti panáčika smerom hore.
     */
    public void posunHore() {
        this.telo.posunZvisle(-PanacikGrafika.VZDIALENOST_POSUNU);
        for (int i = 0; i < 2; i++) {
            this.nohy[i].posunZvisle(-PanacikGrafika.VZDIALENOST_POSUNU);
            this.ruky[i].posunZvisle(-PanacikGrafika.VZDIALENOST_POSUNU);
        }
        this.hlava.posunZvisle(-PanacikGrafika.VZDIALENOST_POSUNU);
        for (int i = 0; i < 3; i++) {
            this.zivoty[i].posunZvisle(-PanacikGrafika.VZDIALENOST_POSUNU);
        }
        
        this.riadok = (this.riadok * 50 - PanacikGrafika.VZDIALENOST_POSUNU) / 50;
    }
    
    /**
     * Metóda posúva všetky časti panáčika smerom dole.
     */
    public void posunDole() {
        this.telo.posunZvisle(PanacikGrafika.VZDIALENOST_POSUNU);
        for (int i = 0; i < 2; i++) {
            this.nohy[i].posunZvisle(PanacikGrafika.VZDIALENOST_POSUNU);
            this.ruky[i].posunZvisle(PanacikGrafika.VZDIALENOST_POSUNU);
        }
        this.hlava.posunZvisle(PanacikGrafika.VZDIALENOST_POSUNU);
        for (int i = 0; i < 3; i++) {
            this.zivoty[i].posunZvisle(PanacikGrafika.VZDIALENOST_POSUNU);
        }
        
        this.riadok = (this.riadok * 50 + PanacikGrafika.VZDIALENOST_POSUNU) / 50;
    }
    
    public int getAktualnyRiadok() {
        return this.riadok;
    }
    
    public int getAktualnyStlpec() {
        return this.stlpec;
    }
    
    /**
     * Metóda vracia hodnotu boolean pre polohu tela.
     * true = rovno;
     * false = profil;
     * @return 
     */
    public boolean getPolohaTela() {
        return this.polohaTela; // True = rovno, False = profil
    }
    
    /**
     * Metóda posunie všetky časti panáčika do rovnej polohy.
     */
    public void setPolohuTelaRovno() {
        if (!this.polohaTela) {
            this.nohy[0].posunVodorovne(-7);
            this.nohy[1].posunVodorovne(7);
            this.ruky[0].posunVodorovne(-10);
            this.ruky[1].posunVodorovne(10);
        
            this.polohaTela = true;
        }
    }
    
    /**
     * Metóda posunie všetky časti panáčika do polohy profilu.
     */
    public void setPolohuTelaDoStrany() {
        if (this.polohaTela) {
            this.nohy[0].posunVodorovne(7);
            this.nohy[1].posunVodorovne(-7);
            this.ruky[0].posunVodorovne(10);
            this.ruky[1].posunVodorovne(-10);
        
            this.polohaTela = false;
        }
    }
    
    /**
     *Metóda zobrazuje časti panáčika.
     */
    public void zobrazPanacika() {
        this.telo.zobraz();
        this.nohy[0].zobraz();
        this.nohy[1].zobraz();
        this.ruky[0].zobraz();
        this.ruky[1].zobraz();
        this.hlava.zobraz();
    }
    
    /**
     * Metóda skrýva všetky časti panáčika.
     */
    public void skryPanacika() {
        this.telo.skry();
        this.nohy[0].skry();
        this.nohy[1].skry();
        this.ruky[0].skry();
        this.ruky[1].skry();
        this.hlava.skry();
        
        for (int i = 0; i < 3; i++) {
            this.zivoty[i].skry();
        }
    }
    
    /**
     * Metóda inicializuje životy pre panáčika.
     */
    public void setZivoty() {
        this.zivot = 3;
        
        for (int i = 0; i < 3; i++) {
            this.zivoty[i] = new Obdlznik();
            this.zivoty[i].zmenStrany(11, 6);
            this.zivoty[i].zmenFarbu("red");
            this.zivoty[i].posunVodorovne((-51 + i * 11) + this.stlpec * 50);
            this.zivoty[i].posunZvisle(-61 + this.riadok * 50);
            this.zivoty[i].zobraz();
        }
    }
    
    public int getZivot() {
        return this.zivot;
    }

    /**
     * Metóda odoberie život panačikovi.
     */
    public void odoberZivot() {
        if (this.zivot > 0) {
            this.zivot--;
        }
        
        if (this.zivot < 1) {
            this.zivoty[0].zmenFarbu("black");
        } else if (this.zivot < 2) {
            this.zivoty[1].zmenFarbu("black");
        } else {
            this.zivoty[2].zmenFarbu("black");
        }
    }
    
    /**
     * Metóda pridá život panačikovi.
     */
    public void pridajZivot() {
        if (this.zivot < 3) {
            this.zivot++;
        }
        
        if (this.zivot > 2) {
            this.zivoty[2].zmenFarbu("red");
        } else if (this.zivot > 1) {
            this.zivoty[1].zmenFarbu("red");
        } else {
            this.zivoty[0].zmenFarbu("red");
        }
    }

    /**
     * Metóda teleportne všetky časti panáčika na pozíciu 0, 0.
     */
    void setPozicia00() {
        int y = this.riadok;
        for (int i = 0; i < y; i++) {
            this.posunHore();
        }
        
        int x = this.stlpec;
        for (int i = 0; i < x; i++) {
            this.posunVlavo();
        }
    }

    
}
