import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;  

import ftmk.rmi.sensor.TemperatureSensor;

/**
 * This class represent the client-side of RMI application
 * 
 * @author - Chaorrupted X -
 *
 */
public class TemperatureClientRMIApp {

	public static void main(String[] args) {
		
		
		try {
			
			// Get registry
			Registry rmiRegistry = LocateRegistry.getRegistry();
			
			// Look-up for the remote object
			TemperatureSensor remoteSensorJasin = (TemperatureSensor) rmiRegistry.lookup("SensorJasin");
			TemperatureSensor remoteSensorAyerKeroh = (TemperatureSensor) rmiRegistry.lookup("SensorAyerKeroh");
			TemperatureSensor remoteSensorMelakaByDay = (TemperatureSensor) rmiRegistry.lookup("SensorMelakaByDay");
			TemperatureSensor remoteSensorMelakaAverage = (TemperatureSensor) rmiRegistry.lookup("SensorMelakaAverage");
			
			Scanner sc = new Scanner(System.in); //System.in is a standard input stream  
			
			System.out.print("Please select day to get temperature from the specific day in Melaka.\n");
			System.out.print("[1] Monday\n");
			System.out.print("[2] Tuesday\n");
			System.out.print("[3] Wednesday\n");
			System.out.print("[4] Thursday\n");
			System.out.print("[5] Friday\n");
			System.out.print("[6] Saturday\n");
			System.out.print("[7] Sunday\n");
			
			int date_select = sc.nextInt(); 
			sc.close();
			
			String day_name = "?";
			
			if (date_select == 1)
			{
				day_name = "Monday";
			}
			else if (date_select == 2)
			{
				day_name = "Tuesday";
			}
			else if (date_select == 3)
			{
				day_name = "Wednesday";
			}
			else if (date_select == 4)
			{
				day_name = "Thursday";
			}
			else if (date_select == 5)
			{
				day_name = "Friday";
			}
			else if (date_select == 6)
			{
				day_name = "Saturday";
			}
			else if (date_select == 7)
			{
				day_name = "Sunday";
			}
			
			// Invoke method from the remote object
			int currentTemperature = remoteSensorJasin.getTemperature();
			int currentTemperatureAyerKeroh = remoteSensorAyerKeroh.getTemperatureAyerKeroh();
			int currentTemperatureMelakaByDay = remoteSensorMelakaByDay.getTemperatureMelakaByDay(date_select);
			int averageTemperatureMelaka = remoteSensorMelakaAverage.getAverageTemperatureInMelaka();
			
			System.out.println("Current temperature in Jasin is " + currentTemperature + " Celcius");
			System.out.println("Current temperature in Ayer Keroh is " + currentTemperatureAyerKeroh + " Celcius");
			System.out.println("Temperature in Melaka on " + day_name + " is " + currentTemperatureMelakaByDay + " Celcius");
			System.out.println("The average temperature in Melaka is " + averageTemperatureMelaka + " Celcius");
			
		} catch (RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
