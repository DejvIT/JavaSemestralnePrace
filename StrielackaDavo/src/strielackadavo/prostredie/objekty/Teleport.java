/**
 * Trieda Teleport je jeden z objektov, ktoré interagujú s hráčom.
 */
package strielackadavo.prostredie.objekty;

import strielackadavo.grafika.Kruh;
import strielackadavo.hrac.Panacik;


public class Teleport implements IObjekt {
    private final String nazov;
    private final int riadok;
    private final int stlpec;
    private final Kruh[] kruhy;
    private int pocitadlo;

    /**
     * Konštruktor inicializuje teleport pomocou tvarov v3 a uloźí ho na danú pozíciu v mape.
     * @param nazov
     * @param riadok
     * @param stlpec 
     */
    public Teleport(String nazov, int riadok, int stlpec) {
        this.nazov = nazov;
        this.riadok = riadok;
        this.stlpec = stlpec;
        this.pocitadlo = 0;
        
        this.kruhy = new Kruh[4];
        for (int i = 0; i < 4; i++) {
            this.kruhy[i] = new Kruh();
            this.kruhy[i].zmenPriemer(50 + (i * -10));
            this.kruhy[i].posunVodorovne(-20 + this.stlpec * 50 + (i * 5));
            this.kruhy[i].posunZvisle(-61 + this.riadok * 50 + (i * 5));
            this.kruhy[i].zobraz();
        }
        this.kruhy[0].zmenFarbu("magenta");
        this.kruhy[1].zmenFarbu("blue");
        this.kruhy[2].zmenFarbu("magenta");
        this.kruhy[3].zmenFarbu("blue");
    }
    
    @Override
    public String getNazov() {
        return this.nazov;
    }

    /**
     * Presunie hráča na iné miesto.
     * @param panacik 
     */
    @Override
    public void doAkcia(Panacik panacik) {
        if (this.riadok == 17 && this.stlpec == 2) {
            panacik.teleport(2, 17);
        } else {
            panacik.teleport(17, 2);
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
     * Metóda riadi blikanie teleportu.
     */
    @Override
    public void doEfekt() {
        this.pocitadlo++;
            
        if (this.pocitadlo % 4 == 0) {
            this.kruhy[0].zmenFarbu("blue");
            this.kruhy[1].zmenFarbu("magenta");
            this.kruhy[2].zmenFarbu("blue");
            this.kruhy[3].zmenFarbu("magenta");
        } else {
            this.kruhy[0].zmenFarbu("magenta");
            this.kruhy[1].zmenFarbu("blue");
            this.kruhy[2].zmenFarbu("magenta");
            this.kruhy[3].zmenFarbu("black");
        } 
    }
    
}
