import javax.swing.JOptionPane;

/**
 * Trieda main => Celá hra sa vytvorí prostredníctvom tejto triedy.
 * Trieda vytvorí bojové pole s dvomi hráčmi, ktorí si volia vlastnú farbu svojho atentátnika.
 * Následne nastáva horúce peklo :-)
 * 
 * @author (Dávid Pavličko) 
 * @version (12.12.2015)
 */
public class BomberMain {
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "BomberDavo je hra pre dvoch hráčov, ktorý sa navzájom snažia zničiť pomocou hodených bômb, možné farby na výber pre svojho atentátnika sú: white, red, blue, magenta, black a yellow. ");
        
        String farba1 = JOptionPane.showInputDialog(null, "Napíš farbu prvého Atentátnika ! => Hru ovládaš šípkami a bombu hodíš ENTEROM.");
        String farba2 = JOptionPane.showInputDialog(null, "Napíš farbu druhého Atentátnika ! => Hru ovládaš WASD a bombu hodíš MEDZERNIKOM.");
        
        JOptionPane.showMessageDialog(null, "Hra sa začne za pár sekund, prosím o chvíľku strpenia. ");
        
        DvajaHraci bomberDavo = new DvajaHraci(farba1, farba2);
    }
}
