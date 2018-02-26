
/**
 * Zjednotenie doteraz možných objektov tj. dvaja hráči, bojové pole s použitím bômb.
 * Hrateľná verzia Bombermana pre dvoch hráčov pomocou klávesnice.
 * 
 * @author (Dávid Pavličko) 
 * @version (28.11.2015)
 */
public class DvajaHraci {
    private BojovePole bojovePole;
    private Bomberman hrac1;
    private Bomberman hrac2;
    
    private Manazer manazer;
    private int pocitadloHrac1;
    private int pocitadloHrac2;
    
    private String ktoVyhral;
    
    /**
     * Konštruktor vytvára dvoch hráčov s možnosťou výberu farieb:
     * * Možné farby sú tieto:
     * červená - "red"
     * žltá    - "yellow"
     * modrá   - "blue"
     * fialová - "magenta"
     * čierna  - "black"
     * 
     *  + priradenie aktuálnych pozícií pre štart - spawny.
     */
    public DvajaHraci(String farbaPrvehoPanacika, String farbaDruhehoPanacika) {
        this.bojovePole = this.bojovePole;
        this.bojovePole = new BojovePole();
        
        this.hrac1 = new Bomberman(this.bojovePole, farbaPrvehoPanacika, 11, 11);
        this.pocitadloHrac1 = 0;
        
        this.hrac2 = new Bomberman(this.bojovePole, farbaDruhehoPanacika, 6, 6);
        this.pocitadloHrac2 = 0;
        
        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this);
        
        this.ktoVyhral = null;
    }
    
    /**
     * Hráč1 sa posunie vpravo na bojovom poli.
     */
    public void posunVpravo() { 
        this.hrac1.chodVpravo();
    }
    
    /**
     * Hráč1 sa posunie vľavo na bojovom poli.
     */
    public void posunVlavo() { 
        this.hrac1.chodVlavo();
    }
    
    /**
     * Hráč1 sa posunie dole na bojovom poli.
     */
    public void posunDole() { 
        this.hrac1.chodDole();
    }
    
    /**
     * Hráč1 sa posunie hore na bojovom poli.
     */
    public void posunHore() {
        this.hrac1.chodHore();
    }
    
    /**
     * Hráč1 vyhodí bombu na bojovom poli, na mieste jemu aktuálnom.
     */
    public void aktivuj() {  
        this.hrac1.hodBombu();
        this.pocitadloHrac1 = 0;
    }
    
    /**
     * Hráč2 sa posunie vpravo na bojovom poli.
     */
    public void posunVpravo2() { 
        this.hrac2.chodVpravo();
    }  
    
    /**
     * Hráč2 sa posunie vľavo na bojovom poli.
     */
    public void posunVLavo2() { 
        this.hrac2.chodVlavo();
    }   
    
    /**
     * Hráč2 sa posunie dole na bojovom poli.
     */
    public void posunDole2() {  
        this.hrac2.chodDole();
    }   
    
    /**
     * Hráč2 sa posunie hore na bojovom poli.
     */
    public void posunHore2() { 
        this.hrac2.chodHore();
    }   
    
    /**
     * Hráč2 vyhodí bombu na bojovom poli, na mieste jemu aktuálnom.
     */
    public void aktivuj2() {  
        this.hrac2.hodBombu();
        this.pocitadloHrac2 = 0;
    }
    
    /**
     * Metóda riadi bombu na zaklade časovačov pre hodenú bombu daného hráča.
     */
    public void tik() {  
        this.pocitadloHrac1++;
        if (this.pocitadloHrac1 == 10) {
            this.hrac1.bombaVybuchni();
        }
        
        if (this.pocitadloHrac1 == 12) {
            this.hrac1.skryVybuch();
            this.pocitadloHrac1 = 0;
        }
        
        this.pocitadloHrac2++;
        if (this.pocitadloHrac2 == 10) {
            this.hrac2.bombaVybuchni();
        }
        
        if (this.pocitadloHrac2 == 12) {
            this.hrac2.skryVybuch();
            this.pocitadloHrac1 = 0;
        }
    }
    
    /**
     * Vráti aktuálny riadok Hráča1.
     */
    public int getAktualnyRiadokHraca1() {  
        return this.hrac1.getAktualnyRiadokBombermana();
    }
    
    /**
     * Vráti aktuálny stĺpec Hráča1.
     */
    public int getAktualnyStlpecHraca1() {  
        return this.hrac1.getAktualnyStlpecBombermana();
    }
    
    /**
     * Vráti aktuálny riadok Hráča2.
     */
    public int getAktualnyRiadokHraca2() {  
        return this.hrac2.getAktualnyRiadokBombermana();
    }
    
    /**
     * Vráti aktuálny stĺpec Hráča2.
     */
    public int getAktualnyStlpecHraca2() { 
        return this.hrac2.getAktualnyStlpecBombermana();
    }
    
    /**
     * Pomocou klávesy ESC možnosť vypnutia hry.
     */
    public void zrus() {
        System.exit(0);
    }
    
    /**
     * Stav hry: Kto vyhral ?
     */
    
    public void ktoVyhral() {
        if (this.hrac1 != null && this.hrac2 == null) {
            System.out.println("Hráč 1 vyhral. ");
        } else if (this.hrac1 == null && this.hrac2 != null) {
            System.out.println("Hráč 2 vyhral. ");
        } else if (this.hrac1 == null && this.hrac2 == null) {
            System.out.println("Obaja hráči vybuchli - REMÍZA ");
        } else {
            System.out.println("Súboj stále prebieha. ");
        }
        
    }
    
}
