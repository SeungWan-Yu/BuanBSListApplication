package com.example.buanbslistapplication

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.example.buanbslistapplication.databinding.FragmentFirstBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.bt1.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        binding.bt2.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_thirdFragment)
        }
        binding.btSearch.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_searchFragment)
        }
        binding.textView2.setOnClickListener {
            checkNetwork()
        }
    }

    fun checkNetwork() {

        if (NetworkManager.checkNetworkState(requireContext()) == 0){ //인터넷 연결 없음
            Toast.makeText(requireActivity(), "인터넷 연결이 없습니다", Toast.LENGTH_SHORT).show()
        }else if(NetworkManager.checkNetworkState(requireContext()) == 1){ //lte
            val builder = AlertDialog.Builder(requireActivity())
                .setTitle("데이터 다운로드")
                .setMessage("현재 3G/LTE 상태입니다\n요금제 환경에 따라 요금이 부과될 수 있습니다\n\n성도 목록을 업데이트 하시겠습니까?")
                .setPositiveButton("확인"
                ) { dialog, which ->
                    getbslist()
                }
                .setNegativeButton("취소"
                ) { dialog, which ->
                    Toast.makeText(requireContext(), "취소", Toast.LENGTH_SHORT).show()
                }
            builder.show()
        }else if(NetworkManager.checkNetworkState(requireContext()) == 2){ // wifi
            getbslist()
        }


//                userDao.insertAll(RoomDB.User("2","유승완","010-7496-6630","부안읍 교동1길29 모던아이빌103동 301호","21구역","청년회"))
//                userDao.insertAllAll()
//                Log.d("DB","insertAllAll")
    }

    private fun getbslist() {
        val userDao: RoomDB.UserDao = RoomDB.AppDatabase.getInstance(requireActivity())!!.userDao()
        userDao.deleteAll()
        Log.d("DB","DB delete all")
        CoroutineScope(Dispatchers.Main).launch {
            val dialog = LoadingDialog(requireActivity())
            dialog.show()
            CoroutineScope(Dispatchers.IO).async {
                val response : List<Users> = UserRepo.fetchUsers()
                Log.d("TAG", "response: $response")
                for(element in response){
                    userDao.insertAll(RoomDB.User(
                        element.id.toString(),
                        element.name,
                        element.phone,
                        element.address,
                        element.area.toString(),
                        element.belong,
                        element.photo,
                        element.etc,
                        null
                    ))
                }
                val response2 : List<Users> = UserRepo.fetchUsers2()
                Log.d("TAG", "response: $response")
                for(element in response2){
                    userDao.insertAll(RoomDB.User(
                        element.id.toString(),
                        element.name,
                        element.phone,
                        element.address,
                        element.area.toString(),
                        element.belong,
                        element.photo,
                        element.etc,
                        null
                    ))
                }
                val response3 : List<Users> = UserRepo.fetchUsers3()
                Log.d("TAG", "response: $response")
                for(element in response3){
                    userDao.insertAll(RoomDB.User(
                        element.id.toString(),
                        element.name,
                        element.phone,
                        element.address,
                        element.area.toString(),
                        element.belong,
                        element.photo,
                        element.etc,
                        null
                    ))
                }
            }.await()
            dialog.dismiss()
            val dialog2 = LoadingDialog2(requireActivity())
            dialog2.show()
            CoroutineScope(Dispatchers.IO).async {
                val userData = userDao.getAll()
                for (i in userData.indices) {
                    Log.d("asdasd", "userData[$i].photo: ${userData[i].photo}")
                    if (userData[i].photo!!.isEmpty()) {
//                    userDao.imageDao(null)
                    } else {
                        Log.d("asdasd", "check")
                        Glide.with(requireActivity())
                            .asBitmap()
                            .load("http://drive.google.com/uc?export=view&id=${userData[i].photo}")
                            .into(object : CustomTarget<Bitmap>() {
                                override fun onResourceReady(
                                    resource: Bitmap,
                                    transition: Transition<in Bitmap>?
                                ) {
                                    val byteArrayOutputStream = ByteArrayOutputStream()
                                    val success = resource.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
                                    if (!success) {
                                        Log.e("asdasd", "Failed to compress bitmap")
                                        return
                                    }
                                    val byteArray = byteArrayOutputStream.toByteArray()

                                    // 바이트 배열을 Room 데이터베이스에 삽입합니다
                                    // Image는 이미지를 저장할 테이블을 나타내는 Room Entity 클래스입니다.
                                    // 해당 클래스는 @Entity 어노테이션을 사용하여 정의되어야 합니다.

                                    // Room DAO를 사용하여 데이터베이스에 이미지를 삽입합니다
                                    Log.d("asdasd", "imageDao ${i+1},$byteArray")
                                    userDao.imageDao(i+1, byteArray)
                                }

                                override fun onLoadCleared(placeholder: Drawable?) {
                                    Log.d("asdasd", "onLoadCleared: fail")
                                }
                            })
                    }
                }
            }.await()
            dialog2.dismiss()
            Toast.makeText(requireActivity(), "성도 목록이 업데이트 되었습니다.", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}