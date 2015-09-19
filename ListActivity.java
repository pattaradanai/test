package com.example.billz.test;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class ListActivity extends ActionBarActivity {

    private SQLiteDatabase db;
    SimpleCursorAdapter adapter;

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        super.onOptionsMenuClosed(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        db = (new ContractsDB(this).getWritableDatabase());

        Cursor cursor = readAlldata();
        adapter = new SimpleCursorAdapter(
                this,
                android.R.layout.simple_expandable_list_item_2,
                cursor,
                new String[] {ContractsDB.COL_NAME, ContractsDB.COL_PHONE_NUMBER},
                new int[] {android.R.id.text1, android.R.id.text2}
        );

        ListView list = (ListView) findViewById(R.id.listView);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                db.execSQL("DELETE FROM " + ContractsDB.TABLE_NAME+" WHERE "+ContractsDB.COL_ID+" LIKE '"+id+"%'");
                Cursor cursor = readAlldata();
                adapter.changeCursor(cursor);
            }
        });

    }

    private Cursor readAlldata() {
        String[] columns = {
                ContractsDB.COL_ID,
                ContractsDB.COL_NAME,
                ContractsDB.COL_PHONE_NUMBER
        };
        Cursor cursor = db.query(ContractsDB.TABLE_NAME, columns, null, null, null, null, null);
        return cursor;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
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
