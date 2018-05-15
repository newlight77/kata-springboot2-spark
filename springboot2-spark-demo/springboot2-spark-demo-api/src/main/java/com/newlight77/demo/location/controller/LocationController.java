package com.newlight77.demo.location.controller;

import com.newlight77.demo.location.service.LocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/locations")
public class LocationController {

  private final Logger LOGGER = LoggerFactory.getLogger(LocationController.class);

  @Autowired
  private LocationService locationService;

  @PostMapping(value = "/upload")
  public long uploadServicePlaces(@RequestParam("file") MultipartFile file)
      throws IOException {

    LOGGER.info("start upload");

    File tempFile = new File("/tmp/" + file.getOriginalFilename());
    file.transferTo(tempFile);

    return locationService.importFromFile(tempFile);
  }

}
