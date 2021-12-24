package com.bsu.catfeeder.controller;

import com.bsu.catfeeder.service.CatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:8080")
@RequestMapping("/cat-feeder/{userId}")
@RequiredArgsConstructor
public class CatController {
	private final CatService catService;

	@PutMapping("release-cat")
	public void releaseCat(@PathVariable Long userId) {
		catService.releaseDaCat(userId);
	}

	@PutMapping("/catch-cat")
	public void catchCat(@PathVariable Long userId) {
		catService.catchDaCat(userId);
	}
}
