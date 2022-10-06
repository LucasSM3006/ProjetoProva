package br.senai.sc.edu.Prova.repos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.senai.sc.edu.Prova.model.Camiseta;

public interface CamisetaRepository extends CrudRepository<Camiseta, Integer> {
	
	@Query(value = "Select * from Camiseta order by codigo", nativeQuery = true)
	List<Camiseta> findAllById();

	@Query(value = "select * from camiseta where disponibilidade = true order by codigo", nativeQuery = true)
	List<Camiseta> findAllAvailable();

	@Query("from Camiseta where categoria = :categoria order by codigo")
	List<Camiseta> findAllByCategoria(@Param("categoria") String categoria);
}
