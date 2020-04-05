package com.example.access.mapper;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.access.model.MenuDto;
import com.example.constant.ErrorMessage;
import com.example.entity.model.Menu;
import com.example.entity.model.MenuItem;
import com.example.exception.DemoException;

@Component
public class MenuMapper implements BaseMapper<Menu, MenuDto> {

	@Autowired
	ModelMapper modelMapper;

	@Override
	public boolean isEntityValid(Menu entity) {
		return true;
	}

	@Override
	public boolean isDtoValid(MenuDto dto) {
		return true;
	}

	@Override
	public Menu mapEntity(MenuDto dto) {
		Menu menu =  modelMapper.map(dto, Menu.class);
		Stream<MenuItem> item = Optional.ofNullable(menu.getMenuItems()).map(itemList -> itemList.stream()
				.map(i -> MenuItem.builder()
						.id(i.getId())
						.name(i.getName())
						.price(i.getPrice())
						.menu(menu).build()))
				.orElseThrow(() -> new DemoException(ErrorMessage.INVALID_PAYLOAD_FORMAT));
		menu.setMenuItems(item.collect(Collectors.toSet()));
		return menu;
	}

	@Override
	public MenuDto mapDto(Menu entity) {
		return modelMapper.map(entity, MenuDto.class);
	}

}
