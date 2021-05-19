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
@Table(name = "master_status")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterStatus.findAll", query = "SELECT m FROM MasterStatus m"),
    @NamedQuery(name = "MasterStatus.findByStatusId", query = "SELECT m FROM MasterStatus m WHERE m.statusId = :statusId"),
    @NamedQuery(name = "MasterStatus.findByStatusName", query = "SELECT m FROM MasterStatus m WHERE m.statusName = :statusName"),
    @NamedQuery(name = "MasterStatus.findByIsEnabled", query = "SELECT m FROM MasterStatus m WHERE m.isEnabled = :isEnabled"),
    @NamedQuery(name = "MasterStatus.findByIsDeleted", query = "SELECT m FROM MasterStatus m WHERE m.isDeleted = :isDeleted"),
    @NamedQuery(name = "MasterStatus.findByDescription", query = "SELECT m FROM MasterStatus m WHERE m.description = :description")})
public class MasterStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "status_id")
    private Integer statusId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "status_name")
    private String statusName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_enabled")
    private boolean isEnabled;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_deleted")
    private boolean isDeleted;
    @Size(max = 45)
    @Column(name = "description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "statusId")
    private Collection<BookDetails> bookDetailsCollection;

    public MasterStatus() {
    }

    public MasterStatus(Integer statusId) {
        this.statusId = statusId;
    }

    public MasterStatus(Integer statusId, String statusName, boolean isEnabled, boolean isDeleted) {
        this.statusId = statusId;
        this.statusName = statusName;
        this.isEnabled = isEnabled;
        this.isDeleted = isDeleted;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public Collection<BookDetails> getBookDetailsCollection() {
        return bookDetailsCollection;
    }

    public void setBookDetailsCollection(Collection<BookDetails> bookDetailsCollection) {
        this.bookDetailsCollection = bookDetailsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (statusId != null ? statusId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterStatus)) {
            return false;
        }
        MasterStatus other = (MasterStatus) object;
        if ((this.statusId == null && other.statusId != null) || (this.statusId != null && !this.statusId.equals(other.statusId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sabhis.Library_Management.models.MasterStatus[ statusId=" + statusId + " ]";
    }
    
}
