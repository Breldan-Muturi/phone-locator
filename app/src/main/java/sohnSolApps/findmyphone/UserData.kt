package sohnSolApps.findmyphone

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences

class  UserData(context: Context) {
    var context:Context?= context
    var sharedRef:SharedPreferences?=null

    init {
        this.sharedRef= context.getSharedPreferences("userData",Context.MODE_PRIVATE)
    }

    fun savePhoneNumber(phoneNumber:String){

        val editor=sharedRef!!.edit()
        editor.putString("phoneNumber",phoneNumber)
        editor.apply()
    }

    fun loadPhoneNumber():String{

        val phoneNumber =sharedRef!!.getString("phoneNumber","empty")
        if ( phoneNumber.equals("empty")){
            val intent =Intent(context,LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context!!.startActivity(intent)
        }
        return  phoneNumber!!
    }

//    fun isFirstTimeLoad(){
//
//        val phoneNumber =sharedRef!!.getString("phoneNumber","empty")
//
//
//    }

    fun saveContactInfo(){
        var listOfTrackers=""
        for ((key,value) in myTrackers){

            if (listOfTrackers.length ==0 ){
                listOfTrackers = key + "%" + value
            }else{
                listOfTrackers += "%"+ key + "%" + value
            }
        }

        if (listOfTrackers.length ==0 ){
            listOfTrackers ="empty"
        }


        val editor=sharedRef!!.edit()
        editor.putString("listOfTrackers",listOfTrackers)
        editor.apply()

    }


    fun loadContactInfo(){
        myTrackers.clear()
        val listOfTrackers =sharedRef!!.getString("listOfTrackers","empty")

        if (!listOfTrackers.equals("empty")){
            val usersInfo=listOfTrackers!!.split("%").toTypedArray()
            var i=0
            while(i<usersInfo.size){

                myTrackers.put(usersInfo[i],usersInfo[i+1])
                i += 2
            }
        }
    }

    companion object {
        var myTrackers: MutableMap<String,String> = HashMap()
        fun formatPhoneNumber(phoneNumber:String):String {
            var onlyNumber= phoneNumber.replace("[^0-9]".toRegex(),"")
            if (phoneNumber[0]== '+') {
                onlyNumber ="+"+ phoneNumber
            }

            return  onlyNumber
        }
    }

}
