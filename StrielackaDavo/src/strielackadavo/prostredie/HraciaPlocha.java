/**
 * Trieda HraciaPlocha.
 */
package strielackadavo.prostredie;

import strielackadavo.grafika.Stvorec;

/**
 * 
 * @author Pavličko.D 
 */
public class HraciaPlocha {
    private final Stvorec podlaha;
   
    
    /**
     * Konštruktor inicializuje hraciu plochu.
     */
    public HraciaPlocha() {
        // Podlaha
        this.podlaha = new Stvorec();
        this.podlaha.zmenFarbu("grey");
        this.podlaha.zmenStranu(1000);
        this.podlaha.posunVodorovne(-60);
        this.podlaha.posunZvisle(-50);
        this.podlaha.zobraz();
    }
}

