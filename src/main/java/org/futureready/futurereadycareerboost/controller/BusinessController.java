package org.futureready.futurereadycareerboost.controller;

import lombok.RequiredArgsConstructor;
import org.futureready.futurereadycareerboost.entity.Business;
import org.futureready.futurereadycareerboost.repository.BusinessRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/business")
@RequiredArgsConstructor
public class BusinessController {

    private final BusinessRepository businessRepository;

    // Merr të gjithë bizneset
    @GetMapping
    public ResponseEntity<List<Business>> getAllBusinesses() {
        return ResponseEntity.ok(businessRepository.findAll());
    }

    // Merr një biznes sipas ID
    @GetMapping("/{id}")
    public ResponseEntity<Business> getBusinessById(@PathVariable Long id) {
        return businessRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Shto një biznes të ri
    @PostMapping
    public ResponseEntity<Business> createBusiness(@RequestBody Business business) {
        return ResponseEntity.ok(businessRepository.save(business));
    }

    // Përditëso informacionet bazë të një biznesi (p.sh. industry, companyName)
    @PutMapping("/{id}")
    public ResponseEntity<Business> updateBusiness(
            @PathVariable Long id,
            @RequestBody Business updatedBusiness) {
        return businessRepository.findById(id)
                .map(existing -> {
                    existing.setCompanyName((String) updatedBusiness.getCompanyName());
                    existing.setIndustry(updatedBusiness.getIndustry());
                    return ResponseEntity.ok(businessRepository.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Fshi një biznes
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBusiness(@PathVariable Long id) {
        if (businessRepository.existsById(id)) {
            businessRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
