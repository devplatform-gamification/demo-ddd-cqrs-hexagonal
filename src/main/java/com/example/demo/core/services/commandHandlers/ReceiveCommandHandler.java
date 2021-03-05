package com.example.demo.core.services.commandHandlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.example.demo.core.ports.commands.ReceiveCommand;

import net.dathoang.cqrs.commandbus.autoscan.CommandMapping;
import net.dathoang.cqrs.commandbus.command.Command;
import net.dathoang.cqrs.commandbus.command.CommandHandler;
import net.dathoang.cqrs.commandbus.spring.CommandBusSpringConfiguration;

@CommandMapping(value = ReceiveCommand.class)
public class ReceiveCommandHandler implements CommandHandler<ReceiveCommand, Void>{

	@Autowired
    private ApplicationContext applicationContext;
	
	@Override
	public Void handle(ReceiveCommand command) throws Exception {
		System.out.println("\n\n");
		System.out.println(" >>>>>>>>>>  >>>>>> Mensagem recebida da fila - recuperando originalCommand: ");
		System.out.println("\n\n");
		
		Command<?> originalCommand = command.originalCommand();
		
		CommandBusSpringConfiguration commandBusConfiguration = 
				new CommandBusSpringConfiguration(applicationContext);

		commandBusConfiguration.getCommandBus().dispatch(originalCommand);

		return null;
	}

}
