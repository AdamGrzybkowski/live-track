object Libs {
    private const val kotlinVersion = "1.3.50"
    private const val navigationVersion = "2.1.0-rc01"
    private const val threetenbpVersion = "1.4.1"
    private const val rxJavaVersion = "2.2.12"
    private const val daggerVersion = "2.24"
    private const val lifecycleVersion = "2.2.0-alpha05"

    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"

    const val appCompat = "androidx.appcompat:appcompat:1.0.2"

    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:$navigationVersion"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:$navigationVersion"

    const val lifecycle = "androidx.lifecycle:lifecycle-extensions:$lifecycleVersion"

    const val material = "com.google.android.material:material:1.1.0-alpha09"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.0-beta2"

    const val dagger = "com.google.dagger:dagger:$daggerVersion"
    const val daggerProccessor = "com.google.dagger:dagger-compiler:$daggerVersion"
    const val daggerAndroid = "com.google.dagger:dagger-android-support:$daggerVersion"
    const val daggerAndroidProccessor = "com.google.dagger:dagger-android-processor:$daggerVersion"

    const val rxJava = "io.reactivex.rxjava2:rxjava:$rxJavaVersion"
    const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:2.4.0"
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:2.1.1"

    const val maps = "com.google.android.gms:play-services-maps:17.0.0"

    const val easyPermissions = "pub.devrel:easypermissions:3.0.0"

    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:2.0-beta-3"

    const val threeTenBpNoTzDb = "org.threeten:threetenbp:$threetenbpVersion:no-tzdb"
    const val threeTenABP = "com.jakewharton.threetenabp:threetenabp:1.2.2"

    const val javaxInject = "javax.inject:javax.inject:1"
}
