package com.kmbt.csa.dav.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user", catalog = "dbo")
public class UserDataModel  implements java.io.Serializable {

	 private static final long serialVersionUID = -8646278967746868290L;
	 private String username;
     private String password;
   
    public UserDataModel() {
    }

    public UserDataModel(String username, String password) {
       this.username = username;
       this.password = password; 
    }
	@Id
    @Column(name="username")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	@Column(name="password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
   
	@Override
	public String toString() {
		return "UserDataModel [username=" + username + ", password=" + password +"]";
	}
}


