package com.example.demo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller for handling graduation messages.
 */
@RestController
public class GraduationController {

    @GetMapping("/graduation")
    public GraduationMessage getGraduationMessage(
            @RequestParam(value = "name", defaultValue = "Graduate") String name,
            @RequestParam(value = "year", defaultValue = "2024") int year,
            @RequestParam(value = "degree", defaultValue = "Computer Science") String degree) {

        String message = "Congratulations " + name + "!  You graduated in " + year + " with a degree in " + degree + ". Wishing you success!";
        return new GraduationMessage(name, year, degree, message);
    }
}
