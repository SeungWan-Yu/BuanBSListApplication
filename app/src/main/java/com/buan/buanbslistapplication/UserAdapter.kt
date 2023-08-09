package com.buan.buanbslistapplication

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class UserAdapter(
    val beeList: ArrayList<RoomDB.User>,
    val appcontext: Activity,
    private val clickListener: (RoomDB.User) -> Unit
) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflate = LayoutInflater.from(parent.context)
        val view = inflate.inflate(R.layout.custom_listview, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(beeList[position], clickListener,appcontext)

    }

    override fun getItemCount(): Int {
        return beeList.size
        Log.e("e",beeList.size.toString())
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        val imageView = itemView?.findViewById<ImageView>(R.id.imageView)
        val name = itemView?.findViewById<TextView>(R.id.user_name)
        val phone = itemView?.findViewById<TextView>(R.id.user_phone)
        val addr = itemView?.findViewById<TextView>(R.id.user_addr)

        @SuppressLint("UseSwitchCompatOrMaterialCode", "SetTextI18n")

        fun bind(user: RoomDB.User?, clickListener: (RoomDB.User) -> Unit,context:Activity) {

            // 바이트 배열로부터 Bitmap 객체를 만듭니다
            if (user?.photourl == null){
                Glide.with(context)
                    .load(R.drawable.ic_launcher_foreground)
                    .into(imageView!!)
            }else{
                val bitmap = BitmapFactory.decodeByteArray(user?.photourl, 0,
                user?.photourl?.size!!)
                Glide.with(context)
                    .load(bitmap)
                    .into(imageView!!)

//            Glide.with(context)
//                .load(user?.photo)
//                .placeholder(android.R.drawable.ic_popup_sync)
//                .error(android.R.drawable.btn_dialog)
//                .into(imageView!!)
//            Log.d("asdasdds", "Glide: user?.photo")
            }
            name?.text = user?.name
            phone?.text = user?.phone
            addr?.text = user?.address
           itemView.setOnClickListener { clickListener(user!!) }
        }

    }
}

//fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
//    itemView.setOnClickListener {
//        event.invoke(adapterPosition, itemViewType)
//    }
//    return this
//}