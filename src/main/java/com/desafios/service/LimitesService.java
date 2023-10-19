package com.desafios.service;

import java.util.List;

import com.desafios.entity.Limite;
import com.desafios.model.dto.request.LimiteRequestDTO;
import com.desafios.model.dto.response.LimiteResponseDTO;

public interface LimitesService {
	
	LimiteResponseDTO findLimiteResponseDTOById(Long id);

	Limite findLimiteById(Long id);
	
	LimiteResponseDTO createLimite(LimiteRequestDTO request) throws Exception;

	List<LimiteResponseDTO> findAll();

	LimiteResponseDTO updateLimite(Long id, LimiteRequestDTO request) throws Exception;

	void deleteLimite(Long id);
}
