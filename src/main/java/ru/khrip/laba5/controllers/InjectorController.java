package ru.khrip.laba5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.khrip.laba5.Injector;
import ru.khrip.laba5.SomeBean;

import java.io.IOException;

@Controller
@RequestMapping("/result")
public class InjectorController {
    private Injector injector;

@Autowired
    public InjectorController(Injector injector){this.injector=injector;}

    @GetMapping()
    public String show(@RequestParam(value="a",defaultValue = "SomeInterface1") String str,  Model model) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        SomeBean someBean=new Injector().inject(new SomeBean(),str);
        model.addAttribute("string",someBean.foo());
        return "result/showPage";
    }

}
