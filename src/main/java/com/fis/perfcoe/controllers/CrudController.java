package com.fis.perfcoe.controllers;

import javax.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import com.fis.perfcoe.models.CrudDTO;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class CrudController {

	private static final String template = "Hello, %s!";

	@PostMapping("/greeting12")
	public CrudDTO greeting(@Valid @RequestParam String name) {
		// System.out.println("==== in greeting ====");
		CrudDTO crudDTO = new CrudDTO();
		crudDTO.setName(name);
		crudDTO.setSurname("xyz1");
		return crudDTO;
	}

	@GetMapping("/greeting")
	public String greetings(@Valid @RequestParam String name) {
		// System.out.println("==== in greeting ====");
		CrudDTO crudDTO = new CrudDTO();
		crudDTO.setName("test");
		crudDTO.setSurname("xyz");
		String x="saiabc";
		return x;

	}

	// 10 or more user load on the application to observe sync issue
	@GetMapping("/syncIssue")
	public CrudDTO syncIssue(@Valid @RequestParam int value) {
		// System.out.println("==== in greeting ====");
		CrudDTO crudDTO = new CrudDTO();
		crudDTO.setName("test");
		crudDTO.setSurname("xyz");
		try {
			long st = System.currentTimeMillis();
			synchronized (this) {
				while (true) {
					if (System.currentTimeMillis() - st > 5000) {
						System.out.println("==== in greeting ====");
						return crudDTO;

					} else {
						System.out.println("In syncBlock");

					}
				}
			}
		} catch (Exception ex) {
			return crudDTO;
		}

	}

	// issue is observerd when value is passed more than '0' and user greater
	// than 10
	@GetMapping("/highcpu")
	public CrudDTO highCpuUsage(@Valid @RequestParam int value) {
		int i = 0;
		CrudDTO crudDTO = new CrudDTO();
		crudDTO.setName("abc");
		crudDTO.setSurname("xyz");
		while (value > 0 && value > i) {
			try {
				InetAddress addr = java.net.InetAddress.getLocalHost();
				// System.out.println(addr);
				String hostname = addr.getHostName();
				// System.out.println("Hostname of system = " + hostname);
			} catch (UnknownHostException e) {
			}
			i++;
		}

		return crudDTO;
	}
	ArrayList sampleList = new ArrayList<>();
	// issue is observerd when value is greater than 1
	@GetMapping("/highmemory")
	public CrudDTO highMemoryUsage(@Valid @RequestParam int value) {
		int i = 0;
		CrudDTO crudDTO = new CrudDTO();
		crudDTO.setName("abc");
		crudDTO.setSurname("xyz");
		Random random = new Random();

		if (value >1) {
			for (i = 1; i < value; i++) {
				int randomValue = random.nextInt(10000000);
				sampleList.add(String.valueOf(System.currentTimeMillis())+randomValue);
				//sampleMap.put(String.valueOf(randomValue), Integer.valueOf(randomValue));
			}

		} else {
			sampleList.clear();
		}
		return crudDTO;
	}

	@GetMapping("/getlargeobject")
	public ArrayList getLargeObject() {
				return sampleList;
	}
	// issue is observerd when value is passed as 1
	@GetMapping("/waitissue")
	public CrudDTO waitIssue(@Valid @RequestParam int value) {
		CrudDTO crudDTO = new CrudDTO();
		crudDTO.setName("abc");
		crudDTO.setSurname("xyz");
		if (value == 1) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
			}
		}
		return crudDTO;

	}
}
