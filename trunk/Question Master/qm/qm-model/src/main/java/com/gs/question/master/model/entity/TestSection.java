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
@Table(name = "test_section")
@NamedQueries({
    @NamedQuery(name = "TestSection.findAll", query = "SELECT t FROM TestSection t"),
    @NamedQuery(name = "TestSection.findBySectionId", query = "SELECT t FROM TestSection t WHERE t.sectionId = :sectionId"),
    @NamedQuery(name = "TestSection.findBySectionName", query = "SELECT t FROM TestSection t WHERE t.sectionName = :sectionName"),
    @NamedQuery(name = "TestSection.findByDisplayIndex", query = "SELECT t FROM TestSection t WHERE t.displayIndex = :displayIndex")})
public class TestSection implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "section_id")
    private Long sectionId;
    @Basic(optional = false)
    @Column(name = "section_name")
    private String sectionName;
    @Column(name = "display_index")
    private Integer displayIndex;
    @JoinColumn(name = "test_paper_id", referencedColumnName = "test_paper_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TestPaper testPaperId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "testSectionId", fetch = FetchType.LAZY)
    private Set<Question> questionSet;

    public TestSection() {
    }

    public TestSection(Long sectionId) {
        this.sectionId = sectionId;
    }

    public TestSection(Long sectionId, String sectionName) {
        this.sectionId = sectionId;
        this.sectionName = sectionName;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public Integer getDisplayIndex() {
        return displayIndex;
    }

    public void setDisplayIndex(Integer displayIndex) {
        this.displayIndex = displayIndex;
    }

    public TestPaper getTestPaperId() {
        return testPaperId;
    }

    public void setTestPaperId(TestPaper testPaperId) {
        this.testPaperId = testPaperId;
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
        hash += (sectionId != null ? sectionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TestSection)) {
            return false;
        }
        TestSection other = (TestSection) object;
        if ((this.sectionId == null && other.sectionId != null) || (this.sectionId != null && !this.sectionId.equals(other.sectionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gs.question.master.model.entity.TestSection[ sectionId=" + sectionId + " ]";
    }
    
}
