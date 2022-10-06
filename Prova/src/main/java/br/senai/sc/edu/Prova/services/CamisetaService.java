package br.senai.sc.edu.Prova.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.senai.sc.edu.Prova.model.Camiseta;
import br.senai.sc.edu.Prova.repos.CamisetaRepository;
import br.senai.sc.edu.Prova.repos.dto.AlterarCamisetaDTO;
import br.senai.sc.edu.Prova.repos.dto.AlterarDisponibilidadeDTO;
import br.senai.sc.edu.Prova.repos.dto.CamisetaDTO;
import br.senai.sc.edu.Prova.repos.dto.NovaCamisetaDTO;

@Service
public class CamisetaService {
	
	private final CamisetaRepository camisetaRepository;
	
	public CamisetaService(CamisetaRepository camisetaRepository) {
		this.camisetaRepository = camisetaRepository;
	}
	
	public String save(NovaCamisetaDTO novaCamiseta) {
		Camiseta camiseta = new Camiseta();
		
		camiseta.setCategoria(novaCamiseta.getCategoria());
		camiseta.setCor(novaCamiseta.getCor());
		camiseta.setDescricao(novaCamiseta.getDescricao());
		camiseta.setDisponibilidade(novaCamiseta.isDisponibilidade());
		camiseta.setModelo(novaCamiseta.getModelo());
		camiseta.setCusto(novaCamiseta.getCusto());
		
		camisetaRepository.save(camiseta);
		
		return "OK.";
	}
	
	public String alterarCamiseta(AlterarCamisetaDTO alterarCamiseta) {
		Optional<Camiseta> camiseta = camisetaRepository.findById(alterarCamiseta.getCodigo());
		
		if(camiseta.isPresent()) {
			Camiseta c = camiseta.get();

			c.setCategoria(alterarCamiseta.getCategoria());
			c.setCor(alterarCamiseta.getCor());
			c.setCusto(alterarCamiseta.getCusto());
			c.setDescricao(alterarCamiseta.getDescricao());
			c.setDisponibilidade(alterarCamiseta.isDisponibilidade());
			c.setModelo(alterarCamiseta.getModelo());
			
			camisetaRepository.save(c);
			
			return "OK.";
		}
		
		return "Camiseta com id '" + alterarCamiseta.getCodigo() + "' não existe.";
	}
	
	public String alterarDisponibilidade(AlterarDisponibilidadeDTO alterarDisponibilidade) {
		Optional<Camiseta> camiseta = camisetaRepository.findById(alterarDisponibilidade.getCodigo());
		
		if(camiseta.isPresent()) {
			Camiseta c = camiseta.get();
			
			c.setDisponibilidade(alterarDisponibilidade.isDisponibilidade());
			
			camisetaRepository.save(c);
			
			return "OK.";
		}
		
		return "Camiseta com id '" + alterarDisponibilidade.getCodigo() + "' não existe.";
	}
	
	public String excluirCamiseta(int id) {
		Optional<Camiseta> camiseta = camisetaRepository.findById(id);
		
		if(camiseta.isPresent()) {
			camisetaRepository.delete(camiseta.get());
			
			return "OK.";
		}
		
		return "Camiseta com id '" + id + "' não existe.";
	}
	
	public List<CamisetaDTO> pegarTodasCamisetas() {
		List<Camiseta> camisetas = (List<Camiseta>) camisetaRepository.findAllById();
		return mapIntoCamisetaDTO(camisetas);
	}

	public List<CamisetaDTO> pegarTodasCamisetasDisponiveis() {
		List<Camiseta> camisetas = (List<Camiseta>) camisetaRepository.findAllAvailable();
		return mapIntoCamisetaDTO(camisetas);
	}

	public List<CamisetaDTO> pegarTodasCamisetasPorCategoria(String categoria) {
		List<Camiseta> camisetas = (List<Camiseta>) camisetaRepository.findAllByCategoria(categoria);
		return mapIntoCamisetaDTO(camisetas);
	}
	
	private List<CamisetaDTO> mapIntoCamisetaDTO(List<Camiseta> camisetas) {
		List<CamisetaDTO> stream = camisetas.stream().map(camiseta -> {
			CamisetaDTO c = new CamisetaDTO();
			c.setCodigo(camiseta.getCodigo());
			c.setCategoria(camiseta.getCategoria());
			c.setCor(camiseta.getCor());
			c.setCusto(camiseta.getCusto());
			c.setDescricao(camiseta.getDescricao());
			c.setDisponibilidade(camiseta.isDisponibilidade());
			c.setModelo(camiseta.getModelo());
			return c;
		}).collect(Collectors.toList());
		return stream;
	}
}
