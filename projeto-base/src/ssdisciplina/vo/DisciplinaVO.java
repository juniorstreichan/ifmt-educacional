package ssdisciplina.vo;

import base.vo.EntidadeVO;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import ssbibliografia.vo.BibliografiaVO;
import ssenum.RegimeEnum;

@Entity
@Table(name = "disciplina")
public class DisciplinaVO extends EntidadeVO {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @Column(length = 50, nullable = false)
    private String codigo;
    
    @Column(length = 50, nullable = false)
    private String nome;
    
    @Column(length = 100, nullable = false)
    private String objetivo;
    
    @Column(length = 100, nullable = false)
    private String ementa;
    
    @Column(nullable = false)
    private int periodo;
    
    @Column(nullable = false)
    private int horasTeoricas;
    
    @Column(nullable = false)
    private int horasPraticas;
    
    @OneToMany
    private BibliografiaVO bibliografiasBasicas;
    
    @OneToMany
    private BibliografiaVO bibliografiasComplementares;
    
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "regime")
    private RegimeEnum regime;    

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getEmenta() {
        return ementa;
    }

    public void setEmenta(String ementa) {
        this.ementa = ementa;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public int getHorasTeoricas() {
        return horasTeoricas;
    }

    public void setHorasTeoricas(int horasTeoricas) {
        this.horasTeoricas = horasTeoricas;
    }

    public int getHorasPraticas() {
        return horasPraticas;
    }

    public void setHorasPraticas(int horasPraticas) {
        this.horasPraticas = horasPraticas;
    }

    public BibliografiaVO getBibliografiasBasicas() {
        return bibliografiasBasicas;
    }

    public void setBibliografiasBasicas(BibliografiaVO bibliografiasBasicas) {
        this.bibliografiasBasicas = bibliografiasBasicas;
    }

    public BibliografiaVO getBibliografiasComplementares() {
        return bibliografiasComplementares;
    }

    public void setBibliografiasComplementares(BibliografiaVO bibliografiasComplementares) {
        this.bibliografiasComplementares = bibliografiasComplementares;
    }

    public RegimeEnum getRegime() {
        return regime;
    }

    public void setRegime(RegimeEnum regime) {
        this.regime = regime;
    }
    
    @Override
    public String toString() {
        return this.nome;
    }

    @Override
    public boolean isValido() {
        boolean resp = true;

        if (this.codigo == null || this.codigo.length() == 0 || this.codigo.length() > 50) {
            this.validacaoMsg += "\nCodigo  inválido";
            resp = false;
        }
        
        if (this.nome == null || this.nome.length() == 0 || this.nome.length() > 50) {
            this.validacaoMsg += "\nNome  invalido";
            resp = false;
        }
        
        if (this.ementa == null || this.ementa.length() == 0 || this.ementa.length() > 100) {
            this.validacaoMsg += "\nEmenta inválida";
            resp = false;
        }
        
        if (this.objetivo == null || this.objetivo.length() == 0 || this.objetivo.length() > 100) {
            this.validacaoMsg += "\nObjetivo inválido";
            resp = false;
        }
        
        if (this.bibliografiasBasicas == null) {
            this.validacaoMsg += "Bibliografias básicas não podem ser nulas para esta disciplina";
            resp = false;
        } else {
            if (!this.bibliografiasBasicas.isValido()) {
                this.validacaoMsg += this.bibliografiasBasicas.getValidacaoMsg();
                resp = false;
            }
        }
        
        if (this.bibliografiasComplementares == null) {
            this.validacaoMsg += "Bibliografias complementares não podem ser nulas para esta disciplina";
            resp = false;
        } else {
            if (!this.bibliografiasComplementares.isValido()) {
                this.validacaoMsg += this.bibliografiasComplementares.getValidacaoMsg();
                resp = false;
            }
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
