package com.example.finalapplicaiton;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;



import java.util.Vector;

public class BeaconAdapter extends BaseAdapter{
    private Vector<Beacon> beacons;
    private LayoutInflater layoutInflater;

    public BeaconAdapter(Vector<Beacon> beacons, LayoutInflater layoutInflater){
        this.beacons=beacons;
        this.layoutInflater=layoutInflater;
    }
    @Override
    public int getCount(){
        return beacons.size();
    }
    @Override
    public Object getItem(int position){
        return beacons.get(position);
    }

    @Override
    public long getItemId(int position){
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        BeaconHolder beaconHolder;
        if(convertView==null){
            beaconHolder = new BeaconHolder();
            convertView=layoutInflater.inflate(R.layout.item, parent, false);
            beaconHolder.addresss=convertView.findViewById(R.id.address);
            beaconHolder.rssi=convertView.findViewById(R.id.rssi);
            beaconHolder.txPower=convertView.findViewById(R.id.txPower);
            beaconHolder.distance=convertView.findViewById(R.id.distance);
            beaconHolder.time=convertView.findViewById(R.id.time);
            beaconHolder.name=convertView.findViewById(R.id.name);
            convertView.setTag(beaconHolder);

        }
        else{
            beaconHolder=(BeaconHolder)convertView.getTag();
        }
        beaconHolder.time.setText("시간:"+beacons.get(position).getNow());
        beaconHolder.addresss.setText("MAC ADDR:"+beacons.get(position).getAddress());
        beaconHolder.rssi.setText("RSSI:"+beacons.get(position).getRssi()+"dBm");
        beaconHolder.txPower.setText("txPower:"+beacons.get(position).getTxPower());
        beaconHolder.distance.setText("distance:"+beacons.get(position).getDistance()+"m");
        beaconHolder.name.setText("Device Name:"+beacons.get(position).getName());
        return convertView;
    }

    private class BeaconHolder{
        TextView addresss;
        TextView rssi;
        TextView time;
        TextView name;
        TextView txPower;
        TextView distance;
    }
}