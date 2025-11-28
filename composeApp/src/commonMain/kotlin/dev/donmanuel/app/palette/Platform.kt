package dev.donmanuel.app.palette

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform