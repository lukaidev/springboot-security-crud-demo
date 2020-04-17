package com.example.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.entity.model.Menu;
import com.example.entity.model.MenuItem;
import com.example.exception.RecordNotFoundException;
import com.example.repository.MenuItemRepository;
import com.example.repository.MenuRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional(rollbackOn = { Exception.class })
public class MenuItemService {
	
	private final MenuItemRepository itemRepo;
	private final MenuRepository menuRepo;
	
	public Page<MenuItem> get(Integer menuId, Integer page, Integer limit){
		Pageable pageable = PageRequest.of(page - 1, limit, Sort.by("id").descending());
		Menu menu = menuRepo.findById(menuId)
				.orElseThrow(RecordNotFoundException::new);
		Page<MenuItem> pageResult = itemRepo.findByMenu(menu, pageable);
		return Optional.ofNullable(pageResult).orElse(Page.empty());
	}
	
	public List<MenuItem> saveAll(List<MenuItem> itemList){
		return itemRepo.saveAll(itemList);
	}
	
	public MenuItem save(MenuItem item) {
		return itemRepo.save(item);
	}
	
	public MenuItem update(MenuItem item) {
		MenuItem result = itemRepo.findById(item.getId())
				.orElseThrow(RecordNotFoundException::new);
		item.setId(result.getId());
		return itemRepo.save(item);
	}
	
	public void delete(Integer menuId, Integer itemId) {
		Optional<Menu> menu = menuRepo.findById(menuId);
		Set<MenuItem> items = menu.map(m -> m.getMenuItems()
				.stream()
				.filter(i -> !itemId.equals(i.getId()))
				.collect(Collectors.toSet()))
		.orElseThrow(RecordNotFoundException::new);
		menuRepo.save(menu.map(m -> m.toBuilder()
				.menuItems(items)
				.build())
				.orElseThrow(RecordNotFoundException::new));
	}
	

}
