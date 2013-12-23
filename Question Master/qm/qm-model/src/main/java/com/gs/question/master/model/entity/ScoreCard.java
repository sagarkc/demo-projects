/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gs.question.master.model.entity;

import java.io.Serializable;
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

/**
 *
 * @author Sabuj
 */
@Entity
@Table(name = "score_card")
@NamedQueries({
    @NamedQuery(name = "ScoreCard.findAll", query = "SELECT s FROM ScoreCard s"),
    @NamedQuery(name = "ScoreCard.findByScoreCardId", query = "SELECT s FROM ScoreCard s WHERE s.scoreCardId = :scoreCardId"),
    @NamedQuery(name = "ScoreCard.findByTotalScore", query = "SELECT s FROM ScoreCard s WHERE s.totalScore = :totalScore")})
public class ScoreCard implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "score_card_id")
    private Long scoreCardId;
    @Column(name = "total_score")
    private Long totalScore;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "scoreCardId", fetch = FetchType.LAZY)
    private Set<Answer> answerSet;
    @JoinColumn(name = "examination_session_id", referencedColumnName = "session_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ExaminationSession examinationSessionId;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User userId;
    @JoinColumn(name = "examination_id", referencedColumnName = "exam_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Examination examinationId;

    public ScoreCard() {
    }

    public ScoreCard(Long scoreCardId) {
        this.scoreCardId = scoreCardId;
    }

    public Long getScoreCardId() {
        return scoreCardId;
    }

    public void setScoreCardId(Long scoreCardId) {
        this.scoreCardId = scoreCardId;
    }

    public Long getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Long totalScore) {
        this.totalScore = totalScore;
    }

    public Set<Answer> getAnswerSet() {
        return answerSet;
    }

    public void setAnswerSet(Set<Answer> answerSet) {
        this.answerSet = answerSet;
    }

    public ExaminationSession getExaminationSessionId() {
        return examinationSessionId;
    }

    public void setExaminationSessionId(ExaminationSession examinationSessionId) {
        this.examinationSessionId = examinationSessionId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Examination getExaminationId() {
        return examinationId;
    }

    public void setExaminationId(Examination examinationId) {
        this.examinationId = examinationId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (scoreCardId != null ? scoreCardId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScoreCard)) {
            return false;
        }
        ScoreCard other = (ScoreCard) object;
        if ((this.scoreCardId == null && other.scoreCardId != null) || (this.scoreCardId != null && !this.scoreCardId.equals(other.scoreCardId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gs.question.master.model.entity.ScoreCard[ scoreCardId=" + scoreCardId + " ]";
    }
    
}
