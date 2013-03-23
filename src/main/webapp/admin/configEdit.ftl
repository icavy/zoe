<#import "../skins/common/util.ftl" as util>
<!DOCTYPE html>  
<html lang="zh_cn">
	 <head>
    	<#include "resource.ftl">
    	<script type="text/javascript" src="<@util.url relativeUrl='/resource/jquery-validation/jquery.validate.min.js' />" charset="utf-8"></script>
    	<script>
			$(document).ready(function(){
				$("#configForm").validate();
			})
		</script>
    </head>
	<body>
		<div class="container">
			<form action="<@util.url relativeUrl='/admin/config' />" method="post" class="form-horizontal" id="configForm">
				<legend>新建</legend>
				<#if operationResult??>
					<div class="alert ${operationResult.messageStyle!}">
						<button type="button" class="close" data-dismiss="alert">×</button>
						${operationResult.message!}
					</div>
				</#if>
				<div class="control-group">
              		<label class="control-label" for="inputEmail">配置项：</label>
              		<div class="controls">
                		<input type="text" id="name" name="name" placeholder="配置项" value="${config.name!}" required/>
              		</div>
            	</div>
            	<div class="control-group">
              		<label class="control-label" for="inputEmail">KEY：</label>
              		<div class="controls">
                		<input type="text" id="key" name="key" placeholder="KEY" value="${config.key!}" required/>
              		</div>
            	</div>
            	<div class="control-group">
              		<label class="control-label" for="inputEmail">值域：</label>
              		<div class="controls">
                		<input type="text" id="value" name="value" placeholder="值域" value="${config.value!}" required/>
              		</div>
            	</div>
            	<div class="control-group">
              		<label class="control-label" for="inputEmail">分组：</label>
              		<div class="controls">
                		<input type="text" id="group" name="group" placeholder="分组" value="${config.group!}" required/>
              		</div>
            	</div>
            	<div class="control-group">
              		<label class="control-label" for="inputEmail">备注：</label>
              		<div class="controls">
                		<input type="text" id="remark" name="remark" placeholder="路径" value="${config.remark!}" />
              		</div>
            	</div>
            	<div class="form-actions">
				  	<button type="submit" class="btn btn-primary">提交</button>
				  	<button type="button" class="btn">取消</button>
				</div>				
			</form>
		</div>
	</body>
</html>