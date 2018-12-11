package servico.instituicao;

import base.negocio.RetornoNegocio;
import com.google.gson.Gson;
import instituicao.negocio.*;
import java.util.ArrayList;
import java.util.Collection;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import sscampus.vo.CampusVO;
import sscurso.vo.CursoVO;
import ssdepartamento.vo.DepartamentoVO;
import ssendereco.vo.EnderecoVO;
import java.util.stream.Collectors;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;

/**
 *
 * @author junior
 */
@Path("/instituicao")
public class ServicoInstituicao {

    private static Collection<CursoVO> cursos = new ArrayList<>();
    private static Collection<DepartamentoVO> departamentos = new ArrayList<>();
    private static Collection<CampusVO> campus = new ArrayList<>();
    private static Curso cursoNegocio = new Curso();
    private static Departamento departamentoNegocio = new Departamento();
    private static Campus campusNegocio = new Campus();
    private static boolean populou = false;

    public ServicoInstituicao() {
        int cont = 1;
        if (!populou) {
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
            populou = true;
        }
    }

//   ======================================================
//   CURSO
//   ======================================================
    @GET
    @Path("/curso")
    @Produces(MediaType.APPLICATION_JSON)
    public String listarCursos() {

        return new Gson().toJson(cursos);
    }

    @POST
    @Path("/curso")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String inserirCurso(String curso) {
        try {
            CursoVO obj = new Gson().fromJson(curso, CursoVO.class);
            obj.setId(System.currentTimeMillis());

            RetornoNegocio ret = cursoNegocio.validarInclusao(obj);

            if (!ret.isValido()) {
                return new Gson().toJson(ret.getReturnoMsg());

            }

            this.cursos.add(obj);
            return new Gson().toJson(obj);
        } catch (Exception e) {
            return new Gson().toJson(e);
        }

    }

    @PUT
    @Path("/curso/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String alterarCurso(@PathParam(value = "id") String id, String curso) {
        try {
            CursoVO obj = new Gson().fromJson(curso, CursoVO.class);
            obj.setId(Long.parseLong(id));

            RetornoNegocio ret = cursoNegocio.validarAlteracao(obj);

            if (!ret.isValido()) {
                return new Gson().toJson(ret.getReturnoMsg());

            }

            this.cursos.stream().forEach(c -> {
                if (c.getId().equals(obj.getId())) {
                    c.setNome(obj.getNome());
                    c.setCodigo(obj.getCodigo());

                }
            });
            return new Gson().toJson(obj);
        } catch (Exception e) {
            return new Gson().toJson(e);
        }

    }

    @POST
    @Path("/curso/delete")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String deletarCurso(String idParams) {
        try {

            Identificador identificador = new Gson().fromJson(idParams, Identificador.class);
            System.out.println(identificador.getId());

            cursos.stream().forEach(c -> {
                if (c.getId().equals(identificador.getId())) {
                    RetornoNegocio r = cursoNegocio.validarExclusao(c);
                    if (r.isValido()) {
                        cursos.remove(c);

                    }
                }
            });

            return idParams;
        } catch (Exception e) {
            return new Gson().toJson(e);
        }

    }

    @GET
    @Path("/curso/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String listarCursoPorId(@PathParam(value = "id") String id) {

        return new Gson().toJson(cursos.stream().filter(c -> c.getId().equals(Long.parseLong(id))).collect(Collectors.toList()));
    }

//   ======================================================
//   DEPARTAMENTO
//   ======================================================
    @GET
    @Path("/departamento")
    @Produces(MediaType.APPLICATION_JSON)
    public String listarDepartamentos() {
        return new Gson().toJson(departamentos);
    }

    @POST
    @Path("/departamento")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String inserirDepartamento(String departamento) {
        try {
            DepartamentoVO obj = new Gson().fromJson(departamento, DepartamentoVO.class);
            obj.setId(System.currentTimeMillis());

            RetornoNegocio ret = departamentoNegocio.validarInclusao(obj);

            if (!ret.isValido()) {
                return new Gson().toJson(ret.getReturnoMsg());

            }

            this.departamentos.add(obj);
            return new Gson().toJson(obj);

        } catch (Exception e) {
            return new Gson().toJson(e);
        }

    }

