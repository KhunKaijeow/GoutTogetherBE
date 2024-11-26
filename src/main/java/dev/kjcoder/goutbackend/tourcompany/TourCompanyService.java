package dev.kjcoder.goutbackend.tourcompany;

import dev.kjcoder.goutbackend.tourcompany.dto.RegisterTourCompanyDto;
import dev.kjcoder.goutbackend.tourcompany.model.TourCompany;

public interface TourCompanyService {

    TourCompany registerTourCompany(RegisterTourCompanyDto registerTourCompanyDto);

    TourCompany approvedTourCompany(Integer id);
}
