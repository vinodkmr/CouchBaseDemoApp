package com.couch.demo.CouchDemo;

import java.util.Arrays;
import java.util.List;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.EntityDocument;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.repository.Repository;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.stream.events.EntityDeclaration;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("web")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Controller {
	static CouchbaseCluster couchbasecluster = null;
	static Bucket bucket = null;
	
	

	static{
		 couchbasecluster = CouchbaseCluster.create("localhost");
		 bucket = couchbasecluster.openBucket("Test");
	}
	
	static  Repository repository = bucket.repository();
@RequestMapping("test")	
@GET
public String getString(){
	return "Working";
}

@RequestMapping("/employee")
@GET
public Employee getEmployee(){
	return new Employee("456", "Test", "Not Yet Decided");
}

@RequestMapping("/saveemployee")
@POST
public @Valid Employee saveEmployee(@Valid @RequestBody Employee employee){
	
	
	EntityDocument<@Valid Employee> insert = repository.insert(EntityDocument.create(employee.getEmpId(),employee));
	return insert.content();
}

@GET
@RequestMapping("/getEmployee/{id}")
public Employee getEmployee(@PathVariable("id") String id){
	System.out.println("ID "+id);
	return repository.get(id,Employee.class).content();
	

}


@POST
@RequestMapping("/updateemployee")
public Employee updateEmployee(@Valid @RequestBody Employee employee){
	return repository.upsert(EntityDocument.create(employee.getEmpId(),employee)).content();
}

@POST
@RequestMapping("/deleteemployee/{id}")
public String deleteEmployee(@PathVariable("id") String id,@Valid @RequestBody Employee employee){
	//return bucket.remove(id).content();
	return bucket.remove(id).id();
	//return repository.remove(EntityDocument.create(id, employee)).id();
}
}
