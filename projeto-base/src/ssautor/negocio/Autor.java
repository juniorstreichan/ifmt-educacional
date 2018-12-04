package ssautor.negocio;

import base.negocio.Negocio;
import java.util.ArrayList;
import java.util.List;
import ssautor.vo.AutorVO;

public class Autor extends Negocio<AutorVO> {

    public Autor(List<AutorVO> listaVO) {
        super(listaVO);
    }

    public static List<AutorVO> gerarListaAutor() {
        List<AutorVO> listaAutor = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            AutorVO autor = new AutorVO();
            autor.setId((i + 1));
            autor.setNome("Autor " + (i + 1));
            listaAutor.add(autor);
        }
        return listaAutor;
    }

}
