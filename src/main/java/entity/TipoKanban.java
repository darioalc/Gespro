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
@Table(name = "a_tipo_kanban")
@NamedQueries({
    @NamedQuery(name = "TipoKanban.findAll", query = "SELECT t FROM TipoKanban t"),
    @NamedQuery(name = "TipoKanban.findByIdTipo", query = "SELECT t FROM TipoKanban t WHERE t.idTipo = :idTipo"),
    @NamedQuery(name = "TipoKanban.findByNombreKanban", query = "SELECT t FROM TipoKanban t WHERE t.nombreKanban = :nombreKanban")})
public class TipoKanban implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_tipo")
    private Integer idTipo;
    @Size(max = 20)
    @Column(name = "nombre_kanban")
    private String nombreKanban;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aTipoKanbanIdTipo")
    private List<Kanban> kanbanList;

    public TipoKanban() {
    }

    public TipoKanban(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public Integer getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public String getNombreKanban() {
        return nombreKanban;
    }

    public void setNombreKanban(String nombreKanban) {
        this.nombreKanban = nombreKanban;
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
        hash += (idTipo != null ? idTipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoKanban)) {
            return false;
        }
        TipoKanban other = (TipoKanban) object;
        if ((this.idTipo == null && other.idTipo != null) || (this.idTipo != null && !this.idTipo.equals(other.idTipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TipoKanban[ idTipo=" + idTipo + " ]";
    }
    
}
