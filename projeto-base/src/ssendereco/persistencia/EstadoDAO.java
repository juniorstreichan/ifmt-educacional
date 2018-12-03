package ssendereco.persistencia;

import base.persistencia.PersistenciaLog;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import base.persistencia.DAO;
import ssendereco.vo.EstadoVO;

public class EstadoDAO extends DAO<EstadoVO> {

    public EstadoDAO(EntityManager entityManager) {
        super(entityManager);
    }

    public EstadoVO buscarPorID(Long id) {

        try {
            return this.entityManager.find(EstadoVO.class, id);
        } catch (PersistenceException ex) {
            PersistenciaLog.registrar(ex.getMessage(), ex);
            return null;
        }
    }

    public EstadoVO buscarPorUF(String sigla) {
        EstadoVO resultado = null;
        try {
            Query query = this.entityManager.createQuery("SELECT e FROM EstadoVO e WHERE e.sigla = :sigla ORDER BY e.nome");
            query.setParameter("sigla", sigla);
            List<EstadoVO> listaTemp = query.getResultList();
            if(listaTemp.size() > 0){
                resultado = listaTemp.get(0);
            }
        } catch (PersistenceException ex) {
            PersistenciaLog.registrar(ex.getMessage(), ex);
        }
        return resultado;
    }
    
    public List<EstadoVO> buscarPorNome(String nome){
        List<EstadoVO> resultado = new ArrayList();
        try {
            Query query = this.entityManager.createQuery("SELECT e FROM EstadoVO e WHERE e.nome LIKE :nome ORDER BY e.nome");
            query.setParameter("nome", nome + "%");
            resultado.addAll(query.getResultList());
        } catch (PersistenceException ex) {
            PersistenciaLog.registrar(ex.getMessage(), ex);
        }
        return resultado;
    }
    

    public List<EstadoVO> buscarTodos() {
        List<EstadoVO> resultado = new ArrayList();
        try {
            Query query = this.entityManager.createQuery("SELECT e FROM EstadoVO e ORDER BY e.nome");
            resultado.addAll(query.getResultList());
        } catch (PersistenceException ex) {
            PersistenciaLog.registrar(ex.getMessage(), ex);
        }
        return resultado;
    }
}
