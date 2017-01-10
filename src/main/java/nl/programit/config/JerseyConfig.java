package nl.programit.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import nl.programit.rest.service.QuestionEndpoint;
import nl.programit.rest.service.TestTemplateEndpoint;
import nl.programit.rest.service.AnswerListEndpoint;
import nl.programit.rest.service.AttemptEndpoint;
import nl.programit.rest.service.StudentEndpoint;
import nl.programit.rest.service.StudentClassEndpoint;
import nl.programit.rest.service.InstructorEndpoint;
import nl.programit.rest.service.VersionEndpoint;

@Component
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {
		register(AnswerListEndpoint.class);
		register(QuestionEndpoint.class);
		register(TestTemplateEndpoint.class);
		register(AttemptEndpoint.class);
		register(StudentEndpoint.class);
		register(StudentClassEndpoint.class);
		register(InstructorEndpoint.class);
		register(VersionEndpoint.class);
	}
}
