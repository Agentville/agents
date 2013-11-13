package agentville.agents.agent005;

import jade.core.Agent;
import jade.util.Logger;

public class MyAgent extends Agent {
	
//	Logger logger = jade.util.Logger.getMyLogger(this.getClass().getName());	
	
	private static final long serialVersionUID = 1L;

	protected void setup() {
		
		try {
		    Thread.sleep(10000);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
//		if (logger.isLoggable(jade.util.Logger.WARNING))
//			logger.log(jade.util.Logger.WARNING, "greetings from setup()");		
	    	
        System.out.println("Hello Eclipse! This is "
                + getLocalName());
    }
}