package org.demo.football.services;

import lombok.extern.slf4j.Slf4j;
import org.demo.football.exceptions.AlreadyExistsException;
import org.demo.football.exceptions.NotFoundException;
import org.demo.football.model.Player;
import org.demo.football.model.PlayerRanking;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
//@RequiredArgsConstructor
public class FootballService {

    private final RestTemplate restTemplate;

    public FootballService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    // Map.of(), Map.ofEntries() create immutable maps
    private final Map<String, Player> initialPlayers = Map.ofEntries(
            Map.entry("p1", new Player("p1", 5, "Alice", "Defender", LocalDate.of(1990, 1, 1))),
            Map.entry("p2", new Player("p2", 31, "Bob", "Midfielder", LocalDate.of(1994, 2, 4)))
    );
    // Convert immutable map to mutable map
    private final Map<String, Player> players = new HashMap<>(initialPlayers);

    public List<Player> listPlayers() {
        return players.values().stream().toList();
    }

    public Player getPlayer(String id) {
        Player player = players.get(id);
        if (player == null) {
            throw new NotFoundException("Player not found");
        }
        log.info("Player: {}", player);
        return player;
    }

    public Player addPlayer(Player player) {
        if (players.containsKey(player.id())) {
            throw new AlreadyExistsException("Player already exists");
        } else {
            players.put(player.id(), player);
            return player;
        }
    }

    public Player updatePlayer(String id, Player player) {
        if (players.containsKey(player.id()) && id.equals(player.id())) {
            players.put(player.id(), player);
            return player;
        } else {
            throw new NotFoundException("Player not found");
        }
    }

    public void deletePlayer(String id) {
        if (players.containsKey(id)) {
            players.remove(id);
        } else {
            throw new NotFoundException("Player not found");
        }
    }

    public List<PlayerRanking> listPlayerRankings() {
        String url = "http://localhost:8000/football/ranking";
        return players.values().stream()
                .map(player -> {
                    int ranking = restTemplate.getForObject(url + "/" + player.number(), int.class);
                    return new PlayerRanking(player, ranking);
                }).toList();
    }
}
