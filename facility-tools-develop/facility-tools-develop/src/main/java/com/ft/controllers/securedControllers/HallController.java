package com.ft.controllers.securedControllers;

import com.ft.dto.HallDto;
import com.ft.dto.ItemDto;
import com.ft.service.base.HallService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/hall")
public class HallController {

    private final HallService service;

    public HallController(HallService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<HallDto>> getAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/getAllPaginated")
    public ResponseEntity<Page<HallDto>> getAllHallsPaginated(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy)
    {
        Page<HallDto> list = service.getAllHallsPaginated(pageNo, pageSize, sortBy);

        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("search/getAllPaginated")
    public ResponseEntity<Page<HallDto>> searchByNamePaginated(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "") String name)
    {
        Page<HallDto> list = service.searchByNamePaginated(name, pageNo, pageSize, sortBy);
        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HallDto> save(@RequestBody HallDto dto) {
        return new ResponseEntity<>(service.save(dto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        service.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HallDto> edit(@PathVariable("id") Long id, @RequestBody HallDto dto) {
        return new ResponseEntity<>(service.edit(id, dto), HttpStatus.OK);
    }



}
