package com.example.constant;

public enum ResponseMessage {

	MENU_SAVED("Menu saved successfully"),
	MENU_DELETED("Menu deleted successfully"),
	MENU_RETRIEVED("Menu retrieved successfully"),
	MENU_ITEM_SAVED("Menu item/s saved successfully"),
	MENU_ITEM_DELETED("Menu item deleted successfully"),
	MENU_ITEM_RETRIEVED("Menu items retrieved successfully");

	private String value;

	ResponseMessage(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
