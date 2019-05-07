package com.conorthomason.garageapp;

import java.io.Serializable;

public class Attendant extends Employee implements Serializable {

	public Attendant(String username, String password, String firstName, String lastName) {
		super(username, password, firstName, lastName);
		EmployeeManagement.addEmployee(this);
	}
	
	public boolean setParked(VehicleType vehicleType, String licensePlate) {
		Vehicle vehicle = new Vehicle(vehicleType, this.getFullName(), licensePlate);
		Garage.parkVehicle(vehicle);
		return false;
	}
}
