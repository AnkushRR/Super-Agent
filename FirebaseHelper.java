package com.rodewad.superagent.m_FireBase;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.rodewad.superagent.m_Model.Customer;

import java.util.ArrayList;

/**
 * Created by Ankush on 4/16/2017.
 */
public class FirebaseHelper {
    DatabaseReference CustomerEntry;
    Boolean saved;
    ArrayList<Customer> customersArray = new ArrayList<>();

     /*
 PASS DATABASE REFRENCE
  */
     public FirebaseHelper(DatabaseReference CustomerEntry){
         this.CustomerEntry= CustomerEntry;
     }

    //WRITE IF NOT NULL
    public Boolean save(Customer customer)
    {
        if(customer==null)
        {
            saved=false;
        }else
        {
            try
            {
                CustomerEntry.child("Customer").push().setValue(customer);
                saved=true;
            }catch (DatabaseException e)
            {
                e.printStackTrace();
                saved=false;
            }
        }
        return saved;
    }

    //IMPLEMENT FETCH DATA AND FILL ARRAYLIST
    private void fetchData(DataSnapshot dataSnapshot)
    {
        customersArray.clear();
        for (DataSnapshot ds : dataSnapshot.getChildren())
        {
            Customer spacecraft=ds.getValue(Customer.class);
            customersArray.add(spacecraft);
        }
    }


    //RETRIEVE
    public ArrayList<Customer> retrieve()
    {
        CustomerEntry.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        return customersArray;
    }


}
