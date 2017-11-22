package com.example.group8.miniagent;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * Created by Stefan Dragic on 11/20/2017.
 */

public class Admin extends Agent {

    public Admin(String first_name, String last_name, int DOB_yr, int DOB_mnt, int DOB_day, String adrs_street, String adrs_city, String adrs_state, int adrs_zip, String adrs_country, String email, String phone, String agnt_username, String agnt_password) {
        super(first_name, last_name, DOB_yr, DOB_mnt, DOB_day, adrs_street, adrs_city, adrs_state, adrs_zip, adrs_country, email, phone, agnt_username, agnt_password);
    }

    public void removeAgent(offLineDB oDB) {
        if (oDB.getaDBcurrent() == oDB.getAgentDB().size()) {
            //doNothing
        } else {
            int n_current = oDB.getcDBcurrent();
            oDB.getAgentDB().remove(n_current);
            oDB.setaDBcurrent(oDB.getAgentDB().size());
            removeCustomer(oDB);
        }
    }

    public void addAgent(offLineDB oDB, String f_name, String l_name, int year, int month, int day, String street, String city, String state, int zip, String country, String email, String phone, String user, String pass) {
        Agent new_account = new Agent(f_name, l_name, year, month, day, street, city, state, zip, country, email, phone, user, pass);
        if (oDB.getAgentDB().size() > 1) {
            int size = oDB.getAgentDB().size();
            for (int i = 0; i < size; i++) {
                if (new_account != null && oDB.getAgentDB().get(i).returnAccount().compareTo(new_account.returnAccount()) > 0) {
                    oDB.getAgentDB().add(i, new_account);
                    oDB.setaDBcurrent(oDB.getAgentDB().indexOf(new_account));
                    new_account = null;
                } else if (new_account != null && oDB.getAgentDB().get(i).returnAccount().compareTo(new_account.returnAccount()) < 0 && i == (size - 1)) {
                    oDB.getAgentDB().add(new_account);
                    oDB.setaDBcurrent(oDB.getAgentDB().indexOf(new_account));
                    i = size;
                }
            }
        } else if (oDB.getAgentDB().size() == 1) {
            if (oDB.getAgentDB().get(0).returnAccount().compareTo(new_account.returnAccount()) > 0) {
                oDB.getAgentDB().add(0, new_account);
                oDB.setaDBcurrent(oDB.getAgentDB().indexOf(new_account));
            } else {
                oDB.getAgentDB().add(new_account);
                oDB.setaDBcurrent(oDB.getAgentDB().indexOf(new_account));
            }
        } else {
            oDB.getAgentDB().add(new_account);
            oDB.setaDBcurrent(oDB.getAgentDB().indexOf(new_account));
        }
    }

    public void lookUp(offLineDB oDB, String ca_ID) {
        char key = ca_ID.charAt(0);
        if (key == 'c') {
            for (int i = 0; i < oDB.getAgentDB().size(); i++) {
                if (oDB.getAgentDB().get(i).getAgnt_ID().equals(ca_ID)) {
                    oDB.setcDBcurrent(i);
                    break;
                } else {
                    oDB.setcDBcurrent(oDB.getAgentDB().size());
                }
            }
        }
        else if (key == 'a') {
            for (int i = 0; i < oDB.getAgentDB().size(); i++) {
                if (oDB.getAgentDB().get(i).getAgnt_ID().equals(ca_ID)) {
                    oDB.setcDBcurrent(i);
                    break;
                } else {
                    oDB.setcDBcurrent(oDB.getAgentDB().size());
                }
            }
        }
    }
}
