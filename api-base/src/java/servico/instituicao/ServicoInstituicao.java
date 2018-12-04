package servico.instituicao;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Collection;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import sscampus.vo.CampusVO;
import sscurso.vo.CursoVO;
import ssdepartamento.vo.DepartamentoVO;
import ssendereco.vo.EnderecoVO;

/**
 *
 * @author junior
 */
@Path("/instituicao")
public class ServicoInstituicao {

    private Collection<CursoVO> cursos = new ArrayList<>();
    private Collection<DepartamentoVO> departamentos = new ArrayList<>();
    private Collection<CampusVO> campus = new ArrayList<>();

    public ServicoInstituicao() {
        int cont = 1;
        while (cont < 20) {

            EnderecoVO endereco = new EnderecoVO();

            endereco.setRua("Rua " + (cont * 10));
            endereco.setNumero(cont);
            endereco.setComplement("Perto da avenida " + (cont * 3) + "" + (cont * 5));

            CampusVO campus = new CampusVO();
            campus.setId(Long.parseLong(cont + ""));
            campus.setNome("Campus " + cont);
            campus.setEndereco(endereco);
            this.campus.add(campus);

            DepartamentoVO departamento = new DepartamentoVO();
            departamento.setId(Long.parseLong(cont + ""));
            departamento.setNome("Departamento " + cont);

            departamentos.add(departamento);

            CursoVO curso = new CursoVO();
            curso.setCodigo("" + (2 * cont) + "" + (3 * cont));
            curso.setId(Long.parseLong(cont + ""));
            curso.setDepartamento(departamento);
            curso.setNome("Curso " + cont);
            cursos.add(curso);
            cont++;
        }
    }

    @GET
    @Path("/curso")
    @Produces(MediaType.APPLICATION_JSON)
    public String listarCursos() {

        return new Gson().toJson(cursos);
    }

    @GET
    @Path("/departamento")
    @Produces(MediaType.APPLICATION_JSON)
    public String listarDepartamentos() {
        return new Gson().toJson(departamentos);
    }

    @GET
    @Path("/campus")
    @Produces(MediaType.APPLICATION_JSON)
    public String listarCampus() {
        return new Gson().toJson(this.campus);
    }
}
