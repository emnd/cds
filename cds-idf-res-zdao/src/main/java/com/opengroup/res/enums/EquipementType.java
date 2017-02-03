package com.opengroup.res.enums;

public enum EquipementType {
	

		Laptop("Laptop"), Desktop("Desktop");

		private final String label;

		private EquipementType(String label) {
			this.label = label;

		}

		public String getLabel() {
			return label;
		}
}
