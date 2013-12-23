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
@Table(name = "examination")
@NamedQueries({
    @NamedQuery(name = "Examination.findAll", query = "SELECT e FROM Examination e"),
    @NamedQuery(name = "Examination.findByExamId", query = "SELECT e FROM Examination e WHERE e.examId = :examId"),
    @NamedQuery(name = "Examination.findByExamName", query = "SELECT e FROM Examination e WHERE e.examName = :examName"),
    @NamedQuery(name = "Examination.findByActivationStart", query = "SELECT e FROM Examination e WHERE e.activationStart = :activationStart"),
    @NamedQuery(name = "Examination.findByActivationEnd", query = "SELECT e FROM Examination e WHERE e.activationEnd = :activationEnd"),
    @NamedQuery(name = "Examination.findByDuration", query = "SELECT e FROM Examination e WHERE e.duration = :duration"),
    @NamedQuery(name = "Examination.findByCreatedDate", query = "SELECT e FROM Examination e WHERE e.createdDate = :createdDate"),
    @NamedQuery(name = "Examination.findByUpdatedDate", query = "SELECT e FROM Examination e WHERE e.updatedDate = :updatedDate"),
    @NamedQuery(name = "Examination.findByComment", query = "SELECT e FROM Examination e WHERE e.comment = :comment"),
    @NamedQuery(name = "Examination.findByMinScore", query = "SELECT e FROM Examination e WHERE e.minScore = :minScore"),
    @NamedQuery(name = "Examination.findByMaxScore", query = "SELECT e FROM Examination e WHERE e.maxScore = :maxScore")})
public class Examination implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "exam_id")
    private Long examId;
    @Column(name = "exam_name")
    private String examName;
    @Column(name = "activation_start")
    @Temporal(TemporalType.TIMESTAMP)
    private Date activationStart;
    @Column(name = "activation_end")
    @Temporal(TemporalType.TIMESTAMP)
    private Date activationEnd;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "duration")
    private Float duration;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    @Column(name = "comment")
    private String comment;
    @Basic(optional = false)
    @Column(name = "min_score")
    private long minScore;
    @Basic(optional = false)
    @Column(name = "max_score")
    private long maxScore;
    @JoinColumn(name = "updated_by", referencedColumnName = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User updatedBy;
    @JoinColumn(name = "created_by", referencedColumnName = "user_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User createdBy;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "examinationId", fetch = FetchType.LAZY)
    private Set<ScoreCard> scoreCardSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "examinationId", fetch = FetchType.LAZY)
    private Set<ExaminationSession> examinationSessionSet;

    public Examination() {
    }

    public Examination(Long examId) {
        this.examId = examId;
    }

    public Examination(Long examId, long minScore, long maxScore) {
        this.examId = examId;
        this.minScore = minScore;
        this.maxScore = maxScore;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public Date getActivationStart() {
        return activationStart;
    }

    public void setActivationStart(Date activationStart) {
        this.activationStart = activationStart;
    }

    public Date getActivationEnd() {
        return activationEnd;
    }

    public void setActivationEnd(Date activationEnd) {
        this.activationEnd = activationEnd;
    }

    public Float getDuration() {
        return duration;
    }

    public void setDuration(Float duration) {
        this.duration = duration;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getMinScore() {
        return minScore;
    }

    public void setMinScore(long minScore) {
        this.minScore = minScore;
    }

    public long getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(long maxScore) {
        this.maxScore = maxScore;
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

    public Set<ScoreCard> getScoreCardSet() {
        return scoreCardSet;
    }

    public void setScoreCardSet(Set<ScoreCard> scoreCardSet) {
        this.scoreCardSet = scoreCardSet;
    }

    public Set<ExaminationSession> getExaminationSessionSet() {
        return examinationSessionSet;
    }

    public void setExaminationSessionSet(Set<ExaminationSession> examinationSessionSet) {
        this.examinationSessionSet = examinationSessionSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (examId != null ? examId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Examination)) {
            return false;
        }
        Examination other = (Examination) object;
        if ((this.examId == null && other.examId != null) || (this.examId != null && !this.examId.equals(other.examId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gs.question.master.model.entity.Examination[ examId=" + examId + " ]";
    }
    
}
