package com.example.buanbslistapplication

import android.content.Context
import android.content.Intent
import android.icu.lang.UCharacter.VerticalOrientation
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.test.core.app.ApplicationProvider
import com.example.buanbslistapplication.databinding.FragmentListBinding
import com.google.android.material.divider.MaterialDividerItemDecoration.VERTICAL


class ListFragment : Fragment() {

    private var _binding : FragmentListBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: ListFragmentArgs by navArgs() //Args 만든 후
        var tMsg = args.belong // 아까 만든 msg 를 tMsg에 대입
        val userDao: RoomDB.UserDao = RoomDB.AppDatabase.getInstance(requireContext())!!.userDao()
        val userData = userDao.findByBelong(tMsg)
        Log.e("userDao",userData.toString())
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
//        Toast.makeText(requireContext(), "name = ${user?.name}", Toast.LENGTH_SHORT).show(
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:${user?.phone}")
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}