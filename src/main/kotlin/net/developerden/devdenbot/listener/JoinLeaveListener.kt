package net.developerden.devdenbot.listener

import net.developerden.devdenbot.extensions.await
import net.developerden.devdenbot.inject.Used
import net.developerden.devdenbot.util.handleEachIn
import net.developerden.devdenbot.util.listenFlow
import net.developerden.devdenbot.util.scope
import net.developerden.devdenbot.discord.WELCOME_CHANNEL_ID
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent


@Used
class JoinLeaveListener : EventListener {
    private suspend fun onGuildMemberJoin(event: GuildMemberJoinEvent) {
        val channel = event.jda.getTextChannelById(WELCOME_CHANNEL_ID)
        channel?.sendMessage("Welcome ${event.user.asMention} to the Developer's Den! There are now ${event.guild.memberCount} users.")
            ?.await()
    }

    private suspend fun onGuildMemberLeave(event: GuildMemberRemoveEvent) {
        val channel = event.jda.getTextChannelById(WELCOME_CHANNEL_ID)
        channel?.sendMessage("${event.user.asMention} has left \\:(. There are now ${event.guild.memberCount} users.")
            ?.await()
    }

    override fun register(jda: JDA) {
        jda.listenFlow<GuildMemberJoinEvent>().handleEachIn(scope, this::onGuildMemberJoin)
        jda.listenFlow<GuildMemberRemoveEvent>().handleEachIn(scope, this::onGuildMemberLeave)
    }
}
