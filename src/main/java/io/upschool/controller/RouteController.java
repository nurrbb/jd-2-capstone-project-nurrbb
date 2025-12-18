package io.upschool.controller;

import io.upschool.dto.BaseResponse;
import io.upschool.dto.RouteProjection;
import io.upschool.dto.RouteSaveRequest;
import io.upschool.dto.RouteSaveResponse;
import io.upschool.entity.Route;
import io.upschool.service.RouteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routes")
@RequiredArgsConstructor
public class RouteController {
    private final RouteService routeService;

    /**
     * Get all routes without pagination (legacy endpoint for backward compatibility)
     */
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

    /**
     * Search for existing routes with pagination and sorting support
     * Query parameters:
     *   - page: page number (default: 0)
     *   - size: page size (default: 10)
     *   - sort: sort field and direction (e.g., "routeID,asc" or "routeID,desc")
     *   - originAirportId: filter by origin airport ID (optional)
     *   - destinationAirportId: filter by destination airport ID (optional)
     *   - originAirportName: filter by origin airport name (optional)
     *   - destinationAirportName: filter by destination airport name (optional)
     * 
     * Example: /api/routes/search?page=0&size=20&sort=routeID,asc&originAirportId=1
     */
    @GetMapping("/search")
    public ResponseEntity<BaseResponse<Page<RouteProjection>>> searchRoutes(
            @RequestParam(required = false) Long originAirportId,
            @RequestParam(required = false) Long destinationAirportId,
            @RequestParam(required = false) String originAirportName,
            @RequestParam(required = false) String destinationAirportName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "routeID,asc") String sort) {
        
        String[] sortParams = sort.split(",");
        Sort.Direction direction = sortParams.length > 1 && sortParams[1].equalsIgnoreCase("desc") 
                ? Sort.Direction.DESC 
                : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortParams[0]));
        
        var routes = routeService.searchRoutes(
                originAirportId, destinationAirportId, originAirportName, destinationAirportName, pageable);
        var response = BaseResponse.<Page<RouteProjection>>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(routes)
                .build();
        return ResponseEntity.ok(response);
    }

    /**
     * Get all routes with pagination and sorting support
     * Query parameters:
     *   - page: page number (default: 0)
     *   - size: page size (default: 10)
     *   - sort: sort field and direction (e.g., "routeID,asc" or "routeID,desc")
     */
    @GetMapping("/paginated")
    public ResponseEntity<BaseResponse<Page<RouteProjection>>> getAllRoutesPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "routeID,asc") String sort) {
        
        String[] sortParams = sort.split(",");
        Sort.Direction direction = sortParams.length > 1 && sortParams[1].equalsIgnoreCase("desc") 
                ? Sort.Direction.DESC 
                : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortParams[0]));
        
        var routes = routeService.getAllRoutes(pageable);
        var response = BaseResponse.<Page<RouteProjection>>builder()
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
    public ResponseEntity<Object> createRoute(@Valid @RequestBody RouteSaveRequest request){
       var routeSaveResponse  = routeService.save(request);
        var response =  BaseResponse.<RouteSaveResponse>builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(routeSaveResponse)
                .build();
        return ResponseEntity.ok(response);
    }
}
