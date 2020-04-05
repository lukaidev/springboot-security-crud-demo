package com.example.access.model;

import java.util.Set;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuDto {
	
	private Integer id;
	private String name;
	private boolean inUsed;
	private Set<MenuItemDto> menuItems;

}
