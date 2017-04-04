package rw.itcg.usecase;

import java.security.MessageDigest;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import rw.itcg.dao.UserDao;
import rw.itcg.model.Users;

@ManagedBean
public class ChangePassword {
	private Users user = new Users();
	private String newPassword;
	private String userna;

	public String getUserna() {
		return userna;
	}

	public void setUserna(String userna) {
		this.userna = userna;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
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

	public void changeNewPassword() {
		try {
			user = new UserDao().findUserByUsername(userna);
			user.setPassword(md5(newPassword));
			new UserDao().updateObject(user);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Passoword Changed", null));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Passoword Not Changed >>Error:" + e.getMessage(), null));
		}
	}
}
