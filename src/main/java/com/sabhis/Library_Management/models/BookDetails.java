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
@Table(name = "book_details")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BookDetails.findAll", query = "SELECT b FROM BookDetails b"),
    @NamedQuery(name = "BookDetails.findByBookId", query = "SELECT b FROM BookDetails b WHERE b.bookId = :bookId"),
    @NamedQuery(name = "BookDetails.findByCreatedOn", query = "SELECT b FROM BookDetails b WHERE b.createdOn = :createdOn"),
    @NamedQuery(name = "BookDetails.findByModifiedOn", query = "SELECT b FROM BookDetails b WHERE b.modifiedOn = :modifiedOn"),
    @NamedQuery(name = "BookDetails.findByTotalPage", query = "SELECT b FROM BookDetails b WHERE b.totalPage = :totalPage"),
    @NamedQuery(name = "BookDetails.findByPublishedYear", query = "SELECT b FROM BookDetails b WHERE b.publishedYear = :publishedYear"),
    @NamedQuery(name = "BookDetails.findByIsEnabled", query = "SELECT b FROM BookDetails b WHERE b.isEnabled = :isEnabled"),
    @NamedQuery(name = "BookDetails.findByIsDeleted", query = "SELECT b FROM BookDetails b WHERE b.isDeleted = :isDeleted"),
    @NamedQuery(name = "BookDetails.findByDescriptiion", query = "SELECT b FROM BookDetails b WHERE b.descriptiion = :descriptiion")})
public class BookDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "book_id")
    private Integer bookId;
    @Column(name = "created_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    @Column(name = "modified_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedOn;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "book_title")
    private String bookTitle;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total_page")
    private int totalPage;
    @Column(name = "published_year")
    @Temporal(TemporalType.DATE)
    private Date publishedYear;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_enabled")
    private boolean isEnabled;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_deleted")
    private boolean isDeleted;
    @Size(max = 45)
    @Column(name = "descriptiion")
    private String descriptiion;
    @JoinColumn(name = "author_id", referencedColumnName = "author_id")
    @ManyToOne(optional = false)
    private AuthorDetails authorId;
    @JoinColumn(name = "created_by", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private CmnUserDetails createdBy;
    @JoinColumn(name = "status_id", referencedColumnName = "status_id")
    @ManyToOne(optional = false)
    private MasterStatus statusId;
    @JoinColumn(name = "publisher_id", referencedColumnName = "publisher_id")
    @ManyToOne(optional = false)
    private PublisherDetails publisherId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bookId")
    private Collection<BookLoanStatus> bookLoanStatusCollection;

    public BookDetails() {
    }

    public BookDetails(Integer bookId) {
        this.bookId = bookId;
    }

    public BookDetails(Integer bookId, String bookTitle, int totalPage, boolean isEnabled, boolean isDeleted) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.totalPage = totalPage;
        this.isEnabled = isEnabled;
        this.isDeleted = isDeleted;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
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

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public Date getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(Date publishedYear) {
        this.publishedYear = publishedYear;
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

    public String getDescriptiion() {
        return descriptiion;
    }

    public void setDescriptiion(String descriptiion) {
        this.descriptiion = descriptiion;
    }

    public AuthorDetails getAuthorId() {
        return authorId;
    }

    public void setAuthorId(AuthorDetails authorId) {
        this.authorId = authorId;
    }

    public CmnUserDetails getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(CmnUserDetails createdBy) {
        this.createdBy = createdBy;
    }

    public MasterStatus getStatusId() {
        return statusId;
    }

    public void setStatusId(MasterStatus statusId) {
        this.statusId = statusId;
    }

    public PublisherDetails getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(PublisherDetails publisherId) {
        this.publisherId = publisherId;
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
        hash += (bookId != null ? bookId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BookDetails)) {
            return false;
        }
        BookDetails other = (BookDetails) object;
        if ((this.bookId == null && other.bookId != null) || (this.bookId != null && !this.bookId.equals(other.bookId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sabhis.Library_Management.models.BookDetails[ bookId=" + bookId + " ]";
    }
    
}
