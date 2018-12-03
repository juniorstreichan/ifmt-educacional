package sspessoa.vo;

import ssendereco.vo.EnderecoVO;
import ssendereco.vo.TelefoneVO;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import base.vo.EntidadeVO;

@Entity
@Table(name = "pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class PessoaVO extends EntidadeVO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;
    
    @Column(length = 50, nullable = false)
    protected String nome;
    
    @Embedded
    protected EnderecoVO enderecoVO;
    
    @ElementCollection
    @CollectionTable(
            name="telefonepessoa",
            joinColumns=@JoinColumn(name="pessoa_fone"),
            uniqueConstraints= @UniqueConstraint(columnNames={"pessoa_fone", "numero"})
    )    
    private Set<TelefoneVO> listaFone;
    
    public PessoaVO() {
        this.listaFone = new HashSet();
    }

    public PessoaVO(String nome) {
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public EnderecoVO getEnderecoVO() {
        return enderecoVO;
    }

    public void setEnderecoVO(EnderecoVO EnderecoVO) {
        this.enderecoVO = EnderecoVO;
    }

    public Set<TelefoneVO> getListaFone() {
        return listaFone;
    }

    public void setListaFone(Set<TelefoneVO> listaFone) {
        this.listaFone = listaFone;
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
            this.validacaoMsg += "Endereco nao pode ser nulo para a Pessoa";
            resp = false;
        } else if (!this.enderecoVO.isValido()) {
            this.validacaoMsg += this.enderecoVO.getValidacaoMsg();
            resp = false;
        }

        for (TelefoneVO fone : this.listaFone) {
            if (!fone.isValido()) {
                this.validacaoMsg += fone.getValidacaoMsg();
                resp = false;
            }
        }

        return resp;
    }
}
