package com.example.remotehomeelectricalcontrolsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.TextView;

import com.example.remotehomeelectricalcontrolsystem.Adapter.DeviceAdapter;
import com.example.remotehomeelectricalcontrolsystem.Model.CheckDevice;
import com.example.remotehomeelectricalcontrolsystem.Model.CheckDHT;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RoomActivity extends AppCompatActivity {
    List<CheckDevice> deviceList;
    DeviceAdapter adapter;
    RecyclerView rec_device;
    TextView txtHumidity;
    TextView txtTemperature;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        Bundle bundle = getIntent().getExtras();
        rec_device = findViewById(R.id.rec_room);
        deviceList = new ArrayList<>();
        adapter = new DeviceAdapter();
        txtHumidity = findViewById(R.id.txtHumidity);
        txtTemperature = findViewById(R.id.txtTemperature);

        rec_device.setLayoutManager(new GridLayoutManager(this, 2));
        String keyRoom = bundle.getString("keyRoom");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("test1");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot data : snapshot.getChildren()){
                    String houseId = data.getKey();
                    for(DataSnapshot dataFloor : data.child("floors").getChildren()){
                        String idFloor = dataFloor.getKey();
                        String nameFloor = dataFloor.child("name").getValue(String.class);
                        String idKeyFloor = bundle.getString("Floor1");
                        if ("e72cf36f-f9c5-4dee-b11a-951c0e3dc638".equals(idFloor)){
                            for(DataSnapshot dataRoom : dataFloor.child("rooms").getChildren()){
                                String idRoom = dataRoom.getKey();
                                String nameRoom = dataRoom.child("name").getValue(String.class);
                                String imgUrl = dataRoom.child("imgUrl").getValue(String.class);

                                for(DataSnapshot dataDHT : dataRoom.child("DHT").getChildren()){
                                    String idDHT = dataDHT.getKey();
                                    Double humidity = dataDHT.child("humidity").getValue(Double.class);
                                    Double temperature = dataDHT.child("temperature").getValue(Double.class);
                                    Log.i("Humidity" , humidity.toString());
                                    Log.i("Temperature" , temperature.toString());
                                    List<CheckDHT> dhtList = new ArrayList<>();
//                                    if(humidity == 0 && temperature == 0){
//                                        txtHumidity.setText(String.valueOf(humidity));
//                                        txtTemperature.setText(String.valueOf(temperature));
//                                    }
                                    //txtHumidity.setText(String.valueOf(humidity));
                                    //txtTemperature.setText(String.valueOf(temperature));
                                    dhtList.add(new CheckDHT(humidity , temperature));
                                    for(DataSnapshot dataDevice : dataRoom.child("devices").getChildren()){
                                        String idDevice = dataDevice.getKey();
                                        Long listCheck = dataRoom.child("devices").getChildrenCount();
                                        Log.i("List Device" , String.valueOf(listCheck));
                                        //List<CheckDHT> dhtList = new ArrayList<>();
                                        String nameDevice = dataDevice.child("name").getValue(String.class);
                                        int endTime = dataDevice.child("endTime").getValue(Integer.class);
                                        int startTime = dataDevice.child("startTime").getValue(Integer.class);
                                        int state = dataDevice.child("state").getValue(Integer.class);
                                        System.out.println("Check Key Room " + keyRoom + "" + idRoom);
                                        if (keyRoom.equals(idRoom)) {
                                            Log.i("Check If", "Ok");
                                            deviceList.add(new CheckDevice(endTime, nameDevice, startTime, state));
                                            adapter.updateDeviceList(deviceList);
                                            adapter.notifyDataSetChanged();
                                        }

                                        Log.i("Get Data Room" , "Ok");
                                        Log.i("keyRoom" , keyRoom +" @ "+ idRoom);
                                        //Log.i("HUMIDITY" , String.valueOf(humidity));
                                        //Log.i("Temperature" , String.valueOf(temperature))

                                    }
                                }

//                                for(DataSnapshot dataDevice : dataRoom.child("devices").getChildren()){
//                                    String idDevice = dataDevice.getKey();
//                                    Long listCheck = dataRoom.child("devices").getChildrenCount();
//                                    Log.i("List Device" , String.valueOf(listCheck));
//                                    //List<CheckDHT> dhtList = new ArrayList<>();
//                                            String nameDevice = dataDevice.child("name").getValue(String.class);
//                                            int endTime = dataDevice.child("endTime").getValue(Integer.class);
//                                            int startTime = dataDevice.child("startTime").getValue(Integer.class);
//                                            int state = dataDevice.child("state").getValue(Integer.class);
//                                            System.out.println("Check Key Room " + keyRoom + "" + idRoom);
//                                            if (keyRoom.equals(idRoom)) {
//                                                Log.i("Check If", "Ok");
//                                                deviceList.add(new CheckDevice(endTime, nameDevice, startTime, state));
//                                                //adapter.updateDeviceList(deviceList);
//                                                adapter.notifyDataSetChanged();
//                                            }
//
//                                        Log.i("Get Data Room" , "Ok");
//                                        Log.i("keyRoom" , keyRoom +" @ "+ idRoom);
//                                        //Log.i("HUMIDITY" , String.valueOf(humidity));
//                                        //Log.i("Temperature" , String.valueOf(temperature))
//
//                                }
//                                for(DataSnapshot dataDHT : dataRoom.child("DHT").getChildren()){
//                                    String idDHT = dataDHT.getKey();
//                                    Double humidity = dataDHT.child("humidity").getValue(Double.class);
//                                    Double temperature = dataDHT.child("temperature").getValue(Double.class);
//                                    List<CheckDHT> dhtList = new ArrayList<>();
//                                    dhtList.add(new CheckDHT(humidity , temperature));
//                                    Log.i("humidity" , String.valueOf(humidity));
//                                    Log.i("temperature",String.valueOf(temperature));
//                                }
                                Log.i("CheckData" , idRoom + nameRoom);
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        adapter.updateDeviceList(deviceList);
        rec_device.setAdapter(adapter);
    }
}