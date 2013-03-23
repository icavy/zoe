<#import "../skins/common/util.ftl" as util>
<!DOCTYPE html>  
<html lang="zh_cn">
	 <head>
    	<#include "resource.ftl">
    </head>
	<body>
		<#include "menu.ftl">
		<div class="container">
			<ul>
				<li><a href="<@util.url relativeUrl='/admin/cache/clearConfigCache' />">清除系统配置缓存</a></li>
			</ul>
		</div>
	</body>
</html>