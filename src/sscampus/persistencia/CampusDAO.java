package sscampus.persistencia;

import base.persistencia.DAO;
import base.persistencia.PersistenciaLog;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import sscampus.vo.CampusVO;

/**
 *
 * @author junior
 */
public class CampusDAO extends DAO<CampusVO> {

    public CampusDAO(EntityManager entityManager) {
        super(entityManager);
    }

    public CampusVO buscaPorId(Long id) {
        try {
            return this.entityManager.find(CampusVO.class, id);
        } catch (PersistenceException ex) {
            PersistenciaLog.registrar(ex.getMessage(), ex);
            return null;

        }
    }

    public Collection<CampusVO> buscarPorNome(String nome) {
        try {
            Query qr = this.entityManager.createQuery("SELECT c FROM CampusVO c WHERE c.nome LIKE :nome");

            qr.setParameter("nome", nome);

            return qr.getResultList();

        } catch (PersistenceException ex) {
            PersistenciaLog.registrar(ex.getMessage(), ex);
            return null;

        }
    }
}
