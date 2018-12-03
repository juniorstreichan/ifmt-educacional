package ssunidadeconteudo.vo;

import base.vo.BaseVO;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UnidadeConteudoVO extends BaseVO {
    
    @Column
    private String identificacao;
    
    @Column
    private String conteudo;
    
    @Column
    private int horasPrevistas;
    
    @Column
    private Date dataInicio;
    
    @Column
    private Date dataFim;

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public int getHorasPrevistas() {
        return horasPrevistas;
    }

    public void setHorasPrevistas(int horasPrevistas) {
        this.horasPrevistas = horasPrevistas;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    @Override
    public String toString() {
        return this.identificacao.trim() + ", " + this.conteudo + ", " + this.horasPrevistas + ", " + this.dataInicio + ", " + this.dataFim;
    }

    @Override
    public boolean isValido() {
        boolean resp = true;

        if (this.identificacao == null || this.identificacao.length() == 0 || this.identificacao.length() > 100) {
            this.validacaoMsg += "Identificação inválida";
            resp = false;
        }
        return resp;
    }    
}
