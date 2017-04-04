package rw.itcg.service;

/**
 * @author NIYOMWUNGERI 7:28:47 PM, Dec 28, 2016
 */
public enum ProvinceEnum {
	KIGALI("Kigali"), EAST("East"), NORTH("North"), SOUTH("South"), WEST("West");

	private String provinces;

	private ProvinceEnum(String provinces) {
		this.provinces = provinces;
	}

	@Override
	public String toString() {
		return this.provinces;
	}
}
