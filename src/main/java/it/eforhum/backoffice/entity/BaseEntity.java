package it.eforhum.backoffice.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Base entity that's inherited by any other entity saved in a database
 * 
 * @version 1.0
 * @author first backoffice-auth team
 * 
 * @field [AI] {@link #id}
 * @field [NOT NULL] {@link #creationUser}
 * @field {@link #updateUser}
 * @field [NOT NULL] {@link #creationUser}
 * @field {@link #updateTime}
 * 
 */

@MappedSuperclass
public class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "creation_user", nullable = false)
	private String creationUser;

	@Column(name = "update_user", nullable = true)
	private String updateUser;

	@Column(name = "creation_time", columnDefinition = "TIMESTAMP NOT NULL")
	private LocalDateTime creationTime;

	@Column(name = "update_time", columnDefinition = "TIMESTAMP")
	private LocalDateTime updateTime;

	public BaseEntity() {
		super();
	}

	public BaseEntity(String creationUser, String updateUser, LocalDateTime creationTime, LocalDateTime updateTime) {
		super();
		this.creationUser = creationUser;
		this.updateUser = updateUser;
		this.creationTime = creationTime;
		this.updateTime = updateTime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCreationUser() {
		return creationUser;
	}

	public void setCreationUser(String creationUser) {
		this.creationUser = creationUser;
	}

	public LocalDateTime getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(LocalDateTime creationTime) {
		this.creationTime = creationTime;
	}

	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	@Override
	public int hashCode() {
		return Objects.hash(creationTime, creationUser, id, updateTime, updateUser);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseEntity other = (BaseEntity) obj;
		return Objects.equals(creationTime, other.creationTime) && Objects.equals(creationUser, other.creationUser)
				&& id == other.id && Objects.equals(updateTime, other.updateTime)
				&& Objects.equals(updateUser, other.updateUser);
	}

	@Override
	public String toString() {
		return "BaseEntity [id=" + id + ", creationUser=" + creationUser + ", updateUser=" + updateUser
				+ ", creationTime=" + creationTime + ", updateTime=" + updateTime + "]";
	}

}
