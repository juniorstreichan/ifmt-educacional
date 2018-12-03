package ssendereco.persistencia;
import base.persistencia.PersistenciaLog;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import base.persistencia.DAO;
import ssendereco.vo.BairroVO;
import ssendereco.vo.MunicipioVO;
public class BairroDAO extends DAO<BairroVO> {

    public BairroDAO(EntityManager entityManager) {
        super(entityManager);
    }

    public BairroVO buscarPorID(Long id) {
        try {
            return this.entityManager.find(BairroVO.class, id);
        } catch (PersistenceException ex) {
            PersistenciaLog.registrar(ex.getMessage(), ex);
            return null;
        }
    }

    public List<BairroVO> buscarPorNome(String nome) {
        List<BairroVO> resultado = new ArrayList();
        try {
            Query query = this.entityManager.createQuery("SELECT b FROM BairroVO b WHERE b.nome LIKE :nome");
            query.setParameter("nome", nome + "%");
            resultado.addAll(query.getResultList());
        } catch (PersistenceException ex) {
            PersistenciaLog.registrar(ex.getMessage(), ex);
        }
        return resultado;
    }

    public List<BairroVO> buscarPorCidade(MunicipioVO municipio) {
        List<BairroVO> resultado = new ArrayList();
        try {
            Query query = this.entityManager.createQuery("SELECT b FROM BairroVO b WHERE b.municipio.id = :id");
            query.setParameter("id", municipio.getId());
            resultado.addAll(query.getResultList());
        } catch (PersistenceException ex) {
            PersistenciaLog.registrar(ex.getMessage(), ex);
        }
        return resultado;
    }
}
