package com.couch.demo.CouchDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;
import com.couchbase.client.java.query.N1qlQueryRow;

@RestController
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class N1qlTest {
	

	@RequestMapping("/employee/{id}")
	@GET
	public  List<Map<String, Object>> getEmployeeDesignationBasedOnId(@PathVariable("id") String  id){
		
	   //System.setProperty("com.couch.demo.CouchDemo", "true");
	   CouchbaseCluster  cluster = CouchbaseCluster.create("localhost");
	   Bucket bucket = cluster.openBucket("Test");
	   System.out.println(id);
	   List<Map<String, Object>> content = new ArrayList<Map<String, Object>>();
	   N1qlQuery airlineQuery = N1qlQuery.simple("SELECT empDesignation from Test  where empId ="+"'"+id+"'");
	   N1qlQueryResult queryResult = bucket.query(airlineQuery);
	   System.out.println(queryResult.info());
	   for (N1qlQueryRow row: queryResult) {
		         content.add(row.value().toMap());	            
		  }           
	   return content;
	}
		
}
