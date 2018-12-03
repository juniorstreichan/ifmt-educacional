package ssensino.vo;

import base.vo.BaseVO;
import javax.persistence.Embeddable;
import ssdisciplina.vo.DisciplinaVO;

@Embeddable
public class EnsinoVO extends BaseVO {

    private DisciplinaVO disciplina;

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
