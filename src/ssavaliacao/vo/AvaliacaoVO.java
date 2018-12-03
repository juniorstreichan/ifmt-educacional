package ssavaliacao.vo;

import base.vo.BaseVO;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AvaliacaoVO extends BaseVO{
    
    @Column(length = 100, nullable = false)
    private String descricao;
    
    @Column(nullable = false)
    private Date data;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    
    @Override
    public String toString() {
        return this.descricao + ", " + this.data ;
    }

    public boolean isValido() {
        boolean resp = true;

        if (this.descricao == null || this.descricao.length() == 0 || this.descricao.length() > 100) {
            this.validacaoMsg += "Descrição inválida";
            resp = false;
        }

        if (this.data == null) {
            this.validacaoMsg += "\nData inválida";
            resp = false;
        }
        return resp;
    }
}
