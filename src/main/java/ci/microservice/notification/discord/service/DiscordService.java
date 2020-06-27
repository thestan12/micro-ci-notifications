package ci.microservice.notification.discord.service;


import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.channel.server.text.WebhooksUpdateEvent;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DiscordService  {

    private static String Token = "NzEwNzg1NjExMDk5MDEzMTMw.XvdRcQ.jlMxLNaQOMlNrBNkqcpI0Hc7xjs";

    public static Multimap<String, String> BuildCodeChannelMap = ArrayListMultimap.create();

    private static DiscordApi api = new DiscordApiBuilder().setToken(Token).login().join();

    public DiscordService(){

    }

    public void discordConnexion(){
        System.out.println(api.createBotInvite());

        api.addMessageCreateListener(event -> {

            String msg = event.getMessageContent();

            if (msg.equalsIgnoreCase("!help")) {
                sendMessageOnChannel(event.getChannel().getIdAsString(), "Entrez : \n - !sendMeMessage pour ouvrir un cannal privé entre le bot et vous. \n - !subscribeHere <codebuild> pour souscrire à un cannal de build" );
            }

            if (msg.equalsIgnoreCase("!ping")) {
                sendMessageOnChannel(event.getChannel().getIdAsString(), "Pong! " + event.getChannel().getIdAsString());
            }

            if (msg.equalsIgnoreCase("!sendMeMessage")) {
                event.getMessage().getUserAuthor().ifPresent(user->user.sendMessage("Hello ! :)"));
                sendMessageOnChannel(event.getChannel().getIdAsString(), "C'est fait !");
            }

            if (msg.contains("!subscribeHere")) {
                String channelId = String.valueOf(event.getMessage().getChannel().getId());
                if(subscribeRequest(channelId, msg)){
                    sendMessageOnChannel(channelId, "Tu seras bien notifié lors des prochains builds ! \n - channel : " + channelId);
                }
                else{
                    sendMessageOnChannel(channelId,"Ton numéro de build n'est pas correct");
                }
            }
        });
    }

    private boolean subscribeRequest(String channelId, String subscriberMessage){
        String subscriberCode = subscriberMessage.replace("!subscribeHere", "").trim();

        if(subscriberCode.length() != 8){
            return false;
        };

        BuildCodeChannelMap.put(subscriberCode, channelId);

        return true;
    }

    public static void notify(String buildId){

        if(!BuildCodeChannelMap.containsKey(buildId)) return;

        BuildCodeChannelMap.get(buildId).forEach(channelid ->{
            sendMessageOnChannel(channelid, "Build : " + channelid + " finalisé");
        });
    }

    private static void sendMessageOnChannel(String channelId, String msg){
        api.getTextChannelById(channelId).ifPresent(action-> {
            action.sendMessage(msg);
        });
    }

}

