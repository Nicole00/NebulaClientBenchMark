package org.example;

import com.vesoft.nebula.client.graph.exception.IOErrorException;
import com.vesoft.nebula.client.graph.exception.NoValidSessionException;
import com.vesoft.nebula.client.graph.net.NebulaClient;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Hierarchy;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 3)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Threads(1)
@Fork(1)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class NebulaClient5 {

    private static final Logger log = LoggerFactory.getLogger(NebulaClient5.class);
    static NebulaClient client = null;
    static {
        try {
           client = NebulaClient
                    .builder("192.168.8.6:3713", "root", "nebula")
                    .setMaxSessionSize(10)
                    .setMinSessionSize(10)
                    .setReconnect(true)
                    .setRetryTimes(3)
                    .setIntervalTimeMills(1000)
                    .setConnectTimeoutMills(3000)
                    .setRequestTimeoutMills(3000)
                    .build();
        } catch (Exception e) {
            log.error("client build error ", e);
        }
    }

    public static void main(String[] args) throws RunnerException {

        Options options = new OptionsBuilder()
                .include(NebulaClient5.class.getSimpleName())
                .resultFormat(ResultFormatType.JSON)
                .build();
        new Runner(options).run();
    }


    @Threads(1)
    @Benchmark
    public static void executeWithSingleThread() throws IOErrorException, NoValidSessionException {
       client.execute("return 1");
    }

    @Threads(10)
    @Benchmark
    public static void executeWithMultipleThreads() throws IOErrorException, NoValidSessionException {
        client.execute("return 1");
    }
}
