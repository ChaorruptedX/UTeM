package ftmk.rmi.sensor;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * This interface represents temperature sensor
 * 
 * @author - Chaorrupted X -
 *
 */
public interface TemperatureSensor extends Remote {
	
	/**
	 * This method gets current temperature
	 * 
	 * @return current temperature
	 * 
	 * @throws RemoteException
	 */
	public int getTemperature() throws RemoteException;

	public int getTemperatureAyerKeroh() throws RemoteException;
	
	public int getTemperatureMelakaByDay(Integer date_select) throws RemoteException;

	public int getAverageTemperatureInMelaka() throws RemoteException;

}
