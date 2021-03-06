/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rexsdesktop.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import rexsdesktop.model.Db;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JComboBox;

/**
 * Clase que contiene los atributos y métodos de una ubicación.
 *
 * @author Eduardo
 * @version 1.2
 */
public class Locations {

    public static String error = "";
    
    public static HashMap ubicacionesNombres;

    /**
     * Método utilizado para cargar las ubicaciones de la API MapWize.
     */
    public static List<String> getPlaces() {
        try {
            List<String> places = new ArrayList<>();
            String APIPlaces = String.format("https://api.mapwize.io/v1/places?api_key=%s&venueId=%s&isPublished=all", General.getMapwizeAPIKey(), General.getMapwizeVenueID());
            URL url = new URL(APIPlaces);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new Exception("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String output;
            JSONParser parser = new JSONParser();
            if ((output = br.readLine()) != null) {
                Object obj = parser.parse(output);
                JSONArray array = (JSONArray) obj;
                for (int i = 0; i < array.size(); i++) {
                    JSONObject place = (JSONObject) array.get(i);
                    String placeId = String.valueOf(place.get("_id"));
                    places.add(placeId);
                    //String placeName = getPlaceName(placeId);
                }
                return places;
            }
        } catch (Exception e) {
            System.out.println("Exception in getPlaces():- " + e);
        }
        return null;
    }
    
    public static void loadPlacesNamesId(){
        Db db = new Db();
        ubicacionesNombres = db.cargarUbicacionesLogin();
    }

    /**
     * Método utilizado para cargar los nombres de las ubicaciones de la API
     * MapWize.
     */
    public static String getPlaceName(String idPlace){
        return String.valueOf(ubicacionesNombres.get(idPlace));
    }
    public static String getPlaceNameMapwize(String placeId) {
        String placeName = "";
        try {
            String APIPlace = String.format("https://api.mapwize.io/v1/places/%s?api_key=%s", placeId, General.getMapwizeAPIKey());
            URL url = new URL(APIPlace);//your url i.e fetch data from .
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String output;
            JSONParser parser = new JSONParser();
            if ((output = br.readLine()) != null) {
                Object obj = parser.parse(output);
                JSONObject place = (JSONObject) obj;
                placeName = String.valueOf(place.get("name"));

            }
        } catch (Exception e) {
            System.out.println("Exception in getPlaceName(): -" + e);
        }
        return placeName;
    }

    public static String getAlias(String placeId) {
        String alias = "";
        try {
            String APIPlace = String.format("https://api.mapwize.io/v1/places/%s?api_key=%s", placeId, General.getMapwizeAPIKey());
            URL url = new URL(APIPlace);//your url i.e fetch data from .
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String output;
            JSONParser parser = new JSONParser();
            if ((output = br.readLine()) != null) {
                Object obj = parser.parse(output);
                JSONObject place = (JSONObject) obj;
                alias = String.valueOf(place.get("alias"));

            }
        } catch (Exception e) {
            System.out.println("Exception in getPlaceName(): -" + e);
        }
        return alias;
    }

    public static boolean loadPlacesToDb() {
        List<String> lugares = getPlaces();
        if (lugares != null) {
            if (!lugares.isEmpty()) {
                Db db = new Db();
                return db.loadPlacesToDb(lugares);
            }
        }
        return false;
    }

    public static List<String> getPlacesFromDb() {
        List<String> places = new ArrayList<>();
        try {
            Db db = new Db();
            ResultSet rs = db.getPlaces();
            if (rs != null) {

                while (rs.next()) {
                    String idPlace = rs.getString(1);
                    places.add(idPlace);
                }
            }
        } catch (Exception e) {
            System.out.println("Error getPlacesFromDb(): " + e);
        }
        return places;
    }
    
    

    public static int getIdUbicacion(String ubicacion) {
        Db db = new Db();
        return db.getIdUbicacion(ubicacion);
    }
    
    public Map<String, Location> createMap() {
        Map<String, Location> map = new HashMap<>();
        List<String> ubicaciones = Locations.getPlacesFromDb();
        for (String ubicacion : ubicaciones) {
            Location s = new Location(ubicacion, Locations.getPlaceName(ubicacion));
            map.put(s.getName(), s);
        }
        return map;
    }

    public void createComboBox(final Map<String, Location> map, JComboBox cbox) {
        for (String name : map.keySet()) {
            cbox.addItem(name);
        }

        cbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = (String) cbox.getSelectedItem();
                System.out.println(map.get(name));
            }
        });
    }

    public class Location {

        String name;
        String id;

        public Location(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return id;
        }
    }
}
