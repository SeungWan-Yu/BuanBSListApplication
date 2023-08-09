package com.buan.buanbslistapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.buan.buanbslistapplication.databinding.FragmentSearchBinding
import com.google.android.material.divider.MaterialDividerItemDecoration.VERTICAL


class SearchFragment : Fragment() {

    private var _binding : FragmentSearchBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etSearch.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
            override fun afterTextChanged(p0: Editable?) {
                val data = binding.etSearch.text.trim()
                Log.e("asd",binding.etSearch.text.trim().toString())
                val userDao: RoomDB.UserDao = RoomDB.AppDatabase.getInstance(requireContext())!!.userDao()
                if (data.toString() == ""){

                }else{
                    val userData = userDao.findByName(data.toString())
                    if (userData.isNotEmpty()) {
                        Log.e("asd",userData.toString())
                        binding.textView4.text = "총 ${userData.size}명"
                        setAdapter(userData as ArrayList<RoomDB.User>)
                    }
                }
            }
        })

    }

    private fun setAdapter(user:ArrayList<RoomDB.User>) {
        binding.list.adapter = UserAdapter(user, requireActivity()) { user -> comItemClicked(user)}
        binding.list.layoutManager = LinearLayoutManager(requireContext())
        binding.list.setHasFixedSize(false)
//        binding.belonglist.scrollToPosition(sposition)
        val decoration = DividerItemDecoration(requireContext(),VERTICAL)
        binding.list.addItemDecoration(decoration)
    }
    private fun comItemClicked(user: RoomDB.User?) {
//        Toast.makeText(requireContext(), "name = ${user?.name}", Toast.LENGTH_SHORT).show()
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:${user?.phone}")
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}