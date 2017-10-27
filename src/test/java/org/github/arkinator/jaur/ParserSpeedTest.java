package org.github.arkinator.jaur;

import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.BenchmarkRule;
import com.carrotsearch.junitbenchmarks.Clock;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jsoniter.JsonIterator;
import org.apache.commons.io.FileUtils;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

@Ignore("Just a benchmark")
@BenchmarkOptions(benchmarkRounds = 100, warmupRounds = 10, callgc = false, clock = Clock.REAL_TIME, concurrency = 1)
public class ParserSpeedTest {
    private JSONParser parser = new JSONParser();
    private Gson gson = new GsonBuilder().create();

    @Rule
    public BenchmarkRule benchmarkRun = new BenchmarkRule();
    private String testString1 = FileUtils.readFileToString(new java.io.File("src/test/resources/json/eilmeldung-100.json"),"UTF-8");

    public ParserSpeedTest() throws IOException {
    }

    @Test
    public void test_jsoniter() {
        JsonIterator.deserialize(testString1);
    }

    @Test
    public void test_jsonSimple() throws ParseException {
        parser.parse(testString1);
    }
}
