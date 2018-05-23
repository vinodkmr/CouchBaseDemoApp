package com.couch.demo.CouchDemo;

import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotBlank;

@XmlRootElement
public class Employee {
		
		
	@NotBlank
	private String empId;
	@NotBlank
	private String empName;
	@NotBlank
	private String empDesignation;
	
	public Employee() {
		// TODO Auto-generated constructor stub
	}
	
	public Employee(String empId, String empName,
			String empDesignation) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empDesignation = empDesignation;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpDesignation() {
		return empDesignation;
	}
	public void setEmpDesignation(String empDesignation) {
		this.empDesignation = empDesignation;
	}
	
}
