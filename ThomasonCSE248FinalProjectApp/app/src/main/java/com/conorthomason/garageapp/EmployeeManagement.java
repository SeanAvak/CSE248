package com.conorthomason.garageapp;

import android.app.Activity;
import android.app.Application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

/**
 * The EmployeeManagement class is the main data structure for storing the information of employees.
 * This will be instantiated in the Garage class.
 * 
 * @see <A href="../src/model/EmployeeManagement.java">Java source code</A>
 * 
 * @author Conor Thomason <A href="mailto:thomc16@mail.sunysuffolk.edu"> thomc16@mail.sunysuffolk.edu </A>
 * 
 * @version V1.0, 4/8/2019
 *
 */

public class EmployeeManagement implements Serializable {


	private TreeMap<String, Employee> tree;
    private boolean exists = false;
	private Employee activeUser = null;


	public EmployeeManagement() {}

	public void createEmployeeManagement() {
		tree = new TreeMap<String, Employee>();
		exists = true;
	}

	public TreeMap<String, Employee> getTree(){
	    return tree;
    }
	public boolean exists(){
	    return exists;
    }

    public void setTree(TreeMap<String, Employee> tree) {
        this.tree = tree;
    }

    /**
     *
     * @param employee - Employee object passed
     * @return boolean, if true added successfully. If false added unsuccessfully.
     */
	public boolean addEmployee(Employee employee) {
		if (this.findEmployee(employee.getUsername()))
			return false;
		tree.put(employee.getUsername(), employee);
		return true;
	}

	public void setActiveEmployee(Employee employee){
	    activeUser = employee;
    }

    public Employee getActiveEmployee(){
	    return activeUser;
    }

	public Employee getEmployee(Employee employee){
	    return getEmployee(employee.getUsername());
    }

    public Employee getEmployee(String username){
	    return tree.get(username);
    }
	/**
	 * 
	 * @param username - key value to search for and remove
	 * @return If removed successfully, return true. If false, removed unsuccessfully.
	 */
	public boolean removeEmployee(String username) {
		Employee removedEmployee = tree.remove(username);
		if (removedEmployee == null) {
			return false;
		}
		return true;
	}
	public boolean removeEmployee(Attendant attendant){
		return removeEmployee(attendant.getUsername());
	}
	/**
	 * 
	 * @param username - key value to search for
	 * @return If the employee is found, returns true. If false, not found in the TreeMap.
	 */
	public boolean findEmployee(String username) {
		if (tree.containsKey(username))
			return true;
		return false;
	}
	
	/**
	 * Prints the employees by iterating through the entrySet of the tree.
	 */
	public void printEmployees() {
		for(Map.Entry<String, Employee> entry: tree.entrySet()) {
			System.out.println(tree.get(entry.getKey()));
		}
		System.out.println("\n");
	}
}
