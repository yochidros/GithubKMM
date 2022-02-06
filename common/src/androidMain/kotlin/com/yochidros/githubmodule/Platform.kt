package com.yochidros.githubmodule

actual class Platform actual constructor() {
    actual val platform: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}
actual class Hello actual constructor() {
    actual val name: String = "Android Hello"
}