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

import com.example.access.mapper.MenuMapper;
import com.example.access.model.ApiResponse;
import com.example.access.model.MenuDto;
import com.example.access.model.PageResponse;
import com.example.constant.ResponseMessage;
import com.example.entity.model.Menu;
import com.example.service.MenuService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MenuController {

	private final MenuService menuService;
	private final MenuMapper menuMapper;
	
	@GetMapping
	public ResponseEntity<ApiResponse> getMenuList(@RequestParam(required = true) Integer page,
			@RequestParam(required = true) Integer limit){
		log.info("START --> getMenuList");
		Page<Menu> result = menuService.get(page, limit);
		List<MenuDto> dtoList = menuMapper.mapDtoList(result.getContent());
		log.info("END --> getMenuList");
		return ResponseEntity.ok(ApiResponse.success(PageResponse.builder()
				.pages(result.getTotalPages())
				.elements(result.getTotalElements())
				.results(dtoList)
				.build(), ResponseMessage.MENU_RETRIEVED));
	}

	@PostMapping("/all")
	public ResponseEntity<ApiResponse> saveAllMenu(@RequestBody List<MenuDto> dtoList) {
		log.info("START --> saveAllMenu");
		List<Menu> menus = menuMapper.mapEntityList(dtoList);
		List<Menu> resultMenus = menuService.saveAll(menus);
		List<MenuDto> reponse = menuMapper.mapDtoList(resultMenus);
		log.info("END --> saveAllMenu");
		return ResponseEntity.ok(ApiResponse.success(reponse, ResponseMessage.MENU_SAVED));
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse> saveMenu(@RequestBody MenuDto dto) {
		log.info("START --> saveMenu");
		Menu menu = menuMapper.mapEntity(dto);
		Menu resultMenus = menuService.save(menu);
		MenuDto reponse = menuMapper.mapDto(resultMenus);
		log.info("END --> saveMenu");
		return ResponseEntity.ok(ApiResponse.success(reponse, ResponseMessage.MENU_SAVED));
	}
	
	@PutMapping
	public ResponseEntity<ApiResponse> updateMenu(@RequestBody MenuDto dto) {
		log.info("START --> updateMenu");
		Menu menu = menuMapper.mapEntity(dto);
		Menu resultMenus = menuService.update(menu);
		MenuDto reponse = menuMapper.mapDto(resultMenus);
		log.info("END --> updateMenu");
		return ResponseEntity.ok(ApiResponse.success(reponse, ResponseMessage.MENU_SAVED));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteMenu(@PathVariable Integer id) {
		log.info("START --> deleteMenu");
		menuService.delete(id);
		log.info("END --> deleteMenu");
		return ResponseEntity.ok(ApiResponse.success(null, ResponseMessage.MENU_DELETED));
	}

}
