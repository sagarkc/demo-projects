/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gs.question.master.model.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sabuj
 */
@Entity
@Table(name = "test_session")
@NamedQueries({
    @NamedQuery(name = "TestSession.findAll", query = "SELECT t FROM TestSession t"),
    @NamedQuery(name = "TestSession.findBySessionId", query = "SELECT t FROM TestSession t WHERE t.sessionId = :sessionId"),
    @NamedQuery(name = "TestSession.findByStartTime", query = "SELECT t FROM TestSession t WHERE t.startTime = :startTime"),
    @NamedQuery(name = "TestSession.findByDuration", query = "SELECT t FROM TestSession t WHERE t.duration = :duration"),
    @NamedQuery(name = "TestSession.findByEndTime", query = "SELECT t FROM TestSession t WHERE t.endTime = :endTime"),
    @NamedQuery(name = "TestSession.findByRandomize", query = "SELECT t FROM TestSession t WHERE t.randomize = :randomize"),
    @NamedQuery(name = "TestSession.findByRelaxed", query = "SELECT t FROM TestSession t WHERE t.relaxed = :relaxed")})
public class TestSession implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "session_id")
    private Long sessionId;
    @Column(name = "start_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "duration")
    private Float duration;
    @Column(name = "end_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;
    @Column(name = "randomize")
    private Boolean randomize;
    @Column(name = "relaxed")
    private Boolean relaxed;
    @JoinColumn(name = "test_paper_id", referencedColumnName = "test_paper_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TestPaper testPaperId;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User userId;

    public TestSession() {
    }

    public TestSession(Long sessionId) {
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

    public Float getDuration() {
        return duration;
    }

    public void setDuration(Float duration) {
        this.duration = duration;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Boolean getRandomize() {
        return randomize;
    }

    public void setRandomize(Boolean randomize) {
        this.randomize = randomize;
    }

    public Boolean getRelaxed() {
        return relaxed;
    }

    public void setRelaxed(Boolean relaxed) {
        this.relaxed = relaxed;
    }

    public TestPaper getTestPaperId() {
        return testPaperId;
    }

    public void setTestPaperId(TestPaper testPaperId) {
        this.testPaperId = testPaperId;
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
        hash += (sessionId != null ? sessionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TestSession)) {
            return false;
        }
        TestSession other = (TestSession) object;
        if ((this.sessionId == null && other.sessionId != null) || (this.sessionId != null && !this.sessionId.equals(other.sessionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gs.question.master.model.entity.TestSession[ sessionId=" + sessionId + " ]";
    }
    
}
