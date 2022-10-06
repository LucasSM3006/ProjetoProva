package br.senai.sc.edu.Prova.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sc.edu.Prova.repos.dto.AlterarCamisetaDTO;
import br.senai.sc.edu.Prova.repos.dto.AlterarDisponibilidadeDTO;
import br.senai.sc.edu.Prova.repos.dto.CamisetaDTO;
import br.senai.sc.edu.Prova.repos.dto.NovaCamisetaDTO;
import br.senai.sc.edu.Prova.services.CamisetaService;

@RestController
@RequestMapping("/camiseta")
public class CamisetaController {
	
	private final CamisetaService camisetaService;
	
	public CamisetaController(CamisetaService camisetaService) {
		this.camisetaService = camisetaService;
	}
	
	@PostMapping("/add")
	public String createCamiseta(@RequestBody NovaCamisetaDTO novaCamiseta) {
		return camisetaService.save(novaCamiseta);
	}
	
	@PutMapping("/alterar/{id}")
	public String alterarCamiseta(@PathVariable("id") int id, @RequestBody AlterarCamisetaDTO camiseta) {
		camiseta.setCodigo(id);
		return camisetaService.alterarCamiseta(camiseta);
	}
	
	@PutMapping("/alterarDisponibilidade/{id}")
	public String alterarDisponibilidade(@PathVariable("id") int id, @RequestBody AlterarDisponibilidadeDTO camiseta) {
		camiseta.setCodigo(id);
		return camisetaService.alterarDisponibilidade(camiseta);
	}
	
	@DeleteMapping("/excluir/{id}")
	public String excluirCamiseta(@PathVariable("id") int id) {
		return camisetaService.excluirCamiseta(id);
	}
	
	@GetMapping("/listar")
	public List<CamisetaDTO> listarCamisetas() {
		return camisetaService.pegarTodasCamisetas();
	}
	
	@GetMapping("/listarDisponivel")
	public List<CamisetaDTO> listarCamisetasDisponiveis() {
		return camisetaService.pegarTodasCamisetasDisponiveis();
	}
	
	@GetMapping("/listarPorCategoria/{categoria}")
	public List<CamisetaDTO> listarPorCategoria(@PathVariable("categoria") String categoria) {
		return camisetaService.pegarTodasCamisetasPorCategoria(categoria);
	}
}
