
package ohtu.intjoukkosovellus;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class JoukkoOperaatiotTest {
    IntJoukko a;
    IntJoukko b;
    
    @Before
    public void setUp() {
        a = teeJoukko(1, 2, 3);
        b = teeJoukko(3, 4);
    }
    
    @Test
    public void yhdisteMuodostuuOikein() {
        IntJoukko tulos = IntJoukko.yhdiste(a, b);
        int[] vastauksenLuvut = tulos.toIntArray();
        Arrays.sort(vastauksenLuvut);
        
        int[] odotettu = {1,2,3,4};
        
        assertArrayEquals(odotettu, vastauksenLuvut);        
    }
    
    @Test
    public void leikkausMuodostuuOikein() {
        IntJoukko tulos = IntJoukko.leikkaus(a, b);
        int[] vastauksenLuvut = tulos.toIntArray();
        Arrays.sort(vastauksenLuvut);
        System.out.println(Arrays.toString(vastauksenLuvut));

        int[] odotettu = {3};
        
        assertArrayEquals(odotettu, vastauksenLuvut);        
    }
    
    @Test
    public void erotusMuodostuuOikein() {
        IntJoukko tulos = IntJoukko.erotus(a, b);
        int[] vastauksenLuvut = tulos.toIntArray();
        System.out.println(Arrays.toString(vastauksenLuvut));
        Arrays.sort(vastauksenLuvut);
        
        int[] odotettu = {1,2};
        
        assertArrayEquals(odotettu, vastauksenLuvut);        
    }

    private IntJoukko teeJoukko(int... luvut) {
        IntJoukko joukko = new IntJoukko();
        
        for (int luku : luvut) {
            joukko.lisaa(luku);
        }
        
        return joukko;
    }
}
