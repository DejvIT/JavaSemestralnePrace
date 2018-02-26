/**
 * Predok všetkých zbraní, ktoré hráč používa, alebo má možnosť pouźiť.
 */
package strielackadavo.hrac.zbrane;

/**
 *
 * @author Pavličko.D
 */
public abstract class Zbran {
    private final String nazov;
    private int zasobnik;
    private int riadok;
    private int stlpec;
    private boolean drzana;

    /**
     * Inicializácia základných parametrov zbrane. Nepracuje s grafikou.
     * @param nazov
     * @param zasobnik
     * @param riadok
     * @param stlpec
     * @param drzana 
     */
    public Zbran(String nazov, int zasobnik, int riadok, int stlpec, boolean drzana) {
        this.nazov = nazov;
        this.zasobnik = zasobnik;
        this.riadok = riadok;
        this.stlpec = stlpec;
        this.drzana = drzana;
    }

    public String getNazov() {
        return this.nazov;
    }

    public int getZasobnik() {
        return this.zasobnik;
    }
    
    /**
     * Metóda pridá muníciu do zbrane.
     * @param pocet 
     */
    void pridajDoZasobnikaMuniciu(int pocet) {
        this.zasobnik = this.getZasobnik() + pocet;
    }
    
    /**
     * Metóda uberá muníciu zo zbrane.
     */
    void uberMuniciuZoZasobnika() {
        this.zasobnik -= 1;
    }

    public int getRiadok() {
        return this.riadok;
    }
    
    public void setRiadok(int riadok) {
        this.riadok = riadok;
    }

    public int getStlpec() {
        return this.stlpec;
    }
    
    public void setStlpec(int stlpec) {
        this.stlpec = stlpec;
    }

    /**
     * Metóda informuje o držaní zbrane.
     * @return 
     */
    public boolean isDrzana() {
        return this.drzana;
    }
    
    /**
     * Metóda nastaví zbraň na "držaná".
     */
    public void setDrzana() {
        this.drzana = true;
    }
    
    
}
