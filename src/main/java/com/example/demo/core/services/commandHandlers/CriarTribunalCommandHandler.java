package com.example.demo.core.services.commandHandlers;

import com.example.demo.core.ports.commands.CriarTribunalCommand;

import net.dathoang.cqrs.commandbus.autoscan.CommandMapping;
import net.dathoang.cqrs.commandbus.command.CommandHandler;

@CommandMapping(value = CriarTribunalCommand.class)
public class CriarTribunalCommandHandler implements CommandHandler<CriarTribunalCommand, Void>{

	@Override
	public Void handle(CriarTribunalCommand command) throws Exception {
		System.out.println("\n\n");
		System.out.println(" >>>>>>>>>>  >>>>>> Criando o tribunal: " + command.nomeTribunal()
				+ " - sigla: " + command.sigla() + " - segmento de justiça: " + command.segmentoJustica());
		System.out.println("\n\n");

		return null;
	}

}
