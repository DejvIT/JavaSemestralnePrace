
/**
 * Hrateľná verzia Bombermana pre dvoch hráčov pomocou klávesnice.
 * Prvý hráč ovláda atentátnika šípkami a hádže bombu ENTEROM.
 * Druhý hráč ovláda atentátnika WASD a hádže bombu MEDZERNÍKOM.
 * Farbu atentátnika si určuje hráč sám zo zoznamu sprístupnených farieb.
 * 
 * @author (Dávid Pavličko) 
 * @version (28.11.2015)
 */
public class DvajaHraci {
    private BojovePole bojovePole;
    private Bomberman hrac1;
    private Bomberman hrac2;
    
    private String farbaPrvehoHraca;
    private String farbaDruhehoHraca;
    
    private Manazer manazer;
    private int pocitadloHrac1;
    private int pocitadloHrac2;
    
    private StavHry stav;
    private String vysledok;
    /**
     * Konštruktor vytvára dvoch hráčov s možnosťou výberu farieb:
     *  + priradenie aktuálnych pozícií pre štart => spawny.
     *  
     * @param Sprístupnené farby atentátnikov sú tieto:
     * červená - "red"
     * žltá    - "yellow"
     * modrá   - "blue"
     * fialová - "magenta"
     * čierna  - "black"
     */
    public DvajaHraci(String farbaPrvehoHraca, String farbaDruhehoHraca) {
        this.bojovePole = this.bojovePole;
        this.bojovePole = new BojovePole();
        this.farbaPrvehoHraca = farbaPrvehoHraca;
        this.farbaDruhehoHraca = farbaDruhehoHraca;
        
        this.hrac1 = new Bomberman(this.bojovePole, farbaPrvehoHraca, 11, 11);
        this.pocitadloHrac1 = 0;
        
        this.hrac2 = new Bomberman(this.bojovePole, farbaDruhehoHraca, 6, 6);
        this.pocitadloHrac2 = 0;
        
        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this);
        
