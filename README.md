# NebulaClientBenchMark
Performance comparison test of 3.x and 5.x clients based on JMH

# 使用方式

1. 安装 nebula client 5.0 依赖
```agsl
sh prepare.sh
```

2. 修改nebula-client5/pom.xml 文件中 <local.maven.repo> 的属性值，改为本地 maven 仓库地址
   本地maven地址默认为~/.m2/repository


3. 分别对nebula-client3 和 nebula-client5 进行打包
```agsl
sh build.sh
```

4. 分别执行性能基准测试
```agsl
sh run.sh
```


## 结果示例
nebula client 3.x

```agsl
Result "org.example.NebulaClient3.executeWithMultipleThreads":
  111.883 ±(99.9%) 21.795 ms/op [Average]
  (min, avg, max) = (105.245, 111.883, 117.627), stdev = 5.660
  CI (99.9%): [90.088, 133.678] (assumes normal distribution)
  
Result "org.example.NebulaClient3.executeWithSingleThread":
  93.518 ±(99.9%) 8.370 ms/op [Average]
  (min, avg, max) = (91.368, 93.518, 96.782), stdev = 2.174
  CI (99.9%): [85.148, 101.889] (assumes normal distribution)


# Run complete. Total time: 00:02:15

Benchmark                                 Mode  Cnt    Score    Error  Units
NebulaClient3.executeWithMultipleThreads  avgt    5  111.883 ± 21.795  ms/op
NebulaClient3.executeWithSingleThread     avgt    5   93.518 ±  8.370  ms/op
```

nebula client 5.0
```agsl
Result "org.example.NebulaClient5.executeWithMultipleThreads":
  23.431 ±(99.9%) 23.522 ms/op [Average]
  (min, avg, max) = (20.259, 23.431, 34.341), stdev = 6.109
  CI (99.9%): [≈ 0, 46.953] (assumes normal distribution)

Result "org.example.NebulaClient5.executeWithSingleThread":
  20.242 ±(99.9%) 1.390 ms/op [Average]
  (min, avg, max) = (19.916, 20.242, 20.841), stdev = 0.361
  CI (99.9%): [18.852, 21.632] (assumes normal distribution)


# Run complete. Total time: 00:02:13

Benchmark                                 Mode  Cnt   Score    Error  Units
NebulaClient5.executeWithMultipleThreads  avgt    5  23.431 ± 23.522  ms/op
NebulaClient5.executeWithSingleThread     avgt    5  20.242 ±  1.390  ms/op
```
更多测试方式和结果参考 https://github.dev/openjdk/jmh
