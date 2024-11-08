package com.example.textingapp

import android.content.Context
import android.os.Message
import android.provider.Telephony.Mms.Sent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth

class MessageAdapter(val context:Context,val messageList:ArrayList<message>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val ITEM_RECEIVE=1
    val ITEM_SENT=2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if(viewType==1){
            val view:View= LayoutInflater.from(context).inflate(R.layout.receive,parent,false)
            return ReceiveViewHolder(view)
        }else{
            val view:View= LayoutInflater.from(context).inflate(R.layout.sent,parent,false)
            return sentViewHolder(view)
        }

    }



    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentmessage=messageList[position]
        if(holder.javaClass==sentViewHolder::class.java){

            val viewHolder=holder as sentViewHolder
            holder.sentMessage.text= currentmessage.message
            //do this stuff for sent view holder
        }else{

            //do this stuff for receive view holder
            val viewHolder=holder as ReceiveViewHolder
            holder.receiveMessage.text= currentmessage.message
        }

    }


    override fun getItemViewType(position: Int): Int {
        val currentMessage=messageList[position]
        if (FirebaseAuth.getInstance().currentUser?.uid.equals(currentMessage.senderId)){
            return ITEM_SENT
        }else{
            return ITEM_RECEIVE
        }
    }
    override fun getItemCount(): Int {

        return messageList.size
    }
    class sentViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){

        val sentMessage=itemView.findViewById<TextView>(R.id.txt_sent_message)
    }
    class ReceiveViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val receiveMessage=itemView.findViewById<TextView>(R.id.txt_recevie_message)
    }
}