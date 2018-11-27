package sspessoa.persistencia;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import base.persistencia.DAO;
import base.persistencia.PersistenciaLog;
import sspessoa.vo.PessoaFisicaVO;

public class PessoaFisicaDAO extends DAO<PessoaFisicaVO> {

    public PessoaFisicaDAO(EntityManager entityManager) {
        super(entityManager);
    }

    public PessoaFisicaVO buscarPorID(Long id) {
        try {
            return this.entityManager.find(PessoaFisicaVO.class, id);
        } catch (PersistenceException ex) {
            PersistenciaLog.registrar(ex.getMessage(), ex);
            return null;
        }
    }

    public List<PessoaFisicaVO> buscarPorNome(String nome) {
        List<PessoaFisicaVO> resultado = new ArrayList();
        try {
            Query query = this.entityManager.createQuery("SELECT pf FROM PessoaFisicaVO pf WHERE UPPER(pf.nome) LIKE :nome");
            query.setParameter("nome", nome.toUpperCase() + "%");
            resultado.addAll(query.getResultList());
        } catch (PersistenceException ex) {
            PersistenciaLog.registrar(ex.getMessage(), ex);
        }
        return resultado;
    }

    public PessoaFisicaVO buscarPorCPF(String cpf) {
        PessoaFisicaVO resultado = null;
        try {
            Query query = this.entityManager.createQuery("SELECT p FROM PessoaFisicaVO p WHERE p.CPF = :cpf");
            query.setParameter("cpf", cpf);
            List<PessoaFisicaVO> listaTemp = query.getResultList();
            if(listaTemp.size() > 0){
                resultado = listaTemp.get(0);
            }
        } catch (PersistenceException ex) {
            PersistenciaLog.registrar(ex.getMessage(), ex);
        }
        return resultado;
    }
}
