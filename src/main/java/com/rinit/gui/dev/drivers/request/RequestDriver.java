package com.rinit.gui.dev.drivers.request;

import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.debugger.server.file.IFileDriver;

public class RequestDriver implements IFileDriver {

	public static final String EXTENTION = "request";
	
	private FileDTO dto;
	
	private String name;
	private String protocol;
	private String adress;
	private String path;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	@Override
	public void fromDTO(FileDTO dto) {
		this.dto = dto;
		if (dto.getContent().isEmpty())
			return;
		RequestImporter importer = new RequestImporter(this, dto);
		importer.parse();
	}

	@Override
	public FileDTO toDTO() {
		if (this.dto != null) {
			this.dto.cwrite(this.getContent());
			return this.dto;
		} else {
			return FileDTO.builder().name(this.name).content(this.getContent()).extention(EXTENTION).build();
		}
	}

	@Override
	public String getContent() {
		RequestExporter exporter = new RequestExporter(this);
		return exporter.toString();
	}

}
