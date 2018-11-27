package ssendereco.vo;

import base.vo.EntidadeVO;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "estado")
public class EstadoVO extends EntidadeVO{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @Column(length = 2, nullable = false)
    private String sigla;
    
    @Column(length = 40, nullable = false)
    private String nome;
    
    public EstadoVO() {
        this.sigla = "";
        this.nome = "";
    }

    public EstadoVO(String sigla, String nome) {
        this.sigla = sigla;
        this.nome = nome;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean isValido() {
        boolean resp = true;

        if (this.sigla == null || this.sigla.length() != 2) {
            this.validacaoMsg += "UF invalido para o estado";
            resp = false;
        }

        if (this.nome == null || this.nome.length() == 0 || this.nome.length() > 40) {
            this.validacaoMsg += "\nNome  invalido para o estado";
            resp = false;
        }

        return resp;
    }

    @Override
    public String toString() {
        return this.nome.trim() + " (" + this.sigla + ")";
    }
}
