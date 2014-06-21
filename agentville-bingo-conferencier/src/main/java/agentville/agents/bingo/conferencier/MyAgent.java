package agentville.agents.bingo.conferencier;

import agentville.agents.bingo.conferencier.behaviours.KeepAliveBehaviour;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
//import jade.domain.introspection.ACLMessage;
import jade.util.Logger;

/**
 * Die Implementierung des Agenten.
 * 
 * @author Marco
 *
 */
public class MyAgent extends Agent {
	
	private static final long serialVersionUID = 1L;

	private static final Logger logger = jade.util.Logger.getMyLogger(
			MyAgent.class.getName());	
	
	/*
	 * (non-Javadoc)
	 * @see jade.core.Agent#setup()
	 */
	protected void setup() {
		
		if (logger.isLoggable(jade.util.Logger.WARNING))
			logger.log(jade.util.Logger.WARNING, 
					"setup() Agent " + getLocalName());
		
		/*
		 * - Dienst anbieten (registrieren yellow pages)
		 * - 
		 */
		
		ACLMessage aclm = new ACLMessage();

		//create and register the bingo service in the yellow pages
		createAndRegisterBingoService();
        
		//Der/die/das Behaviour wird ausgeführt, sobald hier die
        //setup()-Methode abgearbeitet wird. Also danach läuft
        //wohl der Scheduler an und führt den ersten Behaviour aus?
        KeepAliveBehaviour kab = new KeepAliveBehaviour(this);
		this.addBehaviour(kab);
        
    }

	/*
	 * create and register the bingo service in the yellow pages
	 */
	private void createAndRegisterBingoService() {
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		ServiceDescription sd = new ServiceDescription();
		sd.setType("bingo");
		sd.setName("agentville-bingo");
		//add language/ontology?
		dfd.addServices(sd);
		try {
			DFService.register(this, dfd); }
		catch (FIPAException fe) {
			if (logger.isLoggable(jade.util.Logger.WARNING))
				logger.log(jade.util.Logger.WARNING, 
						"Exception in setup(): " + fe.getStackTrace());
			fe.printStackTrace();
		}
	}
	
	/*
	 * Die takeDown-Methode wird automatisch ausgeführt, wenn der Agent 
	 * per "doDelete()" gelöscht wird. Hier wäre also der richtige Ort
	 * fuer Aufraeumarbeiten.
	 * 
	 * @see jade.core.Agent#takeDown()
	 */
	protected void takeDown() {
		if (logger.isLoggable(jade.util.Logger.WARNING))
			logger.log(jade.util.Logger.WARNING, "takeDown");
		
		try {
			DFService.deregister(this); }
		catch (FIPAException fe) {
			if (logger.isLoggable(jade.util.Logger.WARNING))
				logger.log(jade.util.Logger.WARNING, 
						"Exception in setup(): " + fe.getStackTrace());
			fe.printStackTrace();
		}

		if (logger.isLoggable(jade.util.Logger.WARNING))
			logger.log(jade.util.Logger.WARNING, "Agent bingo conferencier terminating");
	}
}