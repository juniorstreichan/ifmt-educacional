package ssendereco.util;

import base.negocio.RetornoNegocio;
import java.util.List;
import ssendereco.negocio.SubsistemaEndereco;
import ssendereco.vo.BairroVO;
import ssendereco.vo.MunicipioVO;
import ssendereco.vo.EstadoVO;

public class TesteUtilSSEndereco {

    private static SubsistemaEndereco subEndereco;

    static {
        TesteUtilSSEndereco.subEndereco = new SubsistemaEndereco("UNIDADETESTE");
    }

    public static void limparBaseDados() {

        List<BairroVO> listaBairro = TesteUtilSSEndereco.subEndereco.bairroBuscarPorNome("");
        for (BairroVO bairroVO : listaBairro) {
            TesteUtilSSEndereco.subEndereco.bairroExcluir(bairroVO);
        }

        List<MunicipioVO> listaCidade = TesteUtilSSEndereco.subEndereco.cidadeBuscarPorNome("");
        for (MunicipioVO cidadeVO : listaCidade) {
            TesteUtilSSEndereco.subEndereco.cidadeExcluir(cidadeVO);
        }

        List<EstadoVO> listaEstado = TesteUtilSSEndereco.subEndereco.estadoBuscarTodos();
        for (EstadoVO estadoVO : listaEstado) {
            TesteUtilSSEndereco.subEndereco.estadoExcluir(estadoVO);
        }
    }

    public static void gerarBaseDados() {
        TesteUtilSSEndereco.limparBaseDados();

        EstadoVO estadoVO1 = new EstadoVO("MT", "Mato Grosso");
        RetornoNegocio retorno1 = TesteUtilSSEndereco.subEndereco.estadoInserir(estadoVO1);
        System.out.println(retorno1.getReturnoMsg());

        EstadoVO estadoVO2 = new EstadoVO("MS", "Mato Grosso do Sul");
        retorno1 = TesteUtilSSEndereco.subEndereco.estadoInserir(estadoVO2);
        System.out.println(retorno1.getReturnoMsg());

        estadoVO1 = TesteUtilSSEndereco.subEndereco.estadoBuscarPorUF("MT");
        estadoVO2 = TesteUtilSSEndereco.subEndereco.estadoBuscarPorUF("MS");

        MunicipioVO cidadeVO1 = new MunicipioVO("Cuiaba", estadoVO1);
        retorno1 = TesteUtilSSEndereco.subEndereco.cidadeInserir(cidadeVO1);
        System.out.println(retorno1.getReturnoMsg());

        MunicipioVO cidadeVO2 = new MunicipioVO("Pocone", estadoVO1);
        retorno1 = TesteUtilSSEndereco.subEndereco.cidadeInserir(cidadeVO2);
        System.out.println(retorno1.getReturnoMsg());

        MunicipioVO cidadeVO3 = new MunicipioVO("Campo Grande", estadoVO2);
        retorno1 = TesteUtilSSEndereco.subEndereco.cidadeInserir(cidadeVO3);
        System.out.println(retorno1.getReturnoMsg());

        cidadeVO1 = TesteUtilSSEndereco.subEndereco.cidadeBuscarPorNome("Cuiaba").get(0);
        cidadeVO2 = TesteUtilSSEndereco.subEndereco.cidadeBuscarPorNome("Pocone").get(0);
        cidadeVO3 = TesteUtilSSEndereco.subEndereco.cidadeBuscarPorNome("Campo Grande").get(0);

        BairroVO bairro01 = new BairroVO("Centro", cidadeVO1);
        retorno1 = TesteUtilSSEndereco.subEndereco.bairroInserir(bairro01);
        BairroVO bairro02 = new BairroVO("CPA", cidadeVO1);
        retorno1 = TesteUtilSSEndereco.subEndereco.bairroInserir(bairro02);
        BairroVO bairro03 = new BairroVO("Centro", cidadeVO2);
        retorno1 = TesteUtilSSEndereco.subEndereco.bairroInserir(bairro03);
        BairroVO bairro04 = new BairroVO("Centro", cidadeVO3);
        retorno1 = TesteUtilSSEndereco.subEndereco.bairroInserir(bairro04);
    }
}
