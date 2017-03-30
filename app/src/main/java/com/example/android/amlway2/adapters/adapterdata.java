package com.example.android.amlway2.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.amlway2.R;
import com.example.android.amlway2.data.ContractBill;
import com.example.android.amlway2.models.billModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Abdulrhman on 23/03/2017.
 */


public class adapterdata extends RecyclerView.Adapter<adapterdata.holderBills>  {

    Context context;
    Cursor mCursor;

public interface onmyItemClickListener{
    public void onmyclick(billModel model);
}
    public adapterdata(Context context) {
        this.context = context;
    }

    @Override
    public holderBills onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_bill, parent, false);
        return new holderBills(view);
    }

    @Override
    public void onBindViewHolder(holderBills holder, int position) {

        mCursor.moveToPosition(position);

        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
        String date = df.format(Calendar.getInstance().getTime());

        String employeeName = mCursor.getString(mCursor.getColumnIndex(ContractBill.billEntry.COLUMN_EMPLOYEE_NAME));
        String customerName = mCursor.getString(mCursor.getColumnIndex(ContractBill.billEntry.COLUMN_CUSTOMER_NAME));
        double price = mCursor.getDouble(mCursor.getColumnIndex(ContractBill.billEntry.COLUMN_PRICE));
        int id = mCursor.getInt(mCursor.getColumnIndex(ContractBill.billEntry._ID));

        holder.itemView.setTag(id);
        holder.date.setText(date);
        holder.employeeName.setText(employeeName);
        holder.customerName.setText(customerName);
        holder.price.setText(String.valueOf(price));
    }

    @Override
    public int getItemCount() {

        if (mCursor == null)
            return 0;

        return mCursor.getCount();
    }

    public class holderBills extends RecyclerView.ViewHolder {

        TextView customerName;
        TextView date;
        TextView employeeName;
        TextView price;

        public holderBills(View itemView) {
            super(itemView);
            customerName = (TextView) itemView.findViewById(R.id.name_customer);
            date = (TextView) itemView.findViewById(R.id.datebill);
            employeeName = (TextView) itemView.findViewById(R.id.employee_name);
            price = (TextView) itemView.findViewById(R.id.overalPrice);
        }
    }

    public Cursor swapCurosr(Cursor cursor) {
        if (mCursor == cursor) {
            return null;
        }
        if (!cursor.equals(null))
            this.notifyDataSetChanged();

        mCursor = cursor;
        return mCursor;
    }

}
