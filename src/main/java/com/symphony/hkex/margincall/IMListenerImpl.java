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

        LOGGER.info("Incoming IM Message:\n" + inboundMessageText);
        if (inboundMessageText != null) {
            if (inboundMessageText.contains("HKKGI2")) {
                OutboundMessage messageOut = new OutboundMessage();
                String messageOutText = "fund is ready";
                messageOut.setMessage(messageOutText);
                try {
                    this.botClient.getMessagesClient().sendMessage(inboundMessage.getStream().getStreamId(), messageOut);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (inboundMessageText.contains("82843")) {
                OutboundMessage messageOut = new OutboundMessage();
                String messageOutText = "fund is ready";
                messageOut.setMessage(messageOutText);
                try {
                    this.botClient.getMessagesClient().sendMessage(inboundMessage.getStream().getStreamId(), messageOut);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (inboundMessageText.contains("83155")) {
                OutboundMessage messageOut = new OutboundMessage();
                String messageOutText = "fund is not ready";
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
