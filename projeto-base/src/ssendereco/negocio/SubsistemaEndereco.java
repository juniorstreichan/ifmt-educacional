package ssendereco.negocio;

import java.util.List;
import base.negocio.RetornoNegocio;
import ssendereco.persistencia.BairroDAO;
import ssendereco.persistencia.MunicipioDAO;
import ssendereco.persistencia.EstadoDAO;
import base.persistencia.FabricaEntityManager;
import ssendereco.vo.BairroVO;
import ssendereco.vo.MunicipioVO;
import ssendereco.vo.EstadoVO;

public class SubsistemaEndereco {

    //Classes de negocio
    private final Estado estado;
    private final Municipio cidade;
    private final Bairro bairro;

    //Classes de persistencia
    private final BairroDAO bairroDAO;
    private final MunicipioDAO cidadeDAO;
    private final EstadoDAO estadoDAO;

    public SubsistemaEndereco(String nomeUnidade) {
        this.bairroDAO = new BairroDAO(FabricaEntityManager.getInstancia(nomeUnidade).getEntityManager());
        this.cidadeDAO = new MunicipioDAO(FabricaEntityManager.getInstancia(nomeUnidade).getEntityManager());
        this.estadoDAO = new EstadoDAO(FabricaEntityManager.getInstancia(nomeUnidade).getEntityManager());
        this.cidade = new Municipio(this);
        this.bairro = new Bairro(this);
        this.estado = new Estado(this);
    }

    //=====================Entidade Estado========================
    public RetornoNegocio estadoInserir(EstadoVO estadoVO) {
        RetornoNegocio retorno = estado.validarInclusao(estadoVO);
        if (retorno.isValido()) {
            estadoDAO.startTransaction();
            if (estadoDAO.inserir(estadoVO)) {
                retorno.setReturnoMsg("Inclusao realizada com sucesso");
            } else {
                retorno.setValido(false);
                retorno.setReturnoMsg("Inclusao nao realizada!!");
            }
            estadoDAO.commitTransaction();
        }
        return retorno;
    }

    public RetornoNegocio estadoAlterar(EstadoVO estadoVO) {
        RetornoNegocio retorno = estado.validarAlteracao(estadoVO);
        if (retorno.isValido()) {
            estadoDAO.startTransaction();
            if (estadoDAO.alterar(estadoVO)) {
                retorno.setReturnoMsg("Alteracao realizada com sucesso");
            } else {
                retorno.setValido(false);
                retorno.setReturnoMsg("Alteracao nao realizada!!");
            }
            estadoDAO.commitTransaction();
        }
        return retorno;
    }

    public RetornoNegocio estadoExcluir(EstadoVO estadoVO) {
        RetornoNegocio retorno = estado.validarExclusao(estadoVO);
        if (retorno.isValido()) {
            estadoDAO.startTransaction();
            if (estadoDAO.excluir(estadoVO)) {
                retorno.setReturnoMsg("Exclusao realizada com sucesso");
            } else {
                retorno.setValido(false);
                retorno.setReturnoMsg("Exclusao nao realizada!!");
            }
            estadoDAO.commitTransaction();
        }
        return retorno;
    }

    public EstadoVO estadoBuscarPorID(Long id) {
        estadoDAO.startTransaction();
        EstadoVO estadoVO = estadoDAO.buscarPorID(id);
        estadoDAO.commitTransaction();
        return estadoVO;
    }

    public EstadoVO estadoBuscarPorUF(String uf) {
        estadoDAO.startTransaction();
        EstadoVO estadoVO = estadoDAO.buscarPorUF(uf);
        estadoDAO.commitTransaction();
        return estadoVO;
    }

    public List<EstadoVO> estadoBuscarPorNome(String nome) {
        estadoDAO.startTransaction();
        List<EstadoVO> listaEstadoVO = estadoDAO.buscarPorNome(nome);
        estadoDAO.commitTransaction();
        return listaEstadoVO;
    }

    public List<EstadoVO> estadoBuscarTodos() {
        estadoDAO.startTransaction();
        List<EstadoVO> lista = estadoDAO.buscarTodos();
        estadoDAO.commitTransaction();
        return lista;
    }

    //=====================Entidade Cidade========================
    public RetornoNegocio cidadeInserir(MunicipioVO cidadeVO) {
        RetornoNegocio retorno = cidade.validarInclusao(cidadeVO);
        if (retorno.isValido()) {
            cidadeDAO.startTransaction();
            if (cidadeDAO.inserir(cidadeVO)) {
                retorno.setReturnoMsg("Inclusao realizada com sucesso");
            } else {
                retorno.setValido(false);
                retorno.setReturnoMsg("Inclusao nao realizada!!");
            }
            cidadeDAO.commitTransaction();
        }
        return retorno;
    }

