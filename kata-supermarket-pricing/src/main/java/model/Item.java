package model;

public class Item {

	private String name;
	private int unitaryPrice;
	private boolean byWeight;

	public Item(String name, int unitaryPrice, boolean byWeight) {
		super();
		this.name = name;
		this.unitaryPrice = unitaryPrice;
		this.byWeight = byWeight;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUnitaryPrice() {
		return unitaryPrice;
	}

	public void setUnitaryPrice(int unitaryPrice) {
		this.unitaryPrice = unitaryPrice;
	}

	public boolean isByWeight() {
		return byWeight;
	}

	public void setByWeight(boolean byWeight) {
		this.byWeight = byWeight;
	}

	@Override
	public String toString() {
		return "Item [name=" + name + ", unitaryPrice=" + unitaryPrice + ", byWeight=" + byWeight + "]";
	}

}
