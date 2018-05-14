package com.imdb.sa.Model;

import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class User {
    @SerializedName("id")
    private int id;
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;
    @SerializedName("name")
    private String name;
    private String favorites, seens;

    public User(String username, String password, String name){
        this.username = username;
        this.password=password;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFavoritesString(){
        return this.favorites;
    }

    public List<Integer> getFavorites() {
        List<Integer> favsarray = null;
        try {
            JSONObject favsjson = new JSONObject(favorites);
            JSONArray favsjsonarray = favsjson.getJSONArray("favorites");
            favsarray = new ArrayList<Integer>();
            for(int i=0; i<favsjsonarray.length(); i++){
                favsarray.add(favsjsonarray.getInt(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return favsarray;
        }
        return favsarray;
    }

    public void setFavorites(List<Integer> favorites) throws JSONException {
        JSONArray favJSONarray = new JSONArray();
        for (Integer fav: favorites) {
            favJSONarray.put(fav);
        }
        this.favorites = favJSONarray.toString();
    }

    public List<Integer> getSeens() {
        List<Integer> seensarray = null;
        try {
            JSONObject seensjson = new JSONObject(favorites);
            JSONArray seensjsonarray = seensjson.getJSONArray("seens");
            seensarray = new ArrayList<Integer>();
            for(int i=0; i<seensjsonarray.length(); i++){
                seensarray.add(seensjsonarray.getInt(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return seensarray;
        }
        return seensarray;
    }

    public void setSeens(List<Integer> seens) throws JSONException {
        JSONArray seensJSONarray = new JSONArray();
        for (Integer seen: seens) {
            seensJSONarray.put(seen);
        }
        this.seens = seensJSONarray.toString();
    }


}
