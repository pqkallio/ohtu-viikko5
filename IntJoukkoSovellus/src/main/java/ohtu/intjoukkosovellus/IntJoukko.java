
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] joukko;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        this(KAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        this(kapasiteetti, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 1 || kasvatuskoko < 1) {
            throw new IllegalArgumentException("Kapasiteetin ja kasvatuskoon tulee olla suurempia kuin 0");
        }
        alustaJoukko(kapasiteetti);
        this.alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    public boolean lisaa(int luku) {
        if (kuuluu(luku)) {
            return false;
        }
        joukko[alkioidenLkm] = luku;
        alkioidenLkm++;
        if (alkioidenLkm == joukko.length) {
            kasvataJoukonKokoa();
        }
        return true;
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == joukko[i]) {
                return true;
            }
        }
        
        return false;
    }

    public void poista(int luku) {
        for (int i = 0; i < joukko.length; i++) {
            if (joukko[i] == luku) {
                poistaLukuIndeksista(i);
                alkioidenLkm--;
            }
        }
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("{");
        if (alkioidenLkm > 0) {
            builder.append(liitaTaulukonJononMerkkijonoksi(", "));
        }
        builder.append("}");
        return builder.toString();
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = joukko[i];
        }
        return taulu;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko yhdiste = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            yhdiste.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            yhdiste.lisaa(bTaulu[i]);
        }
        return yhdiste;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        return erotusJaLeikkaus(a, b, true);
    }
    
    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        return erotusJaLeikkaus(a, b, false);
    }
    
    /**
     * Returns the intersection of the two sets if the parameter "intersection" 
     * is true and the relative complement of the set B in A otherwise.
     * 
     * @param a the first set
     * @param b the second set
     * @param intersection true if intersection, false if relative complement
     * @return the new set
     */
    private static IntJoukko erotusJaLeikkaus(IntJoukko a, IntJoukko b, 
            boolean intersection) {
        IntJoukko tuloJoukko = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            if (b.kuuluu(aTaulu[i]) == intersection) {
                tuloJoukko.lisaa(aTaulu[i]);
            }
        }
 
        return tuloJoukko;
    }

    private void alustaJoukko(int kapasiteetti) {
        joukko = new int[kapasiteetti];
        for (int i = 0; i < joukko.length; i++) {
            joukko[i] = 0;
        }
    }

    private void kasvataJoukonKokoa() {
        int[] newTaulukko = new int[joukko.length + kasvatuskoko];
        kopioiTaulukko(joukko, newTaulukko);
        joukko = newTaulukko;
    }

    private void poistaLukuIndeksista(int index) {
        for (int i = index; i < joukko.length - 1; i++) {
            joukko[i] = joukko[i + 1];
        }
    }

    private String liitaTaulukonJononMerkkijonoksi(String delimiter) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < alkioidenLkm - 1; i++) {
            builder.append(joukko[i]);
            builder.append(delimiter);
        }
        builder.append(joukko[alkioidenLkm - 1]);
        return builder.toString();
    }
}