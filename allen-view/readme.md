# 图片上传文档

##图片显示简略方案

### 基于yml 或者properties配置文件

*  上传的文件存放在某盘某目录下，如  E:/Practices/test


具体配置:
web:
  upload-path: E:/Practices/test
  front-path:  E:/Practices
spring:
  resources:
    static-locations: file:${web.upload-path},file:${web.front-path}

访问方式：IP+端口/文件名
  *  现在：http://localhost:8003/1442823076292.jpeg
          
              