package servico.disciplina;

import base.negocio.Negocio;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import servico.Servico;
import ssareaconcentracao.negocio.AreaConcentracao;
import ssareaconcentracao.vo.AreaConcentracaoVO;

/**
 *
 * @author LUCAS
 */
public class ServicoAreaConcentracao extends Servico<AreaConcentracaoVO> {
    
    public ServicoAreaConcentracao(Negocio<AreaConcentracaoVO> negocio, Class<AreaConcentracaoVO> classe) {
        super(new AreaConcentracao(AreaConcentracao.getListaAreaConcentracao()), AreaConcentracaoVO.class);
    }
    
    @GET
    @Path("/buscarPorId")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public String buscarPorId(String strId) {
        return super.buscarPorId(strId);
    }

    @DELETE
    @Path("/remover")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public String remover(String strObjeto) {
        return super.remover(strObjeto);
    }

    @PUT
    @Path("/alterar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public String alterar(String strObjeto) {
        return super.alterar(strObjeto);
    }

    @POST
    @Path("/cadastrar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public String cadastrar(String strObjeto) {
        return super.cadastrar(strObjeto);
    }
    
}
