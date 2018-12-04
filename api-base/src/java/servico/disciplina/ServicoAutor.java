package servico.disciplina;

import base.negocio.Negocio;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import servico.Servico;
import ssautor.vo.AutorVO;
import ssautor.negocio.Autor;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/aluno")
public class ServicoAutor extends Servico<AutorVO> {
    
    public ServicoAutor(Negocio<AutorVO> negocio, Class<AutorVO> classe) {
        super(new Autor(Autor.gerarListaAutor()), AutorVO.class);
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
