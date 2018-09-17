/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sicor.controlador;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import sicor.controlador.exceptions.NonexistentEntityException;
import sicor.modelo.Tipocuenta;

/**
 *
 * @author gares
 */
public class TipocuentaJpaController implements Serializable {

    public TipocuentaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tipocuenta tipocuenta) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tipocuenta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tipocuenta tipocuenta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tipocuenta = em.merge(tipocuenta);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipocuenta.getIdTipoCuenta();
                if (findTipocuenta(id) == null) {
                    throw new NonexistentEntityException("The tipocuenta with id " + id + " no longer exists.");
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
            Tipocuenta tipocuenta;
            try {
                tipocuenta = em.getReference(Tipocuenta.class, id);
                tipocuenta.getIdTipoCuenta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipocuenta with id " + id + " no longer exists.", enfe);
            }
            em.remove(tipocuenta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tipocuenta> findTipocuentaEntities() {
        return findTipocuentaEntities(true, -1, -1);
    }

    public List<Tipocuenta> findTipocuentaEntities(int maxResults, int firstResult) {
        return findTipocuentaEntities(false, maxResults, firstResult);
    }

    private List<Tipocuenta> findTipocuentaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tipocuenta.class));
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

    public Tipocuenta findTipocuenta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tipocuenta.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipocuentaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tipocuenta> rt = cq.from(Tipocuenta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
