package com.rodewad.superagent;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rodewad.superagent.m_FireBase.FirebaseHelper;
import com.rodewad.superagent.m_Model.Customer;
import com.rodewad.superagent.m_UI.CustomersAdapter;

public class MainActivity extends AppCompatActivity {
    DatabaseReference CustomerEntry;
    FirebaseHelper helper;
    CustomersAdapter adapter;
    ListView lv;
    EditText nameEditTxt, mobileno, customerid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        lv = (ListView) findViewById(R.id.listedview);
        //INITIALIZE FIREBASE DB
        CustomerEntry = FirebaseDatabase.getInstance().getReference();
        helper = new FirebaseHelper(CustomerEntry);
        //ADAPTER
        adapter = new CustomersAdapter(this, helper.retrieve());
        lv.setAdapter(adapter);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayInputDialog();
            }
        });
    }
    //DISPLAY INPUT DIALOG
    private void displayInputDialog() {
        Dialog d = new Dialog(this);
        d.setTitle("Save To Firebase");
        d.setContentView(R.layout.activity_input_dialog);
        nameEditTxt = (EditText) d.findViewById(R.id.nameEditText);
        mobileno = (EditText) d.findViewById(R.id.mobileNumberEditText);
        customerid = (EditText) d.findViewById(R.id.customerIdEditTExt);

        Button saveBtn = (Button) d.findViewById(R.id.saveBtn);
        //SAVE
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //GET DATA
                String name = nameEditTxt.getText().toString();
                long mobileNumber = Long.parseLong(mobileno.getText().toString());
                long customerId =Long.parseLong(customerid.getText().toString());
                //SET DATA
                Customer s = new Customer();
                s.setName(name);
                s.setMobileNumber((mobileNumber));
                s.setCustomerId(customerId);
                //SIMPLE VALIDATION
                if (name != null && name.length() > 0) {
                    //THEN SAVE
                    if (helper.save(s)) {
                        //IF SAVED CLEAR EDITXT
                        nameEditTxt.setText("");
                        mobileno.setText("");
                        adapter = new CustomersAdapter(MainActivity.this, helper.retrieve());
                        lv.setAdapter(adapter);
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Name Must Not Be Empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
        d.show();
    }
}
