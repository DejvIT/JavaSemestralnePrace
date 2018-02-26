/**
 * Trieda Lekarnicka je jeden z objektov, ktoré hráč môźe použiť.
 */
package strielackadavo.prostredie.objekty;

import strielackadavo.grafika.Obdlznik;
import strielackadavo.grafika.Stvorec;
import strielackadavo.hrac.Panacik;


public class Lekarnicka implements IObjekt {
    private final Stvorec krabicka;
    private final Obdlznik[] kriz;
    private final int riadok;
    private final int stlpec;
    private final String nazov;
    private boolean pouzita;

    /**
     * Inicializácia lekarničky pomocou tvarov v3.
     * @param nazov
     * @param riadok
     * @param stlpec 
     */
    public Lekarnicka(String nazov, int riadok, int stlpec) {
        this.nazov = nazov;
        this.riadok = riadok;
        this.stlpec = stlpec;
        this.pouzita = false;
        
        this.krabicka = new Stvorec();
        this.krabicka.zmenFarbu("white");
        this.krabicka.zmenStranu(30);
        this.krabicka.posunVodorovne(-50 + (this.stlpec * 50));
        this.krabicka.posunZvisle(-30 + (this.riadok * 50));
        this.krabicka.zobraz();
        
        this.kriz = new Obdlznik[2];
        for (int i = 0; i < 2; i++) {
            this.kriz[i] = new Obdlznik();
            this.kriz[i].zmenFarbu("red");
            this.kriz[i].zobraz();
            this.kriz[i].posunVodorovne(-47 + (this.stlpec * 50) + (i * 9));
            this.kriz[i].posunZvisle(-20 + (this.riadok * 50) - (i * 9));
        }
        this.kriz[0].zmenStrany(25, 8);
        this.kriz[1].zmenStrany(8, 25);
    }

    
    @Override
    public String getNazov() {
        return this.nazov;
    }

    /**
     * Lekarnička doplní zdravie hráčovi ak cez ňu prejde.
     * @param panacik 
     */
    @Override
    public void doAkcia(Panacik panacik) {
        if (panacik.getAktualnyRiadok() == this.riadok && panacik.getAktualnyStlpec() == this.stlpec) {
            if (panacik.getZivot() < 3 && this.pouzita == false) {
                panacik.pridajZivotOKolko(1);
                this.pouzita = true;
                this.lekarnickaSkry();
            }
        }
    }

    @Override
    public int getRiadok() {
        return this.riadok;
    }

    @Override
    public int getStlpec() {
        return this.stlpec;
    }

    /**
     * Metóda skryje lekárničku.
     */
    private void lekarnickaSkry() {
        this.krabicka.skry();
        for (int i = 0; i < this.kriz.length; i++) {
            this.kriz[i].skry();
        }
    }

    @Override
    public void doEfekt() {
        //tento objekt nevyužíva žiadne efekty
    }
    
    
    
}
