package com.example.uppercutu.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.uppercutu.AuthActivity
import com.example.uppercutu.R
import com.google.firebase.auth.FirebaseAuth

class PerfilFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_user, container, false)
    }
    /**
     * Configura las vistas tras haberse creado la vista del fragmento
     *
     * @param view La vista creada
     * @param savedInstanceState Informacion del estado previamente guardado
     *
     **/

    /**
     * Configura la vista una vez que se ha creado
     * **/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup
        val prefs = requireActivity().getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        val email = prefs.getString("email", null)
        val provider = prefs.getString("provider", null)
        val username = prefs.getString("username",null)
        setup(view, email ?: "", provider ?: "",username?:"")
    }

    private fun setup(view: View, email: String, provider: String,username : String) {
        val fullNameTextView: TextView = view.findViewById(R.id.user_userFullName)
        val emailTextView: TextView = view.findViewById(R.id.emailTextView)
        val userTextView : TextView = view.findViewById(R.id.userTextView)
        val logOutButton: Button = view.findViewById(R.id.logOutButton)
        val changePassButton : Button = view.findViewById(R.id.changePassButton)
        val resetPassButon : Button = view.findViewById(R.id.resetPassButton)
        val editPic : ImageView = view.findViewById(R.id.fragmentUser_editImage)


        val prefs = activity?.getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        val fullname: String? = prefs?.getString("fullname", "")

        fullNameTextView.text = fullname
        emailTextView.text = email
        userTextView.text = username

        logOutButton.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Cerrar sesión")
                .setMessage("¿Estás seguro de que deseas cerrar sesión?")
                .setPositiveButton("Sí") { _, _ ->
                    // Borrado de datos
                    val prefs =
                        requireActivity().getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
                            .edit()
                    prefs.clear()
                    prefs.apply()

                    FirebaseAuth.getInstance().signOut()

                    showAuthActivity()
                }
                .setNegativeButton("Cancelar") { dialog, _ ->
                    dialog.dismiss() // Cierra el diálogo sin hacer nada
                }
                .show()
        }


//        changePassButton.setOnClickListener {
//            val intent = Intent(requireContext(), ChangePasswordActivity::class.java)
//            startActivity(intent)
//        }

//        resetPassButon.setOnClickListener {
//            val tiempoActual = System.currentTimeMillis()
//
//            if (tiempoActual - ultimoTiempoRequest >= 60000) { // 60000 milisegundos = 1 minuto
//                val user = FirebaseInitializer.authInstance.currentUser
//
//                val email = user?.email
//                var confirmedEmail : String = ""
//                if (email != null) {
//                    if (email.isNotEmpty()) {
//                        confirmedEmail = email.toString()
//                    }
//                }
//
//                FirebaseInitializer.authInstance.sendPasswordResetEmail(confirmedEmail).addOnCompleteListener{
//                        task ->
//                    if (task.isSuccessful) {
//                        val builder = AlertDialog.Builder(requireContext())
//
//                        builder.setTitle("Reestablecimiento de constraseña")
//                        builder.setMessage("Revisa tu correo electrónico: $email")
//
//                        builder.setPositiveButton("Aceptar") { dialog, _  ->
//                            dialog.dismiss()
//                        }
//
//                        val alertDialog: AlertDialog = builder.create()
//                        alertDialog.show()
//                    }else {
//                        Toast.makeText(requireContext(),"Error ${task.exception?.message}",
//                            Toast.LENGTH_LONG).show()
//                    }
//                }
//
//                ultimoTiempoRequest = tiempoActual
//            } else {
//                Toast.makeText(requireContext(),"Espera 1 minuto para volver a reenviar el correo de restablecimiento de contraseña.",
//                    Toast.LENGTH_LONG).show()
//            }
//        }
//

    }
    private fun showAuthActivity() {
        val authIntent = Intent(activity, AuthActivity::class.java)

        startActivity(authIntent)
    }

}