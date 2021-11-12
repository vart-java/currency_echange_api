package com.tagsoft.testapi.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "currency_exchange")
public class CurrencyExchangeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private long id;

    @Column(name = "base_currency")
    private String baseCurrency;

    @Column(name = "exchange_currency")
    private String exchangeCurrency;

    @Column(name = "sell_rate")
    private double sellRate;

    @Column(name = "buy_rate")
    private double buyRate;

    @Column(name = "cross_rate")
    private double crossRate;

    @Column(name = "loading_date")
    private Timestamp loadingDate;

    @Column(name = "bank_name")
    private String bankName;

    public record CurrencyPair(String baseCurrency, String exchangeCurrency){}
}
