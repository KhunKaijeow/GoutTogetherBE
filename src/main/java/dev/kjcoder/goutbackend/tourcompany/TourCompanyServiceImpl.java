package dev.kjcoder.goutbackend.tourcompany;

import dev.kjcoder.goutbackend.common.exception.EntityNotFound;
import dev.kjcoder.goutbackend.tourcompany.model.TourCompany;
import dev.kjcoder.goutbackend.tourcompany.model.TourCompanyDto;
import dev.kjcoder.goutbackend.common.enumeration.TourCompanyStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TourCompanyServiceImpl implements TourCompanyService{

    private static final Logger logger = LoggerFactory.getLogger(TourCompanyServiceImpl.class);

    private final TourCompanyRepository tourCompanyRepository;

    public TourCompanyServiceImpl(TourCompanyRepository tourCompanyRepository) {
        this.tourCompanyRepository = tourCompanyRepository;
    }

    @Override
    public TourCompany registerTourCompany(TourCompanyDto tourCompanyDto) {
        logger.debug("[registerTourCompany] newly tour company is registering...");
        var companyName = tourCompanyDto.tourCompanyName();
        var tourCompany = new TourCompany(null,companyName, TourCompanyStatus.WAITING.name());
        var newTourCompany = tourCompanyRepository.save(tourCompany);
        logger.debug("[registerTourCompany] new tour company is registered: {}", newTourCompany);
        return newTourCompany;

    }

    @Override
    public TourCompany approvedTourCompany(Integer id) {
        var tourCompany =  tourCompanyRepository.findById(id)
                .orElseThrow( () -> new EntityNotFound("Tour company not found with ID: " + id));
        tourCompany = new TourCompany(id, tourCompany.tourCompanyName(), TourCompanyStatus.APPROVED.name());
        logger.debug("[approvedTourCompany] tour company ID: {} is approved", id);
        return tourCompanyRepository.save(tourCompany);
    }
}
