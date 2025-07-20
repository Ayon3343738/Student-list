package com.s92076296.myexpensetracker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editAmount,editType,editID;
    Button btnAddData,btnViewAll,btnUpgradeData,btnDeleteData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb =new DatabaseHelper(this);

        editAmount=findViewById(R.id.textInputAmountText1);
        editType=findViewById(R.id.textInputExpensesText);
        editID=findViewById(R.id.textInputUDText2);
        btnAddData=findViewById(R.id.button1);
        btnViewAll=findViewById(R.id.button4);
        btnUpgradeData=findViewById(R.id.button5);
        btnDeleteData=findViewById(R.id.button6);

        addData();
        viewAll();
        upgradeData();
        deleteData();
    }
    public void addData(){
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted=myDb.insertData(editAmount.getText().toString(),editType.getText().toString());
            if(isInserted==true)
                Toast.makeText(MainActivity.this,"Data inserted",Toast.LENGTH_LONG).show();
            else
                Toast.makeText(MainActivity.this,"Data not insertes",Toast.LENGTH_LONG).show();
            }
        });
    }
    public void viewAll(){
        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor results=myDb.getAllData();
                if(results.getCount()==0){
                    showMessage("Error message :","No data about expences");
                    return;

                }
                StringBuffer buffer=new StringBuffer();
                while (results.moveToNext()){
                    buffer.append("ID of expense:"+results.getString(0)+"\n");
                    buffer.append("Amount is:"+results.getString(1)+"\n");
                    buffer.append("Type of expense:"+results.getString(2)+"\n");

                    showMessage("List of details ",buffer.toString());
                }
            }
        });
    }
    public void showMessage(String title, String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void upgradeData(){
        btnUpgradeData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpgrade=myDb.upgradeData(editID.getText().toString(),editAmount.getText().toString(),editType.getText().toString());
                if(isUpgrade==true)
                    Toast.makeText(MainActivity.this,"Data is stored",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Data is  not stored",Toast.LENGTH_LONG).show();
            }
        });
    }
    public  void deleteData(){
        btnDeleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deletedatarows=myDb.deleteData(editID.getText().toString());
                if(deletedatarows>0)
                    Toast.makeText(MainActivity.this,"That data is deleted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"That data is not deleted",Toast.LENGTH_LONG).show();
            }
        });
    }
}