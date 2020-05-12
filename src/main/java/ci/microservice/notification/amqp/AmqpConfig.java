package ci.microservice.notification.amqp;


//import org.springframework.amqp.core.*;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfig {

    public static final String EXCHANGE_NAME = "event_notification_exchange";
    public static final String QUEUE_NAME = "event_notification_queue";

      @Bean
    // @Primary
    public ConnectionFactory createConnctionFactory(){

        CachingConnectionFactory connectionFactory=new CachingConnectionFactory("chinook.rmq.cloudamqp.com");
        connectionFactory.setUsername("fhnnluqg");
        connectionFactory.setPassword("eBUG9yxdq0m5mBb_lmT7SWBXdQX_fZZb");
        connectionFactory.setVirtualHost("fhnnluqg");

        return connectionFactory;
    }

    @Bean
    // @Primary
    public RabbitAdmin createAdmin(){
        ConnectionFactory ConnectionFactory =createConnctionFactory();
        RabbitAdmin admin = new RabbitAdmin(ConnectionFactory);

        return admin;
    }


    @Bean
    Queue createQueue() {
        RabbitAdmin admin = createAdmin();
        Queue queue = new Queue(QUEUE_NAME, true, false, false);
        admin.declareQueue(queue);
        return queue;
    }

    @Bean
    TopicExchange exchange(){
        TopicExchange exchange = new TopicExchange(EXCHANGE_NAME);
        RabbitAdmin admin = createAdmin();
        admin.declareExchange(exchange);
        return exchange;
    }

    @Bean
    Binding binding(Queue q, TopicExchange exchange){
        RabbitAdmin admin = createAdmin();
        admin.declareBinding(BindingBuilder.bind(q).to(exchange).with("mike.#"));
        return BindingBuilder.bind(q).to(exchange).with("mike.#");
    }

  /*  @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory
            , MessageListenerAdapter messageListenerAdapter){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QUEUE_NAME);
        container.setMessageListener(messageListenerAdapter);
        return container;
    }*/


/*
    @Bean
    MessageListenerAdapter listenerAdapter(ReceiveMessageHandler handler){
        return new MessageListenerAdapter(handler, "handleMessage");
    }
*/




}



/*public static  final String AUTH_QUEUE = "auth-queue";
    public static  final String AUTH_EXCHANGE = "auth-exchange";
    @Bean
    Queue authQueue(){
        return QueueBuilder
                .durable("AUTH_QUEUE")
                .build();

    }


    @Bean
    TopicExchange authExchange(){
        return  new TopicExchange(AUTH_EXCHANGE);
    }

    @Bean
    Binding authBinding(){
        return BindingBuilder.bind(authQueue()).to(authExchange()).with("#");
    }*/
