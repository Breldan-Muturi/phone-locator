package sohnSolApps.findmyphone

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences

class UserData {
    var context: Context?= null
    var sharedPreferences: SharedPreferences? = null
    constructor(context: Context) {
        this.context = context
        this.sharedPreferences = context.getSharedPreferences("userData", Context.MODE_PRIVATE)
    }

    fun savePhoneNumber(phoneNumber: String){

        val editor = sharedPreferences!!.edit()
        editor.putString("phoneNumber", phoneNumber)
        editor.commit()
    }

    fun loadPhoneNumber():String? {

        val phoneNumber = sharedPreferences!!.getString("phoneNumber", "empty")
        if(phoneNumber.equals("empty")){
            val intent = Intent(context, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context!!.startActivity(intent)
        }

        return phoneNumber
    }
}