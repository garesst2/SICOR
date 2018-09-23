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
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u")
    , @NamedQuery(name = "Usuarios.findByIdUsuario", query = "SELECT u FROM Usuarios u WHERE u.idUsuario = :idUsuario")
    , @NamedQuery(name = "Usuarios.findByNombUsuario", query = "SELECT u FROM Usuarios u WHERE u.nombUsuario = :nombUsuario")
    , @NamedQuery(name = "Usuarios.findByUserUsuario", query = "SELECT u FROM Usuarios u WHERE u.userUsuario = :userUsuario")
    , @NamedQuery(name = "Usuarios.findByPswUsuario", query = "SELECT u FROM Usuarios u WHERE u.pswUsuario = :pswUsuario")
    , @NamedQuery(name = "Usuarios.findByTipUsuario", query = "SELECT u FROM Usuarios u WHERE u.tipUsuario = :tipUsuario")
    , @NamedQuery(name = "Usuarios.findByEstaUsuario", query = "SELECT u FROM Usuarios u WHERE u.estaUsuario = :estaUsuario")})
public class Usuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idUsuario;
    @Column(length = 250)
    private String nombUsuario;
    @Column(length = 250)
    private String userUsuario;
    @Column(length = 32)
    private String pswUsuario;
    private Integer tipUsuario;
    private Boolean estaUsuario;

    public Usuarios() {
    }

    public Usuarios(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombUsuario() {
        return nombUsuario;
    }

    public void setNombUsuario(String nombUsuario) {
        this.nombUsuario = nombUsuario;
    }

    public String getUserUsuario() {
        return userUsuario;
    }

    public void setUserUsuario(String userUsuario) {
        this.userUsuario = userUsuario;
    }

    public String getPswUsuario() {
        return pswUsuario;
    }

    public void setPswUsuario(String pswUsuario) {
        this.pswUsuario = pswUsuario;
    }

    public Integer getTipUsuario() {
        return tipUsuario;
    }

    public void setTipUsuario(Integer tipUsuario) {
        this.tipUsuario = tipUsuario;
    }

    public Boolean getEstaUsuario() {
        return estaUsuario;
    }

    public void setEstaUsuario(Boolean estaUsuario) {
        this.estaUsuario = estaUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sicor.modelo.Usuarios[ idUsuario=" + idUsuario + " ]";
    }
    
}
