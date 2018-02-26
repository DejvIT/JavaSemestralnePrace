
/**
 * Táto trieda funguje len ako počítadlo pre objekt - bomba, pre jej hod a výbuch.
 * 
 * @author (Dávid Pavličko) 
 * @version (4.12.2015)
 */
public class Pocitadlo {
    private Manazer manazer;
    private int pocitadlo;
    
    /**
     * Konštruktor vytvorí nové počitadlo a priradí ho do manažéra aby dané počitadlo spravoval.
     */
    public Pocitadlo() {
        this.pocitadlo = 0;
        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this);
    }
    
    /**
     * Metóda tik-tak... základ čo bomba potrebuje => časovač :-)
     */
    public int tik() {
        this.pocitadlo++;
        if (this.pocitadlo % 15 == 0) {
            this.pocitadlo = 0;
            return this.pocitadlo;
        } else {
            return this.pocitadlo;
        }
    }
}
