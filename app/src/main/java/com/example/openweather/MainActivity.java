package com.example.openweather;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.openweather.api.RetrofitConfig;
import com.example.openweather.model.Forecast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public final String KEY = "6ce076990a18909a88f380f89a02aef4";

    EditText inputCidade, inputPais;
    TextView outputTemp, outputTempMin, outputTempMax, outputHumid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputCidade   = findViewById(R.id.inputCidade);
        inputPais     = findViewById(R.id.inputPais);
        outputTemp    = findViewById(R.id.outputTemp);
        outputTempMin = findViewById(R.id.outputTMin);
        outputTempMax = findViewById(R.id.outputTMax);
        outputHumid   = findViewById(R.id.outputHumid);

    }

    public void getData(View view) {
        
        if (inputCidade.getText().toString().isEmpty() || inputPais.getText().toString().isEmpty()) {
            Toast.makeText(this, "Informe a Cidade e o País", Toast.LENGTH_SHORT).show();
        } else {
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Aguarde ...");
            progressDialog.show();

            String location = inputCidade.getText().toString()
                    + ","
                    + inputPais.getText().toString();

            Call<Forecast> call = new RetrofitConfig()
                    .getDataService()
                    .getForecast(KEY, "metric", location);

            call.enqueue(new Callback<Forecast>() {
                @Override
                public void onResponse(Call<Forecast> call, Response<Forecast> response) {
                    if (response.isSuccessful()) {
                        Forecast forecast = response.body();

                        System.out.println(forecast.getBase() + ":" + forecast.getMain().getTemp());

                        outputTemp.setText(forecast.getMain().getTemp() + "C");
                        outputTempMin.setText(forecast.getMain().getTemp_min() + "C");
                        outputTempMax.setText(forecast.getMain().getTemp_max() + "C");
                        outputHumid.setText(forecast.getMain().getHumidity() + "%");

                        progressDialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Forecast> call, Throwable t) {
                    Log.e("ERRO", "ERRO");
                }
            });
        }
    }
}