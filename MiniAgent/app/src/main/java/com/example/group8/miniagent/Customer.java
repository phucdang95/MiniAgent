package com.example.group8.miniagent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Stefan Dragic on 11/20/2017.
 */

public class Customer extends User {

    private final static AtomicInteger count = new AtomicInteger(0);
    private String cstmr_ID;
    private int crdt_score;
    private Claim[] cstmr_claims;

    public Customer(String first_name, String last_name, int DOB_yr, int DOB_mnt, int DOB_day, String adrs_street, String adrs_city, String adrs_state, int adrs_zip, String adrs_country, String email, String phone, int crdt_score) {
        super(first_name, last_name, DOB_yr, DOB_mnt, DOB_day, adrs_street, adrs_city, adrs_state, adrs_zip, adrs_country, email, phone);
        this.crdt_score = crdt_score;
        this.cstmr_ID = "C"+String.format("%06d", count.incrementAndGet());
        this.cstmr_claims = new Claim[5];
    }

    public int getCrdt_score() {
        return crdt_score;
    }

    public String getCstmr_ID() {
        return cstmr_ID;
    }

}
