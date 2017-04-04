package rw.itcg.usecase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import rw.itcg.dao.CarDao;
import rw.itcg.dao.MitationDao;
import rw.itcg.model.Car;
import rw.itcg.model.Mitation;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class CarDetails implements Serializable {
	private String searcKey;
	private List<Mitation> mitate;

	public String search() {
		mitate = new MitationDao().findCarByPlatNber(searcKey);
		return "cardDetails";
	}

	public String getSearcKey() {
		return searcKey;
	}

	public void setSearcKey(String searcKey) {
		this.searcKey = searcKey;
	}

	public CarDetails() {
		mitate = new ArrayList<>();
		mitate = new MitationDao().findCarByPlatNber(searcKey);
	}

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

	public List<Mitation> getMitate() {
		return mitate;
	}

	public void setMitate(List<Mitation> mitate) {
		this.mitate = mitate;
	}

}
