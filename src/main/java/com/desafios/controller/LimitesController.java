package com.desafios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.desafios.model.dto.request.LimiteRequestDTO;
import com.desafios.model.dto.response.LimiteResponseDTO;
import com.desafios.service.LimitesService;

@RestController
@RequestMapping("/limites")
public class LimitesController {
	
	private LimitesService service;
	
	@Autowired
	public LimitesController(LimitesService service) {
		this.service = service;
	}

	@GetMapping("/{id}")
	public ResponseEntity<LimiteResponseDTO> findLimiteResponseDTOById(@PathVariable Long id) {
		return ResponseEntity.ok().body(service.findLimiteResponseDTOById(id));
	}
	
	@GetMapping
	public List<LimiteResponseDTO> findAll(){
		return service.findAll();
	}
	
	@PostMapping
	public LimiteResponseDTO createLimite(@RequestBody LimiteRequestDTO request) {
		return service.createLimite(request);
	}

	@PutMapping("/{id}")
	public LimiteResponseDTO updateLimite(
			@PathVariable("id") Long id,
			@RequestBody LimiteRequestDTO requestDTO
			) {
		return service.updateLimite(id, requestDTO);
	}
	
	@DeleteMapping("/{id}")
	public void deleteLimite(@PathVariable Long id) {
		service.deleteLimite(id);
	}

}
