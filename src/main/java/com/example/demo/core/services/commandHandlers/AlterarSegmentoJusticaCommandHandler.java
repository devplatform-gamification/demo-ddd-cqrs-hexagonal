package com.example.demo.core.services.commandHandlers;

import com.example.demo.core.ports.commands.AlterarSegmentoJusticaTribunalCommand;

import net.dathoang.cqrs.commandbus.autoscan.CommandMapping;
import net.dathoang.cqrs.commandbus.command.CommandHandler;

@CommandMapping(value = AlterarSegmentoJusticaTribunalCommand.class)
public class AlterarSegmentoJusticaCommandHandler implements CommandHandler<AlterarSegmentoJusticaTribunalCommand, Void>{

	@Override
	public Void handle(AlterarSegmentoJusticaTribunalCommand command) throws Exception {
		System.out.println("\n\n");
		System.out.println(" >>>>>>> Buscando o tribunal: " + command.sigla());
		System.out.println(" >>>>>>>>>>>>>>>>> Alterando o segmento de justica desse tribunal para: " + command.segmentoJustica());
		System.out.println("\n\n");

		return null;
	}

}
