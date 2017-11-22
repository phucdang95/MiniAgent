package com.example.group8.miniagent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Stefan Dragic on 11/20/2017.
 */

public class Agent extends User {

    private static final AtomicInteger count = new AtomicInteger(0);
    private String agnt_ID;
    private String agnt_username;
    private String agnt_password;
    private double pay_rate;

    public Agent(String first_name, String last_name, int DOB_yr, int DOB_mnt, int DOB_day, String adrs_street, String adrs_city, String adrs_state, int adrs_zip, String adrs_country, String email, String phone, String agnt_username, String agnt_password) {
        super(first_name, last_name, DOB_yr, DOB_mnt, DOB_day, adrs_street, adrs_city, adrs_state, adrs_zip, adrs_country, email, phone);
        this.agnt_ID = "A"+String.format("%06d", count.incrementAndGet());
        this.agnt_username = agnt_username;
        this.agnt_password = agnt_password;
        this.pay_rate = 10.00;
    }

    public String getAgnt_ID() {
        return agnt_ID;
    }

    public String getAgnt_username() {
        return agnt_username;
    }

    public String getAgnt_password() {
        return agnt_password;
    }

    public double getPay_rate() {
        return pay_rate;
    }

    public String printRoll(){
        return "Agent";
    }

    public void decrement(){
        count.decrementAndGet();
    }

    public void removeCustomer(offLineDB oDB) {
        if (oDB.getcDBcurrent() == oDB.getCustomerDB().size()) {
            //doNothing
        } else {
            int n_current = oDB.getcDBcurrent();
            oDB.getCustomerDB().remove(n_current);
            oDB.setcDBcurrent(oDB.getCustomerDB().size());
            removeCustomer(oDB);
        }
    }

    public void addCustomer(offLineDB oDB, String f_name, String l_name, int year, int month, int day, String street, String city, String state, int zip, String country, String email, String phone, int c_score) {
        Customer new_account = new Customer(f_name, l_name, year, month, day, street, city, state, zip, country, email, phone, c_score);
        if (oDB.getCustomerDB().size() > 1) {
            int size = oDB.getCustomerDB().size();
            for (int i = 0; i < size; i++) {
                if (new_account != null && oDB.getCustomerDB().get(i).returnAccount().compareTo(new_account.returnAccount()) > 0) {
                    oDB.getCustomerDB().add(i, new_account);
                    oDB.setcDBcurrent(oDB.getCustomerDB().indexOf(new_account));
                    new_account = null;
                } else if (new_account != null && oDB.getCustomerDB().get(i).returnAccount().compareTo(new_account.returnAccount()) < 0 && i == (size - 1)) {
                    oDB.getCustomerDB().add(new_account);
                    oDB.setcDBcurrent(oDB.getCustomerDB().indexOf(new_account));
                    i = size;
                }
            }
        } else if (oDB.getCustomerDB().size() == 1) {
            if (oDB.getCustomerDB().get(0).returnAccount().compareTo(new_account.returnAccount()) > 0) {
                oDB.getCustomerDB().add(0, new_account);
                oDB.setcDBcurrent(oDB.getCustomerDB().indexOf(new_account));
            } else {
                oDB.getCustomerDB().add(new_account);
                oDB.setcDBcurrent(oDB.getCustomerDB().indexOf(new_account));
            }
        } else {
            oDB.getCustomerDB().add(new_account);
            oDB.setcDBcurrent(oDB.getCustomerDB().indexOf(new_account));
        }
    }

    public void lookUp(offLineDB oDB, String cID) {
        for (int i = 0; i < oDB.getCustomerDB().size(); i++) {
            if (oDB.getCustomerDB().get(i).getCstmr_ID().equals(cID)) {
                oDB.setcDBcurrent(i);
                break;
            }else{
                oDB.setcDBcurrent(oDB.getCustomerDB().size());
            }
        }
    }

    public void returnAccount(offLineDB oDB, int i){
        System.out.println(oDB.getCustomerDB().get(i).getFirst_name()+" "+oDB.getCustomerDB().get(i).getLast_name()+" "+oDB.getCustomerDB().get(i).getCstmr_ID());
    }


}