    @PUT
    @Path("/departamento/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String alterarDepartamento(@PathParam(value = "id") String id, String departamento) {
        try {
            DepartamentoVO obj = new Gson().fromJson(departamento, DepartamentoVO.class);
            obj.setId(System.currentTimeMillis());

            RetornoNegocio ret = departamentoNegocio.validarAlteracao(obj);

            if (!ret.isValido()) {
                return new Gson().toJson(ret.getReturnoMsg());

            }

            this.departamentos.stream().forEach(c -> {
                if (c.getId().equals(obj.getId())) {
                    c.setNome(obj.getNome());
                }
            });
            return new Gson().toJson(obj);
        } catch (Exception e) {
            return new Gson().toJson(e);
        }

    }

    @POST
    @Path("/departamento/delete")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String deletarDepartamento(String idParams) {
        try {

            Identificador identificador = new Gson().fromJson(idParams, Identificador.class);
            System.out.println(identificador.getId());

            departamentos.stream().forEach(c -> {
                if (c.getId().equals(identificador.getId())) {
                    RetornoNegocio r = departamentoNegocio.validarExclusao(c);
                    if (r.isValido()) {
                        cursos.remove(c);
                    }
                }
            });

            return idParams;
        } catch (Exception e) {
            return new Gson().toJson(e);
        }

    }

    @GET
    @Path("/departamento/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String listarDepartamentoPorId(@PathParam(value = "id") String id) {
        return new Gson().toJson(this.departamentos.stream().filter(c -> c.getId().equals(Long.parseLong(id))).collect(Collectors.toList()));
    }

//   ======================================================
//   CAMPUS
//   ======================================================
    @GET
    @Path("/campus")
    @Produces(MediaType.APPLICATION_JSON)
    public String listarCampus() {
        return new Gson().toJson(this.campus);
    }

    @POST
    @Path("/campus")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String inserirCampus(String campus) {
        try {
            CampusVO obj = new Gson().fromJson(campus, CampusVO.class);
            obj.setId(System.currentTimeMillis());
            RetornoNegocio ret = campusNegocio.validarInclusao(obj);

            if (!ret.isValido()) {
                return new Gson().toJson(ret.getReturnoMsg());

            }

            this.campus.stream().forEach(c -> {
                if (c.getId().equals(obj.getId())) {
                    c.setNome(obj.getNome());
                }
            });
            return new Gson().toJson(obj);

        } catch (Exception e) {
            return new Gson().toJson(e);
        }

    }

    @PUT
    @Path("/campus/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String inserirCampus(@PathParam(value = "id") String id, String campus) {
        try {
            CampusVO obj = new Gson().fromJson(campus, CampusVO.class);
            obj.setId(System.currentTimeMillis());
            RetornoNegocio ret = campusNegocio.validarAlteracao(obj);

            if (!ret.isValido()) {
                return new Gson().toJson(ret.getReturnoMsg());

            }

            this.campus.add(obj);
            return new Gson().toJson(obj);

        } catch (Exception e) {
            return new Gson().toJson(e);
        }

    }

    @POST
    @Path("/campus/delete")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String deletarCampus(String idParams) {
        try {

            Identificador identificador = new Gson().fromJson(idParams, Identificador.class);
            System.out.println(identificador.getId());

            campus.stream().forEach(c -> {
                if (c.getId().equals(identificador.getId())) {
                    RetornoNegocio r = campusNegocio.validarExclusao(c);
                    if (r.isValido()) {
                        cursos.remove(c);
                    }
                }
            });

            return idParams;
        } catch (Exception e) {
            return new Gson().toJson(e);
        }

    }

    @GET
    @Path("/campus/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String listarCampusPorId(@PathParam(value = "id") String id) {

        return new Gson().toJson(
                this.campus.stream().filter(c -> c.getId().equals(Long.parseLong(id))).collect(Collectors.toList())
        );
    }
}

class Identificador {

    private Long id;

    public Identificador() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
