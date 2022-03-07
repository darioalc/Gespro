/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author user
 */
@Entity
@Table(name = "a_sprint")
@NamedQueries({
    @NamedQuery(name = "Sprint.findAll", query = "SELECT s FROM Sprint s"),
    @NamedQuery(name = "Sprint.findByIdSprint", query = "SELECT s FROM Sprint s WHERE s.idSprint = :idSprint"),
    @NamedQuery(name = "Sprint.findByNombreSprint", query = "SELECT s FROM Sprint s WHERE s.nombreSprint = :nombreSprint"),
    @NamedQuery(name = "Sprint.findByDescripcion", query = "SELECT s FROM Sprint s WHERE s.descripcion = :descripcion"),
    @NamedQuery(name = "Sprint.findByIdHistory", query = "SELECT s FROM Sprint s WHERE s.idHistory = :idHistory"),
    @NamedQuery(name = "Sprint.findByFechaInicio", query = "SELECT s FROM Sprint s WHERE s.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "Sprint.findByFechiaFin", query = "SELECT s FROM Sprint s WHERE s.fechiaFin = :fechiaFin"),
    @NamedQuery(name = "Sprint.findByIdEstado", query = "SELECT s FROM Sprint s WHERE s.idEstado = :idEstado")})
public class Sprint implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_sprint")
    private Integer idSprint;
    @Size(max = 150)
    @Column(name = "nombre_sprint")
    private String nombreSprint;
    @Size(max = 200)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_history")
    private int idHistory;
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fechia_fin")
    @Temporal(TemporalType.DATE)
    private Date fechiaFin;
    @Size(max = 30)
    @Column(name = "id_estado")
    private String idEstado;
    @JoinColumn(name = "estados_id_estados", referencedColumnName = "id_estado")
    @ManyToOne(optional = false)
    private Estado estadosIdEstados;
    @JoinColumn(name = "a_usr_histo_id_history", referencedColumnName = "id_history")
    @ManyToOne(optional = false)
    private UsrHisto aUsrHistoIdHistory;

    public Sprint() {
    }

    public Sprint(Integer idSprint) {
        this.idSprint = idSprint;
    }

    public Sprint(Integer idSprint, int idHistory) {
        this.idSprint = idSprint;
        this.idHistory = idHistory;
    }

    public Integer getIdSprint() {
        return idSprint;
    }

    public void setIdSprint(Integer idSprint) {
        this.idSprint = idSprint;
    }

    public String getNombreSprint() {
        return nombreSprint;
    }

    public void setNombreSprint(String nombreSprint) {
        this.nombreSprint = nombreSprint;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdHistory() {
        return idHistory;
    }

    public void setIdHistory(int idHistory) {
        this.idHistory = idHistory;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechiaFin() {
        return fechiaFin;
    }

    public void setFechiaFin(Date fechiaFin) {
        this.fechiaFin = fechiaFin;
    }

    public String getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(String idEstado) {
        this.idEstado = idEstado;
    }

    public Estado getEstadosIdEstados() {
        return estadosIdEstados;
    }

    public void setEstadosIdEstados(Estado estadosIdEstados) {
        this.estadosIdEstados = estadosIdEstados;
    }

    public UsrHisto getAUsrHistoIdHistory() {
        return aUsrHistoIdHistory;
    }

    public void setAUsrHistoIdHistory(UsrHisto aUsrHistoIdHistory) {
        this.aUsrHistoIdHistory = aUsrHistoIdHistory;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSprint != null ? idSprint.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sprint)) {
            return false;
        }
        Sprint other = (Sprint) object;
        if ((this.idSprint == null && other.idSprint != null) || (this.idSprint != null && !this.idSprint.equals(other.idSprint))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Sprint[ idSprint=" + idSprint + " ]";
    }
    
}
