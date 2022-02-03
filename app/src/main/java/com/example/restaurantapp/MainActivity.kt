package com.example.restaurantapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.example.restaurantapp.databinding.ActivityMainBinding
import com.example.restaurantapp.db.RestDB
import com.example.restaurantapp.db.entity.RestEntity

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    lateinit var db : RestDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = getDb(this)

        //val restaurant = RestEntity(1, "Goiko Grill", "American")
        //db.restDao().addRest(restaurant)
        val restaurants = db.restDao().findAll()
        Log.d("DATABASE", "restaurants: $restaurants")

        var builder = NotificationCompat.Builder(this, "1")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Noti")
            .setContentText("Hola")
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        val not = builder.build()

        val notManager = NotificationManagerCompat.from(this)
        notManager.notify(1, not)

    }

    

    private fun getDb(context: Context): RestDB = Room.databaseBuilder(
        context.applicationContext,
        RestDB::class.java,
        "restaurant-app.db"
    )
        .allowMainThreadQueries()
        .build()
}

val Fragment.db: RestDB get() = (activity as MainActivity).db
