package dev.kjcoder.goutbackend.tourcompany;

import dev.kjcoder.goutbackend.tourcompany.model.RegisterTourCompanyDto;
import dev.kjcoder.goutbackend.tourcompany.model.TourCompany;

public interface TourCompanyService {

    TourCompany registerTourCompany(RegisterTourCompanyDto registerTourCompanyDto);

    TourCompany approvedTourCompany(Integer id);
}
