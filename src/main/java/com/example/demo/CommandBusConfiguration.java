package com.example.demo;

import org.springframework.stereotype.Component;

import net.dathoang.cqrs.commandbus.autoscan.HandlerScan;

@Component
@HandlerScan(basePackages = {"com.example.demo.core.services.commandHandlers"})
public class CommandBusConfiguration {

}
