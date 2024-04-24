package com.example.uppercutu.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
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
import com.example.uppercutu.firebase.FirebaseInitializer
import com.example.uppercutu.firebase.FirestoreImageManager
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth


/**
 * Enumeracion para los diferentes tipos de proveedores de autenticacion
 **/
enum class ProviderType {
    BASIC,
    GOOGLE
}





