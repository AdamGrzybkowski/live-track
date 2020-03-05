object Libs {
    private const val kotlinVersion = "1.3.70"
    private const val navigationVersion = "2.3.0-alpha02"
    private const val threetenbpVersion = "1.4.1"
    private const val daggerVersion = "2.26"
    private const val lifecycleVersion = "2.2.0"

    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"
    const val kotlinCoroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.3"

    const val appCompat = "androidx.appcompat:appcompat:1.2.0-alpha02"

    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:$navigationVersion"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:$navigationVersion"

    const val lifecycle = "androidx.lifecycle:lifecycle-extensions:$lifecycleVersion"

    const val material = "com.google.android.material:material:1.2.0-alpha05"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.0-beta4"

    const val dagger = "com.google.dagger:dagger:$daggerVersion"
    const val daggerProccessor = "com.google.dagger:dagger-compiler:$daggerVersion"
    const val daggerAndroid = "com.google.dagger:dagger-android-support:$daggerVersion"
    const val daggerAndroidProccessor = "com.google.dagger:dagger-android-processor:$daggerVersion"

    const val maps = "com.google.android.gms:play-services-maps:17.0.0"

    const val easyPermissions = "pub.devrel:easypermissions:3.0.0"

    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:2.2"

    const val threeTenBpNoTzDb = "org.threeten:threetenbp:$threetenbpVersion:no-tzdb"
    const val threeTenABP = "com.jakewharton.threetenabp:threetenabp:1.2.2"

    const val javaxInject = "javax.inject:javax.inject:1"
}
