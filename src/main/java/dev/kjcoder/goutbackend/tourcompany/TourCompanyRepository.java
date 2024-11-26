package dev.kjcoder.goutbackend.tourcompany;

import dev.kjcoder.goutbackend.tourcompany.model.TourCompany;
import org.springframework.data.repository.CrudRepository;

public interface TourCompanyRepository extends CrudRepository<TourCompany, Integer > {
}
