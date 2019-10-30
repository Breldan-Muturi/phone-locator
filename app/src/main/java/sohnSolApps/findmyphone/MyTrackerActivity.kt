package sohnSolApps.findmyphone

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_my_tracker.*
import kotlinx.android.synthetic.main.contact_ticket.view.*

class MyTrackerActivity : AppCompatActivity() {
    var adapter: ContactAdapter? = null
    var listOfContact = ArrayList<UserContact>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_tracker)
        dummyData()
        adapter = ContactAdapter(this,listOfContact)
        lvContacts.adapter = adapter
    }

    //for first time debug
    fun dummyData(){
        listOfContact.add(UserContact("Hussein","6654565656"))
        listOfContact.add(UserContact("Chieftain","6654565655"))
        listOfContact.add(UserContact("Young Tycoon","6654565654"))
        listOfContact.add(UserContact("Young Khan","6654565653"))
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.tracker_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){

            R.id.finishActivity -> {
                finish()
            }

            R.id.addContact -> {
                //TODO: add new contact
            }

            else -> {
                return super.onOptionsItemSelected(item)
            }
        }

        return true
    }

    class ContactAdapter: BaseAdapter{
        var listOfContact = ArrayList<UserContact>()
        var context: Context? = null

        constructor(context: Context, listOfContact: ArrayList<UserContact>){
            this.context = context
            this.listOfContact = listOfContact
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val userContact = listOfContact[position]
            val inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val contactTicketView = inflater.inflate(R.layout.contact_ticket, null)
            contactTicketView.tvName.text = userContact.name
            contactTicketView.tvPhoneNumber.text = userContact.phoneNumber
            return contactTicketView
        }

        override fun getItem(position: Int): Any {
            return listOfContact[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return listOfContact.size
        }
    }
}
