package rw.itcg.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * @author NIYOMWUNGERI 10:31:36 PM, Dec 26, 2016
 */
@Entity
public class Car {
	@Id
	private String carPlate;
	private String brand;
	private String type;
	private Integer manifacturedYear;
	private Integer sits;
	private Integer chassisNumber;

	@ManyToOne
	@JoinColumn(name = "ownerId")
	private Owners owner;

	@OneToMany(mappedBy = "cars")
	private List<Mitation> imitate;

	public List<Mitation> getImitate() {
		return imitate;
	}

	public void setImitate(List<Mitation> imitate) {
		this.imitate = imitate;
	}

	public Integer getManifacturedYear() {
		return manifacturedYear;
	}

	public void setManifacturedYear(Integer manifacturedYear) {
		this.manifacturedYear = manifacturedYear;
	}

	public Integer getSits() {
		return sits;
	}

	public void setSits(Integer sits) {
		this.sits = sits;
	}

	public Integer getChassisNumber() {
		return chassisNumber;
	}

	public void setChassisNumber(Integer chassisNumber) {
		this.chassisNumber = chassisNumber;
	}

	public Owners getOwner() {
		return owner;
	}

	public void setOwner(Owners owner) {
		this.owner = owner;
	}

	public String getCarPlate() {
		return carPlate;
	}

	public void setCarPlate(String carPlate) {
		this.carPlate = carPlate;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
