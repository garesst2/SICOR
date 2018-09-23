/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sicor.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrador
 */
@Entity
@Table(catalog = "sicor", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Configuraciones.findAll", query = "SELECT c FROM Configuraciones c")
    , @NamedQuery(name = "Configuraciones.findByIdConfiguracion", query = "SELECT c FROM Configuraciones c WHERE c.idConfiguracion = :idConfiguracion")
    , @NamedQuery(name = "Configuraciones.findByNombConfiguracion", query = "SELECT c FROM Configuraciones c WHERE c.nombConfiguracion = :nombConfiguracion")
    , @NamedQuery(name = "Configuraciones.findByFormatoCuentas", query = "SELECT c FROM Configuraciones c WHERE c.formatoCuentas = :formatoCuentas")})
public class Configuraciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idConfiguracion;
    @Column(length = 50)
    private String nombConfiguracion;
    @Column(length = 250)
    private String formatoCuentas;

    public Configuraciones() {
    }

    public Configuraciones(Integer idConfiguracion) {
        this.idConfiguracion = idConfiguracion;
    }

    public Integer getIdConfiguracion() {
        return idConfiguracion;
    }

    public void setIdConfiguracion(Integer idConfiguracion) {
        this.idConfiguracion = idConfiguracion;
    }

    public String getNombConfiguracion() {
        return nombConfiguracion;
    }

    public void setNombConfiguracion(String nombConfiguracion) {
        this.nombConfiguracion = nombConfiguracion;
    }

    public String getFormatoCuentas() {
        return formatoCuentas;
    }

    public void setFormatoCuentas(String formatoCuentas) {
        this.formatoCuentas = formatoCuentas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConfiguracion != null ? idConfiguracion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Configuraciones)) {
            return false;
        }
        Configuraciones other = (Configuraciones) object;
        if ((this.idConfiguracion == null && other.idConfiguracion != null) || (this.idConfiguracion != null && !this.idConfiguracion.equals(other.idConfiguracion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sicor.modelo.Configuraciones[ idConfiguracion=" + idConfiguracion + " ]";
    }
    
}
