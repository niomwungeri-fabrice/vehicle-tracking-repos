package rw.itcg.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * @author NIYOMWUNGERI 11:12:43 PM, Dec 26, 2016
 */
@Entity
public class Users {
	@Id
	private String username;
	private String password;
	private String fname;
	private String lname;
	@Column(columnDefinition = "boolean DEFAULT false")
	private boolean active;

	@OneToMany(mappedBy = "user")
	private List<Mitation> imitation;

	public List<Mitation> getImitation() {
		return imitation;
	}

	public void setImitation(List<Mitation> imitation) {
		this.imitation = imitation;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
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

}
