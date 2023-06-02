package io.sippbot.assignment.jobs.models.entities;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "job_records")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JobRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "timestamp")
    private LocalDateTime createDate;

    @Column
    private String employer;

    @Column
    private String location;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "year_of_service")
    private Double yearOfService;

    @Column(name = "year_of_exp")
    private Double yearOfExperience;

    @Column
    private Double salary;
    @Column(name = "signing_bonus")
    private Double signingBonus;
    @Column(name = "annual_bonus")
    private Double annualBonus;
    @Column(name = "annual_stock_bonus")
    private Double annualStockBonus;
    @Column
    private Gender gender;
    @Column(name = "additional_comment")
    private String additionalComment;
}
