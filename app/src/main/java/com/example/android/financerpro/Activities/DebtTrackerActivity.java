package com.example.android.financerpro.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import com.example.android.financerpro.BaseActivities.BaseDrawerActivity;
import com.example.android.financerpro.Adapters.MyAdapter;
import com.example.android.financerpro.DataModels.Debt;
import com.example.android.financerpro.R;

public class DebtTrackerActivity extends BaseDrawerActivity {

    private AppCompatActivity mClass;
    private List<Debt> debtList = new ArrayList<>();
    private EditText editText;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private MyAdapter mAdapter;
    protected Toolbar toolbar;
    private Paint p = new Paint();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContent(R.layout.activity_debt_tracker);
        mClass = new AppCompatActivity();

        editText = (EditText) findViewById(R.id.edit_query);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                displayPop(v);
            }
        });

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

                final int position = (int) viewHolder.getAdapterPosition();
                if (direction == ItemTouchHelper.RIGHT) {
                    AlertDialog.Builder a = new AlertDialog.Builder(DebtTrackerActivity.this);
                    LayoutInflater inflater = DebtTrackerActivity.this.getLayoutInflater();

                    View dView = inflater.inflate(R.layout.swipe_delete, null);
                    a.setView(dView);
                    a.show();
                    Button delete = (Button) dView.findViewById(R.id.Button09);
                    delete.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            debtList.remove(position);
                            mAdapter.notifyDataSetChanged();;
                        }
                    });
                    //debtList.remove(position);
                    mAdapter.notifyDataSetChanged();
                }
                else {
                    final Debt temp = debtList.get(position);
                    AlertDialog.Builder a = new AlertDialog.Builder(DebtTrackerActivity.this);
                    LayoutInflater inflater = DebtTrackerActivity.this.getLayoutInflater();
                    View dialogView = inflater.inflate(R.layout.swipe_dialog, null);
                    a.setView(dialogView);
                    a.show();
                    Button call = (Button) dialogView.findViewById(R.id.Button04);
                    Button text = (Button) dialogView.findViewById(R.id.Button03);
                    call.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            call(temp);
                        }
                    });
                    text.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            sms(temp);
                        }
                    });
                }
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                // view the background view
                //Bitmap icon;

                if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {

                }

            }
        };

        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
        DebtTrackerActivity m = this;
        mAdapter = new MyAdapter(debtList, getApplicationContext(), m);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        prep_debt();

    }

    public void prep_debt() {
        Debt debt = new Debt("Clark K", 20.75, "555-555-5555");
        debtList.add(debt);

        debt = new Debt("Bruce W", 5.00, "555-555-5555");
        debtList.add(debt);

        debt = new Debt("Tony S", 6.00, "555-555-5555");
        debtList.add(debt);

        debt = new Debt("Peter P", 3.75, "555-555-5555");
        debtList.add(debt);

        debt = new Debt("Wally W", 10.05, "555-555-5555");
        debtList.add(debt);

        debt = new Debt("Barry A", 50.76, "555-555-5555");
        debtList.add(debt);

        debt = new Debt("Austin P", 300.00, "555-555-5555");
        debtList.add(debt);

        debt = new Debt("Peter P", 77.77, "555-555-5555");
        debtList.add(debt);

        debt = new Debt("John", 0, "555-555-5555");
        debtList.add(debt);

        mAdapter.notifyDataSetChanged();
    }

    public void displayPop(View v) {

        AlertDialog.Builder alert = new AlertDialog.Builder(DebtTrackerActivity.this);

        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog, null);
        alert.setView(dialogView);
        final EditText edt = (EditText) dialogView.findViewById(R.id.edit_query2);

        alert
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                String input3 = edt.getText().toString();
                                add_debt2(input3);
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });
        alert.setTitle("Add New Debt");
        alert.setMessage("Enter Info Below");
        alert.show();
        mAdapter.notifyDataSetChanged();

    }

    public void add_debt2(String input) {
        String input2 = input.replaceAll(" ","");
        String[] info = input2.split(",");
        if (info.length < 2) {
            //Debt d = new Debt(info[0], 45.5, "655");
            //debtList.add(d);
            mAdapter.notifyDataSetChanged();
            return;
        }
        double amount = Double.parseDouble(info[1]);
        Debt new_debt;
        if (info.length >= 3) {
            new_debt = new Debt(info[0], amount, info[2]);
        }
        else { new_debt = new Debt(info[0], amount); }
        debtList.add(new_debt);

        mAdapter.notifyDataSetChanged();
    }

    public void call(Debt temp) {
        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
        sendIntent.setData(Uri.parse("tel:" + temp.getContact()));
        startActivity(sendIntent);
    }

    public void sms(Debt temp) {
        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
        sendIntent.setData(Uri.parse("sms:" + temp.getContact()));
        startActivity(sendIntent);
    }

    public void search(View v) {
        EditText editText = (EditText) findViewById(R.id.edit_query);
        String input = editText.getText().toString();

        int x = (int) debtList.size();
        int new_pos = 0;
        int old_pos = 0;
        Debt holder;
        Debt old_debt = debtList.get(0);

        for (int i = 0; i < x; i++) {
            String test = debtList.get(i).getName();
            if (input.equals(test)) {
                holder = debtList.get(i);
                old_pos = i;
                if (old_pos == 0) {
                    break;
                }
                else {
                    debtList.set(i, old_debt);
                    debtList.set(new_pos, holder);
                    new_pos++;
                    old_debt = debtList.get(new_pos);
                    mAdapter.notifyDataSetChanged();
                }

            }
        }
    }

}
