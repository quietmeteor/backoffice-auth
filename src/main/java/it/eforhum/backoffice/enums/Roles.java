package it.eforhum.backoffice.enums;
/**
 * @version 1.0
 * @author First backoffice-auth team
 *
 * @enum {@link #SUPER_USER} permit to CREATE/READ/UPDATE/DELETE all entities =>[USER|GROUP|SLOT|RISORSE]
 * @enum {@link #ADMIN} permit to READ/DELETE =>[USER|GROUP], permit CREATE/READ/UPDATE/DELETE =>[SLOT|RISORSE]
 * @enum {@link #MODERATOR} permit to READ all entities =>[USER|GROUP|SLOT|RISORSE]
 * @enum {@link #USER} permit to READ only own reservations
 */
public enum Roles {
	ROLE_CREATE_RISORSE,
	ROLE_CREATE_USER,
	ROLE_CREATE_SLOT,
	ROLE_CREATE_GROUP,
	
	ROLE_READ_RISORSE,
	ROLE_READ_USER,
	ROLE_READ_SLOT,
	ROLE_READ_GROUP,
	
	ROLE_UPDATE_RISORSE,
	ROLE_UPDATE_USER,
	ROLE_UPDATE_SLOT,
	ROLE_UPDATE_GROUP,
	
	ROLE_DELETE_RISORSE,
	ROLE_DELETE_USER,
	ROLE_DELETE_SLOT,
	ROLE_DELETE_GROUP,
	
	SUPER_USER,
	ADMIN,
	MODERATOR,
	USER,
	
}
