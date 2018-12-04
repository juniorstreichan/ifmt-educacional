package ssbibliografia.negocio;

import base.negocio.Negocio;
import java.util.ArrayList;
import java.util.List;
import ssbibliografia.vo.BibliografiaVO;
import ssbibliografia.vo.TipoBibliografia;

public class Bibliografia extends Negocio<BibliografiaVO> {
    
    public Bibliografia(List<BibliografiaVO> listaVO) {
        super(listaVO);
    }
    
    public static List<BibliografiaVO> getListaBibliografia() {
        List<BibliografiaVO> lista = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            BibliografiaVO bibliografia = new BibliografiaVO();
            bibliografia.setId((long) i);
            bibliografia.setTipo(TipoBibliografia.values()[i % TipoBibliografia.values().length]);
            bibliografia.setTitulo("Bibliografia " + i);
            lista.add(bibliografia);
        }
        return lista;
    }
    
}
