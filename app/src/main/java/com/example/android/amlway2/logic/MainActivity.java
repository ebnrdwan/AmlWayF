package com.example.android.amlway2.logic;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.amlway2.R;
import com.example.android.amlway2.adapters.adapterdata;
import com.example.android.amlway2.data.ContractBill;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    RecyclerView recyclerView;
    adapterdata adapter;
    TextView textView;
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int TASK_LOADER_ID = 0;
    Toast toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
       toast = new Toast(MainActivity.this);
        recyclerView = (RecyclerView) findViewById(R.id.recMain);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

//        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
//            @Override
//            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
//                // do it
//                RecyclerView.ViewHolder holder ;
//                int id = holder.itemView.getId();
//                Toast.makeText(MainActivity.this,"you selected "+ id,Toast.LENGTH_SHORT).show();
//            }
//
//
//        });
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int position, View v) {
//                int  = viewHolder.itemView.getId();
                Cursor mCursor = getContentResolver().query(ContractBill.billEntry.CONTENT_URI, null, null, null, null);
                mCursor.moveToPosition(position);
                int id = mCursor.getInt(mCursor.getColumnIndex(ContractBill.billEntry._ID));

                if (toast!=null){
                    toast.cancel();
                }
                toast.makeText(MainActivity.this,"you selected "+ id,Toast.LENGTH_SHORT).show();
            }
        });

        ItemClickSupport.addTo(recyclerView).setOnItemLongClickListener(new ItemClickSupport.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClicked(RecyclerView recyclerView, int position, View v) {
                Cursor mCursor = getContentResolver().query(ContractBill.billEntry.CONTENT_URI, null, null, null, null);
                mCursor.moveToPosition(position);
                int id = mCursor.getInt(mCursor.getColumnIndex(ContractBill.billEntry._ID));

                if (toast!=null){
                    toast.cancel();
                }
                startActivity(new Intent(MainActivity.this,addingNewBill.class));
                toast.makeText(MainActivity.this,"looooong press on item "+ id,Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        FloatingActionButton actionButton = (FloatingActionButton) findViewById(R.id.mainAction);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, addingNewBill.class));
            }
        });

//        String date, String customerName, String employeeName, String from, String to, double price, double weight, String element, String type, String quantity

        adapter = new adapterdata(MainActivity.this);
        recyclerView.setAdapter(adapter);

        getSupportLoaderManager().initLoader(TASK_LOADER_ID, null, this);

    }

//here you RE
    public void display(TextView textView) {

        Cursor mCursor = getContentResolver().query(ContractBill.billEntry.CONTENT_URI, null, null, null, null);
        if (mCursor.moveToFirst()) {
            while (mCursor.moveToNext()) {
                String employeeName = mCursor.getString(mCursor.getColumnIndex(ContractBill.billEntry.COLUMN_EMPLOYEE_NAME));
                String customerName = mCursor.getString(mCursor.getColumnIndex(ContractBill.billEntry.COLUMN_CUSTOMER_NAME));
                double price = mCursor.getDouble(mCursor.getColumnIndex(ContractBill.billEntry.COLUMN_PRICE));
                int id = mCursor.getInt(mCursor.getColumnIndex(ContractBill.billEntry._ID));

                textView.append( id +" "+ employeeName+" "+customerName+" "+price+" ");


            }

        }

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {


        return new AsyncTaskLoader<Cursor>(this) {
            Cursor billCursor = null;

            @Override
            protected void onStartLoading() {
                if (billCursor != null) {
                    dliverResults(billCursor);
                } else {
                    forceLoad();

                }
            }

            @Override
            public Cursor loadInBackground() {

                try {
                    return getContentResolver().query(ContractBill.billEntry.CONTENT_URI, null, null, null, null);

                } catch (Exception e) {
                    return null;
                }
            }

            public void dliverResults(Cursor cursor) {
                billCursor = cursor;
                super.deliverResult(billCursor);
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCurosr(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCurosr(null);
    }
}
