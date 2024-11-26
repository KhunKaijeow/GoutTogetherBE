package dev.kjcoder.goutbackend.tourcompany;

import dev.kjcoder.goutbackend.common.exception.EntityNotFound;
import dev.kjcoder.goutbackend.tourcompany.model.TourCompany;
import dev.kjcoder.goutbackend.tourcompany.dto.RegisterTourCompanyDto;
import dev.kjcoder.goutbackend.common.enumeration.TourCompanyStatus;
import dev.kjcoder.goutbackend.tourcompany.model.TourCompanyLogin;
import dev.kjcoder.goutbackend.tourcompany.model.TourCompanyWallet;
import dev.kjcoder.goutbackend.tourcompany.repository.TourCompanyLoginRepository;
import dev.kjcoder.goutbackend.tourcompany.repository.TourCompanyRepository;
import dev.kjcoder.goutbackend.tourcompany.repository.TourCompanyWalletRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;

@Service
public class TourCompanyServiceImpl implements TourCompanyService{

    private static final Logger logger = LoggerFactory.getLogger(TourCompanyServiceImpl.class);

    private final TourCompanyRepository tourCompanyRepository;
    private final TourCompanyLoginRepository tourCompanyLoginRepository;
    private final TourCompanyWalletRepository tourCompanyWalletRepository;

    private final PasswordEncoder passwordEncoder;

    public TourCompanyServiceImpl(TourCompanyRepository tourCompanyRepository, TourCompanyLoginRepository tourCompanyLoginRepository, TourCompanyWalletRepository tourCompanyWalletRepository, PasswordEncoder passwordEncoder) {
        this.tourCompanyRepository = tourCompanyRepository;
        this.tourCompanyLoginRepository = tourCompanyLoginRepository;
        this.tourCompanyWalletRepository = tourCompanyWalletRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public TourCompany registerTourCompany(RegisterTourCompanyDto registerTourCompanyDto) {
        logger.debug("[registerTourCompany] newly tour company is registering...");
        var companyName = registerTourCompanyDto.tourCompanyName();
        var tourCompany = new TourCompany(null,companyName, TourCompanyStatus.WAITING.name());
        var newTourCompany = tourCompanyRepository.save(tourCompany);
        logger.debug("[registerTourCompany] new tour company is registered: {}", newTourCompany);
        createCompanyCredential(newTourCompany,registerTourCompanyDto);
        return newTourCompany;

    }

    @Override
    @Transactional
    public TourCompany approvedTourCompany(Integer id) {
        var tourCompany =  tourCompanyRepository.findById(id)
                .orElseThrow( () -> new EntityNotFound("Tour company not found with ID: " + id));
        tourCompany = new TourCompany(id, tourCompany.tourCompanyName(), TourCompanyStatus.APPROVED.name());
        logger.debug("[approvedTourCompany] tour company ID: {} is approved", id);
        var updatedTourCompany =  tourCompanyRepository.save(tourCompany);
        createdTourCompanyWallet(updatedTourCompany);
        return updatedTourCompany;

    }

    private void createCompanyCredential(TourCompany tourCompany, RegisterTourCompanyDto registerTourCompanyDto) {
        AggregateReference<TourCompany, Integer> tourCompanyReference = AggregateReference.to(tourCompany.tourCompanyId());
        var encryptedPassword = passwordEncoder.encode(registerTourCompanyDto.password());
        var tourCompanyCredential = new TourCompanyLogin(null, tourCompanyReference, registerTourCompanyDto.username(), encryptedPassword);
        tourCompanyLoginRepository.save(tourCompanyCredential);
        logger.info("[createCompanyCredential] new tour company credential is created in ID: {}", tourCompanyReference.getId());
    }

    private void createdTourCompanyWallet(TourCompany tourCompany) {
        AggregateReference<TourCompany, Integer> tourCompanyReference = AggregateReference.to(tourCompany.tourCompanyId());
        Instant currentDateTimestamp = Instant.now();
        BigDecimal initialBalance = new BigDecimal("0.0");
        var tourCompanyWallet = new TourCompanyWallet(null, tourCompanyReference, initialBalance, currentDateTimestamp);
        tourCompanyWalletRepository.save(tourCompanyWallet);
        logger.info("[createdTourCompanyWallet] new tour company wallet is created in ID: {}", tourCompanyReference.getId());
    }
}
