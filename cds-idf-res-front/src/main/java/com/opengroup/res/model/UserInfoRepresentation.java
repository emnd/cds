package com.opengroup.res.model;

import java.util.Set;

import com.opengroup.res.util.RepresentationBean;

public class UserInfoRepresentation implements RepresentationBean
{

		String loginOpen;   //The OPEN username that will be stored in the database and will be used as query for the LDAP
	    // to recover other info if needed
		String name;    //The name to be used on the site
		String email;   //Electronic mail address used as sender when generating mails
		Set<String> roles;
		
		public String getLoginOpen() {
		return loginOpen;
		}
		
		public void setLoginOpen(String loginOpen) {
		this.loginOpen = loginOpen;
		}
		
		public String getName() {
		return name;
		}
		
		public void setName(String name) {
		this.name = name;
		}
		
		public String getEmail() {
		return email;
		}
		
		public void setEmail(String email) {
		this.email = email;
		}
		
		public Set<String> getRoles() {
		return roles;
		}
		
		public void setRoles(Set<String> roles) {
		this.roles = roles;
		}
	
}
