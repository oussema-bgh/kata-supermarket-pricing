package model;

import java.util.LinkedHashMap;

public class Item {
	
		private String name;
	    private int unitaryPrice;
	    
	    private LinkedHashMap<Integer, Double> reductionValueByNumber  ;
		
	    
	    
	    public Item(String name, int unitaryPrice) {
			super();
			this.name = name;
			this.unitaryPrice = unitaryPrice;
			reductionValueByNumber = new LinkedHashMap<Integer, Double>();
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
		
		public LinkedHashMap<Integer, Double> getReductionValueByNumber() {
			return reductionValueByNumber;
		}
		public void setReductionValueByNumber(LinkedHashMap<Integer, Double> reductionValueByNumber) {
			this.reductionValueByNumber = reductionValueByNumber;
		}
		
		@Override
		public String toString() {
			return "Item [name=" + name + ", unitaryPrice=" + unitaryPrice + ", reductionValueByNumber="
					+ reductionValueByNumber + " ]";
		}

}
