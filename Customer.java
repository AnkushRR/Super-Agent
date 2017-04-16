package com.rodewad.superagent.m_Model;

/**
 * Created by Ankush on 4/15/2017.
 */

public class Customer {
    String name;
    long mobileNumber, customerId, billingUnit;
    public Customer() {
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public long getMobileNumber(){
        return mobileNumber;
    }
    public void setMobileNumber(long mobileNumber){
        this.mobileNumber = mobileNumber;
    }
    public long getCustomerId(){
        return customerId;
    }
    public void setCustomerId(long customerId){
        this.customerId = customerId;
    }

    public long getBillingUnit(){
        return billingUnit;
    }
    public void setBillingUnit(long billingUnit){
        this.billingUnit = billingUnit;
    }

}


