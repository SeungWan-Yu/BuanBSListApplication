package com.buan.buanbslistapplication

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.Button
import android.widget.ImageView
import com.bumptech.glide.Glide

@SuppressLint("MissingInflatedId")
class PhotoDialog
constructor(context: Context, bitmap: Bitmap) : Dialog(context){

    init {
        setCanceledOnTouchOutside(false)
        setCancelable(false)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        setContentView(R.layout.dialog_photo)

        val imageView2 = findViewById<ImageView>(R.id.img)
        val finish = findViewById<Button>(R.id.bt_finish)
        finish.setOnClickListener {
            dismiss()
        }



        Glide.with(context)
            .load(bitmap)
            .into(imageView2)
    }
}