package com.desafios.model.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LimiteRequestDTO {

	private Long id;
	private String userDocument;
	private String creditCardToken;
	private Long value;
	
}
