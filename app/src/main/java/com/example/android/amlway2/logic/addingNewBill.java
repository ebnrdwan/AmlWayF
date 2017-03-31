package com.example.android.amlway2.logic;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.amlway2.R;
import com.example.android.amlway2.data.ContractBill.billEntry;

public class addingNewBill extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    AutoCompleteTextView cutomername;
    AutoCompleteTextView element;
    EditText quantity;
    EditText price;
    EditText weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addingbill);

        cutomername = (AutoCompleteTextView) findViewById(R.id.customer);
        element = (AutoCompleteTextView) findViewById(R.id.element);
        quantity = (EditText) findViewById(R.id.quantity);
        price = (EditText) findViewById(R.id.price);
        weight = (EditText) findViewById(R.id.weight);


        Button doneButton = (Button) findViewById(R.id.doneButton);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveAbill();
                finish();
            }
        });
    }


    public void saveAbill() {

        String customer_name = cutomername.getText().toString();
        String element_name = element.getText().toString();
        String quanitiy_value = quantity.getText().toString();
        String price_value = price.getText().toString();
        String weight_value = weight.getText().toString();
        String type = "sell bill";
        String from = " main store";
        String to = "car 1";

        ContentValues contentValues = new ContentValues();
        contentValues.put(billEntry.COLUMN_CUSTOMER_NAME, customer_name);
        contentValues.put(billEntry.COLUMN_EMPLOYEE_NAME,"Ahmed");
        contentValues.put(billEntry.COLUMN_ELEMENT, element_name);
        contentValues.put(billEntry.COLUMN_WEIGHT, weight_value);
        contentValues.put(billEntry.COLUMN_PRICE, price_value);
        contentValues.put(billEntry.COLUMN_COUNT, quanitiy_value);
        contentValues.put(billEntry.COLUMN_Bill_TYPE, type);
        contentValues.put(billEntry.COLUMN_FROM, from);
        contentValues.put(billEntry.COLUMN_TO, to);
        getContentResolver().insert(billEntry.CONTENT_URI, contentValues);
        getContentResolver().notifyChange(billEntry.CONTENT_URI, null);


    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this,billEntry.CONTENT_URI,null,null,null,null);
    }


    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        if (data==null || data.getCount()<1){
            return ;
        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
