package com.buan.buanbslistapplication

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

class LoadingDialog2(context: Context, count:Int,private val lifecycleOwner: LifecycleOwner,private val viewModelStoreOwner: ViewModelStoreOwner) : Dialog(context){

    private lateinit var viewModel: MyViewModel
    var count = count

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setCanceledOnTouchOutside(false)
        setCancelable(false)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        setContentView(R.layout.dialog_loading2)

//        // LiveData 관찰
//        viewModel.data.observe(lifecycleOwner, Observer { newData ->
//            var percent = findViewById<TextView>(R.id.textView6)
//            percent.text = "$newData"
//        })


        viewModel = ViewModelProvider(viewModelStoreOwner)[MyViewModel::class.java]

        viewModel.data.observe(lifecycleOwner, Observer { newData ->
            // newData가 변경될 때마다 이 블록이 호출됩니다.
            var percent = findViewById<TextView>(R.id.textView6)
//            percent.text = "$newData"
//            percent.text = newData.toString()
        })


    }
}