package com.excilys.cdb.webapp.rest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.cdb.binding.dto.ComputerDTO;
import com.excilys.cdb.binding.mapper.ComputerMapper;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Pager;
import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.service.ServiceException;

@RestController
@RequestMapping("/api")
public class RestComputer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestComputer.class);

    @Autowired
    private ComputerService computerService;

    @GetMapping(value = "/computer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ComputerDTO> getComputer(@PathVariable("id") long id) {

        Optional<Computer> computer = computerService.get(id);
        if (computer.isPresent()) {
            return new ResponseEntity<>(ComputerMapper.mapperToDTO(computer.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ComputerDTO(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/computer", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pager<ComputerDTO>> getAllComputers() {

        Pager<Computer> page = new Pager<>(Integer.MAX_VALUE);
        computerService.find(page);
        Pager<ComputerDTO> pageDTO = new Pager<>(Integer.MAX_VALUE);
        pageDTO.setListEntity(ComputerMapper.mapperToDTO(page.getListEntity()));

        return new ResponseEntity<>(pageDTO, HttpStatus.OK);

    }

    @DeleteMapping(value = "/computer/{id}")
    public ResponseEntity<String> deleteComputer(@PathVariable Long id) {

        LOGGER.debug("rest delete computer id {}", id);
        try {
            List<Long> list = Arrays.asList(id);
            computerService.remove(list);
            return new ResponseEntity<String>(id + "", HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(value = "/computer", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ComputerDTO> createComputer(@Valid @RequestBody ComputerDTO computerDTO,
            BindingResult result) {
        if (result.hasErrors()) {
            System.out.println(result.getAllErrors().get(0));
            return new ResponseEntity<>(new ComputerDTO(), HttpStatus.NOT_FOUND);
        } else {
            computerService.add(ComputerMapper.mapperToModel(computerDTO));
            return new ResponseEntity<>(computerDTO, HttpStatus.OK);
        }
    }
}
