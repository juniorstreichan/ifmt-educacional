package sscurso.persistencia;

import base.persistencia.DAO;
import base.persistencia.PersistenciaLog;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import sscurso.vo.CursoVO;
import ssdepartamento.vo.DepartamentoVO;

/**
 *
 * @author junior
 */
public class CursoDAO extends DAO<CursoVO> {

    public CursoDAO(EntityManager entityManager) {
        super(entityManager);
    }

    public Collection<CursoVO> buscaPorDepartamento(DepartamentoVO departamento) {
        try {
            Query qr = this.entityManager.createQuery("SELECT c FROM CursoVO c WHERE c.departamento.id = :id OR c.departamento.nome LIKE :nome");
            qr.setParameter("id", departamento.getId());
            qr.setParameter("nome", departamento.getNome());

            return qr.getResultList();
        } catch (PersistenceException ex) {
            PersistenciaLog.registrar(ex.getMessage(), ex);
            return null;
        }
    }

}
