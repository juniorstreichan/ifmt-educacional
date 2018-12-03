package ssendereco.vo;

import org.junit.Test;
import static org.junit.Assert.*;

public class EstadoVOTest {

    private EstadoVO estado1VO;
    private EstadoVO estado2VO;

    public EstadoVOTest() {
        this.estado1VO = new EstadoVO("MT", "Mato Grosso");
        this.estado2VO = new EstadoVO("M", "");
    }

    @Test
    public void testIsValido() {
        boolean result1 = this.estado1VO.isValido();
        boolean result2 = this.estado2VO.isValido();
        System.out.println(this.estado1VO.getValidacaoMsg());
        System.out.println(this.estado2VO.getValidacaoMsg());
        assertTrue(result1);
        assertFalse(result2);
    }

    @Test
    public void testToString() {
        String expResult = "Mato Grosso (MT)";
        String result = this.estado1VO.toString();
        assertEquals(expResult, result);
    }

}
