package com.example.remotehomeelectricalcontrolsystem.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.remotehomeelectricalcontrolsystem.Model.CheckDHT;
import com.example.remotehomeelectricalcontrolsystem.Model.CheckDevice;
import com.example.remotehomeelectricalcontrolsystem.Model.Room;
import com.example.remotehomeelectricalcontrolsystem.R;

import java.util.ArrayList;
import java.util.List;

public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.ViewHolder> {
    List<CheckDevice> deviceList = new ArrayList<>();
    public void updateDeviceList(List<CheckDevice> list) {
        this.deviceList = list;
        Log.d("Success Adapter" , "Have Data In Adapter");
        Log.d("Size ListMovie" , String.valueOf(list.size()));

        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public DeviceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DeviceAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_device_recycler , parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull DeviceAdapter.ViewHolder holder, int position) {
        holder.txtNameDevice.setText(deviceList.get(position).getNameDevice());
        //holder.txtNameDevice.setText(String.valueOf(deviceList.get(position).getDhtList().add(new CheckDHT(27.5 , 30.5))));
//        holder.txtTemperature.setText(String.valueOf(deviceList.get(position).getDhtList().get(position).getTemperature()));
//        Log.i("Humidity Adapter" , String.valueOf(deviceList.get(position).getDhtList().get(position).getTemperature()));
//        holder.txtHumidity.setText(String.valueOf(deviceList.get(position).getDhtList().get(position).getHumidity()));
//        Log.i("Humidity Adapter" , String.valueOf(deviceList.get(position).getDhtList().get(position).getTemperature()));

    }

    @Override
    public int getItemCount() {
        return deviceList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNameDevice;
        ImageView imgDevice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNameDevice =  itemView.findViewById(R.id.txtNameDevice);
            imgDevice = itemView.findViewById(R.id.imgDevice);

        }
    }
}
