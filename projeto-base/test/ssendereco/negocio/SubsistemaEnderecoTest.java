package ssendereco.negocio;

import base.negocio.RetornoNegocio;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import ssendereco.util.TesteUtilSSEndereco;
import ssendereco.vo.BairroVO;
import ssendereco.vo.MunicipioVO;
import ssendereco.vo.EstadoVO;

public class SubsistemaEnderecoTest {

    private SubsistemaEndereco subEndereco;

    public SubsistemaEnderecoTest() {
        this.subEndereco = new SubsistemaEndereco("UNIDADETESTE");
    }

    @Test
    public void testEstadoCRUDF() {
        TesteUtilSSEndereco.limparBaseDados();
        System.out.println("----------------estadoInserir--------------------");
        EstadoVO estadoVO1 = new EstadoVO("MG", "Minas Gerais");
        RetornoNegocio retorno1 = this.subEndereco.estadoInserir(estadoVO1);
        System.out.println(retorno1.getReturnoMsg());
        assertTrue(retorno1.isValido());

        estadoVO1 = new EstadoVO("MS", "Mato Grosso do Sul");
        retorno1 = this.subEndereco.estadoInserir(estadoVO1);
        System.out.println(retorno1.getReturnoMsg());
        assertTrue(retorno1.isValido());

        estadoVO1 = new EstadoVO("M", "");
        RetornoNegocio retorno2 = this.subEndereco.estadoInserir(estadoVO1);
        System.out.println(retorno2.getReturnoMsg());
        assertFalse(retorno2.isValido());

        estadoVO1 = null;
        RetornoNegocio retorno3 = this.subEndereco.estadoInserir(estadoVO1);
        System.out.println(retorno3.getReturnoMsg());
        assertFalse(retorno3.isValido());

        System.out.println("----------------estadoAlterar--------------------");
        estadoVO1 = this.subEndereco.estadoBuscarPorUF("MG");
        estadoVO1.setSigla("MT");
        estadoVO1.setNome("Mato Grosso");
        retorno1 = this.subEndereco.estadoAlterar(estadoVO1);
        System.out.println(retorno1.getReturnoMsg());
        assertTrue(retorno1.isValido());

        estadoVO1 = this.subEndereco.estadoBuscarPorUF("MT");
        assertEquals(estadoVO1.getNome(), "Mato Grosso");

        retorno1 = this.subEndereco.estadoAlterar(null);
        System.out.println(retorno1.getReturnoMsg());
        assertFalse(retorno1.isValido());

        estadoVO1 = new EstadoVO("MT", "Mato Grosso");
        retorno1 = this.subEndereco.estadoAlterar(estadoVO1);
        System.out.println(retorno1.getReturnoMsg());
        assertFalse(retorno1.isValido());

        System.out.println("----------------estadobuscar--------------------");
        estadoVO1 = this.subEndereco.estadoBuscarPorUF("MT");
        estadoVO1 = this.subEndereco.estadoBuscarPorID(estadoVO1.getId());
        assertEquals(estadoVO1, estadoVO1);

        List<EstadoVO> listaVO = this.subEndereco.estadoBuscarTodos();
        System.out.println(listaVO.get(0));
        assertEquals(listaVO.size(), 2);

        System.out.println("----------------estadoExcluir--------------------");
        estadoVO1 = this.subEndereco.estadoBuscarPorUF("MT");
        retorno1 = this.subEndereco.estadoExcluir(estadoVO1);
        System.out.println(retorno1.getReturnoMsg());
        assertTrue(retorno1.isValido());

        estadoVO1 = this.subEndereco.estadoBuscarPorUF("MT");
        assertNull(estadoVO1);

    }

