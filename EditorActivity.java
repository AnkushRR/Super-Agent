package com.rodewad.superagent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class EditorActivity extends AppCompatActivity {

    EditText etName;
    EditText etMobileNumber;
    EditText etCustomerId;
    EditText etBillingUnit;
    Button saveBtn;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    DatabaseReference Customer;
    DatabaseReference name;
    DatabaseReference mobile_number;
    DatabaseReference consumer_id;
    DatabaseReference billing_unit;

    String id;
    TextView id_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        etName = (EditText) findViewById(R.id.editText);
        etMobileNumber = (EditText) findViewById(R.id.mobile_number);
        etCustomerId = (EditText) findViewById(R.id.customer_id);
        etBillingUnit = (EditText) findViewById(R.id.billing_unit);

        saveBtn = (Button) findViewById(R.id.savebtn);

        id_text = (TextView) findViewById(R.id.textVi);



        // Write a message to the database

        myRef = database.getReference("Customer Entries");
        Customer = myRef.child("Customer");
        name = Customer.child("name");
        mobile_number = Customer.child("Mobile Number: ");
        consumer_id = Customer.child("Customer Id: ");
        billing_unit = Customer.child("Mobile Number: ");


    }

    public void saveData (View view){
        String Name= etName.getText().toString();
        long MobileNumber = Long.parseLong(etMobileNumber.getText().toString());
        long CustomerId = Long.parseLong(etCustomerId.getText().toString());
        int billingUnit = Integer.parseInt(etBillingUnit.getText().toString());

        ArrayList<String> details = new ArrayList<>();
        details.add(Name);
        details.add(String.valueOf(MobileNumber));
        details.add(String.valueOf(CustomerId));
        details.add(String.valueOf(billingUnit));

        Customer.push().setValue(details);
        id = String.valueOf(Customer.push());
        id_text.setText(id);

    }
}
