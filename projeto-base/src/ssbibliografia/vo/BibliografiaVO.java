package ssbibliografia.vo;

import base.vo.EntidadeVO;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import ssautor.vo.AutorVO;

@Entity()
@Table(name = "bibliografia")
public class BibliografiaVO extends EntidadeVO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @Column(length = 50, nullable = false)
    private String titulo;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoBibliografia tipo;
    
    @Column(nullable = false)
    private Integer ano;
    
    @Column(nullable = false)
    private Integer edicao;
    
    @Column()
    @ManyToMany(mappedBy = "BibliografiaVO", targetEntity = AutorVO.class)
    @JoinTable(name = "bibliografiaAutor", joinColumns = {@JoinColumn(name = "idBibliografia")}, 
            inverseJoinColumns = {@JoinColumn(name = "idAutor")})
    private List<AutorVO> listaAutor;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public TipoBibliografia getTipo() {
        return tipo;
    }

    public void setTipo(TipoBibliografia tipo) {
        this.tipo = tipo;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Integer getEdicao() {
        return edicao;
    }

    public void setEdicao(Integer edicao) {
        this.edicao = edicao;
    }

    public List<AutorVO> getListaAutor() {
        return listaAutor;
    }

    public void setListaAutor(List<AutorVO> listaAutor) {
        this.listaAutor = listaAutor;
    }
    
    @Override
    public String toString() {
        return getTitulo() + " (" + getTipo().name() + ")";
    }

    @Override
    public boolean isValido() {
        boolean resp = true;
        if (titulo == null || titulo.trim().isEmpty()) {
            this.validacaoMsg = "Título está vazio!";
            resp = false;
        }
        if (titulo.length() > 50) {
            this.validacaoMsg += "\nO título é maior que 50 caracteres!";
            resp = false;
        }
        if (tipo == null) {
            this.validacaoMsg += "\nO típo está vazio!";
            resp = false;
        }
        if (ano == null) {
            this.validacaoMsg += "\nO ano de publicação está vazio!";
            resp = false;
        }
        if (edicao == null) {
            this.validacaoMsg += "\nO número da edição está vazio!";
            resp = false;
        }
        if (listaAutor == null || listaAutor.isEmpty()) {
            this.validacaoMsg += "\nNão há autores para esta obra!";
            resp = false;
        }
        return resp;
    }    
}