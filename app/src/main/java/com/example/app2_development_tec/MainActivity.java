package com.example.app2_development_tec;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Spinner spnMathTypes;
    Button btnOpenMenu;

    EditText edtIntegerInput1;
    EditText edtIntegerInput2;

    TextView txtMathResult;
    Button btnCalculate;


    List<String> mathActions = new ArrayList<>(Arrays.asList("+", "-", "*", "/"));

    String selectedAction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spnMathTypes = findViewById(R.id.spnMathTypes);
        btnOpenMenu = findViewById(R.id.btnOpenMenu);
        edtIntegerInput1 = findViewById(R.id.edtIntegerInput1);
        edtIntegerInput2 = findViewById(R.id.edtIntegerInput2);
        txtMathResult = findViewById(R.id.txtMathResult);
        btnCalculate = findViewById(R.id.btnCalculate);

        initializeSpinner(R.id.spnMathTypes);

        btnOpenMenu.setOnClickListener(v -> showPopOpMathMenu());

        btnCalculate.setOnClickListener(v -> calculate());

        spnMathTypes.setOnItemSelectedListener(new setOnItemSelectedListenerHandler(this));


    }



    public void showPopOpMathMenu()
    {
        PopupMenu popupMenu = new PopupMenu(this, btnOpenMenu);

        for (int i = 0; i < mathActions.size(); i++) {
            popupMenu.getMenu().add(Menu.NONE, i, Menu.NONE, mathActions.get(i));
        }

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                selectedAction = menuItem.toString();
                calculate();
                return true;
            }
        });
        popupMenu.show();
    }

    public void calculate()
    {
        int input1 = Integer.parseInt(edtIntegerInput1.getText().toString());
        int input2 = Integer.parseInt(edtIntegerInput2.getText().toString());
        Log.d(TAG, "Input1: " + input1 );
        Log.d(TAG, "Input2: " + input2 );
        Log.d(TAG, "SelectedAction: " + selectedAction );
        switch (selectedAction)
        {
            case "+":
                txtMathResult.setText(Integer.toString(input1 + input2));
                break;
            case "-":
                txtMathResult.setText(Integer.toString(input1 - input2));
                break;
            case "*":
                txtMathResult.setText(Integer.toString(input1 * input2));
                break;
            case "/":
                txtMathResult.setText(Integer.toString(input1 / input2));
                break;
        }
    }

    public void initializeSpinner(int spinnerId)
    {
        Spinner spinner = findViewById(spinnerId);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, mathActions);
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public class setOnItemSelectedListenerHandler implements AdapterView.OnItemSelectedListener
    {

        private MainActivity theActivity;

        public setOnItemSelectedListenerHandler(MainActivity activity)
        {
            theActivity = activity;
        }


        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            selectedAction = adapterView.getSelectedItem().toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }
}