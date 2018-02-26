
/**
 * Táto trieda vytvorí jednotlivé časti Bombermana, pomocou TvarovV3,
 * obsahuje jednotlivé základné metódy na ktorých stoji celá hra.
 * 
 * @author (Dávid Pavličko) 
 * @version (14.11.2015)
 */
public class BombermanCasti {
    private BojovePole bojovePole;
    
    private Obdlznik telo;
    private Kruh hlava;
    private Obdlznik[] ruky;
    private Obdlznik[] nohy;
    private int vzdialenostPosunu;
    
    private int suradnicaPanacikaX;
    private int suradnicaPanacikaY;
    private int riadok;
    private int stlpec;
    
    private boolean polohaTela;
    
    /**
     * Konštruktor vytvára jednotlivé časti Bombermana.
     * 
     * @param Priradenie do bojového poľa, súradnice: riadok, stĺpec
     */
    public BombermanCasti(BojovePole bojovePole, int riadok, int stlpec) {
        this.bojovePole = bojovePole;
        this.polohaTela = true;
        
        this.vzdialenostPosunu = 50; //udáva rýchlosť hry ! ==> ako rýchlo sa postavičky budú pohybovať
        this.suradnicaPanacikaX = stlpec;
        this.suradnicaPanacikaY = riadok;
        
        
        /** Podmienky pre zabezpečenie nesprávneho zadávania pozicie pri vytváraní */
        if (riadok > this.bojovePole.getPocetRiadkov()) { //riadok
            riadok = 1;
        } else if (riadok < 0) {
            riadok = 1;
        } else {
            this.riadok = riadok;
        }
        
        if (stlpec > this.bojovePole.getPocetStlpcov()) { //stlpec
            stlpec = 1;
        } else if (stlpec < 0) {
            stlpec = 1;
        } else {
            this.stlpec = stlpec;
        }
        
        
                                /** Vytvorenie jednotlivých časti postavičky */
        /** Telo */
        this.telo = new Obdlznik();
        this.telo.zmenStrany(20, 20);
        this.telo.zmenFarbu("black");
        this.telo.posunVodorovne(5 + this.stlpec * 50);
        this.telo.posunZvisle(8 + this.riadok * 50);
        this.telo.zobraz();
        
        /** Nohy */
        this.nohy = new Obdlznik[2];
        for (int i = 0; i < 2; i++) {
            this.nohy[i] = new Obdlznik();
            this.nohy[i].zmenStrany(11, 22);
            this.nohy[i].zmenFarbu("black");
            this.nohy[i].posunVodorovne((3 + i * 12) + this.stlpec * 50);
            this.nohy[i].posunZvisle(28 + this.riadok * 50);
            this.nohy[i].zobraz();
        }
        
        /** Ruky */
        this.ruky = new Obdlznik[2];
        for (int i = 0; i < 2; i++) {
            this.ruky[i] = new Obdlznik();
            this.ruky[i].zmenStrany(9, 25);
            this.ruky[i].zmenFarbu("magenta");
            this.ruky[i].posunVodorovne((-1 + i * 22) + this.stlpec * 50);
            this.ruky[i].posunZvisle(9 + this.riadok * 50);
            this.ruky[i].zobraz();
        }
        
        /** Hlava */
        this.hlava = new Kruh();
        this.hlava.zmenPriemer(15);
        this.hlava.zmenFarbu("blue");
        this.hlava.posunVodorovne(47 + this.stlpec * 50);
        this.hlava.posunZvisle(-11 + this.riadok * 50);
        this.hlava.zobraz();
        
    }
    
    /**
     * Bomberman sa prezlečie do aktuálneho trendu:
     * 
     * @param Možné farby postavičky sú tieto:
     * červená - "red"
     * žltá    - "yellow"
     * modrá   - "blue"
     * fialová - "magenta"
     * čierna  - "black"
     */
    public void setFarbaPanacika(String farba) {
        switch (farba) {
            case "red":
                this.ruky[0].zmenFarbu("red");
                this.ruky[1].zmenFarbu("red");
                this.hlava.zmenFarbu("red");
                break;
            case "yellow":
                this.ruky[0].zmenFarbu("yellow");
                this.ruky[1].zmenFarbu("yellow");
                this.hlava.zmenFarbu("yellow");
                break;
            case "blue":
                this.ruky[0].zmenFarbu("blue");
                this.ruky[1].zmenFarbu("blue");
                this.hlava.zmenFarbu("blue");
                break;
            case "magenta":
                this.ruky[0].zmenFarbu("magenta");
                this.ruky[1].zmenFarbu("magenta");
                this.hlava.zmenFarbu("magenta");
                break;
            case "black":
                this.telo.zmenFarbu("white");
                this.ruky[0].zmenFarbu("black");
                this.ruky[1].zmenFarbu("black");
                this.hlava.zmenFarbu("black");
                break;
                
            default:
                this.telo.zmenFarbu("white");
                this.ruky[0].zmenFarbu("black");
                this.ruky[1].zmenFarbu("black");
                this.hlava.zmenFarbu("black");
                break;
        }
    }
    
