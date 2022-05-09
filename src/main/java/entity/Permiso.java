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
@Table(name = "a_permiso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Permiso.findAll", query = "SELECT p FROM Permiso p")
    , @NamedQuery(name = "Permiso.findByIdPermiso", query = "SELECT p FROM Permiso p WHERE p.idPermiso = :idPermiso")
    , @NamedQuery(name = "Permiso.findByIdModulo", query = "SELECT p FROM Permiso p WHERE p.idModulo = :idModulo")
    , @NamedQuery(name = "Permiso.findByTipoPermiso", query = "SELECT p FROM Permiso p WHERE p.tipoPermiso = :tipoPermiso")
    , @NamedQuery(name = "Permiso.findByObservacion", query = "SELECT p FROM Permiso p WHERE p.observacion = :observacion")})
public class Permiso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_permiso")
    private Integer idPermiso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_modulo")
    private int idModulo;
    @Size(max = 10)
    @Column(name = "tipo_permiso")
    private String tipoPermiso;
    @Size(max = 20)
    @Column(name = "observacion")
    private String observacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aPermisoIdPermiso")
    private List<Rol> rolList;
    @JoinColumn(name = "a_modulo_id_modulo", referencedColumnName = "id_modulo")
    @ManyToOne(optional = false)
    private Modulo aModuloIdModulo;

    public Permiso() {
    }

    public Permiso(Integer idPermiso) {
        this.idPermiso = idPermiso;
    }

    public Permiso(Integer idPermiso, int idModulo) {
        this.idPermiso = idPermiso;
        this.idModulo = idModulo;
    }

    public Integer getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(Integer idPermiso) {
        this.idPermiso = idPermiso;
    }

    public int getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(int idModulo) {
        this.idModulo = idModulo;
    }

    public String getTipoPermiso() {
        return tipoPermiso;
    }

    public void setTipoPermiso(String tipoPermiso) {
        this.tipoPermiso = tipoPermiso;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    @XmlTransient
    public List<Rol> getRolList() {
        return rolList;
    }

    public void setRolList(List<Rol> rolList) {
        this.rolList = rolList;
    }

    public Modulo getAModuloIdModulo() {
        return aModuloIdModulo;
    }

    public void setAModuloIdModulo(Modulo aModuloIdModulo) {
        this.aModuloIdModulo = aModuloIdModulo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPermiso != null ? idPermiso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Permiso)) {
            return false;
        }
        Permiso other = (Permiso) object;
        if ((this.idPermiso == null && other.idPermiso != null) || (this.idPermiso != null && !this.idPermiso.equals(other.idPermiso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Permiso[ idPermiso=" + idPermiso + " ]";
    }
    
}
