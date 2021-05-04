@file:UseSerializers(BigIntegerSerializer::class)

package me.bristermitten.devdenbot.stats

import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import me.bristermitten.devdenbot.data.AtomicBigInteger
import me.bristermitten.devdenbot.serialization.BigIntegerSerializer

@Serializable
object GlobalStats {
    var totalMessagesSent: AtomicBigInteger = AtomicBigInteger.ZERO

    var xpGiven: AtomicBigInteger = AtomicBigInteger.ZERO

    var levelUps: AtomicBigInteger = AtomicBigInteger.ZERO

    fun saveToString(): String {
        return Json.encodeToString(serializer(), this)
    }

    fun loadFrom(json: String) {
        val loaded = Json.decodeFromString<GlobalStats>(json)
        this.totalMessagesSent = loaded.totalMessagesSent
        this.xpGiven = loaded.xpGiven
        this.levelUps = loaded.levelUps
    }
}
