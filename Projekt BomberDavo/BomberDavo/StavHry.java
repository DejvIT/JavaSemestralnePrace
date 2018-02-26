
/**
 * Vymenovanie možných stavov hry od začiatku po koniec.
 * Hra môže byť nerozhodnutá ak ešte prebieha a obidvaja hráči sú živý.
 * Hra môže byť remizovaná, ak sa obidvaja hráči zabijú v rovnakom čase.
 * Hru môže vyhrať prvý alebo druhý hráč.
 * 
 * @author (your name here)
 * @version (12.12.2015)
 */
public enum StavHry {
    NEROZHODNUTA,
    REMIZA,
    VYHRAPRVEHO,
    VYHRADRUHEHO
}
