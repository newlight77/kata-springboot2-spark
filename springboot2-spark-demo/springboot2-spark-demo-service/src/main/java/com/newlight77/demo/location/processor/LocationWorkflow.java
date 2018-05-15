package com.newlight77.demo.location.processor;

import com.newlight77.demo.location.model.Location;
import com.newlight77.demo.location.entity.LocationEntity;
import com.newlight77.demo.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class LocationWorkflow {

    private final Logger LOGGER = LoggerFactory.getLogger(LocationWorkflow.class);

    public void reactor(Flux<LocationEntity> flux) {
        long start = System.currentTimeMillis();

        flux
            .parallel()
            .runOn(Schedulers.parallel())
            //            .sequential()
            .doOnNext(entity -> {
                doImport(start, entity);
            })
            .doOnComplete(() -> onComplete(start))
            .subscribe();
        //            .blockLast();

    }

    public void doImport(long start, LocationEntity entity) {
        LOGGER.info("entity oid={}", entity.getOid());

        Location location = JsonUtils.fromJson(entity.getData(), Location.class);

        LOGGER.info("location={}", location);

        onComplete(start);
    }

    public void onComplete(long start) {
        LOGGER.info("processed in {} ms",
            System.currentTimeMillis() - start);

    }
}