        this.stav = StavHry.NEROZHODNUTA;
    }
    
    /**
     * Hráč1 sa posunie vpravo na bojovom poli.
     */
    public void posunVpravo() { 
        if (this.hrac1 != null) {
            if (!this.hrac2.jeTuBomba(this.getAktualnyRiadokHraca1(), this.getAktualnyStlpecHraca1() + 1)) {
                this.hrac1.chodVpravo();
            }
        }
    }
    
    /**
     * Hráč1 sa posunie vľavo na bojovom poli.
     */
    public void posunVlavo() { 
        if (this.hrac1 != null) {
            if (!this.hrac2.jeTuBomba(this.getAktualnyRiadokHraca1(), this.getAktualnyStlpecHraca1() - 1)) {
                this.hrac1.chodVlavo();
            }
        }
    }
    
    /**
     * Hráč1 sa posunie dole na bojovom poli.
     */
    public void posunDole() { 
        if (this.hrac1 != null) {
            if (!this.hrac2.jeTuBomba(this.getAktualnyRiadokHraca1() + 1, this.getAktualnyStlpecHraca1())) {
                this.hrac1.chodDole();
            }
        }
    }
    
    /**
     * Hráč1 sa posunie hore na bojovom poli.
     */
    public void posunHore() {
        if (this.hrac1 != null) {
            if (!this.hrac2.jeTuBomba(this.getAktualnyRiadokHraca1() - 1, this.getAktualnyStlpecHraca1())) {
                this.hrac1.chodHore();
            }
        }
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
        if (this.hrac1 != null) {
            if (!this.hrac1.jeTuBomba(this.getAktualnyRiadokHraca2(), this.getAktualnyStlpecHraca2() + 1)) {
                this.hrac2.chodVpravo();
            }
        }
    }  
    
    /**
     * Hráč2 sa posunie vľavo na bojovom poli.
     */
    public void posunVLavo2() {  
        if (this.hrac1 != null) {
            if (!this.hrac1.jeTuBomba(this.getAktualnyRiadokHraca2(), this.getAktualnyStlpecHraca2() - 1)) {
                this.hrac2.chodVlavo();
            }
        }
    }   
    
    /**
     * Hráč2 sa posunie dole na bojovom poli.
     */
    public void posunDole2() {   
        if (this.hrac1 != null) {
            if (!this.hrac1.jeTuBomba(this.getAktualnyRiadokHraca2() + 1, this.getAktualnyStlpecHraca2())) {
                this.hrac2.chodDole();
            }
        }
    }   
    
    /**
     * Hráč2 sa posunie hore na bojovom poli.
     */
    public void posunHore2() {  
        if (this.hrac1 != null) {
            if (!this.hrac1.jeTuBomba(this.getAktualnyRiadokHraca2() - 1, this.getAktualnyStlpecHraca2())) {
                this.hrac2.chodHore();
            }
        }
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
        if (this.stav == StavHry.NEROZHODNUTA) {
            this.pocitadloHrac1++;
            if (this.pocitadloHrac1 == 10) {
                this.hrac1.bombaVybuchni();
                
                if (this.hrac1.jeTuVybuch(this.getAktualnyRiadokHraca2(), this.getAktualnyStlpecHraca2())) {
                    this.hrac2.skryBombermana();
                    this.hrac2 = null;
                }
            }
            
            if (this.pocitadloHrac1 == 12) {
                this.hrac1.skryVybuch();
                this.pocitadloHrac1 = 0;
                    
                if (!this.hrac1.getStavBombermana()) {
                    this.hrac1 = null;
                }
                this.skontrolujStavHry();
            }
        
            this.pocitadloHrac2++;
            if (this.pocitadloHrac2 == 10) {
                this.hrac2.bombaVybuchni();
                
                if (this.hrac2.jeTuVybuch(this.getAktualnyRiadokHraca1(), this.getAktualnyStlpecHraca1())) {
                    this.hrac1.skryBombermana();
                    this.hrac1 = null;
                }
            }
            
            if (this.pocitadloHrac2 == 12) {
                this.hrac2.skryVybuch();
                this.pocitadloHrac1 = 0;
                
                if (!this.hrac2.getStavBombermana()) {
                    this.hrac1 = null;
                }
                this.skontrolujStavHry();
            }
            
            if (this.stav != StavHry.NEROZHODNUTA) {
                if (this.stav == StavHry.REMIZA) {
                    System.out.println("Obaja hráči sa zabili v rovnakom čase a výsledkom je REMÍZA ! ");
                } else if (this.stav == StavHry.VYHRAPRVEHO) {
                    System.out.println("Túto bitku vyhral Prvý hráč s farbou: " + this.farbaPrvehoHraca);
                } else {
                    System.out.println("Túto bitku vyhral Druhý hráč s farbou: " + this.farbaDruhehoHraca);
                }
            }
        }
    }
    
    /**
     * Vráti aktuálny riadok Hráča1.
     * 
     * @return hodnota riadku prvého Hráča v celom čísle.
     */
    public int getAktualnyRiadokHraca1() {  
        return this.hrac1.getAktualnyRiadokBombermana();
    }
    
    /**
     * Vráti aktuálny stĺpec Hráča1.
     * 
     * @return hodnota stĺpca prvého Hráča v celom čísle.
     */
    public int getAktualnyStlpecHraca1() {  
        return this.hrac1.getAktualnyStlpecBombermana();
    }
    
    /**
     * Vráti aktuálny riadok Hráča2.
     * 
     * @return hodnota riadku druhého Hráča v celom čísle.
     */
    public int getAktualnyRiadokHraca2() {  
        return this.hrac2.getAktualnyRiadokBombermana();
    }
    
    /**
     * Vráti aktuálny stĺpec Hráča2.
     * 
     * @return hodnota stĺpca druhého Hráča v celom čísle.
     */
    public int getAktualnyStlpecHraca2() { 
        return this.hrac2.getAktualnyStlpecBombermana();
    }
    
    /**
     * Kontroluje priebeh hry.
     */
    public void skontrolujStavHry() {
        if (this.hrac1 != null && this.hrac2 == null) {
            this.stav = StavHry.VYHRAPRVEHO;
        } else if (this.hrac1 == null && this.hrac2 != null) {
            this.stav = StavHry.VYHRADRUHEHO;
        } else if (this.hrac1 == null && this.hrac2 == null) {
            this.stav = StavHry.REMIZA;
        } else {
            this.stav = StavHry.NEROZHODNUTA;
        }
    }
    
    /**
     * Vráti stav hry po úspešnom boji.
     * 
     * @return trieda ENUM => StavHry.
     */
    public StavHry getStavHry() {
        return this.stav;
    }
    
    /**
     * Pomocou klávesy ESC možnosť vypnutia hry.
     */
    public void zrus() {
        System.exit(0);
    }
}
