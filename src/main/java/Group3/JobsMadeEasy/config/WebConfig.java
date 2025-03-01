package Group3.JobsMadeEasy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;
import jakarta.servlet.http.HttpSession;
import org.springframework.mock.web.MockHttpSession;

@Configuration
public class WebConfig {
    
    @Bean
    @SessionScope
    public HttpSession session() {
        return new MockHttpSession();
    }
}
