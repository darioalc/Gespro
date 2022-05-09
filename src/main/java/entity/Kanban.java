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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DarioA
 */
@Entity
@Table(name = "a_kanban")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kanban.findAll", query = "SELECT k FROM Kanban k")
    , @NamedQuery(name = "Kanban.findByIdKanban", query = "SELECT k FROM Kanban k WHERE k.idKanban = :idKanban")
    , @NamedQuery(name = "Kanban.findByIdEstadoKan", query = "SELECT k FROM Kanban k WHERE k.idEstadoKan = :idEstadoKan")
    , @NamedQuery(name = "Kanban.findByIdUsHis", query = "SELECT k FROM Kanban k WHERE k.idUsHis = :idUsHis")
    , @NamedQuery(name = "Kanban.findByFechaEstado", query = "SELECT k FROM Kanban k WHERE k.fechaEstado = :fechaEstado")
    , @NamedQuery(name = "Kanban.findByComentario", query = "SELECT k FROM Kanban k WHERE k.comentario = :comentario")
    , @NamedQuery(name = "Kanban.findByATipoJanbanDiTipo", query = "SELECT k FROM Kanban k WHERE k.aTipoJanbanDiTipo = :aTipoJanbanDiTipo")})
public class Kanban implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_kanban")
    private Integer idKanban;
    @Column(name = "id_estado_kan")
    private Integer idEstadoKan;
    @Column(name = "id_us_his")
    private Integer idUsHis;
    @Column(name = "fecha_estado")
    @Temporal(TemporalType.DATE)
    private Date fechaEstado;
    @Size(max = 200)
    @Column(name = "comentario")
    private String comentario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "a_tipo_janban_di_tipo")
    private int aTipoJanbanDiTipo;
    @JoinColumn(name = "a_tipo_kanban_id_tipo", referencedColumnName = "id_tipo")
    @ManyToOne(optional = false)
    private TipoKanban aTipoKanbanIdTipo;
    @JoinColumn(name = "a_usr_histo_id_history", referencedColumnName = "id_history")
    @ManyToOne(optional = false)
    private UsrHisto aUsrHistoIdHistory;

    public Kanban() {
    }

    public Kanban(Integer idKanban) {
        this.idKanban = idKanban;
    }

    public Kanban(Integer idKanban, int aTipoJanbanDiTipo) {
        this.idKanban = idKanban;
        this.aTipoJanbanDiTipo = aTipoJanbanDiTipo;
    }

    public Integer getIdKanban() {
        return idKanban;
    }

    public void setIdKanban(Integer idKanban) {
        this.idKanban = idKanban;
    }

    public Integer getIdEstadoKan() {
        return idEstadoKan;
    }

    public void setIdEstadoKan(Integer idEstadoKan) {
        this.idEstadoKan = idEstadoKan;
    }

    public Integer getIdUsHis() {
        return idUsHis;
    }

    public void setIdUsHis(Integer idUsHis) {
        this.idUsHis = idUsHis;
    }

    public Date getFechaEstado() {
        return fechaEstado;
    }

    public void setFechaEstado(Date fechaEstado) {
        this.fechaEstado = fechaEstado;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getATipoJanbanDiTipo() {
        return aTipoJanbanDiTipo;
    }

    public void setATipoJanbanDiTipo(int aTipoJanbanDiTipo) {
        this.aTipoJanbanDiTipo = aTipoJanbanDiTipo;
    }

    public TipoKanban getATipoKanbanIdTipo() {
        return aTipoKanbanIdTipo;
    }

    public void setATipoKanbanIdTipo(TipoKanban aTipoKanbanIdTipo) {
        this.aTipoKanbanIdTipo = aTipoKanbanIdTipo;
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
        hash += (idKanban != null ? idKanban.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kanban)) {
            return false;
        }
        Kanban other = (Kanban) object;
        if ((this.idKanban == null && other.idKanban != null) || (this.idKanban != null && !this.idKanban.equals(other.idKanban))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Kanban[ idKanban=" + idKanban + " ]";
    }
    
}
