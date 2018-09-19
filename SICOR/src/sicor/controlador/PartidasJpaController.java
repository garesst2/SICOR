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
import sicor.modelo.Diario;
import sicor.modelo.Tipopartida;
import sicor.modelo.Detallepartida;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.swing.JOptionPane;
import sicor.controlador.exceptions.NonexistentEntityException;
import sicor.modelo.Cheque;
import sicor.modelo.Partidas;

/**
 *
 * @author gares
 */
public class PartidasJpaController implements Serializable {

    public PartidasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Partidas partidas) {
        if (partidas.getDetallepartidaList() == null) {
            partidas.setDetallepartidaList(new ArrayList<Detallepartida>());
        }
        if (partidas.getChequeList() == null) {
            partidas.setChequeList(new ArrayList<Cheque>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Diario idDiario = partidas.getIdDiario();
            if (idDiario != null) {
                idDiario = em.getReference(idDiario.getClass(), idDiario.getIdDiario());
                partidas.setIdDiario(idDiario);
            }
            Tipopartida idTipoPartida = partidas.getIdTipoPartida();
            if (idTipoPartida != null) {
                idTipoPartida = em.getReference(idTipoPartida.getClass(), idTipoPartida.getIdTipoPartida());
                partidas.setIdTipoPartida(idTipoPartida);
            }
            List<Detallepartida> attachedDetallepartidaList = new ArrayList<Detallepartida>();
            for (Detallepartida detallepartidaListDetallepartidaToAttach : partidas.getDetallepartidaList()) {
                detallepartidaListDetallepartidaToAttach = em.getReference(detallepartidaListDetallepartidaToAttach.getClass(), detallepartidaListDetallepartidaToAttach.getIdDetallePartida());
                attachedDetallepartidaList.add(detallepartidaListDetallepartidaToAttach);
            }
            partidas.setDetallepartidaList(attachedDetallepartidaList);
            List<Cheque> attachedChequeList = new ArrayList<Cheque>();
            for (Cheque chequeListChequeToAttach : partidas.getChequeList()) {
                chequeListChequeToAttach = em.getReference(chequeListChequeToAttach.getClass(), chequeListChequeToAttach.getIdCheque());
                attachedChequeList.add(chequeListChequeToAttach);
            }
            partidas.setChequeList(attachedChequeList);
            em.persist(partidas);
            if (idDiario != null) {
                idDiario.getPartidasList().add(partidas);
                idDiario = em.merge(idDiario);
            }
            if (idTipoPartida != null) {
                idTipoPartida.getPartidasList().add(partidas);
                idTipoPartida = em.merge(idTipoPartida);
            }
            for (Detallepartida detallepartidaListDetallepartida : partidas.getDetallepartidaList()) {
                Partidas oldIdPartidaOfDetallepartidaListDetallepartida = detallepartidaListDetallepartida.getIdPartida();
                detallepartidaListDetallepartida.setIdPartida(partidas);
                detallepartidaListDetallepartida = em.merge(detallepartidaListDetallepartida);
                if (oldIdPartidaOfDetallepartidaListDetallepartida != null) {
                    oldIdPartidaOfDetallepartidaListDetallepartida.getDetallepartidaList().remove(detallepartidaListDetallepartida);
                    oldIdPartidaOfDetallepartidaListDetallepartida = em.merge(oldIdPartidaOfDetallepartidaListDetallepartida);
                }
            }
            for (Cheque chequeListCheque : partidas.getChequeList()) {
                Partidas oldIdPartidaOfChequeListCheque = chequeListCheque.getIdPartida();
                chequeListCheque.setIdPartida(partidas);
                chequeListCheque = em.merge(chequeListCheque);
                if (oldIdPartidaOfChequeListCheque != null) {
                    oldIdPartidaOfChequeListCheque.getChequeList().remove(chequeListCheque);
                    oldIdPartidaOfChequeListCheque = em.merge(oldIdPartidaOfChequeListCheque);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Partidas partidas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Partidas persistentPartidas = em.find(Partidas.class, partidas.getIdPartida());
            Diario idDiarioOld = persistentPartidas.getIdDiario();
            Diario idDiarioNew = partidas.getIdDiario();
            Tipopartida idTipoPartidaOld = persistentPartidas.getIdTipoPartida();
            Tipopartida idTipoPartidaNew = partidas.getIdTipoPartida();
            List<Detallepartida> detallepartidaListOld = persistentPartidas.getDetallepartidaList();
            List<Detallepartida> detallepartidaListNew = partidas.getDetallepartidaList();
            List<Cheque> chequeListOld = persistentPartidas.getChequeList();
            List<Cheque> chequeListNew = partidas.getChequeList();
            if (idDiarioNew != null) {
                idDiarioNew = em.getReference(idDiarioNew.getClass(), idDiarioNew.getIdDiario());
                partidas.setIdDiario(idDiarioNew);
            }
            if (idTipoPartidaNew != null) {
                idTipoPartidaNew = em.getReference(idTipoPartidaNew.getClass(), idTipoPartidaNew.getIdTipoPartida());
                partidas.setIdTipoPartida(idTipoPartidaNew);
            }
            List<Detallepartida> attachedDetallepartidaListNew = new ArrayList<Detallepartida>();
            for (Detallepartida detallepartidaListNewDetallepartidaToAttach : detallepartidaListNew) {
                detallepartidaListNewDetallepartidaToAttach = em.getReference(detallepartidaListNewDetallepartidaToAttach.getClass(), detallepartidaListNewDetallepartidaToAttach.getIdDetallePartida());
                attachedDetallepartidaListNew.add(detallepartidaListNewDetallepartidaToAttach);
            }
            detallepartidaListNew = attachedDetallepartidaListNew;
            partidas.setDetallepartidaList(detallepartidaListNew);
            List<Cheque> attachedChequeListNew = new ArrayList<Cheque>();
            for (Cheque chequeListNewChequeToAttach : chequeListNew) {
                chequeListNewChequeToAttach = em.getReference(chequeListNewChequeToAttach.getClass(), chequeListNewChequeToAttach.getIdCheque());
                attachedChequeListNew.add(chequeListNewChequeToAttach);
            }
            chequeListNew = attachedChequeListNew;
            partidas.setChequeList(chequeListNew);
            partidas = em.merge(partidas);
            if (idDiarioOld != null && !idDiarioOld.equals(idDiarioNew)) {
                idDiarioOld.getPartidasList().remove(partidas);
                idDiarioOld = em.merge(idDiarioOld);
            }
            if (idDiarioNew != null && !idDiarioNew.equals(idDiarioOld)) {
                idDiarioNew.getPartidasList().add(partidas);
                idDiarioNew = em.merge(idDiarioNew);
            }
            if (idTipoPartidaOld != null && !idTipoPartidaOld.equals(idTipoPartidaNew)) {
                idTipoPartidaOld.getPartidasList().remove(partidas);
                idTipoPartidaOld = em.merge(idTipoPartidaOld);
            }
            if (idTipoPartidaNew != null && !idTipoPartidaNew.equals(idTipoPartidaOld)) {
                idTipoPartidaNew.getPartidasList().add(partidas);
                idTipoPartidaNew = em.merge(idTipoPartidaNew);
            }
            for (Detallepartida detallepartidaListOldDetallepartida : detallepartidaListOld) {
                if (!detallepartidaListNew.contains(detallepartidaListOldDetallepartida)) {
                    detallepartidaListOldDetallepartida.setIdPartida(null);
                    detallepartidaListOldDetallepartida = em.merge(detallepartidaListOldDetallepartida);
                }
            }
            for (Detallepartida detallepartidaListNewDetallepartida : detallepartidaListNew) {
                if (!detallepartidaListOld.contains(detallepartidaListNewDetallepartida)) {
                    Partidas oldIdPartidaOfDetallepartidaListNewDetallepartida = detallepartidaListNewDetallepartida.getIdPartida();
                    detallepartidaListNewDetallepartida.setIdPartida(partidas);
                    detallepartidaListNewDetallepartida = em.merge(detallepartidaListNewDetallepartida);
                    if (oldIdPartidaOfDetallepartidaListNewDetallepartida != null && !oldIdPartidaOfDetallepartidaListNewDetallepartida.equals(partidas)) {
                        oldIdPartidaOfDetallepartidaListNewDetallepartida.getDetallepartidaList().remove(detallepartidaListNewDetallepartida);
                        oldIdPartidaOfDetallepartidaListNewDetallepartida = em.merge(oldIdPartidaOfDetallepartidaListNewDetallepartida);
                    }
                }
            }
            for (Cheque chequeListOldCheque : chequeListOld) {
                if (!chequeListNew.contains(chequeListOldCheque)) {
                    chequeListOldCheque.setIdPartida(null);
                    chequeListOldCheque = em.merge(chequeListOldCheque);
                }
            }
            for (Cheque chequeListNewCheque : chequeListNew) {
                if (!chequeListOld.contains(chequeListNewCheque)) {
                    Partidas oldIdPartidaOfChequeListNewCheque = chequeListNewCheque.getIdPartida();
                    chequeListNewCheque.setIdPartida(partidas);
                    chequeListNewCheque = em.merge(chequeListNewCheque);
                    if (oldIdPartidaOfChequeListNewCheque != null && !oldIdPartidaOfChequeListNewCheque.equals(partidas)) {
                        oldIdPartidaOfChequeListNewCheque.getChequeList().remove(chequeListNewCheque);
                        oldIdPartidaOfChequeListNewCheque = em.merge(oldIdPartidaOfChequeListNewCheque);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = partidas.getIdPartida();
                if (findPartidas(id) == null) {
                    throw new NonexistentEntityException("The partidas with id " + id + " no longer exists.");
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
            Partidas partidas;
            try {
                partidas = em.getReference(Partidas.class, id);
                partidas.getIdPartida();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The partidas with id " + id + " no longer exists.", enfe);
            }
            Diario idDiario = partidas.getIdDiario();
            if (idDiario != null) {
                idDiario.getPartidasList().remove(partidas);
                idDiario = em.merge(idDiario);
            }
            Tipopartida idTipoPartida = partidas.getIdTipoPartida();
            if (idTipoPartida != null) {
                idTipoPartida.getPartidasList().remove(partidas);
                idTipoPartida = em.merge(idTipoPartida);
            }
            List<Detallepartida> detallepartidaList = partidas.getDetallepartidaList();
            for (Detallepartida detallepartidaListDetallepartida : detallepartidaList) {
                detallepartidaListDetallepartida.setIdPartida(null);
                detallepartidaListDetallepartida = em.merge(detallepartidaListDetallepartida);
            }
            List<Cheque> chequeList = partidas.getChequeList();
            for (Cheque chequeListCheque : chequeList) {
                chequeListCheque.setIdPartida(null);
                chequeListCheque = em.merge(chequeListCheque);
            }
            em.remove(partidas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Partidas> findPartidasEntities() {
        return findPartidasEntities(true, -1, -1);
    }

    public List<Partidas> findPartidasEntities(int maxResults, int firstResult) {
        return findPartidasEntities(false, maxResults, firstResult);
    }

    private List<Partidas> findPartidasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Partidas.class));
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

    public Partidas findPartidas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Partidas.class, id);
        } finally {
            em.close();
        }
    }

    public int getPartidasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Partidas> rt = cq.from(Partidas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
