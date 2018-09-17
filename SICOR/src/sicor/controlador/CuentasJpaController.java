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
import sicor.modelo.Cuentas;
import sicor.modelo.Detallepartida;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import sicor.controlador.exceptions.NonexistentEntityException;
import sicor.modelo.Cheque;

/**
 *
 * @author gares
 */
public class CuentasJpaController implements Serializable {

    public CuentasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cuentas cuentas) {
        if (cuentas.getDetallepartidaList() == null) {
            cuentas.setDetallepartidaList(new ArrayList<Detallepartida>());
        }
        if (cuentas.getChequeList() == null) {
            cuentas.setChequeList(new ArrayList<Cheque>());
        }
        if (cuentas.getChequeList1() == null) {
            cuentas.setChequeList1(new ArrayList<Cheque>());
        }
        if (cuentas.getCuentasList() == null) {
            cuentas.setCuentasList(new ArrayList<Cuentas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empresas idEmpresa = cuentas.getIdEmpresa();
            if (idEmpresa != null) {
                idEmpresa = em.getReference(idEmpresa.getClass(), idEmpresa.getIdEmpresa());
                cuentas.setIdEmpresa(idEmpresa);
            }
            Cuentas idCuentaPadre = cuentas.getIdCuentaPadre();
            if (idCuentaPadre != null) {
                idCuentaPadre = em.getReference(idCuentaPadre.getClass(), idCuentaPadre.getIdCuenta());
                cuentas.setIdCuentaPadre(idCuentaPadre);
            }
            List<Detallepartida> attachedDetallepartidaList = new ArrayList<Detallepartida>();
            for (Detallepartida detallepartidaListDetallepartidaToAttach : cuentas.getDetallepartidaList()) {
                detallepartidaListDetallepartidaToAttach = em.getReference(detallepartidaListDetallepartidaToAttach.getClass(), detallepartidaListDetallepartidaToAttach.getIdDetallePartida());
                attachedDetallepartidaList.add(detallepartidaListDetallepartidaToAttach);
            }
            cuentas.setDetallepartidaList(attachedDetallepartidaList);
            List<Cheque> attachedChequeList = new ArrayList<Cheque>();
            for (Cheque chequeListChequeToAttach : cuentas.getChequeList()) {
                chequeListChequeToAttach = em.getReference(chequeListChequeToAttach.getClass(), chequeListChequeToAttach.getIdCheque());
                attachedChequeList.add(chequeListChequeToAttach);
            }
            cuentas.setChequeList(attachedChequeList);
            List<Cheque> attachedChequeList1 = new ArrayList<Cheque>();
            for (Cheque chequeList1ChequeToAttach : cuentas.getChequeList1()) {
                chequeList1ChequeToAttach = em.getReference(chequeList1ChequeToAttach.getClass(), chequeList1ChequeToAttach.getIdCheque());
                attachedChequeList1.add(chequeList1ChequeToAttach);
            }
            cuentas.setChequeList1(attachedChequeList1);
            List<Cuentas> attachedCuentasList = new ArrayList<Cuentas>();
            for (Cuentas cuentasListCuentasToAttach : cuentas.getCuentasList()) {
                cuentasListCuentasToAttach = em.getReference(cuentasListCuentasToAttach.getClass(), cuentasListCuentasToAttach.getIdCuenta());
                attachedCuentasList.add(cuentasListCuentasToAttach);
            }
            cuentas.setCuentasList(attachedCuentasList);
            em.persist(cuentas);
            if (idEmpresa != null) {
                idEmpresa.getCuentasList().add(cuentas);
                idEmpresa = em.merge(idEmpresa);
            }
            if (idCuentaPadre != null) {
                idCuentaPadre.getCuentasList().add(cuentas);
                idCuentaPadre = em.merge(idCuentaPadre);
            }
            for (Detallepartida detallepartidaListDetallepartida : cuentas.getDetallepartidaList()) {
                Cuentas oldIdCuentaOfDetallepartidaListDetallepartida = detallepartidaListDetallepartida.getIdCuenta();
                detallepartidaListDetallepartida.setIdCuenta(cuentas);
                detallepartidaListDetallepartida = em.merge(detallepartidaListDetallepartida);
                if (oldIdCuentaOfDetallepartidaListDetallepartida != null) {
                    oldIdCuentaOfDetallepartidaListDetallepartida.getDetallepartidaList().remove(detallepartidaListDetallepartida);
                    oldIdCuentaOfDetallepartidaListDetallepartida = em.merge(oldIdCuentaOfDetallepartidaListDetallepartida);
                }
            }
            for (Cheque chequeListCheque : cuentas.getChequeList()) {
                Cuentas oldIdCuentaAbonoOfChequeListCheque = chequeListCheque.getIdCuentaAbono();
                chequeListCheque.setIdCuentaAbono(cuentas);
                chequeListCheque = em.merge(chequeListCheque);
                if (oldIdCuentaAbonoOfChequeListCheque != null) {
                    oldIdCuentaAbonoOfChequeListCheque.getChequeList().remove(chequeListCheque);
                    oldIdCuentaAbonoOfChequeListCheque = em.merge(oldIdCuentaAbonoOfChequeListCheque);
                }
            }
            for (Cheque chequeList1Cheque : cuentas.getChequeList1()) {
                Cuentas oldIdCuentaCargoOfChequeList1Cheque = chequeList1Cheque.getIdCuentaCargo();
                chequeList1Cheque.setIdCuentaCargo(cuentas);
                chequeList1Cheque = em.merge(chequeList1Cheque);
                if (oldIdCuentaCargoOfChequeList1Cheque != null) {
                    oldIdCuentaCargoOfChequeList1Cheque.getChequeList1().remove(chequeList1Cheque);
                    oldIdCuentaCargoOfChequeList1Cheque = em.merge(oldIdCuentaCargoOfChequeList1Cheque);
                }
            }
            for (Cuentas cuentasListCuentas : cuentas.getCuentasList()) {
                Cuentas oldIdCuentaPadreOfCuentasListCuentas = cuentasListCuentas.getIdCuentaPadre();
                cuentasListCuentas.setIdCuentaPadre(cuentas);
                cuentasListCuentas = em.merge(cuentasListCuentas);
                if (oldIdCuentaPadreOfCuentasListCuentas != null) {
                    oldIdCuentaPadreOfCuentasListCuentas.getCuentasList().remove(cuentasListCuentas);
                    oldIdCuentaPadreOfCuentasListCuentas = em.merge(oldIdCuentaPadreOfCuentasListCuentas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cuentas cuentas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cuentas persistentCuentas = em.find(Cuentas.class, cuentas.getIdCuenta());
            Empresas idEmpresaOld = persistentCuentas.getIdEmpresa();
            Empresas idEmpresaNew = cuentas.getIdEmpresa();
            Cuentas idCuentaPadreOld = persistentCuentas.getIdCuentaPadre();
            Cuentas idCuentaPadreNew = cuentas.getIdCuentaPadre();
            List<Detallepartida> detallepartidaListOld = persistentCuentas.getDetallepartidaList();
            List<Detallepartida> detallepartidaListNew = cuentas.getDetallepartidaList();
            List<Cheque> chequeListOld = persistentCuentas.getChequeList();
            List<Cheque> chequeListNew = cuentas.getChequeList();
            List<Cheque> chequeList1Old = persistentCuentas.getChequeList1();
            List<Cheque> chequeList1New = cuentas.getChequeList1();
            List<Cuentas> cuentasListOld = persistentCuentas.getCuentasList();
            List<Cuentas> cuentasListNew = cuentas.getCuentasList();
            if (idEmpresaNew != null) {
                idEmpresaNew = em.getReference(idEmpresaNew.getClass(), idEmpresaNew.getIdEmpresa());
                cuentas.setIdEmpresa(idEmpresaNew);
            }
            if (idCuentaPadreNew != null) {
                idCuentaPadreNew = em.getReference(idCuentaPadreNew.getClass(), idCuentaPadreNew.getIdCuenta());
                cuentas.setIdCuentaPadre(idCuentaPadreNew);
            }
            List<Detallepartida> attachedDetallepartidaListNew = new ArrayList<Detallepartida>();
            for (Detallepartida detallepartidaListNewDetallepartidaToAttach : detallepartidaListNew) {
                detallepartidaListNewDetallepartidaToAttach = em.getReference(detallepartidaListNewDetallepartidaToAttach.getClass(), detallepartidaListNewDetallepartidaToAttach.getIdDetallePartida());
                attachedDetallepartidaListNew.add(detallepartidaListNewDetallepartidaToAttach);
            }
            detallepartidaListNew = attachedDetallepartidaListNew;
            cuentas.setDetallepartidaList(detallepartidaListNew);
            List<Cheque> attachedChequeListNew = new ArrayList<Cheque>();
            for (Cheque chequeListNewChequeToAttach : chequeListNew) {
                chequeListNewChequeToAttach = em.getReference(chequeListNewChequeToAttach.getClass(), chequeListNewChequeToAttach.getIdCheque());
                attachedChequeListNew.add(chequeListNewChequeToAttach);
            }
            chequeListNew = attachedChequeListNew;
            cuentas.setChequeList(chequeListNew);
            List<Cheque> attachedChequeList1New = new ArrayList<Cheque>();
            for (Cheque chequeList1NewChequeToAttach : chequeList1New) {
                chequeList1NewChequeToAttach = em.getReference(chequeList1NewChequeToAttach.getClass(), chequeList1NewChequeToAttach.getIdCheque());
                attachedChequeList1New.add(chequeList1NewChequeToAttach);
            }
            chequeList1New = attachedChequeList1New;
            cuentas.setChequeList1(chequeList1New);
            List<Cuentas> attachedCuentasListNew = new ArrayList<Cuentas>();
            for (Cuentas cuentasListNewCuentasToAttach : cuentasListNew) {
                cuentasListNewCuentasToAttach = em.getReference(cuentasListNewCuentasToAttach.getClass(), cuentasListNewCuentasToAttach.getIdCuenta());
                attachedCuentasListNew.add(cuentasListNewCuentasToAttach);
            }
            cuentasListNew = attachedCuentasListNew;
            cuentas.setCuentasList(cuentasListNew);
            cuentas = em.merge(cuentas);
            if (idEmpresaOld != null && !idEmpresaOld.equals(idEmpresaNew)) {
                idEmpresaOld.getCuentasList().remove(cuentas);
                idEmpresaOld = em.merge(idEmpresaOld);
            }
            if (idEmpresaNew != null && !idEmpresaNew.equals(idEmpresaOld)) {
                idEmpresaNew.getCuentasList().add(cuentas);
                idEmpresaNew = em.merge(idEmpresaNew);
            }
            if (idCuentaPadreOld != null && !idCuentaPadreOld.equals(idCuentaPadreNew)) {
                idCuentaPadreOld.getCuentasList().remove(cuentas);
                idCuentaPadreOld = em.merge(idCuentaPadreOld);
            }
            if (idCuentaPadreNew != null && !idCuentaPadreNew.equals(idCuentaPadreOld)) {
                idCuentaPadreNew.getCuentasList().add(cuentas);
                idCuentaPadreNew = em.merge(idCuentaPadreNew);
            }
            for (Detallepartida detallepartidaListOldDetallepartida : detallepartidaListOld) {
                if (!detallepartidaListNew.contains(detallepartidaListOldDetallepartida)) {
                    detallepartidaListOldDetallepartida.setIdCuenta(null);
                    detallepartidaListOldDetallepartida = em.merge(detallepartidaListOldDetallepartida);
                }
            }
            for (Detallepartida detallepartidaListNewDetallepartida : detallepartidaListNew) {
                if (!detallepartidaListOld.contains(detallepartidaListNewDetallepartida)) {
                    Cuentas oldIdCuentaOfDetallepartidaListNewDetallepartida = detallepartidaListNewDetallepartida.getIdCuenta();
                    detallepartidaListNewDetallepartida.setIdCuenta(cuentas);
                    detallepartidaListNewDetallepartida = em.merge(detallepartidaListNewDetallepartida);
                    if (oldIdCuentaOfDetallepartidaListNewDetallepartida != null && !oldIdCuentaOfDetallepartidaListNewDetallepartida.equals(cuentas)) {
                        oldIdCuentaOfDetallepartidaListNewDetallepartida.getDetallepartidaList().remove(detallepartidaListNewDetallepartida);
                        oldIdCuentaOfDetallepartidaListNewDetallepartida = em.merge(oldIdCuentaOfDetallepartidaListNewDetallepartida);
                    }
                }
            }
            for (Cheque chequeListOldCheque : chequeListOld) {
                if (!chequeListNew.contains(chequeListOldCheque)) {
                    chequeListOldCheque.setIdCuentaAbono(null);
                    chequeListOldCheque = em.merge(chequeListOldCheque);
                }
            }
            for (Cheque chequeListNewCheque : chequeListNew) {
                if (!chequeListOld.contains(chequeListNewCheque)) {
                    Cuentas oldIdCuentaAbonoOfChequeListNewCheque = chequeListNewCheque.getIdCuentaAbono();
                    chequeListNewCheque.setIdCuentaAbono(cuentas);
                    chequeListNewCheque = em.merge(chequeListNewCheque);
                    if (oldIdCuentaAbonoOfChequeListNewCheque != null && !oldIdCuentaAbonoOfChequeListNewCheque.equals(cuentas)) {
                        oldIdCuentaAbonoOfChequeListNewCheque.getChequeList().remove(chequeListNewCheque);
                        oldIdCuentaAbonoOfChequeListNewCheque = em.merge(oldIdCuentaAbonoOfChequeListNewCheque);
                    }
                }
            }
            for (Cheque chequeList1OldCheque : chequeList1Old) {
                if (!chequeList1New.contains(chequeList1OldCheque)) {
                    chequeList1OldCheque.setIdCuentaCargo(null);
                    chequeList1OldCheque = em.merge(chequeList1OldCheque);
                }
            }
            for (Cheque chequeList1NewCheque : chequeList1New) {
                if (!chequeList1Old.contains(chequeList1NewCheque)) {
                    Cuentas oldIdCuentaCargoOfChequeList1NewCheque = chequeList1NewCheque.getIdCuentaCargo();
                    chequeList1NewCheque.setIdCuentaCargo(cuentas);
                    chequeList1NewCheque = em.merge(chequeList1NewCheque);
                    if (oldIdCuentaCargoOfChequeList1NewCheque != null && !oldIdCuentaCargoOfChequeList1NewCheque.equals(cuentas)) {
                        oldIdCuentaCargoOfChequeList1NewCheque.getChequeList1().remove(chequeList1NewCheque);
                        oldIdCuentaCargoOfChequeList1NewCheque = em.merge(oldIdCuentaCargoOfChequeList1NewCheque);
                    }
                }
            }
            for (Cuentas cuentasListOldCuentas : cuentasListOld) {
                if (!cuentasListNew.contains(cuentasListOldCuentas)) {
                    cuentasListOldCuentas.setIdCuentaPadre(null);
                    cuentasListOldCuentas = em.merge(cuentasListOldCuentas);
                }
            }
            for (Cuentas cuentasListNewCuentas : cuentasListNew) {
                if (!cuentasListOld.contains(cuentasListNewCuentas)) {
                    Cuentas oldIdCuentaPadreOfCuentasListNewCuentas = cuentasListNewCuentas.getIdCuentaPadre();
                    cuentasListNewCuentas.setIdCuentaPadre(cuentas);
                    cuentasListNewCuentas = em.merge(cuentasListNewCuentas);
                    if (oldIdCuentaPadreOfCuentasListNewCuentas != null && !oldIdCuentaPadreOfCuentasListNewCuentas.equals(cuentas)) {
                        oldIdCuentaPadreOfCuentasListNewCuentas.getCuentasList().remove(cuentasListNewCuentas);
                        oldIdCuentaPadreOfCuentasListNewCuentas = em.merge(oldIdCuentaPadreOfCuentasListNewCuentas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cuentas.getIdCuenta();
                if (findCuentas(id) == null) {
                    throw new NonexistentEntityException("The cuentas with id " + id + " no longer exists.");
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
            Cuentas cuentas;
            try {
                cuentas = em.getReference(Cuentas.class, id);
                cuentas.getIdCuenta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cuentas with id " + id + " no longer exists.", enfe);
            }
            Empresas idEmpresa = cuentas.getIdEmpresa();
            if (idEmpresa != null) {
                idEmpresa.getCuentasList().remove(cuentas);
                idEmpresa = em.merge(idEmpresa);
            }
            Cuentas idCuentaPadre = cuentas.getIdCuentaPadre();
            if (idCuentaPadre != null) {
                idCuentaPadre.getCuentasList().remove(cuentas);
                idCuentaPadre = em.merge(idCuentaPadre);
            }
            List<Detallepartida> detallepartidaList = cuentas.getDetallepartidaList();
            for (Detallepartida detallepartidaListDetallepartida : detallepartidaList) {
                detallepartidaListDetallepartida.setIdCuenta(null);
                detallepartidaListDetallepartida = em.merge(detallepartidaListDetallepartida);
            }
            List<Cheque> chequeList = cuentas.getChequeList();
            for (Cheque chequeListCheque : chequeList) {
                chequeListCheque.setIdCuentaAbono(null);
                chequeListCheque = em.merge(chequeListCheque);
            }
            List<Cheque> chequeList1 = cuentas.getChequeList1();
            for (Cheque chequeList1Cheque : chequeList1) {
                chequeList1Cheque.setIdCuentaCargo(null);
                chequeList1Cheque = em.merge(chequeList1Cheque);
            }
            List<Cuentas> cuentasList = cuentas.getCuentasList();
            for (Cuentas cuentasListCuentas : cuentasList) {
                cuentasListCuentas.setIdCuentaPadre(null);
                cuentasListCuentas = em.merge(cuentasListCuentas);
            }
            em.remove(cuentas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cuentas> findCuentasEntities() {
        return findCuentasEntities(true, -1, -1);
    }

    public List<Cuentas> findCuentasEntities(int maxResults, int firstResult) {
        return findCuentasEntities(false, maxResults, firstResult);
    }

    private List<Cuentas> findCuentasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cuentas.class));
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

    public Cuentas findCuentas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cuentas.class, id);
        } finally {
            em.close();
        }
    }

    public int getCuentasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cuentas> rt = cq.from(Cuentas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
