package ci.microservice.notification.discord.service;


import ci.microservice.notification.adresseMail.dao.AdresseMailRepository;
import ci.microservice.notification.discord.dao.RequestRepository;
import ci.microservice.notification.discord.models.DiscordRequest;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.channel.server.text.WebhooksUpdateEvent;
import org.javacord.api.event.message.MessageCreateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DiscordServiceImpl implements DiscordService {

    private static String Token1 = "NzEwNzg1NjExMDk5MDEzMTMw.";

    private static String Token = DiscordServiceImpl.Token1 +  "Xvdbjg.Pt6qty5Aj9M5oF1XZ0D5_g2aNVI";

    public static Multimap<String, String> BuildCodeChannelMap = ArrayListMultimap.create();

    private static DiscordApi api = new DiscordApiBuilder().setToken(Token).login().join();

    public DiscordServiceImpl(){

    }

    @Override
    public void discordConnexion(){

        api.addMessageCreateListener(event -> {
            if(event.getChannel().getMessagesAfterAsStream(event.getMessage()).count() > 0) return;

            String msg = event.getMessageContent();

            String response = null;

            String channelId = event.getMessage().getChannel().getIdAsString();

            if (msg.equalsIgnoreCase("!help")) {
                response = "Entrez : \n - !sendMeMessage pour ouvrir un cannal privé entre le bot et vous. \n - !subscribeHere <codebuild> pour souscrire à un cannal de build";
            }

            if (msg.equalsIgnoreCase("!ping")) {
                response = "Pongeee! " + event.getChannel().getIdAsString();
            }

            if (msg.equalsIgnoreCase("!sendMeMessage")) {
                event.getMessage().getUserAuthor().ifPresent(user->user.sendMessage("Hello ! :)"));
                response = "C'est fait !";
            }

            if (msg.contains("!subscribeHere")) {
                if(subscribeRequest(channelId, msg)){
                    response = "Tu seras bien notifié lors des prochains builds ! \n - channel : " + channelId;
                }
                else{
                    sendMessageOnChannel(channelId,"Ton numéro de build n'est pas correct");
                    response = "Ton numéro de build n'est pas correct";
                }
            }

            if(response != null){
                if(!this.isDuplicateMessageOnCache(response)){
                    sendMessageOnChannel(channelId, response);
                }
            }

            deleteDuplicateMessage(event);
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

    private static void sendMessageOnChannel(String channelId, Message msg){
        api.getTextChannelById(channelId).ifPresent(action-> {
            action.sendMessage(msg.getContent());
        });
    }

    private static void sendMessageOnChannel(String channelId, String msg){
        api.getTextChannelById(channelId).ifPresent(action-> {
            action.sendMessage(msg);
        });
    }

    private boolean isDuplicateMessageOnCache(String message){
        boolean isDuplicateMessageOnCache = false;
        for(Message m : api.getCachedMessages()){
            if(m.getContent().equals(message)){
                isDuplicateMessageOnCache = true;
            }
        }
        return isDuplicateMessageOnCache;
    }

    private void deleteDuplicateMessage(MessageCreateEvent event){
        int count = 0;
        for (Message m : event.getChannel().getMessagesAfterAsStream(event.getMessage()).collect(Collectors.toList())){

            if(count > 0){
                event.getChannel().deleteMessages(m);
            }
            count += 1;
        }
    }

}

