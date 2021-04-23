import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import ftmk.rmi.sensor.TemperatureSensor;
import ftmk.rmi.sensor.manager.TemperatureSensorManager;

/**
 * This class represent the server-side application using RMI
 * 
 * @author - Chaorrupted X -
 *
 */
public class TemperatureServerRMIApp {

	public static void main(String[] args) {
		
		
		try {
			
			// Create interface object
			TemperatureSensor sensorJasin = new TemperatureSensorManager();
			TemperatureSensor sensorAyerKeroh = new TemperatureSensorManager();
			TemperatureSensor sensorMelakaByDay = new TemperatureSensorManager();
			TemperatureSensor sensorMelakaAverage = new TemperatureSensorManager();
			
			// Get registry
			Registry rmiRegistry = LocateRegistry.getRegistry();
			
			// Register interface object as remote object
			rmiRegistry.rebind("SensorJasin", sensorJasin);
			rmiRegistry.rebind("SensorAyerKeroh", sensorAyerKeroh);
			rmiRegistry.rebind("SensorMelakaByDay", sensorMelakaByDay);
			rmiRegistry.rebind("SensorMelakaAverage", sensorMelakaAverage);
			
			System.out.println("SensorJasin is successfully registered");
			System.out.println("SensorAyerKeroh is successfully registered");
			System.out.println("SensorMelakaAverage is successfully registered");
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
