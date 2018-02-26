/**
 * Trieda Mina je jeden z objektov na bojisku, ktoré interaktívne pracujú s hráčom.
 */
package strielackadavo.prostredie.objekty;

import strielackadavo.grafika.Kruh;
import strielackadavo.hrac.Panacik;


public class Mina implements IObjekt {
    private final Kruh[] mina;
    private final Kruh explozia;
    private final int riadok;
    private final int stlpec;
    private int pocitadlo;
    private final String nazov;
    private boolean vybuchnuta;
    

    /**
     * Konštruktor inicializuje minu pomocou tvarov v3 a uloží na danú pozíciu v mape.
     * @param nazov
     * @param riadok
     * @param stlpec 
     */
    public Mina(String nazov, int riadok, int stlpec) {
        this.nazov = nazov;
        this.riadok = riadok;
        this.stlpec = stlpec;
        this.pocitadlo = 0;
        this.vybuchnuta = false;
        
        this.mina = new Kruh[2];
        for (int i = 0; i < 2; i++) {
            this.mina[i] = new Kruh();
            this.mina[i].zmenPriemer((i * 10) + 8);
            this.mina[i].zmenFarbu("black");
            this.mina[i].posunVodorovne((this.stlpec * 50 + -3) + (i * -4));
            this.mina[i].posunZvisle((i * -4) + (-25 + this.riadok * 50));
            this.mina[i].zobraz();
        }
        
        this.explozia = new Kruh();
        this.explozia.zmenFarbu("yellow");
        this.explozia.zmenPriemer(100);
        this.explozia.posunVodorovne(-50 + this.stlpec * 50);
        this.explozia.posunZvisle(-70 + this.riadok * 50);
    }
    

    @Override
    public String getNazov() {
        return this.nazov;
    }

    /**
     * Po šlapnutí na mínu dochádza k explozii a hráč utrpí škodu.
     * @param panacik 
     */
    @Override
    public void doAkcia(Panacik panacik) {
        if (!this.vybuchnuta) {
            if (panacik.getAktualnyRiadok() == this.riadok && panacik.getAktualnyStlpec() == this.stlpec) {
                this.explozia.zobraz();
                this.pocitadlo = 0;
                
                panacik.uberZivotOKolko(2);
                for (int i = 0; i < this.mina.length; i++) {
                    this.vybuchnuta = true;
                    this.mina[i].skry();
                }
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
     * Metóda riadi svetielko na míne.
     */
    @Override
    public void doEfekt() {
        this.pocitadlo++;
            
        if (this.pocitadlo % 4 == 0) {
            this.mina[0].zmenFarbu("red");
            this.explozia.skry();
        } else {
            this.mina[0].zmenFarbu("black");
        } 
        
    }
}
