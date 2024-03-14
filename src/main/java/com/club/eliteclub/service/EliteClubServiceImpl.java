package com.club.eliteclub.service;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.club.eliteclub.model.ClubDTO;
import com.club.eliteclub.dao.EliteClubRepository;
import com.club.eliteclub.entity.EliteClub;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class EliteClubServiceImpl implements EliteClubService {
    private static final Logger LOG = LoggerFactory.getLogger(EliteClubServiceImpl.class);

    private EliteClubRepository eliteClubRepository;

    @Autowired
    public void setEliteClubRepository(EliteClubRepository eliteClubRepository) {
        this.eliteClubRepository = eliteClubRepository;
    }

    public List<ClubDTO> getAll() {
        return eliteClubRepository.findAll().stream().map(c -> new ClubDTO(c.getClubName())).collect(Collectors.toList());
    }
    private String buildLikePattern(String searchTerm) {
        return searchTerm.toLowerCase() + "%";
    }
    @Override
    public List<ClubDTO> searchClub(String searchTerm) {
        LOG.info("Searching term {}", searchTerm);
        List<ClubDTO> result = eliteClubRepository.findClubs(
                buildLikePattern(searchTerm)).stream().map(c -> new ClubDTO(c.getClubName())).collect(Collectors.toList());
        LOG.info("Search Result: {} ", result);
        return result;
    }

    public void addClub(String... clubNames) {
        for (String clubName : clubNames) {
            EliteClub eliteClub = new EliteClub();
            eliteClub.setClubName(clubName);
            eliteClubRepository.save(eliteClub);
        }
    }
}
