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
@Table(name = "cmn_user_details")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmnUserDetails.findAll", query = "SELECT c FROM CmnUserDetails c"),
    @NamedQuery(name = "CmnUserDetails.findByUserId", query = "SELECT c FROM CmnUserDetails c WHERE c.userId = :userId"),
    @NamedQuery(name = "CmnUserDetails.findByCreatedOn", query = "SELECT c FROM CmnUserDetails c WHERE c.createdOn = :createdOn"),
    @NamedQuery(name = "CmnUserDetails.findByModifiedOn", query = "SELECT c FROM CmnUserDetails c WHERE c.modifiedOn = :modifiedOn"),
    @NamedQuery(name = "CmnUserDetails.findByEmailId", query = "SELECT c FROM CmnUserDetails c WHERE c.emailId = :emailId"),
    @NamedQuery(name = "CmnUserDetails.findByName", query = "SELECT c FROM CmnUserDetails c WHERE c.name = :name"),
    @NamedQuery(name = "CmnUserDetails.findByPassword", query = "SELECT c FROM CmnUserDetails c WHERE c.password = :password"),
    @NamedQuery(name = "CmnUserDetails.findByIsEnabled", query = "SELECT c FROM CmnUserDetails c WHERE c.isEnabled = :isEnabled"),
    @NamedQuery(name = "CmnUserDetails.findByIsDeleted", query = "SELECT c FROM CmnUserDetails c WHERE c.isDeleted = :isDeleted"),
    @NamedQuery(name = "CmnUserDetails.findByImagePath", query = "SELECT c FROM CmnUserDetails c WHERE c.imagePath = :imagePath"),
    @NamedQuery(name = "CmnUserDetails.findByCity", query = "SELECT c FROM CmnUserDetails c WHERE c.city = :city"),
    @NamedQuery(name = "CmnUserDetails.findByCounty", query = "SELECT c FROM CmnUserDetails c WHERE c.county = :county"),
    @NamedQuery(name = "CmnUserDetails.findByZipcode", query = "SELECT c FROM CmnUserDetails c WHERE c.zipcode = :zipcode"),
    @NamedQuery(name = "CmnUserDetails.findByDateOfBirth", query = "SELECT c FROM CmnUserDetails c WHERE c.dateOfBirth = :dateOfBirth"),
    @NamedQuery(name = "CmnUserDetails.findByGender", query = "SELECT c FROM CmnUserDetails c WHERE c.gender = :gender")})
public class CmnUserDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "created_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    @Column(name = "modified_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedOn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "email_id")
    private String emailId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_enabled")
    private boolean isEnabled;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_deleted")
    private boolean isDeleted;
    @Size(max = 45)
    @Column(name = "image_path")
    private String imagePath;
    @Size(max = 45)
    @Column(name = "city")
    private String city;
    @Size(max = 45)
    @Column(name = "county")
    private String county;
    @Size(max = 45)
    @Column(name = "zipcode")
    private String zipcode;
    @Lob
    @Size(max = 65535)
    @Column(name = "about")
    private String about;
    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @Size(max = 45)
    @Column(name = "gender")
    private String gender;
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    @ManyToOne(optional = false)
    private MasterRole roleId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<AuthorDetails> authorDetailsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<PublisherDetails> publisherDetailsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdBy")
    private Collection<BookDetails> bookDetailsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<BookLoanStatus> bookLoanStatusCollection;

    public CmnUserDetails() {
    }

    public CmnUserDetails(Integer userId) {
        this.userId = userId;
    }

    public CmnUserDetails(Integer userId, String emailId, String name, String password, boolean isEnabled, boolean isDeleted) {
        this.userId = userId;
        this.emailId = emailId;
        this.name = name;
        this.password = password;
        this.isEnabled = isEnabled;
        this.isDeleted = isDeleted;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public MasterRole getRoleId() {
        return roleId;
    }

    public void setRoleId(MasterRole roleId) {
        this.roleId = roleId;
    }

    @XmlTransient
    public Collection<AuthorDetails> getAuthorDetailsCollection() {
        return authorDetailsCollection;
    }

    public void setAuthorDetailsCollection(Collection<AuthorDetails> authorDetailsCollection) {
        this.authorDetailsCollection = authorDetailsCollection;
    }

    @XmlTransient
    public Collection<PublisherDetails> getPublisherDetailsCollection() {
        return publisherDetailsCollection;
    }

    public void setPublisherDetailsCollection(Collection<PublisherDetails> publisherDetailsCollection) {
        this.publisherDetailsCollection = publisherDetailsCollection;
    }

    @XmlTransient
    public Collection<BookDetails> getBookDetailsCollection() {
        return bookDetailsCollection;
    }

    public void setBookDetailsCollection(Collection<BookDetails> bookDetailsCollection) {
        this.bookDetailsCollection = bookDetailsCollection;
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
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CmnUserDetails)) {
            return false;
        }
        CmnUserDetails other = (CmnUserDetails) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sabhis.Library_Management.models.CmnUserDetails[ userId=" + userId + " ]";
    }
    
}
