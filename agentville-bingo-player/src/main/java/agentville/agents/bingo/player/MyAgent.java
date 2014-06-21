package agentville.agents.bingo.player;

import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.util.Logger;
import agentville.agents.bingo.player.behaviours.KeepAliveBehaviour;

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

		//create and register the bingo service in the yellow pages
//		createAndRegisterBingoService();
		
		/*
		 * - Nach dem Conferencier-Agenten (bzw. dessen Bingo-Service) suchen
		 * - dort anmelden
		 * - auf zahlen warten
		 * - zahl verarbeiten
		 * - wenn alle zahlen genannt wurden: "bingo" an den conf. senden
		 * - antwort verarbeiten.
		 * - wieder von vorne.
		 */
		
		DFAgentDescription template = new DFAgentDescription();
		ServiceDescription sd = new ServiceDescription();
		sd.setType("bingo");
		template.addServices(sd);
		try {
			DFAgentDescription[] result = DFService.search(this, template);
			AID conferencier = new AID();
			for (int i = 0; i < result.length; ++i) {
				if (result[i].getName().toString() == "agentville-bingo") {
					conferencier = result[i].getName(); 
				}
			}
		} catch (FIPAException fe) {
			fe.printStackTrace();
			
		}
        
		//Der/die/das Behaviour wird ausgeführt, sobald hier die
        //setup()-Methode abgearbeitet wird. Also danach läuft
        //wohl der Scheduler an und führt den ersten Behaviour aus?
        KeepAliveBehaviour kab = new KeepAliveBehaviour(this);
		this.addBehaviour(kab);
        
    }

//	/*
//	 * create and register the bingo service in the yellow pages
//	 */
//	private void createAndRegisterBingoService() {
//		DFAgentDescription dfd = new DFAgentDescription();
//		dfd.setName(getAID());
//		ServiceDescription sd = new ServiceDescription();
//		sd.setType("bingo");
//		sd.setName("agentville-bingo");
//		//add language/ontology?
//		dfd.addServices(sd);
//		try {
//			DFService.register(this, dfd); }
//		catch (FIPAException fe) {
//			if (logger.isLoggable(jade.util.Logger.WARNING))
//				logger.log(jade.util.Logger.WARNING, 
//						"Exception in setup(): " + fe.getStackTrace());
//			fe.printStackTrace();
//		}
//	}
	
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
		
//		try {
//			DFService.deregister(this); }
//		catch (FIPAException fe) {
//			if (logger.isLoggable(jade.util.Logger.WARNING))
//				logger.log(jade.util.Logger.WARNING, 
//						"Exception in setup(): " + fe.getStackTrace());
//			fe.printStackTrace();
//		}

		if (logger.isLoggable(jade.util.Logger.WARNING))
			logger.log(jade.util.Logger.WARNING, "Agent bingo conferencier terminating");
	}
}