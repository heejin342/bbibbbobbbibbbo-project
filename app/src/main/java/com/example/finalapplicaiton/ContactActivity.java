package com.example.finalapplicaiton;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class ContactActivity extends AppCompatActivity {

    private ListView lv;
    public String temp ="";

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contactativity);

        lv = (ListView)findViewById(R.id.list);

        ArrayList<Person> m_orders = new ArrayList<Person>();

        // 폰 주소록
        Map<String, String> phone_address = ContactUtil.getAddressBook(this);

        @SuppressWarnings("rawtypes")
        Iterator ite = phone_address.keySet().iterator();
        while(ite.hasNext())
        {
            String phone = ite.next().toString();
            String name = phone_address.get(phone).toString();
            m_orders.add(new Person(name, phone));
        }

        PersonAdapter m_adapter = new PersonAdapter(this, R.layout.view_friend_list, m_orders);
        lv.setAdapter(m_adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View view, int position, long rowID)
            {
                doSelectFriend((Person)parent.getItemAtPosition(position));

            }});
    }

    // 한명 선택했을 때
    public void doSelectFriend(Person p)
    {
        Log.e("####", p.getName() + ", " + p.getNumber());
    }

    private class PersonAdapter extends ArrayAdapter<Person>
    {
        private ArrayList<Person> items;

        public PersonAdapter(Context context, int textViewResourceId, ArrayList<Person> items)
        {
            super(context, textViewResourceId, items);
            this.items = items;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            View v = convertView;
            if (v == null)
            {
                LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.view_friend_list, null);
            }
            Person p = items.get(position);
            if (p != null)
            {

                final TextView tt = (TextView) v.findViewById(R.id.name);
                final TextView bt = (TextView) v.findViewById(R.id.msg);



                if (tt != null)
                {
                    tt.setText(p.getName());
                }
                if(bt != null)
                {
                    bt.setText(p.getNumber());
                    bt.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            Intent intent1212=new Intent(ContactActivity.this,MainActivity.class);
                            intent1212.putExtra("PN2",String.valueOf(bt.getText()));
                            startActivity(intent1212);
                            finish();

                        }// end onClick
                    }); // end setOnClickListener

                }
            }
            return v;
        }

    }

    class Person
    {
        private String Name;
        private String Number;

        public Person(String _Name, String _Number)
        {
            this.Name = _Name;
            this.Number = _Number;
        }

        public String getName()
        {
            return Name;
        }

        public String getNumber()
        {
            return Number;
        }
    }

}