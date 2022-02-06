package com.yochidros.githubmodule

expect class Platform() {
    val platform: String
}

expect class Hello() {
    val name: String
}
