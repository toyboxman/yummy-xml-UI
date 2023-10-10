# Read Me First

$ cd poc/my-vue/src  
// 运行local的web server 调试  
$ npm run serve    
// 安装 依赖包  
$ npm install --save-dev nprogress   
// <package-folder>: A folder containing a package.json file in its root.   
// To create a production build, run npm run build.  
$ npm build [<package-folder>]  

// 与 spring boot工程集成
打包出来的运行包直接放入spring project的目录下 poc/src/main/resources/static， 启动boot访问即可

CORS(Cross origin) 错误会导致访问不同域URL失败 需要加上如下ACA header
```javascript
axios({
    method: 'post',
    headers: {
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Headers': 'Content-Type, Authorization',
        'Access-Control-Allow-Methods': 'GET, PUT, POST, DELETE, OPTIONS',
        'Content-Type': 'application/json',
    },
    url: 'http://localhost:8081/api/connect/vc',
    params: { admin: this.username, password: this.password },
})
```


The following was discovered as part of building this project:

* The JVM level was changed from '1.8' to '17', review the [JDK Version Range](https://github.com/spring-projects/spring-framework/wiki/Spring-Framework-Versions#jdk-version-range) on the wiki for more details.

# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.1.2/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.1.2/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.1.2/reference/htmlsinge/index.html#web)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/3.1.2/reference/htmlsinge/index.html#using.devtools)
* [Rest Repositories](https://docs.spring.io/spring-boot/docs/3.1.2/reference/htmlsinge/index.html#howto.data-access.exposing-spring-data-repositories-as-rest)
* [Spring Shell](https://spring.io/projects/spring-shell)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Accessing JPA Data with REST](https://spring.io/guides/gs/accessing-data-rest/)
* [Accessing Neo4j Data with REST](https://spring.io/guides/gs/accessing-neo4j-data-rest/)
* [Accessing MongoDB Data with REST](https://spring.io/guides/gs/accessing-mongodb-data-rest/)

