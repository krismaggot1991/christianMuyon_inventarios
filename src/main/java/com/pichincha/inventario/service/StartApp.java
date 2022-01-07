/**
 * 
 */
package com.pichincha.inventario.service;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Christian Muyon
 *
 */
@Slf4j
@Component
public class StartApp {

	@EventListener(ApplicationReadyEvent.class)
	public void startApp() {
		log.info("Programa arranca...");

	}

}
