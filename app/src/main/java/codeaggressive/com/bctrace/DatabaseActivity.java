package codeaggressive.com.bctrace;

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

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

public class DatabaseActivity extends AppCompatActivity {
    Button consumedata;
    DatabaseHelper myDB;
    ListView datalist;
    ArrayList<String> data_list;
    ArrayAdapter dataAdpater;
    GenerateAdapter generateAdapter;
    String ip;

    public static ArrayList<Datas> datasArrayList = new ArrayList<>();

//    private static final String url = "http://192.168.2.219/PhP/retrive_generate.php";
    public static final String BATCH = "batch";
    public static final String MIXER = "mixer";
    public static final String SKU = "sku";
    public static final String KEGS = "kegs";
    public static final String DATE = "date";
    public static final String TIME = "time";
    public static final String OPERATOR = "operator";
    public static final String BIN = "bin";
//    public static final String KEG_1 = "keg_1";
//    public static final String KEG_2 = "keg_2";
//    public static final String KEG_3 = "keg_3";
//    public static final String KEG_4 = "keg_4";
//    public static final String KEG_5 = "keg_5";
//    public static final String KEG_6 = "keg_6";
//    public static final String KEG_7 = "keg_7";
//    public static final String KEG_8 = "keg_8";
//    public static final String KEG_9 = "keg_9";
//    public static final String KEG_10 = "keg_10";
//    public static final String KEG_11 = "keg_11";
//    public static final String KEG_12 = "keg_12";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        ip = getResources().getString(R.string.ip);
        datalist = findViewById(R.id.dataList);
        consumedata = findViewById(R.id.consumeData);
        myDB = new DatabaseHelper(this);
        generateAdapter = new GenerateAdapter(this, datasArrayList);
//        datalist.setAdapter(generateAdapter);

        data_list = new ArrayList<>();
        consumedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DatabaseActivity.this, ConsumptionDatabaseActivity.class);
                startActivity(intent);
                finish();
            }
        });

//        ViewData();
        ViewGeneratedData();
    }

    private void ViewGeneratedData() {

        StringRequest request = new StringRequest(Request.Method.POST, "http://"+ip+"/PhP/retrive_generate.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                showJSON(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DatabaseActivity.this, "Invalid Network Connection", Toast.LENGTH_SHORT).show();
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
//                String keg_1 = jo.getString(KEG_1);
//                String keg_2 = jo.getString(KEG_2);
//                String keg_3 = jo.getString(KEG_3);
//                String keg_4 = jo.getString(KEG_4);
//                String keg_5 = jo.getString(KEG_5);
//                String keg_6 = jo.getString(KEG_6);
//                String keg_7 = jo.getString(KEG_7);
//                String keg_8 = jo.getString(KEG_8);
//                String keg_9 = jo.getString(KEG_9);
//                String keg_10 = jo.getString(KEG_10);
//                String keg_11 = jo.getString(KEG_11);
//                String keg_12 = jo.getString(KEG_12);

                final HashMap<String, String> datas = new HashMap<>();
                datas.put(BATCH, batch);
                datas.put(MIXER, mixer);
                datas.put(SKU, sku);
                datas.put(KEGS, kegs);
                datas.put(DATE, date);
                datas.put(TIME, time);
                datas.put(OPERATOR, operator);
                datas.put(BIN, bin);
//                datas.put(KEG_1, keg_1);
//                datas.put(KEG_2, keg_2);
//                datas.put(KEG_3, keg_3);
//                datas.put(KEG_4, keg_4);
//                datas.put(KEG_5, keg_5);
//                datas.put(KEG_6, keg_6);
//                datas.put(KEG_7, keg_7);
//                datas.put(KEG_8, keg_8);
//                datas.put(KEG_9, keg_9);
//                datas.put(KEG_10, keg_10);
//                datas.put(KEG_11, keg_11);
//                datas.put(KEG_12, keg_12);

                list.add(datas);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        ListAdapter adapter = new SimpleAdapter(
                DatabaseActivity.this, list, R.layout.data_selected_item,
                new String[]{BATCH, SKU, MIXER, OPERATOR, DATE, TIME},
                new int[]{R.id.data_text, R.id.sku_text, R.id.mixer_text, R.id.operator_text, R.id.date_text, R.id.time_text}
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
        Cursor cursor = myDB.retrieveGenerateData();
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