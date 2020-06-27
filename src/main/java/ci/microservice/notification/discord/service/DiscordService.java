package ci.microservice.notification.discord.service;

public interface DiscordService {
    void discordConnexion();
    boolean subscribeRequest(String channelId, String subscriberMessage);
}