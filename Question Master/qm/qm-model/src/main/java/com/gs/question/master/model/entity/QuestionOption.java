/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gs.question.master.model.entity;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "question_option")
@NamedQueries({
    @NamedQuery(name = "QuestionOption.findAll", query = "SELECT q FROM QuestionOption q"),
    @NamedQuery(name = "QuestionOption.findByOptionId", query = "SELECT q FROM QuestionOption q WHERE q.optionId = :optionId"),
    @NamedQuery(name = "QuestionOption.findByOptionText", query = "SELECT q FROM QuestionOption q WHERE q.optionText = :optionText"),
    @NamedQuery(name = "QuestionOption.findByExplanation", query = "SELECT q FROM QuestionOption q WHERE q.explanation = :explanation"),
    @NamedQuery(name = "QuestionOption.findByShowExplanation", query = "SELECT q FROM QuestionOption q WHERE q.showExplanation = :showExplanation"),
    @NamedQuery(name = "QuestionOption.findByDisplayIndex", query = "SELECT q FROM QuestionOption q WHERE q.displayIndex = :displayIndex"),
    @NamedQuery(name = "QuestionOption.findByOptionType", query = "SELECT q FROM QuestionOption q WHERE q.optionType = :optionType"),
    @NamedQuery(name = "QuestionOption.findByCreatedBy", query = "SELECT q FROM QuestionOption q WHERE q.createdBy = :createdBy"),
    @NamedQuery(name = "QuestionOption.findByCreatedDate", query = "SELECT q FROM QuestionOption q WHERE q.createdDate = :createdDate"),
    @NamedQuery(name = "QuestionOption.findByUpdatedBy", query = "SELECT q FROM QuestionOption q WHERE q.updatedBy = :updatedBy"),
    @NamedQuery(name = "QuestionOption.findByUpdatedDate", query = "SELECT q FROM QuestionOption q WHERE q.updatedDate = :updatedDate"),
    @NamedQuery(name = "QuestionOption.findByIsValid", query = "SELECT q FROM QuestionOption q WHERE q.isValid = :isValid")})
public class QuestionOption implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "option_id")
    private Long optionId;
    @Column(name = "option_text")
    private String optionText;
    @Column(name = "explanation")
    private String explanation;
    @Column(name = "show_explanation")
    private Boolean showExplanation;
    @Column(name = "display_index")
    private Integer displayIndex;
    @Column(name = "option_type")
    private String optionType;
    @Basic(optional = false)
    @Column(name = "created_by")
    private long createdBy;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "updated_by")
    private BigInteger updatedBy;
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    @Column(name = "is_valid")
    private Boolean isValid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "questionOptionId", fetch = FetchType.LAZY)
    private Set<Answer> answerSet;
    @JoinColumn(name = "question_id", referencedColumnName = "question_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Question questionId;

    public QuestionOption() {
    }

    public QuestionOption(Long optionId) {
        this.optionId = optionId;
    }

    public QuestionOption(Long optionId, long createdBy) {
        this.optionId = optionId;
        this.createdBy = createdBy;
    }

    public Long getOptionId() {
        return optionId;
    }

    public void setOptionId(Long optionId) {
        this.optionId = optionId;
    }

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public Boolean getShowExplanation() {
        return showExplanation;
    }

    public void setShowExplanation(Boolean showExplanation) {
        this.showExplanation = showExplanation;
    }

    public Integer getDisplayIndex() {
        return displayIndex;
    }

    public void setDisplayIndex(Integer displayIndex) {
        this.displayIndex = displayIndex;
    }

    public String getOptionType() {
        return optionType;
    }

    public void setOptionType(String optionType) {
        this.optionType = optionType;
    }

    public long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public BigInteger getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(BigInteger updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    public Set<Answer> getAnswerSet() {
        return answerSet;
    }

    public void setAnswerSet(Set<Answer> answerSet) {
        this.answerSet = answerSet;
    }

    public Question getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Question questionId) {
        this.questionId = questionId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (optionId != null ? optionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QuestionOption)) {
            return false;
        }
        QuestionOption other = (QuestionOption) object;
        if ((this.optionId == null && other.optionId != null) || (this.optionId != null && !this.optionId.equals(other.optionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gs.question.master.model.entity.QuestionOption[ optionId=" + optionId + " ]";
    }
    
}
