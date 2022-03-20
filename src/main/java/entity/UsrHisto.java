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

/**
 *
 * @author user
 */
@Entity
@Table(name = "a_usr_histo")
@NamedQueries({
    @NamedQuery(name = "UsrHisto.findAll", query = "SELECT u FROM UsrHisto u"),
    @NamedQuery(name = "UsrHisto.findByIdHistory", query = "SELECT u FROM UsrHisto u WHERE u.idHistory = :idHistory"),
    @NamedQuery(name = "UsrHisto.findByNombreHistory", query = "SELECT u FROM UsrHisto u WHERE u.nombreHistory = :nombreHistory"),
    @NamedQuery(name = "UsrHisto.findByIdBackloog", query = "SELECT u FROM UsrHisto u WHERE u.idBackloog = :idBackloog"),
    @NamedQuery(name = "UsrHisto.findByIdUsuario", query = "SELECT u FROM UsrHisto u WHERE u.idUsuario = :idUsuario")})
public class UsrHisto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_history")
    private Integer idHistory;
    @Size(max = 200)
    @Column(name = "nombre_history")
    private String nombreHistory;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_backloog")
    private int idBackloog;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_usuario")
    private int idUsuario;
    @JoinColumn(name = "a_backloog_id_backloog", referencedColumnName = "id_backloog")
    @ManyToOne(optional = false)
    private Backloog aBackloogIdBackloog;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aUsrHistoIdHistory")
    private List<Sprint> sprintList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aUsrHistoIdHistory")
    private List<Kanban> kanbanList;

    public UsrHisto() {
    }

    public UsrHisto(Integer idHistory) {
        this.idHistory = idHistory;
    }

    public UsrHisto(Integer idHistory, int idBackloog, int idUsuario) {
        this.idHistory = idHistory;
        this.idBackloog = idBackloog;
        this.idUsuario = idUsuario;
    }

    public Integer getIdHistory() {
        return idHistory;
    }

    public void setIdHistory(Integer idHistory) {
        this.idHistory = idHistory;
    }

    public String getNombreHistory() {
        return nombreHistory;
    }

    public void setNombreHistory(String nombreHistory) {
        this.nombreHistory = nombreHistory;
    }

    public int getIdBackloog() {
        return idBackloog;
    }

    public void setIdBackloog(int idBackloog) {
        this.idBackloog = idBackloog;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Backloog getABackloogIdBackloog() {
        return aBackloogIdBackloog;
    }

    public void setABackloogIdBackloog(Backloog aBackloogIdBackloog) {
        this.aBackloogIdBackloog = aBackloogIdBackloog;
    }

    public List<Sprint> getSprintList() {
        return sprintList;
    }

    public void setSprintList(List<Sprint> sprintList) {
        this.sprintList = sprintList;
    }

    public List<Kanban> getKanbanList() {
        return kanbanList;
    }

    public void setKanbanList(List<Kanban> kanbanList) {
        this.kanbanList = kanbanList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHistory != null ? idHistory.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsrHisto)) {
            return false;
        }
        UsrHisto other = (UsrHisto) object;
        if ((this.idHistory == null && other.idHistory != null) || (this.idHistory != null && !this.idHistory.equals(other.idHistory))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.UsrHisto[ idHistory=" + idHistory + " ]";
    }
    
}
