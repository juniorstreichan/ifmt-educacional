package servico.instituicao;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author junior
 */
@Path("/instituicao")
public class ServicoInstituicao {
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String Ola(){
        return "OLA WORDI";
    }
    
}
