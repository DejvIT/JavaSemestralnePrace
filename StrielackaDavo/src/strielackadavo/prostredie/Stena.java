/**
 * Trieda Stena vytvára nepriechodne objekty(steny) na hracej ploche.
 */
package strielackadavo.prostredie;

import strielackadavo.grafika.Obdlznik;

/**
 *
 * @author Pavličko.D
 */
public class Stena {
    private final Obdlznik stena;
    private final int stranaA;
    private final int stranaB;
    private final int riadok;
    private final int stlpec;

    /**
     * Konštruktor inicializuje stenu: jej veľkosť, polohu a farbu.
     * @param riadok
     * @param stlpec
     * @param stranaA
     * @param stranaB 
     */
    public Stena(int riadok, int stlpec, int stranaA, int stranaB) {
        this.stena = new Obdlznik();
        this.stena.zmenFarbu("black");
        
        this.stranaA = stranaA;
        this.stranaB = stranaB;
        this.stena.zmenStrany(this.stranaA, this.stranaB);
        
        this.riadok = riadok;
        this.stlpec = stlpec;
        this.stena.posunVodorovne( -60 + (this.stlpec * 50));
        this.stena.posunZvisle( -50 + (this.riadok * 50));
        this.stena.zobraz();
    }
    
    /**
     * Metóda vracia hodnotu boolean, či sa na danej pozícií x a y nachádza stena.
     * @param riadok
     * @param stlpec
     * @return 
     */
    public boolean isStena(int riadok, int stlpec) {
        boolean overenieRiadka = false;
        boolean overenieStlpca = false;
        
        for (int i = this.riadok; i < (this.stranaB / 50) + this.riadok; i++) {
            if (riadok == i) {
                overenieRiadka = true;
            }
        }
        
        for (int j = this.stlpec; j < (this.stranaA / 50) + this.stlpec; j++) {
            if (stlpec == j) {
                overenieStlpca = true;
            }
        }
        
        if (overenieStlpca && overenieRiadka) {
            return true;
        } else {
            return false;
        }
    }
    
    
}
