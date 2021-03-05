package com.example.demo.core.services.middlewares;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.example.demo.buslib.spec.CommandQueue;
import com.example.demo.core.ports.commands.ReceiveCommand;

import net.dathoang.cqrs.commandbus.command.Command;
import net.dathoang.cqrs.commandbus.message.Message;
import net.dathoang.cqrs.commandbus.middleware.Middleware;
import net.dathoang.cqrs.commandbus.middleware.NextMiddlewareFunction;

/**
 * Reference: https://github.com/phpgears/cqrs-async
 *
 */
public class AsyncBusMiddleware implements Middleware{

	private final CommandQueue commandQueue;

	private static final Log log = LogFactory.getLog(AsyncBusMiddleware.class);
	
	public AsyncBusMiddleware(CommandQueue commandQueue) {
		super();
		this.commandQueue = commandQueue;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <R> R handle(Message<R> message, NextMiddlewareFunction<Message<R>, R> next) throws Exception {
		log.info(String.format("Middleware async commandBus com: %s (%s)", 
				message.getClass().getName(), message.toString()));

		try {
			if(message instanceof Command<?>) {
				if(!(message instanceof ReceiveCommand)) {
					log.info("Enviando comando para a fila assíncrona");
					commandQueue.send((Command<?>)message);
					return null;
				}else {
					log.info("Recebendo comando da fila assíncrona");
					ReceiveCommand receiveCommand = (ReceiveCommand) message;
					Command<?> command = receiveCommand.originalCommand();
					message = (Message<R>) command;
				}
			}
			R result = next.call(message);

			return result;
        } catch(Exception ex) {
        	log.error(String.format("Failed to handle %s (%s)", message.getClass().getName(), message.toString()), ex);
        	throw ex;
        }
	}
}
