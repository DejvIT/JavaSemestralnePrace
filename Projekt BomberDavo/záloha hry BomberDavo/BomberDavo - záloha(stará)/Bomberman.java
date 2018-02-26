
/**
 * Funkčná postavička Bombermana s rôznymi metódami ako pohyb, zmena farby, hoď bombu...
 * 
 * @author (Dávid Pavličko) 
 * @version (17.11.2015)
 */
public class Bomberman {
    private BojovePole bojovePole;
    private int riadok;
    private int stlpec;
    
    private BombermanCasti bomberman;
    private String farbaPanacika;
    private boolean[][] jeTuBomberman; 
    
    private Bomba bomba;
    private boolean[][] jeTuBomba;
    private int riadokBomby;
    private int stlpecBomby;
    
    private Vybuch vybuch;
    private boolean[][] jeTuVybuch;
    
    /**
     * Konštruktor vytvorí Bombermana a ten sa priradí triede BojovéPole.
     */
    public Bomberman(BojovePole bojovePole, String farbaPanacika, int riadok, int stlpec) {
        this.bojovePole = bojovePole;
        this.farbaPanacika = farbaPanacika;
        this.riadok = riadok;
        this.stlpec = stlpec;
        
        this.bomberman = new BombermanCasti(this.bojovePole, riadok, stlpec);
        this.bomberman.setFarbaPanacika(farbaPanacika);
        this.jeTuBomberman = new boolean[18][18];
        this.jeTuBomberman[riadok][stlpec] = true;
        
        this.bomba = null;
        this.jeTuBomba = new boolean[18][18];
        this.riadokBomby = 9999;
        this.stlpecBomby = 9999;
        
        this.vybuch = null;
        this.jeTuVybuch = new boolean[18][18];
    }
    
