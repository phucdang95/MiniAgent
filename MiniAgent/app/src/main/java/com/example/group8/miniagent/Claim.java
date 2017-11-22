package com.example.group8.miniagent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Stefan Dragic on 11/20/2017.
 */

public class Claim {

    private static final AtomicInteger count = new AtomicInteger(0);
    private String claim_id;

    public Claim() {
        this.claim_id = "R"+String.format("%010d", count.incrementAndGet());
    }
}
