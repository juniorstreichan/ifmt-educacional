package sscurso.vo;

import base.vo.EntidadeVO;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import ssdepartamento.vo.DepartamentoVO;

@Entity
@Table(name = "curso")
public class CursoVO extends EntidadeVO {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;
    
    @Column(length = 50, nullable = false)
    private String codigo;
    
    @Column(length = 50, nullable = false)
    private String nome;
    
    @ManyToOne
    private DepartamentoVO departamento;

    public CursoVO(){}
    
    public CursoVO(String codigo, String nome){
        this.codigo = codigo;
        this.nome = nome;
    }
    
    public CursoVO(String codigo, String nome, DepartamentoVO departamento){
        this.codigo = codigo;
        this.nome = nome;
        this.departamento = departamento;
    }
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public DepartamentoVO getDepartamento() {
        return departamento;
    }

    public void setDepartamento(DepartamentoVO departamento) {
        this.departamento = departamento;
    }
    
    @Override
    public String toString() {
        return this.nome;
    }

    @Override
    public boolean isValido() {
        boolean resp = true;

        if (this.codigo == null || this.codigo.length() == 0 || this.codigo.length() > 50) {
            this.validacaoMsg += "\nCodigo inválido";
            resp = false;
        }
        
        if (this.nome == null || this.nome.length() == 0 || this.nome.length() > 50) {
            this.validacaoMsg += "\nNome  inválido";
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
