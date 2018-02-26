/**
 * Trieda Hra vytvára dvoch hráčov, ktorí bojujú proti sebe, implementuje mapu, 
 * jej objekty, zbrane, ktoré hráči používajú. 
 */
package strielackadavo.hra;

import javax.swing.JOptionPane;
import strielackadavo.hrac.Panacik;
import strielackadavo.manazer.Manazer;
import strielackadavo.prostredie.Mapa;

/**
 *
 * @author Pavličko.D
 */
public class Hra {
    private final Manazer manazer;
    private final Mapa mapa;
    private final Panacik panacik1;
    private final Panacik panacik2;
    private int pocitanieRegeneracie;
    private StavHry stav;
    
    private int regeneraciaHraca1;
    private int regeneraciaHraca2;

    /**
     * Konštruktor vytvára dvoch hráčov na zadaných pozíciach X, Y, následne im udeľuje farbu a
     * implementuje mapu jej objekty a zbrane pre hráčov, ktoré budú používať.
     * 
     * @param farbaPrvehoPanacika
     * @param farbaDruhehoPanacika 
     */
    public Hra(String farbaPrvehoPanacika, String farbaDruhehoPanacika) {
        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this);
        this.mapa = new Mapa();
        this.panacik1 = new Panacik(this.mapa, farbaPrvehoPanacika, 9, 13);
        this.panacik2 = new Panacik(this.mapa, farbaDruhehoPanacika, 9, 6);
        this.pocitanieRegeneracie = 0;
        
        this.stav = StavHry.NEROZHODNUTA;
        
