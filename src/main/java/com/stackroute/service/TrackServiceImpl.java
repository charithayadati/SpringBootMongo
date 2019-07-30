package com.stackroute.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


import static org.springframework.http.HttpHeaders.USER_AGENT;

@Service
public class TrackServiceImpl implements TrackService{
    @Autowired
    TrackRepository trackRepository;
    @Autowired
    public  String GET_URL= "http://ws.audioscrobbler.com/2.0/?method=artist.getinfo&artist=Cher&api_key=7d6f2b9fc930e3787b10435ea7b7de4a&format=json";

    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    public TrackServiceImpl() {

    }
//saves the new track
    @Override
    public boolean saveTrack(Track track) {
        Track saveduser=trackRepository.save(track);
        return  true;

    }
//deletes the track
    @Override
    public boolean deleteTrack(int id) {
        if(trackRepository.existsById(id))
        {
            Track track=getTrackById(id);
            trackRepository.delete(track);
            return true;
        }
        else
        {
            return false;
        }
    }
    //returns track by id
    @Override
    public Track getTrackById(int id) {
        return getTrackById(id);
    }
//updates the track
    @Override
    public boolean UpdateTrack(Track track) {
        return true;
    }
//fetches all the tracks
    @Override
    public String getAllTracks ()throws IOException {
        URL obj = new URL(GET_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            return response.toString();
        }
        return "Get request not worked";
    }

}





