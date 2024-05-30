package customers.integration.ems;

public interface EmailSender {
	void sendEmail(String email, String message);
	//String getOutgoingMailServer();

}