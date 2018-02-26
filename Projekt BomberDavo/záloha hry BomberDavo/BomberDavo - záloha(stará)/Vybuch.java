
/**
 * Write a description of class Bomba here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Vybuch {
    private BojovePole bojovePole;
    private Stvorec[][] vybuch;
    
    private int riadok;
    private int stlpec;
    public Vybuch(BojovePole bojovePole, int riadok, int stlpec) {
        this.bojovePole = bojovePole;
        this.stlpec = stlpec;
        this.riadok = riadok;
        
        /** Podmienky pre zabezpečenie nesprávneho zadávania pozicie bomby pri vytváraní */
        if (riadok > this.bojovePole.getPocetRiadkov()) {
            riadok = 1;
        } else if (riadok < 0) {
            riadok = 1;
        } else {
            this.riadok = riadok;
        }
        
        if (stlpec > this.bojovePole.getPocetStlpcov()) {
            stlpec = 1;
        } else if (stlpec < 0) {
            stlpec = 1;
        } else {
            this.stlpec = stlpec;
        }
        
        /** Vytvorenie všetkých poličiek, možných výbuchov */
        this.vybuch = new Stvorec[9][9];
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                if (y == 4 || x == 4) {
                    this.vybuch[y][x] = new Stvorec();
                    this.vybuch[y][x].zmenFarbu("yellow");
                    this.vybuch[y][x].zmenStranu(50);
                }
            }
        }
        
        /** Plamene od výbuchu */
        for (int i = 0; i < 5; i++) {
            if (i == 0) {
                this.vybuch[4][4].posunVodorovne(-10 + this.stlpec * 50);
                this.vybuch[4][4].posunZvisle(this.riadok * 50);
            }
                    
            if (i == 1) {
                this.vybuch[4][5].posunVodorovne(-10 + (this.stlpec + 1) * 50);
                this.vybuch[4][5].posunZvisle(this.riadok * 50);
                        
                this.vybuch[3][4].posunVodorovne(-10 + this.stlpec * 50);
                this.vybuch[3][4].posunZvisle((this.riadok - 1) * 50);
                        
                this.vybuch[4][3].posunVodorovne(-10 + (this.stlpec - 1) * 50);
                this.vybuch[4][3].posunZvisle(this.riadok * 50);
                        
                this.vybuch[5][4].posunVodorovne(-10 + this.stlpec * 50);
                this.vybuch[5][4].posunZvisle((this.riadok + 1) * 50);
            }
            
            if (i == 2) {
                this.vybuch[4][6].posunVodorovne(-10 + (this.stlpec + 2) * 50);
                this.vybuch[4][6].posunZvisle(this.riadok * 50);
                        
                this.vybuch[2][4].posunVodorovne(-10 + this.stlpec * 50);
                this.vybuch[2][4].posunZvisle((this.riadok - 2) * 50);
                        
                this.vybuch[4][2].posunVodorovne(-10 + (this.stlpec - 2) * 50);
                this.vybuch[4][2].posunZvisle(this.riadok * 50);
                        
                this.vybuch[6][4].posunVodorovne(-10 + this.stlpec * 50);
                this.vybuch[6][4].posunZvisle((this.riadok + 2) * 50);
            }
            
            if (i == 3) {
                this.vybuch[4][7].posunVodorovne(-10 + (this.stlpec + 3) * 50);
                this.vybuch[4][7].posunZvisle(this.riadok * 50);
                        
                this.vybuch[1][4].posunVodorovne(-10 + this.stlpec * 50);
                this.vybuch[1][4].posunZvisle((this.riadok - 3) * 50);
                        
                this.vybuch[4][1].posunVodorovne(-10 + (this.stlpec - 3) * 50);
                this.vybuch[4][1].posunZvisle(this.riadok * 50);
                        
                this.vybuch[7][4].posunVodorovne(-10 + this.stlpec * 50);
                this.vybuch[7][4].posunZvisle((this.riadok + 3) * 50);
            }
                   
            if (i == 4) {
                this.vybuch[4][8].posunVodorovne(-10 + (this.stlpec + 4) * 50);
                this.vybuch[4][8].posunZvisle(this.riadok * 50);
                        
                this.vybuch[0][4].posunVodorovne(-10 + this.stlpec * 50);
                this.vybuch[0][4].posunZvisle((this.riadok - 4) * 50);
                        
                this.vybuch[4][0].posunVodorovne(-10 + (this.stlpec - 4) * 50);
                this.vybuch[4][0].posunZvisle(this.riadok * 50);
                        
                this.vybuch[8][4].posunVodorovne(-10 + this.stlpec * 50);
                this.vybuch[8][4].posunZvisle((this.riadok + 4) * 50);
            }
        }
    }
    
    public void zobrazVybuch() {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                if (this.vybuch[y][x] != null) {
                    this.vybuch[y][x].zobraz();
                }
            }
        }
    }
    
    public void skryVybuch() {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                if (this.vybuch[y][x] != null) {
                    this.vybuch[y][x].skry();
                }
            }
        }
    }
}
