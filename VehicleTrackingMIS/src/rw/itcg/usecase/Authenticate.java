package rw.itcg.usecase;

import java.io.IOException;
import java.io.Serializable;
import java.security.MessageDigest;

import javax.annotation.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import rw.itcg.dao.UserDao;
import rw.itcg.model.Users;

/**
 * @author NIYOMWUNGERI 8:43:33 AM, Dec 27, 2016
 */
@ManagedBean
@SessionScoped
public class Authenticate implements Serializable {

	private static final long serialVersionUID = 7988409035554836548L;

	private Users users = new Users();
	private Users loggedInUser = new Users();

	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Users getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(Users loggedInUser) {
		this.loggedInUser = loggedInUser;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public String md5(String username) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(username.getBytes());
			byte bytData[] = md.digest();
			StringBuffer hextString = new StringBuffer();
			for (int i = 0; i < bytData.length; i++) {
				String hex = Integer.toHexString(0xff & bytData[i]);
				if (hex.length() == 1) {
					hextString.append('0');
				}
				hextString.append(hex);
			}
			return hextString.toString();

		} catch (Exception e) {
			return "Error" + e.getMessage();
		}

	}

	public String authenticateUsers() throws IOException {
		Users userDB = new UserDao().findUserByUsername(username);
		try {
			if (userDB != null) {
				if (userDB.getPassword().equals(md5(users.getPassword()))) {
					if (userDB.isActive()) {
						if (userDB.getUsername().equals("admin")) {
							loggedInUser = userDB;
							return "admin";
						} else {
							if (userDB.getPassword().equals("827cfb0eea8a706c4c34a16891f84e7b")) {
								return "changePassword";
							} else {
								loggedInUser = userDB;
								return "RegisterCars";
							}
						}
					} else {
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Loggin Failed", "Account is blocked! Contact Administrator "));
						return "login";
					}
				} else {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Loggin Failed", "Username and Password Don't much"));
					return "login";
				}
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"User:", "Doesn't Exist...Contact Administrator"));
				return "login";
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login Error:", e.getMessage()));
			Logging.logs(e.getMessage(), getClass().getSimpleName());
		}
		return "login";
	}

}
