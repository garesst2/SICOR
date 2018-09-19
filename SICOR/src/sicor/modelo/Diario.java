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
import javax.persistence.FetchType;
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
 * @author gares
 */
@Entity
@Table(catalog = "sicor", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Diario.findAll", query = "SELECT d FROM Diario d")
    , @NamedQuery(name = "Diario.findByIdDiario", query = "SELECT d FROM Diario d WHERE d.idDiario = :idDiario")
    , @NamedQuery(name = "Diario.findByA\u00f1o", query = "SELECT d FROM Diario d WHERE d.a\u00f1o = :a\u00f1o")
    , @NamedQuery(name = "Diario.findByMes", query = "SELECT d FROM Diario d WHERE d.mes = :mes")
    , @NamedQuery(name = "Diario.findByCargos", query = "SELECT d FROM Diario d WHERE d.cargos = :cargos")
    , @NamedQuery(name = "Diario.findByAbonos", query = "SELECT d FROM Diario d WHERE d.abonos = :abonos")
    , @NamedQuery(name = "Diario.findByMovimientos", query = "SELECT d FROM Diario d WHERE d.movimientos = :movimientos")
    , @NamedQuery(name = "Diario.findByActivo", query = "SELECT d FROM Diario d WHERE d.activo = :activo")})
public class Diario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idDiario;
    @Column(length = 250)
    private String nombDiario;
    private Integer año;
    private Integer mes;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 6, scale = 2)
    private BigDecimal cargos;
    @Column(precision = 6, scale = 2)
    private BigDecimal abonos;
    private Integer movimientos;
    private Boolean activo;
    @JoinColumn(name = "idEmpresa", referencedColumnName = "idEmpresa")
    @ManyToOne(fetch = FetchType.LAZY)
    private Empresas idEmpresa;
    @OneToMany(mappedBy = "idDiario", fetch = FetchType.LAZY)
    private List<Partidas> partidasList;

    public Diario() {
    }

    public Diario(Integer idDiario) {
        this.idDiario = idDiario;
    }

    public Integer getIdDiario() {
        return idDiario;
    }

    public void setIdDiario(Integer idDiario) {
        this.idDiario = idDiario;
    }

    public String getNombDiario() {
        return nombDiario;
    }

    public void setNombDiario(String nombDiario) {
        this.nombDiario = nombDiario;
    }

    public Integer getAño() {
        return año;
    }

    public void setAño(Integer año) {
        this.año = año;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
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

    public Integer getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(Integer movimientos) {
        this.movimientos = movimientos;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Empresas getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Empresas idEmpresa) {
        this.idEmpresa = idEmpresa;
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
        hash += (idDiario != null ? idDiario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Diario)) {
            return false;
        }
        Diario other = (Diario) object;
        if ((this.idDiario == null && other.idDiario != null) || (this.idDiario != null && !this.idDiario.equals(other.idDiario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sicor.modelo.Diario[ idDiario=" + idDiario + " ]";
    }
    
}
