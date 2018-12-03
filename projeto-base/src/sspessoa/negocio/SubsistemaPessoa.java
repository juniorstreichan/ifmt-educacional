package sspessoa.negocio;

import base.negocio.RetornoNegocio;
import java.util.List;
import sspessoa.persistencia.PessoaFisicaDAO;
import base.persistencia.FabricaEntityManager;
import sspessoa.vo.PessoaFisicaVO;

public class SubsistemaPessoa {

    //Classes de Persistencia
    private final PessoaFisicaDAO pessoaFisicaDAO;

    //Classes de negocio
    private final PessoaFisica pessoaFisica;

    public SubsistemaPessoa(String nomeUnidade) {
        this.pessoaFisicaDAO = new PessoaFisicaDAO(FabricaEntityManager.getInstancia(nomeUnidade).getEntityManager());
        this.pessoaFisica = new PessoaFisica(this);
    }

    //=====================Entidade Pessoa========================
    public RetornoNegocio pessoaFisicaInserir(PessoaFisicaVO pessoaVO) {
        RetornoNegocio retorno = this.pessoaFisica.validarInclusao(pessoaVO);
        if (retorno.isValido()) {
            this.pessoaFisicaDAO.startTransaction();
            this.pessoaFisicaDAO.inserir(pessoaVO);
            this.pessoaFisicaDAO.commitTransaction();
            retorno.setReturnoMsg("Inclusao realizada com sucesso");
        }
        return retorno;
    }

    public RetornoNegocio pessoaFisicaAlterar(PessoaFisicaVO pessoaVO) {
        RetornoNegocio retorno = this.pessoaFisica.validarAlteracao(pessoaVO);
        if (retorno.isValido()) {
            this.pessoaFisicaDAO.startTransaction();
            this.pessoaFisicaDAO.alterar(pessoaVO);
            this.pessoaFisicaDAO.commitTransaction();
            retorno.setReturnoMsg("Alteracao realizada com sucesso");
        }
        return retorno;
    }

    public RetornoNegocio pessoaFisicaExcluir(PessoaFisicaVO pessoaVO) {
        RetornoNegocio retorno = this.pessoaFisica.validarExclusao(pessoaVO);
        if (retorno.isValido()) {
            this.pessoaFisicaDAO.startTransaction();
            this.pessoaFisicaDAO.excluir(pessoaVO);
            this.pessoaFisicaDAO.commitTransaction();
            retorno.setReturnoMsg("Exclusao realizada com sucesso");
        }
        return retorno;
    }


    public PessoaFisicaVO pessoaFisicaBuscarPorID(Long id) {
        pessoaFisicaDAO.startTransaction();
        PessoaFisicaVO pessoaVO = pessoaFisicaDAO.buscarPorID(id);
        pessoaFisicaDAO.commitTransaction();
        return pessoaVO;
    }
    
    public List<PessoaFisicaVO> pessoaFisicaBuscarPorNome(String nome) {
        pessoaFisicaDAO.startTransaction();
        List<PessoaFisicaVO> listaPessoaVO = pessoaFisicaDAO.buscarPorNome(nome);
        pessoaFisicaDAO.commitTransaction();
        return listaPessoaVO;
    }

    public PessoaFisicaVO pessoaFisicaBuscarPorCPF(String cpf) {
        pessoaFisicaDAO.startTransaction();
        PessoaFisicaVO pessoaVO = pessoaFisicaDAO.buscarPorCPF(cpf);
        pessoaFisicaDAO.commitTransaction();
        return pessoaVO;
    }

}
