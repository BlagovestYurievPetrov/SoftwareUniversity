package softuni.exam.service;



import softuni.exam.models.entities.Passenger;

import java.io.IOException;


public interface PassengerService {

    boolean areImported();

    String readPassengersFileContent() throws IOException;
	
	String importPassengers() throws IOException;

	String getPassengersOrderByTicketsCountDescendingThenByEmail();

	boolean doesPassengerExist(String email);

	Passenger findPassengerByEmail(String email);
}
