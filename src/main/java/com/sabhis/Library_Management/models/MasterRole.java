/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sabhis.Library_Management.models;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
 * @author sabhis231
 */
@Entity
@Table(name = "master_role")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterRole.findAll", query = "SELECT m FROM MasterRole m"),
    @NamedQuery(name = "MasterRole.findByRoleId", query = "SELECT m FROM MasterRole m WHERE m.roleId = :roleId"),
    @NamedQuery(name = "MasterRole.findByRoleName", query = "SELECT m FROM MasterRole m WHERE m.roleName = :roleName"),
    @NamedQuery(name = "MasterRole.findByIsEnabled", query = "SELECT m FROM MasterRole m WHERE m.isEnabled = :isEnabled"),
    @NamedQuery(name = "MasterRole.findByIsDeleted", query = "SELECT m FROM MasterRole m WHERE m.isDeleted = :isDeleted"),
    @NamedQuery(name = "MasterRole.findByRoeDescription", query = "SELECT m FROM MasterRole m WHERE m.roeDescription = :roeDescription")})
public class MasterRole implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "role_id")
    private Integer roleId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "role_name")
    private String roleName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_enabled")
    private boolean isEnabled;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_deleted")
    private boolean isDeleted;
    @Size(max = 45)
    @Column(name = "roe_description")
    private String roeDescription;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roleId")
    private Collection<CmnUserDetails> cmnUserDetailsCollection;

    public MasterRole() {
    }

    public MasterRole(Integer roleId) {
        this.roleId = roleId;
    }

    public MasterRole(Integer roleId, String roleName, boolean isEnabled, boolean isDeleted) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.isEnabled = isEnabled;
        this.isDeleted = isDeleted;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public boolean getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getRoeDescription() {
        return roeDescription;
    }

    public void setRoeDescription(String roeDescription) {
        this.roeDescription = roeDescription;
    }

    @XmlTransient
    public Collection<CmnUserDetails> getCmnUserDetailsCollection() {
        return cmnUserDetailsCollection;
    }

    public void setCmnUserDetailsCollection(Collection<CmnUserDetails> cmnUserDetailsCollection) {
        this.cmnUserDetailsCollection = cmnUserDetailsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roleId != null ? roleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterRole)) {
            return false;
        }
        MasterRole other = (MasterRole) object;
        if ((this.roleId == null && other.roleId != null) || (this.roleId != null && !this.roleId.equals(other.roleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sabhis.Library_Management.models.MasterRole[ roleId=" + roleId + " ]";
    }
    
}
