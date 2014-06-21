package agentville.agents.bingo.conferencier.behaviours;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.util.Logger;

public class KeepAliveBehaviour extends CyclicBehaviour {
	
	private static final long serialVersionUID = 1L;

	private static final Logger logger = jade.util.Logger.getMyLogger(
			KeepAliveBehaviour.class.getName());	
	
	Agent agent = null;
	
	public KeepAliveBehaviour(Agent a) {
		super(a);
		agent = a;
	}

	/*
	 * (non-Javadoc)
	 * @see jade.core.behaviours.Behaviour#action()
	 */
	@Override
	public void action() {

		ACLMessage msg = agent.receive();
		if (msg != null) {
			if (logger.isLoggable(jade.util.Logger.INFO))
				logger.log(jade.util.Logger.INFO, "processing message");
		} else {
			block();
		}
		
		//Der Agent bleibt normalerweise am Leben, auch wenn kein Behaviour
		//mehr abgearbeitet wird. Und er kann dann auch noch Messages bekommen
		//und seine Arbeit wieder aufnehmen(TODO: stimmt das?)
		//Hier wollen wir das aber nicht, deshalb wird der Agent hier terminiert.
		//this.getAgent().doDelete();
	}
}
