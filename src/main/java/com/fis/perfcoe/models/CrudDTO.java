package com.fis.perfcoe.models;
import java.util.List;

import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown=true)
public class CrudDTO {
	public CrudDTO()
	{
		
	}
	@JsonProperty("Name")
	private String name;
	@JsonProperty("Surname")
	private String surname;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
}
