/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sicor.controlador;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import sicor.modelo.Empresas;
import sicor.modelo.Cheque;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import sicor.controlador.exceptions.NonexistentEntityException;
import sicor.modelo.Banco;

/**
 *
 * @author gares
 */
public class BancoJpaController implements Serializable {

    public BancoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Banco banco) {
        if (banco.getChequeList() == null) {
            banco.setChequeList(new ArrayList<Cheque>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empresas idEmpresa = banco.getIdEmpresa();
            if (idEmpresa != null) {
                idEmpresa = em.getReference(idEmpresa.getClass(), idEmpresa.getIdEmpresa());
                banco.setIdEmpresa(idEmpresa);
            }
            List<Cheque> attachedChequeList = new ArrayList<Cheque>();
            for (Cheque chequeListChequeToAttach : banco.getChequeList()) {
                chequeListChequeToAttach = em.getReference(chequeListChequeToAttach.getClass(), chequeListChequeToAttach.getIdCheque());
                attachedChequeList.add(chequeListChequeToAttach);
            }
            banco.setChequeList(attachedChequeList);
            em.persist(banco);
            if (idEmpresa != null) {
                idEmpresa.getBancoList().add(banco);
                idEmpresa = em.merge(idEmpresa);
            }
            for (Cheque chequeListCheque : banco.getChequeList()) {
                Banco oldIdBancoOfChequeListCheque = chequeListCheque.getIdBanco();
                chequeListCheque.setIdBanco(banco);
                chequeListCheque = em.merge(chequeListCheque);
                if (oldIdBancoOfChequeListCheque != null) {
                    oldIdBancoOfChequeListCheque.getChequeList().remove(chequeListCheque);
                    oldIdBancoOfChequeListCheque = em.merge(oldIdBancoOfChequeListCheque);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Banco banco) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Banco persistentBanco = em.find(Banco.class, banco.getIdBanco());
            Empresas idEmpresaOld = persistentBanco.getIdEmpresa();
            Empresas idEmpresaNew = banco.getIdEmpresa();
            List<Cheque> chequeListOld = persistentBanco.getChequeList();
            List<Cheque> chequeListNew = banco.getChequeList();
            if (idEmpresaNew != null) {
                idEmpresaNew = em.getReference(idEmpresaNew.getClass(), idEmpresaNew.getIdEmpresa());
                banco.setIdEmpresa(idEmpresaNew);
            }
            List<Cheque> attachedChequeListNew = new ArrayList<Cheque>();
            for (Cheque chequeListNewChequeToAttach : chequeListNew) {
                chequeListNewChequeToAttach = em.getReference(chequeListNewChequeToAttach.getClass(), chequeListNewChequeToAttach.getIdCheque());
                attachedChequeListNew.add(chequeListNewChequeToAttach);
            }
            chequeListNew = attachedChequeListNew;
            banco.setChequeList(chequeListNew);
            banco = em.merge(banco);
            if (idEmpresaOld != null && !idEmpresaOld.equals(idEmpresaNew)) {
                idEmpresaOld.getBancoList().remove(banco);
                idEmpresaOld = em.merge(idEmpresaOld);
            }
            if (idEmpresaNew != null && !idEmpresaNew.equals(idEmpresaOld)) {
                idEmpresaNew.getBancoList().add(banco);
                idEmpresaNew = em.merge(idEmpresaNew);
            }
            for (Cheque chequeListOldCheque : chequeListOld) {
                if (!chequeListNew.contains(chequeListOldCheque)) {
                    chequeListOldCheque.setIdBanco(null);
                    chequeListOldCheque = em.merge(chequeListOldCheque);
                }
            }
            for (Cheque chequeListNewCheque : chequeListNew) {
                if (!chequeListOld.contains(chequeListNewCheque)) {
                    Banco oldIdBancoOfChequeListNewCheque = chequeListNewCheque.getIdBanco();
                    chequeListNewCheque.setIdBanco(banco);
                    chequeListNewCheque = em.merge(chequeListNewCheque);
                    if (oldIdBancoOfChequeListNewCheque != null && !oldIdBancoOfChequeListNewCheque.equals(banco)) {
                        oldIdBancoOfChequeListNewCheque.getChequeList().remove(chequeListNewCheque);
                        oldIdBancoOfChequeListNewCheque = em.merge(oldIdBancoOfChequeListNewCheque);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = banco.getIdBanco();
                if (findBanco(id) == null) {
                    throw new NonexistentEntityException("The banco with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Banco banco;
            try {
                banco = em.getReference(Banco.class, id);
                banco.getIdBanco();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The banco with id " + id + " no longer exists.", enfe);
            }
            Empresas idEmpresa = banco.getIdEmpresa();
            if (idEmpresa != null) {
                idEmpresa.getBancoList().remove(banco);
                idEmpresa = em.merge(idEmpresa);
            }
            List<Cheque> chequeList = banco.getChequeList();
            for (Cheque chequeListCheque : chequeList) {
                chequeListCheque.setIdBanco(null);
                chequeListCheque = em.merge(chequeListCheque);
            }
            em.remove(banco);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Banco> findBancoEntities() {
        return findBancoEntities(true, -1, -1);
    }

    public List<Banco> findBancoEntities(int maxResults, int firstResult) {
        return findBancoEntities(false, maxResults, firstResult);
    }

    private List<Banco> findBancoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Banco.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Banco findBanco(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Banco.class, id);
        } finally {
            em.close();
        }
    }

    public int getBancoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Banco> rt = cq.from(Banco.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
