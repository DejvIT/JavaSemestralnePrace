/**
 * Trieda Naboje je jeden z objektov, ktoré sa vyskytujú na mape.
 */
package strielackadavo.prostredie.objekty;

import strielackadavo.grafika.Kruh;
import strielackadavo.hrac.Panacik;


public class Naboje implements IObjekt {
    private final String nazov;
    private final Kruh[] naboje;
    private final int riadok;
    private final int stlpec;
    private boolean zdvihnute;

    /**
     * Konštruktor inicializuje náboje pomocou tvarov v3 a ukladá ich na danú pozíciu v mape.
     * @param nazov
     * @param riadok
     * @param stlpec 
     */
    public Naboje(String nazov, int riadok, int stlpec) {
        this.nazov = nazov;
        this.riadok = riadok;
        this.stlpec = stlpec;
        this.zdvihnute = false;
        
        this.naboje = new Kruh[3];
        for (int i = 0; i < 3; i++) {
            this.naboje[i] = new Kruh();
            this.naboje[i].zmenFarbu("yellow");
            this.naboje[i].zmenPriemer(5);
            this.naboje[i].posunVodorovne(5 + (i * -8) + this.stlpec * 50);
            this.naboje[i].posunZvisle(-20 + (i * -3) + this.riadok * 50);
            this.naboje[i].zobraz();
        }
        this.naboje[0].posunZvisle(-15);
    }
    

    @Override
    public String getNazov() {
        return this.nazov;
    }

    /**
     * Metóda priradí hráčovi 3 náboje do piśtole po prejdení cez objekt.
     * @param panacik 
     */
    @Override
    public void doAkcia(Panacik panacik) {
        if (panacik.getAktualnyRiadok() == this.riadok && panacik.getAktualnyStlpec() == this.stlpec && this.zdvihnute == false) {
            panacik.pridajMuniciuDoPistole(3);
            this.zdvihnute = true;
            this.nabojeSkry();
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
    
    private void nabojeSkry() {
        for (int i = 0; i < this.naboje.length; i++) {
            this.naboje[i].skry();
        }
    }

    @Override
    public void doEfekt() {
        //tento objekt nevyužíva žiadne efekty
    }
    
    
}
