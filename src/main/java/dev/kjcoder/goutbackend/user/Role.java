package dev.kjcoder.goutbackend.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("roles")
public record Role(
        @Id
        @Column("role_id")
        Integer id,
        @Column("role_name")
        String name
) {
}
