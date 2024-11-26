package dev.kjcoder.goutbackend.tourcompany;

import dev.kjcoder.goutbackend.tourcompany.model.TourCompany;
import dev.kjcoder.goutbackend.tourcompany.model.TourCompanyDto;

public interface TourCompanyService {

    TourCompany registerTourCompany(TourCompanyDto tourCompanyDto);

    TourCompany approvedTourCompany(Integer id);
}
