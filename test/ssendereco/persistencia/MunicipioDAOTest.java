package ssendereco.persistencia;

import java.util.List;
import org.junit.Test;
import base.persistencia.FabricaEntityManager;
import javax.persistence.Query;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import ssendereco.vo.MunicipioVO;
import ssendereco.vo.EstadoVO;

public class MunicipioDAOTest {

    private final MunicipioDAO cidadeDAO;
    private final EstadoDAO estadoDAO;

    public MunicipioDAOTest() {
        this.cidadeDAO = new MunicipioDAO(FabricaEntityManager.getInstancia("UNIDADETESTE").getEntityManager());
        this.estadoDAO = new EstadoDAO(FabricaEntityManager.getInstancia("UNIDADETESTE").getEntityManager());

        //excluir os registros do subsistema de endereco
        this.estadoDAO.startTransaction();
        Query query = this.estadoDAO.getEntityManager().createNativeQuery("Delete from bairro");
        query.executeUpdate();
        query = this.estadoDAO.getEntityManager().createNativeQuery("Delete from municipio");
        query.executeUpdate();
        query = this.estadoDAO.getEntityManager().createNativeQuery("Delete from estado");
        query.executeUpdate();
        this.estadoDAO.commitTransaction();
    }

    @Test
    public void testCRUDF() {
        
        //-----------------Inclusao---------------------
        EstadoVO estadoVO1 = new EstadoVO("MT", "Mato Grosso");
        EstadoVO estadoVO2 = new EstadoVO("MS", "Mato Grosso");
        this.estadoDAO.startTransaction();
        this.estadoDAO.inserir(estadoVO1);
        this.estadoDAO.inserir(estadoVO2);
        this.estadoDAO.commitTransaction();
        
        estadoVO1 = this.estadoDAO.buscarPorUF("MT");
        estadoVO2 = this.estadoDAO.buscarPorUF("MS");
        MunicipioVO cidadeVO1 = new MunicipioVO("Cuiaba", estadoVO1);
        MunicipioVO cidadeVO2 = new MunicipioVO("Pocone", estadoVO1);
        MunicipioVO cidadeVO3 = new MunicipioVO("Campo Grande", estadoVO2);
        
        this.cidadeDAO.startTransaction();
        this.cidadeDAO.inserir(cidadeVO1);
        this.cidadeDAO.inserir(cidadeVO2);
        this.cidadeDAO.inserir(cidadeVO3);
        this.cidadeDAO.commitTransaction();

        List<MunicipioVO> listaCidadeVO = this.cidadeDAO.buscarPorNome("Cuiaba");
        assertEquals(listaCidadeVO.get(0), cidadeVO1);
        listaCidadeVO = this.cidadeDAO.buscarPorNome("Pocone");
        assertEquals(listaCidadeVO.get(0), cidadeVO2);
        listaCidadeVO = this.cidadeDAO.buscarPorNome("Campo Grande");
        assertEquals(listaCidadeVO.get(0), cidadeVO3);
        System.out.println("-----------------Inclusao---------------------");
        
        //-----------------Alteracao---------------------
        this.cidadeDAO.startTransaction();
        cidadeVO1 = this.cidadeDAO.buscarPorNome("Campo Grande").get(0);
        cidadeVO1.setNome("Varzea Grande");
        this.cidadeDAO.alterar(cidadeVO1);
        this.cidadeDAO.commitTransaction();
        listaCidadeVO = this.cidadeDAO.buscarPorNome("Varzea Grande");
        assertEquals(listaCidadeVO.size(), 1);
        System.out.println("-----------------Alteracao---------------------");

        //-----------------Buscas---------------------
        this.cidadeDAO.startTransaction();
        cidadeVO1 = listaCidadeVO.get(0);
        cidadeVO2 = this.cidadeDAO.buscarPorID(cidadeVO1.getId());
        assertEquals(cidadeVO1, cidadeVO2);
        List<MunicipioVO> listaVO1 = this.cidadeDAO.buscarPorEstado(estadoVO1);
        assertEquals(listaVO1.size(), 2);
        this.cidadeDAO.commitTransaction();
        System.out.println("-----------------Buscas---------------------");

        //-----------------Exclusao---------------------
        this.cidadeDAO.startTransaction();
        cidadeVO1 = this.cidadeDAO.buscarPorNome("Pocone").get(0);
        cidadeVO2 = this.cidadeDAO.buscarPorNome("Varzea Grande").get(0);
        this.cidadeDAO.excluir(cidadeVO1);
        this.cidadeDAO.excluir(cidadeVO2);
        this.cidadeDAO.commitTransaction();
        
        cidadeVO1 = this.cidadeDAO.buscarPorID(cidadeVO1.getId());
        cidadeVO2 = this.cidadeDAO.buscarPorID(cidadeVO2.getId());
        assertNull(cidadeVO1);
        assertNull(cidadeVO2);
        System.out.println("-----------------Exclusao---------------------");
    }
}
