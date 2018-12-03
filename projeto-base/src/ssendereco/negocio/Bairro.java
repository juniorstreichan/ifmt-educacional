package ssendereco.negocio;

import base.negocio.INegocio;
import java.util.List;
import base.negocio.RetornoNegocio;
import ssendereco.vo.BairroVO;

public class Bairro implements INegocio<BairroVO>{

    private final SubsistemaEndereco subsistemaEndereco;

    public Bairro(SubsistemaEndereco addressSubsystem) {
        this.subsistemaEndereco = addressSubsystem;
    }

    @Override
    public RetornoNegocio validarInclusao(BairroVO bairroVO) {

        if (bairroVO == null) {
            return new RetornoNegocio(false, "Dados inconsistentes - elemento nulo");
        }

        //validacao de dados
        if (!bairroVO.isValido()) {
            return new RetornoNegocio(false, bairroVO.getValidacaoMsg());
        }

        //verificacao de regras de negocio
        List<BairroVO> listaBairro = subsistemaEndereco.bairroBuscarPorNome(bairroVO.getNome());
        if (listaBairro != null) {
            for (BairroVO tempBairroVO : listaBairro) {
                if (tempBairroVO.getMunicipio().equals(bairroVO.getMunicipio())) {
                    return new RetornoNegocio(false, "Ja existe um Bairro com esse nome para essa Cidade");
                }
            }
        }

        return new RetornoNegocio(true, "Inclusao valida");
    }

    @Override
    public RetornoNegocio validarAlteracao(BairroVO bairroVO) {

        if (bairroVO == null || bairroVO.getId() == null) {
            return new RetornoNegocio(false, "Dados inconsistentes - elemento nulo");
        }
        
        //Verificacao de validacao de dados
        if (!bairroVO.isValido()) {
            return new RetornoNegocio(false, bairroVO.getValidacaoMsg());
        }
        //verificacao de regras de negocio
        if (subsistemaEndereco.bairroBuscarPorID(bairroVO.getId()) == null) {
            return new RetornoNegocio(false, "Nao existe um Bairro com esse ID: " + bairroVO.getId());
        }
        return new RetornoNegocio(true, "Alteracao valida");
    }

    @Override
    public RetornoNegocio validarExclusao(BairroVO bairroVO) {
        
        if (bairroVO == null || bairroVO.getId() == null) {
            return new RetornoNegocio(false, "Dados inconsistentes - elemento nulo");
        }
        
        //verificacao de regras de negocio
        if (subsistemaEndereco.bairroBuscarPorID(bairroVO.getId()) == null) {
            return new RetornoNegocio(false, "Nao existe um Bairro com esse ID: " + bairroVO.getId());
        }
        return new RetornoNegocio(true, "Exclusao valida");
    }
}
