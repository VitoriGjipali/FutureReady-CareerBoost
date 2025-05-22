package org.futureready.futurereadycareerboost.service;

import lombok.RequiredArgsConstructor;
import org.futureready.futurereadycareerboost.entity.Business;
import org.futureready.futurereadycareerboost.entity.Job;
import org.futureready.futurereadycareerboost.entity.JobApplication;
import org.futureready.futurereadycareerboost.repository.BusinessRepository;
import org.futureready.futurereadycareerboost.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor

public class BusinessService {

    private final BusinessRepository businessRepository;
    private final JobRepository jobRepository;



    // Merr të gjithë bizneset
    public List<Business> getAllBusinesses() {
        return businessRepository.findAll();
    }

    // Merr një biznes sipas ID
    public Business getBusinessById(Long id) {
        return businessRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Business not found"));
    }

    // Shton një biznes të ri
    public Business createBusiness(Business business) {
        return businessRepository.save(business);
    }

    // Përditëson një biznes ekzistues
    public Business updateBusiness(Long id, Business updatedBusiness) {
        Business existing = getBusinessById(id);
        existing.setCompanyName((String) updatedBusiness.getCompanyName());
        existing.setIndustry(updatedBusiness.getIndustry());
        return businessRepository.save(existing);
    }

    // Fshin një biznes
    public void deleteBusiness(Long id) {
        if (businessRepository.existsById(id)) {
            businessRepository.deleteById(id);
        } else {
            throw new RuntimeException("Business not found");
        }
    }
}
