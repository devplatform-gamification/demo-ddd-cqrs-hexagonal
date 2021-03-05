package com.example.demo.buslib.spec;

import net.dathoang.cqrs.commandbus.command.Command;

public interface CommandQueue {
	public void send(Command<?> command);
}
