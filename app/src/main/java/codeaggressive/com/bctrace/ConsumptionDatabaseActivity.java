package codeaggressive.com.bctrace;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConsumptionDatabaseActivity extends AppCompatActivity {
    Button generatedata;
    DatabaseHelper myDB;
    ListView datalist;
    ArrayList<String> data_list;
    ArrayAdapter dataAdpater;
    String ip;

//    private static final String url = "http://192.168.2.219/PhP/retrive_consume.php";
    public static final String BATCH = "batch";
    public static final String MIXER = "mixer";
    public static final String SKU = "sku";
    public static final String KEGS = "kegs";
    public static final String DATE = "c_date";
    public static final String TIME = "c_time";
    public static final String OPERATOR = "operator";
    public static final String BIN = "bin";
    public static final String DATERESULT = "dateResult";
    public static final String TIMERESULT = "timeResult";
    public static final String USAGETYPE = "usages";
    public static final String MACHINE = "machine";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumption_database);

        ip = getResources().getString(R.string.ip);
        datalist = findViewById(R.id.condataList);
        generatedata = findViewById(R.id.generateDate);
        myDB = new DatabaseHelper(this);

        data_list = new ArrayList<>();

        generatedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConsumptionDatabaseActivity.this, DatabaseActivity.class);
                startActivity(intent);
                finish();
            }
        });

//        ViewData();
        ViewConsumptionData();
    }

    private void ViewConsumptionData() {

        StringRequest request = new StringRequest(Request.Method.POST, "http://"+ip+"/PhP/retrive_consume.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                showJSON(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ConsumptionDatabaseActivity.this, "Invalid Network Connection", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }

    private void showJSON(String response) {
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray("result");

            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                String batch = jo.getString(BATCH);
                String mixer = jo.getString(MIXER);
                String sku = jo.getString(SKU);
                String kegs = jo.getString(KEGS);
                String date = jo.getString(DATE);
                String time = jo.getString(TIME);
                String operator = jo.getString(OPERATOR);
                String bin = jo.getString(BIN);
                String dateResult = jo.getString(DATERESULT);
                String timeResult = jo.getString(TIMERESULT);
                String usageType = jo.getString(USAGETYPE);
                String machine = jo.getString(MACHINE);

                final HashMap<String, String> datas = new HashMap<>();
                datas.put(BATCH, batch);
                datas.put(MIXER, mixer);
                datas.put(SKU, sku);
                datas.put(KEGS, kegs);
                datas.put(DATE, date);
                datas.put(TIME, time);
                datas.put(OPERATOR, operator);
                datas.put(BIN, bin);
                datas.put(DATERESULT, dateResult);
                datas.put(TIMERESULT, timeResult);
                datas.put(USAGETYPE, usageType);
                datas.put(MACHINE, machine);

                list.add(datas);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        ListAdapter adapter = new SimpleAdapter(
                ConsumptionDatabaseActivity.this, list, R.layout.data_selected_item,
                new String[]{BATCH, SKU, MIXER, OPERATOR},
                new int[]{R.id.data_text, R.id.sku_text, R.id.mixer_text, R.id.operator_text}
        );

        datalist.setAdapter(adapter);
        datalist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String datas = datalist.getItemAtPosition(position).toString();
                AlertDialog.Builder listdatas = new AlertDialog.Builder(view.getContext());
                listdatas.setMessage(datas);
                listdatas.setCancelable(true);
                listdatas.setPositiveButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog result = listdatas.create();
                result.show();
            }
        });

    }

    private void ViewData() {
        Cursor cursor = myDB.retrieveConsumeData();
        if (cursor.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "No Data Available", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                data_list.add(cursor.getString(0) + " - " + cursor.getString(1) + " - " + cursor.getString(2));
            }
            dataAdpater = new ArrayAdapter<>(this, R.layout.data_selected_item, data_list);
            datalist.setAdapter(dataAdpater);
            datalist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String datas = datalist.getItemAtPosition(position).toString();
                    AlertDialog.Builder listdatas = new AlertDialog.Builder(view.getContext());
                    listdatas.setMessage(datas);
                    listdatas.setCancelable(true);
                    listdatas.setPositiveButton("Close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    AlertDialog result = listdatas.create();
                    result.show();
                }
            });

        }
    }
}