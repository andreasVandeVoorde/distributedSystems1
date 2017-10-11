package rental;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.Set;

public interface ICarRentalCompany extends Remote {
	
	public Set<CarType> IGetFreeCarTypes(Date from, Date end) throws RemoteException;
	public Quote ICreateQuote(ReservationConstraints constraints, String client) throws RemoteException;
	public Reservation IConfirmQuote(Quote quote) throws RemoteException;
	public int IGetNumberOfReservationsForCarType(String carType) throws RemoteException;
	
	
}
	
