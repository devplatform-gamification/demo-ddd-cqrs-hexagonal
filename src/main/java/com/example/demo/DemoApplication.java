package com.example.demo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.demo.buslib.spec.Queue;
import com.example.demo.infrastructure.adapters.cli.TesteController;

import net.dathoang.cqrs.commandbus.spring.CommandBusSpringConfiguration;


@SpringBootApplication
public class DemoApplication implements CommandLineRunner{
	
	@Autowired
    private ApplicationContext applicationContext;
	
	@Autowired
	private Queue queue;
	
	private static final Log log = LogFactory.getLog(DemoApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);		
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Criando um novo commandbus: ");
		
		CommandBusSpringConfiguration commandBusConfiguration = 
				new CommandBusSpringConfiguration(applicationContext);
		
		try {
			TesteController testeController = new TesteController(commandBusConfiguration.getCommandBus());
			testeController.criarTribunal();
			testeController.alterarSegmentoJusticaTribunal();
			
			while(queue != null) {
				String message = queue.dequeue();
				if(message != null) {
					// serializar a mensagem e criar o comando ReceiveCommand a partir daí
					log.info(String.format("Mensagem retirada da fila: %s", message));
				}else {
					break;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
