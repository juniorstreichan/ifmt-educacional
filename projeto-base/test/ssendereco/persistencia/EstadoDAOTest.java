package ssendereco.persistencia;

import java.util.List;
import javax.persistence.Query;
import org.junit.Test;
import base.persistencia.FabricaEntityManager;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import ssendereco.vo.EstadoVO;

public class EstadoDAOTest {

    private final EstadoDAO estadoDAO;

    public EstadoDAOTest() {
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
        assertNotNull(estadoVO1);
        assertNotNull(estadoVO2);
        System.out.println("-----------------Inclusao---------------------");

        //-----------------Alteracao---------------------
        this.estadoDAO.startTransaction();
        estadoVO2 = this.estadoDAO.buscarPorUF("MS");
        estadoVO2.setNome("Mato Grosso do Sul");
        this.estadoDAO.alterar(estadoVO2);
        this.estadoDAO.commitTransaction();
        estadoVO2 = this.estadoDAO.buscarPorUF("MS");
        assertEquals(estadoVO2.getNome(), "Mato Grosso do Sul");
        System.out.println("-----------------Alteracao---------------------");

        //-----------------Buscas---------------------
        this.estadoDAO.startTransaction();
        estadoVO1 = this.estadoDAO.buscarPorUF("MS");
        estadoVO2 = this.estadoDAO.buscarPorID(estadoVO1.getId());
        assertEquals(estadoVO1, estadoVO2);
        List<EstadoVO> listaVO = this.estadoDAO.buscarTodos();
        assertEquals(listaVO.size(), 2);
        this.estadoDAO.commitTransaction();
        System.out.println("-----------------Buscas---------------------");

        //-----------------Exclusao---------------------
        this.estadoDAO.startTransaction();
        estadoVO1 = this.estadoDAO.buscarPorUF("MT");
        estadoVO2 = this.estadoDAO.buscarPorUF("MS");
        this.estadoDAO.excluir(estadoVO1);
        this.estadoDAO.excluir(estadoVO2);
        this.estadoDAO.commitTransaction();
        estadoVO1 = this.estadoDAO.buscarPorUF("MT");
        estadoVO2 = this.estadoDAO.buscarPorUF("MS");
        assertNull(estadoVO1);
        assertNull(estadoVO2);
        System.out.println("-----------------Exclusao---------------------");
    }
}
