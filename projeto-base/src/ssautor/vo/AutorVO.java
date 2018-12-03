package ssautor.vo;

import base.vo.EntidadeVO;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import ssbibliografia.vo.BibliografiaVO;

@Entity()
@Table(name = "autor")
public class AutorVO extends EntidadeVO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @Column(length = 50, nullable = false)
    private String nome;
    
    @ManyToMany(mappedBy = "AutorVO", targetEntity = BibliografiaVO.class, fetch = FetchType.LAZY)
    private List<BibliografiaVO> listaBibliografia;

    public AutorVO() {
    }

    public AutorVO(String nome) {
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

    public List<BibliografiaVO> getListaBibliografia() {
        return listaBibliografia;
    }

    public void setListaBibliografia(List<BibliografiaVO> listaBibliografia) {
        this.listaBibliografia = listaBibliografia;
    }

    @Override
    public String toString() {
        return this.nome;
    }

    @Override
    public boolean isValido() {
        boolean resp = true;
        if (nome == null || nome.trim().isEmpty()) {
            this.validacaoMsg = "Nome vazio";
            resp = false;
        }

        if (nome.length() > 50) {
            this.validacaoMsg += "\nNome maior que 50 caracteres!";
            resp = false;
        }

        return resp;
    }
}
