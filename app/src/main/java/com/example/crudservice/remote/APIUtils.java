package com.example.crudservice.remote;

public class APIUtils {

    private APIUtils(){

    };

    public static final String API_url = "http://192.168.43.194:8888/blog2/public/api/";

    public static DataService getDataService(){
        return RetroClient.getClient(API_url).create(DataService.class);
    }

}
