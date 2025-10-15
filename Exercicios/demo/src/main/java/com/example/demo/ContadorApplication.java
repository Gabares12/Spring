package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class ContadorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContadorApplication.class, args);
	}

	public void run (String...args)throws Exception{
		Scanner contador = new Scanner(System.in);

		System.out.println("digite um numero para o contador: ");
		int numero = contador.nextInt();

		for (int i = 0; i <=numero ; i++) {
			System.out.println(i + " ");

		}

	}

}
