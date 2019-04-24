package com.teashop.teacharge.prefs;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefSetting {
    Context _ctx;

    private SharedPreferences sharedPrefs;
    private static String PREFERENCE_ACCESS_KEY = "INDICAL";
    private static String BILL__KEY = "BILLID";

    private SharedPreferences.Editor prefEditor;
    public PrefSetting(Context ctx)
    {
        this._ctx = ctx;
        sharedPrefs = ctx.getSharedPreferences(PREFERENCE_ACCESS_KEY, Context.MODE_PRIVATE);
        prefEditor = sharedPrefs.edit();
    }

    public void SaveBillId(int BillID)
    {
        prefEditor.putInt(BILL__KEY,BillID);
        prefEditor.commit();
    }

    public  int GetBillId()
    {
        return sharedPrefs.getInt(BILL__KEY,0);
    }



}
