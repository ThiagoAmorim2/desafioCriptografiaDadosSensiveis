package com.desafios.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.desafios.entity.Limite;
import com.desafios.model.dto.request.LimiteRequestDTO;
import com.desafios.model.dto.response.LimiteResponseDTO;
import com.desafios.model.mapper.LimiteMapper;
import com.desafios.repository.LimitesRepository;
import com.desafios.service.LimitesService;

@Service
public class LimitesServiceImpl implements LimitesService {

	private final LimitesRepository repository;
	private final PasswordEncoder encoder;

	@Autowired
	public LimitesServiceImpl(LimitesRepository repository, PasswordEncoder encoder) {
		this.repository = repository;
		this.encoder = encoder;
	}

	@Override
	public LimiteResponseDTO findLimiteResponseDTOById(Long id) {
		return LimiteMapper.limiteToLimiteResponse(repository.findById(id).orElse(null));
	}

	@Override
	public Limite findLimiteById(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public LimiteResponseDTO createLimite(LimiteRequestDTO request) {
		Limite limiteNovo = LimiteMapper.limiteRequestToLimite(request);
		encriptaDados(limiteNovo);
		repository.save(limiteNovo);
		return LimiteMapper.limiteToLimiteResponse(limiteNovo);
	}

	private void encriptaDados(Limite limiteNovo) {
		limiteNovo.setCreditCardToken(encoder.encode(limiteNovo.getCreditCardToken()));
		limiteNovo.setUserDocument(encoder.encode(limiteNovo.getUserDocument()));
	}

	@Override
	public List<LimiteResponseDTO> findAll() {
		List<Limite> limiteList = repository.findAll();
		return LimiteMapper.listLimiteToListLimiteResponse(limiteList);
	}

	@Override
	public LimiteResponseDTO updateLimite(Long id, LimiteRequestDTO request) {
		Optional<LimiteRequestDTO> requestRecebida = Optional.of(request);
		LimiteRequestDTO objetoRecedibo = requestRecebida.get();
		Optional<Limite> idProcurado = Optional.of(findLimiteById(id));
		if (requestRecebida.isPresent()) {
			Limite objetoLimite = idProcurado.get();
			encriptaDados(objetoLimite);
			if (objetoRecedibo.getCreditCardToken() != null) {
				objetoLimite.setCreditCardToken(requestRecebida.get().getCreditCardToken());
			}
			if (objetoRecedibo.getUserDocument() != null) {
				objetoLimite.setUserDocument(requestRecebida.get().getUserDocument());
			}
			if (objetoRecedibo.getValue() != null) {
				objetoLimite.setValue(requestRecebida.get().getValue());
			}
			repository.save(objetoLimite);
			return LimiteMapper.limiteToLimiteResponse(objetoLimite);
		}
		return null;
	}

}
