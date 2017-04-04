package rw.itcg.usecase;

import java.io.IOException;
import java.io.Serializable;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import rw.itcg.dao.UserDao;
import rw.itcg.model.Users;

/**
 * @author NIYOMWUNGERI 5:27:31 PM, Dec 27, 2016
 */
@ManagedBean
public class CreateUsers implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Users users = new Users();
	private List<Users> usersList;

	public CreateUsers() {
		usersList = new ArrayList<Users>();
		usersList = new UserDao().findAll();
	}

	// row cancel
	public void onCancel(RowEditEvent event) {
		try {
			Users u = (Users) event.getObject();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Cancelled " + u.getFname(), null));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error>>" + e.getMessage(), null));
		}
	}

	public void onEdit(RowEditEvent event) {
		try {
			users = (Users) event.getObject();
			new UserDao().updateObject(users);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "User Updated", null));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error>>" + e.getMessage(), null));
		}
	}

	public void activateMethod(Users u) throws IOException {
		try {
			this.users = new UserDao().findUserByUsername(u.getUsername());
			if (users.isActive()) {
				users.setActive(false);
				new UserDao().updateObject(users);
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "User BLocked", null));
				usersList = new UserDao().findAll();
			} else {
				users.setActive(true);
				new UserDao().updateObject(users);
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "User Activated", null));
				usersList = new UserDao().findAll();
			}
		} catch (Exception e) {

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error : ", e.getMessage()));
			Logging.logs(e.getMessage(), getClass().getSimpleName().toString());
		}

	}

	public String md5(String username) throws IOException {
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
			Logging.logs(e.getMessage(), getClass().getSimpleName().toString());
			return "Error" + e.getMessage();

		}

	}

	public String suggestUsername() {
		users.setFname("Niyomwungeri");
		users.setLname("Fabrice");
		return users.getFname().toLowerCase() + "." + users.getLname().toLowerCase();
	}

	public void createNewUser() throws IOException {
		try {
			String pass = md5(users.getPassword());
			users.setPassword(pass);
			new UserDao().saveObject(users);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "User Created Successfully", null));
			usersList = new UserDao().findAll();
			users = new Users();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"User [" + users.getUsername() + "] Already Exists!", null));
			Logging.logs(e.getMessage(), getClass().getSimpleName().toString());
		}
	}

	public List<Users> getUsersList() {
		return usersList;
	}

	public void setUsersList(List<Users> usersList) {
		this.usersList = usersList;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
