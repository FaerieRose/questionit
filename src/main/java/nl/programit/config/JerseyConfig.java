package nl.programit.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import nl.programit.rest.service.StudentEndpoint;

//hier Endpoint classes importeren, tenzij ze geregistreerd worden d.m.v. "packages"
//import ....KlantEndpoint;
//import .....PersonEndpoint;

/**
 * 
 * @author stefan
 *
 */

@Component
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {

		// registreer hier je Endpoints. Per stuk, of een heel package met
		// endpoints in een keer!!
		// per stuk (niet vergeten endpoint classes eerst te importeren):
		// register(PersonEndpoint.class);
		// register(KlantEndpoint.class);
		// alle endpoints uit given package in een keer registreren:

//		packages("com.liebregts.rest.service"); // appears not to work when running the jar file!!!!!!!!!!!!!!!!!!!!
		register(StudentEndpoint.class);
	}

}
