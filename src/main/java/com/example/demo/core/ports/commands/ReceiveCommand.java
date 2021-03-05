package com.example.demo.core.ports.commands;

import net.dathoang.cqrs.commandbus.command.Command;

public class ReceiveCommand implements Command<Void>{
	private Command<?> originalCommand;
	
	public ReceiveCommand(Command<?> originalCommand) {
		super();
		this.originalCommand = originalCommand;
	}

	public Command<?> originalCommand() {
		return originalCommand;
	}
}
