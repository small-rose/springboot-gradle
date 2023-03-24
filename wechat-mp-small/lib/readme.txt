aes-util-1.1-SNAPSHOT.jar

来自：https://github.com/small-rose/wechat-aes.git 打包。

fork from : https://github.com/crazy-pizza/wechat-aes

使用

maven

```
<dependency>
    <groupId>com.small.wechat</groupId>
    <artifactId>aes-util</artifactId>
    <version>1.1-SNAPSHOT</version>
</dependency>
```

gradle


```
 implementation 'com.small.wechat:aes-util:1.1-SNAPSHOT'
```

本地jar包

```
    // 依赖lib目录下的某个jar文件
    implementation files('libs/aes-util-1.1-SNAPSHOT.jar')


    // 依赖lib目录下的所有以.jar结尾的文件
    implementation fileTree(dir: 'libs', includes: ['*.jar'])


    // 依赖lib目录下的除了xxx.jar以外的所有以.jar结尾的文件
    implementation fileTree(dir: 'libs', excludes: ['xxx.jar'], includes: ['*.jar'])
```