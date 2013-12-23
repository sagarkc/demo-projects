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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Sabuj
 */
@Entity
@Table(name = "mstr_question_types")
@NamedQueries({
    @NamedQuery(name = "MstrQuestionTypes.findAll", query = "SELECT m FROM MstrQuestionTypes m"),
    @NamedQuery(name = "MstrQuestionTypes.findByType", query = "SELECT m FROM MstrQuestionTypes m WHERE m.type = :type"),
    @NamedQuery(name = "MstrQuestionTypes.findByDescription", query = "SELECT m FROM MstrQuestionTypes m WHERE m.description = :description")})
public class MstrQuestionTypes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "type")
    private String type;
    @Column(name = "description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "questionType", fetch = FetchType.LAZY)
    private Set<Question> questionSet;

    public MstrQuestionTypes() {
    }

    public MstrQuestionTypes(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        hash += (type != null ? type.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MstrQuestionTypes)) {
            return false;
        }
        MstrQuestionTypes other = (MstrQuestionTypes) object;
        if ((this.type == null && other.type != null) || (this.type != null && !this.type.equals(other.type))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gs.question.master.model.entity.MstrQuestionTypes[ type=" + type + " ]";
    }
    
}
