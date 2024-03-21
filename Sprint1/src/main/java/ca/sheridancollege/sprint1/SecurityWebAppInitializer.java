package ca.sheridancollege.sprint1;

import ca.sheridancollege.sprint1.security.SecurityConfiguration;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityWebAppInitializer extends AbstractSecurityWebApplicationInitializer {

    public SecurityWebAppInitializer(){
        super(SecurityConfiguration.class);
    }
}
