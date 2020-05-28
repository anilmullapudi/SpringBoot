package com.mak.kubernetesExample.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class KubernetiesTestController {
	
	@GetMapping("/hello")
	public String hello() {
		return "Welcome to Kubernetes";
	}
}
