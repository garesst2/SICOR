/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sicor.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Administrador
 */
@Entity
@Table(catalog = "sicor", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proveedores.findAll", query = "SELECT p FROM Proveedores p")
    , @NamedQuery(name = "Proveedores.findByIdProveedor", query = "SELECT p FROM Proveedores p WHERE p.idProveedor = :idProveedor")
    , @NamedQuery(name = "Proveedores.findByNombProveedor", query = "SELECT p FROM Proveedores p WHERE p.nombProveedor = :nombProveedor")
    , @NamedQuery(name = "Proveedores.findByRegIVA", query = "SELECT p FROM Proveedores p WHERE p.regIVA = :regIVA")
    , @NamedQuery(name = "Proveedores.findByTel", query = "SELECT p FROM Proveedores p WHERE p.tel = :tel")
    , @NamedQuery(name = "Proveedores.findByDirec", query = "SELECT p FROM Proveedores p WHERE p.direc = :direc")
    , @NamedQuery(name = "Proveedores.findByDoc", query = "SELECT p FROM Proveedores p WHERE p.doc = :doc")})
public class Proveedores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idProveedor;
    @Column(length = 250)
    private String nombProveedor;
    @Column(length = 250)
    private String regIVA;
    @Column(length = 250)
    private String tel;
    @Column(length = 250)
    private String direc;
    @Column(length = 250)
    private String doc;
    @OneToMany(mappedBy = "idProveedor")
    private List<Cheque> chequeList;
    @OneToMany(mappedBy = "idProveedor")
    private List<Detallepartida> detallepartidaList;
    @JoinColumn(name = "idEmpresa", referencedColumnName = "idEmpresa")
    @ManyToOne
    private Empresas idEmpresa;

    public Proveedores() {
    }

    public Proveedores(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombProveedor() {
        return nombProveedor;
    }

    public void setNombProveedor(String nombProveedor) {
        this.nombProveedor = nombProveedor;
    }

    public String getRegIVA() {
        return regIVA;
    }

    public void setRegIVA(String regIVA) {
        this.regIVA = regIVA;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDirec() {
        return direc;
    }

    public void setDirec(String direc) {
        this.direc = direc;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    @XmlTransient
    public List<Cheque> getChequeList() {
        return chequeList;
    }

    public void setChequeList(List<Cheque> chequeList) {
        this.chequeList = chequeList;
    }

    @XmlTransient
    public List<Detallepartida> getDetallepartidaList() {
        return detallepartidaList;
    }

    public void setDetallepartidaList(List<Detallepartida> detallepartidaList) {
        this.detallepartidaList = detallepartidaList;
    }

    public Empresas getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Empresas idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProveedor != null ? idProveedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proveedores)) {
            return false;
        }
        Proveedores other = (Proveedores) object;
        if ((this.idProveedor == null && other.idProveedor != null) || (this.idProveedor != null && !this.idProveedor.equals(other.idProveedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sicor.modelo.Proveedores[ idProveedor=" + idProveedor + " ]";
    }
    
}
