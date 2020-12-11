package com.example;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

/**
 * Classe Unit Test que verifica se a CalculadoraDeFeriados está gerando todos os feriados  
 * exatamente igual a tabela de feriados.
 * 
 * @author Rodrigo Eggea
 * @version 1.1
 *
 */
class CalculadoraDeFeriadosTest {

	@Test
	void testaCalculadoraDeFeriados() throws Exception {
		
		for(int ano=2020;ano<=2050;ano++) {
			System.out.println("Feriados de " + ano + ":");
			for(LocalDate feriado: TabelaDeFeriados.getFeriados(ano)) {
				System.out.println("Data do feriado: " + feriado + " isFeriado => " + CalculaFeriado.isFeriado(feriado));
				assertTrue(CalculaFeriado.isFeriado(feriado));
			}
		}
		
	}
}
