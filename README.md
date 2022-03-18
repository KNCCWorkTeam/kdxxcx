# kdxxcx
lingbaojun制作的一个项目 供参考

使用程序需要在Microsoft SQL Server环境下运行。  
且需配置info.properties文件，三个都是必填的。  
程序在菜单栏可以打开调试模式 SQLServerException报错会显示在屏幕上。  

编辑程序请在com.kdxxcx中删改。  
删改完成可以使用JavaToJar.bat快速打包。  
如果在删改时添加了新的文件夹请编辑JavaToJar.bat中的命令，实例如下：  
在com.kdxxcx.util中新建了一个名为test的文件夹 则需在JavaToJar中添加：  
javac com\kdxxcx\util\test\*.java -encoding UTF-8  
同时需要在最后两行命令的最后添加：  
 com\kdxxcx\util\test\*.class  
注意不要遗漏空格，建议复制后更改。  
为了美观，可以按照顺序加在命令之间，但javac命令一定要在jar命令之前  

CTCODES.lingbaojun用于存放源代码 将文件改名为CTCODES.jar即可解压获取源代码  

.area文件用于存放地址信息，以便用户快捷输入，可在程序菜单栏打开编辑器调试，格式如下：  
省名{市名{区或县名{路名$路名}#区或县名{路名$路名}},市名{区或县名{路名$路名}#区或县名{路名$路名}}}  
省名{...}  
...  
直辖{...}  
注意格式，直辖指的是其中存放的市名是直辖市，在编辑时注意不要写成直辖市，其余所有名称都可以写成..市或..，如上海或上海市都是正确的。  

2.0.2新增：命令行功能
命令行功能需要输入密码，请在程序中自行查看或修改
密码存放于com.kdxxcx.programme.ConstantAndVar中
