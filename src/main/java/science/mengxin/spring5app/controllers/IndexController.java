package science.mengxin.spring5app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>Date:    23/04/18
 *
 * @author mengxin
 * @version 1.0
 */
@Controller
public class IndexController {

    @RequestMapping({"","/","/index"})
    public String getIndexPage() {
        return "index";
    }
}
