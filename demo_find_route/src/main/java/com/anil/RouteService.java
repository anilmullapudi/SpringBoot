package com.anil;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RouteService {
	
	static Logger logger = Logger.getLogger(DFS_Service_Impl.class.getName());
	
	@Autowired
	DFS_Service findrouteService;
	
	@RequestMapping("/connected")
	public String findRoute(@RequestParam(name = "origin") String origin, @RequestParam(name = "destination") String destination) {
		logger.info("origin:" + origin);
		logger.info("destination: " + destination);
		boolean result = findrouteService.isRouteExists(origin, destination); 
		return (result)?"Yes":"No";
	}
}
