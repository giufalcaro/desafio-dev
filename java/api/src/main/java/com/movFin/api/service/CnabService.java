package com.movFin.api.service;

import java.util.List;

import com.movFin.api.model.Cnab;
import com.movFin.api.repository.CnabRepository;

public class CnabService implements ICnabService {
	
	public Cnab createCnab(String line) {
	  	String tipo = discoverType(line.substring(0, 1));
	  	String data = line.substring(1, 9);
	  	boolean isEntry = checkEntry(line.substring(0, 1));
	  	String valor = convertValue(line.substring(9, 19), isEntry).toString(); 
	  	String cpf = line.substring(19, 30);
	  	String cartao = line.substring(30, 42);
	  	String hora = line.substring(42, 48);
	  	String nomeRepresentante = line.substring(48, 62);
	  	String nomeLoja = line.substring(62, 80);
	  	
	  	Cnab cnab = Cnab.criar();
	  	cnab.setTipo(tipo);
	  	cnab.setData(data);
	  	cnab.setValor(Double.valueOf(valor));
	  	cnab.setCpf(cpf);
	  	cnab.setCartao(cartao);
	  	cnab.setHora(hora);
	  	cnab.setNomeRepresentante(nomeRepresentante);
	  	cnab.setNomeLoja(nomeLoja);
	  	
	  	return cnab;
	}

	public void saveManyCnab(List<Cnab> cnabList, CnabRepository repository) {
		repository.saveAll(cnabList);
	}
	
	public Double convertValue(String valor, boolean isEntry) {
		Double d = Double.parseDouble(valor);
		if (isEntry) {
			return d/ 100.00;
		} else {
			return (d/ 100.00) * -1;
		}
	}
	
	public String discoverType (String type) {
		switch (type) {
			case "1":
				return "Debito";
			case "2":
				return "Boleto";
			case "3":
				return "Financiamento";
			case "4":
				return "Credito";
			case "5":
				return "Recebimento Emprestimo";
			case "6":
				return "Vendas";
			case "7":
				return "Recebimento TED";
			case "8":
				return "Recebimento DOC";
			case "9":
				return "Aluguel";
			default: 
				return "Nao encontrado";
		}
	}
	
	public boolean checkEntry (String type) {
		switch (type) {
			case "1":
				return true;
			case "2":
				return false;
			case "3":
				return false;
			case "4":
				return true;
			case "5":
				return true;
			case "6":
				return true;
			case "7":
				return true;
			case "8":
				return true;
			case "9":
				return false;
			default: 
				return false;
		}
	}
}