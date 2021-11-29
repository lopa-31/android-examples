package com.example.roomdatabasedemo.fragment

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.*
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

class UpdateFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel
    private lateinit var currentUser: User

    private lateinit var updateBtn : Button
    private lateinit var updateFnEt: EditText
    private lateinit var updateLnEt: EditText
    private lateinit var updateAgeEt: EditText


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_update, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.apply {
            updateFnEt = findViewById(R.id.update_fn_et)
            updateLnEt = findViewById(R.id.update_ln_et)
            updateAgeEt = findViewById(R.id.update_age_et)
            updateBtn = findViewById(R.id.addBtn)
        }

        //Add Menu
        setHasOptionsMenu(true)

        currentUser = arguments?.getParcelable<User>("currentUser") ?:
                User(0,"","",0)

        updateFnEt.setText(currentUser.firstName)
        updateLnEt.setText( currentUser.lastName)
        updateAgeEt.setText(currentUser.age.toString())

        updateBtn.setOnClickListener {
            updateItem()
        }

        return view
    }

    private fun updateItem(){
        val fn = updateFnEt.text.toString().trim()
        val ln = updateLnEt.text.toString().trim()
        val age = updateAgeEt.text.toString()

        if(inputCheck(fn, ln, age)) {
            val updatedUser = User(currentUser.id, fn , ln , age.toInt())

            mUserViewModel.updateUser(updatedUser)
            //Navigate Back

            Toast.makeText(requireContext(), "Successfully updated", Toast.LENGTH_SHORT).show()
            findNavController().navigateUp().also {
                view?.let { hideKeyboardFrom(requireContext(),it) }
            }

        }
        else{
            Toast.makeText(requireContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(fn: String, ln: String, age: String) : Boolean{
        return !(TextUtils.isEmpty(fn) || TextUtils.isEmpty((ln)) || TextUtils.isEmpty(age))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu , menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_item_delete){
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(
                "Delete ${currentUser.firstName}?"
        )
        builder.setMessage(
                "Are you sure you want to delete ${currentUser.firstName}?"
        )
        builder.setPositiveButton("Yes"){
            _,_ ->
            mUserViewModel.deleteUser(currentUser)
            Toast.makeText(requireContext(),
                    "Successfully Removed: ${currentUser.firstName}",
                    Toast.LENGTH_SHORT).show()

            findNavController().navigateUp()
        }
        builder.setNegativeButton("No"){
            _,_ ->

        }
        builder.create().show()
    }

    private fun hideKeyboardFrom(context: Context, view: View) {
        val imm: InputMethodManager = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}