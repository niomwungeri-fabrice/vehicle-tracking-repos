package rw.itcg.usecase;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FlowEvent;

import rw.itcg.dao.CarDao;
import rw.itcg.dao.MitationDao;
import rw.itcg.dao.OwnerDao;
import rw.itcg.model.Car;
import rw.itcg.model.Mitation;
import rw.itcg.model.Owners;
import rw.itcg.model.Users;
import rw.itcg.service.CarTypes;
import rw.itcg.service.ProvinceEnum;

/**
 * @author NIYOMWUNGERI 11:27:50 PM, Dec 27, 2016
 */
@ManagedBean
@ViewScoped
public class RegisterOwners implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Owners owner;
	private Car car;
	private List<String> carType;
	private String SelectedType;
	private List<String> provinces;
	private String SelectedProvinces;
	private boolean skip;
	public String action;
	private Mitation ownership;

	public Mitation getOwnership() {
		return ownership;
	}

	public void setOwnership(Mitation ownership) {
		this.ownership = ownership;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public RegisterOwners() {

		provinces = new ArrayList<String>();
		carType = new ArrayList<String>();
		owner = new Owners();
		car = new Car();
		ownership = new Mitation();
		for (CarTypes c : CarTypes.values()) {
			carType.add(c.toString());
		}
		for (ProvinceEnum p : ProvinceEnum.values()) {
			provinces.add(p.toString());
		}
	}

	public void addNewCar() {
		try {

			Owners o = owner;
			car.setOwner(o);
			car.setType(SelectedType);
			new CarDao().saveObject(car);

			// recording ownship

			ownership.setCars(car);
			ownership.setOwners(o);
			ownership.setStatus("New");
			ownership.setComments("fdfsd");
			// registered by

			Users registered = new Users();
			registered.setUsername(action);
			ownership.setUser(registered);

			new MitationDao().saveObject(ownership);

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Car added", null));
			car = new Car();
			ownership = new Mitation();

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:" + e.getMessage(), null));
		}
	}

	public void register() throws IOException {
		try {
			// Registering Owner
			owner.setProvinces(SelectedProvinces);
			new OwnerDao().saveObject(owner);
			// Registering Owner's Cars
			Owners o = owner;
			car.setOwner(o);
			car.setType(SelectedType);
			new CarDao().saveObject(car);

			// recording ownship
			ownership.setCars(car);
			ownership.setOwners(o);
			ownership.setStatus("New");
			// registered by

			Users registered = new Users();
			registered.setUsername(action);
			ownership.setUser(registered);

			new MitationDao().saveObject(ownership);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Car " + car.getCarPlate() + " Registered to "
							+ owner.getFirstName().toUpperCase() + " " + owner.getLastName(), null));
			car = new Car();
			ownership = new Mitation();
		} catch (Exception e) {

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:" + e.getMessage(), null));
			Logging.logs(e.getMessage(), getClass().getSimpleName());
		}
	}

	public String onFlowProcess(FlowEvent event) {
		if (skip) {
			return "confirm";
		} else {
			return event.getNewStep();
		}
	}

	public String getSelectedType() {
		return SelectedType;
	}

	public List<String> getProvinces() {
		return provinces;
	}

	public void setProvinces(List<String> provinces) {
		this.provinces = provinces;
	}

	public String getSelectedProvinces() {
		return SelectedProvinces;
	}

	public void setSelectedProvinces(String selectedProvinces) {
		SelectedProvinces = selectedProvinces;
	}

	public void setSelectedType(String selectedType) {
		SelectedType = selectedType;
	}

	public List<String> getCarType() {
		return carType;
	}

	public void setCarType(List<String> carType) {
		this.carType = carType;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Owners getOwner() {
		return owner;
	}

	public void setOwner(Owners owner) {
		this.owner = owner;
	}

}
