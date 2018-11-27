package sspessoa.negocio;

import base.negocio.INegocio;
import base.negocio.RetornoNegocio;
import sspessoa.vo.PessoaFisicaVO;

public class PessoaFisica implements INegocio<PessoaFisicaVO> {

    private final SubsistemaPessoa sisPessoa;
    
    public PessoaFisica(SubsistemaPessoa sisPessoa) {
        this.sisPessoa = sisPessoa;
    }

    @Override
    public RetornoNegocio validarInclusao(PessoaFisicaVO pessoaVO) {

        if (pessoaVO == null) {
            return new RetornoNegocio(false, "Dados inconsistentes - elemento nulo");
        }

        //validacao de dados
        if (!pessoaVO.isValido()) {
            return new RetornoNegocio(false, pessoaVO.getValidacaoMsg());
        }

        if (this.sisPessoa.pessoaFisicaBuscarPorCPF(pessoaVO.getCPF()) != null) {
            return new RetornoNegocio(false, "Ja existe uma pessoa com esse CPF");
        }

        return new RetornoNegocio(true, "Dados Válidos para a Inclusão");
    }

    @Override
    public RetornoNegocio validarAlteracao(PessoaFisicaVO pessoaVO) {

        if (pessoaVO == null) {
            return new RetornoNegocio(false, "Dados inconsistentes - elemento nulo");
        }

        //validacao de dados
        if (!pessoaVO.isValido()) {
            return new RetornoNegocio(false, pessoaVO.getValidacaoMsg());
        }

        if (this.sisPessoa.pessoaFisicaBuscarPorCPF(pessoaVO.getCPF()) != null) {
            return new RetornoNegocio(false, "Ja existe uma pessoa com esse CPF");
        }

        return new RetornoNegocio(true, "Dados Válidos para a Inclusão");

    }

    @Override
    public RetornoNegocio validarExclusao(PessoaFisicaVO pessoaVO) {

        if (pessoaVO == null) {
            return new RetornoNegocio(false, "Dados inconsistentes - elemento nulo");
        }
        
        if (this.sisPessoa.pessoaFisicaBuscarPorID(pessoaVO.getId()) == null) {
            return new RetornoNegocio(false, "Nao existe uma Pessoa com a ID: " + pessoaVO.getId());
        }
        
        return new RetornoNegocio(true, "Dados Válidos para a Exclusão");
    }

}
