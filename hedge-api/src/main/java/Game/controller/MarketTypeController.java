package Game.controller;

import Game.domain.MarketTypeService;
import Game.domain.Result;
import Game.model.MarketType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/markettype")
public class MarketTypeController {

    private final MarketTypeService service;

    public MarketTypeController(MarketTypeService service) {
        this.service = service;
    }

    @GetMapping("/{roll}/{companyId}")
    public ResponseEntity<MarketType> findById(@PathVariable int roll, int companyId) {
        Result<MarketType> marketType = service.findRoll(roll,companyId);
        if (marketType.getPayload() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(marketType.getPayload());
    }

}
