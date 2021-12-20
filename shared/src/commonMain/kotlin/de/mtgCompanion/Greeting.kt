package de.mtgCompanion

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}