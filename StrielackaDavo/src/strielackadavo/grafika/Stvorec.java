package strielackadavo.grafika;

import java.awt.Rectangle;

/**
 * Ĺ tvorec, s ktorĂ˝m moĹľno pohybovaĹĄ a nakreslĂ­ sa na plĂˇtno.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0  (15 July 2000)
 */

public class Stvorec {
    private int strana;
    private int lavyHornyX;
    private int lavyHornyY;
    private String farba;
    private boolean jeViditelny;

    /**
     * Vytvor novĂ˝ Ĺˇtvorec preddefinovanej farby na preddefinovanej pozĂ­cii.
     */
    public Stvorec() {
        this.strana = 30;
        this.lavyHornyX = 60;
        this.lavyHornyY = 50;
        this.farba = "red";
        this.jeViditelny = false;
    }

    /**
     * (Ĺ tvorec) Zobraz sa.
     */
    public void zobraz() {
        this.jeViditelny = true;
        this.nakresli();
    }
    
    /**
     * (Ĺ tvorec) Skry sa.
     */
    public void skry() {
        this.zmaz();
        this.jeViditelny = false;
    }
    
    /**
     * (Ĺ tvorec) PosuĹ� sa vpravo o pevnĂş dÄşĹľku.
     */
    public void posunVpravo() {
        this.posunVodorovne(20);
    }

    /**
     * (Ĺ tvorec) PosuĹ� sa vÄľavo o pevnĂş dÄşĹľku.
     */
    public void posunVlavo() {
        this.posunVodorovne(-20);
    }

    /**
     * (Ĺ tvorec) PosuĹ� sa hore o pevnĂş dÄşĹľku.
     */
    public void posunHore() {
        this.posunZvisle(-20);
    }

    /**
     * (Ĺ tvorec) PosuĹ� sa dole o pevnĂş dÄşĹľku.
     */
    public void posunDole() {
        this.posunZvisle(20);
    }

    /**
     * (Ĺ tvorec) PosuĹ� sa vodorovne o dÄşĹľku danĂş parametrom.
     */
    public void posunVodorovne(int vzdialenost) {
        this.zmaz();
        this.lavyHornyX += vzdialenost;
        this.nakresli();
    }

    /**
     * (Ĺ tvorec) PosuĹ� sa zvisle o dÄşĹľku danĂş parametrom.
     */
    public void posunZvisle(int vzdialenost) {
        this.zmaz();
        this.lavyHornyY += vzdialenost;
        this.nakresli();
    }

    /**
     * (Ĺ tvorec) PosuĹ� sa pomaly vodorovne o dÄşĹľku danĂş parametrom.
     */
    public void pomalyPosunVodorovne(int vzdialenost) {
        int delta;

        if (vzdialenost < 0) {
            delta = -1;
            vzdialenost = -vzdialenost;
        } else  {
            delta = 1;
        }

        for (int i = 0; i < vzdialenost; i++) {
            this.lavyHornyX += delta;
            this.nakresli();
        }
    }

    /**
     * (Ĺ tvorec) PosuĹ� sa pomaly vodorovne o dÄşĹľku danĂş parametrom.
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
     * (Ĺ tvorec) ZmeĹ� dÄşĹľku strany na hodnotu danĂş parametrom.
     * DÄşĹľka strany musĂ­ byĹĄ nezĂˇpornĂ© celĂ© ÄŤĂ­slo. 
     */
    public void zmenStranu(int dlzka) {
        this.zmaz();
        this.strana = dlzka;
        this.nakresli();
    }

    /**
     * (Ĺ tvorec) ZmeĹ� farbu na hodnotu danĂş parametrom.
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
     * Draw the square with current specifications on screen.
     */
    private void nakresli() {
        if (this.jeViditelny) {
            Platno canvas = Platno.dajPlatno();
            canvas.draw(this, this.farba,
                        new Rectangle(this.lavyHornyX, this.lavyHornyY, this.strana, this.strana));
            //canvas.wait(10);
        }
    }

    /*
     * Erase the square on screen.
     */
    private void zmaz() {
        if (this.jeViditelny) {
            Platno canvas = Platno.dajPlatno();
            canvas.erase(this);
        }
    }
}
