package onlinechat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ChatMessage implements Serializable // реализуем для возможности сериализации обьекта (разбиение на поток байт)
{
    private LocalDateTime departureDate; //дата отправки
    private String sender; //отправитель
    private String textMessage; //текст сообщения
    private int code = 100;

    public ChatMessage(String sender, String textMessage)
    {
        this.sender = sender;
        this.textMessage = textMessage;
    }

    public int getCode() {
        return code;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public void setDepartureDate () //метод устанавливающий текущее время (для установки времени отправки сообщения)
    {
        setDepartureDate(LocalDateTime.now());
    }

    @Override
    public String toString()
    {
        String []  s = textMessage.split(" ");
        int codeLength = s[s.length-1].length();
        String mes = textMessage.substring(0,textMessage.length()-codeLength);

        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMMM, dd, yyyy, hh:mm");

        return "[Sender: " + sender + " (" + departureDate.format(format) + ")" + "]: " + mes;
    }
}
