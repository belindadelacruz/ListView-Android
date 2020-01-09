package com.example.aplicacion01.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.aplicacion01.MainActivity;
import com.example.aplicacion01.helpers.QueueUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Contacto {
    public String phone;
    public String nickname;
    public String image;

    public  String getSmallImage(){
        return  this.image;
    }

    public Contacto(String _phone, String _nickname, String _image) {
        this.phone = _phone;
        this.nickname = _nickname;
        this.image = _image;
    }

    public static ArrayList getCollection() {
        ArrayList<Contacto> collection = new ArrayList<>();
        collection.add(new Contacto("912353923", "Muelle","https://static5.depositphotos.com/1013513/502/i/450/depositphotos_5021225-stock-photo-beautiful-morning-at-spring-before.jpg"));
        collection.add(new Contacto("985346343", "Montañas","https://culturafotografica.es/wp-content/uploads/2018/01/paisajes-marc-adamus-1-750x500.jpg"));
        collection.add(new Contacto("983453463", "Paramo","https://image.freepik.com/foto-gratis/muchacha-triguena-turistica-bonita-que-relaja-cerca-montanas_90153-122.jpg"));
        collection.add(new Contacto("943514313", "Paisaje","https://image.freepik.com/foto-gratis/mujeres-montana-tomar-foto-telefono-inteligente_33807-112.jpg"));
        return collection;
    }

    public static void sendRequestPOST(QueueUtils.QueueObject o, final MainActivity _interface) {
        String url = "http://rrojasen.alwaysdata.net/purchaseorders.json";
        url = "http://fipo.equisd.com/api/users/new.json";
        //url = "http://192.168.58.3:8056/api/users/new.json";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //Do it with this it will work
                            JSONObject _response = new JSONObject(response);
                            if (_response.has("object")) {
                                JSONObject object_response = null;
                                try {
                                    object_response = _response.getJSONObject("data");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                if ( object_response != null ) {
                                    try {
                                        System.out.println(object_response.getInt("id"));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("first_name","aaa");
                params.put("last_name","ddd");
                params.put("avatar","xxx");

                return params;
            }
        };
        o.addToRequestQueue(stringRequest);
    }

    public static void injectContactsFromCloud(final QueueUtils.QueueObject o,
                                               final ArrayList<Contacto> contactos,
                                               final MainActivity _interface) {
        String url = "http://fipo.equisd.com/api/users.json";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        if (response.has("objects")) {

                            try {
                                JSONArray list = response.getJSONArray("objects");
                                for (int i=0; i < list.length(); i++) {
                                    JSONObject o = list.getJSONObject(i);
                                    contactos.add(new Contacto(o.getString("first_name"),
                                            o.getString("last_name"),o.getString("avatar")));
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            _interface.refreshList(); // Esta función debemos implementarla
                            // en nuestro activity
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        o.addToRequestQueue(jsonObjectRequest);
    }


}