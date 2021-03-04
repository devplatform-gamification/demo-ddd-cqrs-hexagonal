package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.demo.infrastructure.adapters.cli.TesteController;

import net.dathoang.cqrs.commandbus.spring.CommandBusSpringConfiguration;


@SpringBootApplication
public class DemoApplication implements CommandLineRunner{
	
	@Autowired
    private ApplicationContext applicationContext;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);		
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Criando um novo commandbus: ");
		
		CommandBusSpringConfiguration commandBusConfiguration = 
				new CommandBusSpringConfiguration(applicationContext);
		
		try {
			new TesteController(commandBusConfiguration.getCommandBus()).criarTribunal();
			new TesteController(commandBusConfiguration.getCommandBus()).alterarSegmentoJusticaTribunal();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
