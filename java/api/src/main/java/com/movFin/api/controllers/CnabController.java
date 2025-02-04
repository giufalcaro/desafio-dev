package com.movFin.api.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.movFin.api.model.Cnab;
import com.movFin.api.repository.CnabRepository;
import com.movFin.api.service.CnabServiceFactory;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class CnabController {
	
	@Autowired
	private CnabRepository cnabRepository;

	
	@PostMapping("/file")
	public String importFile(@RequestParam("file")MultipartFile multiParfile) {
		try {
			List<Cnab> cnabList = new ArrayList<Cnab>();
			InputStream inputStream = multiParfile.getInputStream();
			new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
			                    .lines()
			                    .forEach(t ->  {
			                    	Cnab cnab = CnabServiceFactory.cnabService().createCnab(t);
			                    	cnabList.add(cnab);
			                    });
			
			CnabServiceFactory.cnabService().saveManyCnab(cnabList, cnabRepository);
	    } catch(IOException ex)	{
	        ex.printStackTrace();
	    }
		return String.format("file is here!!!", multiParfile.getOriginalFilename());
	}
	
	@GetMapping("/find")
	public List<Cnab> findAllCnab() {
	    return (List<Cnab>) cnabRepository.findAll();
	}
	
	@GetMapping("/findByCpf")
	public List<Cnab> findCnabByCpf(@RequestHeader(value = "cpf") String cpf) {
	    return (List<Cnab>) cnabRepository.findBycpf(cpf);
	}
}