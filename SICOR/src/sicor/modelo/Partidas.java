/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sicor.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    @NamedQuery(name = "Partidas.findAll", query = "SELECT p FROM Partidas p")
    , @NamedQuery(name = "Partidas.findByIdPartida", query = "SELECT p FROM Partidas p WHERE p.idPartida = :idPartida")
    , @NamedQuery(name = "Partidas.findByNumPartida", query = "SELECT p FROM Partidas p WHERE p.numPartida = :numPartida")
    , @NamedQuery(name = "Partidas.findByFechPartida", query = "SELECT p FROM Partidas p WHERE p.fechPartida = :fechPartida")
    , @NamedQuery(name = "Partidas.findByConcepto", query = "SELECT p FROM Partidas p WHERE p.concepto = :concepto")
    , @NamedQuery(name = "Partidas.findByCargos", query = "SELECT p FROM Partidas p WHERE p.cargos = :cargos")
    , @NamedQuery(name = "Partidas.findByAbonos", query = "SELECT p FROM Partidas p WHERE p.abonos = :abonos")
    , @NamedQuery(name = "Partidas.findByCorrecta", query = "SELECT p FROM Partidas p WHERE p.correcta = :correcta")
    , @NamedQuery(name = "Partidas.findByMes", query = "SELECT p FROM Partidas p WHERE p.mes = :mes")
    , @NamedQuery(name = "Partidas.findByA\u00f1o", query = "SELECT p FROM Partidas p WHERE p.a\u00f1o = :a\u00f1o")})
public class Partidas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idPartida;
    private Integer numPartida;
    @Temporal(TemporalType.DATE)
    private Date fechPartida;
    @Column(length = 250)
    private String concepto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 8, scale = 2)
    private BigDecimal cargos;
    @Column(precision = 8, scale = 2)
    private BigDecimal abonos;
    private Boolean correcta;
    private Integer mes;
    private Integer año;
    @JoinColumn(name = "idDiario", referencedColumnName = "idDiario")
    @ManyToOne
    private Diario idDiario;
    @JoinColumn(name = "idTipoPartida", referencedColumnName = "idTipoPartida")
    @ManyToOne
    private Tipopartida idTipoPartida;
    @OneToMany(mappedBy = "idPartida")
    private List<Cheque> chequeList;
    @OneToMany(mappedBy = "idPartida")
    private List<Detallepartida> detallepartidaList;

    public Partidas() {
    }

    public Partidas(Integer idPartida) {
        this.idPartida = idPartida;
    }

    public Integer getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(Integer idPartida) {
        this.idPartida = idPartida;
    }

    public Integer getNumPartida() {
        return numPartida;
    }

    public void setNumPartida(Integer numPartida) {
        this.numPartida = numPartida;
    }

    public Date getFechPartida() {
        return fechPartida;
    }

    public void setFechPartida(Date fechPartida) {
        this.fechPartida = fechPartida;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public BigDecimal getCargos() {
        return cargos;
    }

    public void setCargos(BigDecimal cargos) {
        this.cargos = cargos;
    }

    public BigDecimal getAbonos() {
        return abonos;
    }

    public void setAbonos(BigDecimal abonos) {
        this.abonos = abonos;
    }

    public Boolean getCorrecta() {
        return correcta;
    }

    public void setCorrecta(Boolean correcta) {
        this.correcta = correcta;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getAño() {
        return año;
    }

    public void setAño(Integer año) {
        this.año = año;
    }

    public Diario getIdDiario() {
        return idDiario;
    }

    public void setIdDiario(Diario idDiario) {
        this.idDiario = idDiario;
    }

    public Tipopartida getIdTipoPartida() {
        return idTipoPartida;
    }

    public void setIdTipoPartida(Tipopartida idTipoPartida) {
        this.idTipoPartida = idTipoPartida;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPartida != null ? idPartida.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Partidas)) {
            return false;
        }
        Partidas other = (Partidas) object;
        if ((this.idPartida == null && other.idPartida != null) || (this.idPartida != null && !this.idPartida.equals(other.idPartida))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sicor.modelo.Partidas[ idPartida=" + idPartida + " ]";
    }
    
}
