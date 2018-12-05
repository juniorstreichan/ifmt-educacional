package ssgradecurricular.vo;

import base.vo.EntidadeVO;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import sscurso.vo.CursoVO;

@Entity
@Table(name = "gradecurricular")
public class GradeCurricularVO extends EntidadeVO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @Column(length = 50, nullable = false)
    private String identificador;
    
    @Column(nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataInicio;
    
    @Column(nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataFim;
    
    @ManyToOne
    private CursoVO curso;

    public Long getID() {
        return id;
    }

    public void setID(Long id) {
        this.id = id;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public CursoVO getCurso() {
        return curso;
    }

    public void setCurso(CursoVO curso) {
        this.curso = curso;
    }
    
    @Override
    public String toString() {
        return this.identificador;
    }

    @Override
    public boolean isValido() {
        boolean resp = true;

        if (this.identificador == null || this.identificador.length() == 0 || this.identificador.length() > 50) {
            this.validacaoMsg += "\nIdentificador  invalido";
            resp = false;
        }
        
        if (this.dataInicio == null) {
            this.validacaoMsg += "\nData de início inválida";
            resp = false;
        }
        
        if (this.dataFim == null) {
            this.validacaoMsg += "\nData fim inválida";
            resp = false;
        }
        return resp;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
