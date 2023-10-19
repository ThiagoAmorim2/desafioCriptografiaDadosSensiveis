package com.desafios.model.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LimiteResponseDTO {

	private Long id;
	private String userDocument;
	private String creditCardToken;
	private Long value;
	
}
