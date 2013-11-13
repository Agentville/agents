package agentville.agents.agent004;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

public class AgentStarter {

	public static void main(String[] args){
	
		String host;
		int port;
		String platform = null;		//default name
		boolean main = true;
	
		host = "localhost";
		port = -1;			//default-port 1099
	
		Runtime runtime = Runtime.instance();
	
		Profile profile = null;
		AgentContainer container = null;
	
		profile = new ProfileImpl(host, port, platform, main);
	
		//Container erzeugen
		container = runtime.createMainContainer(profile);

//		Agent rmagent = new jade.tools.rma.rma();
//		// Remote Monitoring Agenten erzeugen
//		try {
//			AgentController rma = container.acceptNewAgent(
//	                	"RMA",
//	                    rmagent
//	                    );
//			rma.start();
//		} catch(StaleProxyException e) {
//			throw new RuntimeException(e);
//		}
	

		// Agenten erzeugen und startet - oder aussteigen.
		try {
			AgentController agent = container.createNewAgent(
	                	"MyAgent",
	                    MyAgent.class.getName(),
	                    args);
			agent.start();
	    } catch(StaleProxyException e) {
	        throw new RuntimeException(e);
	    }
	}
}
