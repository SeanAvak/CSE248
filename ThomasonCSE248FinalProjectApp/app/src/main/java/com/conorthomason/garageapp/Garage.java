package com.conorthomason.garageapp;

import java.util.ArrayList;
import java.util.HashMap;

public class Garage {

    public void setVehicles(HashMap<String, Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public void setSpaces(ArrayList<Space> spaces) {
        this.spaces = spaces;
    }

    public void setCarSpaces(int carSpaces) {
        this.carSpaces = carSpaces;
    }

    public void setCurrentCars(int currentCars) {
        this.currentCars = currentCars;
    }

    public void setTruckSpaces(int truckSpaces) {
        this.truckSpaces = truckSpaces;
    }

    public void setCurrentTrucks(int currentTrucks) {
        this.currentTrucks = currentTrucks;
    }

    public void setMotorcycleSpaces(int motorcycleSpaces) {
        this.motorcycleSpaces = motorcycleSpaces;
    }

    public void setCurrentMotorcycles(int currentMotorcycles) {
        this.currentMotorcycles = currentMotorcycles;
    }

    private HashMap<String, Vehicle> vehicles;
	private ArrayList<Space> spaces;
	private int carSpaces;
	private int currentCars = 0;
	private int truckSpaces;
	private int currentTrucks = 0;
	private int motorcycleSpaces;
	private int currentMotorcycles = 0;
	private Garage _garage = new Garage();

	private Garage() {}

	public Garage createGarage(ArrayList<PaymentScheme> carPaymentSchemes, ArrayList<PaymentScheme> motorcyclePaymentSchemes, ArrayList<PaymentScheme> truckPaymentSchemes,
			int carSpaces, int truckSpaces, int motorcycleSpaces) {
		this.carSpaces = carSpaces;
		this.truckSpaces = truckSpaces;
		this.motorcycleSpaces = motorcycleSpaces;
		this.spaces = new ArrayList<Space>();
		this.spaceSetup(VehicleType.CAR, this.carSpaces, carPaymentSchemes);
		this.spaceSetup(VehicleType.MOTORCYCLE, this.motorcycleSpaces, motorcyclePaymentSchemes);
		this.spaceSetup(VehicleType.TRUCK, this.truckSpaces, truckPaymentSchemes);
		vehicles = new HashMap<String, Vehicle>((carSpaces + truckSpaces + motorcycleSpaces) * 2);
		return _garage;
	}

	public int getCarSpaces(){
	    return carSpaces;
    }
    public int getCurrentCars(){
	    return currentCars;
    }
    public int getTruckSpaces(){
	    return truckSpaces;
    }
    public int getCurrentTrucks(){
	    return currentTrucks;
    }
    public int getMotorcycleSpaces(){
	    return motorcycleSpaces;
    }
    public int getCurrentMotorcycles(){
	    return currentMotorcycles;
    }

	public ArrayList<Space> getSpaces(){
	    return spaces;
    }

	public void spaceSetup(VehicleType vehicleType, int numberOfSpaces, ArrayList<PaymentScheme> paymentScheme) {
		for (int i = 0; i < numberOfSpaces; i++) {
			Space newSpace = new Space(vehicleType, paymentScheme);
			spaces.add(newSpace);
		}
	}
	
	private boolean addVehicle(Vehicle vehicle) {
		if (!this.findVehicle(vehicle.getLicensePlate())) {
			vehicles.put(vehicle.getLicensePlate(), vehicle);
			return true;
		}
		return false;
	}
	
	public boolean spaceAvailable(VehicleType type) {
		boolean returnedValue;
		switch(type) {
		case CAR: returnedValue = (currentCars < carSpaces) ? true : false;
			break;
		case MOTORCYCLE: returnedValue = (currentMotorcycles < motorcycleSpaces) ? true : false;
			break;
		case TRUCK: returnedValue = (currentTrucks < truckSpaces) ? true : false;
			break;
		default: returnedValue = false;
			break;
		}
		return returnedValue;
	}
	
	public Space getSpace(int spaceIndex) {
		return spaces.get(spaceIndex);
	}
	
	public boolean parkVehicle(Vehicle vehicle) {
		int carOffset = carSpaces - currentCars;
		int motorcycleOffset = carSpaces + (motorcycleSpaces - currentMotorcycles);
		int truckOffset = carSpaces + motorcycleSpaces + (truckSpaces - currentTrucks);
		if (spaceAvailable(vehicle.getVehicleType())) {
			switch(vehicle.getVehicleType()) {
			case CAR:
				vehicle.setParkingSpot(carOffset);
				spaces.get(carSpaces - currentCars).setVehicleLicense(vehicle.getLicensePlate());
				break;
			case MOTORCYCLE:
				vehicle.setParkingSpot(motorcycleOffset);
				spaces.get(motorcycleSpaces - currentMotorcycles).setVehicleLicense(vehicle.getLicensePlate());
				break;
			case TRUCK:
				vehicle.setParkingSpot(truckOffset);
				spaces.get(truckSpaces - currentTrucks).setVehicleLicense(vehicle.getLicensePlate());
				break;
			default:
				break;
			}
			addVehicle(vehicle);
			return true;
		}
		return false;
	}
	public void printVehiclesKeySet() {
		System.out.println("Printing Vehicles");
		for (String vehicleLicense : vehicles.keySet()) {
			System.out.println(vehicleLicense);
		}
		System.out.println("Done printing vehicles \n");
	}
	public Vehicle getVehicle(String vehicleLicense) {
		if (findVehicle(vehicleLicense)) {
			return vehicles.get(vehicleLicense);
		}
		return null;
	}
	
	public boolean findVehicle(String vehicleLicense) {
		if (vehicles.get(vehicleLicense) == null) {
			return false;
		}
		return true;
	}
	
	public boolean removeVehicle(String vehicleLicense) {
		if (findVehicle(vehicleLicense)) {
			vehicles.remove(vehicleLicense);
			return true;
		}
		return false;
	}
	
	public HashMap<String, Vehicle> getVehicles() {
		return vehicles;
	}
	
	public String garageDetails() {
		return "Car Spaces: " + this.carSpaces + ", Motorcycle Spaces: " + this.motorcycleSpaces + ", Truck Spaces: " +
				this.truckSpaces;
	}
}
