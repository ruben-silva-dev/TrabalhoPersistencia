package com.ufc.service;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ufc.model.Instituicao;

public interface IInstituicaoService {

	List<Instituicao> buscarTudo();

	void adicionarJson(String instituicoes) throws JsonParseException, JsonMappingException, IOException;

	void adicionarXml(String instituicoes) throws JsonParseException, JsonMappingException, IOException;

	String serializarJson() throws JsonProcessingException;
	
	String serializarXml() throws JsonProcessingException;
}
