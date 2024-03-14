package com.club.eliteclub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.club.eliteclub.model.ClubDTO;
import com.club.eliteclub.service.EliteClubServiceImpl;


@RestController
public class EliteClubController {
    private EliteClubServiceImpl eliteClubService;

    @Autowired
    public void setEliteClubService(EliteClubServiceImpl eliteClubService) {
        this.eliteClubService = eliteClubService;
    }

    @GetMapping(path = "/clubs", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ClubDTO> clubs() {
        return eliteClubService.getAll();
    }
}