    @Test
    public void testCidadeCRUDF() {
        TesteUtilSSEndereco.limparBaseDados();

        System.out.println("----------------cidadeInserir--------------------");
        EstadoVO estadoVO1 = new EstadoVO("MT", "Mato Grosso");
        RetornoNegocio retorno1 = this.subEndereco.estadoInserir(estadoVO1);
        System.out.println(retorno1.getReturnoMsg());

        EstadoVO estadoVO2 = new EstadoVO("MS", "Mato Grosso do Sul");
        retorno1 = this.subEndereco.estadoInserir(estadoVO2);
        System.out.println(retorno1.getReturnoMsg());

        estadoVO1 = this.subEndereco.estadoBuscarPorUF("MT");
        MunicipioVO cidadeVO1 = new MunicipioVO("Cuiaba", estadoVO1);
        retorno1 = this.subEndereco.cidadeInserir(cidadeVO1);
        System.out.println(retorno1.getReturnoMsg());
        assertTrue(retorno1.isValido());

        cidadeVO1 = new MunicipioVO("Pocone", estadoVO1);
        retorno1 = this.subEndereco.cidadeInserir(cidadeVO1);
        System.out.println(retorno1.getReturnoMsg());
        assertTrue(retorno1.isValido());

        estadoVO2 = this.subEndereco.estadoBuscarPorUF("MS");
        cidadeVO1 = new MunicipioVO("Campo Grande", estadoVO2);
        retorno1 = this.subEndereco.cidadeInserir(cidadeVO1);
        System.out.println(retorno1.getReturnoMsg());
        assertTrue(retorno1.isValido());

        List<MunicipioVO> listaCidadeVO = this.subEndereco.cidadeBuscarPorNome("Cuiaba");
        assertTrue(listaCidadeVO.size() > 0);
        listaCidadeVO = this.subEndereco.cidadeBuscarPorNome("Pocone");
        assertTrue(listaCidadeVO.size() > 0);
        listaCidadeVO = this.subEndereco.cidadeBuscarPorNome("Campo Grande");
        assertTrue(listaCidadeVO.size() > 0);

        System.out.println("----------------cidadeAlterar--------------------");
        cidadeVO1 = this.subEndereco.cidadeBuscarPorNome("Campo Grande").get(0);
        cidadeVO1.setNome("Varzea Grande");
        cidadeVO1.setEstado(this.subEndereco.estadoBuscarPorUF("MT"));
        retorno1 = this.subEndereco.cidadeAlterar(cidadeVO1);
        System.out.println(retorno1.getReturnoMsg());
        listaCidadeVO = this.subEndereco.cidadeBuscarPorNome("Varzea Grande");
        assertEquals(listaCidadeVO.size(), 1);

        System.out.println("----------------cidadeBuscar--------------------");
        cidadeVO1 = this.subEndereco.cidadeBuscarPorNome("Cuiaba").get(0);
        MunicipioVO cidadeVO2 = this.subEndereco.cidadeBuscarPorID(cidadeVO1.getId());
        System.out.println(cidadeVO2);
        assertEquals(cidadeVO1, cidadeVO2);
        listaCidadeVO = this.subEndereco.cidadeBuscarPorEstado(estadoVO1);
        assertEquals(listaCidadeVO.size(), 3);

        System.out.println("----------------cidadeExcluir--------------------");
        cidadeVO1 = this.subEndereco.cidadeBuscarPorNome("Cuiaba").get(0);
        cidadeVO2 = this.subEndereco.cidadeBuscarPorNome("Varzea Grande").get(0);
        this.subEndereco.cidadeExcluir(cidadeVO1);
        this.subEndereco.cidadeExcluir(cidadeVO2);

        cidadeVO1 = this.subEndereco.cidadeBuscarPorID(cidadeVO1.getId());
        cidadeVO2 = this.subEndereco.cidadeBuscarPorID(cidadeVO2.getId());
        assertNull(cidadeVO1);
        assertNull(cidadeVO2);
    }

