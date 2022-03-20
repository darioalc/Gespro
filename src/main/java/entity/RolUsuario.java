/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author user
 */
@Entity
@Table(name = "a_rol_usuario")
@NamedQueries({
    @NamedQuery(name = "RolUsuario.findAll", query = "SELECT r FROM RolUsuario r"),
    @NamedQuery(name = "RolUsuario.findByIdRolUsu", query = "SELECT r FROM RolUsuario r WHERE r.rolUsuarioPK.idRolUsu = :idRolUsu"),
    @NamedQuery(name = "RolUsuario.findByIdUsuario", query = "SELECT r FROM RolUsuario r WHERE r.rolUsuarioPK.idUsuario = :idUsuario"),
    @NamedQuery(name = "RolUsuario.findByIdRol", query = "SELECT r FROM RolUsuario r WHERE r.idRol = :idRol"),
    @NamedQuery(name = "RolUsuario.findByIdProyecto", query = "SELECT r FROM RolUsuario r WHERE r.rolUsuarioPK.idProyecto = :idProyecto")})
public class RolUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RolUsuarioPK rolUsuarioPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_rol")
    private int idRol;
    @JoinColumn(name = "a_proyecto_id_proyecto", referencedColumnName = "id_proyecto")
    @ManyToOne(optional = false)
    private Proyecto aProyectoIdProyecto;
    @JoinColumn(name = "a_rol_id_rol", referencedColumnName = "id_rol")
    @ManyToOne(optional = false)
    private Rol aRolIdRol;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;

    public RolUsuario() {
    }

    public RolUsuario(RolUsuarioPK rolUsuarioPK) {
        this.rolUsuarioPK = rolUsuarioPK;
    }

    public RolUsuario(RolUsuarioPK rolUsuarioPK, int idRol) {
        this.rolUsuarioPK = rolUsuarioPK;
        this.idRol = idRol;
    }

    public RolUsuario(int idRolUsu, int idUsuario, int idProyecto) {
        this.rolUsuarioPK = new RolUsuarioPK(idRolUsu, idUsuario, idProyecto);
    }

    public RolUsuarioPK getRolUsuarioPK() {
        return rolUsuarioPK;
    }

    public void setRolUsuarioPK(RolUsuarioPK rolUsuarioPK) {
        this.rolUsuarioPK = rolUsuarioPK;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public Proyecto getAProyectoIdProyecto() {
        return aProyectoIdProyecto;
    }

    public void setAProyectoIdProyecto(Proyecto aProyectoIdProyecto) {
        this.aProyectoIdProyecto = aProyectoIdProyecto;
    }

    public Rol getARolIdRol() {
        return aRolIdRol;
    }

    public void setARolIdRol(Rol aRolIdRol) {
        this.aRolIdRol = aRolIdRol;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rolUsuarioPK != null ? rolUsuarioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolUsuario)) {
            return false;
        }
        RolUsuario other = (RolUsuario) object;
        if ((this.rolUsuarioPK == null && other.rolUsuarioPK != null) || (this.rolUsuarioPK != null && !this.rolUsuarioPK.equals(other.rolUsuarioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.RolUsuario[ rolUsuarioPK=" + rolUsuarioPK + " ]";
    }
    
}
