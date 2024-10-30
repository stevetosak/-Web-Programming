package mk.finki.ukim.wp.lab.config;

import mk.finki.ukim.wp.lab.service.EventService;
import mk.finki.ukim.wp.lab.web.EventListServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Configuration
public class ServletConfig {
    @Bean
    public ServletRegistrationBean<EventListServlet> eventListServlet(EventService eventService, SpringTemplateEngine springTemplateEngine) {
        return new ServletRegistrationBean<>(new EventListServlet(eventService, springTemplateEngine), "/");
    }
}
