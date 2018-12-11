package ssareaconcentracao.vo;

import base.vo.EntidadeVO;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AreaConcentracao")
public class AreaConcentracaoVO extends EntidadeVO {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @Column(length = 10, nullable = false)
    private String identificador;
    
    @Column(length = 100, nullable = false)
    private String descricao;
    
    public AreaConcentracaoVO(){}
    
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
    
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
        validacaoMsg = "";
        if (this.identificador == null || this.identificador.length() == 0 || this.identificador.length() > 10) {
            this.validacaoMsg += "Identificador inválido";
            resp = false;
        }

        if (this.descricao == null || this.identificador.length() == 0 || this.identificador.length() > 100) {
            this.validacaoMsg += "\nNumero invalido para a descrição";
            resp = false;
        }
        return resp;
    }

}
