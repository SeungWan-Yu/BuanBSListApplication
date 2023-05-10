package com.example.buanbslistapplication

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.buanbslistapplication.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listView: ListView = view.findViewById(R.id.lv)
        listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val item = parent?.getItemAtPosition(position) as String
                val action = SecondFragmentDirections.actionSecondFragmentToListFragment(item)
                findNavController().navigate(action)
//                Toast.makeText(requireContext(), "Clicked item: $item", Toast.LENGTH_SHORT).show()
            }

        val items = listOf("은장회", "봉사회", "어머니회", "청년회","교회학교")
        val adapter = StringListAdapter(requireContext(), items)
        listView.adapter = adapter
    }

    class StringListAdapter(context: Context, items: List<String>) :
        ArrayAdapter<String>(context, android.R.layout.simple_expandable_list_item_1, items)


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}