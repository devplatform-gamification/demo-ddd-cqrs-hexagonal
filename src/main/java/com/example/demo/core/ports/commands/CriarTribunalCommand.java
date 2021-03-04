package com.example.demo.core.ports.commands;

import net.dathoang.cqrs.commandbus.command.Command;

public class CriarTribunalCommand implements Command<Void>{
	private String nomeTribunal;
	private String siglaTribunal;
	private String segmentoJustica;
	
	public CriarTribunalCommand(String nomeTribunal, String siglaTribunal, String segmentoJustica) {
		super();
		this.nomeTribunal = nomeTribunal;
		this.siglaTribunal = siglaTribunal;
		this.segmentoJustica = segmentoJustica;
	}

	public String nomeTribunal() {
		return nomeTribunal;
	}

	public String sigla() {
		return siglaTribunal;
	}

	public String segmentoJustica() {
		return segmentoJustica;
	}
}
