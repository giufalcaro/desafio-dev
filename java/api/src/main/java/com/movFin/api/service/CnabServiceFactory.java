package com.movFin.api.service;

public class CnabServiceFactory {
	
	public static ICnabService cnabService() {
		return new CnabService();
	}
}