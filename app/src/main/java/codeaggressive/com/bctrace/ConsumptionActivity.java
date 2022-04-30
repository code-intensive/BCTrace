package codeaggressive.com.bctrace;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.text.format.DateFormat;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ConsumptionActivity extends AppCompatActivity {
    TextView batchNo, mixer, sku, kegNo, c_date, c_time, operator, binNo;
    EditText timeResult, dateResult, batchInput;
    Spinner machine, usageType;
    Button search, scan, submit;
    String ip;
    int Hour, Minute;
    DatabaseHelper myDB;

//    private static final String production_url = "http://192.168.2.219/PhP/Consume_prod.php";
//    private static final String rework_url = "http://192.168.2.219/PhP/consume_rwk.php";
//    private static final String consume_url = "http://192.168.2.219/PhP/consume.php";
//    private static final String consumption_url = "http://192.168.2.219/PhP/consumption.php";
//    private static final String fetch_url = "http://192.168.2.219/PhP/fetch_data.php";
//    private static final String cleanGenerate_url = "http://192.168.2.219/PhP/CleanGenerate.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumption);

        ip = getResources().getString(R.string.ip);
        batchNo = findViewById(R.id.batch_No_Result_cons);
        mixer = findViewById(R.id.mixer_Result_cons);
        sku = findViewById(R.id.sku_Result_cons);
        kegNo = findViewById(R.id.kegNo_Result_cons);
        c_date = findViewById(R.id.date_Result_cons);
        c_time = findViewById(R.id.time_Result_cons);
        operator = findViewById(R.id.operator_Result_cons);
        binNo = findViewById(R.id.bin_Result_cons);

        batchInput = findViewById(R.id.batch_input);
        timeResult = findViewById(R.id.cons_time_Result);
        dateResult = findViewById(R.id.cons_date_Result);

        machine = findViewById(R.id.machine_cons);
        usageType = findViewById(R.id.usage_cons);

        search = findViewById(R.id.search_barcode);
        scan = findViewById(R.id.batch_scan_input);
        submit = findViewById(R.id.submit_result);

        myDB = new DatabaseHelper(this);
        CleanGenerate();

        ArrayAdapter<String> lineAdapter = new ArrayAdapter<String>(ConsumptionActivity.this, R.layout.consume_selected_item, getResources().getStringArray(R.array.machine));
        lineAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        machine.setAdapter(lineAdapter);

        ArrayAdapter<String> usageAdapter = new ArrayAdapter<String>(ConsumptionActivity.this, R.layout.consume_selected_item, getResources().getStringArray(R.array.usage));
        usageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        usageType.setAdapter(usageAdapter);

        File consumptionFile = new File(Environment.getExternalStorageDirectory() + "/Consumption.xlsx");

        if (!consumptionFile.exists()) {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Production");
            XSSFSheet reworkSheet = workbook.createSheet("Rework");
            String[] columnHeader = {"BATCH NO", "MIXER", "SKU", "KEG NO", "GENERATED DATE", "GENERATED TIME", "OPERATOR", "BIN NO", "CONSUMPTION DATE", "CONSUMPTION TIME", "USAGE", "MACHINE", "CONSUMED KEG"};
            String[] reworkHeader = {"BATCH NO", "MIXER", "SKU", "KEG NO", "GENERATED DATE", "GENERATED TIME", "OPERATOR", "BIN NO", "CONSUMPTION DATE", "CONSUMPTION TIME", "USAGE", "PERCENTAGE", "CONSUMED KEG"};

            XSSFFont headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 12);
            headerFont.setColor(IndexedColors.BLACK.index);

            XSSFCellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFont(headerFont);
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);

            XSSFRow headerReworkRow = reworkSheet.createRow(0);
            for (int i = 0; i < reworkHeader.length; i++) {
                XSSFCell headerCell = headerReworkRow.createCell(i);
                headerCell.setCellValue(reworkHeader[i]);
                headerCell.setCellStyle(headerStyle);
            }
            sheet.createFreezePane(0, 1);

            XSSFRow headerRow = sheet.createRow(0);
            for (int i = 0; i < columnHeader.length; i++) {
                XSSFCell headerCell = headerRow.createCell(i);
                headerCell.setCellValue(columnHeader[i]);
                headerCell.setCellStyle(headerStyle);
            }
            sheet.createFreezePane(0, 1);

            Toast.makeText(getApplicationContext(), "Consumption.xlsx Created successfully", Toast.LENGTH_LONG).show();
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(consumptionFile);
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

        search.setOnClickListener(v -> {
            SearchOutput();
        });

        scan.setOnClickListener(v -> {
            ScanOutput();
        });

        submit.setOnClickListener(v -> {
            SubmitOutput();
        });
    }

    private void ScanOutput() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(CaptureAct.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scanning...");
        integrator.initiateScan();
    }

    private void Continue(String result) {
        StringRequest request = new StringRequest(Request.Method.POST, "http://"+ip+"/PhP/fetch_data.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray results = jsonObject.getJSONArray("result");
                    JSONObject jo = results.getJSONObject(0);
                    String batch = jo.getString("batch");
                    String Mixer = jo.getString("mixer");
                    String Sku = jo.getString("sku");
                    String kegs = jo.getString("kegs");
                    String date = jo.getString("date");
                    String time = jo.getString("time");
                    String operators = jo.getString("operator");
                    String bin = jo.getString("bin");

                    batchInput.setText(result);
                    batchNo.setText(batch);
                    mixer.setText(Mixer);
                    sku.setText(Sku);
                    kegNo.setText(kegs);
                    c_date.setText(date);
                    c_time.setText(time);
                    operator.setText(operators);
                    binNo.setText(bin);

                    Calendar calendar = Calendar.getInstance();
                    final int year = calendar.get(Calendar.YEAR);
                    final int month = calendar.get(Calendar.MONTH);
                    final int day = calendar.get(Calendar.DAY_OF_MONTH);

                    SimpleDateFormat dateFormat = new SimpleDateFormat(" MM/dd/yyy", Locale.getDefault());
                    SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a", Locale.getDefault());
                    String date_string = dateFormat.format(calendar.getTime());
                    String time_string = timeFormat.format(calendar.getTime());
                    dateResult.setText(date_string);
                    timeResult.setText(time_string);


                    dateResult.setOnClickListener(v -> {
                        DatePickerDialog datePickerDialog = new DatePickerDialog(
                                ConsumptionActivity.this, (view, year1, month1, day1) -> {
                            month1 = month1 + 1;
                            String date1 = day1 + "/" + month1 + "/" + year1;
                            dateResult.setText(date1);
                        }, year, month, day);
                        datePickerDialog.show();
                    });

                    // Time

                    timeResult.setOnClickListener(v -> {
                        TimePickerDialog timePickerDialog = new TimePickerDialog(
                                ConsumptionActivity.this, (view, hour, minute) -> {
                            Hour = hour;
                            Minute = minute;

                            Calendar calendar1 = Calendar.getInstance();

                            calendar1.set(0, 0, 0, Hour, Minute);

                            timeResult.setText(DateFormat.format("hh:mm aa", calendar1));
                        }, 12, 0, false
                        );
                        timePickerDialog.updateTime(Hour, Minute);
                        timePickerDialog.show();
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ConsumptionActivity.this, "Invalid Network Connection", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> param = new HashMap<String, String>();
                param.put("search", result);
                return param;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {
                // -------------------------------------------------------------//
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(result.getContents());
                builder.setTitle("Result");
                builder.setPositiveButton("Scan Again", (dialog, which) -> ScanOutput()).setNegativeButton("Fetch", (dialog, which) -> Continue(result.getContents()));
                AlertDialog dialog = builder.create();
                dialog.show();
            } else {
                Toast.makeText(this, "No Result", Toast.LENGTH_LONG).show();
                finish();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void SearchOutput() {

        if (batchInput.getText().toString().isEmpty()) {
            Toast.makeText(this, "Batch Field Empty", Toast.LENGTH_SHORT).show();
        } else {
            fetchFromDb();
//            fetchFromSql();
            Calendar calendar = Calendar.getInstance();
            final int year = calendar.get(Calendar.YEAR);
            final int month = calendar.get(Calendar.MONTH);
            final int day = calendar.get(Calendar.DAY_OF_MONTH);

            SimpleDateFormat dateFormat = new SimpleDateFormat(" MM/dd/yyy", Locale.getDefault());
            SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a", Locale.getDefault());
            String date = dateFormat.format(calendar.getTime());
            String time = timeFormat.format(calendar.getTime());
            dateResult.setText(date);
            timeResult.setText(time);


            dateResult.setOnClickListener(v -> {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        ConsumptionActivity.this, (view, year1, month1, day1) -> {
                    month1 = month1 + 1;
                    String date1 = day1 + "/" + month1 + "/" + year1;
                    dateResult.setText(date1);
                }, year, month, day);
                datePickerDialog.show();
            });

            // Time

            timeResult.setOnClickListener(v -> {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        ConsumptionActivity.this, (view, hour, minute) -> {
                    Hour = hour;
                    Minute = minute;

                    Calendar calendar1 = Calendar.getInstance();

                    calendar1.set(0, 0, 0, Hour, Minute);

                    timeResult.setText(DateFormat.format("hh:mm aa", calendar1));
                }, 12, 0, false
                );
                timePickerDialog.updateTime(Hour, Minute);
                timePickerDialog.show();
            });
        }
    }

    private void fetchFromSql() {
        Cursor result = myDB.getBatchData(batchInput.getText().toString());
        while (result.moveToNext()) {
            batchNo.setText(result.getString(0));
            mixer.setText(result.getString(1));
            sku.setText(result.getString(2));
            kegNo.setText(result.getString(3));
            c_date.setText(result.getString(4));
            c_time.setText(result.getString(5));
            operator.setText(result.getString(6));
            binNo.setText(result.getString(7));
        }
    }

    private void fetchFromDb() {
        StringRequest request = new StringRequest(Request.Method.POST, "http://"+ip+"/PhP/fetch_data.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray result = jsonObject.getJSONArray("result");
                    JSONObject jo = result.getJSONObject(0);
                    String batch = jo.getString("batch");
                    String Mixer = jo.getString("mixer");
                    String Sku = jo.getString("sku");
                    String kegs = jo.getString("kegs");
                    String date = jo.getString("date");
                    String time = jo.getString("time");
                    String operators = jo.getString("operator");
                    String bin = jo.getString("bin");

                    batchNo.setText(batch);
                    mixer.setText(Mixer);
                    sku.setText(Sku);
                    kegNo.setText(kegs);
                    c_date.setText(date);
                    c_time.setText(time);
                    operator.setText(operators);
                    binNo.setText(bin);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ConsumptionActivity.this, "Invalid Network Connection", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> param = new HashMap<String, String>();
                param.put("search", batchInput.getText().toString());
                return param;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }

    private void SubmitOutput() {
        String consume_batch_string = batchNo.getText().toString();
        String consume_mixer_string = mixer.getText().toString();
        String consume_sku_string = sku.getText().toString();
        String consume_keg_string = kegNo.getText().toString();
        String consume_date_string = c_date.getText().toString();
        String consume_time_string = c_time.getText().toString();
        String consume_operator_string = operator.getText().toString();
        String consume_bin_string = binNo.getText().toString();
        String consume_dateResult_string = dateResult.getText().toString();
        String consume_time_result_string = timeResult.getText().toString();
        String consume_usageType_string = usageType.getSelectedItem().toString();
        String consume_machine_string = machine.getSelectedItem().toString();

        if (consume_batch_string.isEmpty() || consume_mixer_string.isEmpty() || consume_sku_string.isEmpty() || consume_keg_string.isEmpty() || consume_date_string.isEmpty() || consume_time_string.isEmpty() || consume_operator_string.isEmpty() || consume_bin_string.isEmpty() || consume_dateResult_string.isEmpty() || consume_time_result_string.isEmpty() || consume_usageType_string.isEmpty() || consume_machine_string.isEmpty()) {
            Toast.makeText(this, "Fill Empty TextField", Toast.LENGTH_SHORT).show();
        } else if (consume_usageType_string.equals("Select") || consume_machine_string.equals("Select")) {
            Toast.makeText(this, "Fill Empty TextField", Toast.LENGTH_SHORT).show();
        } else {
            InsertToExcel();
            InsertSQL();
            InsertConsumptionData();
            InsertConsumeData();
            CleanGenerate();
        }
    }

    private void InsertSQL() {
        String consume_batch_string = batchNo.getText().toString();
        String consume_mixer_string = mixer.getText().toString();
        String consume_sku_string = sku.getText().toString();
        String consume_keg_string = kegNo.getText().toString();
        String consume_date_string = c_date.getText().toString();
        String consume_time_string = c_time.getText().toString();
        String consume_operator_string = operator.getText().toString();
        String consume_bin_string = binNo.getText().toString();
        String consume_dateResult_string = dateResult.getText().toString();
        String consume_time_result_string = timeResult.getText().toString();
        String consume_usageType_string = usageType.getSelectedItem().toString();
        String consume_machine_string = machine.getSelectedItem().toString();
        String consume_consumed_keg_string = batchInput.getText().toString();

        if (consume_batch_string.isEmpty() || consume_mixer_string.isEmpty() || consume_sku_string.isEmpty() || consume_keg_string.isEmpty() || consume_date_string.isEmpty() || consume_time_string.isEmpty() || consume_operator_string.isEmpty() || consume_bin_string.isEmpty() || consume_dateResult_string.isEmpty() || consume_time_result_string.isEmpty() || consume_usageType_string.isEmpty() || consume_machine_string.isEmpty()) {
            Toast.makeText(this, "Found Empty Field", Toast.LENGTH_SHORT).show();
        } else {
            boolean isInserted = myDB.insertConsumeDate(consume_batch_string, consume_mixer_string, consume_sku_string, consume_keg_string, consume_date_string, consume_time_string, consume_operator_string, consume_bin_string, consume_dateResult_string, consume_time_result_string, consume_machine_string, consume_usageType_string, consume_consumed_keg_string);
            if (isInserted) {
                Toast.makeText(getApplicationContext(), "Data Insert Successfully", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void InsertToExcel() {
        if (usageType.getSelectedItem().toString().equals("Production")) {
            File sources = new File(Environment.getExternalStorageDirectory() + "/Consumption.xlsx");
            try {
                FileInputStream existing = new FileInputStream(sources);
                XSSFWorkbook workbook = new XSSFWorkbook(existing);
                XSSFSheet worksheet = workbook.getSheetAt(0);
                int lastRow = worksheet.getLastRowNum();
                XSSFRow row = worksheet.createRow(++lastRow);
                row.createCell(0).setCellValue(batchNo.getText().toString().trim());
                row.createCell(1).setCellValue(mixer.getText().toString().trim());
                row.createCell(2).setCellValue(sku.getText().toString().trim());
                row.createCell(3).setCellValue(kegNo.getText().toString().trim());
                row.createCell(4).setCellValue(c_date.getText().toString().trim());
                row.createCell(5).setCellValue(c_time.getText().toString().trim());
                row.createCell(6).setCellValue(operator.getText().toString().trim());
                row.createCell(7).setCellValue(binNo.getText().toString().trim());
                row.createCell(8).setCellValue(dateResult.getText().toString().trim());
                row.createCell(9).setCellValue(timeResult.getText().toString().trim());
                row.createCell(10).setCellValue(usageType.getSelectedItem().toString().trim());
                row.createCell(11).setCellValue(machine.getSelectedItem().toString().trim());
                row.createCell(12).setCellValue(batchInput.getText().toString().trim());

                existing.close();
                FileOutputStream newinput = new FileOutputStream(sources);
                workbook.write(newinput);
                newinput.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            InsertProductionData();
        } else if (usageType.getSelectedItem().toString().equals("Rework")) {
            File sources = new File(Environment.getExternalStorageDirectory() + "/Consumption.xlsx");
            try {
                FileInputStream existing = new FileInputStream(sources);
                XSSFWorkbook workbook = new XSSFWorkbook(existing);
                XSSFSheet worksheet = workbook.getSheetAt(1);
                int lastRow = worksheet.getLastRowNum();
                XSSFRow row = worksheet.createRow(++lastRow);
                row.createCell(0).setCellValue(batchNo.getText().toString().trim());
                row.createCell(1).setCellValue(mixer.getText().toString().trim());
                row.createCell(2).setCellValue(sku.getText().toString().trim());
                row.createCell(3).setCellValue(kegNo.getText().toString().trim());
                row.createCell(4).setCellValue(c_date.getText().toString().trim());
                row.createCell(5).setCellValue(c_time.getText().toString().trim());
                row.createCell(6).setCellValue(operator.getText().toString().trim());
                row.createCell(7).setCellValue(binNo.getText().toString().trim());
                row.createCell(8).setCellValue(dateResult.getText().toString().trim());
                row.createCell(9).setCellValue(timeResult.getText().toString().trim());
                row.createCell(10).setCellValue(usageType.getSelectedItem().toString().trim());
                row.createCell(11).setCellValue(machine.getSelectedItem().toString().trim());
                row.createCell(12).setCellValue(batchInput.getText().toString().trim());

                existing.close();
                FileOutputStream newinput = new FileOutputStream(sources);
                workbook.write(newinput);
                newinput.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            InsertReworkData();
        } else {
            Toast.makeText(this, "Usage Field Required", Toast.LENGTH_SHORT).show();
        }
    }

    private void InsertReworkData() {
        String ConsumptionRework_batch_data = batchNo.getText().toString();
        String ConsumptionRework_mixer_data = mixer.getText().toString();
        String ConsumptionRework_sku_data = sku.getText().toString();
        String ConsumptionRework_keg_data = kegNo.getText().toString();
        String ConsumptionRework_c_date_data = c_date.getText().toString();
        String ConsumptionRework_c_time_data = c_time.getText().toString();
        String ConsumptionRework_operator_data = operator.getText().toString();
        String ConsumptionRework_bin_data = binNo.getText().toString();
        String ConsumptionRework_date_data = dateResult.getText().toString();
        String ConsumptionRework_time_data = timeResult.getText().toString();
        String ConsumptionRework_usage_data = usageType.getSelectedItem().toString();
        String ConsumptionRework_percentage_data = machine.getSelectedItem().toString();
        String ConsumptionRework_consumed_keg_data = batchInput.getText().toString();
        StringRequest request = new StringRequest(Request.Method.POST, "http://"+ip+"/PhP/consume_rwk.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(ConsumptionActivity.this, response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ConsumptionActivity.this, "Invalid Network Connection", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                param.put("batchNo", ConsumptionRework_batch_data);
                param.put("mixer", ConsumptionRework_mixer_data);
                param.put("sku", ConsumptionRework_sku_data);
                param.put("kegNo", ConsumptionRework_keg_data);
                param.put("c_date", ConsumptionRework_c_date_data);
                param.put("c_time", ConsumptionRework_c_time_data);
                param.put("operator", ConsumptionRework_operator_data);
                param.put("binNo", ConsumptionRework_bin_data);
                param.put("dateResult", ConsumptionRework_date_data);
                param.put("timeResult", ConsumptionRework_time_data);
                param.put("usageType", ConsumptionRework_usage_data);
                param.put("percentage", ConsumptionRework_percentage_data);
                param.put("consumed_keg", ConsumptionRework_consumed_keg_data);
                return param;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }

    private void InsertProductionData() {
        String ConsumptionProduction_batch_data = batchNo.getText().toString();
        String ConsumptionProduction_mixer_data = mixer.getText().toString();
        String ConsumptionProduction_sku_data = sku.getText().toString();
        String ConsumptionProduction_keg_data = kegNo.getText().toString();
        String ConsumptionProduction_c_date_data = c_date.getText().toString();
        String ConsumptionProduction_c_time_data = c_time.getText().toString();
        String ConsumptionProduction_operator_data = operator.getText().toString();
        String ConsumptionProduction_bin_data = binNo.getText().toString();
        String ConsumptionProduction_date_data = dateResult.getText().toString();
        String ConsumptionProduction_time_data = timeResult.getText().toString();
        String ConsumptionProduction_usage_data = usageType.getSelectedItem().toString();
        String ConsumptionProduction_machine_data = machine.getSelectedItem().toString();
        String ConsumptionRework_consumed_keg_data = batchInput.getText().toString();
        StringRequest request = new StringRequest(Request.Method.POST, "http://"+ip+"/PhP/Consume_prod.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(ConsumptionActivity.this, response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ConsumptionActivity.this, "Invalid Network Connection", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                param.put("batchNo", ConsumptionProduction_batch_data);
                param.put("mixer", ConsumptionProduction_mixer_data);
                param.put("sku", ConsumptionProduction_sku_data);
                param.put("kegNo", ConsumptionProduction_keg_data);
                param.put("c_date", ConsumptionProduction_c_date_data);
                param.put("c_time", ConsumptionProduction_c_time_data);
                param.put("operator", ConsumptionProduction_operator_data);
                param.put("binNo", ConsumptionProduction_bin_data);
                param.put("dateResult", ConsumptionProduction_date_data);
                param.put("timeResult", ConsumptionProduction_time_data);
                param.put("usageType", ConsumptionProduction_usage_data);
                param.put("machine", ConsumptionProduction_machine_data);
                param.put("consumed_keg", ConsumptionRework_consumed_keg_data);
                return param;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }

    private void InsertConsumeData() {
        String ConsumptionProduction_batch_data = batchNo.getText().toString();
        String ConsumptionProduction_mixer_data = mixer.getText().toString();
        String ConsumptionProduction_sku_data = sku.getText().toString();
        String ConsumptionProduction_keg_data = kegNo.getText().toString();
        String ConsumptionProduction_c_date_data = c_date.getText().toString();
        String ConsumptionProduction_c_time_data = c_time.getText().toString();
        String ConsumptionProduction_operator_data = operator.getText().toString();
        String ConsumptionProduction_bin_data = binNo.getText().toString();
        String ConsumptionProduction_date_data = dateResult.getText().toString();
        String ConsumptionProduction_time_data = timeResult.getText().toString();
        String ConsumptionProduction_usage_data = usageType.getSelectedItem().toString();
        String ConsumptionProduction_machine_data = machine.getSelectedItem().toString();
        String ConsumptionRework_consumed_keg_data = batchInput.getText().toString();
        StringRequest request = new StringRequest(Request.Method.POST, "http://"+ip+"/PhP/consume.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                clearData();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ConsumptionActivity.this, "Invalid Network Connection", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                param.put("batchNo", ConsumptionProduction_batch_data);
                param.put("mixer", ConsumptionProduction_mixer_data);
                param.put("sku", ConsumptionProduction_sku_data);
                param.put("kegNo", ConsumptionProduction_keg_data);
                param.put("c_date", ConsumptionProduction_c_date_data);
                param.put("c_time", ConsumptionProduction_c_time_data);
                param.put("operator", ConsumptionProduction_operator_data);
                param.put("binNo", ConsumptionProduction_bin_data);
                param.put("dateResult", ConsumptionProduction_date_data);
                param.put("timeResult", ConsumptionProduction_time_data);
                param.put("usageType", ConsumptionProduction_usage_data);
                param.put("machine", ConsumptionProduction_machine_data);
                param.put("consumed_keg", ConsumptionRework_consumed_keg_data);
                return param;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }

    private void InsertConsumptionData() {
        String ConsumptionProduction_batch_data = batchNo.getText().toString();
        String ConsumptionProduction_mixer_data = mixer.getText().toString();
        String ConsumptionProduction_sku_data = sku.getText().toString();
        String ConsumptionProduction_keg_data = kegNo.getText().toString();
        String ConsumptionProduction_c_date_data = c_date.getText().toString();
        String ConsumptionProduction_c_time_data = c_time.getText().toString();
        String ConsumptionProduction_operator_data = operator.getText().toString();
        String ConsumptionProduction_bin_data = binNo.getText().toString();
        String ConsumptionProduction_date_data = dateResult.getText().toString();
        String ConsumptionProduction_time_data = timeResult.getText().toString();
        String ConsumptionProduction_usage_data = usageType.getSelectedItem().toString();
        String ConsumptionProduction_machine_data = machine.getSelectedItem().toString();
        String ConsumptionRework_consumed_keg_data = batchInput.getText().toString();
        StringRequest request = new StringRequest(Request.Method.POST, "http://"+ip+"/PhP/consumption.php", response -> {
        }, error -> Toast.makeText(ConsumptionActivity.this, "Invalid Network Connection", Toast.LENGTH_SHORT).show()) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                param.put("batchNo", ConsumptionProduction_batch_data);
                param.put("mixer", ConsumptionProduction_mixer_data);
                param.put("sku", ConsumptionProduction_sku_data);
                param.put("kegNo", ConsumptionProduction_keg_data);
                param.put("c_date", ConsumptionProduction_c_date_data);
                param.put("c_time", ConsumptionProduction_c_time_data);
                param.put("operator", ConsumptionProduction_operator_data);
                param.put("binNo", ConsumptionProduction_bin_data);
                param.put("dateResult", ConsumptionProduction_date_data);
                param.put("timeResult", ConsumptionProduction_time_data);
                param.put("usageType", ConsumptionProduction_usage_data);
                param.put("machine", ConsumptionProduction_machine_data);
                param.put("consumed_keg", ConsumptionRework_consumed_keg_data);
                return param;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }

    private void CleanGenerate() {
        StringRequest request = new StringRequest(Request.Method.POST, "http://"+ip+"/PhP/CleanGenerate.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ConsumptionActivity.this, "Invalid Network Connection", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> param = new HashMap<String, String>();
                param.put("command", "refresh");
                return param;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }

    private void clearData() {
        batchNo.setText("");
        mixer.setText("");
        sku.setText("");
        kegNo.setText("");
        c_date.setText("");
        c_time.setText("");
        operator.setText("");
        binNo.setText("");
        dateResult.setText("");
        timeResult.setText("");
        CleanGenerate();

        ArrayAdapter<String> lineAdapter = new ArrayAdapter<String>(ConsumptionActivity.this, R.layout.consume_selected_item, getResources().getStringArray(R.array.machine));
        lineAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        machine.setAdapter(lineAdapter);

        ArrayAdapter<String> usageAdapter = new ArrayAdapter<String>(ConsumptionActivity.this, R.layout.consume_selected_item, getResources().getStringArray(R.array.usage));
        usageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        usageType.setAdapter(usageAdapter);
    }

}