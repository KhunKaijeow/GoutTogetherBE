package dev.kjcoder.goutbackend.tourcompany.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("tour_companies")
public record TourCompany(
        @Column("tour_company_id")
        @Id
        Integer tourCompanyId,

        @Column("tour_company_name")
        String tourCompanyName,

        @Column("status")
        String status

) {
}
