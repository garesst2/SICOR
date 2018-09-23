/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sicor.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
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
    @NamedQuery(name = "Cuentas.findAll", query = "SELECT c FROM Cuentas c")
    , @NamedQuery(name = "Cuentas.findByIdCuenta", query = "SELECT c FROM Cuentas c WHERE c.idCuenta = :idCuenta")
    , @NamedQuery(name = "Cuentas.findByCodCuenta", query = "SELECT c FROM Cuentas c WHERE c.codCuenta = :codCuenta")
    , @NamedQuery(name = "Cuentas.findByNombCuenta", query = "SELECT c FROM Cuentas c WHERE c.nombCuenta = :nombCuenta")
    , @NamedQuery(name = "Cuentas.findByCargoDirecto", query = "SELECT c FROM Cuentas c WHERE c.cargoDirecto = :cargoDirecto")
    , @NamedQuery(name = "Cuentas.findByIdTipoCuenta", query = "SELECT c FROM Cuentas c WHERE c.idTipoCuenta = :idTipoCuenta")
    , @NamedQuery(name = "Cuentas.findByCargo", query = "SELECT c FROM Cuentas c WHERE c.cargo = :cargo")
    , @NamedQuery(name = "Cuentas.findByAbono", query = "SELECT c FROM Cuentas c WHERE c.abono = :abono")})
public class Cuentas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idCuenta;
    @Column(length = 150)
    private String codCuenta;
    @Column(length = 250)
    private String nombCuenta;
    private Boolean cargoDirecto;
    private Integer idTipoCuenta;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 8, scale = 2)
    private BigDecimal cargo;
    @Column(precision = 8, scale = 2)
    private BigDecimal abono;
    @OneToMany(mappedBy = "idCuentaAbono")
    private List<Cheque> chequeList;
    @OneToMany(mappedBy = "idCuentaCargo")
    private List<Cheque> chequeList1;
    @OneToMany(mappedBy = "idCuenta")
    private List<Detallepartida> detallepartidaList;
    @JoinColumn(name = "idEmpresa", referencedColumnName = "idEmpresa")
    @ManyToOne
    private Empresas idEmpresa;
    @OneToMany(mappedBy = "idCuentaPadre")
    private List<Cuentas> cuentasList;
    @JoinColumn(name = "idCuentaPadre", referencedColumnName = "idCuenta")
    @ManyToOne
    private Cuentas idCuentaPadre;

    public Cuentas() {
    }

    public Cuentas(Integer idCuenta) {
        this.idCuenta = idCuenta;
    }

    public Integer getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Integer idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getCodCuenta() {
        return codCuenta;
    }

    public void setCodCuenta(String codCuenta) {
        this.codCuenta = codCuenta;
    }

    public String getNombCuenta() {
        return nombCuenta;
    }

    public void setNombCuenta(String nombCuenta) {
        this.nombCuenta = nombCuenta;
    }

    public Boolean getCargoDirecto() {
        return cargoDirecto;
    }

    public void setCargoDirecto(Boolean cargoDirecto) {
        this.cargoDirecto = cargoDirecto;
    }

    public Integer getIdTipoCuenta() {
        return idTipoCuenta;
    }

    public void setIdTipoCuenta(Integer idTipoCuenta) {
        this.idTipoCuenta = idTipoCuenta;
    }

    public BigDecimal getCargo() {
        return cargo;
    }

    public void setCargo(BigDecimal cargo) {
        this.cargo = cargo;
    }

    public BigDecimal getAbono() {
        return abono;
    }

    public void setAbono(BigDecimal abono) {
        this.abono = abono;
    }

    @XmlTransient
    public List<Cheque> getChequeList() {
        return chequeList;
    }

    public void setChequeList(List<Cheque> chequeList) {
        this.chequeList = chequeList;
    }

    @XmlTransient
    public List<Cheque> getChequeList1() {
        return chequeList1;
    }

    public void setChequeList1(List<Cheque> chequeList1) {
        this.chequeList1 = chequeList1;
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

    @XmlTransient
    public List<Cuentas> getCuentasList() {
        return cuentasList;
    }

    public void setCuentasList(List<Cuentas> cuentasList) {
        this.cuentasList = cuentasList;
    }

    public Cuentas getIdCuentaPadre() {
        return idCuentaPadre;
    }

    public void setIdCuentaPadre(Cuentas idCuentaPadre) {
        this.idCuentaPadre = idCuentaPadre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCuenta != null ? idCuenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cuentas)) {
            return false;
        }
        Cuentas other = (Cuentas) object;
        if ((this.idCuenta == null && other.idCuenta != null) || (this.idCuenta != null && !this.idCuenta.equals(other.idCuenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sicor.modelo.Cuentas[ idCuenta=" + idCuenta + " ]";
    }
    
}
