package client;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;
import java.util.List;
import java.util.Set;

import rental.ICarRentalCompany;
import rental.Quote;
import rental.Reservation;
import rental.ReservationConstraints;

public class Client extends AbstractTestBooking {
	ICarRentalCompany crc;
	
	/********
	 * MAIN *
	 ********/
	
	public static void main(String[] args) throws Exception {
		System.setSecurityManager(null);
		
//		String host = "localhost";
//		int port = 8080;
//		Registry registry= LocateRegistry.getRegistry(host, port);
		
		ICarRentalCompany crc = (ICarRentalCompany) Naming.lookup("//localhost:8080/"+"rental");
//				registry.lookup("rental");
		
		String carRentalCompanyName = "Hertz";
		
		// An example reservation scenario on car rental company 'Hertz' would be...
		Client client = new Client("simpleTrips", carRentalCompanyName,crc);
		client.run();
	}
	
	/***************
	 * CONSTRUCTOR *
	 ***************/
	
	public Client(String scriptFile, String carRentalCompanyName,ICarRentalCompany crc) {
		super(scriptFile);
		this.crc = crc;
		System.out.println("succes client");
//		throw new UnsupportedOperationException("TODO 0");
	}
	
	/**
	 * Check which car types are available in the given period
	 * and print this list of car types.
	 *
	 * @param 	start
	 * 			start time of the period
	 * @param 	end
	 * 			end time of the period
	 * @throws 	Exception
	 * 			if things go wrong, throw exception
	 */
	@Override
	protected void checkForAvailableCarTypes(Date start, Date end) throws Exception {
		Set freeCarTypes = crc.IGetFreeCarTypes(start, end);
		System.out.println("checking for available cars");
		for (Object x : freeCarTypes){
			System.out.println(x);
			
		}
//		throw new UnsupportedOperationException("TODO 1");
	}

	/**
	 * Retrieve a quote for a given car type (tentative reservation).
	 * 
	 * @param	clientName 
	 * 			name of the client 
	 * @param 	start 
	 * 			start time for the quote
	 * @param 	end 
	 * 			end time for the quote
	 * @param 	carType 
	 * 			type of car to be reserved
	 * @param 	region
	 * 			region in which car must be available
	 * @return	the newly created quote
	 *  
	 * @throws 	Exception
	 * 			if things go wrong, throw exception
	 */
	@Override
	protected Quote createQuote(String clientName, Date start, Date end,
			String carType, String region) throws Exception {
		System.out.println("creatQuote");
		ReservationConstraints constraints = new ReservationConstraints(start, end, carType, region);
		return crc.ICreateQuote(constraints, clientName);
		
		
//		throw new UnsupportedOperationException("TODO 2");
	}

	/**
	 * Confirm the given quote to receive a final reservation of a car.
	 * 
	 * @param 	quote 
	 * 			the quote to be confirmed
	 * @return	the final reservation of a car
	 * 
	 * @throws 	Exception
	 * 			if things go wrong, throw exception
	 */
	@Override
	protected Reservation confirmQuote(Quote quote) throws Exception {
		System.out.println("confirming Quote");
		return crc.IConfirmQuote(quote);
		//throw new UnsupportedOperationException("TODO 3");
	}
	
	/**
	 * Get all reservations made by the given client.
	 *
	 * @param 	clientName
	 * 			name of the client
	 * @return	the list of reservations of the given client
	 * 
	 * @throws 	Exception
	 * 			if things go wrong, throw exception
	 */
	@Override
	protected List<Reservation> getReservationsByRenter(String clientName) throws Exception {
		System.out.println("Searching for reservations");
		return crc.IGetReservationsByRenter(clientName);
		//throw new UnsupportedOperationException("TODO 4");
	}

	/**
	 * Get the number of reservations for a particular car type.
	 * 
	 * @param 	carType 
	 * 			name of the car type
	 * @return 	number of reservations for the given car type
	 * 
	 * @throws 	Exception
	 * 			if things go wrong, throw exception
	 */
	@Override
	protected int getNumberOfReservationsForCarType(String carType) throws Exception {
		System.out.println("Calculating number of reservations");
		return crc.IGetNumberOfReservationsForCarType(carType);
		//throw new UnsupportedOperationException("TODO 5");
	}
}