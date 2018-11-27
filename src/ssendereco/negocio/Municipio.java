package ssendereco.negocio;

import base.negocio.INegocio;
import java.util.List;
import base.negocio.RetornoNegocio;
import ssendereco.vo.MunicipioVO;

public class Municipio implements INegocio<MunicipioVO>{

    private final SubsistemaEndereco sisEndereco;

    public Municipio(SubsistemaEndereco sisEndereco) {
        this.sisEndereco = sisEndereco;
    }

    public RetornoNegocio validarInclusao(MunicipioVO cidadeVO) {

        if (cidadeVO == null) {
            return new RetornoNegocio(false, "Dados inconsistentes - elemento nulo");
        }

        //validacao de dados
        if (!cidadeVO.isValido()) {
            return new RetornoNegocio(false, cidadeVO.getValidacaoMsg());
        }

        //verificacao de regras de negocio
        List<MunicipioVO> listaCidade = this.sisEndereco.cidadeBuscarPorNome(cidadeVO.getNome());
        if (listaCidade != null) {
            for (MunicipioVO tempCidadeVO : listaCidade) {
                if (tempCidadeVO.getEstado().equals(cidadeVO.getEstado())) {
                    return new RetornoNegocio(false, "Ja existe uma cidade com esse nome para esse estado");
                }
            }
        }
        return new RetornoNegocio(true, "Inclusao valida");
    }

    public RetornoNegocio validarAlteracao(MunicipioVO cidadeVO) {

        if (cidadeVO == null || cidadeVO.getId() == null) {
            return new RetornoNegocio(false, "Dados inconsistentes - elemento nulo");
        }

        //validacao de dados
        if (!cidadeVO.isValido()) {
            return new RetornoNegocio(false, cidadeVO.getValidacaoMsg());
        }

        //verificacao de regras de negocio
        if (this.sisEndereco.cidadeBuscarPorID(cidadeVO.getId()) == null) {
            return new RetornoNegocio(false, "Nao existe uma cidade com esse ID: " + cidadeVO.getId());
        }
        
        return new RetornoNegocio(true, "Alteracao valida");
    }

    public RetornoNegocio validarExclusao(MunicipioVO cidadeVO) {

        if (cidadeVO == null || cidadeVO.getId() == null) {
            return new RetornoNegocio(false, "Dados inconsistentes - elemento nulo");
        }

        //validacao de dados
        if (!cidadeVO.isValido()) {
            return new RetornoNegocio(false, cidadeVO.getValidacaoMsg());
        }

        //verificacao de regras de negocio
        if (this.sisEndereco.cidadeBuscarPorID(cidadeVO.getId()) == null) {
            return new RetornoNegocio(false, "Nao existe uma cidade com esse ID: " + cidadeVO.getId());
        }
        
        return new RetornoNegocio(true, "Exclusao valida");
    }
}
