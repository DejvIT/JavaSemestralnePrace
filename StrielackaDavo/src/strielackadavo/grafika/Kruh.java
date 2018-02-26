package strielackadavo.grafika;

import java.awt.geom.Ellipse2D;

/**
 * Kruh, s ktorĂ˝m moĹľno pohybovaĹĄ a nakreslĂ­ sa na plĂˇtno.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0  (15 July 2000)
 */

public class Kruh {
    private int priemer;
    private int lavyHornyX;
    private int lavyHornyY;
    private String farba;
    private boolean jeViditelny;
    
    /**
     * Vytvor novĂ˝ kruh preddefinovanej farby na preddefinovanej pozĂ­cii. 
     */
    public Kruh() {
        this.priemer = 30;
        this.lavyHornyX = 20;
        this.lavyHornyY = 60;
        this.farba = "blue";
        this.jeViditelny = false;
    }

    /**
     * (Kruh) Zobraz sa.
     */
    public void zobraz() {
        this.jeViditelny = true;
        this.nakresli();
    }
    
    /**
     * (Kruh) Skry sa.
     */
    public void skry() {
        this.zmaz();
        this.jeViditelny = false;
    }
    
    /**
     * (Kruh) PosuĹ� sa vpravo o pevnĂş dÄşĹľku. 
     */
    public void posunVpravo() {
        this.posunVodorovne(20);
    }

    /**
     * (Kruh) PosuĹ� sa vÄľavo o pevnĂş dÄşĹľku. 
     */
    public void posunVlavo() {
        this.posunVodorovne(-20);
    }

    /**
     * (Kruh) PosuĹ� sa hore o pevnĂş dÄşĹľku. 
     */
    public void posunHore() {
        this.posunZvisle(-20);
    }

    /**
     * (Kruh) PosuĹ� sa dole o pevnĂş dÄşĹľku. 
     */
    public void posunDole() {
        this.posunZvisle(20);
    }

    /**
     * (Kruh) PosuĹ� sa vodorovne o dÄşĹľku danĂş parametrom. 
     */
    public void posunVodorovne(int vzdialenost) {
        this.zmaz();
        this.lavyHornyX += vzdialenost;
        this.nakresli();
    }

    /**
     * (Kruh) PosuĹ� sa zvisle o dÄşĹľku danĂş parametrom. 
     */
    public void posunZvisle(int vzdialenost) {
        this.zmaz();
        this.lavyHornyY += vzdialenost;
        this.nakresli();
    }

    /**
     * (Kruh) PosuĹ� sa pomaly vodorovne o dÄşĹľku danĂş parametrom. 
     */
    public void pomalyPosunVodorovne(int vzdialenost) {
        int delta;

        if (vzdialenost < 0) {
            delta = -1;
            vzdialenost = -vzdialenost;
        } else {
            delta = 1;
        }

        for (int i = 0; i < vzdialenost; i++) {
            this.lavyHornyX += delta;
            this.nakresli();
        }
    }

    /**
     * (Kruh) PosuĹ� sa pomaly zvisle o dÄşĹľku danĂş parametrom. 
     */
    public void pomalyPosunZvisle(int vzdialenost) {
        int delta;

        if (vzdialenost < 0) {
            delta = -1;
            vzdialenost = -vzdialenost;
        } else {
            delta = 1;
        }

        for (int i = 0; i < vzdialenost; i++) {
            this.lavyHornyY += delta;
            this.nakresli();
        }
    }

    /**
     * (Kruh) ZmeĹ� priemer na hodnotu danĂş parametrom.
     * Priemer musĂ­ byĹĄ nezĂˇpornĂ© celĂ© ÄŤĂ­slo. 
     */
    public void zmenPriemer(int priemer) {
        this.zmaz();
        this.priemer = priemer;
        this.nakresli();
    }

    /**
     * (Kruh) ZmeĹ� farbu na hodnotu danĂş parametrom.
     * Nazov farby musĂ­ byĹĄ po anglicky.
     * MoĹľnĂ© farby sĂş tieto:
     * ÄŤervenĂˇ - "red"
     * ĹľltĂˇ    - "yellow"
     * modrĂˇ   - "blue"
     * zelenĂˇ  - "green"
     * fialovĂˇ - "magenta"
     * ÄŤierna  - "black"
     */
    public void zmenFarbu(String farba) {
        this.farba = farba;
        this.nakresli();
    }

    /*
     * Draw the circle with current specifications on screen.
     */
    private void nakresli() {
        if (this.jeViditelny) {
            Platno canvas = Platno.dajPlatno();
            canvas.draw(this, this.farba, new Ellipse2D.Double(this.lavyHornyX, this.lavyHornyY, 
                                                          this.priemer, this.priemer));
            //canvas.wait(10);
        }
    }

    /*
     * Erase the circle on screen.
     */
    private void zmaz() {
        if (this.jeViditelny) {
            Platno canvas = Platno.dajPlatno();
            canvas.erase(this);
        }
    }

}
