package servico.disciplina;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import ssdisciplina.vo.DisciplinaVO;
import servico.Servico;
import ssdisciplina.negocio.Disciplina;

@Path("/Disciplina")
public class ServicoDisciplina extends Servico<DisciplinaVO> {

    public ServicoDisciplina() {
        super(new Disciplina(Disciplina.getListaDisciplina()), DisciplinaVO.class);
    }
    
    @GET
    @Path("/buscarPorId/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public String buscarPorId(@PathParam("id") String strId) {
        return super.buscarPorId(strId);
    }

    @PUT
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
