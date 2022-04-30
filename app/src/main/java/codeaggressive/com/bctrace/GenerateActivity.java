package codeaggressive.com.bctrace;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.text.format.DateFormat;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class GenerateActivity extends AppCompatActivity {
    //    AdView adView;
    Button next, amixon, lodige, morton;
    EditText batchNo, etime, edate;
    Spinner mixer, sku, kegNo, operator, binNo;
    String batch_string, mixer_string, sku_string, keg_string, time_string, date_string, operator_string, bin_string, ip;
    int Hour, Minute;

//    private static final String batch_url = "http://192.168.2.219/PhP/batch_data_from_minor.php";
//    private static final String refactor_url = "http://192.168.2.219/PhP/refactor.php";
    public static final String BATCH = "batch";
    public static final String MIXER = "mixer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_generate);

        ip = getResources().getString(R.string.ip);
        next = findViewById(R.id.next);
        amixon = findViewById(R.id.amixon);
        lodige = findViewById(R.id.lodige);
        morton = findViewById(R.id.morton);
        etime = findViewById(R.id.time);
        edate = findViewById(R.id.date);
        mixer = findViewById(R.id.mixer);
        sku = findViewById(R.id.sku);
        kegNo = findViewById(R.id.kegNo);
        operator = findViewById(R.id.operator);
        binNo = findViewById(R.id.binNo);
        batchNo = findViewById(R.id.batchNo);

        refactor();

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        SimpleDateFormat dateFormat = new SimpleDateFormat(" MM/dd/yyy", Locale.getDefault());
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a", Locale.getDefault());
        String date = dateFormat.format(calendar.getTime());
        String time = timeFormat.format(calendar.getTime());

        edate.setText(date);
        etime.setText(time);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        ArrayAdapter<String> mixerAdapter = new ArrayAdapter<String>(GenerateActivity.this, R.layout.my_selected_item, getResources().getStringArray(R.array.mixer_Names));
        mixerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mixer.setAdapter(mixerAdapter);

        ArrayAdapter<String> skuAdapter = new ArrayAdapter<String>(GenerateActivity.this, R.layout.my_selected_item, getResources().getStringArray(R.array.sku));
        skuAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sku.setAdapter(skuAdapter);

        ArrayAdapter<String> kegNoAdapter = new ArrayAdapter<String>(GenerateActivity.this, R.layout.my_selected_item, getResources().getStringArray(R.array.keg_no));
        kegNoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        kegNo.setAdapter(kegNoAdapter);

        String operatorName = getIntent().getExtras().getString("operatorsName");
        ArrayAdapter<String> operatorAdapter;
        if (operatorName.isEmpty() || operatorName.equals("null")) {
            operatorAdapter = new ArrayAdapter<String>(GenerateActivity.this, R.layout.my_selected_item, getResources().getStringArray(R.array.operator_name));
            operatorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        } else {
            operatorAdapter = new ArrayAdapter<String>(GenerateActivity.this, R.layout.my_selected_item, Collections.singletonList(operatorName));
        }
        operator.setAdapter(operatorAdapter);

        ArrayAdapter<String> binNoAdapter = new ArrayAdapter<String>(GenerateActivity.this, R.layout.my_selected_item, getResources().getStringArray(R.array.bin_no));
        binNoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binNo.setAdapter(binNoAdapter);

        File generateFile = new File(Environment.getExternalStorageDirectory() + "/Generate.xlsx");

        if (!generateFile.exists()) {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Generate");
            String[] columnHeader = {"BATCH NO", "MIXER", "SKU", "KEG NO", "DATE", "TIME", "OPERATOR", "BIN NO", "KEG_1", "KEG_2", "KEG_3", "KEG_4", "KEG_5", "KEG_6", "KEG_7", "KEG_8", "KEG_9", "KEG_10", "KEG_11", "KEG_12"};

            XSSFFont headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 12);
            headerFont.setColor(IndexedColors.BLACK.index);

            XSSFCellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFont(headerFont);
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);

            XSSFRow headerRow = sheet.createRow(0);
            for (int i = 0; i < columnHeader.length; i++) {
                XSSFCell headerCell = headerRow.createCell(i);
                headerCell.setCellValue(columnHeader[i]);
                headerCell.setCellStyle(headerStyle);
            }
            sheet.createFreezePane(0, 1);

            Toast.makeText(getApplicationContext(), "Generate.xlsx Created successfully", Toast.LENGTH_LONG).show();
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(generateFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                workbook.write(fos);
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Date

        edate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        GenerateActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        edate.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        // Time

        etime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        GenerateActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hour, int minute) {
                        Hour = hour;
                        Minute = minute;

                        Calendar calendar = Calendar.getInstance();

                        calendar.set(0, 0, 0, Hour, Minute);

                        etime.setText(DateFormat.format("hh:mm aa", calendar));
                    }
                }, 12, 0, false
                );
                timePickerDialog.updateTime(Hour, Minute);
                timePickerDialog.show();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean emptyBatch = batchNo.getText().toString().isEmpty();
                boolean emptyMixer = mixer.getSelectedItem().toString().equals("Mixer");
                boolean emptySku = sku.getSelectedItem().toString().equals("Sku");
                boolean emptyKeg = kegNo.getSelectedItem().toString().equals("Kegs");
                boolean emptyTime = etime.getText().toString().isEmpty();
                boolean emptyDate = edate.getText().toString().isEmpty();
                boolean emptyOperator = operator.getSelectedItem().toString().equals("Operator");
                boolean emptyBin = binNo.getSelectedItem().toString().equals("Bin");

                if (emptyBatch || emptyMixer || emptySku || emptyKeg || emptyDate || emptyTime || emptyOperator || emptyBin) {
                    Toast.makeText(GenerateActivity.this, "All Field are Required", Toast.LENGTH_SHORT).show();
                } else {

                    Intent intent = new Intent(GenerateActivity.this, KegScanActivity.class);

                    batch_string = batchNo.getText().toString();
                    mixer_string = mixer.getSelectedItem().toString();
                    sku_string = sku.getSelectedItem().toString();
                    keg_string = kegNo.getSelectedItem().toString();
                    time_string = etime.getText().toString();
                    date_string = edate.getText().toString();
                    operator_string = operator.getSelectedItem().toString();
                    bin_string = binNo.getSelectedItem().toString();

                    intent.putExtra("batch", batch_string);
                    intent.putExtra("mixer", mixer_string);
                    intent.putExtra("sku", sku_string);
                    intent.putExtra("keg", keg_string);
                    intent.putExtra("time", time_string);
                    intent.putExtra("date", date_string);
                    intent.putExtra("operator", operator_string);
                    intent.putExtra("bin", bin_string);
                    intent.putExtra("operatorsName", operatorName);

                    startActivity(intent);
                    finish();
                }
            }
        });

        amixon.setOnClickListener(v -> {
            amixonOnClick();
        });

        lodige.setOnClickListener(v -> {
            LodigeOnClick();

        });

        morton.setOnClickListener(v -> {
            mortonOnClick();
        });
    }

    private void mortonOnClick() {
        StringRequest request = new StringRequest(Request.Method.POST, "http://"+ip+"/PhP/batch_data_from_minor.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray result = jsonObject.getJSONArray("result");

                    JSONObject jo = result.getJSONObject(0);
                    String batch = jo.getString(BATCH);
                    String Mixer = jo.getString(MIXER);
                    batchNo.setText(batch);

                    ArrayAdapter<String> skuAdapter = new ArrayAdapter<String>(GenerateActivity.this, R.layout.my_selected_item, Collections.singletonList(SkuFromBatch.getSku(batch)));
                    sku.setAdapter(skuAdapter);

                    if (Mixer.equals("Morton") || Mixer.isEmpty()) {

                        ArrayAdapter<String> mixerDataAdapter = new ArrayAdapter<String>(GenerateActivity.this, R.layout.my_selected_item, Collections.singletonList(Mixer));
                        mixer.setAdapter(mixerDataAdapter);
                    } else {
                        ArrayAdapter<String> mixerAdapter = new ArrayAdapter<String>(GenerateActivity.this, R.layout.my_selected_item, getResources().getStringArray(R.array.mixer_Names));
                        mixerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mixer.setAdapter(mixerAdapter);
                        batchNo.setText("");
                        ArrayAdapter<String> skuAdapt = new ArrayAdapter<String>(GenerateActivity.this, R.layout.my_selected_item, getResources().getStringArray(R.array.sku));
                        skuAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        sku.setAdapter(skuAdapt);

                    }

                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(GenerateActivity.this, "Invalid Network Connection", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                param.put("request", "morton");
                return param;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }

    private void amixonOnClick() {
        StringRequest request = new StringRequest(Request.Method.POST, "http://"+ip+"/PhP/batch_data_from_minor.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray result = jsonObject.getJSONArray("result");

                    JSONObject jo = result.getJSONObject(0);
                    String batch = jo.getString(BATCH);
                    String Mixer = jo.getString(MIXER);
                    batchNo.setText(batch);

                    ArrayAdapter<String> skuAdapter = new ArrayAdapter<String>(GenerateActivity.this, R.layout.my_selected_item, Collections.singletonList(SkuFromBatch.getSku(batch)));
                    sku.setAdapter(skuAdapter);

                    if (Mixer.equals("Amixon") || Mixer.isEmpty()) {

                        ArrayAdapter<String> mixerDataAdapter = new ArrayAdapter<String>(GenerateActivity.this, R.layout.my_selected_item, Collections.singletonList(Mixer));
                        mixer.setAdapter(mixerDataAdapter);
                    } else {
                        ArrayAdapter<String> mixerAdapter = new ArrayAdapter<String>(GenerateActivity.this, R.layout.my_selected_item, getResources().getStringArray(R.array.mixer_Names));
                        mixerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mixer.setAdapter(mixerAdapter);
                        batchNo.setText("");
                        ArrayAdapter<String> skuAdapt = new ArrayAdapter<String>(GenerateActivity.this, R.layout.my_selected_item, getResources().getStringArray(R.array.sku));
                        skuAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        sku.setAdapter(skuAdapt);
                    }

                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(GenerateActivity.this, "Invalid Network Connection", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                param.put("request", "amixon");
                return param;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }

    private void LodigeOnClick() {
        StringRequest request = new StringRequest(Request.Method.POST, "http://"+ip+"/PhP/batch_data_from_minor.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray result = jsonObject.getJSONArray("result");

                    JSONObject jo = result.getJSONObject(0);
                    String batch = jo.getString(BATCH);
                    String Mixer = jo.getString(MIXER);
                    batchNo.setText(batch);

                    ArrayAdapter<String> skuAdapter = new ArrayAdapter<String>(GenerateActivity.this, R.layout.my_selected_item, Collections.singletonList(SkuFromBatch.getSku(batch)));
                    sku.setAdapter(skuAdapter);

                    if (Mixer.equals("Lodige") || Mixer.isEmpty()) {
                        ArrayAdapter<String> mixerDataAdapter = new ArrayAdapter<String>(GenerateActivity.this, R.layout.my_selected_item, Collections.singletonList(Mixer));
                        mixer.setAdapter(mixerDataAdapter);
                    } else {
                        ArrayAdapter<String> mixerAdapter = new ArrayAdapter<String>(GenerateActivity.this, R.layout.my_selected_item, getResources().getStringArray(R.array.mixer_Names));
                        mixerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mixer.setAdapter(mixerAdapter);
                        batchNo.setText("");
                        ArrayAdapter<String> skuAdapt = new ArrayAdapter<String>(GenerateActivity.this, R.layout.my_selected_item, getResources().getStringArray(R.array.sku));
                        skuAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        sku.setAdapter(skuAdapt);

                    }

                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(GenerateActivity.this, "Invalid Network Connection", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                param.put("request", "lodige");
                return param;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }

    private void refactor() {

        StringRequest request = new StringRequest(Request.Method.POST, "http://"+ip+"/PhP/refactor.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equals("successful")) {
                    Intent intent = new Intent(getApplicationContext(), EndActivity.class);
                    startActivity(intent);
                    finish();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(GenerateActivity.this, "Invalid Network Connection", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);

    }
}
