package rental;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.Set;

public interface ICarRentalCompany extends Remote {
	
	public Set<CarType> getFreeCarTypes(Date from, Date end) throws RemoteException;
	
	public Quote IcreateQuote(ReservationConstraints constraints, String client) throws RemoteException;
}
	
