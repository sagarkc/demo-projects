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
@Table(name = "examination_session")
@NamedQueries({
    @NamedQuery(name = "ExaminationSession.findAll", query = "SELECT e FROM ExaminationSession e"),
    @NamedQuery(name = "ExaminationSession.findBySessionId", query = "SELECT e FROM ExaminationSession e WHERE e.sessionId = :sessionId"),
    @NamedQuery(name = "ExaminationSession.findByStartTime", query = "SELECT e FROM ExaminationSession e WHERE e.startTime = :startTime"),
    @NamedQuery(name = "ExaminationSession.findByEndTime", query = "SELECT e FROM ExaminationSession e WHERE e.endTime = :endTime")})
public class ExaminationSession implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "session_id")
    private Long sessionId;
    @Column(name = "start_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    @Column(name = "end_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "examinationSessionId", fetch = FetchType.LAZY)
    private Set<ScoreCard> scoreCardSet;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User userId;
    @JoinColumn(name = "examination_id", referencedColumnName = "exam_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Examination examinationId;

    public ExaminationSession() {
    }

    public ExaminationSession(Long sessionId) {
        this.sessionId = sessionId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Set<ScoreCard> getScoreCardSet() {
        return scoreCardSet;
    }

    public void setScoreCardSet(Set<ScoreCard> scoreCardSet) {
        this.scoreCardSet = scoreCardSet;
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
        hash += (sessionId != null ? sessionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExaminationSession)) {
            return false;
        }
        ExaminationSession other = (ExaminationSession) object;
        if ((this.sessionId == null && other.sessionId != null) || (this.sessionId != null && !this.sessionId.equals(other.sessionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gs.question.master.model.entity.ExaminationSession[ sessionId=" + sessionId + " ]";
    }
    
}
