/**
 * Trieda náboj, tvorená prostredníctvom tvarov v3, využíva Pištol a je potrebná na strieľanie.
 */
package strielackadavo.hrac.zbrane;

import strielackadavo.grafika.Kruh;
import strielackadavo.hrac.Panacik;
import strielackadavo.prostredie.Mapa;

/**
 *
 * @author Pavličko.D
 */
public class Naboj {
    private Kruh naboj;
    private final Panacik panacik;
    private final Mapa mapa;
    
    private int riadok;
    private int stlpec;
    
    /**
     * Konštruktor implementuje panacika a mapu
     * @param panacik
     * @param mapa 
     */
    public Naboj(Panacik panacik, Mapa mapa) {
        this.panacik = panacik;
        this.mapa = mapa;
    }

    public int getAktualnyRiadok() {
        if (this.naboj != null) {
            return this.riadok;
        }
        return 999;
    }

    public int getAktualnyStlpec() {
        if (this.naboj != null) {
            return this.stlpec;
        }
        return 999;
    }
    
    /**
     * Metóda vytvorí náboj pomocou tvarov v3 na zadaných súradniciach x, y
     * @param riadok
     * @param stlpec 
     */
    public void vytvorNaboj(int riadok, int stlpec) {
        this.naboj = new Kruh();
        this.naboj.zmenFarbu("yellow");
        this.naboj.zmenPriemer(5);
        this.naboj.posunVodorovne(stlpec * 50);
        this.naboj.posunZvisle(-40 + riadok * 50);
        this.naboj.zobraz();
        
        this.riadok = riadok;
        this.stlpec = stlpec;
    }
    
    /**
     * Metóda simuluje výstrel v smere určenia vzhľadom na nepriateľa, alebo steny,
     * kde kontroluje kolíziu s nimi...
     * @param smerom
     * @param nepriatel 
     */
    public void vystrel(String smerom, Panacik nepriatel) {
        switch (smerom) {
            case "vpravo":
                this.vytvorNaboj(this.panacik.getAktualnyRiadok(), this.panacik.getAktualnyStlpec() + 1);
                   
                while (!this.kolizia(nepriatel)) {                        
                    this.naboj.posunVodorovne(50);
                    this.stlpec++;
                }
                break;
            case "vlavo":
                this.vytvorNaboj(this.panacik.getAktualnyRiadok(), this.panacik.getAktualnyStlpec() - 1);
                
                while (!this.kolizia(nepriatel)) {                        
                    this.naboj.posunVodorovne(-50);
                    this.stlpec--;
                }
                break;
            case "hore":
                this.vytvorNaboj(this.panacik.getAktualnyRiadok() - 1, this.panacik.getAktualnyStlpec());
                
                while (!this.kolizia(nepriatel)) {                        
                    this.naboj.posunZvisle(-50);
                    this.riadok--;
                }
                break;
            case "dole":
                this.vytvorNaboj(this.panacik.getAktualnyRiadok() + 1, this.panacik.getAktualnyStlpec());
                
                while (!this.kolizia(nepriatel)) {                        
                    this.naboj.posunZvisle(50);
                    this.riadok++;
                }
                break;
        }
    }
    
    /**
     * Metóda skryje náboj.
     */
    public void skryNaboj() {
        this.naboj.skry();
    }

    /**
     * Metóda kontroluje kolíziu s hráčom alebo stenou.
     * @param nepriatel
     * @return 
     */
    private boolean kolizia(Panacik nepriatel) {
        if (nepriatel.getAktualnyRiadok() == this.getAktualnyRiadok() && nepriatel.getAktualnyStlpec() == this.getAktualnyStlpec()) {
            nepriatel.uberZivotOKolko(1);
            this.krv();
            return true;
        } else if (this.mapa.isStena(this.getAktualnyRiadok(), this.getAktualnyStlpec())) {
            return true;
        } else if (this.getAktualnyStlpec() > 20 || this.getAktualnyStlpec() < 0) {
            return true;
        }
        
        return false;
    }

    /**
     * Metóda simulujúca krv.
     */
    private void krv() {
        this.naboj.zmenPriemer(10);
        this.naboj.zmenFarbu("red");
    }
}
