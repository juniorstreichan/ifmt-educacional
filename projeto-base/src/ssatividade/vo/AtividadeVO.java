package ssatividade.vo;

import base.vo.BaseVO;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import ssprofessor.vo.ProfessorVO;

@Embeddable
public class AtividadeVO extends BaseVO {

    @Column(length = 50, nullable = false)
    private String periodoLetivo;
    
    @ManyToOne
    private ProfessorVO professor;
    
    public AtividadeVO(){}
    
    public AtividadeVO(String periodoLetivo, ProfessorVO professor){
        this.periodoLetivo = periodoLetivo;
        this.professor = professor;
    }

    public String getPeriodoLetivo() {
        return periodoLetivo;
    }

    public void setPeriodoLetivo(String periodoLetivo) {
        this.periodoLetivo = periodoLetivo;
    }

    public ProfessorVO getProfessor() {
        return professor;
    }

    public void setProfessor(ProfessorVO professor) {
        this.professor = professor;
    }
    
    @Override
    public String toString() {
        return this.periodoLetivo + ", " + this.professor;
    }

    @Override
    public boolean isValido() {
       boolean resp = true;
       
       if (this.periodoLetivo == null || this.periodoLetivo.length() == 0 || this.periodoLetivo.length() > 50) {
            this.validacaoMsg += "Período Letivo inválido";
            resp = false;
        }
        return resp;
    }    
}
