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
    @NamedQuery(name = "Tipopartida.findAll", query = "SELECT t FROM Tipopartida t")
    , @NamedQuery(name = "Tipopartida.findByIdTipoPartida", query = "SELECT t FROM Tipopartida t WHERE t.idTipoPartida = :idTipoPartida")
    , @NamedQuery(name = "Tipopartida.findByNombTipoPartida", query = "SELECT t FROM Tipopartida t WHERE t.nombTipoPartida = :nombTipoPartida")})
public class Tipopartida implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idTipoPartida;
    @Column(length = 50)
    private String nombTipoPartida;
    @OneToMany(mappedBy = "idTipoPartida", fetch = FetchType.LAZY)
    private List<Partidas> partidasList;

    public Tipopartida() {
    }

    public Tipopartida(Integer idTipoPartida) {
        this.idTipoPartida = idTipoPartida;
    }

    public Integer getIdTipoPartida() {
        return idTipoPartida;
    }

    public void setIdTipoPartida(Integer idTipoPartida) {
        this.idTipoPartida = idTipoPartida;
    }

    public String getNombTipoPartida() {
        return nombTipoPartida;
    }

    public void setNombTipoPartida(String nombTipoPartida) {
        this.nombTipoPartida = nombTipoPartida;
    }

    @XmlTransient
    public List<Partidas> getPartidasList() {
        return partidasList;
    }

    public void setPartidasList(List<Partidas> partidasList) {
        this.partidasList = partidasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoPartida != null ? idTipoPartida.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipopartida)) {
            return false;
        }
        Tipopartida other = (Tipopartida) object;
        if ((this.idTipoPartida == null && other.idTipoPartida != null) || (this.idTipoPartida != null && !this.idTipoPartida.equals(other.idTipoPartida))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sicor.modelo.Tipopartida[ idTipoPartida=" + idTipoPartida + " ]";
    }
    
}
