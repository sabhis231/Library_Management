/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sabhis.Library_Management.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sabhis231
 */
@Entity
@Table(name = "book_loan_status")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BookLoanStatus.findAll", query = "SELECT b FROM BookLoanStatus b"),
    @NamedQuery(name = "BookLoanStatus.findByBookLoanId", query = "SELECT b FROM BookLoanStatus b WHERE b.bookLoanId = :bookLoanId"),
    @NamedQuery(name = "BookLoanStatus.findByOnCreated", query = "SELECT b FROM BookLoanStatus b WHERE b.onCreated = :onCreated"),
    @NamedQuery(name = "BookLoanStatus.findByOnModified", query = "SELECT b FROM BookLoanStatus b WHERE b.onModified = :onModified"),
    @NamedQuery(name = "BookLoanStatus.findByDescription", query = "SELECT b FROM BookLoanStatus b WHERE b.description = :description"),
    @NamedQuery(name = "BookLoanStatus.findByIsEnabled", query = "SELECT b FROM BookLoanStatus b WHERE b.isEnabled = :isEnabled"),
    @NamedQuery(name = "BookLoanStatus.findByIsDeleted", query = "SELECT b FROM BookLoanStatus b WHERE b.isDeleted = :isDeleted"),
    @NamedQuery(name = "BookLoanStatus.findByIsCanceled", query = "SELECT b FROM BookLoanStatus b WHERE b.isCanceled = :isCanceled"),
    @NamedQuery(name = "BookLoanStatus.findByDueDate", query = "SELECT b FROM BookLoanStatus b WHERE b.dueDate = :dueDate")})
public class BookLoanStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "book_loan_id")
    private Integer bookLoanId;
    @Column(name = "on_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date onCreated;
    @Column(name = "on_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date onModified;
    @Size(max = 45)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_enabled")
    private boolean isEnabled;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_deleted")
    private boolean isDeleted;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_canceled")
    private boolean isCanceled;
    @Column(name = "due_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dueDate;
    @JoinColumn(name = "book_id", referencedColumnName = "book_id")
    @ManyToOne(optional = false)
    private BookDetails bookId;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private CmnUserDetails userId;
    @JoinColumn(name = "loan_status_id", referencedColumnName = "loan_status_id")
    @ManyToOne(optional = false)
    private MasterLoanStatus loanStatusId;

    public BookLoanStatus() {
    }

    public BookLoanStatus(Integer bookLoanId) {
        this.bookLoanId = bookLoanId;
    }

    public BookLoanStatus(Integer bookLoanId, boolean isEnabled, boolean isDeleted, boolean isCanceled) {
        this.bookLoanId = bookLoanId;
        this.isEnabled = isEnabled;
        this.isDeleted = isDeleted;
        this.isCanceled = isCanceled;
    }

    public Integer getBookLoanId() {
        return bookLoanId;
    }

    public void setBookLoanId(Integer bookLoanId) {
        this.bookLoanId = bookLoanId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public boolean getIsCanceled() {
        return isCanceled;
    }

    public void setIsCanceled(boolean isCanceled) {
        this.isCanceled = isCanceled;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public BookDetails getBookId() {
        return bookId;
    }

    public void setBookId(BookDetails bookId) {
        this.bookId = bookId;
    }

    public CmnUserDetails getUserId() {
        return userId;
    }

    public void setUserId(CmnUserDetails userId) {
        this.userId = userId;
    }

    public MasterLoanStatus getLoanStatusId() {
        return loanStatusId;
    }

    public void setLoanStatusId(MasterLoanStatus loanStatusId) {
        this.loanStatusId = loanStatusId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bookLoanId != null ? bookLoanId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BookLoanStatus)) {
            return false;
        }
        BookLoanStatus other = (BookLoanStatus) object;
        if ((this.bookLoanId == null && other.bookLoanId != null) || (this.bookLoanId != null && !this.bookLoanId.equals(other.bookLoanId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sabhis.Library_Management.models.BookLoanStatus[ bookLoanId=" + bookLoanId + " ]";
    }
    
}
