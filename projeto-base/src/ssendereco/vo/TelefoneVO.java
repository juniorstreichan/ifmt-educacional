package ssendereco.vo;

import base.vo.BaseVO;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class TelefoneVO extends BaseVO {
    
    @Enumerated(EnumType.ORDINAL)
    private TipoUsoFoneEnum tipoUsoFone;
    
    @Enumerated(EnumType.ORDINAL)
    private TipoFoneEnum tipoFone;

    @Column(length = 3, nullable = false)
    private String ddi;
    
    @Column(length = 3, nullable = false)
    private String ddd;
    
    @Column(length = 9, nullable = false)
    private String numero;

    public TelefoneVO() {
    }

    public TelefoneVO(TipoUsoFoneEnum tipoUsoFone, TipoFoneEnum tipoFone, String ddi, String ddd, String numero) {
        this.tipoUsoFone = tipoUsoFone;
        this.tipoFone = tipoFone;
        this.ddi = ddi;
        this.ddd = ddd;
        this.numero = numero;
    }

    public TipoUsoFoneEnum getTipoUsoFone() {
        return tipoUsoFone;
    }

    public void setTipoUsoFone(TipoUsoFoneEnum tipoUsoFone) {
        this.tipoUsoFone = tipoUsoFone;
    }

    public TipoFoneEnum getTipoFone() {
        return tipoFone;
    }

    public void setTipoFone(TipoFoneEnum tipoFone) {
        this.tipoFone = tipoFone;
    }

    public String getDdi() {
        return ddi;
    }

    public void setDdi(String ddi) {
        this.ddi = ddi;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public boolean isValido() {
        boolean resp = true;

        if (this.tipoUsoFone == null) {
            this.validacaoMsg += "Tipo de uso do telefone invalido";
            resp = false;
        }

        if (this.tipoFone == null) {
            this.validacaoMsg += "Tipo do telefone invalido";
            resp = false;
        }
        
        if (this.ddi == null || this.ddi.length() == 0) {
            this.validacaoMsg += "\nDDI invalido";
            resp = false;
        }

        if (this.ddd == null || this.ddd.length() == 0) {
            this.validacaoMsg += "\nDDD invalido";
            resp = false;
        }
        
        if (this.numero == null || this.numero.length() == 0) {
            this.validacaoMsg += "\nNumero invalido";
            resp = false;
        }
        return resp;
    }

    @Override
    public String toString() {
        return this.ddi+" ("+this.ddd+") "+this.numero;
    }
}

