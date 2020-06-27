package ci.microservice.notification.discord.service;


import ci.microservice.notification.adresseMail.dao.AdresseMailRepository;
import ci.microservice.notification.discord.dao.RequestRepository;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.channel.server.text.WebhooksUpdateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DiscordServiceImpl implements DiscordService {

    @Autowired
    RequestRepository requestRepository;

    private static String Token1 = "NzEwNzg1NjExMDk5MDEzMTMw.";

    private static String Token = DiscordServiceImpl.Token1 +  "Xvdbjg.Pt6qty5Aj9M5oF1XZ0D5_g2aNVI";

    public static Multimap<String, String> BuildCodeChannelMap = ArrayListMultimap.create();

    private static DiscordApi api = new DiscordApiBuilder().setToken(Token).login().join();

    public DiscordServiceImpl(){

    }

    @Override
    public void discordConnexion(){
        System.out.println(api.createBotInvite());

        api.addMessageCreateListener(event -> {

            if(event.getChannel().getMessagesAfterAsStream(event.getMessage()).count() > 0) return;

            String msg = event.getMessageContent();

            if (msg.equalsIgnoreCase("!help")) {
                sendMessageOnChannel(event.getChannel().getIdAsString(), "Entrez : \n - !sendMeMessage pour ouvrir un cannal privé entre le bot et vous. \n - !subscribeHere <codebuild> pour souscrire à un cannal de build" );
            }

            if (msg.equalsIgnoreCase("!ping")) {
                sendMessageOnChannel(event.getChannel().getIdAsString(), "Ponge! " + event.getChannel().getIdAsString());
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

    @Override
    public boolean subscribeRequest(String channelId, String subscriberMessage){
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

