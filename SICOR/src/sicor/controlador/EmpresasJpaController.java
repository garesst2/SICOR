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
import sicor.modelo.Proveedores;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import sicor.controlador.exceptions.IllegalOrphanException;
import sicor.controlador.exceptions.NonexistentEntityException;
import sicor.modelo.Diario;
import sicor.modelo.Banco;
import sicor.modelo.Cheque;
import sicor.modelo.Cuentas;
import sicor.modelo.Empresas;

/**
 *
 * @author gares
 */
public class EmpresasJpaController implements Serializable {

    public EmpresasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Empresas empresas) {
        if (empresas.getProveedoresList() == null) {
            empresas.setProveedoresList(new ArrayList<Proveedores>());
        }
        if (empresas.getDiarioList() == null) {
            empresas.setDiarioList(new ArrayList<Diario>());
        }
        if (empresas.getBancoList() == null) {
            empresas.setBancoList(new ArrayList<Banco>());
        }
        if (empresas.getChequeList() == null) {
            empresas.setChequeList(new ArrayList<Cheque>());
        }
        if (empresas.getCuentasList() == null) {
            empresas.setCuentasList(new ArrayList<Cuentas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Proveedores> attachedProveedoresList = new ArrayList<Proveedores>();
            for (Proveedores proveedoresListProveedoresToAttach : empresas.getProveedoresList()) {
                proveedoresListProveedoresToAttach = em.getReference(proveedoresListProveedoresToAttach.getClass(), proveedoresListProveedoresToAttach.getIdProveedor());
                attachedProveedoresList.add(proveedoresListProveedoresToAttach);
            }
            empresas.setProveedoresList(attachedProveedoresList);
            List<Diario> attachedDiarioList = new ArrayList<Diario>();
            for (Diario diarioListDiarioToAttach : empresas.getDiarioList()) {
                diarioListDiarioToAttach = em.getReference(diarioListDiarioToAttach.getClass(), diarioListDiarioToAttach.getIdDiario());
                attachedDiarioList.add(diarioListDiarioToAttach);
            }
            empresas.setDiarioList(attachedDiarioList);
            List<Banco> attachedBancoList = new ArrayList<Banco>();
            for (Banco bancoListBancoToAttach : empresas.getBancoList()) {
                bancoListBancoToAttach = em.getReference(bancoListBancoToAttach.getClass(), bancoListBancoToAttach.getIdBanco());
                attachedBancoList.add(bancoListBancoToAttach);
            }
            empresas.setBancoList(attachedBancoList);
            List<Cheque> attachedChequeList = new ArrayList<Cheque>();
            for (Cheque chequeListChequeToAttach : empresas.getChequeList()) {
                chequeListChequeToAttach = em.getReference(chequeListChequeToAttach.getClass(), chequeListChequeToAttach.getIdCheque());
                attachedChequeList.add(chequeListChequeToAttach);
            }
            empresas.setChequeList(attachedChequeList);
            List<Cuentas> attachedCuentasList = new ArrayList<Cuentas>();
            for (Cuentas cuentasListCuentasToAttach : empresas.getCuentasList()) {
                cuentasListCuentasToAttach = em.getReference(cuentasListCuentasToAttach.getClass(), cuentasListCuentasToAttach.getIdCuenta());
                attachedCuentasList.add(cuentasListCuentasToAttach);
            }
            empresas.setCuentasList(attachedCuentasList);
            em.persist(empresas);
            for (Proveedores proveedoresListProveedores : empresas.getProveedoresList()) {
                Empresas oldIdEmpresaOfProveedoresListProveedores = proveedoresListProveedores.getIdEmpresa();
                proveedoresListProveedores.setIdEmpresa(empresas);
                proveedoresListProveedores = em.merge(proveedoresListProveedores);
                if (oldIdEmpresaOfProveedoresListProveedores != null) {
                    oldIdEmpresaOfProveedoresListProveedores.getProveedoresList().remove(proveedoresListProveedores);
                    oldIdEmpresaOfProveedoresListProveedores = em.merge(oldIdEmpresaOfProveedoresListProveedores);
                }
            }
            for (Diario diarioListDiario : empresas.getDiarioList()) {
                Empresas oldIdEmpresaOfDiarioListDiario = diarioListDiario.getIdEmpresa();
                diarioListDiario.setIdEmpresa(empresas);
                diarioListDiario = em.merge(diarioListDiario);
                if (oldIdEmpresaOfDiarioListDiario != null) {
                    oldIdEmpresaOfDiarioListDiario.getDiarioList().remove(diarioListDiario);
                    oldIdEmpresaOfDiarioListDiario = em.merge(oldIdEmpresaOfDiarioListDiario);
                }
            }
            for (Banco bancoListBanco : empresas.getBancoList()) {
                Empresas oldIdEmpresaOfBancoListBanco = bancoListBanco.getIdEmpresa();
                bancoListBanco.setIdEmpresa(empresas);
                bancoListBanco = em.merge(bancoListBanco);
                if (oldIdEmpresaOfBancoListBanco != null) {
                    oldIdEmpresaOfBancoListBanco.getBancoList().remove(bancoListBanco);
                    oldIdEmpresaOfBancoListBanco = em.merge(oldIdEmpresaOfBancoListBanco);
                }
            }
            for (Cheque chequeListCheque : empresas.getChequeList()) {
                Empresas oldIdEmpresaOfChequeListCheque = chequeListCheque.getIdEmpresa();
                chequeListCheque.setIdEmpresa(empresas);
                chequeListCheque = em.merge(chequeListCheque);
                if (oldIdEmpresaOfChequeListCheque != null) {
                    oldIdEmpresaOfChequeListCheque.getChequeList().remove(chequeListCheque);
                    oldIdEmpresaOfChequeListCheque = em.merge(oldIdEmpresaOfChequeListCheque);
                }
            }
            for (Cuentas cuentasListCuentas : empresas.getCuentasList()) {
                Empresas oldIdEmpresaOfCuentasListCuentas = cuentasListCuentas.getIdEmpresa();
                cuentasListCuentas.setIdEmpresa(empresas);
                cuentasListCuentas = em.merge(cuentasListCuentas);
                if (oldIdEmpresaOfCuentasListCuentas != null) {
                    oldIdEmpresaOfCuentasListCuentas.getCuentasList().remove(cuentasListCuentas);
                    oldIdEmpresaOfCuentasListCuentas = em.merge(oldIdEmpresaOfCuentasListCuentas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Empresas empresas) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empresas persistentEmpresas = em.find(Empresas.class, empresas.getIdEmpresa());
            List<Proveedores> proveedoresListOld = persistentEmpresas.getProveedoresList();
            List<Proveedores> proveedoresListNew = empresas.getProveedoresList();
            List<Diario> diarioListOld = persistentEmpresas.getDiarioList();
            List<Diario> diarioListNew = empresas.getDiarioList();
            List<Banco> bancoListOld = persistentEmpresas.getBancoList();
            List<Banco> bancoListNew = empresas.getBancoList();
            List<Cheque> chequeListOld = persistentEmpresas.getChequeList();
            List<Cheque> chequeListNew = empresas.getChequeList();
            List<Cuentas> cuentasListOld = persistentEmpresas.getCuentasList();
            List<Cuentas> cuentasListNew = empresas.getCuentasList();
            List<String> illegalOrphanMessages = null;
            for (Banco bancoListOldBanco : bancoListOld) {
                if (!bancoListNew.contains(bancoListOldBanco)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Banco " + bancoListOldBanco + " since its idEmpresa field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Proveedores> attachedProveedoresListNew = new ArrayList<Proveedores>();
            for (Proveedores proveedoresListNewProveedoresToAttach : proveedoresListNew) {
                proveedoresListNewProveedoresToAttach = em.getReference(proveedoresListNewProveedoresToAttach.getClass(), proveedoresListNewProveedoresToAttach.getIdProveedor());
                attachedProveedoresListNew.add(proveedoresListNewProveedoresToAttach);
            }
            proveedoresListNew = attachedProveedoresListNew;
            empresas.setProveedoresList(proveedoresListNew);
            List<Diario> attachedDiarioListNew = new ArrayList<Diario>();
            for (Diario diarioListNewDiarioToAttach : diarioListNew) {
                diarioListNewDiarioToAttach = em.getReference(diarioListNewDiarioToAttach.getClass(), diarioListNewDiarioToAttach.getIdDiario());
                attachedDiarioListNew.add(diarioListNewDiarioToAttach);
            }
            diarioListNew = attachedDiarioListNew;
            empresas.setDiarioList(diarioListNew);
            List<Banco> attachedBancoListNew = new ArrayList<Banco>();
            for (Banco bancoListNewBancoToAttach : bancoListNew) {
                bancoListNewBancoToAttach = em.getReference(bancoListNewBancoToAttach.getClass(), bancoListNewBancoToAttach.getIdBanco());
                attachedBancoListNew.add(bancoListNewBancoToAttach);
            }
            bancoListNew = attachedBancoListNew;
            empresas.setBancoList(bancoListNew);
            List<Cheque> attachedChequeListNew = new ArrayList<Cheque>();
            for (Cheque chequeListNewChequeToAttach : chequeListNew) {
                chequeListNewChequeToAttach = em.getReference(chequeListNewChequeToAttach.getClass(), chequeListNewChequeToAttach.getIdCheque());
                attachedChequeListNew.add(chequeListNewChequeToAttach);
            }
            chequeListNew = attachedChequeListNew;
            empresas.setChequeList(chequeListNew);
            List<Cuentas> attachedCuentasListNew = new ArrayList<Cuentas>();
            for (Cuentas cuentasListNewCuentasToAttach : cuentasListNew) {
                cuentasListNewCuentasToAttach = em.getReference(cuentasListNewCuentasToAttach.getClass(), cuentasListNewCuentasToAttach.getIdCuenta());
                attachedCuentasListNew.add(cuentasListNewCuentasToAttach);
            }
            cuentasListNew = attachedCuentasListNew;
            empresas.setCuentasList(cuentasListNew);
            empresas = em.merge(empresas);
            for (Proveedores proveedoresListOldProveedores : proveedoresListOld) {
                if (!proveedoresListNew.contains(proveedoresListOldProveedores)) {
                    proveedoresListOldProveedores.setIdEmpresa(null);
                    proveedoresListOldProveedores = em.merge(proveedoresListOldProveedores);
                }
            }
            for (Proveedores proveedoresListNewProveedores : proveedoresListNew) {
                if (!proveedoresListOld.contains(proveedoresListNewProveedores)) {
                    Empresas oldIdEmpresaOfProveedoresListNewProveedores = proveedoresListNewProveedores.getIdEmpresa();
                    proveedoresListNewProveedores.setIdEmpresa(empresas);
                    proveedoresListNewProveedores = em.merge(proveedoresListNewProveedores);
                    if (oldIdEmpresaOfProveedoresListNewProveedores != null && !oldIdEmpresaOfProveedoresListNewProveedores.equals(empresas)) {
                        oldIdEmpresaOfProveedoresListNewProveedores.getProveedoresList().remove(proveedoresListNewProveedores);
                        oldIdEmpresaOfProveedoresListNewProveedores = em.merge(oldIdEmpresaOfProveedoresListNewProveedores);
                    }
                }
            }
            for (Diario diarioListOldDiario : diarioListOld) {
                if (!diarioListNew.contains(diarioListOldDiario)) {
                    diarioListOldDiario.setIdEmpresa(null);
                    diarioListOldDiario = em.merge(diarioListOldDiario);
                }
            }
            for (Diario diarioListNewDiario : diarioListNew) {
                if (!diarioListOld.contains(diarioListNewDiario)) {
                    Empresas oldIdEmpresaOfDiarioListNewDiario = diarioListNewDiario.getIdEmpresa();
                    diarioListNewDiario.setIdEmpresa(empresas);
                    diarioListNewDiario = em.merge(diarioListNewDiario);
                    if (oldIdEmpresaOfDiarioListNewDiario != null && !oldIdEmpresaOfDiarioListNewDiario.equals(empresas)) {
                        oldIdEmpresaOfDiarioListNewDiario.getDiarioList().remove(diarioListNewDiario);
                        oldIdEmpresaOfDiarioListNewDiario = em.merge(oldIdEmpresaOfDiarioListNewDiario);
                    }
                }
            }
            for (Banco bancoListNewBanco : bancoListNew) {
                if (!bancoListOld.contains(bancoListNewBanco)) {
                    Empresas oldIdEmpresaOfBancoListNewBanco = bancoListNewBanco.getIdEmpresa();
                    bancoListNewBanco.setIdEmpresa(empresas);
                    bancoListNewBanco = em.merge(bancoListNewBanco);
                    if (oldIdEmpresaOfBancoListNewBanco != null && !oldIdEmpresaOfBancoListNewBanco.equals(empresas)) {
                        oldIdEmpresaOfBancoListNewBanco.getBancoList().remove(bancoListNewBanco);
                        oldIdEmpresaOfBancoListNewBanco = em.merge(oldIdEmpresaOfBancoListNewBanco);
                    }
                }
            }
            for (Cheque chequeListOldCheque : chequeListOld) {
                if (!chequeListNew.contains(chequeListOldCheque)) {
                    chequeListOldCheque.setIdEmpresa(null);
                    chequeListOldCheque = em.merge(chequeListOldCheque);
                }
            }
            for (Cheque chequeListNewCheque : chequeListNew) {
                if (!chequeListOld.contains(chequeListNewCheque)) {
                    Empresas oldIdEmpresaOfChequeListNewCheque = chequeListNewCheque.getIdEmpresa();
                    chequeListNewCheque.setIdEmpresa(empresas);
                    chequeListNewCheque = em.merge(chequeListNewCheque);
                    if (oldIdEmpresaOfChequeListNewCheque != null && !oldIdEmpresaOfChequeListNewCheque.equals(empresas)) {
                        oldIdEmpresaOfChequeListNewCheque.getChequeList().remove(chequeListNewCheque);
                        oldIdEmpresaOfChequeListNewCheque = em.merge(oldIdEmpresaOfChequeListNewCheque);
                    }
                }
            }
            for (Cuentas cuentasListOldCuentas : cuentasListOld) {
                if (!cuentasListNew.contains(cuentasListOldCuentas)) {
                    cuentasListOldCuentas.setIdEmpresa(null);
                    cuentasListOldCuentas = em.merge(cuentasListOldCuentas);
                }
            }
            for (Cuentas cuentasListNewCuentas : cuentasListNew) {
                if (!cuentasListOld.contains(cuentasListNewCuentas)) {
                    Empresas oldIdEmpresaOfCuentasListNewCuentas = cuentasListNewCuentas.getIdEmpresa();
                    cuentasListNewCuentas.setIdEmpresa(empresas);
                    cuentasListNewCuentas = em.merge(cuentasListNewCuentas);
                    if (oldIdEmpresaOfCuentasListNewCuentas != null && !oldIdEmpresaOfCuentasListNewCuentas.equals(empresas)) {
                        oldIdEmpresaOfCuentasListNewCuentas.getCuentasList().remove(cuentasListNewCuentas);
                        oldIdEmpresaOfCuentasListNewCuentas = em.merge(oldIdEmpresaOfCuentasListNewCuentas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = empresas.getIdEmpresa();
                if (findEmpresas(id) == null) {
                    throw new NonexistentEntityException("The empresas with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empresas empresas;
            try {
                empresas = em.getReference(Empresas.class, id);
                empresas.getIdEmpresa();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empresas with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Banco> bancoListOrphanCheck = empresas.getBancoList();
            for (Banco bancoListOrphanCheckBanco : bancoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Empresas (" + empresas + ") cannot be destroyed since the Banco " + bancoListOrphanCheckBanco + " in its bancoList field has a non-nullable idEmpresa field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Proveedores> proveedoresList = empresas.getProveedoresList();
            for (Proveedores proveedoresListProveedores : proveedoresList) {
                proveedoresListProveedores.setIdEmpresa(null);
                proveedoresListProveedores = em.merge(proveedoresListProveedores);
            }
            List<Diario> diarioList = empresas.getDiarioList();
            for (Diario diarioListDiario : diarioList) {
                diarioListDiario.setIdEmpresa(null);
                diarioListDiario = em.merge(diarioListDiario);
            }
            List<Cheque> chequeList = empresas.getChequeList();
            for (Cheque chequeListCheque : chequeList) {
                chequeListCheque.setIdEmpresa(null);
                chequeListCheque = em.merge(chequeListCheque);
            }
            List<Cuentas> cuentasList = empresas.getCuentasList();
            for (Cuentas cuentasListCuentas : cuentasList) {
                cuentasListCuentas.setIdEmpresa(null);
                cuentasListCuentas = em.merge(cuentasListCuentas);
            }
            em.remove(empresas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Empresas> findEmpresasEntities() {
        return findEmpresasEntities(true, -1, -1);
    }

    public List<Empresas> findEmpresasEntities(int maxResults, int firstResult) {
        return findEmpresasEntities(false, maxResults, firstResult);
    }

    private List<Empresas> findEmpresasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Empresas.class));
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

    public Empresas findEmpresas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Empresas.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmpresasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Empresas> rt = cq.from(Empresas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
