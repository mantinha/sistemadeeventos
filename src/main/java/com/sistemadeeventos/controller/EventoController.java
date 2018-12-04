package com.sistemadeeventos.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sistemadeeventos.model.Convidado;
import com.sistemadeeventos.model.Evento;
import com.sistemadeeventos.repository.ConvidadoRepository;
import com.sistemadeeventos.repository.EventoRepository;

@Controller
public class EventoController {
	
	@Autowired
	private EventoRepository eventoCrud;
	
	@Autowired
	private ConvidadoRepository convidadoCrud;
	
	
	@RequestMapping("/listadeeventos")
	public ModelAndView listaEventos() {
		ModelAndView mv = new ModelAndView("index");
		Iterable<Evento> eventos = eventoCrud.findAll();
		mv.addObject("listadeeventos", eventos);
		return mv;
	}
	
	@RequestMapping(value="/cadastrarevento", method=RequestMethod.GET)
	public String form() {
		return "eventodir/cadastrarevento";
	}
	
	@RequestMapping(value="/cadastrarevento", method=RequestMethod.POST)
	public String form(@Valid Evento evento, BindingResult result, RedirectAttributes redirectAttrs) {
		if(result.hasErrors()) {
			redirectAttrs.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/cadastrarevento";
		}
		eventoCrud.save(evento);
		redirectAttrs.addFlashAttribute("mensagem", "Evento cadastrado!");
		return "redirect:/cadastrarevento";
	}
	
	@RequestMapping("/deletaevento")
	public String deletaEvento(long codigo) {
		Evento evento = eventoCrud.findByCodigo(codigo);
		eventoCrud.delete(evento);
		return "redirect:/listadeeventos";
	}
	
	@RequestMapping(value="/{codigo}", method=RequestMethod.GET)
	public ModelAndView detalhesGet(@PathVariable("codigo") long codigo) {
		Evento evento = eventoCrud.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("eventodir/detalhesdoevento");
		mv.addObject("evento", evento);
		System.out.println("evento" + evento);
		
		Iterable<Convidado> convidados = convidadoCrud.findByEvento(evento);
		mv.addObject("convidados", convidados);
		return mv;
	}
	
	@RequestMapping(value="/{codigo}", method=RequestMethod.POST)
	public String detalhesPost(@PathVariable("codigo") long codigo, @Valid Convidado convidado, BindingResult result, RedirectAttributes redirectAttrs) {
		if(result.hasErrors()) {
			redirectAttrs.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/{codigo}";
		}
		Evento evento = eventoCrud.findByCodigo(codigo);
		convidado.setEvento(evento);
		convidadoCrud.save(convidado);
		redirectAttrs.addFlashAttribute("mensagem", "Convidado adicionado!");
		return "redirect:/{codigo}";
	}
	
	@RequestMapping("/deletaconvidado")
	public String deletaConvidado(String rg) {
		Convidado convidado = convidadoCrud.findByRg(rg);
		convidadoCrud.delete(convidado);
		
		Evento evento = convidado.getEvento();
		long codigoLong = evento.getCodigo();
		String codigo = "" + codigoLong;
		return "redirect:/" + codigo;
	}
}
