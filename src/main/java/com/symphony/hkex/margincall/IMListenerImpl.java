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
                callId = inboundMessageText.substring(inboundMessageText.indexOf("D-") + 2, inboundMessageText.indexOf("D-") + 5).trim();
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
                String messageOutText = "Sorry we can't make it before due time " + callId;
                messageOut.setMessage(messageOutText);
                try {
                    this.botClient.getMessagesClient().sendMessage(inboundMessage.getStream().getStreamId(), messageOut);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }


    public void onIMCreated(Stream stream) {

    }

}
