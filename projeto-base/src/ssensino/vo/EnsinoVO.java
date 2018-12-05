package ssensino.vo;

import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import ssatividade.vo.AtividadeVO;
import ssdisciplina.vo.DisciplinaVO;

@Embeddable
public class EnsinoVO extends AtividadeVO {

    @OneToMany
    private DisciplinaVO disciplina;

    public EnsinoVO(){}
    
    public EnsinoVO(DisciplinaVO disciplina){
        this.disciplina = disciplina;
    }
    
    @Override
    public String toString() {
        return this.disciplina.toString();
    }

    @Override
    public boolean isValido() {
        boolean resp = true;

        if (this.disciplina == null ) {
            this.validacaoMsg += "Disciplina inválida";
            resp = false;
        }
        return resp;
    }   
}
