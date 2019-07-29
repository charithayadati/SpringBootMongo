package com.stackroute.service;

import com.stackroute.domain.Track;

public interface TrackService {


   public boolean saveTrack(Track track);

    public boolean deleteTrack(int id);

    public String getAllTracks() throws Exception;

   public Track getTrackById(int id);

    public boolean UpdateTrack(Track track);
}
