package base.negocio;

import base.vo.EntidadeVO;
import java.util.List;

public abstract class Negocio<VO extends EntidadeVO> implements INegocio<VO> {

    List<VO> listaVO;
    
    public Negocio(List<VO> listaVO) {
        this.listaVO = listaVO;
    }
    
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
        return new RetornoNegocio(true, "Dados Válidos para a Exclusão");
    }
    
    public RetornoNegocio validarBuscaPorId(String strId) {
        if (strId == null || strId.trim().isEmpty()) {
            return new RetornoNegocio(false, "Dados inconsistentes - elemento vazio");
        }
        try {
            Long.parseLong(strId);
        } catch(NumberFormatException ex) {
            return new RetornoNegocio(false, "Dados inconsistentes - o identificador não é um número inteiro!");
        }
        return new RetornoNegocio(true, "Dados Válidos para a Busca por ID");
    }
    
    public VO buscarPorId(Long id) {
        for (VO vo : listaVO) {
            if (vo.getId().equals(id)) {
                return vo;
            }
        }
        return null;
    }
    
}
