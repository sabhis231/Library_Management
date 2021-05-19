/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sabhis.Library_Management.models;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sabhis231
 */
@Entity
@Table(name = "master_loan_status")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterLoanStatus.findAll", query = "SELECT m FROM MasterLoanStatus m"),
    @NamedQuery(name = "MasterLoanStatus.findByLoanStatusId", query = "SELECT m FROM MasterLoanStatus m WHERE m.loanStatusId = :loanStatusId"),
    @NamedQuery(name = "MasterLoanStatus.findByOnCreated", query = "SELECT m FROM MasterLoanStatus m WHERE m.onCreated = :onCreated"),
    @NamedQuery(name = "MasterLoanStatus.findByOnModified", query = "SELECT m FROM MasterLoanStatus m WHERE m.onModified = :onModified"),
    @NamedQuery(name = "MasterLoanStatus.findByStatusName", query = "SELECT m FROM MasterLoanStatus m WHERE m.statusName = :statusName"),
    @NamedQuery(name = "MasterLoanStatus.findByIsEnabled", query = "SELECT m FROM MasterLoanStatus m WHERE m.isEnabled = :isEnabled"),
    @NamedQuery(name = "MasterLoanStatus.findByIsDeleted", query = "SELECT m FROM MasterLoanStatus m WHERE m.isDeleted = :isDeleted"),
    @NamedQuery(name = "MasterLoanStatus.findByDescription", query = "SELECT m FROM MasterLoanStatus m WHERE m.description = :description")})
public class MasterLoanStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "loan_status_id")
    private Integer loanStatusId;
    @Column(name = "on_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date onCreated;
    @Column(name = "on_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date onModified;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "loanStatusId")
    private Collection<BookLoanStatus> bookLoanStatusCollection;

    public MasterLoanStatus() {
    }

    public MasterLoanStatus(Integer loanStatusId) {
        this.loanStatusId = loanStatusId;
    }

    public MasterLoanStatus(Integer loanStatusId, String statusName, boolean isEnabled, boolean isDeleted) {
        this.loanStatusId = loanStatusId;
        this.statusName = statusName;
        this.isEnabled = isEnabled;
        this.isDeleted = isDeleted;
    }

    public Integer getLoanStatusId() {
        return loanStatusId;
    }

    public void setLoanStatusId(Integer loanStatusId) {
        this.loanStatusId = loanStatusId;
    }

    public Date getOnCreated() {
        return onCreated;
    }

    public void setOnCreated(Date onCreated) {
        this.onCreated = onCreated;
    }

    public Date getOnModified() {
        return onModified;
    }

    public void setOnModified(Date onModified) {
        this.onModified = onModified;
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
    public Collection<BookLoanStatus> getBookLoanStatusCollection() {
        return bookLoanStatusCollection;
    }

    public void setBookLoanStatusCollection(Collection<BookLoanStatus> bookLoanStatusCollection) {
        this.bookLoanStatusCollection = bookLoanStatusCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (loanStatusId != null ? loanStatusId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterLoanStatus)) {
            return false;
        }
        MasterLoanStatus other = (MasterLoanStatus) object;
        if ((this.loanStatusId == null && other.loanStatusId != null) || (this.loanStatusId != null && !this.loanStatusId.equals(other.loanStatusId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sabhis.Library_Management.models.MasterLoanStatus[ loanStatusId=" + loanStatusId + " ]";
    }
    
}
