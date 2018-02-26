import java.util.Random;

/**
 * Write a description of class Bomba here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Mapa {
    private int riadok;
    private int stlpec;
    
    private Obdlznik[][] okraj;
    private Stvorec[][] neznicitelneBloky;
    
    private Obdlznik[][] znicitelneBloky;
    private Random suradnicaVRiadku;
    private Random suradnicaVStlpci;
    private int znicitelnaSurX;
    private int znicitelnaSurY;
    private int pocetRiadkov;
    private int pocetStlpcov;
    
    private Stvorec trava;
    
    // Objekty v mape
    private boolean[][] znicitelnyObjekt;
    private boolean[][] neznicitelnyObjekt;
    
    public Mapa() {
        
        /**Generovanie okrajov mapy */
        this.okraj = new Obdlznik[2][2];
        for (int y = 0; y < 2; y++) { //riadok 0, riadok 1 = ľavé rohy mapy(horný, dolný)
            for (int x = 0; x < 2; x++) { //stĺpec 0, stĺpec 1 = pravé rohy mapy(horný dolný)
                this.okraj[y][x] = new Obdlznik();
                this.okraj[y][x].zmenFarbu("black");
                this.okraj[y][x].posunVodorovne(-60 + 950 * x);
                this.okraj[y][x].posunZvisle(-50 + 950 * y);
                this.okraj[y][x].zobraz();
                if ((y == 0 && x == 0) || (y == 1 && x == 0)) {
                    this.okraj[y][x].zmenStrany(1000, 50);
                }
                if ((y == 0 && x == 1) || (y == 1 && x == 1)) {
                    this.okraj[y][x].zmenStrany(50, 1000);
                }
                
                /** Táto podmienka je kvôli pravému dolnému rohu v mape, 
                  * aby okraj nevychádzal mimo rozmerov plátna a doplnil chýbajúcu hranicu mapy 
                  * na lavej strane - this.okraj[1][1] sa presunie na súradnice this.okraj[0][0]
                  */
                if (y == 1 && x == 1) {           //presun kvôli nezáporným hodnotám strán obdlžníka
                    this.okraj[1][1].posunVodorovne(-950);
                    this.okraj[1][1].posunZvisle(-950);
                }
            }
        }
        
        /** Generovanie trávy */
        this.trava = new Stvorec();
        this.trava.zmenFarbu("green");
        this.trava.zmenStranu(900);
        this.trava.posunVodorovne(-10);
        this.trava.zobraz();
        
        
        // ======================//
        this.riadok = 0;
        this.stlpec = 0;
        this.pocetRiadkov = 18;
        this.pocetStlpcov = 18;
        //=======================//
        
        /** Generovanie zničitelných blokov */
        this.znicitelnyObjekt = new boolean[18][18];
        this.znicitelneBloky = new Obdlznik[18][18];
        this.suradnicaVStlpci = new Random();
        this.suradnicaVRiadku = new Random();
        this.znicitelnaSurY = 0;
        this.znicitelnaSurX = 0;
        for (this.riadok = 0; this.riadok <  this.pocetRiadkov; this.riadok++) {
            for (this.stlpec = 0; this.stlpec <  this.pocetStlpcov; this.stlpec++) {
                /*
                this.znicitelneBloky[this.riadok][this.stlpec] = new Obdlznik();
                this.znicitelneBloky[this.riadok][this.stlpec].zmenStrany(50, 50);
                this.znicitelneBloky[this.riadok][this.stlpec].zmenFarbu("magenta");
                this.znicitelneBloky[this.riadok][this.stlpec].skry();
                */
                
                this.znicitelnaSurY = this.suradnicaVRiadku.nextInt(18);
                this.znicitelnaSurX = this.suradnicaVStlpci.nextInt(18);
                this.znicitelnyObjekt[this.znicitelnaSurY][this.znicitelnaSurX] = true;
                
                if (this.znicitelneBloky[this.znicitelnaSurY][this.znicitelnaSurX] == null) {
                    this.znicitelneBloky[this.znicitelnaSurY][this.znicitelnaSurX] = new Obdlznik();
                    this.znicitelneBloky[this.znicitelnaSurY][this.znicitelnaSurX].zmenStrany(50, 50);
                    this.znicitelneBloky[this.znicitelnaSurY][this.znicitelnaSurX].zmenFarbu("grey");
                    this.znicitelneBloky[this.znicitelnaSurY][this.znicitelnaSurX].zobraz();
                    this.znicitelneBloky[this.znicitelnaSurY][this.znicitelnaSurX].posunVodorovne(-10 + this.znicitelnaSurX * 50);
                    this.znicitelneBloky[this.znicitelnaSurY][this.znicitelnaSurX].posunZvisle(this.znicitelnaSurY * 50);
                }
                
                
                /** Zmazanie steny v oblasti spawnu hráčov */
                if (this.znicitelneBloky[6][6] != null) {
                    this.znicitelneBloky[6][6].skry();
                    this.znicitelnyObjekt[6][6] = false;
                }
                if (this.znicitelneBloky[6][7] != null) {
                    this.znicitelneBloky[6][7].skry();
                    this.znicitelnyObjekt[6][7] = false;
                }
                if (this.znicitelneBloky[7][6] != null) {
                    this.znicitelneBloky[7][6].skry();  
                    this.znicitelnyObjekt[7][6] = false;
                }
                if (this.znicitelneBloky[11][11] != null) {
                    this.znicitelneBloky[11][11].skry();
                    this.znicitelnyObjekt[11][11] = false;
                }
                if (this.znicitelneBloky[11][10] != null) {
                    this.znicitelneBloky[11][10].skry();
                    this.znicitelnyObjekt[11][10] = false;
                }
                if (this.znicitelneBloky[10][11] != null) {
                    this.znicitelneBloky[10][11].skry();
                    this.znicitelnyObjekt[10][11] = false;
                }
            }
        }
        
        
        /** Generovanie nezničitelných blokov */
        this.neznicitelneBloky = new Stvorec[18][18];
        this.neznicitelnyObjekt = new boolean[18][18];
        for (this.riadok = 0; this.riadok <  this.pocetRiadkov; this.riadok++) {
            for (this.stlpec = 0; this.stlpec <  this.pocetStlpcov; this.stlpec++) {
                this.neznicitelneBloky[this.riadok][this.stlpec] = new Stvorec();
                this.neznicitelneBloky[this.riadok][this.stlpec].zmenFarbu("black");
                this.neznicitelneBloky[this.riadok][this.stlpec].zmenStranu(50);
                if ((this.riadok < 9 && this.riadok % 2 == 1) && (this.stlpec < 9 && this.stlpec % 2 == 1)) {
                    this.neznicitelneBloky[this.riadok][this.stlpec].posunVodorovne(-10 + this.stlpec * 50);
                    this.neznicitelneBloky[this.riadok][this.stlpec].posunZvisle(this.riadok * 50);
                    this.neznicitelneBloky[this.riadok][this.stlpec].zobraz();
                    this.neznicitelnyObjekt[this.riadok][this.stlpec] = true;
                } else if ((this.riadok < 9 && this.riadok % 2 == 1) && (this.stlpec > 9 && this.stlpec % 2 == 0)) {
                    this.neznicitelneBloky[this.riadok][this.stlpec].posunVodorovne(-10 + this.stlpec * 50);
                    this.neznicitelneBloky[this.riadok][this.stlpec].posunZvisle(this.riadok * 50);
                    this.neznicitelneBloky[this.riadok][this.stlpec].zobraz();
                    this.neznicitelnyObjekt[this.riadok][this.stlpec] = true;
                } else if ((this.riadok > 9 && this.riadok % 2 == 0) && (this.stlpec < 9 && this.stlpec % 2 == 1)) {
                    this.neznicitelneBloky[this.riadok][this.stlpec].posunVodorovne(-10 + this.stlpec * 50);
                    this.neznicitelneBloky[this.riadok][this.stlpec].posunZvisle(this.riadok * 50);
                    this.neznicitelneBloky[this.riadok][this.stlpec].zobraz();
                    this.neznicitelnyObjekt[this.riadok][this.stlpec] = true;
                } else if ((this.riadok > 9 && this.riadok % 2 == 0) && (this.stlpec > 9 && this.stlpec % 2 == 0)) {
                    this.neznicitelneBloky[this.riadok][this.stlpec].posunVodorovne(-10 + this.stlpec * 50);
                    this.neznicitelneBloky[this.riadok][this.stlpec].posunZvisle(this.riadok * 50);
                    this.neznicitelneBloky[this.riadok][this.stlpec].zobraz();
                    this.neznicitelnyObjekt[this.riadok][this.stlpec] = true;
                }
            }
        }
    }
    
    /** METÓDY */
    public int getPocetRiadkov() {
        return this.pocetRiadkov;
    }
    
    public int getPocetStlpcov() {
        return this.pocetStlpcov;
    }
    
    public boolean jeTuZnicitelnyObjekt(int riadok, int stlpec) {
        return this.znicitelnyObjekt[riadok][stlpec];
    }
    
    public boolean jeTuNeznicitelnyObjekt(int riadok, int stlpec) {
        return this.neznicitelnyObjekt[riadok][stlpec];
    }
    
    public void skryStenu(int riadok, int stlpec) {
        this.riadok = riadok;
        this.stlpec = stlpec;
        
        if (this.znicitelneBloky[riadok][stlpec] != null) {
            //this.znicitelneBloky[riadok][stlpec].zmenFarbu("green");
            this.znicitelneBloky[riadok][stlpec].skry();
            this.znicitelnyObjekt[riadok][stlpec] = false;
        }
    }
    
    public void zobrazStenu(int riadok, int stlpec) {
        this.riadok = riadok;
        this.stlpec = stlpec;
        
        if (this.znicitelneBloky[riadok][stlpec] == null) {
            this.znicitelneBloky[riadok][stlpec] = new Obdlznik();
            this.znicitelneBloky[riadok][stlpec].zmenStrany(50, 50);
            this.znicitelneBloky[riadok][stlpec].zmenFarbu("grey");
            this.znicitelneBloky[riadok][stlpec].posunVodorovne(-10 + stlpec * 50);
            this.znicitelneBloky[riadok][stlpec].posunZvisle(riadok * 50);
            this.znicitelneBloky[riadok][stlpec].zobraz();
            this.znicitelnyObjekt[riadok][stlpec] = true;
        } else {
            //this.znicitelneBloky[riadok][stlpec].zmenFarbu("magenta");
            this.znicitelneBloky[riadok][stlpec].zobraz();
            this.znicitelnyObjekt[riadok][stlpec] = true;
        }
    }
}

