package com.it_academy.by.database.entities;

import lombok.*;
import javax.persistence.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@Builder
@Entity
@Table(name = "passport", schema = "rental_company")
public class Passport implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "series", nullable = false)
    private String series;

    @Column(name = "number", nullable = false, unique = true)
    private String number;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User owner;
}