        this.regeneraciaHraca1 = 40;
        this.regeneraciaHraca2 = 40;
    }
    
    /**
     * Metóda posúva prvého hráča smerom vpravo, ak nenastala kolízia.
     */
    public void posunVpravo() {
        this.panacik1.setPostojHraca("vpravo");
        if (!this.panacik1.koliziaPrePohyb("vpravo", this.panacik2)) {
            this.panacik1.posunVpravo();
        }
    }
    
    /**
     * Metóda posúva prvého hráča smerom vľavo, ak nenastala kolízia.
     */
    public void posunVlavo() {
        this.panacik1.setPostojHraca("vlavo");
        if (!this.panacik1.koliziaPrePohyb("vlavo", this.panacik2)) {
            this.panacik1.posunVlavo();
        }
    }
    
    /**
     * Metóda posúva prvého hráča smerom hore, ak nenastala kolízia.
     */
    public void posunHore() {
        this.panacik1.setPostojHraca("hore");
        if (!this.panacik1.koliziaPrePohyb("hore", this.panacik2)) {
            this.panacik1.posunHore();
        }
    }
    
    /**
     * Metóda posúva prvého hráča smerom dole, ak nenastala kolízia.
     */
    public void posunDole() {
        this.panacik1.setPostojHraca("dole");
        if (!this.panacik1.koliziaPrePohyb("dole", this.panacik2)) {
            this.panacik1.posunDole();
        }
    }
    
    /**
     * Metóda útoku prvého hráča, buď bude strieľať alebo použije baseballku v prípade nedostatku nábojov v pištoli.
     */
    public void aktivuj() {
        this.panacik1.zautoc(this.panacik2);
    }
    
    /**
     * Metóda posúva druhého hráča smerom vpravo, ak nenastala kolízia.
     */
    public void posunVpravo2() {
        this.panacik2.setPostojHraca("vpravo");
        if (!this.panacik2.koliziaPrePohyb("vpravo", this.panacik1)) {
            this.panacik2.posunVpravo();
        }
    }
    
    /**
     * Metóda posúva druhého hráča smerom vľavo, ak nenastala kolízia.
     */
    public void posunVLavo2() {
        this.panacik2.setPostojHraca("vlavo");
        if (!this.panacik2.koliziaPrePohyb("vlavo", this.panacik1)) {
            this.panacik2.posunVlavo();
        }
    }
    
    /**
     * Metóda posúva druhého hráča smerom hore, ak nenastala kolízia.
     */
    public void posunHore2() {
        this.panacik2.setPostojHraca("hore");
        if (!this.panacik2.koliziaPrePohyb("hore", this.panacik1)) {
            this.panacik2.posunHore();
        }
    }
    
    /**
     * Metóda posúva druhého hráča smerom dole, ak nenastala kolízia.
     */
    public void posunDole2() {
        this.panacik2.setPostojHraca("dole");
        if (!this.panacik2.koliziaPrePohyb("dole", this.panacik1)) {
            this.panacik2.posunDole();
        }
    }
    
    /**
     * Metóda útoku druhého hráča, buď bude strieľať alebo použije baseballku v prípade nedostatku nábojov v pištoli.
     */
    public void aktivuj2() {
        this.panacik2.zautoc(this.panacik1);
    }
    
    /**
     * Metóda tik riadi objekty na mape, ktoré majú efekt(animáciu) a taktiež
     * riadi regeneráciu zdravia oboch hráčov.
     * Prípadne ukončuje hru.
     */
    public void tik() {
        if (this.stav == StavHry.NEROZHODNUTA) {
            this.skontrolujStavHry();
            
            this.mapa.spravanieObjektov();
            this.regeneraciaZdraviaHracov();
            
        } else {
            this.setStavHry();
            this.zrus();
        }
    }
    
    /**
     * Metóda regenerácie zdravia oboch hráčov.
     */
    void regeneraciaZdraviaHracov() {
        this.pocitanieRegeneracie++;
        
        if (this.pocitanieRegeneracie % this.regeneraciaHraca1 == 0) {
            this.panacik1.pridajZivotOKolko(1);
        }
        
        if (this.pocitanieRegeneracie % this.regeneraciaHraca2 == 0) {
            this.panacik2.pridajZivotOKolko(1);
        }
    }
    
    /**
     * Metóda na kontrolu stavu hry na základe stavu hráčov, kto je ešte živý.
     */
    public void skontrolujStavHry() {
        if (!this.panacik1.isDead() && this.panacik2.isDead()) {
            this.stav = StavHry.VYHRAPRVEHO;
        } else if (this.panacik1.isDead() && !this.panacik2.isDead()) {
            this.stav = StavHry.VYHRADRUHEHO;
        } else if (this.panacik1.isDead() && this.panacik2.isDead()) {
            this.stav = StavHry.REMIZA;
        } else {
            this.stav = StavHry.NEROZHODNUTA;
        }
    }
    
    /**
     * Metóda nastavuje stav hry na základe určitých podmienok.
     */
    public void setStavHry() {
        if (this.stav != StavHry.NEROZHODNUTA) {
            if (this.stav == StavHry.REMIZA) {
                JOptionPane.showMessageDialog(null, "Obaja hráči sa zabili v rovnakom čase a výsledkom je REMÍZA ! ");
            } else if (this.stav == StavHry.VYHRAPRVEHO) {
                JOptionPane.showMessageDialog(null, "Túto hru vyhral Prvý hráč s farbou: " + this.panacik1.getFarba());
            } else {
                JOptionPane.showMessageDialog(null, "Túto hru vyhral Druhý hráč s farbou: " + this.panacik2.getFarba());
            }
        }
    }
    
    /**
     * Cheat pre prvého hráča, aktivuje sa pomocou klávesy 9.
     * Cheat zrýchli regeneráciu zdravia 40-násobne a prídá 20 nábojov,
     * opakovane stlačenie cheat vypína.
     */
    public void cheat1() {
        if (this.regeneraciaHraca1 == 40) {
            this.regeneraciaHraca1 = 1;
            this.panacik1.pridajMuniciuDoPistole(20);
        } else {
            this.regeneraciaHraca1 = 40;
        }
    }
    
    /**
     * Cheat pre druhého hráča, aktivuje sa pomocou klávesy 6.
     * Cheat zrýchli regeneráciu zdravia 40-násobne a prídá 20 nábojov,
     * opakovane stlačenie cheat vypína.
     */
    public void cheat2() {
        if (this.regeneraciaHraca2 == 40) {
            this.regeneraciaHraca2 = 1;
            this.panacik2.pridajMuniciuDoPistole(20);
        } else {
            this.regeneraciaHraca2 = 40;
        }
    }
    
    /**
     * Zatvorí hru.
     */
    public void zrus() {
        System.exit(0);
    }
}
