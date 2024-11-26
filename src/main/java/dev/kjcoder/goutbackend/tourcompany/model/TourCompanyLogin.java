package dev.kjcoder.goutbackend.tourcompany.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("tour_companies_login")
public record TourCompanyLogin(
        @Id
        @Column("tour_company_login_id")
        Integer tourCompanyLoginId,

        @Column("tour_company_id")
        AggregateReference<TourCompany, Integer> tourCompanyId,

        @Column("username")
        String username,

        @Column("password")
        String password
) {
}
