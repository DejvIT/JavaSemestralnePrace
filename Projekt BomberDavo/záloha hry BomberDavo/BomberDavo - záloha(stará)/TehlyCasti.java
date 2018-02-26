
/**
 * Táto trieda vytvára grafický objekt pomocou Obdlžníkov do útvaru tzv. tehál.
 * 
 * @author (Dávid Pavličko) 
 * @version (28.11.2015)
 */
public class TehlyCasti {
    private Stvorec podklad;
    private Obdlznik[][] tehla;
    private Obdlznik[] tehlaDnu;
    
    /**
     * Constructor for objects of class ZnicitelnaStena
     * Vytvára objekt - tehly.
     */
    public TehlyCasti() {
        this.podklad = new Stvorec();
        this.podklad.zmenFarbu("black");
        this.podklad.zmenStranu(50);
        this.podklad.zobraz();
        
        this.tehla = new Obdlznik[5][2];
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 2; x++) {
                this.tehla[y][x] = new Obdlznik();
                this.tehla[y][x].zmenFarbu("red");
                
                if (y == 0 || y == 2 || y == 4) {
                    this.tehla[y][x].zmenStrany(22, 8);
                    this.tehla[y][x].posunVodorovne(2 + x * 24);
                    this.tehla[y][x].posunZvisle(1 + y * 10);
                }
                
                if (y == 1 || y == 3) {
                    this.tehla[y][x].zmenStrany(11, 8);
                    this.tehla[y][x].posunVodorovne(x * 39);
                    this.tehla[y][x].posunZvisle(1 + y * 10);
                }
                
                this.tehla[y][x].zobraz();
            }
        }
        
        this.tehlaDnu = new Obdlznik[2];
        for (int y = 0; y < 2; y++) {
            this.tehlaDnu[y] = new Obdlznik();
            this.tehlaDnu[y].zmenStrany(24, 8);
            this.tehlaDnu[y].zmenFarbu("red");
            this.tehlaDnu[y].posunVodorovne(13);
            this.tehlaDnu[y].posunZvisle(11 + y * 20);
            this.tehlaDnu[y].zobraz();
        }
    }
    
    /**
     * Zobrazí daný objekt vytvorený v konštruktore.
     */
    public void zobrazTehly() {
        this.podklad.zobraz();
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 2; x++) {
                this.tehla[y][x].zobraz();
            }
        }
        this.tehlaDnu[0].zobraz();
        this.tehlaDnu[1].zobraz();
    }
    
    /**
     * Skryje daný objekt vytvorený v konštruktore.
     */
    public void skryTehly() {
        this.podklad.skry();
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 2; x++) {
                this.tehla[y][x].skry();
            }
        }
        this.tehlaDnu[0].skry();
        this.tehlaDnu[1].skry();
    }
    
    /**
     * Posúva objekt - tehly vodorovným smerom.
     */
    public void posunTehlyVodorovne(int vzdialenost) {
        this.podklad.posunVodorovne(vzdialenost);
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 2; x++) {
                this.tehla[y][x].posunVodorovne(vzdialenost);
            }
        }
        this.tehlaDnu[0].posunVodorovne(vzdialenost);
        this.tehlaDnu[1].posunVodorovne(vzdialenost);
    }
    
    /**
     * Posúva objekt - tehly v zvislom smere.
     */
    public void posunTehlyZvisle(int vzdialenost) {
        this.podklad.posunZvisle(vzdialenost);
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 2; x++) {
                this.tehla[y][x].posunZvisle(vzdialenost);
            }
        }
        this.tehlaDnu[0].posunZvisle(vzdialenost);
        this.tehlaDnu[1].posunZvisle(vzdialenost);
    } 
}
