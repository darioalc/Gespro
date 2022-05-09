/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DarioA
 */
@Entity
@Table(name = "a_backloog")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Backloog.findAll", query = "SELECT b FROM Backloog b")
    , @NamedQuery(name = "Backloog.findByIdBackloog", query = "SELECT b FROM Backloog b WHERE b.idBackloog = :idBackloog")
    , @NamedQuery(name = "Backloog.findByIdProyecto", query = "SELECT b FROM Backloog b WHERE b.idProyecto = :idProyecto")
    , @NamedQuery(name = "Backloog.findByEnunciado", query = "SELECT b FROM Backloog b WHERE b.enunciado = :enunciado")
    , @NamedQuery(name = "Backloog.findByEsfuerzo", query = "SELECT b FROM Backloog b WHERE b.esfuerzo = :esfuerzo")
    , @NamedQuery(name = "Backloog.findByPrioridad", query = "SELECT b FROM Backloog b WHERE b.prioridad = :prioridad")
    , @NamedQuery(name = "Backloog.findByIdHistory", query = "SELECT b FROM Backloog b WHERE b.idHistory = :idHistory")})
public class Backloog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_backloog")
    private Integer idBackloog;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_proyecto")
    private int idProyecto;
    @Size(max = 200)
    @Column(name = "enunciado")
    private String enunciado;
    @Size(max = 200)
    @Column(name = "esfuerzo")
    private String esfuerzo;
    @Size(max = 200)
    @Column(name = "prioridad")
    private String prioridad;
    @Column(name = "id_history")
    private Integer idHistory;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aBackloogIdBackloog")
    private List<UsrHisto> usrHistoList;
    @JoinColumn(name = "a_proyecto_id_proyecto", referencedColumnName = "id_proyecto")
    @ManyToOne(optional = false)
    private Proyecto aProyectoIdProyecto;

    public Backloog() {
    }

    public Backloog(Integer idBackloog) {
        this.idBackloog = idBackloog;
    }

    public Backloog(Integer idBackloog, int idProyecto) {
        this.idBackloog = idBackloog;
        this.idProyecto = idProyecto;
    }

    public Integer getIdBackloog() {
        return idBackloog;
    }

    public void setIdBackloog(Integer idBackloog) {
        this.idBackloog = idBackloog;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getEsfuerzo() {
        return esfuerzo;
    }

    public void setEsfuerzo(String esfuerzo) {
        this.esfuerzo = esfuerzo;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public Integer getIdHistory() {
        return idHistory;
    }

    public void setIdHistory(Integer idHistory) {
        this.idHistory = idHistory;
    }

    @XmlTransient
    public List<UsrHisto> getUsrHistoList() {
        return usrHistoList;
    }

    public void setUsrHistoList(List<UsrHisto> usrHistoList) {
        this.usrHistoList = usrHistoList;
    }

    public Proyecto getAProyectoIdProyecto() {
        return aProyectoIdProyecto;
    }

    public void setAProyectoIdProyecto(Proyecto aProyectoIdProyecto) {
        this.aProyectoIdProyecto = aProyectoIdProyecto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBackloog != null ? idBackloog.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Backloog)) {
            return false;
        }
        Backloog other = (Backloog) object;
        if ((this.idBackloog == null && other.idBackloog != null) || (this.idBackloog != null && !this.idBackloog.equals(other.idBackloog))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Backloog[ idBackloog=" + idBackloog + " ]";
    }
    
}
