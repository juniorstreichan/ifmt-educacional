package ssendereco.vo;

import javax.persistence.CascadeType;
import base.vo.EntidadeVO;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bairro")
public class BairroVO extends EntidadeVO{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 40, nullable = false)
    private String nome;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH})
    private MunicipioVO municipio;

    public BairroVO(){}

    public BairroVO(String nome, MunicipioVO municipio) {
        this.municipio = municipio;
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

    public MunicipioVO getMunicipio() {
        return municipio;
    }

    public void setMunicipio(MunicipioVO municipioVO) {
        this.municipio = municipioVO;
    }

    @Override
    public boolean isValido() {
        boolean resp = true;

        if (this.nome == null || this.nome.length() == 0 || this.nome.length() > 40) {
            this.validacaoMsg += "Nome invalido para o Bairro";
            resp = false;
        }

        if (this.municipio == null) {
            this.validacaoMsg += "\nCidade nao pode ser nulo para a Bairro";
            resp = false;
        } else {
            if (!this.municipio.isValido()) {
                this.validacaoMsg += this.municipio.getValidacaoMsg();
                resp = false;
            }
        }
        return resp;
    }

    @Override
    public String toString() {
        return this.nome.trim()+", "+this.municipio;
    }
}
