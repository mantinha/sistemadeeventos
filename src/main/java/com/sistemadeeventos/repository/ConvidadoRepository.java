package com.sistemadeeventos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistemadeeventos.model.Convidado;
import com.sistemadeeventos.model.Evento;

@Repository
public interface ConvidadoRepository extends JpaRepository<Convidado, String>{
	
	Iterable<Convidado> findByEvento(Evento evento);
	Convidado findByRg(String rg);
	
}
