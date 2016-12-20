package com.ufc.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ufc.service.IItemService;

@Controller
@RequestMapping("itens")
public class ItemController {

	@Autowired
	private IItemService itemService;

	@GetMapping("")
	public ModelAndView itens() {
		ModelAndView modelAndView = new ModelAndView("item/itens");
		modelAndView.addObject("itens", itemService.buscarTudo());
		return modelAndView;
	}

	@GetMapping("adicionar")
	public ModelAndView adicionarItensForm() {
		ModelAndView modelAndView = new ModelAndView("item/adicionar");
		return modelAndView;
	}

	@PostMapping(value = "adicionar")
	public ModelAndView adicionarItens(String itens, String formato) {
		ModelAndView modelAndView = new ModelAndView();

		try {
			if (formato.equals("JSON")) {
				itemService.adicionarJson(itens);

			} else if (formato.equals("XML")) {
				itemService.adicionarXml(itens);

			}
		} catch (IOException e) {
			modelAndView = adicionarItensForm();
			modelAndView.addObject("hasError", true);
			return modelAndView;
		}

		modelAndView.setViewName("redirect:/itens");
		return modelAndView;
	}
	
	@GetMapping("json")
	public ModelAndView relatorioJson() {
		ModelAndView modelAndView = new ModelAndView();

		String json = null;
		try {
			json = itemService.serializarJson();
		} catch (JsonProcessingException e) {
			modelAndView = itens();
			modelAndView.addObject("hasError", true);
			return modelAndView;
		}

		modelAndView.setViewName("item/relatorio");
		modelAndView.addObject("json", json);
		return modelAndView;
	}

	@GetMapping("xml")
	public ModelAndView relatorioXml() {
		ModelAndView modelAndView = new ModelAndView();

		String xml = null;
		try {
			xml = itemService.serializarXml();
		} catch (JsonProcessingException e) {
			modelAndView = itens();
			modelAndView.addObject("hasError", true);
			return modelAndView;
		}
		
		modelAndView.setViewName("item/relatorio");
		modelAndView.addObject("xml", xml);
		return modelAndView;
	}
}
