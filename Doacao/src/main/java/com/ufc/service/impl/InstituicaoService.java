package com.ufc.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.ufc.model.Instituicao;
import com.ufc.repository.InstituicaoRepository;
import com.ufc.service.IInstituicaoService;

@Service
public class InstituicaoService implements IInstituicaoService {

	@Autowired
	private InstituicaoRepository instituicaoRepository;

	@Override
	public List<Instituicao> buscarTudo() {
		return instituicaoRepository.findAll();
	}

	@Override
	public void adicionarJson(String instituicoes) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();

		List<Instituicao> listaInstituicoes = mapper.readValue(instituicoes,
				mapper.getTypeFactory().constructCollectionType(List.class, Instituicao.class));

		instituicaoRepository.save(listaInstituicoes);
	}

	@Override
	public void adicionarXml(String instituicoes) throws JsonParseException, JsonMappingException, IOException {
		XmlMapper mapper = new XmlMapper();

		List<Instituicao> listaInstituicoes = mapper.readValue(instituicoes,
				mapper.getTypeFactory().constructCollectionType(List.class, Instituicao.class));

		instituicaoRepository.save(listaInstituicoes);
	}

	@Override
	public String serializarJson() throws JsonProcessingException {
		List<Instituicao> instituicoes = instituicaoRepository.findAll();
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(instituicoes);
		
		return json;
	}

	@Override
	public String serializarXml() throws JsonProcessingException {
		List<Instituicao> instituicoes = instituicaoRepository.findAll();
		
		XmlMapper mapper = new XmlMapper();
		String xml = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(instituicoes);
		
		return xml;
	}
}
