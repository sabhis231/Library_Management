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
@Table(name = "author_details")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuthorDetails.findAll", query = "SELECT a FROM AuthorDetails a"),
    @NamedQuery(name = "AuthorDetails.findByAuthorId", query = "SELECT a FROM AuthorDetails a WHERE a.authorId = :authorId"),
    @NamedQuery(name = "AuthorDetails.findByCreatedOn", query = "SELECT a FROM AuthorDetails a WHERE a.createdOn = :createdOn"),
    @NamedQuery(name = "AuthorDetails.findByModifiedOn", query = "SELECT a FROM AuthorDetails a WHERE a.modifiedOn = :modifiedOn"),
    @NamedQuery(name = "AuthorDetails.findByAuthorName", query = "SELECT a FROM AuthorDetails a WHERE a.authorName = :authorName"),
    @NamedQuery(name = "AuthorDetails.findByIsEnabled", query = "SELECT a FROM AuthorDetails a WHERE a.isEnabled = :isEnabled"),
    @NamedQuery(name = "AuthorDetails.findByIsDeleted", query = "SELECT a FROM AuthorDetails a WHERE a.isDeleted = :isDeleted"),
    @NamedQuery(name = "AuthorDetails.findByDescription", query = "SELECT a FROM AuthorDetails a WHERE a.description = :description")})
public class AuthorDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "author_id")
    private Integer authorId;
    @Column(name = "created_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    @Column(name = "modified_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedOn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "author_name")
    private String authorName;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "authorId")
    private Collection<BookDetails> bookDetailsCollection;

    public AuthorDetails() {
    }

    public AuthorDetails(Integer authorId) {
        this.authorId = authorId;
    }

    public AuthorDetails(Integer authorId, String authorName, boolean isEnabled, boolean isDeleted) {
        this.authorId = authorId;
        this.authorName = authorName;
        this.isEnabled = isEnabled;
        this.isDeleted = isDeleted;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
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

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
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
        hash += (authorId != null ? authorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AuthorDetails)) {
            return false;
        }
        AuthorDetails other = (AuthorDetails) object;
        if ((this.authorId == null && other.authorId != null) || (this.authorId != null && !this.authorId.equals(other.authorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sabhis.Library_Management.models.AuthorDetails[ authorId=" + authorId + " ]";
    }
    
}
