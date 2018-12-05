package ssareaconcentracao.vo;

import base.vo.BaseVO;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AreaConcentracaoVO extends BaseVO {
    
    @Column(length = 10, nullable = false)
    private String identificador;
    
    @Column(length = 100, nullable = false)
    private String descricao;
    
    public AreaConcentracaoVO(){}
    
    public AreaConcentracaoVO(String identificador, String descricao){
        this.identificador = identificador;
        this.descricao = descricao;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    @Override
    public String toString() {
        return this.identificador + ", " + this.descricao ;
    }

    @Override
    public boolean isValido() {
        boolean resp = true;

        if (this.identificador == null || this.identificador.length() == 0 || this.identificador.length() > 10) {
            this.validacaoMsg += "Identificador inválido";
            resp = false;
        }

        if (this.descricao == null || this.identificador.length() == 0 || this.identificador.length() > 100) {
            this.validacaoMsg += "\nNumero invalido para o endereco";
            resp = false;
        }
        return resp;
    }
}
