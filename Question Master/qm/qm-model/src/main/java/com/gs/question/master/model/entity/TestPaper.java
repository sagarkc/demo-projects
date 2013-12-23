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
@Table(name = "test_paper")
@NamedQueries({
    @NamedQuery(name = "TestPaper.findAll", query = "SELECT t FROM TestPaper t"),
    @NamedQuery(name = "TestPaper.findByTestPaperId", query = "SELECT t FROM TestPaper t WHERE t.testPaperId = :testPaperId"),
    @NamedQuery(name = "TestPaper.findByTestPaperName", query = "SELECT t FROM TestPaper t WHERE t.testPaperName = :testPaperName")})
public class TestPaper implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "test_paper_id")
    private Long testPaperId;
    @Basic(optional = false)
    @Column(name = "test_paper_name")
    private String testPaperName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "testPaperId", fetch = FetchType.LAZY)
    private Set<TestSession> testSessionSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "testPaperId", fetch = FetchType.LAZY)
    private Set<TestSection> testSectionSet;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User userId;

    public TestPaper() {
    }

    public TestPaper(Long testPaperId) {
        this.testPaperId = testPaperId;
    }

    public TestPaper(Long testPaperId, String testPaperName) {
        this.testPaperId = testPaperId;
        this.testPaperName = testPaperName;
    }

    public Long getTestPaperId() {
        return testPaperId;
    }

    public void setTestPaperId(Long testPaperId) {
        this.testPaperId = testPaperId;
    }

    public String getTestPaperName() {
        return testPaperName;
    }

    public void setTestPaperName(String testPaperName) {
        this.testPaperName = testPaperName;
    }

    public Set<TestSession> getTestSessionSet() {
        return testSessionSet;
    }

    public void setTestSessionSet(Set<TestSession> testSessionSet) {
        this.testSessionSet = testSessionSet;
    }

    public Set<TestSection> getTestSectionSet() {
        return testSectionSet;
    }

    public void setTestSectionSet(Set<TestSection> testSectionSet) {
        this.testSectionSet = testSectionSet;
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
        hash += (testPaperId != null ? testPaperId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TestPaper)) {
            return false;
        }
        TestPaper other = (TestPaper) object;
        if ((this.testPaperId == null && other.testPaperId != null) || (this.testPaperId != null && !this.testPaperId.equals(other.testPaperId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gs.question.master.model.entity.TestPaper[ testPaperId=" + testPaperId + " ]";
    }
    
}
