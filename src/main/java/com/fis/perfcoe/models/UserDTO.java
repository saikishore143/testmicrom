package com.fis.perfcoe.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserDTO {
	    @Id
	    private String name;
	    public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		private String message   ;

	

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	


	}

