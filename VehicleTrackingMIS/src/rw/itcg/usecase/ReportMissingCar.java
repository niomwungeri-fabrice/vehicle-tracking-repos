package rw.itcg.usecase;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import rw.itcg.dao.CarDao;
import rw.itcg.dao.MitationDao;
import rw.itcg.model.Car;
import rw.itcg.model.Mitation;
import rw.itcg.model.Owners;
import rw.itcg.model.Users;

/**
 * @author NIYOMWUNGERI 10:46:49 AM, Jan 3, 2017
 */
@ManagedBean
@SessionScoped
public class ReportMissingCar {
	private Mitation mitation;
	private Car selectedCar;
	private String username;
	private List<Car> carList;
	private String searcKey;

	public String getSearcKey() {
		return searcKey;
	}

	public void setSearcKey(String searcKey) {
		this.searcKey = searcKey;
	}

	public boolean disableButton(Car c) {
		List<Mitation> mi = new MitationDao().findCarByPlatNber(c.getCarPlate());
		boolean stat = false;
		for (Mitation m : mi) {
			if (m.getStatus().equalsIgnoreCase("Stolen")) {
				stat = false;
			} else if (m.getStatus().equalsIgnoreCase("Found")) {
				stat = true;
			} else  {
				stat = false;
			}
		}
		return stat;
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

	public List<Car> getCarList() {
		return carList;
	}

	public void setCarList(List<Car> carList) {
		this.carList = carList;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void search() {
		try {
			carList = new CarDao().CarByPlatNberList(searcKey);

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Search Result: ", e.getMessage()));
		}
	}

	public ReportMissingCar() {
		selectedCar = new Car();
		mitation = new Mitation();
		carList = new CarDao().findAllCars();
	}

	public Mitation getMitation() {
		return mitation;
	}

	public void setMitation(Mitation mitation) {
		this.mitation = mitation;
	}

	public Car getSelectedCar() {
		return selectedCar;
	}

	public void setSelectedCar(Car selectedCar) {
		this.selectedCar = selectedCar;
	}

	public void missing(Car ck) {
		try {
			this.selectedCar = ck;
			Car car = new CarDao().CarByPlatNber(selectedCar.getCarPlate());
			mitation.setStatus("Stolen");
			// carId
			mitation.setCars(selectedCar);
			// ownId
			Owners own = new Owners();
			own.setNationalId(car.getOwner().getNationalId());
			mitation.setOwners(own);
			// userId
			Users user = new Users();
			user.setUsername(username);
			mitation.setUser(user);
			mitation.setComments("dfdagd");
			// save
			new MitationDao().saveObject(mitation);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Car is " + selectedCar.getCarPlate() + " is reported missing", null));

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

	public void found(Car ck) {
		try {
			this.selectedCar = ck;
			Car car = new CarDao().CarByPlatNber(selectedCar.getCarPlate());
			mitation.setStatus("Found");
			mitation.setComments("dgfdfdf");
			// carId
			mitation.setCars(selectedCar);
			// ownId
			Owners own = new Owners();
			own.setNationalId(car.getOwner().getNationalId());
			mitation.setOwners(own);
			// userId
			Users user = new Users();
			user.setUsername(username);
			mitation.setUser(user);
			// save
			new MitationDao().saveObject(mitation);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Car is " + selectedCar.getCarPlate() + " is reported Found", null));

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

	public void comments(Car ck) {
		try {
			this.selectedCar = ck;
			Car car = new CarDao().CarByPlatNber(selectedCar.getCarPlate());
			mitation.setStatus("comments");
			mitation.setComments("dfadfdf");
			// carId
			mitation.setCars(selectedCar);
			// ownId
			Owners own = new Owners();
			own.setNationalId(car.getOwner().getNationalId());
			mitation.setOwners(own);
			// userId
			Users user = new Users();
			user.setUsername(username);
			mitation.setUser(user);
			// save
			new MitationDao().saveObject(mitation);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Comments Added on this Vehicle" + selectedCar.getCarPlate(), null));

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}
}
