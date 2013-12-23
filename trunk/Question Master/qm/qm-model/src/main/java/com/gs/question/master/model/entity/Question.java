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

/**
 *
 * @author Sabuj
 */
@Entity
@Table(name = "question")
@NamedQueries({
    @NamedQuery(name = "Question.findAll", query = "SELECT q FROM Question q"),
    @NamedQuery(name = "Question.findByQuestionId", query = "SELECT q FROM Question q WHERE q.questionId = :questionId"),
    @NamedQuery(name = "Question.findByQuestionText", query = "SELECT q FROM Question q WHERE q.questionText = :questionText"),
    @NamedQuery(name = "Question.findByCreatedDate", query = "SELECT q FROM Question q WHERE q.createdDate = :createdDate"),
    @NamedQuery(name = "Question.findByUpdatedDate", query = "SELECT q FROM Question q WHERE q.updatedDate = :updatedDate"),
    @NamedQuery(name = "Question.findByDisplayIndex", query = "SELECT q FROM Question q WHERE q.displayIndex = :displayIndex")})
public class Question implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "question_id")
    private Long questionId;
    @Basic(optional = false)
    @Column(name = "question_text")
    private String questionText;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    @Column(name = "display_index")
    private Integer displayIndex;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "questionId", fetch = FetchType.LAZY)
    private Set<Answer> answerSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "questionId", fetch = FetchType.LAZY)
    private Set<QuestionOption> questionOptionSet;
    @JoinColumn(name = "test_section_id", referencedColumnName = "section_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TestSection testSectionId;
    @JoinColumn(name = "question_type", referencedColumnName = "type")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MstrQuestionTypes questionType;
    @JoinColumn(name = "access_type", referencedColumnName = "type")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MstrAccessTypes accessType;
    @JoinColumn(name = "updated_by", referencedColumnName = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User updatedBy;
    @JoinColumn(name = "created_by", referencedColumnName = "user_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User createdBy;
    @JoinColumn(name = "question_bank_id", referencedColumnName = "bank_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private QuestionBank questionBankId;

    public Question() {
    }

    public Question(Long questionId) {
        this.questionId = questionId;
    }

    public Question(Long questionId, String questionText) {
        this.questionId = questionId;
        this.questionText = questionText;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
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

    public Integer getDisplayIndex() {
        return displayIndex;
    }

    public void setDisplayIndex(Integer displayIndex) {
        this.displayIndex = displayIndex;
    }

    public Set<Answer> getAnswerSet() {
        return answerSet;
    }

    public void setAnswerSet(Set<Answer> answerSet) {
        this.answerSet = answerSet;
    }

    public Set<QuestionOption> getQuestionOptionSet() {
        return questionOptionSet;
    }

    public void setQuestionOptionSet(Set<QuestionOption> questionOptionSet) {
        this.questionOptionSet = questionOptionSet;
    }

    public TestSection getTestSectionId() {
        return testSectionId;
    }

    public void setTestSectionId(TestSection testSectionId) {
        this.testSectionId = testSectionId;
    }

    public MstrQuestionTypes getQuestionType() {
        return questionType;
    }

    public void setQuestionType(MstrQuestionTypes questionType) {
        this.questionType = questionType;
    }

    public MstrAccessTypes getAccessType() {
        return accessType;
    }

    public void setAccessType(MstrAccessTypes accessType) {
        this.accessType = accessType;
    }

    public User getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(User updatedBy) {
        this.updatedBy = updatedBy;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public QuestionBank getQuestionBankId() {
        return questionBankId;
    }

    public void setQuestionBankId(QuestionBank questionBankId) {
        this.questionBankId = questionBankId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (questionId != null ? questionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Question)) {
            return false;
        }
        Question other = (Question) object;
        if ((this.questionId == null && other.questionId != null) || (this.questionId != null && !this.questionId.equals(other.questionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gs.question.master.model.entity.Question[ questionId=" + questionId + " ]";
    }
    
}
