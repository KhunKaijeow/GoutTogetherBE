package dev.kjcoder.goutbackend.tourcompany;

import dev.kjcoder.goutbackend.tourcompany.dto.RegisterTourCompanyDto;
import dev.kjcoder.goutbackend.tourcompany.model.TourCompany;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tour-companies")
public class TourCompanyController {

    private static final Logger logger = LoggerFactory.getLogger(TourCompanyController.class);

    private final TourCompanyService tourCompanyService;

    public TourCompanyController(TourCompanyService tourCompanyService) {
        this.tourCompanyService = tourCompanyService;
    }

    @PostMapping
    public ResponseEntity<TourCompany> registerTourCompany(@RequestBody @Validated RegisterTourCompanyDto registerTourCompanyDto) {
        var tourCompany =  tourCompanyService.registerTourCompany(registerTourCompanyDto);
        return ResponseEntity.ok(tourCompany);
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<TourCompany> approveTourCompany(@PathVariable Integer id) {
        var approvedTourCompany =  tourCompanyService.approvedTourCompany(id);
        logger.info("[approveTourCompany] tour company ID: {} is approved", id);
        return ResponseEntity.ok(approvedTourCompany);
    }
}
