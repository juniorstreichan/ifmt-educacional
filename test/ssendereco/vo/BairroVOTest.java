package ssendereco.vo;

import org.junit.Test;
import static org.junit.Assert.*;

public class BairroVOTest {

    private final BairroVO bairroVO1;
    private final BairroVO bairroVO2;
    private final MunicipioVO cidadeVO;
    private final EstadoVO estadoVO;

    public BairroVOTest() {
        this.estadoVO = new EstadoVO("MT", "Mato Grosso");
        this.estadoVO.setId(Long.MIN_VALUE);
        this.cidadeVO = new MunicipioVO("Cuiaba", this.estadoVO);
        this.cidadeVO.setId(Long.MIN_VALUE);
        this.bairroVO1 = new BairroVO("Centro", cidadeVO);
        this.bairroVO1.setId(Long.MIN_VALUE);
        this.bairroVO2 = new BairroVO("", null);
        this.bairroVO2.setId(Long.MIN_VALUE);
    }

    @Test
    public void testGetCidade() {
        MunicipioVO expResult = this.cidadeVO;
        MunicipioVO result = this.bairroVO1.getMunicipio();
        assertEquals(expResult, result);
    }

    @Test
    public void testIsValido() {
        boolean result1 = this.bairroVO1.isValido();
        boolean result2 = this.bairroVO2.isValido();
        System.out.println(this.bairroVO1.getValidacaoMsg());
        System.out.println(this.bairroVO2.getValidacaoMsg());
        assertTrue(result1);
        assertFalse(result2);
    }

    @Test
    public void testToString() {
        String expResult = "Centro, Cuiaba";
        String result = this.bairroVO1.toString();
        assertEquals(expResult, result);
    }
}
