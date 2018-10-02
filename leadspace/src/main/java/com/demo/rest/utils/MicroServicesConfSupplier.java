package com.demo.rest.utils;

import com.google.gson.Gson;
import com.demo.rest.pojo.MicroServiceConf;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@NoArgsConstructor
public class MicroServicesConfSupplier {

    private static final int TIMEOUT = 20;

    public List<MicroServiceConf> getMicroServicesConf(OkHttpClient httpClient, Map<String, String> microServices) {

        return microServices.entrySet().stream()
                .map(map -> getMicroServiceConf(httpClient, map.getKey(),map.getValue())).collect(Collectors.toList());

    }

    private MicroServiceConf getMicroServiceConf(OkHttpClient httpClient, String name, String url) {

        MicroServicesConfFetcher microServicesConfFetcher = new MicroServicesConfFetcher(new Gson(), httpClient, name, url);

        try {
            Optional<MicroServiceConf> completableFuture =
                    microServicesConfFetcher.fetchMicroServiceConf().get(TIMEOUT, TimeUnit.SECONDS);
            return completableFuture.orElse(new MicroServiceConf(name, new HashMap<>()));

        } catch (Exception e) {
            log.error("Error Micro Service Conf {} for url {}, error {} ",name , url, e);
            return (new MicroServiceConf(name, new HashMap<>()));
            
        } finally {
        shutdownHttpClient(httpClient);
        log.debug("Task [{}] finished", "task");
    }

    }
    private void shutdownHttpClient(OkHttpClient httpClient) {
        httpClient.dispatcher().executorService().shutdownNow();
        httpClient.connectionPool().evictAll();
    }


}
