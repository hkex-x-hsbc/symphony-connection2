package com.symphony.hkex.margincall;

import clients.SymBotClient;
import listeners.IMListener;
import model.InboundMessage;
import model.OutboundMessage;
import model.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IMListenerImpl implements IMListener {

    private static Logger LOGGER = LoggerFactory.getLogger(IMListenerImpl.class);

    private SymBotClient botClient;

    public IMListenerImpl(SymBotClient botClient) {
        this.botClient = botClient;
    }

    public void onIMMessage(InboundMessage inboundMessage) {

        String inboundMessageText = inboundMessage.getMessageText();
        String callId =  "";

        LOGGER.info("Incoming IM Message:\n" + inboundMessageText);
        if (inboundMessageText != null) {
            if (inboundMessageText.contains("D-")) {
                callId = inboundMessageText.substring(inboundMessageText.indexOf("D-"), inboundMessageText.indexOf("D-") + 5).trim();
                OutboundMessage messageOut = new OutboundMessage();
                String messageOutText = "fund is ready " + callId;
                messageOut.setMessage(messageOutText);
                try {
                    this.botClient.getMessagesClient().sendMessage(inboundMessage.getStream().getStreamId(), messageOut);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (inboundMessageText.contains("83074") && inboundMessageText.contains("C-")) {
                callId = inboundMessageText.substring(inboundMessageText.indexOf("C-"), inboundMessageText.indexOf("C-") + 5).trim();
                OutboundMessage messageOut = new OutboundMessage();
                String messageOutText = "Hi HSBC, the payment is ready " + callId;
                messageOut.setMessage(messageOutText);
                try {
                    this.botClient.getMessagesClient().sendMessage(inboundMessage.getStream().getStreamId(), messageOut);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (inboundMessageText.contains("00005") && inboundMessageText.contains("C-")) {
                callId = inboundMessageText.substring(inboundMessageText.indexOf("C-"), inboundMessageText.indexOf("C-") + 5).trim();
                OutboundMessage messageOut = new OutboundMessage();
                String messageOutText = "Sorry for long waiting, Ok, fund is ready " + callId;
                messageOut.setMessage(messageOutText);
                try {
                    this.botClient.getMessagesClient().sendMessage(inboundMessage.getStream().getStreamId(), messageOut);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (inboundMessageText.contains("Hi Ameli")) {
                String outboundtext = "";
                OutboundMessage messageTxt = new OutboundMessage();
                if (inboundMessageText.contains("hot topics")){
                    outboundtext="Topics";
                }else if (inboundMessageText.contains("outstanding items")){
                    outboundtext="o/s item";
                }else if (inboundMessageText.contains("reminder")){
                    outboundtext="reminder";
                }
                messageTxt.setMessage(outboundtext);
                try {
                    this.botClient.getMessagesClient().sendMessage(inboundMessage.getStream().getStreamId(), messageTxt);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }


    public void onIMCreated(Stream stream) {

    }

}
