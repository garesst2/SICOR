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
import javax.swing.JOptionPane;
import sicor.exceptions.NonexistentEntityException;
import sicor.exceptions.PreexistingEntityException;
import sicor.modelo.Partidasdiarios;

/**
 *
 * @author Administrador
 */
public class PartidasdiariosJpaController implements Serializable {

    public PartidasdiariosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Partidasdiarios partidasdiarios) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(partidasdiarios);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPartidasdiarios(partidasdiarios.getIdPartida()) != null) {
                throw new PreexistingEntityException("Partidasdiarios " + partidasdiarios + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Partidasdiarios partidasdiarios) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            partidasdiarios = em.merge(partidasdiarios);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = partidasdiarios.getIdPartida();
                if (findPartidasdiarios(id) == null) {
                    throw new NonexistentEntityException("The partidasdiarios with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Partidasdiarios partidasdiarios;
            try {
                partidasdiarios = em.getReference(Partidasdiarios.class, id);
                partidasdiarios.getIdPartida();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The partidasdiarios with id " + id + " no longer exists.", enfe);
            }
            em.remove(partidasdiarios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Partidasdiarios> findPartidasdiariosEntities() {
        return findPartidasdiariosEntities(true, -1, -1);
    }

    public List<Partidasdiarios> findPartidasdiariosEntities(int maxResults, int firstResult) {
        return findPartidasdiariosEntities(false, maxResults, firstResult);
    }

    private List<Partidasdiarios> findPartidasdiariosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Partidasdiarios.class));
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

    public Partidasdiarios findPartidasdiarios(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Partidasdiarios.class, id);
        } finally {
            em.close();
        }
    }
    
    public List<Partidasdiarios> getDiarioPartidaByEmpresa(Object empresa, Object año, Object mes){
        List<Partidasdiarios> valor = null;
        EntityManager em = getEntityManager();
        try {
            valor =em.createQuery("SELECT p FROM Partidasdiarios p WHERE p.idDiario = :idDiario AND p.mes = :mes p.a\u00f1o = :a\u00f1o ",Partidasdiarios.class).setParameter("idEmpresa", empresa).setParameter("a\u00f1o", año).setParameter("mes", mes).getResultList();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error: "+e.toString());
        }
        return valor;
    }

    public int getPartidasdiariosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Partidasdiarios> rt = cq.from(Partidasdiarios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
