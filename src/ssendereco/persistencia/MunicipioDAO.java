package ssendereco.persistencia;

import base.persistencia.PersistenciaLog;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import base.persistencia.DAO;
import ssendereco.vo.MunicipioVO;
import ssendereco.vo.EstadoVO;

public class MunicipioDAO extends DAO<MunicipioVO> {

    public MunicipioDAO(EntityManager entityManager) {
        super(entityManager);
    }

    public MunicipioVO buscarPorID(Long id) {
        try {
            return this.entityManager.find(MunicipioVO.class, id);
        } catch (PersistenceException ex) {
            PersistenciaLog.registrar(ex.getMessage(), ex);
            return null;
        }
    }

    public List<MunicipioVO> buscarPorNome(String nome){
        List<MunicipioVO> resultado = new ArrayList();
        try {
            Query query = this.entityManager.createQuery("SELECT m FROM MunicipioVO m WHERE m.nome LIKE :nome");
            query.setParameter("nome", nome + "%");
            resultado.addAll(query.getResultList());
        } catch (PersistenceException ex) {
            PersistenciaLog.registrar(ex.getMessage(), ex);
        }
        return resultado;
    }

    public List<MunicipioVO> buscarPorEstado(EstadoVO estadoVO){
        List<MunicipioVO> resultado = new ArrayList();
        try {
            Query query = this.entityManager.createQuery("SELECT m FROM MunicipioVO m WHERE m.estado.id = :id");
            query.setParameter("id", estadoVO.getId());
            resultado.addAll(query.getResultList());
        } catch (PersistenceException ex) {
            PersistenciaLog.registrar(ex.getMessage(), ex);
        }
        return resultado;
    }
}
