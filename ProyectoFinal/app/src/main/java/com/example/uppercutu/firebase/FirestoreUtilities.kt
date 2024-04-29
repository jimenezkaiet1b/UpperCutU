package com.example.uppercutu.firebase

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

object FirestoreUtilities {
    /**
     * Guarda la información del usuario en Firestore.
     *
     * @param firestore Instancia de [FirebaseFirestore].
     * @param auth Instancia de [FirebaseAuth].
     * @param username Nombre de usuario.
     * @param email Correo electrónico del usuario.
     * @param fullName Nombre completo del usuario.
     * @param birthDate Fecha de nacimiento del usuario en formato de cadena.
     * @param callback Función de retorno de llamada que indica si la operación fue exitosa o no.
     */
    fun saveUserInFirestore(
        firestore: FirebaseFirestore,
        auth: FirebaseAuth,
        username: String,
        email: String,
        fullName: String,
        birthDate: String,
        callback: (Boolean) -> Unit
    ) {
        val currentUser = auth.currentUser
        currentUser?.let { user ->
            val userData = hashMapOf(
                "birthDate" to birthDate,
                "fullName" to fullName,
                "username" to username,
                "email" to email
            )

            firestore.collection("users").document(user.uid)
                .set(userData)
                .addOnSuccessListener {
                    callback(true)
                }
                .addOnFailureListener { e ->
                    // Captura cualquier error que ocurra al intentar guardar los datos en Firestore
                    Log.e("Firestore", "Error al guardar el usuario en Firestore", e)
                    callback(false)
                }
        }
    }


    /**
     * Crea una entrada de lista de usuario en Firestore.
     *
     * @param firestore Instancia de [FirebaseFirestore].
     * @param email Correo electrónico del usuario.
     */
    fun createUserListEntryInFirestore(firestore: FirebaseFirestore, email: String) {
        firestore.collection("lists").document(email)
            .set(hashMapOf<String, Any>())
    }
}