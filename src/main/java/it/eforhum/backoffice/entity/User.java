package it.eforhum.backoffice.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

/**
 * Entity that represents information of a user, has an One-to-Many relationship with {@link #UserGroups}
 * 
 * @version 1.0
 * @author First backoffice-auth team
 * 
 * @field [FK] {@link #userGroup}
 * @field [NOT NULL] {@link #username}
 * @field [NOT NULL] {@link #password}
 * @field [NOT NULL] {@link #name}
 * @field [NOT NULL] {@link #lastName}
 * @field [NOT NULL] [UNIQUE] {@link #email} 
 * @field {@link #lastLogin}
 * @field {@link #dateModifiedPass} indicates the last time the user's password has been modified
 * @field {@link #isDeleted} indicates if the user's account has been deleted and deactivated without it's record being removed from the database
 * @field {@link #isVerified} indicates if the user has activated his account via link sent him during the registration
 * 
 * @see BaseEntity
 * @see UserGroups
 * 
 */

@Entity
@Table(name = "user")
public class User extends BaseEntity {
	@NotBlank
	@ManyToOne
	@JoinColumn(name = "group_id", foreignKey = @ForeignKey(name = "FK_group_id"))
	private UserGroups userGroup;
	
	@NotBlank
	@Column(name = "username", unique = true, nullable = false, length = 32)
	private String username;
	
	@NotBlank
	@Column(name = "password", nullable = false, length = 32)
	private String password;
	
	@NotBlank
	@Column(name = "name", nullable = false)
	private String name;
	
	@NotBlank
	@Column(name = "lastName", nullable = false)
	private String lastName;
	
	@NotBlank
	@Column(name = "email", unique = true)
	private String email;

	@PastOrPresent
	@Column(name = "last_login", columnDefinition = "TIMESTAMP NOT NULL")
	private LocalDateTime lastLogin;
	
	@PastOrPresent
	@Column(name = "date_modified_pass", columnDefinition = "TIMESTAMP")
	private LocalDateTime dateModifiedPass;
	
	
	@Column(name = "is_deleted", columnDefinition = "BOOLEAN DEFAULT FALSE")
	private boolean isDeleted;

	@Column(name = "is_verified", columnDefinition = "BOOLEAN DEFAULT FALSE")
	private boolean isVerified;

	public User() {

	}

	public User(String username, String password, String name, String lastName, String email, LocalDateTime lastLogin,
			LocalDateTime dateModifiedPass, boolean isDeleted, boolean isVerified) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.lastLogin = lastLogin;
		this.dateModifiedPass = dateModifiedPass;
		this.isDeleted = isDeleted;
		this.isVerified = isVerified;
	}

	public UserGroups getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(UserGroups userGroup) {
		this.userGroup = userGroup;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}

	public LocalDateTime getDateModifiedPass() {
		return dateModifiedPass;
	}

	public void setDateModifiedPass(LocalDateTime dateModifiedPass) {
		this.dateModifiedPass = dateModifiedPass;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public boolean isVerified() {
		return isVerified;
	}

	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}
	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(dateModifiedPass, email, isDeleted, isVerified, lastLogin, lastName,
				name, password, username);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(dateModifiedPass, other.dateModifiedPass) && Objects.equals(email, other.email)
				&& isDeleted == other.isDeleted && isVerified == other.isVerified
				&& Objects.equals(lastLogin, other.lastLogin) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(name, other.name) && Objects.equals(password, other.password)
				&& Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", name=" + name + ", lastName=" + lastName
				+ ", email=" + email + ", lastLogin=" + lastLogin + ", dateModifiedPass=" + dateModifiedPass
				+ ", isDeleted=" + isDeleted + ", isVerified=" + isVerified + "]";
	}

}
