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
import sicor.modelo.Banco;
import sicor.modelo.Cheque;
import sicor.modelo.Cuentas;
import sicor.modelo.Empresas;
import sicor.modelo.Proveedores;
import sicor.modelo.Partidas;

/**
 *
 * @author gares
 */
public class ChequeJpaController implements Serializable {

    public ChequeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cheque cheque) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Banco idBanco = cheque.getIdBanco();
            if (idBanco != null) {
                idBanco = em.getReference(idBanco.getClass(), idBanco.getIdBanco());
                cheque.setIdBanco(idBanco);
            }
            Cuentas idCuentaAbono = cheque.getIdCuentaAbono();
            if (idCuentaAbono != null) {
                idCuentaAbono = em.getReference(idCuentaAbono.getClass(), idCuentaAbono.getIdCuenta());
                cheque.setIdCuentaAbono(idCuentaAbono);
            }
            Empresas idEmpresa = cheque.getIdEmpresa();
            if (idEmpresa != null) {
                idEmpresa = em.getReference(idEmpresa.getClass(), idEmpresa.getIdEmpresa());
                cheque.setIdEmpresa(idEmpresa);
            }
            Proveedores idProveedor = cheque.getIdProveedor();
            if (idProveedor != null) {
                idProveedor = em.getReference(idProveedor.getClass(), idProveedor.getIdProveedor());
                cheque.setIdProveedor(idProveedor);
            }
            Partidas idPartida = cheque.getIdPartida();
            if (idPartida != null) {
                idPartida = em.getReference(idPartida.getClass(), idPartida.getIdPartida());
                cheque.setIdPartida(idPartida);
            }
            Cuentas idCuentaCargo = cheque.getIdCuentaCargo();
            if (idCuentaCargo != null) {
                idCuentaCargo = em.getReference(idCuentaCargo.getClass(), idCuentaCargo.getIdCuenta());
                cheque.setIdCuentaCargo(idCuentaCargo);
            }
            em.persist(cheque);
            if (idBanco != null) {
                idBanco.getChequeList().add(cheque);
                idBanco = em.merge(idBanco);
            }
            if (idCuentaAbono != null) {
                idCuentaAbono.getChequeList().add(cheque);
                idCuentaAbono = em.merge(idCuentaAbono);
            }
            if (idEmpresa != null) {
                idEmpresa.getChequeList().add(cheque);
                idEmpresa = em.merge(idEmpresa);
            }
            if (idProveedor != null) {
                idProveedor.getChequeList().add(cheque);
                idProveedor = em.merge(idProveedor);
            }
            if (idPartida != null) {
                idPartida.getChequeList().add(cheque);
                idPartida = em.merge(idPartida);
            }
            if (idCuentaCargo != null) {
                idCuentaCargo.getChequeList().add(cheque);
                idCuentaCargo = em.merge(idCuentaCargo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cheque cheque) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cheque persistentCheque = em.find(Cheque.class, cheque.getIdCheque());
            Banco idBancoOld = persistentCheque.getIdBanco();
            Banco idBancoNew = cheque.getIdBanco();
            Cuentas idCuentaAbonoOld = persistentCheque.getIdCuentaAbono();
            Cuentas idCuentaAbonoNew = cheque.getIdCuentaAbono();
            Empresas idEmpresaOld = persistentCheque.getIdEmpresa();
            Empresas idEmpresaNew = cheque.getIdEmpresa();
            Proveedores idProveedorOld = persistentCheque.getIdProveedor();
            Proveedores idProveedorNew = cheque.getIdProveedor();
            Partidas idPartidaOld = persistentCheque.getIdPartida();
            Partidas idPartidaNew = cheque.getIdPartida();
            Cuentas idCuentaCargoOld = persistentCheque.getIdCuentaCargo();
            Cuentas idCuentaCargoNew = cheque.getIdCuentaCargo();
            if (idBancoNew != null) {
                idBancoNew = em.getReference(idBancoNew.getClass(), idBancoNew.getIdBanco());
                cheque.setIdBanco(idBancoNew);
            }
            if (idCuentaAbonoNew != null) {
                idCuentaAbonoNew = em.getReference(idCuentaAbonoNew.getClass(), idCuentaAbonoNew.getIdCuenta());
                cheque.setIdCuentaAbono(idCuentaAbonoNew);
            }
            if (idEmpresaNew != null) {
                idEmpresaNew = em.getReference(idEmpresaNew.getClass(), idEmpresaNew.getIdEmpresa());
                cheque.setIdEmpresa(idEmpresaNew);
            }
            if (idProveedorNew != null) {
                idProveedorNew = em.getReference(idProveedorNew.getClass(), idProveedorNew.getIdProveedor());
                cheque.setIdProveedor(idProveedorNew);
            }
            if (idPartidaNew != null) {
                idPartidaNew = em.getReference(idPartidaNew.getClass(), idPartidaNew.getIdPartida());
                cheque.setIdPartida(idPartidaNew);
            }
            if (idCuentaCargoNew != null) {
                idCuentaCargoNew = em.getReference(idCuentaCargoNew.getClass(), idCuentaCargoNew.getIdCuenta());
                cheque.setIdCuentaCargo(idCuentaCargoNew);
            }
            cheque = em.merge(cheque);
            if (idBancoOld != null && !idBancoOld.equals(idBancoNew)) {
                idBancoOld.getChequeList().remove(cheque);
                idBancoOld = em.merge(idBancoOld);
            }
            if (idBancoNew != null && !idBancoNew.equals(idBancoOld)) {
                idBancoNew.getChequeList().add(cheque);
                idBancoNew = em.merge(idBancoNew);
            }
            if (idCuentaAbonoOld != null && !idCuentaAbonoOld.equals(idCuentaAbonoNew)) {
                idCuentaAbonoOld.getChequeList().remove(cheque);
                idCuentaAbonoOld = em.merge(idCuentaAbonoOld);
            }
            if (idCuentaAbonoNew != null && !idCuentaAbonoNew.equals(idCuentaAbonoOld)) {
                idCuentaAbonoNew.getChequeList().add(cheque);
                idCuentaAbonoNew = em.merge(idCuentaAbonoNew);
            }
            if (idEmpresaOld != null && !idEmpresaOld.equals(idEmpresaNew)) {
                idEmpresaOld.getChequeList().remove(cheque);
                idEmpresaOld = em.merge(idEmpresaOld);
            }
            if (idEmpresaNew != null && !idEmpresaNew.equals(idEmpresaOld)) {
                idEmpresaNew.getChequeList().add(cheque);
                idEmpresaNew = em.merge(idEmpresaNew);
            }
            if (idProveedorOld != null && !idProveedorOld.equals(idProveedorNew)) {
                idProveedorOld.getChequeList().remove(cheque);
                idProveedorOld = em.merge(idProveedorOld);
            }
            if (idProveedorNew != null && !idProveedorNew.equals(idProveedorOld)) {
                idProveedorNew.getChequeList().add(cheque);
                idProveedorNew = em.merge(idProveedorNew);
            }
            if (idPartidaOld != null && !idPartidaOld.equals(idPartidaNew)) {
                idPartidaOld.getChequeList().remove(cheque);
                idPartidaOld = em.merge(idPartidaOld);
            }
            if (idPartidaNew != null && !idPartidaNew.equals(idPartidaOld)) {
                idPartidaNew.getChequeList().add(cheque);
                idPartidaNew = em.merge(idPartidaNew);
            }
            if (idCuentaCargoOld != null && !idCuentaCargoOld.equals(idCuentaCargoNew)) {
                idCuentaCargoOld.getChequeList().remove(cheque);
                idCuentaCargoOld = em.merge(idCuentaCargoOld);
            }
            if (idCuentaCargoNew != null && !idCuentaCargoNew.equals(idCuentaCargoOld)) {
                idCuentaCargoNew.getChequeList().add(cheque);
                idCuentaCargoNew = em.merge(idCuentaCargoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cheque.getIdCheque();
                if (findCheque(id) == null) {
                    throw new NonexistentEntityException("The cheque with id " + id + " no longer exists.");
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
            Cheque cheque;
            try {
                cheque = em.getReference(Cheque.class, id);
                cheque.getIdCheque();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cheque with id " + id + " no longer exists.", enfe);
            }
            Banco idBanco = cheque.getIdBanco();
            if (idBanco != null) {
                idBanco.getChequeList().remove(cheque);
                idBanco = em.merge(idBanco);
            }
            Cuentas idCuentaAbono = cheque.getIdCuentaAbono();
            if (idCuentaAbono != null) {
                idCuentaAbono.getChequeList().remove(cheque);
                idCuentaAbono = em.merge(idCuentaAbono);
            }
            Empresas idEmpresa = cheque.getIdEmpresa();
            if (idEmpresa != null) {
                idEmpresa.getChequeList().remove(cheque);
                idEmpresa = em.merge(idEmpresa);
            }
            Proveedores idProveedor = cheque.getIdProveedor();
            if (idProveedor != null) {
                idProveedor.getChequeList().remove(cheque);
                idProveedor = em.merge(idProveedor);
            }
            Partidas idPartida = cheque.getIdPartida();
            if (idPartida != null) {
                idPartida.getChequeList().remove(cheque);
                idPartida = em.merge(idPartida);
            }
            Cuentas idCuentaCargo = cheque.getIdCuentaCargo();
            if (idCuentaCargo != null) {
                idCuentaCargo.getChequeList().remove(cheque);
                idCuentaCargo = em.merge(idCuentaCargo);
            }
            em.remove(cheque);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cheque> findChequeEntities() {
        return findChequeEntities(true, -1, -1);
    }

    public List<Cheque> findChequeEntities(int maxResults, int firstResult) {
        return findChequeEntities(false, maxResults, firstResult);
    }

    private List<Cheque> findChequeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cheque.class));
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

    public Cheque findCheque(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cheque.class, id);
        } finally {
            em.close();
        }
    }

    public int getChequeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cheque> rt = cq.from(Cheque.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
