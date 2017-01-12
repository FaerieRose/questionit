package nl.programit.rest.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
//import org.springframework.beans.factory.annotation.Autowired;


/**
 * Endpoint to get version information of back end
 * 
 * @author Bas Smulders
 * @version v0.1
 * @since 2016-11-04
 */

@Path("version")
public class VersionEndpoint {

	//@Autowired
	//TestTemplateService testTemplateService;

	/**
	 * GET the current version of the back end application
	 * Path = 'api/version/app'
	 * @return 200 + version string
	 * @author Bas
	 */
	@GET
	//@Produces(MediaType.APPLICATION_JSON)
	@Path("app")		
	public Response getAppVersion() {
		// TO DO: get version string and time stamp from POM file.
		return Response.ok("0.3.0-SNAPSHOT-20170112_163500").build();
	}

}


