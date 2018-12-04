package ssdisciplina.negocio;

import base.negocio.Negocio;
import java.util.ArrayList;
import java.util.List;
import ssdisciplina.vo.DisciplinaVO;

public class Disciplina extends Negocio<DisciplinaVO> {
    
    public Disciplina(List<DisciplinaVO> listaVO) {
        super(listaVO);
    }
    
    public static List<DisciplinaVO> getListaDisciplina() {
        List<DisciplinaVO> lista = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            DisciplinaVO d = new DisciplinaVO();
            d.setId((long) i);
            d.setNome("Disciplina " + i);
            d.setCodigo("DCP" + i);
            lista.add(d);
        }
        return lista;
    }
    
}
