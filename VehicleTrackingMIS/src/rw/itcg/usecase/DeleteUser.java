package rw.itcg.usecase;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import rw.itcg.dao.UserDao;
import rw.itcg.model.Users;

@ManagedBean
public class DeleteUser {
	private Users user = new Users();

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public void delete(Users us) {

		try {
			this.user = us;
			new UserDao().deleteObject(user);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "User " + user.getUsername() + " Deleted", null));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"User " + user.getUsername() + " Not Deleted, Error: " + e.getMessage(), null));
		}
	}
	public void passwordReset(Users us) {

		try {
			this.user = us;
			user.setPassword("827ccb0eea8a706c4c34a16891f84e7b");
			new UserDao().updateObject(user);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Password Reset Successfully", null));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Password for " + user.getUsername() + " Not Reset, Error: " + e.getMessage(), null));
		}
	}
}
