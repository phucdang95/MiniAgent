package com.example.group8.miniagent;

import java.io.Serializable;
import java.util.GregorianCalendar;

/**
 * Created by Stefan Dragic on 11/20/2017.
 */

public class User implements Serializable {
    private String first_name;
    private String last_name;
    private GregorianCalendar DOB;
    private String adrs_street;
    private String adrs_city;
    private String adrs_state;
    private int adrs_zip;
    private String adrs_country;
    private String email;
    private String phone;

    public User(String first_name, String last_name, int DOB_yr, int DOB_mnt, int DOB_day, String adrs_street, String adrs_city, String adrs_state, int adrs_zip, String adrs_country, String email, String phone){
        this.first_name = first_name;
        this.last_name = last_name;
        this.DOB = new GregorianCalendar(DOB_yr, DOB_mnt-1, DOB_day);
        this.adrs_street = adrs_street;
        this.adrs_city = adrs_city;
        this.adrs_state = adrs_state;
        this.adrs_zip = adrs_zip;
        this.adrs_country = adrs_country;
        this.email = email;
        this.phone = phone;
    }
    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public GregorianCalendar getDOB() {
        return DOB;
    }

    public String getAdrs_city() {
        return adrs_city;
    }

    public String getAdrs_country() {
        return adrs_country;
    }

    public String getAdrs_state() {
        return adrs_state;
    }

    public String getAdrs_street() {
        return adrs_street;
    }

    public int getAdrs_zip() {
        return adrs_zip;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String returnAccount(){
        return last_name+" "+first_name;
    }

}