
/**
 * Funkčná postavička Bombermana, ktorá je priradená na bojové pole.
 * Možnosť pohybu do strán, možnosť hodenia bomby, výbuchu a zničenia možných objektov
 * vrátane seba samého.
 * 
 * @author (Dávid Pavličko) 
 * @version (17.11.2015)
 */
public class Bomberman {
    private BojovePole bojovePole;
    private int riadok;
    private int stlpec;
    
    private BombermanCasti bomberman;
    private boolean jeZivy;
    private String farbaPanacika;
    private boolean[][] jeTuBomberman; 
    
    private Bomba bomba;
    private boolean[][] jeTuBomba;
    private int riadokBomby;
    private int stlpecBomby;
    
    private Vybuch vybuch;
    
    /**
     * Konštruktor vytvorí Bombermana a ten sa priradí triede BojovéPole
     * na zadané súradnice bojového poľa, ktorá je tvorená formou riadkou a stĺpcov.
     * 
     * @param Priradenie do bojového poľa, farba panačíka, súradnice: riadok, stĺpec
     */
    public Bomberman(BojovePole bojovePole, String farbaPanacika, int riadok, int stlpec) {
        this.bojovePole = bojovePole;
        this.farbaPanacika = farbaPanacika;
        this.riadok = riadok;
        this.stlpec = stlpec;
        
        this.bomberman = new BombermanCasti(this.bojovePole, riadok, stlpec);
        this.bomberman.setFarbaPanacika(farbaPanacika);
        this.jeZivy = true;
        this.jeTuBomberman = new boolean[18][18];
        this.jeTuBomberman[riadok][stlpec] = true;
        
        this.bomba = null;
        this.jeTuBomba = new boolean[18][18];
        this.riadokBomby = 9999;
        this.stlpecBomby = 9999;
        
        this.vybuch = null;
    }
    
    /**
     * Pohyb Bombermana vpravo.
     */
    public void chodVpravo() { 
        if (this.vybuch != null) {
            if (this.vybuch.jeTuVybuch(this.riadok, this.stlpec + 1)) {
                this.bomberman.skryPanacika();
                this.jeZivy = false;
                this.bomberman = null;
            }
        }
        
        if (this.bomberman != null) {
            if (!this.jeTuBomba[this.riadok][this.stlpec + 1]) {
                this.jeTuBomberman[this.riadok][this.stlpec] = false;
                this.bomberman.posunVpravo();
                this.bomberman.getPolohuTelaDoStrany();
                this.stlpec = this.bomberman.getAktualnyStlpec();    
        
                this.jeTuBomberman[this.riadok][this.bomberman.getAktualnyStlpec()] = true; 
            }
        }
    }  
    
    /**
     * Pohyb Bombermana vľavo.
     */
    public void chodVlavo() { 
        if (this.vybuch != null) {
            if (this.vybuch.jeTuVybuch(this.riadok, this.stlpec - 1)) {
                this.bomberman.skryPanacika();
                this.jeZivy = false;
                this.bomberman = null;
            }
        }
        
        if (this.bomberman != null) {
            if (!this.jeTuBomba[this.riadok][this.stlpec - 1]) {
                this.jeTuBomberman[this.riadok][this.stlpec] = false;
                this.bomberman.posunVlavo();
                this.bomberman.getPolohuTelaDoStrany();
                this.stlpec = this.bomberman.getAktualnyStlpec();
        
                this.jeTuBomberman[this.riadok][this.bomberman.getAktualnyStlpec()] = true;
            }
        }
    }   
    
    /**
     * Pohyb Bombermana dole.
     */
    public void chodDole() { 
        if (this.vybuch != null) {
            if (this.vybuch.jeTuVybuch(this.riadok + 1, this.stlpec)) {
                this.bomberman.skryPanacika();
                this.jeZivy = false;
                this.bomberman = null;
            }
        }
        
        if (this.bomberman != null) {
            if (!this.jeTuBomba[this.riadok + 1][this.stlpec]) {
                this.jeTuBomberman[this.riadok + 1][this.stlpec] = false;
                this.bomberman.posunDole();
                this.bomberman.getPolohuTelaRovno();
                this.riadok = this.bomberman.getAktualnyRiadok();
        
                this.jeTuBomberman[this.bomberman.getAktualnyRiadok()][this.stlpec] = true;
            }
        }
    }   
    
    /**
     * Pohyb Bombermana hore.
     */
    public void chodHore() {  
        if (this.vybuch != null) {
            if (this.vybuch.jeTuVybuch(this.riadok - 1, this.stlpec)) {
                this.bomberman.skryPanacika();
                this.jeZivy = false;
                this.bomberman = null;
            }
        }
        
        if (this.bomberman != null) {
            if (!this.jeTuBomba[this.riadok - 1][this.stlpec]) {
                this.jeTuBomberman[this.riadok - 1][this.stlpec] = false;
                this.bomberman.posunHore();
                this.bomberman.getPolohuTelaRovno();
                this.riadok = this.bomberman.getAktualnyRiadok();
        
                this.jeTuBomberman[this.bomberman.getAktualnyRiadok()][this.stlpec] = true;
            }
        }
    }   
    
