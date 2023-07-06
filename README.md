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

