package com.example.inlocker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.lang.String;
public class PaymentActivity extends AppCompatActivity implements View.OnClickListener {
    Spinner spinner;
    Button confirm;
    String currentDate;
    EditText Date, setTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        /*Calendar calender = Calendar.getInstance();
        currentDate = DateFormat.getDateInstance().format(calender.getTime());
        Date currentTime = Calendar.getInstance().getTime();*/
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

        setTime = (EditText) findViewById(R.id.pickUpTimeEditText);
        Date = (EditText) findViewById(R.id.pickUpDateEditText);

        //Date.setText(currentDate);

        spinner = findViewById(R.id.paymentType_confirm_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.paymentType, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        confirm = findViewById(R.id.confirm_confirmBtn);
        confirm.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.confirm_confirmBtn) {
            Intent intent = new Intent(getApplicationContext(), com.example.inlocker.GenerateQRCodeActivity.class);
            startActivity(intent);
        }
    }
}
