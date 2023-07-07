package org.example;

import com.vesoft.nebula.client.graph.NebulaPoolConfig;
import com.vesoft.nebula.client.graph.data.HostAddress;
import com.vesoft.nebula.client.graph.data.ResultSet;
import com.vesoft.nebula.client.graph.exception.AuthFailedException;
import com.vesoft.nebula.client.graph.exception.ClientServerIncompatibleException;
import com.vesoft.nebula.client.graph.exception.IOErrorException;
import com.vesoft.nebula.client.graph.exception.NotValidConnectionException;
import com.vesoft.nebula.client.graph.net.NebulaPool;
import com.vesoft.nebula.client.graph.net.Session;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.Unsafe;

@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 3)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class NebulaClient3 {

    private static final Logger log = LoggerFactory.getLogger(NebulaClient3.class);
    static NebulaPool pool = new NebulaPool();

    static {
        // 初始化NebulaPool
        NebulaPoolConfig nebulaPoolConfig = new NebulaPoolConfig();
        nebulaPoolConfig.setMaxConnSize(10);
        nebulaPoolConfig.setMinConnSize(10);
        nebulaPoolConfig.setTimeout(3000);
        nebulaPoolConfig.setIdleTime(1000);
        nebulaPoolConfig.setIntervalIdle(1000);
        nebulaPoolConfig.setWaitTime(1000);
        nebulaPoolConfig.setMinClusterHealthRate(1);
        List<HostAddress> addresses = Arrays.asList(new HostAddress("192.168.15.2", 9669));
        boolean initResult = false;
        try {
            initResult = pool.init(addresses, nebulaPoolConfig);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        // 校验连接池初始化是否成功
        if (!initResult) {
            log.error("pool init failed.");
        }
    }

    public static void main(String[] args) throws UnknownHostException, RunnerException {
        Options options = new OptionsBuilder()
                .include(NebulaClient3.class.getSimpleName())
                .resultFormat(ResultFormatType.JSON)
                .build();
        new Runner(options).run();
    }

    @Benchmark
    @Threads(1)
    public void executeWithSingleThread() throws IOErrorException, AuthFailedException, ClientServerIncompatibleException,
            NotValidConnectionException {
        Session session = pool.getSession("root", "nebula", false);
        session.execute("return 1");
        session.release();
    }

    @Benchmark
    @Threads(10)
    public void executeWithMultipleThreads() throws IOErrorException, AuthFailedException, ClientServerIncompatibleException,
            NotValidConnectionException {
        Session session = pool.getSession("root", "nebula", false);
        session.execute("return 1");
        session.release();
    }
}
