package com.desafios.model.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.desafios.entity.Limite;
import com.desafios.model.dto.request.LimiteRequestDTO;
import com.desafios.model.dto.response.LimiteResponseDTO;

@Component
public class LimiteMapper {

	public static Limite limiteRequestToLimite(LimiteRequestDTO dto) {
		Limite entity = new Limite();
		entity.setUserDocument(dto.getUserDocument());
		entity.setCreditCardToken(dto.getCreditCardToken());
		entity.setValue(dto.getValue());
		return entity;
	}

	public static LimiteResponseDTO limiteToLimiteResponse(Limite limiteEncontrado) {
		return LimiteResponseDTO
				.builder()
				.id(limiteEncontrado.getId())
				.creditCardToken(limiteEncontrado.getCreditCardToken())
				.userDocument(limiteEncontrado.getUserDocument())
				.value(limiteEncontrado.getValue())
				.build();
	}

	public static List<LimiteResponseDTO> listLimiteToListLimiteResponse(List<Limite> limiteList) {
		List<LimiteResponseDTO> responseDTOs = new ArrayList<>();
		for(Limite limite : limiteList) {
			LimiteResponseDTO object = LimiteResponseDTO.builder()
			.id(limite.getId())
			.userDocument(limite.getUserDocument())
			.creditCardToken(limite.getCreditCardToken())
			.value(limite.getValue())
			.build();
			responseDTOs.add(object);
		}
		return responseDTOs;
	}
}