    /**
     * Bomberman hodí bombu na aktuálne miesto kde práve stojí.
     */
    public void hodBombu() {
        if (this.bomba == null) {
            this.bomba = new Bomba(this.bomberman.getAktualnyRiadok(), this.bomberman.getAktualnyStlpec());
            this.bomba.setFarbaBomby(this.farbaPanacika);
            this.bomba.zobrazBombu();
            this.bomba = this.bomba;
            this.riadokBomby = this.bomberman.getAktualnyRiadok();
            this.stlpecBomby = this.bomberman.getAktualnyStlpec(); 
            this.jeTuBomba[this.riadokBomby][this.stlpecBomby] = true;
        }
    }
    
    /**
     * Bomba na danom mieste položenia vybuchne a zničí možné objekty v rámci dosahu výbuchu.
     */
    public void bombaVybuchni() {
        if (this.bomba != null) {
            this.bomba.skryBombu();
            this.bomba = null;
            this.jeTuBomba[this.riadokBomby][this.stlpecBomby] = false;
            
            this.vybuch = new Vybuch(this.bojovePole, this.riadokBomby, this.stlpecBomby);
            this.vybuch.zobrazVybuch();
            
            if (this.jeTuBomberman(this.riadok, this.stlpec) && this.vybuch.jeTuVybuch(this.riadok, this.stlpec)) {
                this.bomberman.skryPanacika();
                this.jeZivy = false;
                this.bomberman = null;
            }
        }
    }
    
    /**
     * Metóda skryje výbuch od bomby.
     */
    public void skryVybuch() {
        if (this.vybuch != null) {
            this.vybuch.znicStenu();
            
            this.vybuch.skryVybuch();
            this.vybuch = null;
        }
    }
    
    /**
     * Nastaví požadovanú farbu pre Bombermana.
     * 
     * @param Možné farby sú tieto:
     * červená - "red"
     * žltá    - "yellow"
     * modrá   - "blue"
     * fialová - "magenta"
     * čierna  - "black"
     */
    public void zmenFarbuBombermana(String farba) {
        this.bomberman.setFarbaPanacika(farba);
    }
    
    /**
     * Vráti aktuálny riadok, kde sa práve Bomberman nachádza.
     * 
     * @return hodnota aktuálneho riadku postavičky v celom čísle.
     */
    public int getAktualnyRiadokBombermana() {
        return this.riadok;
    }
    
    /**
     * Vráti daný stĺpec, kde sa práve Bomberman nachádza.
     * 
     * @return hodnota aktuálneho stĺpca postavičky v celom čísle.
     */
    public int getAktualnyStlpecBombermana() {
        return this.stlpec;
    }
    
    /**
     * Vráti aktuálny riadok bomby, ktorá bola vyhodená panáčikom.
     * 
     * @return hodnota aktuálneho riadku bomby v celom čísle.
     */
    public int getAktualnyRiadokBomby() {
        if (this.bomba != null) {
            return this.riadokBomby;
        } else {
            return 9999; //Bomba sa nenachádza na bojovom poli
        }
    }
    
    /**
     * Vráti aktuálny stĺpec bomby, ktorá bola vyhodená panáčikom.
     * 
     * @return hodnota aktuálneho stĺpca bomby v celom čísle.
     */
    public int getAktualnyStlpecBomby() {
        if (this.bomba != null) {
            return this.stlpecBomby;
        } else {
            return 9999; //Bomba sa nenachádza na bojovom poli
        }
    }
    
    /**
     * Zisťuje či sa na daných súradniciach nachádza bomba.
     * 
     * @param riadok, stĺpec
     * @return hodnota true alebo false.
     */
    public boolean jeTuBomba(int riadok, int stlpec) {
        return this.jeTuBomba[riadok][stlpec];
    }
    
    /**
     * Zisťuje či je na daných súradniciach výbuch od bomby.
     * 
     * @param riadok, stĺpec
     * @return hodnota true alebo false.
     */
    public boolean jeTuVybuch(int riadok, int stlpec) {
        return this.vybuch.jeTuVybuch(riadok, stlpec);
    }
    
    /**
     * Vráti hodnotu, ak je na danom mieste panáčik.
     * 
     * @param riadok, stĺpec
     * @return hodnota true alebo folse
     */
    public boolean jeTuBomberman(int riadok, int stlpec) {
        return this.jeTuBomberman[riadok][stlpec];
    }
    
    /** 
     * Vykresli Bombermana na plátno.
     */
    public void zobrazBombermana() {
        this.bomberman.zobrazPanacika();
    }
    
    /**
     * Skryje Bombermana na plátne.
     */
    public void skryBombermana() {
        this.bomberman.skryPanacika();
        this.bomberman = null;
    }
    
    /**
     * Vráti hodnotu, na základe ktorej vieme čí je Bomberman živý, alebo mŕtvy.
     * 
     * @return hodnota true alebo false.
     */
    public boolean getStavBombermana() {
        if (this.bomberman == null) {
            return false;
        } else {
            return this.jeZivy;
        }
    }
}
