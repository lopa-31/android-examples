package com.example.roomdatabasedemo.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabasedemo.R
import com.example.roomdatabasedemo.adapter.ListAdapter
import com.example.roomdatabasedemo.data.UserViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        //Add Menu
        setHasOptionsMenu(true)

        //RecyclerView
        val adapter = ListAdapter()
        val rv = view.findViewById<RecyclerView>(R.id.rv)
        rv.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        mUserViewModel.users.observe(viewLifecycleOwner, {
            adapter.setData(it)
        })

        val fab = view.findViewById<FloatingActionButton>(R.id.floatingActionButton)
        fab.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_item_deleteAll){
            deleteAllUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(
                "Delete everything?"
        )
        builder.setMessage(
                "Are you sure you want to delete everything?"
        )
        builder.setPositiveButton("Yes"){
            _,_ ->
            mUserViewModel.deleteAllUser()
            Toast.makeText(requireContext(),
                    "Successfully Removed everything",
                    Toast.LENGTH_SHORT).show()

        }
        builder.setNegativeButton("No"){
            _,_ ->

        }
        builder.setCancelable(false)
        builder.create().show()
    }
}