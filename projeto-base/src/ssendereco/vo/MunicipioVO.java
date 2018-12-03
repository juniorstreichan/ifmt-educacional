package ssendereco.vo;

import javax.persistence.CascadeType;
import base.vo.EntidadeVO;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "municipio")
public class MunicipioVO extends EntidadeVO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 40, nullable = false)
    private String nome;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH})
    private EstadoVO estado;

    public MunicipioVO() {

    }

    public MunicipioVO(String nome, EstadoVO estado) {
        this.estado = estado;
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

    public EstadoVO getEstado() {
        return estado;
    }

    public void setEstado(EstadoVO estado) {
        this.estado = estado;
    }

    @Override
    public boolean isValido() {
        boolean resp = true;

        if (this.nome == null || this.nome.length() == 0 || this.nome.length() > 40) {
            this.validacaoMsg += "Nome invalido para a Cidade";
            resp = false;
        }

        if (this.estado == null) {
            this.validacaoMsg += "\nEstado nao pode ser nulo para o municipio";
            resp = false;
        }else {
            if (!this.estado.isValido()) {
                this.validacaoMsg += this.estado.getValidacaoMsg();
                resp = false;
            }
        }
        return resp;
    }

    @Override
    public String toString() {
        return this.nome.trim();
    }
}
