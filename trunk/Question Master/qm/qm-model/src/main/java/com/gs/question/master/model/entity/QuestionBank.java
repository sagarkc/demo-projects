/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gs.question.master.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sabuj
 */
@Entity
@Table(name = "question_bank")
@NamedQueries({
    @NamedQuery(name = "QuestionBank.findAll", query = "SELECT q FROM QuestionBank q"),
    @NamedQuery(name = "QuestionBank.findByBankId", query = "SELECT q FROM QuestionBank q WHERE q.bankId = :bankId"),
    @NamedQuery(name = "QuestionBank.findByBankName", query = "SELECT q FROM QuestionBank q WHERE q.bankName = :bankName"),
    @NamedQuery(name = "QuestionBank.findByCreatedDate", query = "SELECT q FROM QuestionBank q WHERE q.createdDate = :createdDate"),
    @NamedQuery(name = "QuestionBank.findByUpdatedDate", query = "SELECT q FROM QuestionBank q WHERE q.updatedDate = :updatedDate"),
    @NamedQuery(name = "QuestionBank.findByDescription", query = "SELECT q FROM QuestionBank q WHERE q.description = :description")})
public class QuestionBank implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "bank_id")
    private Long bankId;
    @Basic(optional = false)
    @Column(name = "bank_name")
    private String bankName;
    @Basic(optional = false)
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "access_type", referencedColumnName = "type")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MstrAccessTypes accessType;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User userId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "questionBankId", fetch = FetchType.LAZY)
    private Set<Question> questionSet;

    public QuestionBank() {
    }

    public QuestionBank(Long bankId) {
        this.bankId = bankId;
    }

    public QuestionBank(Long bankId, String bankName, Date createdDate) {
        this.bankId = bankId;
        this.bankName = bankName;
        this.createdDate = createdDate;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MstrAccessTypes getAccessType() {
        return accessType;
    }

    public void setAccessType(MstrAccessTypes accessType) {
        this.accessType = accessType;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Set<Question> getQuestionSet() {
        return questionSet;
    }

    public void setQuestionSet(Set<Question> questionSet) {
        this.questionSet = questionSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bankId != null ? bankId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QuestionBank)) {
            return false;
        }
        QuestionBank other = (QuestionBank) object;
        if ((this.bankId == null && other.bankId != null) || (this.bankId != null && !this.bankId.equals(other.bankId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gs.question.master.model.entity.QuestionBank[ bankId=" + bankId + " ]";
    }
    
}
