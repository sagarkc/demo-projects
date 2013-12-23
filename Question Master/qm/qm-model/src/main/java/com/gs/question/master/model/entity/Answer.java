/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gs.question.master.model.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Sabuj
 */
@Entity
@Table(name = "answer")
@NamedQueries({
    @NamedQuery(name = "Answer.findAll", query = "SELECT a FROM Answer a"),
    @NamedQuery(name = "Answer.findByAnswerId", query = "SELECT a FROM Answer a WHERE a.answerId = :answerId"),
    @NamedQuery(name = "Answer.findByAnswerText", query = "SELECT a FROM Answer a WHERE a.answerText = :answerText"),
    @NamedQuery(name = "Answer.findByFileType", query = "SELECT a FROM Answer a WHERE a.fileType = :fileType")})
public class Answer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "answer_id")
    private Long answerId;
    @Column(name = "answer_text")
    private String answerText;
    @Column(name = "file_type")
    private String fileType;
    @Lob
    @Column(name = "file_data")
    private byte[] fileData;
    @JoinColumn(name = "score_card_id", referencedColumnName = "score_card_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ScoreCard scoreCardId;
    @JoinColumn(name = "question_option_id", referencedColumnName = "option_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private QuestionOption questionOptionId;
    @JoinColumn(name = "question_id", referencedColumnName = "question_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Question questionId;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User userId;

    public Answer() {
    }

    public Answer(Long answerId) {
        this.answerId = answerId;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    public ScoreCard getScoreCardId() {
        return scoreCardId;
    }

    public void setScoreCardId(ScoreCard scoreCardId) {
        this.scoreCardId = scoreCardId;
    }

    public QuestionOption getQuestionOptionId() {
        return questionOptionId;
    }

    public void setQuestionOptionId(QuestionOption questionOptionId) {
        this.questionOptionId = questionOptionId;
    }

    public Question getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Question questionId) {
        this.questionId = questionId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (answerId != null ? answerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Answer)) {
            return false;
        }
        Answer other = (Answer) object;
        if ((this.answerId == null && other.answerId != null) || (this.answerId != null && !this.answerId.equals(other.answerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gs.question.master.model.entity.Answer[ answerId=" + answerId + " ]";
    }
    
}