    public RetornoNegocio cidadeAlterar(MunicipioVO cidadeVO) {
        RetornoNegocio retorno = cidade.validarAlteracao(cidadeVO);
        if (retorno.isValido()) {
            cidadeDAO.startTransaction();
            if (cidadeDAO.alterar(cidadeVO)) {
                retorno.setReturnoMsg("Alteracao realizada com sucesso");
            } else {
                retorno.setValido(false);
                retorno.setReturnoMsg("Alteracao nao realizada!!");
            }
            cidadeDAO.commitTransaction();
        }
        return retorno;
    }

    public RetornoNegocio cidadeExcluir(MunicipioVO cidadeVO) {
        RetornoNegocio retorno = cidade.validarExclusao(cidadeVO);
        if (retorno.isValido()) {
            cidadeDAO.startTransaction();
            if (cidadeDAO.excluir(cidadeVO)) {
                retorno.setReturnoMsg("Exclusao realizada com sucesso");
            } else {
                retorno.setValido(false);
                retorno.setReturnoMsg("Exclusao nao realizada!!");
            }
            cidadeDAO.commitTransaction();
        }
        return retorno;
    }

    public MunicipioVO cidadeBuscarPorID(Long id) {
        cidadeDAO.startTransaction();
        MunicipioVO cidadeVO = cidadeDAO.buscarPorID(id);
        cidadeDAO.commitTransaction();
        return cidadeVO;
    }

    public List<MunicipioVO> cidadeBuscarPorNome(String nome) {
        cidadeDAO.startTransaction();
        List<MunicipioVO> listaCidadeVO = cidadeDAO.buscarPorNome(nome);
        cidadeDAO.commitTransaction();
        return listaCidadeVO;
    }

    public List<MunicipioVO> cidadeBuscarPorEstado(EstadoVO estadoVO) {
        cidadeDAO.startTransaction();
        List<MunicipioVO> listaCidadeVO = cidadeDAO.buscarPorEstado(estadoVO);
        cidadeDAO.commitTransaction();
        return listaCidadeVO;
    }

    //=====================Entidade Bairro========================
    //============================================================
    public RetornoNegocio bairroInserir(BairroVO bairroVO) {

        RetornoNegocio retorno = bairro.validarInclusao(bairroVO);
        if (retorno.isValido()) {
            bairroDAO.startTransaction();
            if (bairroDAO.inserir(bairroVO)) {
                retorno.setReturnoMsg("Inclusao realizada com sucesso");
            } else {
                retorno.setValido(false);
                retorno.setReturnoMsg("Inclusao nao realizada!!");
            }
            bairroDAO.commitTransaction();
        }
        return retorno;
    }

    public RetornoNegocio bairroAlterar(BairroVO bairroVO) {

        RetornoNegocio retorno = bairro.validarAlteracao(bairroVO);
        if (retorno.isValido()) {
            bairroDAO.startTransaction();
            if (bairroDAO.alterar(bairroVO)) {
                retorno.setReturnoMsg("Alteracao realizada com sucesso");
            } else {
                retorno.setValido(false);
                retorno.setReturnoMsg("Alteracao nao realizada!!");
            }
            bairroDAO.commitTransaction();
        }
        return retorno;
    }

    public RetornoNegocio bairroExcluir(BairroVO bairroVO) {
        RetornoNegocio retorno = bairro.validarExclusao(bairroVO);
        if (retorno.isValido()) {
            bairroDAO.startTransaction();
            if (bairroDAO.excluir(bairroVO)) {
                retorno.setReturnoMsg("Exclusao realizada com sucesso");
            } else {
                retorno.setValido(false);
                retorno.setReturnoMsg("Exclusao nao realizada!!");
            }
            bairroDAO.commitTransaction();
        }
        return retorno;
    }

    public BairroVO bairroBuscarPorID(Long id) {
        bairroDAO.startTransaction();
        BairroVO bairroVO = bairroDAO.buscarPorID(id);
        bairroDAO.commitTransaction();
        return bairroVO;
    }

    public List<BairroVO> bairroBuscarPorNome(String nome) {
        bairroDAO.startTransaction();
        List<BairroVO> listaBairroVO = bairroDAO.buscarPorNome(nome);
        bairroDAO.commitTransaction();
        return listaBairroVO;
    }

    public List<BairroVO> bairroBuscarPorCidade(MunicipioVO cidadeVO) {
        bairroDAO.startTransaction();
        List<BairroVO> listaBairroVO = bairroDAO.buscarPorCidade(cidadeVO);
        bairroDAO.commitTransaction();
        return listaBairroVO;
    }
}
