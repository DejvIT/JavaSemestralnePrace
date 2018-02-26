/**
 * Trieda ZoznamObjektov uchováva v sebe všetky objekty, ktoré sa na mape vyskytujú.
 */
package strielackadavo.prostredie;

import java.util.TreeMap;
import strielackadavo.prostredie.objekty.IObjekt;

/**
 *
 * @author Pavličko.D
 */
public class ZoznamObjektov {
    private final TreeMap<String, IObjekt> objekty;

    /**
     * Konštruktor vytvorí prázdny zoznam.
     */
    public ZoznamObjektov() {
        this.objekty = new TreeMap<String, IObjekt>();
    }
    
    /**
     * Metóda pridá objekt do zoznamu.
     * @param objekt 
     */
    public void pridajObjekt(IObjekt objekt) {
        this.objekty.put(objekt.getNazov(), objekt);
    }
    
    /**
     * Metóda odstráni objekt zo zoznamu.
     * @param nazovObjektu 
     */
    public void odstranObjekt(String nazovObjektu) {
        this.objekty.remove(nazovObjektu);
    }
    
    /**
     * Metóda vracia objekt zo zoznamu.
     * @param nazovObjektu
     * @return 
     */
    public IObjekt getObjekt(String nazovObjektu) {
        return this.objekty.get(nazovObjektu);
    }
    
    int getSize() {
        return this.objekty.size();
    }
    
    /**
     * Metóda vráti objekt na danej pozícií.
     * @param riadok
     * @param stlpec
     * @return 
     */
    public IObjekt getObjekt(int riadok, int stlpec) {
        for (IObjekt tmpObjekt : this.objekty.values()) {
            IObjekt objekt = tmpObjekt;
            if (objekt.getRiadok() == riadok && objekt.getStlpec() == stlpec) {
                return objekt;
            }
        }
        return null;
    }
    
    /**
     * Metóda posiela správu interfacu na riadenie objektov.
     */
    public void doEfekt() {
        for (IObjekt objekt : this.objekty.values()) {
            objekt.doEfekt();
        }
    }
    
}