    /**
     * Posun všetkých častí tela Bombermana doprava.
     */
    public void posunVpravo() {
        if (!this.bojovePole.jeTuNeznicitelnyObjekt(this.riadok, this.stlpec + 1)) {
            if (!this.bojovePole.jeTuZnicitelnyObjekt(this.riadok, this.stlpec + 1)) {
                this.telo.posunVodorovne(this.vzdialenostPosunu);
                this.nohy[0].posunVodorovne(this.vzdialenostPosunu);
                this.nohy[1].posunVodorovne(this.vzdialenostPosunu);
                this.ruky[0].posunVodorovne(this.vzdialenostPosunu);
                this.ruky[1].posunVodorovne(this.vzdialenostPosunu);
                this.hlava.posunVodorovne(this.vzdialenostPosunu);
        
                this.stlpec = (this.stlpec * 50 + this.vzdialenostPosunu) / 50;
            } 
        }
    } 
    
    /**
     * Posun všetkých častí tela Bombermana doľava.
     */
    public void posunVlavo() {
        if (!this.bojovePole.jeTuNeznicitelnyObjekt(this.riadok, this.stlpec - 1)) {
            if (!this.bojovePole.jeTuZnicitelnyObjekt(this.riadok, this.stlpec - 1)) {
                this.telo.posunVodorovne(-this.vzdialenostPosunu);
                this.nohy[0].posunVodorovne(-this.vzdialenostPosunu);
                this.nohy[1].posunVodorovne(-this.vzdialenostPosunu);
                this.ruky[0].posunVodorovne(-this.vzdialenostPosunu);
                this.ruky[1].posunVodorovne(-this.vzdialenostPosunu);
                this.hlava.posunVodorovne(-this.vzdialenostPosunu);
        
                this.stlpec = (this.stlpec * 50 - this.vzdialenostPosunu) / 50;
            }
        }
    }
    
    /**
     * Posun všetkých častí tela Bombermana smerom hore.
     */
    public void posunHore() {
        if (!this.bojovePole.jeTuNeznicitelnyObjekt(this.riadok - 1, this.stlpec)) {
            if (!this.bojovePole.jeTuZnicitelnyObjekt(this.riadok - 1, this.stlpec)) {
                this.telo.posunZvisle(-this.vzdialenostPosunu);
                this.nohy[0].posunZvisle(-this.vzdialenostPosunu);
                this.nohy[1].posunZvisle(-this.vzdialenostPosunu);
                this.ruky[0].posunZvisle(-this.vzdialenostPosunu);
                this.ruky[1].posunZvisle(-this.vzdialenostPosunu);
                this.hlava.posunZvisle(-this.vzdialenostPosunu);
                
                this.riadok = (this.riadok * 50 - this.vzdialenostPosunu) / 50;
            }
        }
    }
    
    /**
     * Posun všetkých častí tela Bombermana smerom dole.
     */
    public void posunDole() {
        if (!this.bojovePole.jeTuNeznicitelnyObjekt(this.riadok + 1, this.stlpec)) {
            if (!this.bojovePole.jeTuZnicitelnyObjekt(this.riadok + 1, this.stlpec)) {
                this.telo.posunZvisle(this.vzdialenostPosunu);
                this.nohy[0].posunZvisle(this.vzdialenostPosunu);
                this.nohy[1].posunZvisle(this.vzdialenostPosunu);
                this.ruky[0].posunZvisle(this.vzdialenostPosunu);
                this.ruky[1].posunZvisle(this.vzdialenostPosunu);
                this.hlava.posunZvisle(this.vzdialenostPosunu);
        
                this.riadok = (this.riadok * 50 + this.vzdialenostPosunu) / 50;
            }
        }
    }
    
    /**
     * Vráti aktuálny riadok na ktorom sa Bomberman nachádza.
     * 
     * @return hodnota riadku v celom čísle.
     */
    public int getAktualnyRiadok() {
        return this.riadok;
    }
    
    /**
     * Vráti aktuálny stĺpec na ktorom sa Bomberman nachádza.
     * 
     * @return hodnota stĺpca v celom čísle.
     */
    public int getAktualnyStlpec() {
        return this.stlpec;
    }
    
    /**
     * Vráti aktuálnu polohu tela.
     * 
     * @return
     * Polohy: True  = Bomberman stoji rovno
     *         False = Bomberman stoji z profilu
     */
    public boolean getPolohaTela() {
        return this.polohaTela; // True = rovno, False = profil
    }
    
    /**
     * Vráti polohu tela do rovnej polohy.
     */
    public void getPolohuTelaRovno() {
        if (!this.polohaTela) {
            this.nohy[0].posunVodorovne(-7);
            this.nohy[1].posunVodorovne(7);
            this.ruky[0].posunVodorovne(-10);
            this.ruky[1].posunVodorovne(10);
        
            this.polohaTela = true;
        }
    }
    
    /**
     * Otočí telo Bombermana do smeru cesty.
     */
    public void getPolohuTelaDoStrany() {
        if (this.polohaTela) {
            this.nohy[0].posunVodorovne(7);
            this.nohy[1].posunVodorovne(-7);
            this.ruky[0].posunVodorovne(10);
            this.ruky[1].posunVodorovne(-10);
        
            this.polohaTela = false;
        }
    }
    
    /**
     * Zobrazí časti tela panačika na bojovom poli.
     */
    public void zobrazPanacika() {
        this.telo.zobraz();
        this.nohy[0].zobraz();
        this.nohy[1].zobraz();
        this.ruky[0].zobraz();
        this.ruky[1].zobraz();
        this.hlava.zobraz();
    }
    
    /**
     * Skryje časti tela panačika na bojovom poli.
     */
    public void skryPanacika() {
        this.telo.skry();
        this.nohy[0].skry();
        this.nohy[1].skry();
        this.ruky[0].skry();
        this.ruky[1].skry();
        this.hlava.skry();
    }
}
