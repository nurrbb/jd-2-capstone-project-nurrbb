package io.upschool.controller;


import io.upschool.dto.RouteSaveRequest;
import io.upschool.dto.RouteSaveResponse;
import io.upschool.entity.Route;
import io.upschool.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/route")
@RequiredArgsConstructor
public class RouteController {
    private final RouteService routeService;

    @GetMapping
    public ResponseEntity<List<Route>> getRoute(){
        var route = routeService.getAllRoute();
        return ResponseEntity.ok(route);
    }

    @PostMapping
    public ResponseEntity<RouteSaveResponse> createRoute(@RequestBody RouteSaveRequest request){
        RouteSaveResponse savedRoute = routeService.save(request);
        return ResponseEntity.ok(savedRoute);
    }
}
