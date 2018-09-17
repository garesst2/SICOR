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
import sicor.modelo.Proveedores;

/**
 *
 * @author gares
 */
public class ProveedoresJpaController implements Serializable {

    public ProveedoresJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Proveedores proveedores) {
        if (proveedores.getChequeList() == null) {
            proveedores.setChequeList(new ArrayList<Cheque>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empresas idEmpresa = proveedores.getIdEmpresa();
            if (idEmpresa != null) {
                idEmpresa = em.getReference(idEmpresa.getClass(), idEmpresa.getIdEmpresa());
                proveedores.setIdEmpresa(idEmpresa);
            }
            List<Cheque> attachedChequeList = new ArrayList<Cheque>();
            for (Cheque chequeListChequeToAttach : proveedores.getChequeList()) {
                chequeListChequeToAttach = em.getReference(chequeListChequeToAttach.getClass(), chequeListChequeToAttach.getIdCheque());
                attachedChequeList.add(chequeListChequeToAttach);
            }
            proveedores.setChequeList(attachedChequeList);
            em.persist(proveedores);
            if (idEmpresa != null) {
                idEmpresa.getProveedoresList().add(proveedores);
                idEmpresa = em.merge(idEmpresa);
            }
            for (Cheque chequeListCheque : proveedores.getChequeList()) {
                Proveedores oldIdProveedorOfChequeListCheque = chequeListCheque.getIdProveedor();
                chequeListCheque.setIdProveedor(proveedores);
                chequeListCheque = em.merge(chequeListCheque);
                if (oldIdProveedorOfChequeListCheque != null) {
                    oldIdProveedorOfChequeListCheque.getChequeList().remove(chequeListCheque);
                    oldIdProveedorOfChequeListCheque = em.merge(oldIdProveedorOfChequeListCheque);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Proveedores proveedores) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proveedores persistentProveedores = em.find(Proveedores.class, proveedores.getIdProveedor());
            Empresas idEmpresaOld = persistentProveedores.getIdEmpresa();
            Empresas idEmpresaNew = proveedores.getIdEmpresa();
            List<Cheque> chequeListOld = persistentProveedores.getChequeList();
            List<Cheque> chequeListNew = proveedores.getChequeList();
            if (idEmpresaNew != null) {
                idEmpresaNew = em.getReference(idEmpresaNew.getClass(), idEmpresaNew.getIdEmpresa());
                proveedores.setIdEmpresa(idEmpresaNew);
            }
            List<Cheque> attachedChequeListNew = new ArrayList<Cheque>();
            for (Cheque chequeListNewChequeToAttach : chequeListNew) {
                chequeListNewChequeToAttach = em.getReference(chequeListNewChequeToAttach.getClass(), chequeListNewChequeToAttach.getIdCheque());
                attachedChequeListNew.add(chequeListNewChequeToAttach);
            }
            chequeListNew = attachedChequeListNew;
            proveedores.setChequeList(chequeListNew);
            proveedores = em.merge(proveedores);
            if (idEmpresaOld != null && !idEmpresaOld.equals(idEmpresaNew)) {
                idEmpresaOld.getProveedoresList().remove(proveedores);
                idEmpresaOld = em.merge(idEmpresaOld);
            }
            if (idEmpresaNew != null && !idEmpresaNew.equals(idEmpresaOld)) {
                idEmpresaNew.getProveedoresList().add(proveedores);
                idEmpresaNew = em.merge(idEmpresaNew);
            }
            for (Cheque chequeListOldCheque : chequeListOld) {
                if (!chequeListNew.contains(chequeListOldCheque)) {
                    chequeListOldCheque.setIdProveedor(null);
                    chequeListOldCheque = em.merge(chequeListOldCheque);
                }
            }
            for (Cheque chequeListNewCheque : chequeListNew) {
                if (!chequeListOld.contains(chequeListNewCheque)) {
                    Proveedores oldIdProveedorOfChequeListNewCheque = chequeListNewCheque.getIdProveedor();
                    chequeListNewCheque.setIdProveedor(proveedores);
                    chequeListNewCheque = em.merge(chequeListNewCheque);
                    if (oldIdProveedorOfChequeListNewCheque != null && !oldIdProveedorOfChequeListNewCheque.equals(proveedores)) {
                        oldIdProveedorOfChequeListNewCheque.getChequeList().remove(chequeListNewCheque);
                        oldIdProveedorOfChequeListNewCheque = em.merge(oldIdProveedorOfChequeListNewCheque);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = proveedores.getIdProveedor();
                if (findProveedores(id) == null) {
                    throw new NonexistentEntityException("The proveedores with id " + id + " no longer exists.");
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
            Proveedores proveedores;
            try {
                proveedores = em.getReference(Proveedores.class, id);
                proveedores.getIdProveedor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The proveedores with id " + id + " no longer exists.", enfe);
            }
            Empresas idEmpresa = proveedores.getIdEmpresa();
            if (idEmpresa != null) {
                idEmpresa.getProveedoresList().remove(proveedores);
                idEmpresa = em.merge(idEmpresa);
            }
            List<Cheque> chequeList = proveedores.getChequeList();
            for (Cheque chequeListCheque : chequeList) {
                chequeListCheque.setIdProveedor(null);
                chequeListCheque = em.merge(chequeListCheque);
            }
            em.remove(proveedores);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Proveedores> findProveedoresEntities() {
        return findProveedoresEntities(true, -1, -1);
    }

    public List<Proveedores> findProveedoresEntities(int maxResults, int firstResult) {
        return findProveedoresEntities(false, maxResults, firstResult);
    }

    private List<Proveedores> findProveedoresEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Proveedores.class));
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

    public Proveedores findProveedores(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Proveedores.class, id);
        } finally {
            em.close();
        }
    }

    public int getProveedoresCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Proveedores> rt = cq.from(Proveedores.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
