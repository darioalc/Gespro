/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author user
 */
@Embeddable
public class RolUsuarioPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_rol_usu")
    private int idRolUsu;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_usuario")
    private int idUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_proyecto")
    private int idProyecto;

    public RolUsuarioPK() {
    }

    public RolUsuarioPK(int idRolUsu, int idUsuario, int idProyecto) {
        this.idRolUsu = idRolUsu;
        this.idUsuario = idUsuario;
        this.idProyecto = idProyecto;
    }

    public int getIdRolUsu() {
        return idRolUsu;
    }

    public void setIdRolUsu(int idRolUsu) {
        this.idRolUsu = idRolUsu;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idRolUsu;
        hash += (int) idUsuario;
        hash += (int) idProyecto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolUsuarioPK)) {
            return false;
        }
        RolUsuarioPK other = (RolUsuarioPK) object;
        if (this.idRolUsu != other.idRolUsu) {
            return false;
        }
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        if (this.idProyecto != other.idProyecto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.RolUsuarioPK[ idRolUsu=" + idRolUsu + ", idUsuario=" + idUsuario + ", idProyecto=" + idProyecto + " ]";
    }
    
}
