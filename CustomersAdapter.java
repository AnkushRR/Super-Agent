package com.rodewad.superagent.m_UI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.rodewad.superagent.R;
import com.rodewad.superagent.m_Model.Customer;

import java.util.ArrayList;

/**
 * Created by Ankush on 4/15/2017.
 */

public class CustomersAdapter extends BaseAdapter{Context c; ArrayList<Customer> customersArray;

    public CustomersAdapter(Context c, ArrayList<Customer> customersArray) {
        this.c = c;
        this.customersArray = customersArray;
    }


    @Override
    public int getCount() {
        return customersArray.size();
    }

    @Override
    public Object getItem(int position) {
        return customersArray.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            convertView= LayoutInflater.from(c).inflate(R.layout.list_item,parent,false);
        }
        TextView nameTxt= (TextView) convertView.findViewById(R.id.customer_name);
        TextView mobileNo= (TextView) convertView.findViewById(R.id.mobilr_no);
        TextView customerId= (TextView) convertView.findViewById(R.id.customer_id);
        TextView billingUnit= (TextView) convertView.findViewById(R.id.billing_unit);
        final Customer s= (Customer) this.getItem(position);
        nameTxt.setText(s.getName());
        mobileNo.setText((int) s.getMobileNumber());
        customerId.setText((int) s.getCustomerId());
        billingUnit.setText((int) s.getBillingUnit());

        //ONITECLICK
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(c,s.getName(),Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }
}