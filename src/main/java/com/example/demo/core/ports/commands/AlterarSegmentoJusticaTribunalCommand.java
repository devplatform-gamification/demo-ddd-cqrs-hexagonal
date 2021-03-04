package com.example.demo.core.ports.commands;

import net.dathoang.cqrs.commandbus.command.Command;

public class AlterarSegmentoJusticaTribunalCommand implements Command<Void>{
	private String siglaTribunal;
	private String segmentoJustica;
	
	public AlterarSegmentoJusticaTribunalCommand(String siglaTribunal, String segmentoJustica) {
		super();
		this.siglaTribunal = siglaTribunal;
		this.segmentoJustica = segmentoJustica;
	}

	public String sigla() {
		return siglaTribunal;
	}

	public String segmentoJustica() {
		return segmentoJustica;
	}
}
