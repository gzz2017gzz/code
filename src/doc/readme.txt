git的author和commiter的修改 
eclipse的git插件使用,其他开发软件也类似
进入你的项目目录,windows设置一下，显示隐藏的文件，就可以看到有个.git的文件夹，进去，就会看到有一个config文件,编辑,加上
[user]
#name = $USER
#设置用户的邮箱
#email = $EMAIL,
如果是eclipse，修改后得关闭eclipse，再次打开后才会生效

keytool -genkey -alias tomcat  -storetype PKCS12 -keyalg RSA -keysize 2048  -keystore keystore.p12 -validity 3650