package com.imdb.sa.Model;


import org.json.*;

import java.util.HashMap;
import java.util.Map;

public class Genres {
    private Map<Integer,String> genres;
    private final String genresJson = "{\n" +
            "    'genres': [\n" +
            "        {\n" +
            "            'id': 28,\n" +
            "            'name': 'Action'\n" +
            "        },\n" +
            "        {\n" +
            "            'id': 12,\n" +
            "            'name': 'Adventure'\n" +
            "        },\n" +
            "        {\n" +
            "            'id': 16,\n" +
            "            'name': 'Animation'\n" +
            "        },\n" +
            "        {\n" +
            "            'id': 35,\n" +
            "            'name': 'Comedy'\n" +
            "        },\n" +
            "        {\n" +
            "            'id': 80,\n" +
            "            'name': 'Crime'\n" +
            "        },\n" +
            "        {\n" +
            "            'id': 99,\n" +
            "            'name': 'Documentary'\n" +
            "        },\n" +
            "        {\n" +
            "            'id': 18,\n" +
            "            'name': 'Drama'\n" +
            "        },\n" +
            "        {\n" +
            "            'id': 10751,\n" +
            "            'name': 'Family'\n" +
            "        },\n" +
            "        {\n" +
            "            'id': 14,\n" +
            "            'name': 'Fantasy'\n" +
            "        },\n" +
            "        {\n" +
            "            'id': 36,\n" +
            "            'name': 'History'\n" +
            "        },\n" +
            "        {\n" +
            "            'id': 27,\n" +
            "            'name': 'Horror'\n" +
            "        },\n" +
            "        {\n" +
            "            'id': 10402,\n" +
            "            'name': 'Music'\n" +
            "        },\n" +
            "        {\n" +
            "            'id': 9648,\n" +
            "            'name': 'Mystery'\n" +
            "        },\n" +
            "        {\n" +
            "            'id': 10749,\n" +
            "            'name': 'Romance'\n" +
            "        },\n" +
            "        {\n" +
            "            'id': 878,\n" +
            "            'name': 'Science Fiction'\n" +
            "        },\n" +
            "        {\n" +
            "            'id': 10770,\n" +
            "            'name': 'TV Movie'\n" +
            "        },\n" +
            "        {\n" +
            "            'id': 53,\n" +
            "            'name': 'Thriller'\n" +
            "        },\n" +
            "        {\n" +
            "            'id': 10752,\n" +
            "            'name': 'War'\n" +
            "        },\n" +
            "        {\n" +
            "            'id': 37,\n" +
            "            'name': 'Western'\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    public Genres(){
        this.genres = DecodeJson();
    }

    private Map<Integer,String> DecodeJson() {
            Map<Integer, String> result = new HashMap<Integer, String>();
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(genresJson);
                JSONArray msg = (JSONArray) jsonObject.get("genres");
                for(int i=0; i<msg.length(); i++){
                    result.put(((JSONObject)msg.get(i)).getInt("id"),((JSONObject)msg.get(i)).getString("name"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
            return result;
    }
}
