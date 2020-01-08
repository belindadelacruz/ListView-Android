package com.example.aplicacion01;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.aplicacion01.adapters.ContactoAdaptador;
import com.example.aplicacion01.helpers.QueueUtils;
import com.example.aplicacion01.models.Contacto;

public class MainActivity extends AppCompatActivity {
    ListView contactosList;
    ContactoAdaptador contactoAdaptador;

    QueueUtils.QueueObject queue = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contactosList = findViewById(R.id.contactosList);

        queue = QueueUtils.getInstance(this.getApplicationContext());

        contactoAdaptador = new ContactoAdaptador(this, Contacto.getCollection(), queue.getImageLoader());
        contactosList.setAdapter(contactoAdaptador);

    }
}
