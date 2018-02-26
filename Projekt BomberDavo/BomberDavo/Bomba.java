
/**
 * Táto trieda obsahuje bombu, jej časti z ktorých sa skladá, 
 * možnosť zmeny farby loga pre daného Bombermana
 * a lokalizačné metódy.
 * 
 * @author (Dávid Pavličko) 
 * @version (27.11.2015)
 */
public class Bomba {
    private Kruh bomba;
    private Obdlznik[] znak;
    
    private int riadok;
    private int stlpec;
    private boolean[][] jeTuBomba;
    
    /**
     * Konštruktor vytvorí objekt - bomba v danom riadku a stĺpci.
     * 
     * @param riadok, stĺpec
     */
    public Bomba(int riadok, int stlpec) {
        this.stlpec = stlpec;
        this.riadok = riadok;
        /** Podmienky pre zabezpečenie nesprávneho zadávania pozicie bomby pri vytváraní */
        if (riadok > 18) { //riadok
            riadok = 1;
        } else if (riadok < 0) {
            riadok = 1;
        } else {
            this.riadok = riadok;
        }
        
        if (stlpec > 18) { //stlpec
            stlpec = 1;
        } else if (stlpec < 0) {
            stlpec = 1;
        } else {
            this.stlpec = stlpec;
        }
        
        
        /** Bomba */
        this.bomba = new Kruh();
        this.bomba.zmenPriemer(50);
        this.bomba.zmenFarbu("black");
        this.bomba.posunVodorovne(30 + this.stlpec * 50);
        this.bomba.posunZvisle(-10 + this.riadok * 50);
        
        /** Znak T */
        this.znak = new Obdlznik[2];
        for (int i = 0; i < 2; i++) {
            this.znak[i] = new Obdlznik();
            this.znak[i].zmenFarbu("red");
            if (i == 0) {
                this.znak[i].zmenStrany(7, 25);
                this.znak[i].posunVodorovne(12 + this.stlpec * 50);
                this.znak[i].posunZvisle(10 + this.riadok * 50);
            } else {
                this.znak[i].zmenStrany(25, 7);
                this.znak[i].posunVodorovne(3 + this.stlpec * 50);
                this.znak[i].posunZvisle(10 + this.riadok * 50);
            }
        }
        
        this.jeTuBomba = new boolean[18][18];
        this.jeTuBomba[this.riadok][this.stlpec] = false;
    }
    
    /**
     * Zmena farby požadovanej pre daného Bombermana.
     * Dôvod rozpoznania vlastnej bomby na bojovom poli.
     * 
     * Možné farby na výber sú:
     * červená - red
     * žltá - yellow
     * fialová - magenta
     * čierna - black
     * biela - white
     */
    public void setFarbaBomby(String farba) {
        switch (farba) {
            case "red":
                this.znak[0].zmenFarbu("red");
                this.znak[1].zmenFarbu("red");
                break;
            case "yellow":
                this.znak[0].zmenFarbu("yellow");
                this.znak[1].zmenFarbu("yellow");
                break;
            case "blue":
                this.znak[0].zmenFarbu("blue");
                this.znak[1].zmenFarbu("blue");
                break;
            case "magenta":
                this.znak[0].zmenFarbu("magenta");
                this.znak[1].zmenFarbu("magenta");
                break;
            case "black":
                this.znak[0].zmenFarbu("black");
                this.znak[1].zmenFarbu("black");
                break;
            case "white":
                this.znak[0].zmenFarbu("white");
                this.znak[1].zmenFarbu("white");
                break;
                
            default:
                this.znak[0].zmenFarbu("white");
                this.znak[1].zmenFarbu("white");
                break;
        }
    }
    
    /**
     * Zobrazí bombu na platne v danom riadku a stĺpci.
     */
    public void zobrazBombu() {
        this.bomba.zobraz();
        this.znak[0].zobraz();
        this.znak[1].zobraz();
        this.jeTuBomba[this.riadok][this.stlpec] = true;
    }
    
    /**
     * Skryje bombu na plátne v danom riadku a stĺpci.
     */
    public void skryBombu() {
        this.bomba.skry();
        this.znak[0].skry();
        this.znak[1].skry();
        this.jeTuBomba[this.riadok][this.stlpec] = false;
    }
    
    /**
     * Vráti aktuálny riadok, v ktorom sa bomba nachádza.
     * 
     * @return hodnota riadku v celom čísle.
     */
    public int getAktualnyRiadok() {
        return this.riadok;
    }
    
    /**
     * Vráti aktuálny stĺpec, v ktorom sa bomba nachádza.
     * 
     * @return hodnota stĺpca v celom čísle.
     */
    public int getAktualnyStlpec() {
        return this.stlpec;
    }
    
    /**
     * Vráti hodnotu, pre zistenie, či sa bomba v danom riadku a stĺpci naozaj nachádza.
     * 
     * @param riadok, stĺpec
     * @return hodnota true alebo false.
     */
    public boolean jeTuBomba(int riadok, int stlpec) {
        return this.jeTuBomba[riadok][stlpec];
    }
}
