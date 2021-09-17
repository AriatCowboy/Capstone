package Game.controller;

import Game.domain.CompanyService;
import Game.domain.Result;
import Game.model.Company;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    private final CompanyService service;

    public CompanyController(CompanyService service) {
        this.service = service;
    }

    @GetMapping
    public List<Company> findAll() {
        return service.findAll();
    }


    @GetMapping("/{companyId}")
    public ResponseEntity<Company> findById(@PathVariable int companyId) {
        Result<Company> company = service.findById(companyId);
        if (company.getPayload() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(company.getPayload());
    }
}
