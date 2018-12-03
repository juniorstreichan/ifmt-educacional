package ssplanoensino.vo;

import base.vo.BaseVO;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import ssavaliacao.vo.AvaliacaoVO;
import ssdisciplina.vo.DisciplinaVO;
import ssprofessor.vo.ProfessorVO;
import ssunidadeconteudo.vo.UnidadeConteudoVO;

@Embeddable
public class PlanoEnsinoVO extends BaseVO {
    
    private ProfessorVO professor;
    
    @Column
    private String periodoLetivo;
    
    private DisciplinaVO disciplinas;
    
    @Column
    private String metodologia;
    
    private UnidadeConteudoVO unidadesConteudoVO;
    
    private AvaliacaoVO avaliacoes;
    
    @Column
    private String criterioAvaliacao;

    public ProfessorVO getProfessor() {
        return professor;
    }

    public void setProfessor(ProfessorVO professor) {
        this.professor = professor;
    }

    public String getPeriodoLetivo() {
        return periodoLetivo;
    }

    public void setPeriodoLetivo(String periodoLetivo) {
        this.periodoLetivo = periodoLetivo;
    }

    public DisciplinaVO getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(DisciplinaVO disciplinas) {
        this.disciplinas = disciplinas;
    }

    public String getMetodologia() {
        return metodologia;
    }

    public void setMetodologia(String metodologia) {
        this.metodologia = metodologia;
    }

    public UnidadeConteudoVO getUnidadesConteudoVO() {
        return unidadesConteudoVO;
    }

    public void setUnidadesConteudoVO(UnidadeConteudoVO unidadesConteudoVO) {
        this.unidadesConteudoVO = unidadesConteudoVO;
    }

    public AvaliacaoVO getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(AvaliacaoVO avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public String getCriterioAvaliacao() {
        return criterioAvaliacao;
    }

    public void setCriterioAvaliacao(String criterioAvaliacao) {
        this.criterioAvaliacao = criterioAvaliacao;
    }
    
    @Override
    public String toString() {
        return this.professor
                + ", " + this.periodoLetivo
                + ", " + this.disciplinas
                + ", " + this.metodologia
                + ", " + this.unidadesConteudoVO
                + ", " + this.avaliacoes
                + ", " + this.criterioAvaliacao;
    }

    public boolean isValido() {
        boolean resp = true;

        if (this.periodoLetivo == null || this.periodoLetivo.length() == 0 || this.periodoLetivo.length() > 100) {
            this.validacaoMsg += "Período letivo inválido";
            resp = false;
        }
        return resp;
    }   
}
