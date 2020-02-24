package com.hackzen.onboardme.slack.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenFillupFormRequest {
	 private String trigger_id;
	 
	 private String callback_id;
}


//==================================
//package ;
//public class Actions
//{
//    private String name;
//
//    private String type;
//
//    private String value;
//
//    public void setName(String name){
//        this.name = name;
//    }
//    public String getName(){
//        return this.name;
//    }
//    public void setType(String type){
//        this.type = type;
//    }
//    public String getType(){
//        return this.type;
//    }
//    public void setValue(String value){
//        this.value = value;
//    }
//    public String getValue(){
//        return this.value;
//    }
//}
//
//==================================
//package ;
//public class Team
//{
//    private String id;
//
//    private String domain;
//
//    public void setId(String id){
//        this.id = id;
//    }
//    public String getId(){
//        return this.id;
//    }
//    public void setDomain(String domain){
//        this.domain = domain;
//    }
//    public String getDomain(){
//        return this.domain;
//    }
//}
//
//==================================
//package ;
//public class Channel
//{
//    private String id;
//
//    private String name;
//
//    public void setId(String id){
//        this.id = id;
//    }
//    public String getId(){
//        return this.id;
//    }
//    public void setName(String name){
//        this.name = name;
//    }
//    public String getName(){
//        return this.name;
//    }
//}
//
//==================================
//package ;
//public class User
//{
//    private String id;
//
//    private String name;
//
//    public void setId(String id){
//        this.id = id;
//    }
//    public String getId(){
//        return this.id;
//    }
//    public void setName(String name){
//        this.name = name;
//    }
//    public String getName(){
//        return this.name;
//    }
//}
//
//==================================
//package ;
//public class Icons
//{
//    private String image_36;
//
//    private String image_48;
//
//    private String image_72;
//
//    public void setImage_36(String image_36){
//        this.image_36 = image_36;
//    }
//    public String getImage_36(){
//        return this.image_36;
//    }
//    public void setImage_48(String image_48){
//        this.image_48 = image_48;
//    }
//    public String getImage_48(){
//        return this.image_48;
//    }
//    public void setImage_72(String image_72){
//        this.image_72 = image_72;
//    }
//    public String getImage_72(){
//        return this.image_72;
//    }
//}
//
//==================================
//package ;
//public class Bot_profile
//{
//    private String id;
//
//    private boolean deleted;
//
//    private String name;
//
//    private int updated;
//
//    private String app_id;
//
//    private Icons icons;
//
//    private String team_id;
//
//    public void setId(String id){
//        this.id = id;
//    }
//    public String getId(){
//        return this.id;
//    }
//    public void setDeleted(boolean deleted){
//        this.deleted = deleted;
//    }
//    public boolean getDeleted(){
//        return this.deleted;
//    }
//    public void setName(String name){
//        this.name = name;
//    }
//    public String getName(){
//        return this.name;
//    }
//    public void setUpdated(int updated){
//        this.updated = updated;
//    }
//    public int getUpdated(){
//        return this.updated;
//    }
//    public void setApp_id(String app_id){
//        this.app_id = app_id;
//    }
//    public String getApp_id(){
//        return this.app_id;
//    }
//    public void setIcons(Icons icons){
//        this.icons = icons;
//    }
//    public Icons getIcons(){
//        return this.icons;
//    }
//    public void setTeam_id(String team_id){
//        this.team_id = team_id;
//    }
//    public String getTeam_id(){
//        return this.team_id;
//    }
//}
//
//==================================
//package ;
//public class Actions
//{
//    private String id;
//
//    private String name;
//
//    private String text;
//
//    private String type;
//
//    private String value;
//
//    private String style;
//
//    public void setId(String id){
//        this.id = id;
//    }
//    public String getId(){
//        return this.id;
//    }
//    public void setName(String name){
//        this.name = name;
//    }
//    public String getName(){
//        return this.name;
//    }
//    public void setText(String text){
//        this.text = text;
//    }
//    public String getText(){
//        return this.text;
//    }
//    public void setType(String type){
//        this.type = type;
//    }
//    public String getType(){
//        return this.type;
//    }
//    public void setValue(String value){
//        this.value = value;
//    }
//    public String getValue(){
//        return this.value;
//    }
//    public void setStyle(String style){
//        this.style = style;
//    }
//    public String getStyle(){
//        return this.style;
//    }
//}
//
//==================================
//package ;
//import java.util.ArrayList;
//import java.util.List;
//public class Attachments
//{
//    private String callback_id;
//
//    private int id;
//
//    private String color;
//
//    private List<Actions> actions;
//
//    private String fallback;
//
//    public void setCallback_id(String callback_id){
//        this.callback_id = callback_id;
//    }
//    public String getCallback_id(){
//        return this.callback_id;
//    }
//    public void setId(int id){
//        this.id = id;
//    }
//    public int getId(){
//        return this.id;
//    }
//    public void setColor(String color){
//        this.color = color;
//    }
//    public String getColor(){
//        return this.color;
//    }
//    public void setActions(List<Actions> actions){
//        this.actions = actions;
//    }
//    public List<Actions> getActions(){
//        return this.actions;
//    }
//    public void setFallback(String fallback){
//        this.fallback = fallback;
//    }
//    public String getFallback(){
//        return this.fallback;
//    }
//}
//
//==================================
//package ;
//import java.util.ArrayList;
//import java.util.List;
//public class Original_message
//{
//    private String bot_id;
//
//    private String type;
//
//    private String text;
//
//    private String user;
//
//    private String ts;
//
//    private String team;
//
//    private Bot_profile bot_profile;
//
//    private List<Attachments> attachments;
//
//    public void setBot_id(String bot_id){
//        this.bot_id = bot_id;
//    }
//    public String getBot_id(){
//        return this.bot_id;
//    }
//    public void setType(String type){
//        this.type = type;
//    }
//    public String getType(){
//        return this.type;
//    }
//    public void setText(String text){
//        this.text = text;
//    }
//    public String getText(){
//        return this.text;
//    }
//    public void setUser(String user){
//        this.user = user;
//    }
//    public String getUser(){
//        return this.user;
//    }
//    public void setTs(String ts){
//        this.ts = ts;
//    }
//    public String getTs(){
//        return this.ts;
//    }
//    public void setTeam(String team){
//        this.team = team;
//    }
//    public String getTeam(){
//        return this.team;
//    }
//    public void setBot_profile(Bot_profile bot_profile){
//        this.bot_profile = bot_profile;
//    }
//    public Bot_profile getBot_profile(){
//        return this.bot_profile;
//    }
//    public void setAttachments(List<Attachments> attachments){
//        this.attachments = attachments;
//    }
//    public List<Attachments> getAttachments(){
//        return this.attachments;
//    }
//}
//
//==================================
//package ;
//import java.util.ArrayList;
//import java.util.List;
//public class 0
//{
//    private String type;
//
//    private List<Actions> actions;
//
//    private String callback_id;
//
//    private Team team;
//
//    private Channel channel;
//
//    private User user;
//
//    private String action_ts;
//
//    private String message_ts;
//
//    private String attachment_id;
//
//    private String token;
//
//    private boolean is_app_unfurl;
//
//    private Original_message original_message;
//
//    private String response_url;
//
//    private String trigger_id;
//
//    public void setType(String type){
//        this.type = type;
//    }
//    public String getType(){
//        return this.type;
//    }
//    public void setActions(List<Actions> actions){
//        this.actions = actions;
//    }
//    public List<Actions> getActions(){
//        return this.actions;
//    }
//    public void setCallback_id(String callback_id){
//        this.callback_id = callback_id;
//    }
//    public String getCallback_id(){
//        return this.callback_id;
//    }
//    public void setTeam(Team team){
//        this.team = team;
//    }
//    public Team getTeam(){
//        return this.team;
//    }
//    public void setChannel(Channel channel){
//        this.channel = channel;
//    }
//    public Channel getChannel(){
//        return this.channel;
//    }
//    public void setUser(User user){
//        this.user = user;
//    }
//    public User getUser(){
//        return this.user;
//    }
//    public void setAction_ts(String action_ts){
//        this.action_ts = action_ts;
//    }
//    public String getAction_ts(){
//        return this.action_ts;
//    }
//    public void setMessage_ts(String message_ts){
//        this.message_ts = message_ts;
//    }
//    public String getMessage_ts(){
//        return this.message_ts;
//    }
//    public void setAttachment_id(String attachment_id){
//        this.attachment_id = attachment_id;
//    }
//    public String getAttachment_id(){
//        return this.attachment_id;
//    }
//    public void setToken(String token){
//        this.token = token;
//    }
//    public String getToken(){
//        return this.token;
//    }
//    public void setIs_app_unfurl(boolean is_app_unfurl){
//        this.is_app_unfurl = is_app_unfurl;
//    }
//    public boolean getIs_app_unfurl(){
//        return this.is_app_unfurl;
//    }
//    public void setOriginal_message(Original_message original_message){
//        this.original_message = original_message;
//    }
//    public Original_message getOriginal_message(){
//        return this.original_message;
//    }
//    public void setResponse_url(String response_url){
//        this.response_url = response_url;
//    }
//    public String getResponse_url(){
//        return this.response_url;
//    }
//    public void setTrigger_id(String trigger_id){
//        this.trigger_id = trigger_id;
//    }
//    public String getTrigger_id(){
//        return this.trigger_id;
//    }
//}
//
