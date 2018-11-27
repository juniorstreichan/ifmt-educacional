package ssendereco.negocio;

import base.negocio.INegocio;
import base.negocio.RetornoNegocio;
import ssendereco.vo.EstadoVO;

public class Estado implements INegocio<EstadoVO> {

    private final SubsistemaEndereco sisEndereco;

    public Estado(SubsistemaEndereco sisEndereco) {
        this.sisEndereco = sisEndereco;
    }

    @Override
    public RetornoNegocio validarInclusao(EstadoVO estadoVO) {
        if (estadoVO == null) {
            return new RetornoNegocio(false, "Dados inconsistentes - elemento nulo");
        }

        //validacao de dados
        if (!estadoVO.isValido()) {
            return new RetornoNegocio(false, estadoVO.getValidacaoMsg());
        }

        //verificacao de regras de negocio
        if (this.sisEndereco.estadoBuscarPorUF(estadoVO.getSigla()) != null) {
            return new RetornoNegocio(false, "Ja existe um Estado com a UF: " + estadoVO.getSigla());
        }

        return new RetornoNegocio(true, "Inclusao valida");
    }

    @Override
    public RetornoNegocio validarAlteracao(EstadoVO estadoVO) {
        if (estadoVO == null || estadoVO.getId() == null) {
            return new RetornoNegocio(false, "Dados inconsistentes - elemento nulo");
        }

        if (!estadoVO.isValido()) {
            return new RetornoNegocio(false, estadoVO.getValidacaoMsg());
        }

        if (this.sisEndereco.estadoBuscarPorID(estadoVO.getId()) == null) {
            return new RetornoNegocio(false, "Nao existe um Estado com a ID: " + estadoVO.getId());
        }

        EstadoVO estadoTempVO = this.sisEndereco.estadoBuscarPorUF(estadoVO.getSigla());
        if (estadoTempVO != null && !estadoTempVO.equals(estadoVO)) {
            return new RetornoNegocio(false, "Ja existe um Estado com a UF: " + estadoVO.getSigla());
        }
        
        return new RetornoNegocio(true, "Alteracao valida");
    }

    @Override
    public RetornoNegocio validarExclusao(EstadoVO estadoVO) {
        if (estadoVO == null || estadoVO.getId() == null) {
            return new RetornoNegocio(false, "Dados inconsistentes - elemento nulo");
        }

        if (this.sisEndereco.estadoBuscarPorID(estadoVO.getId()) == null) {
            return new RetornoNegocio(false, "Nao existe um Estado com a ID: " + estadoVO.getId());
        }

        return new RetornoNegocio(true, "Exclusao valida");
    }
}
