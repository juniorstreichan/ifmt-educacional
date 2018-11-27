package base.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FabricaEntityManager {

    private EntityManagerFactory emf;
    private static FabricaEntityManager instance;

    private FabricaEntityManager(String unitName) {
        try {
            this.emf = Persistence.createEntityManagerFactory(unitName);
        } catch (Exception ex) {
            PersistenciaLog.registrar(ex.getMessage(), ex);
        }
    }

    public static FabricaEntityManager getInstancia(String unidadePersistencia){
        if (FabricaEntityManager.instance == null) {
            try {
                FabricaEntityManager.instance = new FabricaEntityManager(unidadePersistencia);
            } catch (Exception ex) {
                PersistenciaLog.registrar(ex.getMessage(), ex);
            }
        }
        return FabricaEntityManager.instance;
    }

    public EntityManager getEntityManager() {
        EntityManager entityManager = null;
        try {
            entityManager = emf.createEntityManager();
        } catch (Exception ex) {
            PersistenciaLog.registrar(ex.getMessage(), ex);
        }
        return entityManager;
    }
}
