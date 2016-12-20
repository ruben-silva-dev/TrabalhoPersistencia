package com.ufc.service;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ufc.model.Item;

public interface IItemService {

	List<Item> buscarTudo();

	void adicionarJson(String itens) throws JsonParseException, JsonMappingException, IOException;

	void adicionarXml(String itens) throws JsonParseException, JsonMappingException, IOException;

	String serializarJson() throws JsonProcessingException;

	String serializarXml() throws JsonProcessingException;
}
