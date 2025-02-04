package com.movFin.api.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.movFin.api.model.Cnab;

@Repository
public interface CnabRepository extends CrudRepository<Cnab, Long> {
	List<Cnab> findBycpf(String cpf);
}