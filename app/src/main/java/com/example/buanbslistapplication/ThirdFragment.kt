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
import com.example.buanbslistapplication.databinding.FragmentThirdBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ThirdFragment : Fragment() {

    private var _binding: FragmentThirdBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listView: ListView = view.findViewById(R.id.tlv)
        listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val item = parent?.getItemAtPosition(position) as String
                val action = ThirdFragmentDirections.actionThirdFragmentToAreaListFragment(item)
                findNavController().navigate(action)
//                Toast.makeText(requireContext(), "Clicked item: $item", Toast.LENGTH_SHORT).show()
            }

        val items = listOf("11구역", "12구역", "13구역", "14구역","21구역","22구역","23구역","24구역")
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