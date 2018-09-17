/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sicor.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author gares
 */
@Entity
@Table(catalog = "sicor", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empresas.findAll", query = "SELECT e FROM Empresas e")
    , @NamedQuery(name = "Empresas.findByIdEmpresa", query = "SELECT e FROM Empresas e WHERE e.idEmpresa = :idEmpresa")
    , @NamedQuery(name = "Empresas.findByCodEmpresa", query = "SELECT e FROM Empresas e WHERE e.codEmpresa = :codEmpresa")
    , @NamedQuery(name = "Empresas.findByRazonSocial", query = "SELECT e FROM Empresas e WHERE e.razonSocial = :razonSocial")
    , @NamedQuery(name = "Empresas.findByRepre", query = "SELECT e FROM Empresas e WHERE e.repre = :repre")
    , @NamedQuery(name = "Empresas.findByTel", query = "SELECT e FROM Empresas e WHERE e.tel = :tel")
    , @NamedQuery(name = "Empresas.findByConciliacion", query = "SELECT e FROM Empresas e WHERE e.conciliacion = :conciliacion")
    , @NamedQuery(name = "Empresas.findByRecuDatos", query = "SELECT e FROM Empresas e WHERE e.recuDatos = :recuDatos")})
public class Empresas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idEmpresa;
    @Column(length = 50)
    private String codEmpresa;
    @Column(length = 250)
    private String razonSocial;
    @Column(length = 250)
    private String repre;
    @Column(length = 250)
    private String tel;
    private Boolean conciliacion;
    private Boolean recuDatos;
    @OneToMany(mappedBy = "idEmpresa", fetch = FetchType.LAZY)
    private List<Proveedores> proveedoresList;
    @OneToMany(mappedBy = "idEmpresa", fetch = FetchType.LAZY)
    private List<Diario> diarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmpresa", fetch = FetchType.LAZY)
    private List<Banco> bancoList;
    @OneToMany(mappedBy = "idEmpresa", fetch = FetchType.LAZY)
    private List<Cheque> chequeList;
    @OneToMany(mappedBy = "idEmpresa", fetch = FetchType.LAZY)
    private List<Cuentas> cuentasList;

    public Empresas() {
    }

    public Empresas(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(String codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getRepre() {
        return repre;
    }

    public void setRepre(String repre) {
        this.repre = repre;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Boolean getConciliacion() {
        return conciliacion;
    }

    public void setConciliacion(Boolean conciliacion) {
        this.conciliacion = conciliacion;
    }

    public Boolean getRecuDatos() {
        return recuDatos;
    }

    public void setRecuDatos(Boolean recuDatos) {
        this.recuDatos = recuDatos;
    }

    @XmlTransient
    public List<Proveedores> getProveedoresList() {
        return proveedoresList;
    }

    public void setProveedoresList(List<Proveedores> proveedoresList) {
        this.proveedoresList = proveedoresList;
    }

    @XmlTransient
    public List<Diario> getDiarioList() {
        return diarioList;
    }

    public void setDiarioList(List<Diario> diarioList) {
        this.diarioList = diarioList;
    }

    @XmlTransient
    public List<Banco> getBancoList() {
        return bancoList;
    }

    public void setBancoList(List<Banco> bancoList) {
        this.bancoList = bancoList;
    }

    @XmlTransient
    public List<Cheque> getChequeList() {
        return chequeList;
    }

    public void setChequeList(List<Cheque> chequeList) {
        this.chequeList = chequeList;
    }

    @XmlTransient
    public List<Cuentas> getCuentasList() {
        return cuentasList;
    }

    public void setCuentasList(List<Cuentas> cuentasList) {
        this.cuentasList = cuentasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmpresa != null ? idEmpresa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empresas)) {
            return false;
        }
        Empresas other = (Empresas) object;
        if ((this.idEmpresa == null && other.idEmpresa != null) || (this.idEmpresa != null && !this.idEmpresa.equals(other.idEmpresa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sicor.modelo.Empresas[ idEmpresa=" + idEmpresa + " ]";
    }
    
}
