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
import sicor.modelo.Partidas;
import sicor.modelo.Cuentas;
import sicor.modelo.Detallepartida;

/**
 *
 * @author gares
 */
public class DetallepartidaJpaController implements Serializable {

    public DetallepartidaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detallepartida detallepartida) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Partidas idPartida = detallepartida.getIdPartida();
            if (idPartida != null) {
                idPartida = em.getReference(idPartida.getClass(), idPartida.getIdPartida());
                detallepartida.setIdPartida(idPartida);
            }
            Cuentas idCuenta = detallepartida.getIdCuenta();
            if (idCuenta != null) {
                idCuenta = em.getReference(idCuenta.getClass(), idCuenta.getIdCuenta());
                detallepartida.setIdCuenta(idCuenta);
            }
            em.persist(detallepartida);
            if (idPartida != null) {
                idPartida.getDetallepartidaList().add(detallepartida);
                idPartida = em.merge(idPartida);
            }
            if (idCuenta != null) {
                idCuenta.getDetallepartidaList().add(detallepartida);
                idCuenta = em.merge(idCuenta);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detallepartida detallepartida) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallepartida persistentDetallepartida = em.find(Detallepartida.class, detallepartida.getIdDetallePartida());
            Partidas idPartidaOld = persistentDetallepartida.getIdPartida();
            Partidas idPartidaNew = detallepartida.getIdPartida();
            Cuentas idCuentaOld = persistentDetallepartida.getIdCuenta();
            Cuentas idCuentaNew = detallepartida.getIdCuenta();
            if (idPartidaNew != null) {
                idPartidaNew = em.getReference(idPartidaNew.getClass(), idPartidaNew.getIdPartida());
                detallepartida.setIdPartida(idPartidaNew);
            }
            if (idCuentaNew != null) {
                idCuentaNew = em.getReference(idCuentaNew.getClass(), idCuentaNew.getIdCuenta());
                detallepartida.setIdCuenta(idCuentaNew);
            }
            detallepartida = em.merge(detallepartida);
            if (idPartidaOld != null && !idPartidaOld.equals(idPartidaNew)) {
                idPartidaOld.getDetallepartidaList().remove(detallepartida);
                idPartidaOld = em.merge(idPartidaOld);
            }
            if (idPartidaNew != null && !idPartidaNew.equals(idPartidaOld)) {
                idPartidaNew.getDetallepartidaList().add(detallepartida);
                idPartidaNew = em.merge(idPartidaNew);
            }
            if (idCuentaOld != null && !idCuentaOld.equals(idCuentaNew)) {
                idCuentaOld.getDetallepartidaList().remove(detallepartida);
                idCuentaOld = em.merge(idCuentaOld);
            }
            if (idCuentaNew != null && !idCuentaNew.equals(idCuentaOld)) {
                idCuentaNew.getDetallepartidaList().add(detallepartida);
                idCuentaNew = em.merge(idCuentaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = detallepartida.getIdDetallePartida();
                if (findDetallepartida(id) == null) {
                    throw new NonexistentEntityException("The detallepartida with id " + id + " no longer exists.");
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
            Detallepartida detallepartida;
            try {
                detallepartida = em.getReference(Detallepartida.class, id);
                detallepartida.getIdDetallePartida();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detallepartida with id " + id + " no longer exists.", enfe);
            }
            Partidas idPartida = detallepartida.getIdPartida();
            if (idPartida != null) {
                idPartida.getDetallepartidaList().remove(detallepartida);
                idPartida = em.merge(idPartida);
            }
            Cuentas idCuenta = detallepartida.getIdCuenta();
            if (idCuenta != null) {
                idCuenta.getDetallepartidaList().remove(detallepartida);
                idCuenta = em.merge(idCuenta);
            }
            em.remove(detallepartida);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detallepartida> findDetallepartidaEntities() {
        return findDetallepartidaEntities(true, -1, -1);
    }

    public List<Detallepartida> findDetallepartidaEntities(int maxResults, int firstResult) {
        return findDetallepartidaEntities(false, maxResults, firstResult);
    }

    private List<Detallepartida> findDetallepartidaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detallepartida.class));
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

    public Detallepartida findDetallepartida(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detallepartida.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetallepartidaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detallepartida> rt = cq.from(Detallepartida.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
