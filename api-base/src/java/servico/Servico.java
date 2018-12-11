package servico;

import base.negocio.Negocio;
import base.negocio.RetornoNegocio;
import base.vo.EntidadeVO;
import com.google.gson.Gson;

public abstract class Servico<VO extends EntidadeVO> implements IServico {

    private final Negocio<VO> negocio;
    private final Class classe;
    private final Gson gson = new Gson();
    
    public Servico(Negocio<VO> negocio, Class<VO> classe) {
        this.negocio = negocio;
        this.classe = classe;
    }
    
    @Override
    public String cadastrar(String strObjeto) {
        VO vo = (VO) gson.fromJson(strObjeto, classe);
        RetornoNegocio retorno = negocio.validarInclusao(vo);
        if (!retorno.isValido()) {
            return gson.toJson(retorno);
        }
        negocio.incluir(vo);
        return gson.toJson(new RetornoNegocio(true, vo.toString() + " cadastrado com sucesso!"));
    }

    @Override
    public String alterar(String strObjeto) {
        VO vo = (VO) gson.fromJson(strObjeto, classe);
        RetornoNegocio retorno = negocio.validarAlteracao(vo);
        if (!retorno.isValido()) {
            return gson.toJson(retorno);
        }
        negocio.alterar(vo);
        return gson.toJson(new RetornoNegocio(true, vo.toString() + " alterado com sucesso!"));
    }

    @Override
    public String remover(String strObjeto) {
        VO vo = (VO) gson.fromJson(strObjeto, classe);
        RetornoNegocio retorno = negocio.validarExclusao(vo);
        if (!retorno.isValido()) {
            return gson.toJson(retorno);
        }
        negocio.excluir(vo);
        return gson.toJson(new RetornoNegocio(true, vo.toString() + " excluído com sucesso!"));
    }

    @Override
    public String buscarPorId(String strId) {
        RetornoNegocio retorno = negocio.validarBuscaPorId(strId);
        if (!retorno.isValido()) {
            return gson.toJson(retorno);
        }
        VO vo = negocio.buscarPorId(Long.parseLong(strId));
        if (vo == null) {
            return gson.toJson(new RetornoNegocio(false, "Nenhum resultado encontrado para o ID " + strId));
        }
        vo.setValidacaoMsg(null);
        return gson.toJson(vo);
    }
    
}
