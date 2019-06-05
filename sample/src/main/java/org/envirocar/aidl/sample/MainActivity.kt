package org.envirocar.aidl.sample

import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.envirocar.aidl.IECRecordingService


class MainActivity : AppCompatActivity() {

    private var ecService: IECRecordingService? = null


    private val serviceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            ecService = null
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            ecService = IECRecordingService.Stub.asInterface(service)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val obdListView = findViewById<ListView>(R.id.listview)

        val obdValues = ArrayList<Pair<String, String>>()
        val obdValuesAdapter = OBDArrayAdapter(this, obdValues)
        obdListView.adapter = obdValuesAdapter


        val handler = Handler()
        val r = object : Runnable {
            override fun run() {
                handler.postDelayed(this, 5000)
                if (ecService != null) {
                    ecService?.latestMeasurement.let {
                        if (it == null)
                            return
                        obdValues.clear()

                        it.properties.let {
                            for (e in it.entries) {
                                obdValues.add(Pair(e.key, e.value))
                            }
                        }
                    }
                    obdValuesAdapter.notifyDataSetChanged()
                    obdListView.invalidate()
                }
            }
        }
        handler.postDelayed(r, 0)
    }

    override fun onResume() {
        super.onResume()
        if (ecService == null) {
            initConnection()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(serviceConnection)
    }

    private fun initConnection() {
        if (ecService == null) {
            val intent = Intent()
                .setComponent(ComponentName("org.envirocar.app", "org.envirocar.app.aidl.EnviroCarDataService"))
            bindService(intent, serviceConnection, Service.BIND_AUTO_CREATE)
        }
    }


    private class OBDArrayAdapter(context: Context, objects: MutableList<Pair<String, String>>) :
        ArrayAdapter<Pair<String, String>>(context, R.layout.main_listview_item, objects) {

        private class OBDValueViewHolder(
            internal var phenomenonView: TextView? = null,
            internal var valueView: TextView? = null
        )

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var view = convertView

            val viewHolder: OBDValueViewHolder

            if (view == null) {
                val inflater = LayoutInflater.from(context)
                view = inflater.inflate(R.layout.main_listview_item, parent, false)

                viewHolder = OBDValueViewHolder()
                viewHolder.phenomenonView = view!!.findViewById(R.id.obd_parameter) as TextView
                viewHolder.valueView = view!!.findViewById(R.id.obd_value) as TextView

            } else {
                viewHolder = view.tag as OBDValueViewHolder
            }

            val value = getItem(position)
            viewHolder.phenomenonView!!.text = value.first
            viewHolder.valueView!!.text = value.second

            view.tag = viewHolder
            return view
        }
    }
}
