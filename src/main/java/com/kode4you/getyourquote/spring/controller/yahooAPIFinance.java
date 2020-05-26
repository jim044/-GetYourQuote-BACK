package com.kode4you.getyourquote.spring.controller;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.juli.logging.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/get")
public class yahooAPIFinance {

    @GetMapping("/components")
    public List<String> getCACComponents(@RequestParam String symbol) throws UnirestException, IOException, JSONException {

        HttpResponse<String> response = Unirest.get("https://apidojo-yahoo-finance-v1.p.rapidapi.com/market/get-quotes?region=FR&lang=fr&symbols=" + symbol)
        .header("x-rapidapi-host", "apidojo-yahoo-finance-v1.p.rapidapi.com")
        .header("x-rapidapi-key", "954dc40319msh4f008109627ddbbp1559eajsn2d02192b751e")
        .asString();

        JSONObject jsonObject = null;
        JSONArray jsonArray = null;
        List<String> listComponents = new ArrayList<String>();
        //int regularMarketPrice = 0;

        try{
            jsonObject = new JSONObject(response.getBody());
            System.out.println(jsonObject);
            //regularMarketPrice = Integer.parseInt(jsonObject.getJSONObject("quoteResponse").getJSONArray("result").getJSONObject(0).getString("regularMarketPrice"));
            jsonArray = jsonObject.getJSONObject("quoteResponse").getJSONArray("result").getJSONObject(0).getJSONArray("components");
            for (int i = 0; i < jsonArray.length(); i++)
            {
                String component = jsonArray.getString(i);
                listComponents.add(component);
            }
        }catch (JSONException err){
            System.out.println(err.getMessage());
        }

        return listComponents;
    }

    @GetMapping("/chart")
    public Object getChartByComponent(@RequestParam String interval, @RequestParam String symbol, @RequestParam String range) throws UnirestException, IOException, JSONException {

        HttpResponse<String> response = Unirest.get("https://apidojo-yahoo-finance-v1.p.rapidapi.com/stock/v2/get-chart?interval=" + interval + "&region=FR&symbol=" + symbol + "&lang=fr&range=" + range)
                .header("x-rapidapi-host", "apidojo-yahoo-finance-v1.p.rapidapi.com")
                .header("x-rapidapi-key", "954dc40319msh4f008109627ddbbp1559eajsn2d02192b751e")
                .asString();


        JSONObject jsonObject = null;
        JSONArray jsonArrayHigh = null;
        JSONArray jsonArrayTimeStamp = null;
        Map<Long, Double> mapHighChartResultWithDate = new HashMap<Long, Double>();

        try{
            jsonObject = new JSONObject(response.getBody());
            jsonArrayHigh = jsonObject.getJSONObject("chart").getJSONArray("result").getJSONObject(0).getJSONObject("indicators").getJSONArray("quote").getJSONObject(0).getJSONArray("high");
            jsonArrayTimeStamp = jsonObject.getJSONObject("chart").getJSONArray("result").getJSONObject(0).getJSONArray("timestamp");

            for (int i = 0; i < jsonArrayHigh.length(); i++)
            {
                mapHighChartResultWithDate.put(Long.parseLong(jsonArrayTimeStamp.get(i).toString()), Double.parseDouble(jsonArrayHigh.get(i).toString()));
                System.out.println(mapHighChartResultWithDate);
            }
        }catch (JSONException err){
            System.out.println(err.getMessage());
        }

        System.out.println(jsonObject);

        return mapHighChartResultWithDate;
    }

}
