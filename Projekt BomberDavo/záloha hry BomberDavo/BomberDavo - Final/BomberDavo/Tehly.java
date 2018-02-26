
/**
 * Obsahuje referenciu na triedu Tehly, ide o zjednodušenie,
 * prehľad a lepšiu použivateľnosť danej triedy.
 * 
 * @author (Dávid Pavličko) 
 * @version (28.11.2015)
 */
public class Tehly {
    private TehlyCasti tehly;
    
    /**
     * Konštruktor vytvorí objekt - tehly, kde si pýta referenciu na triedu Tehly.
     */
    public Tehly() {
        this.tehly = new TehlyCasti();
    }
    
    /**
     * Zobrazí daný objekt vytvorený v konštruktore.
     */
    public void zobraz() {
        this.tehly.zobrazTehly();
    }
    
    /**
     * Skryje daný objekt vytvorený v konštruktore.
     */
    public void skry() {
        this.tehly.skryTehly();
    }
    
    /**
     * Posúva objekt - tehly vodorovným smerom.
     * 
     * @param vzdialenosť v celých číslach.
     */
    public void posunVodorovne(int vzdialenost) {
        this.tehly.posunTehlyVodorovne(vzdialenost);
    }
    
    /**
     * Posúva objekt - tehly v zvislom smere.
     * 
     * @param vzdialenosť v celých číslach.
     */
    public void posunZvisle(int vzdialenost) {
        this.tehly.posunTehlyZvisle(vzdialenost);
    } 
}
