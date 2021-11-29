package com.example.roomdatabasedemo.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomdatabasedemo.R
import com.example.roomdatabasedemo.data.UserViewModel
import com.example.roomdatabasedemo.data.model.User


class AddFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    private lateinit var addBtn: Button
    private lateinit var addFnEt: EditText
    private lateinit var addLnEt: EditText
    private lateinit var addAgeEt: EditText


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        view.apply {
            addBtn = findViewById(R.id.addBtn)
            addFnEt = findViewById(R.id.update_fn_et)
            addLnEt = findViewById(R.id.update_ln_et)
            addAgeEt = findViewById(R.id.update_age_et)
        }

        addBtn.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val fn = addFnEt.text.toString().trim()
        val ln = addLnEt.text.toString().trim()
        val age = addAgeEt.text.toString()

        if (inputCheck(fn, ln, age)) {
            mUserViewModel.users.observe(viewLifecycleOwner, {
                if (it.isEmpty()) {
                    val list = listOf(
                            User(0, "Rahul", "Chahar", 18),
                            User(0, "Rohit", "Sharma", 19)
                    )
                    mUserViewModel.addUsers(list)
                } else {
                    //Creating Obj of User Class
                    val user = User(0, fn, ln, age.toInt())
                    //Adding Data To Database
                    mUserViewModel.addUser(user)
                }

            })


            //Navigate Back
            Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_SHORT).show()
            findNavController().navigateUp().also {
                view?.let {
                    hideKeyboardFrom(requireContext(), it)
                }
            }

        } else {
            Toast.makeText(requireContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(fn: String, ln: String, age: String): Boolean {
        return !(TextUtils.isEmpty(fn) || TextUtils.isEmpty((ln)) || TextUtils.isEmpty(age))
    }

    private fun hideKeyboardFrom(context: Context, view: View) {
        val imm: InputMethodManager = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}