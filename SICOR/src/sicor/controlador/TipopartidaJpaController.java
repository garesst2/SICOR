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
import sicor.modelo.Partidas;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import sicor.controlador.exceptions.NonexistentEntityException;
import sicor.modelo.Tipopartida;

/**
 *
 * @author gares
 */
public class TipopartidaJpaController implements Serializable {

    public TipopartidaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tipopartida tipopartida) {
        if (tipopartida.getPartidasList() == null) {
            tipopartida.setPartidasList(new ArrayList<Partidas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Partidas> attachedPartidasList = new ArrayList<Partidas>();
            for (Partidas partidasListPartidasToAttach : tipopartida.getPartidasList()) {
                partidasListPartidasToAttach = em.getReference(partidasListPartidasToAttach.getClass(), partidasListPartidasToAttach.getIdPartida());
                attachedPartidasList.add(partidasListPartidasToAttach);
            }
            tipopartida.setPartidasList(attachedPartidasList);
            em.persist(tipopartida);
            for (Partidas partidasListPartidas : tipopartida.getPartidasList()) {
                Tipopartida oldIdTipoPartidaOfPartidasListPartidas = partidasListPartidas.getIdTipoPartida();
                partidasListPartidas.setIdTipoPartida(tipopartida);
                partidasListPartidas = em.merge(partidasListPartidas);
                if (oldIdTipoPartidaOfPartidasListPartidas != null) {
                    oldIdTipoPartidaOfPartidasListPartidas.getPartidasList().remove(partidasListPartidas);
                    oldIdTipoPartidaOfPartidasListPartidas = em.merge(oldIdTipoPartidaOfPartidasListPartidas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tipopartida tipopartida) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipopartida persistentTipopartida = em.find(Tipopartida.class, tipopartida.getIdTipoPartida());
            List<Partidas> partidasListOld = persistentTipopartida.getPartidasList();
            List<Partidas> partidasListNew = tipopartida.getPartidasList();
            List<Partidas> attachedPartidasListNew = new ArrayList<Partidas>();
            for (Partidas partidasListNewPartidasToAttach : partidasListNew) {
                partidasListNewPartidasToAttach = em.getReference(partidasListNewPartidasToAttach.getClass(), partidasListNewPartidasToAttach.getIdPartida());
                attachedPartidasListNew.add(partidasListNewPartidasToAttach);
            }
            partidasListNew = attachedPartidasListNew;
            tipopartida.setPartidasList(partidasListNew);
            tipopartida = em.merge(tipopartida);
            for (Partidas partidasListOldPartidas : partidasListOld) {
                if (!partidasListNew.contains(partidasListOldPartidas)) {
                    partidasListOldPartidas.setIdTipoPartida(null);
                    partidasListOldPartidas = em.merge(partidasListOldPartidas);
                }
            }
            for (Partidas partidasListNewPartidas : partidasListNew) {
                if (!partidasListOld.contains(partidasListNewPartidas)) {
                    Tipopartida oldIdTipoPartidaOfPartidasListNewPartidas = partidasListNewPartidas.getIdTipoPartida();
                    partidasListNewPartidas.setIdTipoPartida(tipopartida);
                    partidasListNewPartidas = em.merge(partidasListNewPartidas);
                    if (oldIdTipoPartidaOfPartidasListNewPartidas != null && !oldIdTipoPartidaOfPartidasListNewPartidas.equals(tipopartida)) {
                        oldIdTipoPartidaOfPartidasListNewPartidas.getPartidasList().remove(partidasListNewPartidas);
                        oldIdTipoPartidaOfPartidasListNewPartidas = em.merge(oldIdTipoPartidaOfPartidasListNewPartidas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipopartida.getIdTipoPartida();
                if (findTipopartida(id) == null) {
                    throw new NonexistentEntityException("The tipopartida with id " + id + " no longer exists.");
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
            Tipopartida tipopartida;
            try {
                tipopartida = em.getReference(Tipopartida.class, id);
                tipopartida.getIdTipoPartida();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipopartida with id " + id + " no longer exists.", enfe);
            }
            List<Partidas> partidasList = tipopartida.getPartidasList();
            for (Partidas partidasListPartidas : partidasList) {
                partidasListPartidas.setIdTipoPartida(null);
                partidasListPartidas = em.merge(partidasListPartidas);
            }
            em.remove(tipopartida);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tipopartida> findTipopartidaEntities() {
        return findTipopartidaEntities(true, -1, -1);
    }

    public List<Tipopartida> findTipopartidaEntities(int maxResults, int firstResult) {
        return findTipopartidaEntities(false, maxResults, firstResult);
    }

    private List<Tipopartida> findTipopartidaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tipopartida.class));
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

    public Tipopartida findTipopartida(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tipopartida.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipopartidaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tipopartida> rt = cq.from(Tipopartida.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
