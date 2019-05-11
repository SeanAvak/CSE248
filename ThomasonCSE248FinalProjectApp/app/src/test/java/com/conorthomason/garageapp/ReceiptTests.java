package com.conorthomason.garageapp;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.conorthomason.garageapp.*;

class ReceiptTests {

	ArrayList<PaymentScheme> carPayment = new ArrayList<PaymentScheme>();
	ArrayList<PaymentScheme> truckPayment = new ArrayList<PaymentScheme>();
	ArrayList<PaymentScheme> motorcyclePayment = new ArrayList<PaymentScheme>();
	
	@Test
	void instantiationTest() {
		TimeControl.createTimeThread(1);
		Garage.createGarage(carPayment, motorcyclePayment, truckPayment, 5, 5, 5);
		Vehicle vehicle = new Vehicle(VehicleType.CAR, "John", "AA12 345");
		Garage.parkVehicle(vehicle);
		Attendant attendant = new Attendant("Test", "123", "John", "Doe");
		Receipt receipt = new Receipt(vehicle, attendant, PaymentScheme.CASH);
		assertTrue(vehicle.getLicensePlate().equals(receipt.getLicensePlate()));
		assertTrue(vehicle.getVehicleType().equals(receipt.getVehicleType()));
		assertTrue(attendant.getFullName().equals(receipt.getAttendantName()));
		assertTrue(PaymentScheme.CASH.equals(receipt.getPaymentScheme()));
		
	}

}