package com.khandelwal.distributors

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.HandlerCompat.postDelayed
import com.balysv.materialripple.MaterialRippleLayout
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider


class SplashActivity : AppCompatActivity() {

    private lateinit var gso:GoogleSignInOptions
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private val RC_SIGN_IN = 1
    private lateinit var mAuth: FirebaseAuth
    private var account: GoogleSignInAccount? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        mAuth = FirebaseAuth.getInstance()

        account = GoogleSignIn.getLastSignedInAccount(this)

        if (account==null && mAuth.currentUser==null)
            animateSplash()
        else{
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(baseContext, MainActivity::class.java))
            },2000)
        }

        MaterialRippleLayout.on(findViewById(R.id.sign_in_btn))
                .rippleColor(R.color.rippleColor)
                .create()
                .setOnClickListener { googleSignIn() }
    }

    private fun animateSplash() {
        Handler(Looper.getMainLooper()).postDelayed({

            findViewById<ImageButton>(R.id.sign_in_btn).visibility = View.VISIBLE
            YoYo.with(Techniques.BounceIn)
                    .duration(700)
                    .repeat(0)
                    .playOn(findViewById(R.id.sign_in_btn));

        }, 2000)
    }

    private fun googleSignIn() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data);
            val account: GoogleSignInAccount = task?.getResult(ApiException::class.java)!!
            firebaseAuthWithGoogle(account.idToken);
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String?) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = mAuth.currentUser
                        Snackbar.make(findViewById(R.id.splash_screen), "Authentication Success.",
                                Snackbar.LENGTH_SHORT).show()
                        startActivity(Intent(baseContext, MainActivity::class.java))
                    } else {
                        Snackbar.make(findViewById(R.id.splash_screen), "Authentication Failed.",
                                Snackbar.LENGTH_SHORT).show()
                    }
                }
    }

}