package rw.itcg.usecase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import rw.itcg.dao.CarDao;
import rw.itcg.dao.MitationDao;
import rw.itcg.dao.OwnerDao;
import rw.itcg.model.Car;
import rw.itcg.model.Mitation;
import rw.itcg.model.Owners;
import rw.itcg.model.Users;

/**
 * @author NIYOMWUNGERI 11:21:55 AM, Dec 29, 2016
 */
@ManagedBean
@SessionScoped
public class SearchCar implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String searcKey;
	private String seachKarPlate;
	public String action_username;
	private String com;
	private List<Car> carList;
	private List<Owners> ownList;
	private Car car;
	private Mitation mit;
	private String comments;
	private boolean stat; 

	public boolean isStat() {
		return stat;
	}

	public void setStat(boolean stat) {
		this.stat = stat;
	}

	public SearchCar() {
		carList = new ArrayList<Car>();
		carList = new CarDao().findAllCars();
		ownList = new ArrayList<>();
		mit = new Mitation();
	}

	public String getAction_username() {
		return action_username;
	}

	public String getCom() {
		return com;
	}

	public void setCom(String com) {
		this.com = com;
	}

	public void setAction_username(String action_username) {
		this.action_username = action_username;
	}

	public String getSeachKarPlate() {
		return seachKarPlate;
	}

	public void setSeachKarPlate(String seachKarPlate) {
		this.seachKarPlate = seachKarPlate;
	}

	private String nid;

	public String getNid() {
		return nid;
	}

	public void setNid(String nid) {
		this.nid = nid;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Mitation getMit() {
		return mit;
	}

	public void setMit(Mitation mit) {
		this.mit = mit;
	}

	public void disableButton(Car c) {
		this.car = c;
		List<Mitation> mi = new MitationDao().findCarByPlatNber(this.car.getCarPlate());
		for (Mitation m : mi) {
			if (m.getStatus().equalsIgnoreCase("Stolen")) {
				this.stat = false;
			} else if (m.getStatus().equalsIgnoreCase("Found")) {
				this.stat = true;
			} else {
				this.stat = true;
			}
		}
	}

	// main search
	public List<String> autoCompleteCar(String query) {

		List<String> carPlate = new ArrayList<String>();
		List<Car> allCars = new CarDao().findAllCars();

		for (Car ca : allCars) {
			if (ca.getCarPlate().startsWith(query)) {
				carPlate.add(ca.getCarPlate());
			}
		}
		return carPlate;
	}

	public List<String> autoCompleteOwner(String query) {

		List<String> nid = new ArrayList<String>();
		List<Owners> allNid = new OwnerDao().findAll();

		for (Owners ow : allNid) {
			if (ow.getNationalId().startsWith(query)) {
				nid.add(ow.getNationalId());
			}
		}
		return nid;
	}

	public void searchOwnersCar() {
		carList = new CarDao().findCarByOwner(seachKarPlate);

	}

	public void search() {
		try {
			carList = new CarDao().CarByPlatNberList(searcKey);

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Search Result: ", e.getMessage()));
		}
	}

	public void transfer() {
		try {
			Car car = new CarDao().CarByPlatNber(searcKey);

			Owners newOwner = new Owners();
			newOwner.setNationalId(seachKarPlate);
			car.setOwner(newOwner);

			mit.setStatus("Bought");
			mit.setCars(car);
			mit.setOwners(newOwner);
			mit.setComments("dfsfsdf");
			Users u = new Users();
			u.setUsername(action_username);

			mit.setUser(u);

			new CarDao().updateObject(car);

			new MitationDao().saveObject(mit);

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Car [" + car.getCarPlate() + "] Transfered", null));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transfer Failed: Invalid Plate number or National Id Card ", null));
		}
	}

	public List<Owners> getOwnList() {
		return ownList;
	}

	public void setOwnList(List<Owners> ownList) {
		this.ownList = ownList;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public String getSearcKey() {
		return searcKey;
	}

	public void setSearcKey(String searcKey) {
		this.searcKey = searcKey;
	}

	public List<Car> getCarList() {
		return carList;
	}

	public void setCarList(List<Car> carList) {
		this.carList = carList;
	}

}
