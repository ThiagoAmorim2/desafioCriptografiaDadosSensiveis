package com.desafios.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafios.entity.Limite;
import com.desafios.model.dto.request.LimiteRequestDTO;
import com.desafios.model.dto.response.LimiteResponseDTO;
import com.desafios.model.mapper.LimiteMapper;
import com.desafios.repository.LimitesRepository;
import com.desafios.security.Crypto;
import com.desafios.service.LimitesService;

@Service
public class LimitesServiceImpl implements LimitesService {

	private final LimitesRepository repository;

	@Autowired
	public LimitesServiceImpl(LimitesRepository repository) {
		this.repository = repository;
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
	public LimiteResponseDTO createLimite(LimiteRequestDTO request) throws Exception {
		Limite limiteNovo = LimiteMapper.limiteRequestToLimite(request);
		encriptografarDados(limiteNovo);
		repository.save(limiteNovo);
		return LimiteMapper.limiteToLimiteResponse(limiteNovo);
	}

	private void encriptografarDados(Limite limiteNovo) throws Exception {
		String creditCardToken = limiteNovo.getCreditCardToken();
		limiteNovo.setCreditCardToken(Crypto.sha512(creditCardToken));
		String userDocument = limiteNovo.getCreditCardToken();
		limiteNovo.setUserDocument(Crypto.sha512(userDocument));
	}

	private void encriptografarDadosLimiteRequestDTO(LimiteRequestDTO limiteNovo) throws Exception {
		if (limiteNovo.getCreditCardToken() != null) {
			String creditCardToken = limiteNovo.getCreditCardToken();
			limiteNovo.setCreditCardToken(Crypto.sha512(creditCardToken));
		}
		if (limiteNovo.getUserDocument() != null) {
			String userDocument = limiteNovo.getUserDocument();
			limiteNovo.setUserDocument(Crypto.sha512(userDocument));
		}
	}

	@Override
	public List<LimiteResponseDTO> findAll() {
		List<Limite> limiteList = repository.findAll();
		return LimiteMapper.listLimiteToListLimiteResponse(limiteList);
	}

	@Override
	public LimiteResponseDTO updateLimite(Long id, LimiteRequestDTO request) throws Exception {
		Optional<LimiteRequestDTO> requestRecebida = Optional.of(request);
		LimiteRequestDTO objetoRecedibo = requestRecebida.get();
		encriptografarDadosLimiteRequestDTO(objetoRecedibo);
		Optional<Limite> idProcurado = Optional.of(findLimiteById(id));
		if (requestRecebida.isPresent()) {
			Limite objetoLimite = idProcurado.get();
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

	@Override
	public void deleteLimite(Long id) {
		repository.deleteById(id);
	}

}
