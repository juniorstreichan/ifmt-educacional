package ssprofessor.negocio;

import base.negocio.Negocio;
import java.util.ArrayList;
import java.util.List;
import ssprofessor.vo.ProfessorVO;

public class Professor  extends Negocio<ProfessorVO> {
    public Professor(List<ProfessorVO> listaVO) {
        super(listaVO);
    }
    
    public static List<ProfessorVO> getListaProfessor() {
        List<ProfessorVO> lista = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            ProfessorVO professor = new ProfessorVO();            
            
            professor.setId((long) i);
            professor.setNome("Nome Professor");
            professor.setCPF("44455522244");
            professor.setRG("45457313");
            professor.setEmail("professor@email.com");
            
            lista.add(professor);
        }
        return lista;
    }
}
