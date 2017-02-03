package com.opengroup.res.enums;

public enum StateType {

Available("Available"), Loaned("Loaned");
	
	private final String label;
		
		private StateType(String label){
			this.label=label;
			
		}
		
	public String getLabel(){
		return label;
	}

	
	
	
}
