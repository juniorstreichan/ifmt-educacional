package ssprofessor.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import ssdepartamento.vo.DepartamentoVO;
import sspessoa.vo.PessoaFisicaVO;

@Entity
@Table(name = "professor")
@Inheritance(strategy = InheritanceType.JOINED)
public class ProfessorVO extends PessoaFisicaVO {
    
    @Column(length = 50, nullable = false)
    private String matricula;
    
    @ManyToOne
    @JoinColumn(name = "id_departamento")
    private DepartamentoVO lotacao;
    
    @Column(length = 50, nullable = false)
    private String email;

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public DepartamentoVO getLotacao() {
        return lotacao;
    }

    public void setLotacao(DepartamentoVO lotacao) {
        this.lotacao = lotacao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }  
}
