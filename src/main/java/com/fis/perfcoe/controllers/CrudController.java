package com.fis.perfcoe.controllers;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Random;

import com.fis.perfcoe.models.CrudDTO;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class CrudController {

	private static final String template = "Hello, %s!";

	@PostMapping("/greeting")
	public CrudDTO greeting(@Valid @RequestBody String name) {
		// System.out.println("==== in greeting ====");
		CrudDTO crudDTO = new CrudDTO();
		crudDTO.setName(name);
		crudDTO.setSurname("xyz");
		return crudDTO;
	}

	@GetMapping("/greeting")
	public CrudDTO greeting1() {
		// System.out.println("==== in greeting ====");
		CrudDTO crudDTO = new CrudDTO();
		crudDTO.setName("test");
		crudDTO.setSurname("xyz");
		return crudDTO;

	}

	// 10 or more user load on the application to observe sync issue
	@GetMapping("/syncIssue")
	public CrudDTO syncIssue() {
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
	@PostMapping("/highcpu")
	public CrudDTO highCpuUsage(@Valid @RequestBody int value) {
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

	// issue is observerd when value is passed as 1
	@PostMapping("/highmemory")
	public CrudDTO highMemoryUsage(@Valid @RequestBody int value) {
		int i = 0;
		CrudDTO crudDTO = new CrudDTO();
		crudDTO.setName("abc");
		crudDTO.setSurname("xyz");
		Random random = new Random();
		HashMap sampleMap = new HashMap();
		if (value == 1) {
			for (i = 1; i < 10000; i++) {
				int randomValue = random.nextInt(10000000);
				sampleMap.put(Integer.valueOf(randomValue), String.valueOf(randomValue));
				sampleMap.put(String.valueOf(randomValue), Integer.valueOf(randomValue));
				try {
					Thread.sleep(random.nextInt(10));
				} catch (InterruptedException ex) {
					return crudDTO;
				}
			}

		}
		return crudDTO;
	}

	// issue is observerd when value is passed as 1
	@PostMapping("/waitissue")
	public CrudDTO waitIssue(@Valid @RequestBody int value) {
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
