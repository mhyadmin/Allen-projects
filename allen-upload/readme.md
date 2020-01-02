# 上传下载的文档

##图片显示的三大方案实现

### 基于springboot配置类，改变原来的默认配置
  此处不在赘述上传过程，简单描述它的上传思路：

*  上传的文件存放在某盘某目录下，如 E:/视频制作/图片/
*  通过配置文件类 WebMvcConfig 将本地存放文件目录映射到项目路径下，具体查看配置类
*  项目启动，加载配置类。

访问方式：
  *  原来：file:///E:/%E8%A7%86%E9%A2%91%E5%88%B6%E4%BD%9C/%E5%9B%BE%E7%89%87/1442823076292.jpeg
  *  现在：http://localhost:9001/image/1442823076292.jpeg
  
对比解析：配置文件类加载后，将` E:/视频制作/图片/` 映射到 `http://localhost:9001/image/`，访问文件的时候，直接通过项目访问，
如：http://localhost:9001/image/1442823076292.jpeg
    

### 基于绝对路径访问
 简单思路:

* 基于springboot的默认静态文件配置，注：若配置类有配置其他文件路径，会让springboot默认静态配置文件失效。需要自己针对项目路径重新配置，如WebMvcConfig
* 获取项目下resources的某路径，此处选择resources/static/ 。获取方法 ResourceUtils.getFile("classpath:static")
* 根据获得的路径，创建新的路径，并上传文件。详情查看AbsolutePathController的方法uploadToProject

对比解析：上传的文件到项目的resources目录下，实际加载得到的文件是绝对路径下的文件如image/1442823076292.jpeg，主要的原理还是文件映射。访问的时候，直接http://localhost:9001/images/1442823076292.jpeg 

### 基于Nginx配置的访问
 简单思路:
 * 上传文件到某路径下，如C:/文件
 * 使用Nginx服务器，配置访问路径并映射到文件上传的路径下
 
 以下是Nginx的配置
 * 基于alias配置 
 
           location /static/ {
                   alias  /var/www/static/;
               }
 
  alas会把指定路径当作文件路径           
 注意：alias指定的目录后面必须要加上"/"，即/var/www/static/不能改成/var/www/static
 访问http://IP:PORT/static/index.html时，实际访问的是/var/www/static/index.html 
 这种实现，类似 **基于springboot配置类**     
 
 * 基于配置root
 
          location /static/ {
               root  /var/www/;
           }
          
   root会把指定路径拼接到文件路径后，再进行访问        
   注意：location中指定的/static/必须是在root指定的/var/www/目录中真实存在的。
   通过浏览器访问http://127.0.0.1:7001/static/t.txt，则访问服务器的文件是/var/www/static/t.txt
          
               
              