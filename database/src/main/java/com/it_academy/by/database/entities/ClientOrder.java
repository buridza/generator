package com.it_academy.by.database.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "client_order", schema = "rental_company")
public class ClientOrder implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "passport_details", nullable = false)
    private String passportDetails;

    @Column(name = "admin_approval")
    private Boolean adminApproval;

    @Column(name = "notes", nullable = false)
    private String notes;

    @Column(name = "lease_period", nullable = false)
    private LocalDateTime leasePeriod;


}
