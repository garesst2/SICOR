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
import sicor.modelo.Partidas;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import sicor.controlador.exceptions.NonexistentEntityException;
import sicor.modelo.Diario;

/**
 *
 * @author gares
 */
public class DiarioJpaController implements Serializable {

    public DiarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Diario diario) {
        if (diario.getPartidasList() == null) {
            diario.setPartidasList(new ArrayList<Partidas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empresas idEmpresa = diario.getIdEmpresa();
            if (idEmpresa != null) {
                idEmpresa = em.getReference(idEmpresa.getClass(), idEmpresa.getIdEmpresa());
                diario.setIdEmpresa(idEmpresa);
            }
            List<Partidas> attachedPartidasList = new ArrayList<Partidas>();
            for (Partidas partidasListPartidasToAttach : diario.getPartidasList()) {
                partidasListPartidasToAttach = em.getReference(partidasListPartidasToAttach.getClass(), partidasListPartidasToAttach.getIdPartida());
                attachedPartidasList.add(partidasListPartidasToAttach);
            }
            diario.setPartidasList(attachedPartidasList);
            em.persist(diario);
            if (idEmpresa != null) {
                idEmpresa.getDiarioList().add(diario);
                idEmpresa = em.merge(idEmpresa);
            }
            for (Partidas partidasListPartidas : diario.getPartidasList()) {
                Diario oldIdDiarioOfPartidasListPartidas = partidasListPartidas.getIdDiario();
                partidasListPartidas.setIdDiario(diario);
                partidasListPartidas = em.merge(partidasListPartidas);
                if (oldIdDiarioOfPartidasListPartidas != null) {
                    oldIdDiarioOfPartidasListPartidas.getPartidasList().remove(partidasListPartidas);
                    oldIdDiarioOfPartidasListPartidas = em.merge(oldIdDiarioOfPartidasListPartidas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Diario diario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Diario persistentDiario = em.find(Diario.class, diario.getIdDiario());
            Empresas idEmpresaOld = persistentDiario.getIdEmpresa();
            Empresas idEmpresaNew = diario.getIdEmpresa();
            List<Partidas> partidasListOld = persistentDiario.getPartidasList();
            List<Partidas> partidasListNew = diario.getPartidasList();
            if (idEmpresaNew != null) {
                idEmpresaNew = em.getReference(idEmpresaNew.getClass(), idEmpresaNew.getIdEmpresa());
                diario.setIdEmpresa(idEmpresaNew);
            }
            List<Partidas> attachedPartidasListNew = new ArrayList<Partidas>();
            for (Partidas partidasListNewPartidasToAttach : partidasListNew) {
                partidasListNewPartidasToAttach = em.getReference(partidasListNewPartidasToAttach.getClass(), partidasListNewPartidasToAttach.getIdPartida());
                attachedPartidasListNew.add(partidasListNewPartidasToAttach);
            }
            partidasListNew = attachedPartidasListNew;
            diario.setPartidasList(partidasListNew);
            diario = em.merge(diario);
            if (idEmpresaOld != null && !idEmpresaOld.equals(idEmpresaNew)) {
                idEmpresaOld.getDiarioList().remove(diario);
                idEmpresaOld = em.merge(idEmpresaOld);
            }
            if (idEmpresaNew != null && !idEmpresaNew.equals(idEmpresaOld)) {
                idEmpresaNew.getDiarioList().add(diario);
                idEmpresaNew = em.merge(idEmpresaNew);
            }
            for (Partidas partidasListOldPartidas : partidasListOld) {
                if (!partidasListNew.contains(partidasListOldPartidas)) {
                    partidasListOldPartidas.setIdDiario(null);
                    partidasListOldPartidas = em.merge(partidasListOldPartidas);
                }
            }
            for (Partidas partidasListNewPartidas : partidasListNew) {
                if (!partidasListOld.contains(partidasListNewPartidas)) {
                    Diario oldIdDiarioOfPartidasListNewPartidas = partidasListNewPartidas.getIdDiario();
                    partidasListNewPartidas.setIdDiario(diario);
                    partidasListNewPartidas = em.merge(partidasListNewPartidas);
                    if (oldIdDiarioOfPartidasListNewPartidas != null && !oldIdDiarioOfPartidasListNewPartidas.equals(diario)) {
                        oldIdDiarioOfPartidasListNewPartidas.getPartidasList().remove(partidasListNewPartidas);
                        oldIdDiarioOfPartidasListNewPartidas = em.merge(oldIdDiarioOfPartidasListNewPartidas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = diario.getIdDiario();
                if (findDiario(id) == null) {
                    throw new NonexistentEntityException("The diario with id " + id + " no longer exists.");
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
            Diario diario;
            try {
                diario = em.getReference(Diario.class, id);
                diario.getIdDiario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The diario with id " + id + " no longer exists.", enfe);
            }
            Empresas idEmpresa = diario.getIdEmpresa();
            if (idEmpresa != null) {
                idEmpresa.getDiarioList().remove(diario);
                idEmpresa = em.merge(idEmpresa);
            }
            List<Partidas> partidasList = diario.getPartidasList();
            for (Partidas partidasListPartidas : partidasList) {
                partidasListPartidas.setIdDiario(null);
                partidasListPartidas = em.merge(partidasListPartidas);
            }
            em.remove(diario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Diario> findDiarioEntities() {
        return findDiarioEntities(true, -1, -1);
    }

    public List<Diario> findDiarioEntities(int maxResults, int firstResult) {
        return findDiarioEntities(false, maxResults, firstResult);
    }

    private List<Diario> findDiarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Diario.class));
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

    public Diario findDiario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Diario.class, id);
        } finally {
            em.close();
        }
    }

    public int getDiarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Diario> rt = cq.from(Diario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
