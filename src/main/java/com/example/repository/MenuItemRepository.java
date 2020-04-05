package com.example.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.model.Menu;
import com.example.entity.model.MenuItem;


@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {

	Page<MenuItem> findByMenu(Menu menu, Pageable pageable);

}
