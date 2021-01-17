package com.example.finalapplicaiton;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import java.util.HashMap;
import java.util.Map;

public class ContactUtil {

    public static Map<String, String> getAddressBook(Context context, boolean isIDD)
    {
        Map<String, String> result = new HashMap<String, String>();
        Cursor cursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        while(cursor.moveToNext())
        {
            int phone_idx = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            int name_idx = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            String phone = cursor.getString(phone_idx);
            String name = cursor.getString(name_idx);

            if (isIDD)
                result.put(getIDD(phone), name);
            else
                result.put(phone, name);

//          Log.e("####getAddressBook", name + " : "+phone);
        }

        return result;
    }
    /**
     * 주소록의 이름, 전화번호 맵을 가져온다
     * @param context
     * @return 이름, 전화번호 map
     */
    public static Map<String, String> getAddressBook(Context context)
    {
        return getAddressBook(context, false);
    }

    /**
     * 국제전화 형식으로 변경한다.
     * @param phone
     * @return 국제전화번호 규격
     */
    public static String getIDD(String phone)
    {
        String result = phone;

        try {
            result = "82" + Long.parseLong(phone);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}