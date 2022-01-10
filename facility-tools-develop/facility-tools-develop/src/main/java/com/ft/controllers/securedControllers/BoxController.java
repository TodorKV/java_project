package com.ft.controllers.securedControllers;

import com.ft.dto.BoxDto;
import com.ft.dto.HallDto;
import com.ft.service.base.BoxService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/box")
public class BoxController {

    private final BoxService service;

    public BoxController(BoxService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<BoxDto>> getAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/getAllPaginated")
    public ResponseEntity<Page<BoxDto>> getAllBoxesPaginated(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy)
    {
        Page<BoxDto> list = service.getAllBoxesPaginated(pageNo, pageSize, sortBy);

        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BoxDto> save(@RequestBody BoxDto dto) {
        return new ResponseEntity<>(service.save(dto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        service.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoxDto> edit(@PathVariable("id") Long id, @RequestBody BoxDto dto) {
        return new ResponseEntity<>(service.edit(id, dto), HttpStatus.OK);
    }
}
