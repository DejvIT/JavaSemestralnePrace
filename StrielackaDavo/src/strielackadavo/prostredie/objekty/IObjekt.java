/**
 * Interface pre interaktívne Objekty v mape.
 */
package strielackadavo.prostredie.objekty;

import strielackadavo.hrac.Panacik;

/**
 *
 * @author Pavličko.D
 */
public interface IObjekt {
    String getNazov();
    void doAkcia(Panacik panacik);
    int getRiadok();
    int getStlpec();
    void doEfekt();

}
