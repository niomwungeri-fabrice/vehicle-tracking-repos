package rw.itcg.service;

/**
 * @author NIYOMWUNGERI 11:23:24 PM, Dec 26, 2016
 */
public enum CarTypes {
	JEEP("Jeep"), COASTER("Coaster"), VAN("Van"), MINIBUS("Mini-Bus");

	private String realNames;

	private CarTypes(String realNames) {
		this.realNames = realNames;
	}

	@Override
	public String toString() {
		return this.realNames;
	}

}
