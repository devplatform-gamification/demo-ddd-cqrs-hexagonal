package com.example.demo.infrastructure.adapters.cli;

import com.example.demo.core.ports.commands.AlterarSegmentoJusticaTribunalCommand;
import com.example.demo.core.ports.commands.CriarTribunalCommand;

import net.dathoang.cqrs.commandbus.command.CommandBus;

public class TesteController {

	private CommandBus bus;
	
	public TesteController(CommandBus bus) {
		super();
		this.bus = bus;
	}

	public void criarTribunal() throws Exception {
		CriarTribunalCommand command = new CriarTribunalCommand(
				"Tribunal Regional Federal da 8a região", "TRF8", "Justiça Federal");
		
		bus.dispatch(command);
	}

	public void alterarSegmentoJusticaTribunal() throws Exception {
		AlterarSegmentoJusticaTribunalCommand command = new AlterarSegmentoJusticaTribunalCommand(
				"TRF8", "Justiça Federativa");
		
		bus.dispatch(command);
	}
}
