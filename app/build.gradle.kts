plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.devjucelio.allmovies"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.devjucelio.allmovies"
        minSdk = 24
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

    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.firebase.messaging.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)



        implementation("androidx.navigation:navigation-fragment-ktx:")
        implementation("androidx.navigation:navigation-ui-ktx:")
        implementation("com.google.dagger:dagger:2.52")
        implementation("com.google.code.gson:gson:2.8.5")
        implementation("androidx.room:room-runtime:2.5.0")

        implementation("androidx.databinding:databinding-runtime:8.0.0")
        implementation ("com.google.android.gms:play-services-ads:22.0.0")
        implementation("com.squareup.retrofit2:retrofit:2.11.0")
        implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    //Mockito
    testImplementation ("org.mockito:mockito-core:3.3.3")

    //Mockito-Kotlin
    testImplementation ("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")

    //Junit4
    testImplementation ("junit:junit:4.12")

    //Test Rule Architecture Components
    testImplementation ("androidx.arch.core:core-testing:$rootProject.archTestVersion")

    //Test Coroutines
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.3.8")

    implementation ("androidx.navigation:navigation-fragment-ktx:2.7.3")
    implementation ("androidx.navigation:navigation-ui-ktx:2.7.3")

         implementation ("androidx.room:room-compiler:2.5.2")
            implementation ("androidx.room:room-compiler:2.5.2")

         //Retrofit
         implementation ("com.squareup.retrofit2:retrofit:2.9.0")

        //Gson
        implementation  ("com.squareup.retrofit2:converter-gson:2.9.0")
         implementation ("com.squareup.retrofit2:converter-moshi:2.9.0")
        //Okhttp3
        implementation ("com.squareup.okhttp3:okhttp:5.0.0")
        implementation ("com.squareup.okhttp3:logging-interceptor:5.0.0")


       //picaso
        implementation ("com.squareup.picasso:picasso:2.71828")

        //RecyclerView
        implementation ("com.google.android.material:material:1.0.0")
        implementation ("androidx.recyclerview:recyclerview:1.1.0-beta04'//1.0.0")
        implementation ("androidx.constraintlayout:constraintlayout:1.1.3")

        //Admob
        implementation ("com.google.android.gms:play-services-ads:18.3.0")

        // Read More TextView
        implementation ("com.borjabravo:readmoretextview:2.1.0")

        //Shimmer layout
        implementation ("com.facebook.shimmer:shimmer:0.5.0@aar")

        implementation ("androidx.multidex:multidex:2.0.0")
        implementation ("com.squareup.okio:okio:3.3.0")
        implementation ("androidx.navigation:navigation-fragment-ktx:2.7.1")
        implementation ("androidx.navigation:navigation-ui-ktx:2.7.1")
}
