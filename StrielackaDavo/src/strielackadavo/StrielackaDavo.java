/*
 * Trieda s main metódou, ktorá len zavolá základne rozhranie tzv. "menu", kde 
 * po kliknutí na tlačitko Hrať sa spustí daná hra.
 */
package strielackadavo;


/**
 * TODO: zivoty(hotovo), lekarnicka(hotovo), naboje(hotovo), pistol(hotovo), 
 * baseballka(hotovo), teleport(hotovo), skratka mimo mapu(hotovo), kolizia s hracom aj stenou(hotovo), 
 * vybuch(hotovo), regeneracia zdravia, GUI(hotovo), easter-egg(cheaty hotove)
 * 
 * @author Pavličko.D
 */
public class StrielackaDavo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.setVisible(true);
    }
    
}
