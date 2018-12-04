package servico.disciplina;

import base.negocio.Negocio;
import servico.Servico;
import ssautor.vo.AutorVO;
import ssautor.negocio.Autor;

public class ServicoAutor extends Servico<AutorVO> {
    
    public ServicoAutor(Negocio<AutorVO> negocio, Class<AutorVO> classe) {
        super(new Autor(Autor.gerarListaAutor()), AutorVO.class);
    }
    
}
