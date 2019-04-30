package com.example.tropicollage;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tropicollage.Adapters.HauseAdaper;

import java.util.ArrayList;
import java.util.List;

public class ReservarActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Spinner spi_hause, spi_car;
    Button btn_reservar;
    TextView txt_datos_personales;
    String[] cliente_datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservar);

        //--------------------- init ------------------------
        spi_hause = (Spinner) findViewById(R.id.spi_hause);
        spi_car = (Spinner) findViewById(R.id.spi_car);
        txt_datos_personales = (TextView) findViewById(R.id.txt_datos_personales);
        btn_reservar = (Button) findViewById(R.id.btn_reservar);
        Bundle datos_intent = getIntent().getExtras();

        // save data to Intent from DatosPersonalesActivity
        cliente_datos = new String[3];
        cliente_datos[0] = datos_intent.getString("phone");
        cliente_datos[1] = datos_intent.getString("full_name");
        cliente_datos[2] = datos_intent.getString("country");

        btn_reservar.setOnClickListener(this);

        //-------------------- code -------------------------

        // asignar los datos de la Activity DatosPersonalesActivity al txt_datos_personales

        txt_datos_personales.setText(cliente_datos[0] +" - "+ cliente_datos[1] +" - "+ cliente_datos[2]);

        // crear lista de casas y asignarlas a el adaptador para la lista
        List<HauseAdaper.Hause> listHause = new ArrayList<HauseAdaper.Hause>();
        listHause.add(new HauseAdaper.Hause(1,"Casa 1",20));
        listHause.add(new HauseAdaper.Hause(2,"Casa 2",25));
        listHause.add(new HauseAdaper.Hause(3,"Casa 3",40));
        listHause.add(new HauseAdaper.Hause(4,"Casa 4",30));

        HauseAdaper arrayAdapterHause = new HauseAdaper(this, listHause);
        //spi_hause.setAdapter(arrayAdapterHause);

        ArrayAdapter arrayAdapterH = new ArrayAdapter(this,R.layout.item_hause,getResources().getStringArray(R.array.array_hause));
        spi_hause.setAdapter(arrayAdapterH);

        ArrayAdapter arrayAdapterCar = new ArrayAdapter(this,R.layout.item_hause,getResources().getStringArray(R.array.array_car));
        spi_car.setAdapter(arrayAdapterCar);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // seleccion de la casa
            case R.id.spi_hause:
                break;

            // selccion de el carro a alquilar
            case R.id.spi_car:
                break;
            // Recopilar los datos selecionados por el cliente y enviarlos por un SMS
            // phone = ########### | name = Leandro Jose Capdesuner Garcia | country = Cuba | hause = $20 - Casa 1 | car = $25 Car 2 | total = 4$5
            case R.id.btn_reservar:
                String sms_text = "phone = "+ cliente_datos[0] + " | "
                            +"name = "+ cliente_datos[1]+ " | "
                            +"country = "+ cliente_datos[2]+ " | ";

                sms_text += "hause = " + spi_hause.getSelectedItem().toString() +" | "+ "car = " + spi_car.getSelectedItem().toString();
                try {

                    // verificar permiso de SMS
                    int permisionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
                    if (permisionCheck != PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(this,getResources().getString(R.string.str_sms_permision_error),Toast.LENGTH_LONG).show();
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS},225);
                    }
                    // usar SmsManger
                    SmsManager sms = SmsManager.getDefault();

                    //Insertar los datos para enviar el SMS  roly-5358342026    lenadro Father-58829903   cliente - 58040385
                    sms.sendTextMessage("58040385",null,"SMS Generado App-TropicalCollage =>> "+sms_text,null,null);

                    // confirmacion de envio de SMS
                    int contrar = sms_text.length() + 37;
                    Toast.makeText(this,getResources().getString(R.string.str_sms_confirm)+contrar,Toast.LENGTH_LONG).show();


                }catch (Exception e){
                    // error al enviar mensaje
                    Toast.makeText(this,getResources().getString(R.string.str_sms_error_send),Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this,spi_hause.getSelectedItem().toString(),Toast.LENGTH_LONG);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
