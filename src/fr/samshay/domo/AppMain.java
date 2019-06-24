package fr.samshay.domo;

import java.util.logging.Level;
import java.util.logging.Logger;

import fr.samshay.domo.lib.jneat.AppLock;
import fr.samshay.domo.lib.jneat.CrossLock;


public class AppMain {
	
	private static final Logger log = Logger.getGlobal();

	public static void main(String[] args) {

		// TODO Auto-generated method stub
		String arg0 = args[0];
		int zone = Integer.valueOf(arg0);
		String commande = args[1];
		
		if(!AppLock.lock("LockVolet"))
		{
			while(AppLock.lock("LockVolet") != true)
			{
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		}
		log.log(Level.INFO, "volet", zone);
		
		RaspberryServiceImpl serviceRaspberry = new RaspberryServiceImpl();
		
		switch(commande)
        {
	        case "stop":
	        	log.log(Level.INFO, "Stop volet zone : {}", zone);
	        	serviceRaspberry.stop(zone);
	        	break;
	        case "fermeture":
	        	log.log(Level.INFO, "Fermeture volet zone : {}", zone);
	        	serviceRaspberry.fermeture(zone);
	        	break;
	        case "ouverture":
	        	log.log(Level.INFO, "Ouverture volet zone : {}", zone);
	        	serviceRaspberry.ouverture(zone);
	        	break;

        }
        //On fait une pause à chaque sortie pour éviter les conflits
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
        AppLock.release();
	        
	}

}
