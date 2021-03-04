package com.example.demo.core.services.middlewares;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.dathoang.cqrs.commandbus.message.Message;
import net.dathoang.cqrs.commandbus.middleware.Middleware;
import net.dathoang.cqrs.commandbus.middleware.NextMiddlewareFunction;

public class TesteMiddleware implements Middleware{

	  private static final Log log = LogFactory.getLog(TesteMiddleware.class);

	@Override
	public <R> R handle(Message<R> message, NextMiddlewareFunction<Message<R>, R> next) throws Exception {
		log.info(String.format("Entrou no middleware com: %s (%s)", 
				message.getClass().getName(), message.toString()));

		try {
          R result = next.call(message);

          String serializedResult = result != null ? result.toString() : null;
          
          log.info(String.format("Tratou corretamente o %s (%s) com o resultado: %s",
              message.getClass().getName(), message.toString(), serializedResult));

          return result;
        } catch(Exception ex) {
          log.error(String.format("Failed to handle %s (%s)", message.getClass().getName(), message.toString()), ex);
          throw ex;
        }
	}
}
