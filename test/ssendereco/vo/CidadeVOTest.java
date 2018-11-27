package ssendereco.vo;

import org.junit.Test;
import static org.junit.Assert.*;

public class CidadeVOTest {

    private MunicipioVO cidadeVO1;
    private EstadoVO estadoVO1;
    private MunicipioVO cidadeVO2;

    public CidadeVOTest() {
        this.estadoVO1 = new EstadoVO("MT", "Mato Grosso");
        this.estadoVO1.setId(Long.MIN_VALUE);
        this.cidadeVO1 = new MunicipioVO("Cuiaba", this.estadoVO1);
        this.cidadeVO1.setId(Long.MIN_VALUE);
        this.cidadeVO2 = new MunicipioVO("", null);
        this.cidadeVO2.setId(Long.MIN_VALUE);
    }

    @Test
    public void testGetEstado() {
        EstadoVO expResult = this.estadoVO1;
        EstadoVO result = this.cidadeVO1.getEstado();
        assertEquals(expResult, result);
    }

    @Test
    public void testIsValido() {
        boolean result1 = this.cidadeVO1.isValido();
        boolean result2 = this.cidadeVO2.isValido();
        System.out.println(this.cidadeVO1.getValidacaoMsg());
        System.out.println(this.cidadeVO2.getValidacaoMsg());
        assertTrue(result1);
        assertFalse(result2);
    }

    @Test
    public void testToString() {
        String expResult = "Cuiaba";
        String result = this.cidadeVO1.toString();
        assertEquals(expResult, result);
    }

}
