package ssendereco.vo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import base.vo.BaseVO;

@Embeddable
public class EnderecoVO extends BaseVO {

    @Column(length = 100, nullable = false)
    private String rua;

    @Column(nullable = false)
    private int numero;

    @Column(length = 10, nullable = false)
    private String cep;
    
    @Column(length = 50, nullable = false)
    private String complemento;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH})
    private BairroVO bairro;

    public EnderecoVO() {
        this.rua = "";
        this.cep = "";
    }

    public EnderecoVO(String rua, int numero, String cep, BairroVO bairro) {
        this.rua = rua;
        this.numero = numero;
        this.cep = cep;
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCEP() {
        return cep;
    }

    public void setCEP(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplement(String complemento) {
        this.complemento = complemento;
    }

    public BairroVO getBairro() {
        return bairro;
    }

    public void setBairro(BairroVO bairro) {
        this.bairro = bairro;
    }

    public boolean isValido() {
        boolean resp = true;

        if (this.rua == null || this.rua.length() == 0 || this.rua.length() > 100) {
            this.validacaoMsg += "Logradouro invalido para o endereco";
            resp = false;
        }

        if (this.numero <= 0) {
            this.validacaoMsg += "\nNumero invalido para o endereco";
            resp = false;
        }

        if (this.cep == null || this.cep.length() != 10) {
            this.validacaoMsg += "CEP invalido para o endereco";
            resp = false;
        }

        if (this.bairro == null) {
            this.validacaoMsg += "Bairro nao pode ser nulo para o endereco";
            resp = false;
        } else {
            if (!this.bairro.isValido()) {
                this.validacaoMsg += this.bairro.getValidacaoMsg();
                resp = false;
            }
        }
        return resp;
    }

    @Override
    public String toString() {
        return this.rua.trim() + ", " + this.numero + ", " + this.bairro + ", " + this.cep;
    }
}
