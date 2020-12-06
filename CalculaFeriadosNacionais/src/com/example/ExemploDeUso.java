package com.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.example.FeriadosNacionais.Feriado;

public class ExemploDeUso {

	public static void main(String[] args) throws Exception {
		System.out.format("%-30s %-10s \n", "Descrição", "Data");
		System.out.println("--------------------------------------------");
		for (Feriado feriado : FeriadosNacionais.getTodos(2021)) {
			System.out.format("%-30s %-10s \n", feriado.getNomeDoFeriado(),
			feriado.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		}

		System.out.println("1/1/2020 é feriado? " + FeriadosNacionais.isFeriado(LocalDate.of(2020, 1, 1))); // TRUE
		System.out.println("2/1/2020 é feriado? " + FeriadosNacionais.isFeriado(LocalDate.of(2020, 1, 2))); // FALSE
		
	}
}
