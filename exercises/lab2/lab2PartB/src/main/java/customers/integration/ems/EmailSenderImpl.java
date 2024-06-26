package customers.integration.ems;

import customers.integration.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderImpl implements EmailSender {

	@Value("${outgoingMailServer}")
	String outgoingMailServer;

	private Logger logger;

	public EmailSenderImpl(Logger logger) {
		this.logger = logger;
	}

	public String getOutgoingMailServer() {
		return outgoingMailServer;
	}

	public void sendEmail (String email, String message){
		System.out.println("EmailSender: sending '"+message+"' to "+email + " server: " + outgoingMailServer );
		logger.log("Email is sent: message= "+message +" , emailaddress ="+ email  );
	}

}
