package com.michi.michislifever2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;


public class Detalle extends AppCompatActivity {

    private static String mqttHost="tcp://proyectomichi.cloud.shiftr.io:1883";
    private static String IdUsuario="AppAndroid";
    private static String Topico="correos";
    private static String User="proyectomichi";
    private static String Pass="vx36wqzUP9kKtqzE";
    //variables de impresion de datos
    private TextView textView;
    private EditText editTextMessage;
    private Button buttonSendMessage;
    private MqttClient mqttCliente;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        //enlazar variables
        textView=findViewById(R.id.textView);
        editTextMessage=findViewById(R.id.editTextMessage);
        buttonSendMessage=findViewById(R.id.buttonSendMessage);


        try {

            mqttCliente=new MqttClient(mqttHost,IdUsuario,null );
            MqttConnectOptions options=new MqttConnectOptions();
            options.setUserName(User);
            options.setPassword(Pass.toCharArray());

            //conexion al servidor
            mqttCliente.connect(options);
            //si se conecta imprime un mensaje
            Toast.makeText(this,"Aplicacion conectada al servidor MQTT",Toast.LENGTH_SHORT).show();

            //manejo de entrega de datos y perdida de conexion
            mqttCliente.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    Log.d("Mqtt","Conexion perdida");

                }

                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    String payload=new String(message.getPayload());
                    runOnUiThread(()-> textView.setText(payload));

                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    Log.d("Mqtt","Entrega Completa");

                }
            });

            //el cliente se suscribe al Topico
            mqttCliente.subscribe(Topico);
            //Envio de mensaje al presionar el boton
            buttonSendMessage.setOnClickListener(v -> {

                String message=editTextMessage.getText().toString();
                if (!message.isEmpty()){
                    sendMessage(message);
                    editTextMessage.getText().clear();

                }else {
                    Toast.makeText(this,"Ingrese un Mensaje",Toast.LENGTH_SHORT).show();
                }
            });



        }catch (MqttException e){

            e.printStackTrace();
        }


    }

    //metodo para enviar el mensaje
    private void sendMessage(String message){
        try {
            MqttMessage mqttMessage=new MqttMessage(message.getBytes());
            mqttCliente.publish(Topico,mqttMessage);

        }catch (MqttException e){

            e.printStackTrace();
        }

    }

    //se desconecta al cerrar la aplicación
    @Override
    protected void onDestroy(){

        super.onDestroy();
        try {
            mqttCliente.disconnect();


        }catch (MqttException e){


            e.printStackTrace();
        }
    }
}
