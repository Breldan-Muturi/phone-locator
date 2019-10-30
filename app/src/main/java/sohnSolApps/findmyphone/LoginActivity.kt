package sohnSolApps.findmyphone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login.*
import java.text.SimpleDateFormat
import java.util.*

private const val TAG = "LoginActivity"
class LoginActivity : AppCompatActivity() {

    var mAuth: FirebaseAuth? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mAuth = FirebaseAuth.getInstance()
        signInAnonymously()
    }

    fun signInAnonymously(){
        mAuth!!.signInAnonymously()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(applicationContext, "Authentication Successful.",
                        Toast.LENGTH_SHORT).show()
                    val user = mAuth!!.getCurrentUser()
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(applicationContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }

                // ...
            }    }

    fun buRegisterEvent(view: View) {

        val userData = UserData(this)
        userData.savePhoneNumber(enterPhoneNumber.text.toString())
        //get Datatime
        val df = SimpleDateFormat("yyyy/MMM/dd HH:MM:SS")
        val date = Date()
        //save to database
        val mDatabase = FirebaseDatabase.getInstance().reference
        mDatabase.child("UserLocation").child(enterPhoneNumber.text.toString()).child("request").setValue(df.format(date).toString())
        mDatabase.child("UserLocation").child(enterPhoneNumber.text.toString()).child("Finders").setValue(df.format(date).toString())
        finish()
    }
}
