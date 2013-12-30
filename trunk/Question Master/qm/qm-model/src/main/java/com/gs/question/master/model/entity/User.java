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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "user")
@NamedQueries({
		@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
		@NamedQuery(name = "User.findByUserId", query = "SELECT u FROM User u WHERE u.userId = :userId"),
		@NamedQuery(name = "User.findByUserName", query = "SELECT u FROM User u WHERE u.userName = :userName"),
		@NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
		@NamedQuery(name = "User.findByFirstName", query = "SELECT u FROM User u WHERE u.firstName = :firstName"),
		@NamedQuery(name = "User.findByLastName", query = "SELECT u FROM User u WHERE u.lastName = :lastName") })
public class User implements Serializable {
	private static final long serialVersionUID = 11651654656546564L;
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Basic(optional = false)
	@Column(name = "user_id")
	private Long userId;
	@Basic(optional = false)
	@Column(name = "user_name")
	private String userName;
	@Basic(optional = false)
	@Column(name = "password")
	private String password;
	@Basic(optional = false)
	@Column(name = "email")
	private String email;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "middle_name")
	private String middleName;
	@Column(name = "last_name")
	private String lastName;
	@JoinTable(name = "user_role_map", joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "role_id") })
	@ManyToMany(fetch = FetchType.LAZY)
	private Set<Role> roleSet;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userId", fetch = FetchType.LAZY)
	private Set<UserSession> userSessionSet;
	@OneToMany(mappedBy = "updatedBy", fetch = FetchType.LAZY)
	private Set<Examination> examinationSet;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "createdBy", fetch = FetchType.LAZY)
	private Set<Examination> examinationSet1;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userId", fetch = FetchType.LAZY)
	private Set<TestSession> testSessionSet;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userId", fetch = FetchType.LAZY)
	private Set<Answer> answerSet;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userId", fetch = FetchType.LAZY)
	private Set<TestPaper> testPaperSet;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userId", fetch = FetchType.LAZY)
	private Set<ScoreCard> scoreCardSet;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userId", fetch = FetchType.LAZY)
	private Set<QuestionBank> questionBankSet;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userId", fetch = FetchType.LAZY)
	private Set<ExaminationSession> examinationSessionSet;
	@OneToMany(mappedBy = "updatedBy", fetch = FetchType.LAZY)
	private Set<Question> questionSet;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "createdBy", fetch = FetchType.LAZY)
	private Set<Question> questionSet1;

	@Column(name = "is_activated")
	private Boolean activated;
	@Column(name = "activation_code")
	private String activationCode;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "activation_expire_by")
	private Date activationExpireBy;

	public User() {
	}

	public User(Long userId) {
		this.userId = userId;
	}

	public User(Long userId, String userName, String password, String email) {
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.email = email;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<Role> getRoleSet() {
		return roleSet;
	}

	public void setRoleSet(Set<Role> roleSet) {
		this.roleSet = roleSet;
	}

	public Set<UserSession> getUserSessionSet() {
		return userSessionSet;
	}

	public void setUserSessionSet(Set<UserSession> userSessionSet) {
		this.userSessionSet = userSessionSet;
	}

	public Set<Examination> getExaminationSet() {
		return examinationSet;
	}

	public void setExaminationSet(Set<Examination> examinationSet) {
		this.examinationSet = examinationSet;
	}

	public Set<Examination> getExaminationSet1() {
		return examinationSet1;
	}

	public void setExaminationSet1(Set<Examination> examinationSet1) {
		this.examinationSet1 = examinationSet1;
	}

	public Set<TestSession> getTestSessionSet() {
		return testSessionSet;
	}

	public void setTestSessionSet(Set<TestSession> testSessionSet) {
		this.testSessionSet = testSessionSet;
	}

	public Set<Answer> getAnswerSet() {
		return answerSet;
	}

	public void setAnswerSet(Set<Answer> answerSet) {
		this.answerSet = answerSet;
	}

	public Set<TestPaper> getTestPaperSet() {
		return testPaperSet;
	}

	public void setTestPaperSet(Set<TestPaper> testPaperSet) {
		this.testPaperSet = testPaperSet;
	}

	public Set<ScoreCard> getScoreCardSet() {
		return scoreCardSet;
	}

	public void setScoreCardSet(Set<ScoreCard> scoreCardSet) {
		this.scoreCardSet = scoreCardSet;
	}

	public Set<QuestionBank> getQuestionBankSet() {
		return questionBankSet;
	}

	public void setQuestionBankSet(Set<QuestionBank> questionBankSet) {
		this.questionBankSet = questionBankSet;
	}

	public Set<ExaminationSession> getExaminationSessionSet() {
		return examinationSessionSet;
	}

	public void setExaminationSessionSet(
			Set<ExaminationSession> examinationSessionSet) {
		this.examinationSessionSet = examinationSessionSet;
	}

	public Set<Question> getQuestionSet() {
		return questionSet;
	}

	public void setQuestionSet(Set<Question> questionSet) {
		this.questionSet = questionSet;
	}

	public Set<Question> getQuestionSet1() {
		return questionSet1;
	}

	public void setQuestionSet1(Set<Question> questionSet1) {
		this.questionSet1 = questionSet1;
	}

	
	public Boolean getActivated() {
		return activated;
	}

	public void setActivated(Boolean activated) {
		this.activated = activated;
	}

	public String getActivationCode() {
		return activationCode;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

	public Date getActivationExpireBy() {
		return activationExpireBy;
	}

	public void setActivationExpireBy(Date activationExpireBy) {
		this.activationExpireBy = activationExpireBy;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (userId != null ? userId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof User)) {
			return false;
		}
		User other = (User) object;
		if ((this.userId == null && other.userId != null)
				|| (this.userId != null && !this.userId.equals(other.userId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.gs.question.master.model.entity.User[ userId=" + userId
				+ " ]";
	}

}
