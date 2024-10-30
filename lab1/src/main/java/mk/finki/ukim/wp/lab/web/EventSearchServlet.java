package mk.finki.ukim.wp.lab.web;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.wp.lab.model.Event;
import mk.finki.ukim.wp.lab.service.EventService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "EventSearchServlet",urlPatterns = "/search")
public class EventSearchServlet extends HttpServlet {

    private final EventService eventService;
    private final SpringTemplateEngine springTemplateEngine;

    public EventSearchServlet(EventService eventService, SpringTemplateEngine springTemplateEngine) {
        this.eventService = eventService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange =
                JakartaServletWebApplication
                        .buildApplication(getServletContext())
                        .buildExchange(req, resp);

        WebContext webContext = new WebContext(webExchange);

        String searchText = req.getParameter("searchText");
        String searchRating = req.getParameter("searchRating");

        long start = System.nanoTime();

        List<Event> results = eventService.dynamicSearch(searchText,searchRating);

        long end = System.nanoTime();

        System.out.println("Time for search: " + ((end - start) / (Math.pow(10,6))) + "ms");

        if(!results.isEmpty()) {
            webContext.setVariable("events",results);
        } else {
            webContext.setVariable("events",eventService.listAll());
        }
        springTemplateEngine.process("listEvents.html", webContext, resp.getWriter());
    }
}
