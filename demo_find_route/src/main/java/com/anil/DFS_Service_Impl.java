package com.anil;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * 
 * @author anilm
 * Below service will find route between two cities.
 * Finding route is a graph problem. The route in the graph can be find using depth first search.
 * Assuming all the cities are vertices in the graph and edges are the roads.
 */

@Component
public class DFS_Service_Impl implements DFS_Service {
	static Logger logger = Logger.getLogger(DFS_Service_Impl.class.getName());
	
	@Override
	public boolean isRouteExists(String source, String destination) {
		
		boolean result= false;
		try {
			//Reading text input file with routes
			logger.info("File reading started:"); 
			String fileName="src/main/resources/routes.txt";
			
			//verticesSet contain unique city list
			Set<String> verticesSet = new HashSet<String>();
			
			//edgeList contain all the valid city pairs
			ArrayList<String[]> edgeList = new ArrayList<String[]>();
			
			//File reading logic implemented in below method
			readInput(fileName, verticesSet, edgeList);
			
			logger.info("File reading completed:");

			Graph graph = new Graph();
			for(String city: verticesSet) {
				graph.addVertex(city);
			}
			
			for (String[] array : edgeList){
			    graph.addEdge(array[0], array[1]);
			}
			
			logger.info("Graph construction is successful");
			
			//Clean the source and destination names
			source = source.trim().replaceAll(" ", "_").toLowerCase();
			destination = destination.trim().replaceAll(" ", "_").toLowerCase();
			
			
			//Check if source is available in input list of cities
			if(!verticesSet.contains(source)) {
				return false;
			}
			
			//Check if destination is available in input list of cities
			if(!verticesSet.contains(destination)) {
				return false;
			}
			
			//DFS algorithm to find the route
			result = graph.depthFirstTraversal(graph, source, destination);
		} catch (FileNotFoundException e) {
			logger.error("Exceptio whiel reading file", e);
			result = false;
		} catch (Exception e) {
			logger.error("Error whiel computing route ", e);
			result=false;
		}
		
		return result;
	}
	
	private static void readInput(String fileName, Set<String> verticesSet, ArrayList<String[]> edgeList) throws IOException, FileNotFoundException {
		try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
		    for(String line; (line = br.readLine()) != null; ) {
		        String[] strArray = line.split(",");
		        if(strArray.length == 2) {
		        	String city1 = strArray[0].trim().replaceAll(" ", "_").toLowerCase();
		        	String city2 = strArray[1].trim().replaceAll(" ", "_").toLowerCase();
		        	if(city1.length()>1 && city2.length()>1 && !city1.equals(city2)) {
		        		verticesSet.add(city1);
		        		verticesSet.add(city2);
		        		edgeList.add(new String[] { city1, city2 });
		        	}
		        }
		    }
		}
	}
	
	private String readFromInputStream(InputStream inputStream)
			  throws IOException {
			    StringBuilder resultStringBuilder = new StringBuilder();
			    try (BufferedReader br
			      = new BufferedReader(new InputStreamReader(inputStream))) {
			        String line;
			        while ((line = br.readLine()) != null) {
			            resultStringBuilder.append(line).append("\n");
			        }
			    }
			  return resultStringBuilder.toString();
			}

}
