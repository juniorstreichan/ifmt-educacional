package base.negocio;

import base.vo.EntidadeVO;

public class Negocio<VO extends EntidadeVO> implements INegocio<VO> {

    @Override
    public RetornoNegocio validarInclusao(VO vo) {
        if (vo == null) {
            return new RetornoNegocio(false, "Dados inconsistentes - elemento nulo");
        }
        if (!vo.isValido()) {
            return new RetornoNegocio(false, vo.getValidacaoMsg());
        }
        return new RetornoNegocio(true, "Dados Válidos para a Inclusão");
    }

    @Override
    public RetornoNegocio validarAlteracao(VO vo) {
        if (vo == null) {
            return new RetornoNegocio(false, "Dados inconsistentes - elemento nulo");
        }
        if (vo.getId() == null) {
            return new RetornoNegocio(false, "O identificador dos dados está vazio!");
        }
        if (!vo.isValido()) {
            return new RetornoNegocio(false, vo.getValidacaoMsg());
        }
        return new RetornoNegocio(true, "Dados Válidos para a Alteração");
    }

    @Override
    public RetornoNegocio validarExclusao(VO vo) {
        if (vo == null) {
            return new RetornoNegocio(false, "Dados inconsistentes - elemento nulo");
        }
        if (vo.getId() == null) {
            return new RetornoNegocio(false, "O identificador dos dados está vazio!");
        }
        return new RetornoNegocio(true, "Dados Válidos para a Alteração");
    }
    
}
