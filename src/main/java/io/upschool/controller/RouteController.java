package io.upschool.controller;

import io.upschool.dto.BaseResponse;
import io.upschool.dto.RouteSaveRequest;
import io.upschool.dto.RouteSaveResponse;
import io.upschool.entity.Route;
import io.upschool.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routes")
@RequiredArgsConstructor
public class RouteController {
    private final RouteService routeService;

    @GetMapping
    public ResponseEntity<BaseResponse<List<Route>>> getRoute() {
        var routes = routeService.getAllRoute();
        var response = BaseResponse.<List<Route>>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(routes)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/routes/{id}")
    public ResponseEntity<BaseResponse<Route>> search(@PathVariable Long id) {
        var route = routeService.getRouteById(id);
        var response = BaseResponse.<Route>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(route)
                .build();
        return ResponseEntity.ok(response);
    }


    @PostMapping
    public ResponseEntity<Object> createRoute(@RequestBody RouteSaveRequest request){
       var routeSaveResponse  = routeService.save(request);
        var response =  BaseResponse.<RouteSaveResponse>builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(routeSaveResponse)
                .build();
        return ResponseEntity.ok(response);
    }
}
