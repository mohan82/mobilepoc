package mobilepoc.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;


/**
 * Created with IntelliJ IDEA.
 * User: mohan
 * Date: 22/09/12
 * Time: 2:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class HelloController implements  Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String now = (new Date()).toString();
        return new ModelAndView("hello", "now", now);
    }
}
