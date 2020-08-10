package com.aditya.service;

import com.aditya.constant.ShortnerConstants;
import com.aditya.utilities.ShortnerUtility;
import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UrlService {
    @Autowired
    private AerospikeClient aerospikeClient;

    @Autowired
    private ShortnerUtility utility;

    public String shortenUrl(String url) {
        int uniqueNumber = getUniqueNumber();
        String shortUrl = utility.getUniqueShortUrl(uniqueNumber);
        System.out.println("short url --- " + shortUrl);
        Key key = new Key("test", ShortnerConstants.setName, shortUrl);
        Bin bin1 = new Bin("long-url", url);
        Bin bin2 = new Bin("short-url", createNewShortUrl(shortUrl));
        aerospikeClient.put(null, key, bin1, bin2);
        return shortUrl;
    }

    private int getUniqueNumber() {
        Random random = new Random();
        return Math.abs(random.nextInt());
    }

    private String createNewShortUrl(String hash) {
        return "http://short.ly/" + hash;
    }

    public String getLongUrl(String hash) {
        Record record = aerospikeClient.get(null, new Key("test", ShortnerConstants.setName, hash));
        String longUrl = record.bins.get("long-url").toString();
        System.out.println("long url-- " + longUrl);
        return longUrl;
    }
}
