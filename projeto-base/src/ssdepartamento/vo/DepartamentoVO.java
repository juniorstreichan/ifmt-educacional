package ssdepartamento.vo;

import base.vo.EntidadeVO;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import sscampus.vo.CampusVO;
import ssendereco.vo.TelefoneVO;

@Entity
@Table(name = "departamento")
public class DepartamentoVO extends EntidadeVO {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;
    
    @Column(length = 50, nullable = false)
    private String nome;    
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH})
    private CampusVO campus;
    
    @ElementCollection
    @CollectionTable(
            name="telefonedepartamento",
            joinColumns=@JoinColumn(name="departamento_fone"),
            uniqueConstraints= @UniqueConstraint(columnNames={"departamento_fone", "numero"})
    )
    private Set<TelefoneVO> listaFones;
    
    public DepartamentoVO(){
        this.listaFones = new HashSet();
    }
    
    public DepartamentoVO(String nome, CampusVO campus){
        this.nome = nome;
        this.campus = campus;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<TelefoneVO> getListaFones() {
        return listaFones;
    }

    public void setListaFones(Set<TelefoneVO> listaFones) {
        this.listaFones = listaFones;
    }

    public CampusVO getCampus() {
        return campus;
    }

    public void setCampus(CampusVO campus) {
        this.campus = campus;
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

        for (TelefoneVO fone : this.listaFones) {
            if (!fone.isValido()) {
                this.validacaoMsg += fone.getValidacaoMsg();
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
