package com.demo.rest.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.demo.rest.pojo.MicroServiceConf;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Slf4j
@AllArgsConstructor
public class MicroServicesConfFetcher {

    private Gson gson;
    private OkHttpClient httpClient;
    private String name;
    private String url;

    public CompletableFuture<Optional<MicroServiceConf>> fetchMicroServiceConf() {

        CompletableFuture<Optional<MicroServiceConf>> completableFuture = new CompletableFuture<>();
        Request request = new Request.Builder()
                .url(url)
                .build();

        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                log.error("Failed calling micro service conf {} in url {}, error {}", name, url, e);
                completableFuture.complete(Optional.empty());
            }

            @Override
            public void onResponse(Call call, Response response) {

                if (!response.isSuccessful()) {
                    completableFuture.complete(Optional.empty());
                } else {
                    if (response.code() >= 200 && response.code() <= 202) {
                        try {

                            String responseBody = response.body().string();
                            response.close();

                            TypeToken mapType = new TypeToken<Map<String, String>>(){};
                            Map<String,String> configurations = gson.fromJson(responseBody, mapType.getType());
                            log.debug("Finished getting microService conf: {} ", name);

                            completableFuture.complete(Optional.of(new MicroServiceConf(name, configurations)));

                        } catch (Exception e) {
                            log.error("Response failed, micro service conf {} in url {}, error {}", name, url, e.getMessage());
                            completableFuture.complete(Optional.empty());
                        }
                    }
                }
            }
        });

        return completableFuture;
    }

}