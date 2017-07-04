package restcontroller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreedingController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String greeding() {

        return "Hello ";
    }

  

}
