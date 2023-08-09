package com.buan.buanbslistapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.buan.buanbslistapplication.databinding.FragmentAreaLIstBinding
import com.google.android.material.divider.MaterialDividerItemDecoration.VERTICAL


class AreaListFragment : Fragment() {

    private var _binding : FragmentAreaLIstBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAreaLIstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: AreaListFragmentArgs by navArgs() //Args 만든 후
        var data = ""
        var tMsg = args.area // 아까 만든 msg 를 tMsg에 대입
        when(tMsg){
            "11구역" -> {
                data = "11"
            }
            "12구역" -> {
                data = "12"
            }
            "13구역" -> {
                data = "13"
            }
            "14구역" -> {
                data = "14"
            }
            "21구역" -> {
                data = "21"
            }
            "22구역" -> {
                data = "22"
            }
            "23구역" -> {
                data = "23"
            }
            "24구역" -> {
                data = "24"
            }
        }
        val userDao: RoomDB.UserDao = RoomDB.AppDatabase.getInstance(requireContext())!!.userDao()
        val userData = userDao.findByArea(data)
        Log.e("userDao $tMsg",userData.toString())
        binding.textView.text = "총 ${userData.size}명"
        setAdapter(userData as ArrayList<RoomDB.User>)
    }

    private fun setAdapter(user:ArrayList<RoomDB.User>) {
        binding.belonglist.adapter = UserAdapter(user, requireActivity()) { user -> comItemClicked(user)}
        binding.belonglist.layoutManager = LinearLayoutManager(requireContext())
        binding.belonglist.setHasFixedSize(false)
//        binding.belonglist.scrollToPosition(sposition)
        val decoration = DividerItemDecoration(requireContext(),VERTICAL)
        binding.belonglist.addItemDecoration(decoration)
    }
    private fun comItemClicked(user: RoomDB.User?) {
//        Toast.makeText(requireContext(), "id = ${user?.id} name =  ${user?.name} phone =  ${user?.phone} address =  ${user?.address} area =  ${user?.area} belong =  ${user?.belong} photo =  ${user?.photo} etc =  ${user?.etc} photourl = ${user?.photourl}", Toast.LENGTH_SHORT).show()
//        Log.d("asdasd", "comItemClicked: id = ${user?.id} name =  ${user?.name} phone =  ${user?.phone} address =  ${user?.address} area =  ${user?.area} belong =  ${user?.belong} photo =  ${user?.photo} etc =  ${user?.etc} photourl = ${user?.photourl}")
      val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:${user?.phone}")
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}