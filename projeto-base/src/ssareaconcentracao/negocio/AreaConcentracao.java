package ssareaconcentracao.negocio;

import base.negocio.Negocio;
import java.util.ArrayList;
import java.util.List;
import ssareaconcentracao.vo.AreaConcentracaoVO;

/**
 *
 * @author LUCAS
 */
public class AreaConcentracao extends Negocio<AreaConcentracaoVO> {
    
    public AreaConcentracao(List<AreaConcentracaoVO> listaVO) {
        super(listaVO);
    }
    
    public static List<AreaConcentracaoVO> getListaAreaConcentracao() {
        List<AreaConcentracaoVO> lista = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            AreaConcentracaoVO area = new AreaConcentracaoVO();
            
            area.setId((long) i);
            area.setIdentificador("COD" + i);
            area.setDescricao("Area " + area.getIdentificador());
            
            lista.add(area);
        }
        return lista;
    }
    
}
