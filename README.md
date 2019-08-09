## 码匠社区

##部署
###依赖
- Git
- JDK
- Maven
- MySQL
###步骤
- yum update(更新源)
- yum install git(安装Git)
- mkdir App(创建一个文件夹App用来存放项目)
- cd App(进入项目目录)
- git clone https://github.com/heyoupangren/community.git(使用git克隆项目)
- yum install maven(安装maven)
- mvn clean compile package(将项目打包)
- vim src/main/resources/application-production.properties(编辑复制的配置文件)
- mvn package(再次打包项目)
- docker start MySql(开启MySQL数据库连接)
- java -jar -Dspring.profiles.action=production target/community-0.0.1-SNAPSHOT.jar(运行项目的代码)
- ps -aux  |grep java(检测当前的进程是否存在)
##资料

[Spring 文档](https://spring.io/guides)

[Spring Web](https://spring.io/guides/gs/serving-web-content/)

[es社区](https://elasticsearch.cn/explore)

[Github deploy key](https://develloper.github.com/v3/guides/managing-deploy-keys/#deploy-keys)

[Bootstrap](https://v3.bootcss.com/getting-started/)

[Github OAuth](https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/)

[Spring MVC](https://docs.spring.io/spring/docs/5.0.3.RELEASE/spring-framework-reference/web.html#mvc-config-interceptors)

[MyBatis Generator](http://www.mybatis.org/generator/index.html)

[Markdown 插件](https://pandao.github.io/editor.md/)

##工具

[Git](https://git-scm.com/download)

[Visual Paradigm](https://www.visual-paradigm.com)

[Flyway](https://flywaydb.org/getstarted/firststeps/maven)

[Lombok](https://www.projectlombok.org/setup/maven)

##命令

git commit --amend --no-edit  //追加命令


git提交命令


1.git init

2.git status

3.git add .

4.git commit -m "描述"

5.git remote add origin https://github.com/heyoupangren/cwh.git(第一次时使用)

6.git push -u origin master



##
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