    /**
     * Pohyb Bombermana vpravo.
     */
    public void chodVpravo() { 
        if (this.jeTuVybuch[this.riadok][this.stlpec + 1]) {
            this.bomberman.skryPanacika();
            this.bomberman = null;
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
        if (this.jeTuVybuch[this.riadok][this.stlpec - 1]) {
            this.bomberman.skryPanacika();
            this.bomberman = null;
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
        if (this.jeTuVybuch[this.riadok + 1][this.stlpec]) {
            this.bomberman.skryPanacika();
            this.bomberman = null;
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
        if (this.jeTuVybuch[this.riadok - 1][this.stlpec]) {
            this.bomberman.skryPanacika();
            this.bomberman = null;
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
            
            for (int i = 1; i < 5; i++) {
                this.jeTuVybuch[this.riadokBomby][this.stlpecBomby] = true;
                this.jeTuVybuch[this.riadokBomby - 5 + i][this.stlpecBomby] = true;
                this.jeTuVybuch[this.riadokBomby + i][this.stlpecBomby] = true;
                this.jeTuVybuch[this.riadokBomby][this.stlpecBomby - 5 + i] = true;
                this.jeTuVybuch[this.riadokBomby][this.stlpecBomby + i] = true;
                
                if (i == 1) {
                    if (this.bojovePole.jeTuZnicitelnyObjekt(this.riadokBomby, this.stlpecBomby + 1)) {
                        this.bojovePole.skryStenu(this.riadokBomby, this.stlpecBomby + 1);
                    }
                    
                    if (this.bojovePole.jeTuZnicitelnyObjekt(this.riadokBomby + 1, this.stlpecBomby)) {
                        this.bojovePole.skryStenu(this.riadokBomby + 1, this.stlpecBomby);
                    }
                    
                    if (this.bojovePole.jeTuZnicitelnyObjekt(this.riadokBomby, this.stlpecBomby - 1)) {
                        this.bojovePole.skryStenu(this.riadokBomby, this.stlpecBomby - 1);
                    }
                    
                    if (this.bojovePole.jeTuZnicitelnyObjekt(this.riadokBomby - 1, this.stlpecBomby)) {
                        this.bojovePole.skryStenu(this.riadokBomby - 1, this.stlpecBomby);
                    }
                    
                } else if (i == 2) {
                    if (this.bojovePole.jeTuZnicitelnyObjekt(this.riadokBomby, this.stlpecBomby + 2)) {
                        this.bojovePole.skryStenu(this.riadokBomby, this.stlpecBomby + 2);
                    }
                    
                    if (this.bojovePole.jeTuZnicitelnyObjekt(this.riadokBomby + 2, this.stlpecBomby)) {
                        this.bojovePole.skryStenu(this.riadokBomby + 2, this.stlpecBomby);
                    }
                    
                    if (this.bojovePole.jeTuZnicitelnyObjekt(this.riadokBomby, this.stlpecBomby - 2)) {
                        this.bojovePole.skryStenu(this.riadokBomby, this.stlpecBomby - 2);
                    }
                    
                    if (this.bojovePole.jeTuZnicitelnyObjekt(this.riadokBomby - 2, this.stlpecBomby)) {
                        this.bojovePole.skryStenu(this.riadokBomby - 2, this.stlpecBomby);
                    }
                    
                } else if (i == 3) {
                    if (this.bojovePole.jeTuZnicitelnyObjekt(this.riadokBomby, this.stlpecBomby + 3)) {
                        this.bojovePole.skryStenu(this.riadokBomby, this.stlpecBomby + 3);
                    }
                    
                    if (this.bojovePole.jeTuZnicitelnyObjekt(this.riadokBomby + 3, this.stlpecBomby)) {
                        this.bojovePole.skryStenu(this.riadokBomby + 3, this.stlpecBomby);
                    }
                    
                    if (this.bojovePole.jeTuZnicitelnyObjekt(this.riadokBomby, this.stlpecBomby - 3)) {
                        this.bojovePole.skryStenu(this.riadokBomby, this.stlpecBomby - 3);
                    }
                    
                    if (this.bojovePole.jeTuZnicitelnyObjekt(this.riadokBomby - 3, this.stlpecBomby)) {
                        this.bojovePole.skryStenu(this.riadokBomby - 3, this.stlpecBomby);
                    }
                    
                } else if (i == 4) {
                    if (this.bojovePole.jeTuZnicitelnyObjekt(this.riadokBomby, this.stlpecBomby + 4)) {
                        this.bojovePole.skryStenu(this.riadokBomby, this.stlpecBomby + 4);
                    }
                    
                    if (this.bojovePole.jeTuZnicitelnyObjekt(this.riadokBomby + 4, this.stlpecBomby)) {
                        this.bojovePole.skryStenu(this.riadokBomby + 4, this.stlpecBomby);
                    }
                    
                    if (this.bojovePole.jeTuZnicitelnyObjekt(this.riadokBomby, this.stlpecBomby - 4)) {
                        this.bojovePole.skryStenu(this.riadokBomby, this.stlpecBomby - 4);
                    }
                    
                    if (this.bojovePole.jeTuZnicitelnyObjekt(this.riadokBomby - 4, this.stlpecBomby)) {
                        this.bojovePole.skryStenu(this.riadokBomby - 4, this.stlpecBomby);
                    }
                }
            }
        }
    }
    
    /**
     * Metóda skryje výbuch od bomby.
     */
    public void skryVybuch() {
        if (this.vybuch != null) {
            this.vybuch.skryVybuch();
            this.vybuch = null;
            
            for (int i = 1; i < 5; i++) {
                this.jeTuVybuch[this.riadokBomby][this.stlpecBomby] = false;
                this.jeTuVybuch[this.riadokBomby - 5 + i][this.stlpecBomby] = false;
                this.jeTuVybuch[this.riadokBomby + i][this.stlpecBomby] = false;
                this.jeTuVybuch[this.riadokBomby][this.stlpecBomby - 5 + i] = false;
                this.jeTuVybuch[this.riadokBomby][this.stlpecBomby + i] = false;
            }
        }
    }
    
    /**
     * Nastaví požadovanú farbu pre Bombermana.
     * Možné farby sú tieto:
     * 
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
     */
    public int getAktualnyRiadokBombermana() {
        return this.riadok;
    }
    
    /**
     * Vráti daný stĺpec, kde sa práve Bomberman nachádza.
     */
    public int getAktualnyStlpecBombermana() {
        return this.stlpec;
    }
    
    /**
     * Vráti hodnotu true, false ak je na danom mieste panáčik.
     */
    public boolean jeTuBomberman(int riadok, int stlpec) {
        return this.jeTuBomberman[riadok][stlpec];
    }
    
    /**
     * Vráti aktuálny riadok bomby, ktorá bola vyhodená panáčikom.
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
     */
    public int getAktualnyStlpecBomby() {
        if (this.bomba != null) {
            return this.stlpecBomby;
        } else {
            return 9999; //Bomba sa nenachádza na bojovom poli
        }
    }
    
    /**
     * Vráti hodnotu true, false ak je na danom mieste bomba.
     */
    public boolean jeTuBomba(int riadok, int stlpec) {
        return this.jeTuBomba[riadok][stlpec];
    }
    
    /**
     * Vráti hodnotu true, false ak je na danom mieste práve výbuch.
     */
    public boolean jeTuVybuch(int riadok, int stlpec) {
        return this.jeTuVybuch[riadok][stlpec];
    }
}
