package com.example.access.mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.access.model.MenuItemDto;
import com.example.entity.model.MenuItem;
import com.example.service.MenuService;

@Component
public class MenuItemMapper implements BaseMapper<MenuItem, MenuItemDto>{
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	MenuService menuService;

	@Override
	public boolean isEntityValid(MenuItem entity) {
		return true;
	}

	@Override
	public boolean isDtoValid(MenuItemDto dto) {
		return true;
	}

	@Override
	public MenuItem mapEntity(MenuItemDto dto) {
		return modelMapper.map(dto, MenuItem.class);
	}

	@Override
	public MenuItemDto mapDto(MenuItem entity) {
		return modelMapper.map(entity, MenuItemDto.class);
	}
	
	public MenuItem mapEntity(MenuItemDto dto, Integer menuId) {
		return mapEntity(dto).toBuilder()
				.menu(menuService.getMenu(menuId))
				.build();
	}

	public List<MenuItem> mapEntityList(List<MenuItemDto> dtoList, Integer menuId) {
		if (dtoList == null) {
			return Collections.emptyList();
		}
		return dtoList.stream().map(d -> mapEntity(d, menuId)).collect(Collectors.toList());
	}
	
	

}
