package platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import platform.models.Code;
import platform.services.CodeService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;


@Controller
public class MainController {

    @Autowired
    private CodeService codeService;


    @GetMapping(value = "/code/latest", produces = "text/html")
    public String getLatestHtmlCode(Model model) {

        List<Code> latestCodes = codeService.latestTenCodes();
        model.addAttribute("codes", latestCodes);
        return "latest";
    }

    @GetMapping(value = "/api/code/latest", produces = "application/json")
    public ResponseEntity<List<Code>> getLatestCode() {

        List<Code> latestCodes = codeService.latestTenCodes();
        return ResponseEntity.ok(latestCodes);
    }

    @GetMapping(value = "/api/code/{id}", produces = "application/json")
    public ResponseEntity<Code> getCode(@PathVariable String id) {

        try {
            Code code = codeService.getById(id);
            return ResponseEntity.ok(code);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/code/{id}", produces = "text/html")
    public String getHtmlCode(@PathVariable String id, Model model) {

        try {
            Code code = codeService.getById(id);
            model.addAttribute("Code", code);
            return "index";
        } catch (NoSuchElementException e) {
            return "";
        }
    }

    @GetMapping(value = "/code/new", produces = "text/html")
    public String getHtmlForm() {

        return "form";
    }


    @PostMapping(value = "/api/code/new", produces = "application/json")
    public ResponseEntity<Map<String, String>> postCode(@Valid @RequestBody Code code) {

        code.setRestrictions();
        String id = codeService.save(code);
        Map<String, String> response = new HashMap<>();
        response.put("id", id);
        return ResponseEntity.ok(response);
    }
}
