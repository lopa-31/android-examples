package com.example.googlesignin

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.material.button.MaterialButton


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private val RC_SIGN_IN = 1
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private val displayImage by lazy {
        findViewById<ImageView>(R.id.display_image)
    }
    private val displayName by lazy {
        findViewById<TextView>(R.id.display_name)
    }
    private val emailId by lazy {
        findViewById<TextView>(R.id.email_id)
    }
    private val signInButton by lazy {
        findViewById<SignInButton>(R.id.sign_in_button)
    }
    private val  signOutButton by lazy {
        findViewById<MaterialButton>(R.id.sign_out_button)
    }

    override fun onStart() {
        super.onStart()
        val account = GoogleSignIn.getLastSignedInAccount(this)
        updateUI(account)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gso = GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        signInButton.setOnClickListener {
            signIn()
        }

        signOutButton.setOnClickListener {
            signOut()
        }
    }
    private fun signIn() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this) {
                    updateUI(null)
                }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            // Signed in successfully, show authenticated UI.
            updateUI(account)
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.statusCode)
            updateUI(null)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateUI(account: GoogleSignInAccount?) {
        if (account != null) {
            bindImageFromUrl(displayImage, account.photoUrl.toString())
            displayName.text = "Hello ${account.displayName}"
            emailId.text = account.email
            signInButton.visibility = View.GONE
            signOutButton.visibility = View.VISIBLE
        } else {
            displayImage.setImageResource(R.drawable.ic_baseline_account_circle_24)
            displayName.text = "Please Sign In."
            emailId.text = ""
            signInButton.visibility = View.VISIBLE
            signOutButton.visibility = View.GONE
        }
    }
}