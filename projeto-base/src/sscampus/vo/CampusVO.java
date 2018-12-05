package sscampus.vo;

import base.vo.EntidadeVO;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import ssendereco.vo.EnderecoVO;

@Entity
@Table(name = "campus")
public class CampusVO extends EntidadeVO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;
    
    @Column(length = 50, nullable = false)
    private String nome;
    
    @Embedded
    private EnderecoVO enderecoVO;

    public CampusVO(){}
    
    public CampusVO(String nome, EnderecoVO enderecoVO){
        this.nome = nome;
        this.enderecoVO = enderecoVO;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public EnderecoVO getEndereco() {
        return enderecoVO;
    }

    public void setEndereco(EnderecoVO endereco) {
        this.enderecoVO = endereco;
    }
    
    @Override
    public String toString() {
        return this.nome;
    }

    @Override
    public boolean isValido() {
        boolean resp = true;

        if (this.nome == null || this.nome.length() == 0 || this.nome.length() > 50) {
            this.validacaoMsg += "\nNome  invalido";
            resp = false;
        }

        if (this.enderecoVO == null) {
            this.validacaoMsg += "Endereco nao pode ser nulo";
            resp = false;
        } else if (!this.enderecoVO.isValido()) {
            this.validacaoMsg += this.enderecoVO.getValidacaoMsg();
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
