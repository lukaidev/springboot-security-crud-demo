package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.access.mapper.MenuItemMapper;
import com.example.access.model.ApiResponse;
import com.example.access.model.MenuItemDto;
import com.example.access.model.PageResponse;
import com.example.constant.ResponseMessage;
import com.example.entity.model.MenuItem;
import com.example.service.MenuItemService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MenuItemController {
	
	private final MenuItemService itemService;
	private final MenuItemMapper itemMapper;

	@GetMapping("/{menuId}/item")
	public ResponseEntity<ApiResponse> getMenuItems(@PathVariable Integer menuId, @RequestParam(required = true) Integer page,
			@RequestParam(required = true) Integer limit) {
		log.info("START --> getMenuItems");
		Page<MenuItem> result = itemService.get(menuId, page, limit);
		List<MenuItemDto> dtoList = itemMapper.mapDtoList(result.getContent());
		log.info("END --> getMenuItems");
		return ResponseEntity.ok(ApiResponse.success(ApiResponse.success(PageResponse.builder()
				.pages(result.getTotalPages())
				.elements(result.getTotalElements())
				.results(dtoList)
				.build(), ResponseMessage.MENU_ITEM_RETRIEVED)));
	}

	@PostMapping("/{menuId}/item/all")
	public ResponseEntity<ApiResponse> saveAllMenuItem(@PathVariable Integer menuId, @RequestBody List<MenuItemDto> dtoList) {
		log.info("START --> saveAllMenuItem");
		List<MenuItem> items = itemMapper.mapEntityList(dtoList, menuId);
		List<MenuItem> resultItems = itemService.saveAll(items);
		List<MenuItemDto> reponse = itemMapper.mapDtoList(resultItems);
		log.info("END --> saveAllMenuItem");
		return ResponseEntity.ok(ApiResponse.success(reponse, ResponseMessage.MENU_ITEM_SAVED));
	}
	
	@PostMapping("/{menuId}/item")
	public ResponseEntity<ApiResponse> saveMenuItem(@PathVariable Integer menuId, @RequestBody MenuItemDto dto) {
		log.info("START --> saveMenuItem");
		MenuItem item = itemMapper.mapEntity(dto, menuId);
		MenuItem resultItem = itemService.save(item);
		MenuItemDto reponse = itemMapper.mapDto(resultItem);
		log.info("END --> saveMenuItem");
		return ResponseEntity.ok(ApiResponse.success(reponse, ResponseMessage.MENU_ITEM_SAVED));
	}
	
	@PutMapping("/{menuId}/item")
	public ResponseEntity<ApiResponse> updateMenuItem(@PathVariable Integer menuId, @RequestBody MenuItemDto dto) {
		log.info("START --> updateMenuItem");
		MenuItem item = itemMapper.mapEntity(dto, menuId);
		MenuItem resultItem = itemService.update(item);
		MenuItemDto reponse = itemMapper.mapDto(resultItem);
		log.info("END --> updateMenuItem");
		return ResponseEntity.ok(ApiResponse.success(reponse, ResponseMessage.MENU_ITEM_SAVED));
	}
	
	@DeleteMapping("/{menuId}/item/{itemId}")
	public ResponseEntity<ApiResponse> deleteMenuItem(@PathVariable Integer menuId, @PathVariable Integer itemId) {
		log.info("START --> deleteMenuItem");
		itemService.delete(menuId, itemId);
		log.info("END --> deleteMenuItem");
		return ResponseEntity.ok(ApiResponse.success(null, ResponseMessage.MENU_DELETED));
	}
	
	
}
