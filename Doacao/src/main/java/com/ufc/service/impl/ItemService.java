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
import com.ufc.model.Item;
import com.ufc.repository.ItemRepository;
import com.ufc.service.IItemService;

@Service
public class ItemService implements IItemService {

	@Autowired
	private ItemRepository itemRepository;

	@Override
	public List<Item> buscarTudo() {
		return itemRepository.findAll();
	}

	@Override
	public void adicionarJson(String itens) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();

		List<Item> listaItens = mapper.readValue(itens,
				mapper.getTypeFactory().constructCollectionType(List.class, Item.class));

		itemRepository.save(listaItens);
	}

	@Override
	public void adicionarXml(String itens) throws JsonParseException, JsonMappingException, IOException {
		XmlMapper mapper = new XmlMapper();

		List<Item> listaItens = mapper.readValue(itens,
				mapper.getTypeFactory().constructCollectionType(List.class, Item.class));

		itemRepository.save(listaItens);
	}

	@Override
	public String serializarJson() throws JsonProcessingException {
		List<Item> itens = itemRepository.findAll();
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(itens);
		
		return json;
	}

	@Override
	public String serializarXml() throws JsonProcessingException {
		List<Item> itens = itemRepository.findAll();
		
		XmlMapper mapper = new XmlMapper();
		String xml = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(itens);
		
		return xml;
	}

}
