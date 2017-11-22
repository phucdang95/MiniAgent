package com.example.group8.miniagent;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Stefan Dragic on 11/20/2017.
 */

public class offLineDB {
    private int aDBcurrent;
    private int cDBcurrent;
    private LinkedList<Agent> agentDB;
    private LinkedList<Customer> customerDB;

    public offLineDB (List<Agent> agentDB, List<Customer> customerDB){
        this.agentDB = (LinkedList<Agent>) agentDB;
        this.customerDB = (LinkedList<Customer>) customerDB;
        cDBcurrent = 0;
        aDBcurrent = 0;

    }

    public LinkedList<Agent> getAgentDB() {
        return agentDB;
    }

    public LinkedList<Customer> getCustomerDB() {
        return customerDB;
    }

    public int getaDBcurrent() {
        return aDBcurrent;
    }

    public int getcDBcurrent() {
        return cDBcurrent;
    }

    public void setcDBcurrent(int cDBcurrent) {
        this.cDBcurrent = cDBcurrent;
    }

    public void setaDBcurrent(int aDBcurrent) {
        this.aDBcurrent = aDBcurrent;
    }
}
