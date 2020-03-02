package com.example.pullokone;

//LÄHDE: https://www.youtube.com/watch?v=GtxVILjLcw8, https://www.youtube.com/watch?v=G03ZR0jKtVs

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import static android.widget.Toast.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    BottleDispenser kone = BottleDispenser.getInstance();
    private Spinner spinner;
    private SeekBar seekBar;
    public Context context = null;
    public ArrayList<Bottle> ostetut = new ArrayList<Bottle>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        System.out.println("KATOTÄNNE!?!?!?!?" + context.getFilesDir());

        seekBar = (SeekBar) findViewById(R.id.seekBar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            double progressi = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressi = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
               Toast.makeText(MainActivity.this, progressi  +" €", LENGTH_SHORT).show();
            }
        });

        Button button1 = findViewById(R.id.lisaarahaa);
        Button button2 = findViewById(R.id.palautarahat);
        Button button4 = findViewById(R.id.kuitti);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button4.setOnClickListener(this);

        spinner = findViewById(R.id.spinner);

        ArrayAdapter<Bottle> adapter = new ArrayAdapter<Bottle>(this,
                android.R.layout.simple_spinner_item, kone.bottle_arraylist);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void valitse(View v){
        if(kone.bottle_arraylist.size() != 0) {
            int id, tarkistus = 0;
            Bottle pullo = (Bottle) spinner.getSelectedItem();
            id = pullotiedot(pullo);
            for (int i = 0; i < kone.bottle_arraylist.size(); i++) {
                if (kone.bottle_arraylist.get(i).getId() == id) {
                    String temp;
                    ostetut.add(kone.bottle_arraylist.get(i));
                    temp = kone.buyBottle(i + 1);
                    Toast.makeText(this, temp, LENGTH_LONG).show();
                    tarkistus = 1;
                    break;
                }
            }
            if (tarkistus == 0) {
                Toast.makeText(this, "We have ran out of stock!", LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this, "We have ran out of stock!", LENGTH_LONG).show();
        }
    }

    public int pullotiedot(Bottle pullo){
        String name = pullo.getName();
        int id = pullo.getId();
        double size  = pullo.getSize();
        Toast.makeText(this, "You bought bottle " + name + " " + size, LENGTH_LONG).show();
        return id;
    }

    @Override
    public void onClick(View v) {
        double temp, raha = 0;
        int valinta = 1;
        String pullo;
        switch (v.getId()){
            case R.id.lisaarahaa:
                raha = seekBar.getProgress();
                kone.addMoney(raha);
                seekBar.setProgress(0);
                makeText(this, "Klink! Added " + raha + " €", LENGTH_SHORT).show();
                break;
            case R.id.palautarahat:
                temp = kone.getMoney();
                makeText(this, "Klink klink. Money came out! You got " + temp + "€ back.", LENGTH_SHORT).show();
                break;
            case R.id.kuitti:
                writeFile();
                break;
        }
    }

    public void writeFile(){
        if(ostetut.size() != 0) {
            String nimi = ostetut.get(ostetut.size() - 1).getName();
            double hinta = ostetut.get(ostetut.size() - 1).getPrice();
            String a = "Nimi, Hinta:\n" + nimi + " " + hinta + "€\n";
            try {
                OutputStreamWriter ows = new OutputStreamWriter(context.openFileOutput("kuitti.txt", Context.MODE_PRIVATE));
                ows.write(a);
                ows.close();
                makeText(this, "You have received a receipt!", LENGTH_SHORT).show();
            } catch (IOException e) {
                Log.e("IOExecption", "Virhe");
            }
        }else{
            makeText(this, "You haven't bought anything", LENGTH_SHORT).show();
        }
    }



}

