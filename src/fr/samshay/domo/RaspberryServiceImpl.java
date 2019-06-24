package fr.samshay.domo;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.RaspiPin;

public class RaspberryServiceImpl  {


	private final GpioController gpio = GpioFactory.getInstance();
	final GpioPinDigitalOutput pinOuverture = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, "Ouverture");
	final GpioPinDigitalOutput pinFermeture = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "Fermeture");
	final GpioPinDigitalOutput pinStop = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "Stop");
	final GpioPinDigitalOutput pinZone = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_12, "Zone");
	final GpioPinDigitalInput pinZone1 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_13, "Zone1");

	private void ouverture() {
		pinOuverture.pulse(250, true);
		try {
			Thread.sleep(350);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pinOuverture.pulse(250, true);
		try {
			Thread.sleep(350);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void fermeture() {
		pinFermeture.pulse(250, true);
		try {
			Thread.sleep(350);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pinFermeture.pulse(250, true);
		try {
			Thread.sleep(350);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void stop() {
		pinStop.pulse(250, true);
		try {
			Thread.sleep(350);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pinStop.pulse(250, true);
		try {
			Thread.sleep(350);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean isZone1() {
		return pinZone1.isHigh();
	}

	private void changerZone() {
		pinZone.pulse(500, true);
		try {
			Thread.sleep(700);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void selectionnerZone(int iZone) {	
		//J'active le bouton pour allumer les leds de zone
		pinZone.pulse(250, true);
		try {
			Thread.sleep(350);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while (getZone() != iZone) {
			changerZone();
		}
	}

	private int getZone() {
		if (isZone1())
			return 1;
		else
			return 2;
	}

	public void ouverture(int zone) {
		selectionnerZone(zone);
		ouverture();
	}

	public void fermeture(int zone) {
		selectionnerZone(zone);
		fermeture();
	}

	public void stop(int zone) {
		selectionnerZone(zone);
		stop();
	}
}
