package com.example.demo.buslib.implementation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.example.demo.buslib.spec.CommandQueue;
import com.example.demo.buslib.spec.Queue;
import com.example.demo.core.ports.commands.ReceiveCommand;

import net.dathoang.cqrs.commandbus.command.Command;

/***
 * Utilizar como referência este projeto: https://github.com/phpgears/cqrs-async-queue-interop/blob/master/src/QueueInteropCommandQueue.php
 * @author zeniel
 *
 */
@Component
public class CustomCommandQueue implements CommandQueue{

	private Queue queue;
	
	private static final Log log = LogFactory.getLog(CustomCommandQueue.class);
	
	public CustomCommandQueue(Queue queue) {
		super();
		this.queue = queue;
	}

	@Override
	public void send(Command<?> command) {
		log.info("Enviando comando para a fila ....");
		ReceiveCommand queueCommand = new ReceiveCommand(command);
		
		queue.enqueue("Commando serializado no formato JSON para a fila " + String.valueOf(Math.random() * 10));
	}
}
