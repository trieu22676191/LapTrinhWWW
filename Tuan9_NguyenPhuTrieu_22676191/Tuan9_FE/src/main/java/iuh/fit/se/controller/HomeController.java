package iuh.fit.se.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Value("${api.backend.url}")
    private String apiUrl;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("apiUrl", apiUrl);
        return "danh-sach";
    }

    @GetMapping("/quan-ly")
    public String quanLy(Model model) {
        model.addAttribute("apiUrl", apiUrl);
        return "quan-ly";
    }

    @GetMapping("/them-moi")
    public String themMoi(Model model) {
        model.addAttribute("apiUrl", apiUrl);
        return "them-moi";
    }

    @GetMapping("/cap-nhat")
    public String capNhat(Model model) {
        model.addAttribute("apiUrl", apiUrl);
        return "cap-nhat";
    }
}

