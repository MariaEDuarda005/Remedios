package com.remedios.curso.repositories;

import com.remedios.curso.remedio.Remedio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RemedioRepository extends JpaRepository<Remedio, Long> {

}
