package com.kode4you.getyourquote.spring.controller;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.juli.logging.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/getQuote")
public class yahooAPIFinance {

    @GetMapping("/")
    public String getCACComponents() throws UnirestException, IOException, JSONException {

        HttpResponse<String> response = Unirest.get("https://apidojo-yahoo-finance-v1.p.rapidapi.com/market/get-quotes?region=FR&lang=fr&symbols=%255EFCHI")
        .header("x-rapidapi-host", "apidojo-yahoo-finance-v1.p.rapidapi.com")
        .header("x-rapidapi-key", "954dc40319msh4f008109627ddbbp1559eajsn2d02192b751e")
        .asString();

        JSONObject jsonObject = null;
        JSONArray arrayObject = null;

        try{
            jsonObject = new JSONObject(response.getBody());
            arrayObject = jsonObject.getJSONObject("quoteResponse").getJSONArray("result").getJSONObject(0).getJSONArray("components");
            for (int i = 0; i < arrayObject.length(); i++)
            {
                String component = arrayObject.getString(i);
                System.out.println(component);
            }
        }catch (JSONException err){
            System.out.println(err.getMessage());
        }

        String arrayString = arrayObject.toString();
        return arrayString;
    }
}
