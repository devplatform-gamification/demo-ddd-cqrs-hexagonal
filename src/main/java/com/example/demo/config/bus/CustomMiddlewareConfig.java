package com.example.demo.config.bus;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.example.demo.buslib.spec.CommandQueue;
import com.example.demo.core.services.middlewares.AsyncBusMiddleware;
import com.example.demo.core.services.middlewares.TesteMiddleware;

import net.dathoang.cqrs.commandbus.middleware.Middleware;
import net.dathoang.cqrs.commandbus.middleware.logging.LoggingMiddleware;
import net.dathoang.cqrs.commandbus.spring.MiddlewareConfig;

@Configuration
public class CustomMiddlewareConfig implements MiddlewareConfig {

	@Autowired
	private CommandQueue commandQueue;

	@Override
	public List<Middleware> getCommandMiddlewarePipeline() {
		return Collections.unmodifiableList(
				Arrays.asList(
							new AsyncBusMiddleware(this.commandQueue),
							new LoggingMiddleware(), 
							new TesteMiddleware()));
	}

	@Override
	public List<Middleware> getQueryMiddlewarePipeline() {
		return Collections.unmodifiableList(
				Arrays.asList(
							new AsyncBusMiddleware(this.commandQueue),
							new LoggingMiddleware(), 
							new TesteMiddleware()));
	}
}
