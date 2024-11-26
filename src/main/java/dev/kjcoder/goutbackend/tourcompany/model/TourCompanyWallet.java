package dev.kjcoder.goutbackend.tourcompany.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.Instant;

@Table("tours_companies_wallet")
public record TourCompanyWallet(

        @Id
        @Column("tour_company_wallet_id")
        Integer tourCompanyWalletId,

        @Column("tour_company_id")
        AggregateReference<TourCompany, Integer> tourCompanyId,

        @Column("balance")
        BigDecimal balance,

        @Column("last_updated")
        Instant lastUpdated
) {

}
