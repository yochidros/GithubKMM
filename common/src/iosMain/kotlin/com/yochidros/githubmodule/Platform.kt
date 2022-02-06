package com.yochidros.githubmodule

import platform.UIKit.UIDevice

actual class Platform actual constructor() {
    actual val platform: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual class Hello actual constructor() {
    actual val name: String = "iOS Hello"
}