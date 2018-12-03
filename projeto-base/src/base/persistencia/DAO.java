package base.persistencia;

import javax.persistence.EntityManager;

public class DAO<VO> {

    protected EntityManager entityManager;
   
    public DAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public boolean inserir(VO vo) {
        boolean retrunValue = false;
        try {
            this.entityManager.persist(vo);
            retrunValue = true;
        } catch (Exception ex) {
            PersistenciaLog.registrar(ex.getMessage(), ex);
        }
        return retrunValue;
    }

    public boolean alterar(VO vo) {
        boolean returnValue = false;
        try {
            this.entityManager.merge(vo);
            returnValue = true;
        } catch (Exception ex) {
            PersistenciaLog.registrar(ex.getMessage(), ex);
        }
        return returnValue;
    }

    public boolean excluir(VO vo) {
        boolean returnValue = false;
        try {
            vo = this.entityManager.merge(vo);
            this.entityManager.remove(vo);
            returnValue = true;
        } catch (Exception ex) {
            PersistenciaLog.registrar(ex.getMessage(), ex);
        }
        return returnValue;
    }

    public boolean startTransaction() {
        boolean returnValue = false;
        try {
            this.entityManager.getTransaction().begin();
            returnValue = true;
        } catch (Exception ex) {
            PersistenciaLog.registrar(ex.getMessage(), ex);
        }
        return returnValue;
    }

    public boolean commitTransaction() {
        boolean returnValue = false;
        try {
            this.entityManager.getTransaction().commit();
            returnValue = true;
        } catch (Exception ex) {
            PersistenciaLog.registrar(ex.getMessage(), ex);
        }
        return returnValue;
    }

    public boolean rollbackTransaction() {
        boolean returnValue = false;
        try {
            this.entityManager.getTransaction().rollback();
            returnValue = true;
        } catch (Exception ex) {
            PersistenciaLog.registrar(ex.getMessage(), ex);
        }
        return returnValue;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
}
