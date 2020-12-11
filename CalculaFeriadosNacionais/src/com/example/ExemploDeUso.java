package com.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.example.CalculaFeriado.Feriado;

public class ExemploDeUso {

	public static void main(String[] args) throws Exception {
		
		// Mostra na tela todos os feriados de 2020
		System.out.format("%-30s %-10s \n", "Descrição", "Data");
		System.out.println("--------------------------------------------");
		for (Feriado feriado : CalculaFeriado.getFeriados(2021)) {
			System.out.format("%-30s %-10s \n", feriado.getNomeDoFeriado(),
			feriado.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		}

		// Verificando se a data é feriado
		System.out.println("1/1/2020 é feriado? " + CalculaFeriado.isFeriado(LocalDate.of(2020, 1, 1))); // TRUE
		System.out.println("2/1/2020 é feriado? " + CalculaFeriado.isFeriado(LocalDate.of(2020, 1, 2))); // FALSE
	}
}
