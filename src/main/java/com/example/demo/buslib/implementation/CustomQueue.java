package com.example.demo.buslib.implementation;

import java.util.LinkedList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.example.demo.buslib.spec.Queue;

/**
 * Utilizar como referência este projeto: https://github.com/php-enqueue/enqueue
 * @author zeniel
 *
 */
@Component
public class CustomQueue implements Queue{

	private static final Log log = LogFactory.getLog(CustomQueue.class);
	
	private java.util.Queue<String> queue;
	
	public CustomQueue() {
		super();
		this.queue = new LinkedList<String>();
	}

	@Override
	public void enqueue(String message) {
		log.info(String.format("Enfileirando mensagem .... (%s) ", message));
		this.queue.add(message);
	}

	@Override
	public String dequeue() {
		log.info("Verificando mensagem da fila....");

		String message = this.queue.peek();
		if(message != null) {
			log.info("Fila tem mensagem");
			this.queue.remove();
		}else {
			log.info("Fila vazia");
		}
		return message;
	}
}
