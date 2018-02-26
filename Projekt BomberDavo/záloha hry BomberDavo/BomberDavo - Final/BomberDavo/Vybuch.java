
/**
 * Trieda vytvorí výbuch, ktorého plamene dosahujú dĺžku až do 4 blokov.
 * Dokáže výbuch zobraziť, skryť, ba aj zničiť možné objekty v dosahu.
 * 
 * @author (Dávid Pavličko) 
 * @version (8.12.2015)
 */
public class Vybuch {
    private BojovePole bojovePole;
    private Stvorec[][] vybuch;
    private boolean[][] jeTuVybuch;
    
    private int riadok;
    private int stlpec;
    
    /**
     * Konštruktor vytvorí výbuch, ktorého dosah plameňa je 4 bloky do všetkých štyroch strán
     * a dokáže vnímať mapu na základe ktorej zničí len prvý zničiteĺný blok,
     * ktorý plameňu príde do cesty.
     * 
     * @param Priradenie do bojového poľa, súradnice: riadok, stĺpec
     */
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
        this.jeTuVybuch = new boolean[18][18];
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
    
    /**
     * Zobrazí výbuch na bojovom poli.
     */
    public void zobrazVybuch() {
        for (int dalsiBlok = 0; dalsiBlok < 5; dalsiBlok++) {
            if (dalsiBlok == 0) {
                this.vybuch[4][4].zobraz();
                this.jeTuVybuch[this.riadok][this.stlpec] = true;
            }
            
            if (dalsiBlok == 1) {
                if (this.jeTuVybuch(this.riadok, this.stlpec)) {
                    if (!this.bojovePole.jeTuNeznicitelnyObjekt(this.riadok, this.stlpec + dalsiBlok)) {
                        if (!this.bojovePole.jeTuZnicitelnyObjekt(this.riadok, this.stlpec)) {
                            this.vybuch[4][4 + dalsiBlok].zobraz();
                            this.jeTuVybuch[this.riadok][this.stlpec + dalsiBlok] = true;
                        }
                    }
                }
                   
                if (this.jeTuVybuch(this.riadok, this.stlpec)) {
                    if (!this.bojovePole.jeTuNeznicitelnyObjekt(this.riadok - dalsiBlok, this.stlpec)) {
                        if (!this.bojovePole.jeTuZnicitelnyObjekt(this.riadok, this.stlpec)) {
                            this.vybuch[4 - dalsiBlok][4].zobraz();
                            this.jeTuVybuch[this.riadok - dalsiBlok][this.stlpec] = true;
                        }
                    }
                }
                
                if (this.jeTuVybuch(this.riadok, this.stlpec)) {
                    if (!this.bojovePole.jeTuNeznicitelnyObjekt(this.riadok, this.stlpec - dalsiBlok)) {
                        if (!this.bojovePole.jeTuZnicitelnyObjekt(this.riadok, this.stlpec)) {
                            this.vybuch[4][4 - dalsiBlok].zobraz();
                            this.jeTuVybuch[this.riadok][this.stlpec - dalsiBlok] = true;
                        }
                    }
                }
                
                if (this.jeTuVybuch(this.riadok, this.stlpec)) {
                    if (!this.bojovePole.jeTuNeznicitelnyObjekt(this.riadok + dalsiBlok, this.stlpec)) {
                        if (!this.bojovePole.jeTuZnicitelnyObjekt(this.riadok, this.stlpec)) {
                            this.vybuch[4 + dalsiBlok][4].zobraz();
                            this.jeTuVybuch[this.riadok + dalsiBlok][this.stlpec] = true;
                        }
                    }
                }
            }
            
            if (dalsiBlok == 2) {
                if (this.jeTuVybuch(this.riadok, this.stlpec + 1)) {
                    if (!this.bojovePole.jeTuNeznicitelnyObjekt(this.riadok, this.stlpec + dalsiBlok)) {
                        if (!this.bojovePole.jeTuZnicitelnyObjekt(this.riadok, this.stlpec + 1)) {
                            this.vybuch[4][4 + dalsiBlok].zobraz();
                            this.jeTuVybuch[this.riadok][this.stlpec + dalsiBlok] = true;
                        }
                    }
                }
                
                if (this.jeTuVybuch(this.riadok - 1, this.stlpec)) {
                    if (!this.bojovePole.jeTuNeznicitelnyObjekt(this.riadok - dalsiBlok, this.stlpec)) {
                        if (!this.bojovePole.jeTuZnicitelnyObjekt(this.riadok - 1, this.stlpec)) {
                            this.vybuch[4 - dalsiBlok][4].zobraz();
                            this.jeTuVybuch[this.riadok - dalsiBlok][this.stlpec] = true;
                        }
                    }
                }
                
                if (this.jeTuVybuch(this.riadok, this.stlpec - 1)) {
                    if (!this.bojovePole.jeTuNeznicitelnyObjekt(this.riadok, this.stlpec - dalsiBlok)) {
                        if (!this.bojovePole.jeTuZnicitelnyObjekt(this.riadok, this.stlpec - 1)) {
                            this.vybuch[4][4 - dalsiBlok].zobraz();
                            this.jeTuVybuch[this.riadok][this.stlpec - dalsiBlok] = true;
                        }
                    }
                }
               
                if (this.jeTuVybuch(this.riadok + 1, this.stlpec)) {
                    if (!this.bojovePole.jeTuNeznicitelnyObjekt(this.riadok + dalsiBlok, this.stlpec)) {
                        if (!this.bojovePole.jeTuZnicitelnyObjekt(this.riadok + 1, this.stlpec)) {
                            this.vybuch[4 + dalsiBlok][4].zobraz();
                            this.jeTuVybuch[this.riadok + dalsiBlok][this.stlpec] = true;
                        }
                    }
                }
            }
            
            if (dalsiBlok == 3) {
                if (this.jeTuVybuch(this.riadok, this.stlpec + 2)) {
                    if (!this.bojovePole.jeTuNeznicitelnyObjekt(this.riadok, this.stlpec + dalsiBlok)) {
                        if (!this.bojovePole.jeTuZnicitelnyObjekt(this.riadok, this.stlpec + 2)) {
                            this.vybuch[4][4 + dalsiBlok].zobraz();
                            this.jeTuVybuch[this.riadok][this.stlpec + dalsiBlok] = true;
                        }
                    }
                }
                
                if (this.jeTuVybuch(this.riadok - 2, this.stlpec)) {
                    if (!this.bojovePole.jeTuNeznicitelnyObjekt(this.riadok - dalsiBlok, this.stlpec)) {
                        if (!this.bojovePole.jeTuZnicitelnyObjekt(this.riadok - 2, this.stlpec)) {
                            this.vybuch[4 - dalsiBlok][4].zobraz();
                            this.jeTuVybuch[this.riadok - dalsiBlok][this.stlpec] = true;
                        }
                    }
                }
                
                if (this.jeTuVybuch(this.riadok, this.stlpec - 2)) {
                    if (!this.bojovePole.jeTuNeznicitelnyObjekt(this.riadok, this.stlpec - dalsiBlok)) {
                        if (!this.bojovePole.jeTuZnicitelnyObjekt(this.riadok, this.stlpec - 2)) {
                            this.vybuch[4][4 - dalsiBlok].zobraz();
                            this.jeTuVybuch[this.riadok][this.stlpec - dalsiBlok] = true;
                        }
                    }
                }
                
                if (this.jeTuVybuch(this.riadok + 2, this.stlpec)) {
                    if (!this.bojovePole.jeTuNeznicitelnyObjekt(this.riadok + dalsiBlok, this.stlpec)) {
                        if (!this.bojovePole.jeTuZnicitelnyObjekt(this.riadok + 2, this.stlpec)) {
                            this.vybuch[4 + dalsiBlok][4].zobraz();
                            this.jeTuVybuch[this.riadok + dalsiBlok][this.stlpec] = true;
                        }
                    }
                }
            }
            
            if (dalsiBlok == 4) {
                if (this.jeTuVybuch(this.riadok, this.stlpec + 3)) {
                    if (!this.bojovePole.jeTuNeznicitelnyObjekt(this.riadok, this.stlpec + dalsiBlok)) {
                        if (!this.bojovePole.jeTuZnicitelnyObjekt(this.riadok, this.stlpec + 3)) {
                            this.vybuch[4][4 + dalsiBlok].zobraz();
                            this.jeTuVybuch[this.riadok][this.stlpec + dalsiBlok] = true;
                        }
                    }
                }
                
                if (this.jeTuVybuch(this.riadok - 3, this.stlpec)) {
                    if (!this.bojovePole.jeTuNeznicitelnyObjekt(this.riadok - dalsiBlok, this.stlpec)) {
                        if (!this.bojovePole.jeTuZnicitelnyObjekt(this.riadok - 3, this.stlpec)) {
                            this.vybuch[4 - dalsiBlok][4].zobraz();
                            this.jeTuVybuch[this.riadok - dalsiBlok][this.stlpec] = true;
                        }
                    }
                }
                
                if (this.jeTuVybuch(this.riadok, this.stlpec - 3)) {
                    if (!this.bojovePole.jeTuNeznicitelnyObjekt(this.riadok, this.stlpec - dalsiBlok)) {
                        if (!this.bojovePole.jeTuZnicitelnyObjekt(this.riadok, this.stlpec - 3)) {
                            this.vybuch[4][4 - dalsiBlok].zobraz();
                            this.jeTuVybuch[this.riadok][this.stlpec - dalsiBlok] = true;
                        }
                    }
                }
                
                if (this.jeTuVybuch(this.riadok + 3, this.stlpec)) {
                    if (!this.bojovePole.jeTuNeznicitelnyObjekt(this.riadok + dalsiBlok, this.stlpec)) {
                        if (!this.bojovePole.jeTuZnicitelnyObjekt(this.riadok + 3, this.stlpec)) {
                            this.vybuch[4 + dalsiBlok][4].zobraz();
                            this.jeTuVybuch[this.riadok + dalsiBlok][this.stlpec] = true;
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Skryje výbuch na bojovom poli.
     */
    public void skryVybuch() {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                if (this.vybuch[y][x] != null) {
                    this.vybuch[y][x].skry();
                }
            }
        }
    }
    
    /**
     * Ak je na bojovom poli vykreslený výbuch zničí prvé možné objekty v dosahu jeho plameňov.
     */
    public void znicStenu() {
        for (int y = 0; y < 18; y++) {
            for (int x = 0; x < 18; x++) {
                if (this.bojovePole.jeTuZnicitelnyObjekt(y, x) && this.jeTuVybuch(y, x)) {
                    this.bojovePole.skryStenu(y, x);
                }
            }
        }
    }
    
    /**
     * Vráti hodnotu či sa na danej súradnici nachádzajú plamene od výbuchu.
     * 
     * @param riadok, stĺpec
     * @return hodnota true alebo false.
     */
    public boolean jeTuVybuch(int riadok, int stlpec) {
        if (riadok >= 0 && stlpec >= 0 && riadok <= 17 && stlpec <= 17) {
            return this.jeTuVybuch[riadok][stlpec];    
        } else {
            return false;
        }
    }
}
