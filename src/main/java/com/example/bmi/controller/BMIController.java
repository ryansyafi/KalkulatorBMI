package com.example.bmi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BMIController {

    @GetMapping("/")
    public String showForm() {
        return "index";  // Pastikan ada file index.html di folder templates
    }

    @PostMapping("/calculateBMI")
    public String calculateBMI(
            @RequestParam("name") String name,
            @RequestParam("weight") int weight,
            @RequestParam("height") int height,
            Model model) {

        float heightInM = height / 100F;
        float bmi = weight / (heightInM * heightInM);

        String message;
        if (bmi < 18.5) {
            message = "Pola makannya tolong diperhatikan!!";
        } else if (bmi >= 18.5 && bmi < 25) {
            message = "Anda termasuk SEHAT, Pertahankan!!";
        } else {
            message = "Sebaiknya kurangi kandungan gula berlebihan!!";
        }

        model.addAttribute("bmi", String.format("%.2f", bmi));  // Format BMI dengan 2 desimal
        model.addAttribute("name", name);
        model.addAttribute("message", message);

        return "result";  // Pastikan ada file result.html di folder templates
    }
}
