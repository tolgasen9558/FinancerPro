package com.example.android.financerpro.Activities;

import com.example.android.financerpro.BaseActivities.BaseDrawerActivity;
import com.example.android.financerpro.DataModels.ExpenseEntry2;
import com.example.android.financerpro.R;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.Entry;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.text.SimpleDateFormat;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChartViewActivity extends BaseDrawerActivity {

    private AppCompatActivity mClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContent(R.layout.activity_chart_view);
        mClass = new AppCompatActivity();

        final ArrayList<ExpenseEntry2> expenseList = new ArrayList<ExpenseEntry2>();
        ArrayList<Entry> temp1 = new ArrayList<Entry>();
        ArrayList<Entry> temp2 = new ArrayList<Entry>();
        Bitmap bitmap = null;
        Date date1 = new Date(2017, 11, 10);
        Date date2 = new Date(2017, 11, 11);
        Date date3 = new Date(2016, 11, 14);
        Date date4 = new Date(2015, 10, 3);
        Date date5 = new Date(2017, 10, 20);
        ExpenseEntry2 entry1 = new ExpenseEntry2("John", date1, "Clothes", 30.45, bitmap);
        ExpenseEntry2 entry2 = new ExpenseEntry2("Harold", date2, "Food", 20.29, bitmap);
        ExpenseEntry2 entry3 = new ExpenseEntry2("John", date3, "Food", 41.33, bitmap);
        ExpenseEntry2 entry4 = new ExpenseEntry2("Kyle", date4, "Bills",60.98, bitmap);
        ExpenseEntry2 entry5 = new ExpenseEntry2("Sam", date5, "Bills", 6.50, bitmap);
        expenseList.add(entry1);
        expenseList.add(entry2);
        expenseList.add(entry3);
        expenseList.add(entry4);

        Button button = (Button) findViewById(R.id.button_month);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PieChart p  = findViewById(R.id.piechart);
                p.clear();
                p.setVisibility(View.INVISIBLE);
                monthGraph(expenseList);
            }
        });
        Button button2 = (Button) findViewById(R.id.button_year);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PieChart p  = findViewById(R.id.piechart);
                p.clear();
                p.setVisibility(View.INVISIBLE);
                yearGraph(expenseList);
            }
        });
        Button button3 = (Button) findViewById(R.id.button_day);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PieChart p  = findViewById(R.id.piechart);
                p.clear();
                p.setVisibility(View.INVISIBLE);
                thirty_days_Graph(expenseList);
            }
        });
        yearGraph(expenseList);

        Button button4 = (Button) findViewById(R.id.button_switch);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //PieChart p = view.findViewById(R.id.piechart);
                LineChart l = findViewById(R.id.chart);
                l.clear();
                l.setVisibility(View.INVISIBLE);
                pieChart(expenseList);
            }
        });
        yearGraph(expenseList);
    }

    public void thirty_days_Graph(ArrayList<ExpenseEntry2> expenseEntries) {
        ArrayList<Entry> entries = new ArrayList<Entry>();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String[] date_array = date.split("-");
        float day = Float.parseFloat(date_array[2]);
        float total = 0;
        for(ExpenseEntry2 e : expenseEntries) {
            if (true) {
                total += e.getAmount().floatValue();
                entries.add(new Entry(e.getDate().getDate(), total));
            }
        }
        LineChart lineChart = findViewById(R.id.chart);
        lineChart.setVisibility(View.VISIBLE);
        LineDataSet dataSet = new LineDataSet(entries, "DATA");
        dataSet.setFormSize(16);
        dataSet.setValueTextSize(16);
        LineData data = new LineData(dataSet);
        lineChart.setData(data);
        lineChart.notifyDataSetChanged();
        lineChart.invalidate();
    }

    public void monthGraph(ArrayList<ExpenseEntry2> expenseEntries) {
        ArrayList<Entry> entries = new ArrayList<Entry>();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String[] date_array = date.split("-");
        //Toast.makeText(getApplicationContext(), date_array[1], Toast.LENGTH_LONG).show();
        float year = Float.parseFloat(date_array[0]);
        float month = Float.parseFloat(date_array[1]);
        //Toast.makeText(getApplicationContext(), "" + month, Toast.LENGTH_LONG).show();
        float total = 0;
        for(ExpenseEntry2 e : expenseEntries) {
            Date f = e.getDate();
            String fdate = new SimpleDateFormat("yyyy-MM-dd").format(f);
            String[] fdate_array = fdate.split("-");
            Float test_year = Float.parseFloat(fdate_array[0]) - 1900;
            Float test_month = Float.parseFloat(fdate_array[1]);
            //Toast.makeText(getApplicationContext(), year+""+test_year, Toast.LENGTH_LONG).show();
            if (test_month == month && year == test_year) {
                total += e.getAmount().floatValue();
                entries.add(new Entry(e.getDate().getDate(), total));
            }
        }
        LineChart lineChart = findViewById(R.id.chart);
        lineChart.setVisibility(View.VISIBLE);
        LineDataSet dataSet = new LineDataSet(entries, "DATA");
        dataSet.setFormSize(16);
        dataSet.setValueTextSize(16);
        LineData data = new LineData(dataSet);
        lineChart.setData(data);
        lineChart.notifyDataSetChanged();
        lineChart.invalidate();
    }

    public void yearGraph(ArrayList<ExpenseEntry2> expenseEntries) {
        ArrayList<Entry> entries = new ArrayList<Entry>();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String[] date_array = date.split("-");
        float year = Float.parseFloat(date_array[0]);
        float total = 0;
        for(ExpenseEntry2 e : expenseEntries) {
            if (e.getDate().getYear() == year) {
                total += e.getAmount().floatValue();
                entries.add(new Entry(e.getDate().getDate(), total));
            }
        }
        LineChart lineChart = findViewById(R.id.chart);
        lineChart.setVisibility(View.VISIBLE);
        LineDataSet dataSet = new LineDataSet(entries, "DATA");
        dataSet.setValueTextSize(16);
        dataSet.setFormSize(16);
        LineData data = new LineData(dataSet);
        lineChart.setData(data);
        lineChart.notifyDataSetChanged();
        lineChart.invalidate();
    }

    public void pieChart(ArrayList<ExpenseEntry2> expenseEntries) {

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        HashMap<String, Integer> cat = new HashMap<String, Integer>();
        for (ExpenseEntry2 e : expenseEntries) {
            if (!cat.containsKey(e.getCategory())) {
                cat.put(e.getCategory(), 1);
            }
            else {
                int x = cat.get(e.getCategory()) + 1;
                cat.put(e.getCategory(), x);
            }

        }
        for (String k : cat.keySet()) {

            float z = (float) cat.get(k);
            float t = (float) expenseEntries.size();
            float y = (z / t) * 100;

            entries.add(new PieEntry(y, k));

        }
        PieChart pieChart = findViewById(R.id.piechart);
        pieChart.setVisibility(View.VISIBLE);
        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setValueTextSize(18);
        dataSet.setFormSize(18);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        pieChart.notifyDataSetChanged();
        pieChart.invalidate();

    }
}
