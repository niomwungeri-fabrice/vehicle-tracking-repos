package rw.itcg.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * @author NIYOMWUNGERI 10:33:50 PM, Dec 26, 2016
 */
@Entity
public class Owners {
	@Id
	private String nationalId;
	private String firstName;
	private String lastName;
	private String provinces;
	private String phone;
	private String email;

	@OneToMany(mappedBy = "owners")
	private List<Mitation> imitation;

	@OneToMany(mappedBy = "owner")
	private List<Car> myCarList;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Mitation> getImitation() {
		return imitation;
	}

	public void setImitation(List<Mitation> imitation) {
		this.imitation = imitation;
	}

	public String getProvinces() {
		return provinces;
	}

	public void setProvinces(String provinces) {
		this.provinces = provinces;
	}

	public String getNationalId() {
		return nationalId;
	}

	public void setNationalId(String nationalId) {
		this.nationalId = nationalId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {

		this.firstName = firstName.toUpperCase();
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Car> getMyCarList() {
		return myCarList;
	}

	public void setMyCarList(List<Car> myCarList) {
		this.myCarList = myCarList;
	}

}
