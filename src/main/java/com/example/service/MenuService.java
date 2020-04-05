package com.example.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.entity.model.Menu;
import com.example.exception.RecordNotFoundException;
import com.example.repository.MenuRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional(rollbackOn = { Exception.class })
public class MenuService {

	private final MenuRepository menuRepo;
	
	public Page<Menu> get(Integer page, Integer limit){
		Pageable pageable = PageRequest.of(page - 1, limit);
		Page<Menu> pageResult = menuRepo.findAll(pageable);
		return Optional.ofNullable(pageResult).orElse(Page.empty());
	}
	
	public Menu getMenu(Integer id) {
		return menuRepo.findById(id)
				.orElseThrow(RecordNotFoundException::new);
	}

	public List<Menu> saveAll(List<Menu> menus) {
		return menuRepo.saveAll(menus);
	}
	
	public Menu save(Menu menu) {
		return menuRepo.save(menu);
	}
	
	public Menu update(Menu menu) {
		Menu result = menuRepo.findById(menu.getId())
				.orElseThrow(RecordNotFoundException::new);
		menu.setId(result.getId());
		return menuRepo.save(menu);
	}

	public void delete(Integer id) {
		Menu menu = menuRepo.findById(id)
				.orElseThrow(RecordNotFoundException::new);
		menuRepo.delete(menu);
	}

}
