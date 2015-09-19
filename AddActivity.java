package com.example.billz.test;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class AddActivity extends ActionBarActivity {

    private ContractsDB dbContacts;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        dbContacts = new ContractsDB(getApplicationContext());
        db = dbContacts.getWritableDatabase();

        Button addBtn = (Button) findViewById(R.id.add2_button);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nameValue = (EditText) findViewById(R.id.edit_name);
                String name = nameValue.getText().toString();
                EditText phoneValue = (EditText) findViewById(R.id.edit_phone_number);
                String phoneNumber = phoneValue.getText().toString();
                ContentValues cv = new ContentValues();
                cv.put(ContractsDB.COL_NAME, name);
                cv.put(ContractsDB.COL_PHONE_NUMBER, name);
                db.insert(ContractsDB.TABLE_NAME, null, cv);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}