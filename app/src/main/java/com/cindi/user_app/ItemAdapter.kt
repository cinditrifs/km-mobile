package com.cindi.user_app

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.cindi.user_app.data.Data
import com.squareup.picasso.Picasso

class ItemAdapter( var nameList: List<Data>, var context: Activity)
    : ArrayAdapter<Data>(context, R.layout.item_list, nameList)

{   override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return nameList.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflayer : LayoutInflater = LayoutInflater.from(context)
        val view : View = layoutInflayer.inflate(R.layout.item_list, null)
        val first_name: TextView = view.findViewById(R.id.firstName)
        val last_name: TextView = view.findViewById(R.id.lastName)
        val email: TextView = view.findViewById(R.id.email)
        val avatar: ImageView = view.findViewById(R.id.avatar)

        val konten = nameList.get(position)
        first_name.text = konten.firstName
        last_name.text = konten.lastName
        email.text = konten.email
        Picasso.get().load(konten.avatar).into(avatar)

        println(konten.email)

        return view
    }
}