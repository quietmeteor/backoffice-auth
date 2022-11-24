package it.eforhum.backoffice.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;

/**
 * 
 * @version 1.0
 * @author First backoffice-auth team
 * 
 * @field [NOT NULL] {@link #roles}
 * @field [NOT NULL] {@link #groupName} indicates user's current role, we will
 *        be assuming the following roles: Super-Admin (CRUD permissions on both
 *        users and groups), Admin (CRUD permissions on users and reservations)
 *        user (can only see it's own reservations), Moderator (Read only).
 * @field [NOT NULL] {@link #permissions} indicates user's current permission
 *        such as Create, Read, Update or Delete.
 * @field [NOT NULL] {@link #enabled} indicates if the role has been
 *        activated, non-activated roles can't use their permissions.
 * @field [Bidirectional mapping] {@link #users} contains all users belonging to
 *        the group.
 * 
 */

@Entity
@Table(name = "user_groups")
public class UserGroups extends BaseEntity {

	@Column(name = "roles", nullable = false)
	private String roles;

	@Column(name = "group_name", nullable = false)
	private String groupName;

	@Column(name = "permissions", nullable = false)
	private String permissions;

	@Column(name = "enabled", nullable = false)
	private boolean isEnabled;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userGroup", cascade={CascadeType.PERSIST})
	private Set<User> users = new HashSet<>();

	public UserGroups() {

	}
	
	@PreRemove
	private void preRemove() {
	   users.forEach( child -> child.setUserGroup(null));
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(groupName, isEnabled, permissions, roles, users);
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
		UserGroups other = (UserGroups) obj;
		return Objects.equals(groupName, other.groupName) && isEnabled == other.isEnabled
				&& Objects.equals(permissions, other.permissions) && roles == other.roles
				&& Objects.equals(users, other.users);
	}

	@Override
	public String toString() {
		return "UserGroups [roles=" + roles + ", groupName=" + groupName + ", permissions=" + permissions
				+ ", isEnabled=" + isEnabled + ", users=" + users + "]";
	}

}
