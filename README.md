# 事件管理系统(Incident Management System)

## 作用
一个简单的事件管理系统，用于管理和追踪各类事件。支持事件的创建、查看、更新和删除操作，并提供优先级和状态管理功能。

## 快速入门

### 如何运行
该项目是基于Spring Boot的全栈应用，包含后端API和React前端界面。基础依赖要求:
1. Java 17 [下载地址](https://www.oracle.com/java/technologies/downloads/#java17)
2. Maven 3.5+ [下载地址](https://maven.apache.org/download.cgi)
3. Node.js 14+ [下载地址](https://nodejs.org/)

运行步骤:
1. 克隆项目到本地
2. 启动后端:
```bash
# 在项目根目录下
mvn spring-boot:run
```
3. 启动前端:
```bash
# 进入frontend目录
cd frontend
npm install
npm start
```

### 构建部署
1. 后端构建:
```bash
mvn clean package
```

2. 前端构建:
```bash
cd frontend
npm run build
npm install -g serve
serve -s build
```

### 配置说明
在`application.properties`中配置数据库连接:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/incident_management
spring.datasource.username=root
spring.datasource.password=123456
```

### Docker部署
使用Docker Compose进行部署:
```bash
docker-compose up --build
```

## 主要功能
- 事件的创建、查看、更新和删除
- 事件优先级管理(低、中、高、紧急)
- 事件状态跟踪(待处理、处理中、已解决、已关闭)
- 分页查询支持
- RESTful API接口
- 响应式Web界面

## 技术栈
### 后端
- Spring Boot 3.x
- Spring Data JPA
- MySQL
- Swagger/OpenAPI

### 前端
- React
- Material-UI
- Axios
- React Router

## API文档
启动应用后访问: http://localhost:8080/swagger-ui.html

## 后期扩展方向
- 添加用户认证和授权
- 实现事件评论功能
- 添加文件附件支持
- 增加事件统计和报表功能
- 添加邮件通知功能
- 支持多语言