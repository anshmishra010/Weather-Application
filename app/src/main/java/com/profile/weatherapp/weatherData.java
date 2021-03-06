package com.profile.weatherapp;
import org.json.JSONException;
import org.json.JSONObject;

public class weatherData {

    // as we are only displaying 4 things
    private String mTemperature,micon,mcity,mWeatherType;
    private int mCondition;

    //taking the data generated from the JASON fie
    public static weatherData fromJson(JSONObject jsonObject)
    {

        //All the data we are getting from the API call, according to that we will call the variable
        try//featching data
        {

            weatherData weatherD=new weatherData();
            //city name
            weatherD.mcity=jsonObject.getString("name");
            //as in jason generated by API , to get the temp condition the integer value is given to monitor the type of weather
            weatherD.mCondition=jsonObject.getJSONArray("weather").getJSONObject(0).getInt("id");
            // main the type wether it's clear/cloudy/sunny etc.
            weatherD.mWeatherType=jsonObject.getJSONArray("weather").getJSONObject(0).getString("main");
            weatherD.micon=updateWeatherIcon(weatherD.mCondition);
            // for tempratue as temp is in Kelvin thats why we wre subtracting
            double tempResult=jsonObject.getJSONObject("main").getDouble("temp")-273.15;
            int roundedValue=(int)Math.rint(tempResult);
            weatherD.mTemperature=Integer.toString(roundedValue);
            return weatherD;
        }


        catch (JSONException e) {
            e.printStackTrace();
            return null;
        }


    }

// now here are comparing the id which we got
// as these condition are predefined in by API of the OPEN WEATHER WEBSITE
    private static String updateWeatherIcon(int condition)
    {
        if(condition>=0 && condition<=300)
        {
            return "thunderstrom1";
        }
        else if(condition>=300 && condition<=500)
        {
            return "lightrain";
        }
        else if(condition>=500 && condition<=600)
        {
            return "shower";
        }
        else  if(condition>=600 && condition<=700)
        {
            return "snow2";
        }
        else if(condition>=701 && condition<=771)
        {
            return "fog";
        }

        else if(condition>=772 && condition<=800)
        {
            return "overcast";
        }
        else if(condition==800)
        {
            return "sunny";
        }
        else if(condition>=801 && condition<=804)
        {
            return "cloudy";
        }
        else  if(condition>=900 && condition<=902)
        {
            return "thunderstrom1";
        }
        if(condition==903)
        {
            return "snow1";
        }
        if(condition==904)
        {
            return "sunny";
        }
        if(condition>=905 && condition<=1000)
        {
            return "thunderstrom2";
        }

        return "dunno";

    }

    // THESE ALL ARE GETTER  , as it was declared private
// for temp
    public String getmTemperature() {
        return mTemperature+"??C";
    }

    public String getMicon() {
        return micon;
    }
// for city
    public String getMcity() {
        return mcity;
    }
// weather type
    public String getmWeatherType() {
        return mWeatherType;
    }
}