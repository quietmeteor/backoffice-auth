package entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user_groups")
public class UserGroups extends BaseEntity {

	@Column(name = "roles", nullable = false)
	private String roles; //Super-Admin (CRUD permissions), Admin base (has full read permissions), user (can only see it's own reservations)

	@Column(name = "group_name", nullable = false)
	private String groupName;

	@Column(name = "permissions", nullable = false)
	private String permissions; // CRUD

	@Column(name = "visibility", nullable = false)
	private String visibility; // TODO to understand

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user_groups")
	private Set<User> users = new HashSet<>();
	
	public UserGroups() {
		
	}

	public UserGroups(String roles, String groupName, String permissions, String visibility, Set<User> users) {
		super();
		this.roles = roles;
		this.groupName = groupName;
		this.permissions = permissions;
		this.visibility = visibility;
		this.users = users;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
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

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	@Override
	public String toString() {
		return "UserGroups [roles=" + roles + ", permits=" + ", groupName=" + groupName + ", permissions=" + permissions
				+ ", visibility=" + visibility + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(groupName, permissions, roles, users, visibility);
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
		return Objects.equals(groupName, other.groupName) && Objects.equals(permissions, other.permissions)
				&& Objects.equals(roles, other.roles) && Objects.equals(users, other.users)
				&& Objects.equals(visibility, other.visibility);
	}

}
