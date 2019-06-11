package com.symphony.hkex.margincall;

import clients.SymBotClient;
import listeners.RoomListener;
import model.InboundMessage;
import model.OutboundMessage;
import model.Stream;
import model.events.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RoomListenerTestImpl implements RoomListener {

    private static Logger LOGGER = LoggerFactory.getLogger(RoomListenerTestImpl.class);

    private SymBotClient botClient;

    public RoomListenerTestImpl(SymBotClient botClient) {
        this.botClient = botClient;
    }

    private final Logger logger = LoggerFactory.getLogger(RoomListenerTestImpl.class);

    public void onRoomMessage(InboundMessage inboundMessage) {
        String inboundMessageText = inboundMessage.getMessageText();
        String inboundMsg = inboundMessage.getMessage();
        String callId =  "";

        LOGGER.info("Incoming Room Message:\n" + inboundMessageText);
        if (inboundMessageText != null) {
            if (inboundMessageText.contains("D-")) {

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                callId = inboundMsg.substring(inboundMsg.indexOf("D-"));
                callId = callId.substring(0, callId.indexOf("</")).trim();
                OutboundMessage messageOut = new OutboundMessage();
                String messageOutText = "Noted. Let's sort out the funding";
                messageOut.setMessage(messageOutText);
                try {
                    this.botClient.getMessagesClient().sendMessage(inboundMessage.getStream().getStreamId(), messageOut);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                OutboundMessage messageOut1 = new OutboundMessage();
                String messageOutText1 = "fund is ready " + callId;
                messageOut1.setMessage(messageOutText1);
                try {
                    this.botClient.getMessagesClient().sendMessage(inboundMessage.getStream().getStreamId(), messageOut1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (inboundMessageText.contains("83074") && inboundMessageText.contains("C-")) {

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                callId = inboundMsg.substring(inboundMsg.indexOf("C-"));
                callId = callId.substring(0, callId.indexOf("</")).trim();
                OutboundMessage messageOut = new OutboundMessage();
                String messageOutText = "Noted. Let's sort out the funding";
                messageOut.setMessage(messageOutText);
                try {
                    this.botClient.getMessagesClient().sendMessage(inboundMessage.getStream().getStreamId(), messageOut);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                OutboundMessage messageOut1 = new OutboundMessage();
                String messageOutText1 = "Hi HSBC, the payment is ready " + callId;
                messageOut1.setMessage(messageOutText1);
                try {
                    this.botClient.getMessagesClient().sendMessage(inboundMessage.getStream().getStreamId(), messageOut1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (inboundMessageText.contains("00005") && inboundMessageText.contains("C-")) {
                callId = inboundMessageText.substring(inboundMessageText.indexOf("C-"), inboundMessageText.indexOf("C-") + 5).trim();

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                callId = inboundMsg.substring(inboundMsg.indexOf("C-"));
                callId = callId.substring(0, callId.indexOf("</")).trim();
                OutboundMessage messageOut = new OutboundMessage();
                String messageOutText = "Noted. Let's sort out the funding";
                messageOut.setMessage(messageOutText);
                try {
                    this.botClient.getMessagesClient().sendMessage(inboundMessage.getStream().getStreamId(), messageOut);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                OutboundMessage messageOut1 = new OutboundMessage();
//                String messageOutText1 = "Sorry we can't make it before due time " + callId;

                String messageOutText1 = "Sorry for long waiting, Ok, fund is ready " + callId;
                messageOut1.setMessage(messageOutText1);
                try {
                    this.botClient.getMessagesClient().sendMessage(inboundMessage.getStream().getStreamId(), messageOut1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (inboundMessageText.contains("#Clean")) {
                String messageOutTextclean = " &#10;&#13; &#10;&#13; &#10;&#13; &#10;&#13; &#10;&#13; &#10;&#13; &#10;&#13; &#10;&#13; &#10;&#13; &#10;&#13; &#10;&#13; &#10;&#13; &#10;&#13; &#10;&#13; &#10;&#13; &#10;&#13; &#10;&#13; &#10;&#13; &#10;&#13; &#10;&#13; &#10;&#13; &#10;&#13; &#10;&#13; &#10;&#13; &#10;&#13; &#10;&#13; &#10;&#13; &#10;&#13; &#10;&#13; &#10;&#13; &#10;&#13; &#10;&#13; &#10;&#13; ";
                OutboundMessage messageOut = new OutboundMessage();
                messageOut.setMessage(messageOutTextclean);
                try {
                    this.botClient.getMessagesClient().sendMessage(inboundMessage.getStream().getStreamId(), messageOut);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public void onRoomCreated(RoomCreated roomCreated) {

    }

    public void onRoomDeactivated(RoomDeactivated roomDeactivated) {

    }

    public void onRoomMemberDemotedFromOwner(RoomMemberDemotedFromOwner roomMemberDemotedFromOwner) {

    }

    public void onRoomMemberPromotedToOwner(RoomMemberPromotedToOwner roomMemberPromotedToOwner) {

    }

    public void onRoomReactivated(Stream stream) {

    }

    public void onRoomUpdated(RoomUpdated roomUpdated) {

    }

    public void onUserJoinedRoom(UserJoinedRoom userJoinedRoom) {

    }

    public void onUserLeftRoom(UserLeftRoom userLeftRoom) {

    }
}
