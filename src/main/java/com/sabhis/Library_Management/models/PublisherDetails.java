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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "publisher_details")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PublisherDetails.findAll", query = "SELECT p FROM PublisherDetails p"),
    @NamedQuery(name = "PublisherDetails.findByPublisherId", query = "SELECT p FROM PublisherDetails p WHERE p.publisherId = :publisherId"),
    @NamedQuery(name = "PublisherDetails.findByCreatedOn", query = "SELECT p FROM PublisherDetails p WHERE p.createdOn = :createdOn"),
    @NamedQuery(name = "PublisherDetails.findByModifiedOn", query = "SELECT p FROM PublisherDetails p WHERE p.modifiedOn = :modifiedOn"),
    @NamedQuery(name = "PublisherDetails.findByIsEnabled", query = "SELECT p FROM PublisherDetails p WHERE p.isEnabled = :isEnabled"),
    @NamedQuery(name = "PublisherDetails.findByIsDeleted", query = "SELECT p FROM PublisherDetails p WHERE p.isDeleted = :isDeleted"),
    @NamedQuery(name = "PublisherDetails.findByDescription", query = "SELECT p FROM PublisherDetails p WHERE p.description = :description")})
public class PublisherDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "publisher_id")
    private Integer publisherId;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "publisher_name")
    private String publisherName;
    @Column(name = "created_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    @Column(name = "modified_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedOn;
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
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private CmnUserDetails userId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "publisherId")
    private Collection<BookDetails> bookDetailsCollection;

    public PublisherDetails() {
    }

    public PublisherDetails(Integer publisherId) {
        this.publisherId = publisherId;
    }

    public PublisherDetails(Integer publisherId, String publisherName, boolean isEnabled, boolean isDeleted) {
        this.publisherId = publisherId;
        this.publisherName = publisherName;
        this.isEnabled = isEnabled;
        this.isDeleted = isDeleted;
    }

    public Integer getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Integer publisherId) {
        this.publisherId = publisherId;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
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

    public CmnUserDetails getUserId() {
        return userId;
    }

    public void setUserId(CmnUserDetails userId) {
        this.userId = userId;
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
        hash += (publisherId != null ? publisherId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PublisherDetails)) {
            return false;
        }
        PublisherDetails other = (PublisherDetails) object;
        if ((this.publisherId == null && other.publisherId != null) || (this.publisherId != null && !this.publisherId.equals(other.publisherId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sabhis.Library_Management.models.PublisherDetails[ publisherId=" + publisherId + " ]";
    }
    
}
