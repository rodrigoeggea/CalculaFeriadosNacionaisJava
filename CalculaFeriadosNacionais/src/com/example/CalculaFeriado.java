package com.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe utilitária que calcula e retorna todos os Feriados Nacionais no Brasil.      <br>
 * Utiliza o método "Anonymous Gregorian Algorithm" para determinar o dia de Páscoa.   <br>
 * Os demais feriados móveis são calculados a partir do dia de Páscoa como referência. <br>
 * Obs.: O Carnaval (segunda e terça-feira) são considerados como feriados nacionais pelo programa <br>
 * mas em alguns estados brasileiros o carnaval não é considerado feriado. <br>  
 * Para remover um feriado da lista basta comentá-lo na lista. <br>
 * 
 * @author Rodrigo Eggea
 * @version 1.1
 *
 */
public class CalculaFeriado {

	/**
	 * Classe que representa um dia de feriado com Descrição do Feriado e Data.
	 */
	static class Feriado {
		final String nomeDoFeriado;
		final LocalDate dataDoFeriado;

		public Feriado(String nomeDoFeriado, LocalDate dataDoFeriado) {
			super();
			this.nomeDoFeriado = nomeDoFeriado;
			this.dataDoFeriado = dataDoFeriado;
		}

		public String getNomeDoFeriado() {
			return nomeDoFeriado;
		}

		public LocalDate getData() {
			return dataDoFeriado;
		}

		@Override
		public String toString() {
			return "Feriado [nomeDoFeriado=" + nomeDoFeriado + ", data=" + dataDoFeriado + "]";
		}
	}

	/**
	 * Cálculo do dia de Páscoa utilizando o método "Anonymous Gregorian algorithm"
	 */
	private static LocalDate getDiaDaPascoa(int ano) {
		double a = ano % 19;
		double b = Math.floor(ano / 100);
		double c = ano % 100;
		double d = Math.floor(b / 4);
		double e = b % 4;
		double f = Math.floor((b + 8) / 25);
		double g = Math.floor((b - f + 1) / 3);
		double h = (19 * a + b - d - g + 15) % 30;
		double i = Math.floor(c / 4);
		double k = c % 4;
		double l = (32 + 2 * e + 2 * i - h - k) % 7;
		double m = Math.floor((a + 11 * h + 22 * l) / 451);
		int mes = (int) Math.floor((h + l - 7 * m + 114) / 31);
		int dia = (int) (((h + l - 7 * m + 114) % 31) + 1);
		LocalDate diaDePascoa = LocalDate.of(ano, mes, dia);
		return diaDePascoa;
	}

	/**
	 * Retorna uma lista com todos os Feriados Nacionais do Brasil.
	 * 
	 * @author Rodrigo Eggea
	 * @param ano - Fornecer o ano.
	 * @return Lista com todos os feriados no ano.
	 */
	public static List<Feriado> getFeriados(int ano) throws Exception {
		LocalDate pascoa          = getDiaDaPascoa(ano);
		LocalDate sextaFeiraSanta = pascoa.minusDays(2);
		LocalDate carnaval         = pascoa.minusDays(47 + 1); // +1 de ajuste do minusDays
		LocalDate corpusChristi   = pascoa.plusDays(60);
		
		// Cria uma lista com todos os Feriados Nacionais
		List<Feriado> feriadosNacionais = new ArrayList<>();
		feriadosNacionais.add(new Feriado("Ano Novo"               , LocalDate.of(ano, 1, 1)      )); // fixo
		feriadosNacionais.add(new Feriado("Carnaval"               , carnaval                      )); // movel
		feriadosNacionais.add(new Feriado("Carnaval"               , carnaval.plusDays(1)          )); // movel
		feriadosNacionais.add(new Feriado("Sexta-Feira Santa"      , sextaFeiraSanta              )); // movel
		feriadosNacionais.add(new Feriado("Tiradentes"             , LocalDate.of(ano, 4, 21)     )); // fixo
		feriadosNacionais.add(new Feriado("Dia do Trabalho"        , LocalDate.of(ano, 5, 1)      )); // fixo
		feriadosNacionais.add(new Feriado("Corpus Christi"         , corpusChristi                )); // movel
		feriadosNacionais.add(new Feriado("Independência do Brasil", LocalDate.of(ano, 9, 7)      )); // fixo
		feriadosNacionais.add(new Feriado("Dia de Nossa Sra Aparecida", LocalDate.of(ano, 10, 12) )); // fixo
		feriadosNacionais.add(new Feriado("Finados"                   , LocalDate.of(ano, 11, 2)  )); // fixo
		feriadosNacionais.add(new Feriado("Proclamação da República"  , LocalDate.of(ano, 11, 15) )); // fixo
		feriadosNacionais.add(new Feriado("Natal"                     , LocalDate.of(ano, 12, 25) )); // fixo
		return feriadosNacionais;
	}

	/**
	 * Verifica se uma determinada data é feriado.
	 * 
	 * @param data - Data a ser verificada de é feriado
	 * @return <b>true</b> se a data é um feriado nacional.
	 */
	public static boolean isFeriado(LocalDate data) throws Exception {
		int ano = data.getYear();
		for (Feriado feriado : getFeriados(ano)) {
			if (feriado.getData().equals(data)) {
				return true;
			}
		}
		return false;
	}
}
