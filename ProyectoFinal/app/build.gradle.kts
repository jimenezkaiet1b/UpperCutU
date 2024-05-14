import io.grpc.internal.SharedResourceHolder.release

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.uppercutu"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.uppercutu"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

}

dependencies {
    implementation(platform("com.google.firebase:firebase-bom:32.8.1"))
    implementation("com.google.firebase:firebase-analytics")

    implementation("androidx.core:core-ktx:1.13.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.firebase:firebase-auth-ktx:22.3.1")
    implementation("com.google.firebase:firebase-firestore-ktx:24.11.1")
    implementation("com.google.firebase:firebase-storage-ktx:20.3.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("com.google.android.material:material:1.11.0")
    // CircleImage
    //https://github.com/hdodenhof/CircleImageView
    implementation("de.hdodenhof:circleimageview:3.1.0")

    implementation ("com.google.android.material:material:1.11.0")
    // librerias de Basefuego
    // https://firebase.google.com/docs/android/setup#available-libraries

    // Firebase BoM
    implementation(platform("com.google.firebase:firebase-bom:32.7.1"))

    // Firebase analytics
    implementation("com.google.firebase:firebase-analytics")

    // Firebase Authentication
    implementation("com.google.firebase:firebase-auth")

    // Firebase Authentication with Play Services
    implementation("com.google.android.gms:play-services-auth:21.0.0")

    // Firebase Firestore
    implementation("com.google.firebase:firebase-firestore-ktx")

    //Firebase Storage
    implementation("com.google.firebase:firebase-storage-ktx:20.3.0")

    //okhttp
    implementation("com.squareup.okhttp3:okhttp:4.9.2")

    // Gson (Retrofit version)
    // https://github.com/google/gson
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

//    implementation ("com.google.android.material:material:1.4.0")

    implementation ("com.airbnb.android:lottie:4.0.0")

    implementation ("com.flaviofaria:kenburnsview:1.0.7")

    implementation ("com.squareup.picasso:picasso:2.71828")

    //news APIs
    //implementation("com.github.KwabenBerko:News-API-Java:1.0.2")

    // Retrofit
    // https://square.github.io/retrofit/
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    // Gson (Retrofit version)
    // https://github.com/google/gson
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    // CarouselRecyclerView
    // https://github.com/sparrow007/CarouselRecyclerview
    implementation ("com.github.sparrow007:carouselrecyclerview:1.2.6")

    implementation ("com.squareup.okhttp3:logging-interceptor:4.5.0")
    implementation("com.github.bumptech.glide:glide:4.12.0")
    implementation("com.github.kittinunf.fuel:fuel:3.0.0-alpha1")

    implementation ("com.google.android.material:material:1.5.0")
    implementation ("com.wdullaer:materialdatetimepicker:4.2.3")

    implementation ("androidx.recyclerview:recyclerview:1.2.1")
    implementation ("androidx.cardview:cardview:1.0.0")

    implementation ("androidx.fragment:fragment-ktx:1.4.0")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0")

    implementation ("org.json:json:20210307")
}






