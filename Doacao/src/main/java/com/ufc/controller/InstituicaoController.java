package com.ufc.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ufc.model.Instituicao;
import com.ufc.service.IInstituicaoService;

@Controller
@RequestMapping("instituicoes")
public class InstituicaoController {

	@Autowired
	private IInstituicaoService instituicaoService;

	@GetMapping("")
	public ModelAndView instituicoes() {
		ModelAndView modelAndView = new ModelAndView("instituicao/instituicoes");
		modelAndView.addObject("instituicoes", instituicaoService.buscarTudo());
		return modelAndView;
	}

	@GetMapping("{idInstituicao}")
	public ModelAndView instituicao(@PathVariable("idInstituicao") Instituicao instituicao) {
		ModelAndView modelAndView = new ModelAndView("instituicao/instituicao");
		modelAndView.addObject("instituicao", instituicao);
		return modelAndView;
	}

	@GetMapping("adicionar")
	public ModelAndView adicionarInstituicoesForm() {
		ModelAndView modelAndView = new ModelAndView("instituicao/adicionar");
		return modelAndView;
	}

	@PostMapping(value = "adicionar")
	public ModelAndView adicionarItens(String itens, String formato) {
		ModelAndView modelAndView = new ModelAndView();

		try {
			if (formato.equals("JSON")) {
				instituicaoService.adicionarJson(itens);

			} else if (formato.equals("XML")) {
				instituicaoService.adicionarXml(itens);

			}
		} catch (IOException e) {
			modelAndView = adicionarInstituicoesForm();
			modelAndView.addObject("hasError", true);
			return modelAndView;
		}

		modelAndView.setViewName("redirect:/instituicoes");
		return modelAndView;
	}

	@GetMapping("json")
	public ModelAndView relatorioJson() {
		ModelAndView modelAndView = new ModelAndView();

		String json = null;
		try {
			json = instituicaoService.serializarJson();
		} catch (JsonProcessingException e) {
			modelAndView = instituicoes();
			modelAndView.addObject("hasError", true);
			return modelAndView;
		}

		modelAndView.setViewName("instituicao/relatorio");
		modelAndView.addObject("json", json);
		return modelAndView;
	}

	@GetMapping("xml")
	public ModelAndView relatorioXml() {
		ModelAndView modelAndView = new ModelAndView();

		String xml = null;
		try {
			xml = instituicaoService.serializarXml();
		} catch (JsonProcessingException e) {
			modelAndView = instituicoes();
			modelAndView.addObject("hasError", true);
			return modelAndView;
		}
		
		modelAndView.setViewName("instituicao/relatorio");
		modelAndView.addObject("xml", xml);
		return modelAndView;
	}
}
