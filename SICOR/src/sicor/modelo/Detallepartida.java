/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sicor.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gares
 */
@Entity
@Table(catalog = "sicor", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detallepartida.findAll", query = "SELECT d FROM Detallepartida d")
    , @NamedQuery(name = "Detallepartida.findByIdDetallePartida", query = "SELECT d FROM Detallepartida d WHERE d.idDetallePartida = :idDetallePartida")
    , @NamedQuery(name = "Detallepartida.findByConcepto", query = "SELECT d FROM Detallepartida d WHERE d.concepto = :concepto")
    , @NamedQuery(name = "Detallepartida.findByAbono", query = "SELECT d FROM Detallepartida d WHERE d.abono = :abono")
    , @NamedQuery(name = "Detallepartida.findByCargo", query = "SELECT d FROM Detallepartida d WHERE d.cargo = :cargo")
    , @NamedQuery(name = "Detallepartida.findByCorrecto", query = "SELECT d FROM Detallepartida d WHERE d.correcto = :correcto")})
public class Detallepartida implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idDetallePartida;
    @Column(length = 250)
    private String concepto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 8, scale = 2)
    private BigDecimal abono;
    @Column(precision = 8, scale = 2)
    private BigDecimal cargo;
    private Boolean correcto;
    @JoinColumn(name = "idPartida", referencedColumnName = "idPartida")
    @ManyToOne(fetch = FetchType.LAZY)
    private Partidas idPartida;
    @JoinColumn(name = "idCuenta", referencedColumnName = "idCuenta")
    @ManyToOne(fetch = FetchType.LAZY)
    private Cuentas idCuenta;

    public Detallepartida() {
    }

    public Detallepartida(Integer idDetallePartida) {
        this.idDetallePartida = idDetallePartida;
    }

    public Integer getIdDetallePartida() {
        return idDetallePartida;
    }

    public void setIdDetallePartida(Integer idDetallePartida) {
        this.idDetallePartida = idDetallePartida;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public BigDecimal getAbono() {
        return abono;
    }

    public void setAbono(BigDecimal abono) {
        this.abono = abono;
    }

    public BigDecimal getCargo() {
        return cargo;
    }

    public void setCargo(BigDecimal cargo) {
        this.cargo = cargo;
    }

    public Boolean getCorrecto() {
        return correcto;
    }

    public void setCorrecto(Boolean correcto) {
        this.correcto = correcto;
    }

    public Partidas getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(Partidas idPartida) {
        this.idPartida = idPartida;
    }

    public Cuentas getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Cuentas idCuenta) {
        this.idCuenta = idCuenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetallePartida != null ? idDetallePartida.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detallepartida)) {
            return false;
        }
        Detallepartida other = (Detallepartida) object;
        if ((this.idDetallePartida == null && other.idDetallePartida != null) || (this.idDetallePartida != null && !this.idDetallePartida.equals(other.idDetallePartida))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sicor.modelo.Detallepartida[ idDetallePartida=" + idDetallePartida + " ]";
    }
    
}
