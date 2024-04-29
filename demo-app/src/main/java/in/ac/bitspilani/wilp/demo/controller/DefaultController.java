package in.ac.bitspilani.wilp.demo.controller;

import in.ac.bitspilani.wilp.demo.model.AppDetail;
import in.ac.bitspilani.wilp.demo.service.impl.IDefaultService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping(path = "/")
public class DefaultController {

    private static Logger LOGGER = LoggerFactory.getLogger(DefaultController.class);
    @Autowired
    IDefaultService defaultService;

    @GetMapping(path = "/")
    public String index(Model model){
        LOGGER.info(defaultService.getAppDetails().toString());
        model.addAttribute("appDetail", defaultService.getAppDetails());
        return "index";
    }
}
