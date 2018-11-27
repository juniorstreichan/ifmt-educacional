package ssendereco.vo;
import org.junit.Test;
import static org.junit.Assert.*;
public class TelefoneVOTest {
    
    private TelefoneVO telefoneVO;
    
    public TelefoneVOTest() {
        this.telefoneVO = new TelefoneVO(TipoUsoFoneEnum.TRABALHO, TipoFoneEnum.FIXO, "+55", "65", "3052-3977");
    }

    @Test
    public void testIsValido() {
        boolean expResult = true;
        boolean result = this.telefoneVO.isValido();
        if(!result){
            System.out.println(this.telefoneVO.getValidacaoMsg());
        }
        assertEquals(expResult, result);
    }

    @Test
    public void testToString() {
        String expResult = "+55 (65) 3052-3977";
        String result = this.telefoneVO.toString();
        assertEquals(expResult, result);
    }
}
