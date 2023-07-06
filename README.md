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
Result "execute":
  157.725 ±(99.9%) 116.771 ms/op [Average]
  (min, avg, max) = (133.705, 157.725, 210.583), stdev = 30.325
  CI (99.9%): [40.954, 274.496] (assumes normal distribution)


# Run complete. Total time: 00:01:19

Benchmark               Mode  Cnt    Score     Error   Units
NebulaClient3.execute  thrpt    5    0.006 ±   0.006  ops/ms
NebulaClient3.execute   avgt    5  157.725 ± 116.771   ms/op
```

nebula client 5.0
```agsl
Result "org.example.NebulaClient5.execute":
  53.990 ±(99.9%) 35.151 ms/op [Average]
  (min, avg, max) = (46.140, 53.990, 69.214), stdev = 9.129
  CI (99.9%): [18.839, 89.141] (assumes normal distribution)


# Run complete. Total time: 00:01:07

Benchmark              Mode  Cnt   Score    Error  Units
NebulaClient5.execute  avgt    5  53.990 ± 35.151  ms/op
```
更多测试方式和结果参考 https://github.dev/openjdk/jmh
