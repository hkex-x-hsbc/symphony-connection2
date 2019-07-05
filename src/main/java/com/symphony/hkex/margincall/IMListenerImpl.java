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
                    outboundtext= "Hi Dear, <br/> Here are the top 3 hot topics when you away<br /><br />" +
                            "                            <table>" +
                            "                            <tr>" +
                            "                            <td><b>No.</b></td>" +
                            "                            <td><b>Topic</b></td>" +
                            "                            <td><b>Email Number</b></td>" +
                            "                            </tr>" +
                            "                            <tr>" +
                            "                            <td> 1 </td>" +
                            "                            <td> Project X </td>" +
                            "                            <td> 60 </td>" +
                            "                            </tr>" +
                            "                            <tr>" +
                            "                            <td> 2 </td>" +
                            "                            <td> Amy Schumer </td>" +
                            "                            <td> 40 </td>" +
                            "                            </tr>" +
                            "                            <tr>" +
                            "                            <td> 3 </td>" +
                            "                            <td> Macys </td>" +
                            "                            <td> 30 </td>" +
                            "                            </tr>" +
                            "                            </table>" +
                            "                            <br/>" +
                            "                            For more detail, can see in <b>Dashboard</b>.<br/>";
                } else if (inboundMessageText.contains("outstanding items")){
                    outboundtext="Hi dear,<br /> Here are the 3 most urgent tasks from your mailbox<br /><br />" +
                            "<table>" +
                            "<tr>" +
                            "<td><b>No.</b></td>" +
                            "<td><b>Related Topic</b></td>" +
                            "<td><b>From</b></td>" +
                            "<td><b>Action</b></td>" +
                            "<td><b>Due Time</b></td>" +
                            "<td><b>Set Reminder</b></td>" +
                            "</tr>" +
                            "<tr>" +
                            "<td> 1 </td>" +
                            "<td> Project X </td>" +
                            "<td> Steve </td>" +
                            "<td> Confirm the budget </td>" +
                            "<td> 2 hour left </td>" +
                            "<td> 30 min before deadline </td>" +
                            "</tr>" +
                            "<tr>" +
                            "<td> 2 </td>" +
                            "<td> Project X </td>" +
                            "<td> Tony </td>" +
                            "<td> Arrange a meeting </td>" +
                            "<td> 4 hour left </td>" +
                            "<td> 1 hour before deadline </td>" +
                            "</tr>" +
                            "<tr>" +
                            "<td> 3 </td>" +
                            "<td> Project X </td>" +
                            "<td> Peter </td>" +
                            "<td> Dinner together </td>" +
                            "<td> 8 hour left </td>" +
                            "<td> 5 min before deadline </td>" +
                            "</tr>" +
                            "</table>" +
                            "<br/>" +
                            "For more detail, you can find in <a href=\"about:blank\">Dashboard</a>.<br/>";
                }else if (inboundMessageText.contains("reminder")) {
                    outboundtext = "No outstanding reminder, do you want me remind outstanding task?";
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
