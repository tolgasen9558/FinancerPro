package com.example.android.financerpro.Activities;

import com.example.android.financerpro.BaseActivities.BaseDrawerActivity;
import com.example.android.financerpro.DataModels.ExpenseEntry;
import com.example.android.financerpro.FinancerAppData;
import com.example.android.financerpro.R;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.BarDataSet;

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
import android.widget.Toast;

import java.util.List;
public class ChartViewActivity extends BaseDrawerActivity {

    private AppCompatActivity mClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContent(R.layout.activity_chart_view);
        mClass = new AppCompatActivity();


        List<ExpenseEntry> list2 = new ArrayList<>();
        list2 = FinancerAppData.getInstance().getExpensesList();
        final List<ExpenseEntry> list = list2;
        if (list.isEmpty()) {

        }
        else {
            String x = "";
            for (ExpenseEntry e : list) {
                x = e.getName();
            }
        }

            Button button = (Button) findViewById(R.id.button_month);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PieChart p = findViewById(R.id.piechart);
                    p.clear();
                    p.setVisibility(View.INVISIBLE);
                    if (list.isEmpty()) {
                        p.clear();
                    }
                    else {
                        monthGraph(list);
                    }
                }
            });
            Button button2 = (Button) findViewById(R.id.button_year);
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PieChart p = findViewById(R.id.piechart);
                    p.clear();
                    p.setVisibility(View.INVISIBLE);
                    yearGraph(list);
                }
            });
            Button button3 = (Button) findViewById(R.id.button_day);
            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PieChart p = findViewById(R.id.piechart);
                    p.clear();
                    p.setVisibility(View.INVISIBLE);
                    allGraph(list);
                }
            });

            Button button4 = (Button) findViewById(R.id.button_switch);
            button4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    BarChart l = findViewById(R.id.chart);
                    l.clear();
                    l.setVisibility(View.INVISIBLE);
                    pieChart(list);
                }
            });

    }

    public void allGraph(List<ExpenseEntry> expenseEntries) {
        HashMap<Float, Float> trans = new HashMap<Float, Float>();
        List<BarEntry> entries = new ArrayList<BarEntry>();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String[] date_array = date.split("-");
        float day = Float.parseFloat(date_array[2]);
        float total = 0;

        for (int i = 0; i < expenseEntries.size(); i++) {
            ExpenseEntry e = expenseEntries.get(i);
            if (true) {
                total += e.getAmount().floatValue();
                entries.add(new BarEntry(e.getDate().getDate(), total));
            }
        }

        BarChart lineChart = findViewById(R.id.chart);
        lineChart.setVisibility(View.VISIBLE);
        BarDataSet dataSet = new BarDataSet(entries, "$");
        dataSet.setFormSize(18);
        dataSet.setValueTextSize(18);
        BarData data = new BarData(dataSet);
        lineChart.setData(data);
        lineChart.notifyDataSetChanged();
        lineChart.invalidate();
    }

    public void monthGraph(List<ExpenseEntry> expenseEntries) {
        HashMap<Float, Float> trans = new HashMap<Float, Float>();
        List<BarEntry> entries = new ArrayList<BarEntry>();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String[] date_array = date.split("-");
        float year = Float.parseFloat(date_array[0]);
        float month = Float.parseFloat(date_array[1]);
        float total = 0;
        float day;

        for (int i = 0; i < expenseEntries.size(); i++) {
            ExpenseEntry e = expenseEntries.get(i);
            Date f = e.getDate();
            String fdate = new SimpleDateFormat("yyyy-MM-dd").format(f);
            String[] fdate_array = fdate.split("-");
            float test_year = Float.parseFloat(fdate_array[0]);
            float test_month = Float.parseFloat(fdate_array[1]);

            if (test_month == month && year == test_year) {

                total = e.getAmount().floatValue();
                entries.add(new BarEntry(e.getDate().getDate(), total));
                if (!trans.containsKey(month)) {
                    trans.put(month, total);
                }
                else {
                    trans.put(month, trans.get(month)+total);
                }

            }
        }
        BarChart lineChart = findViewById(R.id.chart);
        lineChart.setVisibility(View.VISIBLE);
        BarDataSet dataSet = new BarDataSet(entries, "$");
        dataSet.setFormSize(18);
        dataSet.setValueTextSize(18);
        BarData data = new BarData(dataSet);
        data.notifyDataChanged();
        lineChart.setData(data);
        lineChart.notifyDataSetChanged();
        lineChart.invalidate();
    }

    public void yearGraph(List<ExpenseEntry> expenseEntries) {
        HashMap<Float, Float> trans = new HashMap<Float, Float>();
        List<BarEntry> entries = new ArrayList<BarEntry>();
        final HashMap<Float, String> xnums = new HashMap<Float, String>();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String[] date_array = date.split("-");
        float year = Float.parseFloat(date_array[0]);
        float test_year = 1900;
        float total = 0;
        float month;
        for (int i = 0; i < expenseEntries.size(); i++) {
            ExpenseEntry e = expenseEntries.get(i);
            test_year += e.getDate().getYear();

            if (test_year == year) {
                month = e.getDate().getMonth();
                xnums.put(month, Float.toString(year));
                total += e.getAmount().floatValue();
                entries.add(new BarEntry(e.getDate().getDate(), total));
                test_year -= e.getDate().getYear();
                if (!trans.containsKey(month)) {
                    trans.put(month, total);
                }
                else {
                    trans.put(month, trans.get(month)+total);
                }
            }
            else { test_year -= e.getDate().getYear(); }
        }

        BarChart lineChart = findViewById(R.id.chart);
        lineChart.setVisibility(View.VISIBLE);
        BarDataSet dataSet = new BarDataSet(entries, "$");
        dataSet.setValueTextSize(18);
        dataSet.setFormSize(18);
        BarData data = new BarData(dataSet);
        data.notifyDataChanged();
        lineChart.setData(data);
        lineChart.notifyDataSetChanged();
        lineChart.invalidate();
    }

    public void pieChart(List<ExpenseEntry> expenseEntries) {

        List<PieEntry> entries = new ArrayList<PieEntry>();
        HashMap<String, Integer> cat = new HashMap<String, Integer>();
        for (int i = 0; i < expenseEntries.size(); i++) {
            ExpenseEntry e = expenseEntries.get(i);
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