    @Test
    public void testBairroCRUDF() {
        TesteUtilSSEndereco.limparBaseDados();

        System.out.println("----------------bairroInserir--------------------");
        EstadoVO estadoVO1 = new EstadoVO("MT", "Mato Grosso");
        RetornoNegocio retorno1 = this.subEndereco.estadoInserir(estadoVO1);
        System.out.println(retorno1.getReturnoMsg());

        EstadoVO estadoVO2 = new EstadoVO("MS", "Mato Grosso do Sul");
        retorno1 = this.subEndereco.estadoInserir(estadoVO2);
        System.out.println(retorno1.getReturnoMsg());

        estadoVO1 = this.subEndereco.estadoBuscarPorUF("MT");
        estadoVO2 = this.subEndereco.estadoBuscarPorUF("MS");
        
        MunicipioVO cidadeVO1 = new MunicipioVO("Cuiaba", estadoVO1);
        retorno1 = this.subEndereco.cidadeInserir(cidadeVO1);
        System.out.println(retorno1.getReturnoMsg());

        MunicipioVO cidadeVO2 = new MunicipioVO("Pocone", estadoVO1);
        retorno1 = this.subEndereco.cidadeInserir(cidadeVO2);
        System.out.println(retorno1.getReturnoMsg());

        MunicipioVO cidadeVO3 = new MunicipioVO("Campo Grande", estadoVO2);
        retorno1 = this.subEndereco.cidadeInserir(cidadeVO3);
        System.out.println(retorno1.getReturnoMsg());

        cidadeVO1 = this.subEndereco.cidadeBuscarPorNome("Cuiaba").get(0);
        cidadeVO2 = this.subEndereco.cidadeBuscarPorNome("Pocone").get(0);
        cidadeVO3 = this.subEndereco.cidadeBuscarPorNome("Campo Grande").get(0);
        
        BairroVO bairro01 = new BairroVO("Centro", cidadeVO1);
        retorno1 = this.subEndereco.bairroInserir(bairro01);
        assertTrue(retorno1.isValido());
        BairroVO bairro02 = new BairroVO("CPA", cidadeVO1);
        retorno1 = this.subEndereco.bairroInserir(bairro02);
        assertTrue(retorno1.isValido());
        BairroVO bairro03 = new BairroVO("Centro", cidadeVO2);
        retorno1 = this.subEndereco.bairroInserir(bairro03);
        assertTrue(retorno1.isValido());
        BairroVO bairro04 = new BairroVO("Centro", cidadeVO3);
        retorno1 = this.subEndereco.bairroInserir(bairro04);
        assertTrue(retorno1.isValido());
        
        
        System.out.println("----------------bairroAlterar--------------------");
        bairro01 = this.subEndereco.bairroBuscarPorCidade(cidadeVO2).get(0);
        bairro01.setNome("Centro Pocone");
        retorno1 = this.subEndereco.bairroAlterar(bairro01);
        System.out.println(retorno1.getReturnoMsg());
        bairro01 = this.subEndereco.bairroBuscarPorCidade(cidadeVO2).get(0);
        assertEquals(bairro01.getNome(), "Centro Pocone");

        
        System.out.println("----------------bairroBuscar--------------------");
        bairro01 = this.subEndereco.bairroBuscarPorCidade(cidadeVO2).get(0);
        bairro02 = this.subEndereco.bairroBuscarPorID(bairro01.getId());
        System.out.println(bairro02);
        assertEquals(bairro01, bairro02);
        List<BairroVO> listaBairroVO = this.subEndereco.bairroBuscarPorCidade(cidadeVO1);
        assertEquals(listaBairroVO.size(), 2);

        System.out.println("----------------cidadeExcluir--------------------");
        bairro01 = this.subEndereco.bairroBuscarPorCidade(cidadeVO1).get(0);
        bairro02 = this.subEndereco.bairroBuscarPorCidade(cidadeVO2).get(0);
        this.subEndereco.bairroExcluir(bairro01);
        this.subEndereco.bairroExcluir(bairro02);

        bairro01 = this.subEndereco.bairroBuscarPorID(bairro01.getId());
        bairro02 = this.subEndereco.bairroBuscarPorID(bairro02.getId());
        assertNull(bairro01);
        assertNull(bairro02);
    }
}
