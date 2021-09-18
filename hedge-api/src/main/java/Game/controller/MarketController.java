package Game.controller;

import Game.domain.MarketService;
import Game.domain.Result;
import Game.model.Market;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/market")
public class MarketController {

    private final MarketService service;

    public MarketController(MarketService service) {
        this.service = service;
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<List<Market>> findByGameId(@PathVariable int gameId) {
        List<Market> market = service.findByGameId(gameId);
        if (market == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(market);
    }

    @GetMapping("/{gameId}/{yearNumber}")
    public ResponseEntity<List<Market>> findPortfolio(@PathVariable int gameId, int yearNumber) {
        List<Market> market = service.findPortfolio(gameId, yearNumber);
        if (market == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(market);
    }

    @GetMapping("/{companyId}/{gameId}")
    public ResponseEntity<List<Market>> findByCompanyId(@PathVariable int companyId, int gameId) {
        List<Market> market = service.findByCompanyId(companyId, gameId);
        if (market == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(market);
    }

    @PostMapping
    public ResponseEntity<Object> addMarket(@RequestBody Market market) {
        Result<Market> result = service.addMarket(market);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PostMapping("/bankrupt")
    public ResponseEntity<Object> setBankrupt(@RequestBody Market market) {
        Boolean result = service.setBankrupt(market);
        if (result) {
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{marketId}")
    public ResponseEntity<Void> deleteMarket(@PathVariable int marketId) {
        if (service.deleteMarket(marketId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
