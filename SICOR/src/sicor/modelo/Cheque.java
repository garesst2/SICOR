/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sicor.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrador
 */
@Entity
@Table(catalog = "sicor", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cheque.findAll", query = "SELECT c FROM Cheque c")
    , @NamedQuery(name = "Cheque.findByIdCheque", query = "SELECT c FROM Cheque c WHERE c.idCheque = :idCheque")
    , @NamedQuery(name = "Cheque.findBySerieCheque", query = "SELECT c FROM Cheque c WHERE c.serieCheque = :serieCheque")
    , @NamedQuery(name = "Cheque.findByNumCheque", query = "SELECT c FROM Cheque c WHERE c.numCheque = :numCheque")
    , @NamedQuery(name = "Cheque.findByLugarEmision", query = "SELECT c FROM Cheque c WHERE c.lugarEmision = :lugarEmision")
    , @NamedQuery(name = "Cheque.findByFechEmision", query = "SELECT c FROM Cheque c WHERE c.fechEmision = :fechEmision")
    , @NamedQuery(name = "Cheque.findByMontoDinero", query = "SELECT c FROM Cheque c WHERE c.montoDinero = :montoDinero")
    , @NamedQuery(name = "Cheque.findByConceptoGeneral", query = "SELECT c FROM Cheque c WHERE c.conceptoGeneral = :conceptoGeneral")
    , @NamedQuery(name = "Cheque.findByAnulado", query = "SELECT c FROM Cheque c WHERE c.anulado = :anulado")})
public class Cheque implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idCheque;
    @Column(length = 250)
    private String serieCheque;
    @Column(length = 250)
    private String numCheque;
    @Column(length = 250)
    private String lugarEmision;
    @Temporal(TemporalType.DATE)
    private Date fechEmision;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 8, scale = 2)
    private BigDecimal montoDinero;
    @Column(length = 250)
    private String conceptoGeneral;
    private Boolean anulado;
    @JoinColumn(name = "idBanco", referencedColumnName = "idBanco")
    @ManyToOne
    private Banco idBanco;
    @JoinColumn(name = "idCuentaAbono", referencedColumnName = "idCuenta")
    @ManyToOne
    private Cuentas idCuentaAbono;
    @JoinColumn(name = "idEmpresa", referencedColumnName = "idEmpresa")
    @ManyToOne
    private Empresas idEmpresa;
    @JoinColumn(name = "idProveedor", referencedColumnName = "idProveedor")
    @ManyToOne
    private Proveedores idProveedor;
    @JoinColumn(name = "idPartida", referencedColumnName = "idPartida")
    @ManyToOne
    private Partidas idPartida;
    @JoinColumn(name = "idCuentaCargo", referencedColumnName = "idCuenta")
    @ManyToOne
    private Cuentas idCuentaCargo;

    public Cheque() {
    }

    public Cheque(Integer idCheque) {
        this.idCheque = idCheque;
    }

    public Integer getIdCheque() {
        return idCheque;
    }

    public void setIdCheque(Integer idCheque) {
        this.idCheque = idCheque;
    }

    public String getSerieCheque() {
        return serieCheque;
    }

    public void setSerieCheque(String serieCheque) {
        this.serieCheque = serieCheque;
    }

    public String getNumCheque() {
        return numCheque;
    }

    public void setNumCheque(String numCheque) {
        this.numCheque = numCheque;
    }

    public String getLugarEmision() {
        return lugarEmision;
    }

    public void setLugarEmision(String lugarEmision) {
        this.lugarEmision = lugarEmision;
    }

    public Date getFechEmision() {
        return fechEmision;
    }

    public void setFechEmision(Date fechEmision) {
        this.fechEmision = fechEmision;
    }

    public BigDecimal getMontoDinero() {
        return montoDinero;
    }

    public void setMontoDinero(BigDecimal montoDinero) {
        this.montoDinero = montoDinero;
    }

    public String getConceptoGeneral() {
        return conceptoGeneral;
    }

    public void setConceptoGeneral(String conceptoGeneral) {
        this.conceptoGeneral = conceptoGeneral;
    }

    public Boolean getAnulado() {
        return anulado;
    }

    public void setAnulado(Boolean anulado) {
        this.anulado = anulado;
    }

    public Banco getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(Banco idBanco) {
        this.idBanco = idBanco;
    }

    public Cuentas getIdCuentaAbono() {
        return idCuentaAbono;
    }

    public void setIdCuentaAbono(Cuentas idCuentaAbono) {
        this.idCuentaAbono = idCuentaAbono;
    }

    public Empresas getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Empresas idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Proveedores getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Proveedores idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Partidas getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(Partidas idPartida) {
        this.idPartida = idPartida;
    }

    public Cuentas getIdCuentaCargo() {
        return idCuentaCargo;
    }

    public void setIdCuentaCargo(Cuentas idCuentaCargo) {
        this.idCuentaCargo = idCuentaCargo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCheque != null ? idCheque.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cheque)) {
            return false;
        }
        Cheque other = (Cheque) object;
        if ((this.idCheque == null && other.idCheque != null) || (this.idCheque != null && !this.idCheque.equals(other.idCheque))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sicor.modelo.Cheque[ idCheque=" + idCheque + " ]";
    }
    
}
