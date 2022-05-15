package com.movFin.api.service;

import java.util.List;

import com.movFin.api.model.Cnab;
import com.movFin.api.repository.CnabRepository;

public interface ICnabService {
  
	public Cnab createCnab(String line);
	
	public Double convertValue(String valor, boolean isEntry);
	
	public String discoverType (String type);
	
	public void saveManyCnab(List<Cnab> cnabList, CnabRepository repository);
}