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
import javax.persistence.Id;
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
    @NamedQuery(name = "Partidasdiarios.findAll", query = "SELECT p FROM Partidasdiarios p")
    , @NamedQuery(name = "Partidasdiarios.findByIdPartida", query = "SELECT p FROM Partidasdiarios p WHERE p.idPartida = :idPartida")
    , @NamedQuery(name = "Partidasdiarios.findByIdDiario", query = "SELECT p FROM Partidasdiarios p WHERE p.idDiario = :idDiario")
    , @NamedQuery(name = "Partidasdiarios.findByIdTipoPartida", query = "SELECT p FROM Partidasdiarios p WHERE p.idTipoPartida = :idTipoPartida")
    , @NamedQuery(name = "Partidasdiarios.findByNumPartida", query = "SELECT p FROM Partidasdiarios p WHERE p.numPartida = :numPartida")
    , @NamedQuery(name = "Partidasdiarios.findByFechPartida", query = "SELECT p FROM Partidasdiarios p WHERE p.fechPartida = :fechPartida")
    , @NamedQuery(name = "Partidasdiarios.findByConcepto", query = "SELECT p FROM Partidasdiarios p WHERE p.concepto = :concepto")
    , @NamedQuery(name = "Partidasdiarios.findByCargos", query = "SELECT p FROM Partidasdiarios p WHERE p.cargos = :cargos")
    , @NamedQuery(name = "Partidasdiarios.findByAbonos", query = "SELECT p FROM Partidasdiarios p WHERE p.abonos = :abonos")
    , @NamedQuery(name = "Partidasdiarios.findByCorrecta", query = "SELECT p FROM Partidasdiarios p WHERE p.correcta = :correcta")
    , @NamedQuery(name = "Partidasdiarios.findByMes", query = "SELECT p FROM Partidasdiarios p WHERE p.mes = :mes")
    , @NamedQuery(name = "Partidasdiarios.findByA\u00f1o", query = "SELECT p FROM Partidasdiarios p WHERE p.a\u00f1o = :a\u00f1o")})
public class Partidasdiarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false)
    private int idPartida;
    private Integer idDiario;
    private Integer idTipoPartida;
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

    public Partidasdiarios() {
    }

    public int getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(int idPartida) {
        this.idPartida = idPartida;
    }

    public Integer getIdDiario() {
        return idDiario;
    }

    public void setIdDiario(Integer idDiario) {
        this.idDiario = idDiario;
    }

    public Integer getIdTipoPartida() {
        return idTipoPartida;
    }

    public void setIdTipoPartida(Integer idTipoPartida) {
        this.idTipoPartida = idTipoPartida;
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
    
}
