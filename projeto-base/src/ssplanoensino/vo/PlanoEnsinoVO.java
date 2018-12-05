package ssplanoensino.vo;

import base.vo.BaseVO;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import ssavaliacao.vo.AvaliacaoVO;
import ssdisciplina.vo.DisciplinaVO;
import ssprofessor.vo.ProfessorVO;
import ssunidadeconteudo.vo.UnidadeConteudoVO;

@Embeddable
public class PlanoEnsinoVO extends BaseVO {
    
    @ManyToOne
    private ProfessorVO professor;
    
    @Column(length = 50, nullable = false)
    private String periodoLetivo;
    
    @OneToMany
    private DisciplinaVO disciplinas;
    
    @Column(length = 50, nullable = false)
    private String metodologia;
    
    @OneToMany
    private UnidadeConteudoVO unidadesConteudoVO;
    
    @OneToMany
    private AvaliacaoVO avaliacoes;
    
    @Column(length = 50, nullable = false)
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

        if (this.periodoLetivo == null || this.periodoLetivo.length() == 0 || this.periodoLetivo.length() > 50) {
            this.validacaoMsg += "Período letivo inválido";
            resp = false;
        }
        
        if (this.metodologia == null || this.metodologia.length() == 0 || this.metodologia.length() > 100) {
            this.validacaoMsg += "Metodologia inválida";
            resp = false;
        }
        
        if (this.criterioAvaliacao == null || this.criterioAvaliacao.length() == 0 || this.criterioAvaliacao.length() > 100) {
            this.validacaoMsg += "Metodologia inválida";
            resp = false;
        }
        return resp;
    }   
}
