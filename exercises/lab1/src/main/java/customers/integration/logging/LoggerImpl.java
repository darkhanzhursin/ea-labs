package customers.integration.logging;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LoggerImpl implements Logger{

	public void log(String logstring) {
		System.out.println("Logging "+LocalDateTime.now()+" "+logstring);		
	}

}
