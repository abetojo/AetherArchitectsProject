package ca.sheridancollege.sprints;

import ca.sheridancollege.sprints.security.SecurityConfiguration;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityWebAppInitializer extends AbstractSecurityWebApplicationInitializer {

    public SecurityWebAppInitializer(){
        super(SecurityConfiguration.class);
    }
}
