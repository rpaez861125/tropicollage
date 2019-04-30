package com.example.tropicollage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DatosPeronalesActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_next;
    TextView txt_full_name, txt_phone, txt_country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_personales);

        //----------------------- init components ---------------------------
        btn_next = (Button) findViewById(R.id.btn_next);
        btn_next.setOnClickListener(this);
        txt_full_name = (TextView) findViewById(R.id.txt_fullName);
        txt_phone = (TextView) findViewById(R.id.txt_phone);
        txt_country = (TextView) findViewById(R.id.txt_country);

        //----------------------- dev ---------------------------

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            // accion para el boton Siguiente de los datos personales
            // pasao los datos a la actividad de Reservaciones
            case R.id.btn_next:
                Intent newAct = new Intent(this , ReservarActivity.class);
                newAct.putExtra("full_name",txt_full_name.getText().toString());
                newAct.putExtra("phone",txt_phone.getText().toString());
                newAct.putExtra("country",txt_country.getText().toString());
                startActivity(newAct);
                break;
        }
    }
}
