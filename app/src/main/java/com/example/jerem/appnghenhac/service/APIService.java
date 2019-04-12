package com.example.jerem.appnghenhac.service;

public class APIService {
   private static String BASE_URL="https://jeremy04.herokuapp.com/";
  // private static String BASE_URL="http://192.168.0.109:3000/";

    public static DataService getService(){
           return APIRetrofitClient.getClient(BASE_URL).create(DataService.class);
    }
}
