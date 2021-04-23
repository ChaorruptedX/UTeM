package ftmk.rmi.sensor.manager;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

import ftmk.rmi.sensor.TemperatureSensor;

/**
 * This class manage the value of temperature from the sensor.
 * 
 * @author - Chaorrupted X -
 *
 */
public class TemperatureSensorManager extends UnicastRemoteObject implements TemperatureSensor {

	public TemperatureSensorManager() throws RemoteException {
		super();
	}

	@Override
	public int getTemperature() throws RemoteException {
		
		return 35;
	}
	
	@Override
	public int getTemperatureAyerKeroh() throws RemoteException {
		
		return 29;
	}
	
	@Override
	public int getTemperatureMelakaByDay(Integer date_select) throws RemoteException {
		
		// create a hashmap
		HashMap<Integer, Integer> temperature = new HashMap<>();
		
		// add elements to hashmap
		temperature.put(1, 32); // Monday
		temperature.put(2, 31); // Tuesday
		temperature.put(3, 33); // Wednesday
		temperature.put(4, 35); // Thursday
		temperature.put(5, 36); // Friday
		temperature.put(6, 33); // Saturday
		temperature.put(7, 33); // Sunday
	    
		Integer value = temperature.get(date_select);
	    
		return value;
	}
	
	@Override
	public int getAverageTemperatureInMelaka() throws RemoteException {
		
		int total_temperature = 0;
		int average_temperature = 0;
		
		for (int i = 1; i < 8; i++)
		{
			total_temperature += this.getTemperatureMelakaByDay(i);
		}
		
		average_temperature = total_temperature / 7;
		
		return average_temperature;
	}


}
