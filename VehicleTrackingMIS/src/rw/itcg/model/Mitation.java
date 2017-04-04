package rw.itcg.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author NIYOMWUNGERI 10:56:21 PM, Dec 26, 2016
 */
@Entity
public class Mitation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long transactionId;
	@ManyToOne
	@JoinColumn(name = "ownerId")
	private Owners owners;
	@ManyToOne
	@JoinColumn(name = "carId")
	private Car cars;
	@Column(nullable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date ownedDate;

	@ManyToOne
	@JoinColumn(name = "userId")
	private Users user;
	private String status;
	private String comments;

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Owners getOwners() {
		return owners;
	}

	public void setOwners(Owners owners) {
		this.owners = owners;
	}

	public Car getCars() {
		return cars;
	}

	public void setCars(Car cars) {
		this.cars = cars;
	}

	public Date getOwnedDate() {
		return ownedDate;
	}

	public void setOwnedDate(Date ownedDate) {
		this.ownedDate = ownedDate;
	}

}
