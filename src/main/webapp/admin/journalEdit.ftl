<#import "../skins/common/util.ftl" as util>
<!DOCTYPE html>  
<html lang="zh_cn">
	 <head>
    	<#include "resource.ftl">
    	<script type="text/javascript">
    		window.UEDITOR_HOME_URL="<@util.url relativeUrl='/resource/ueditor/' />";    		    		
    		window.APP_HOME_URL="<@util.url relativeUrl='/' />";
    	</script>
    	
		<script type="text/javascript" src="<@util.url relativeUrl='/resource/ueditor_config.js' />"></script>
		<script type="text/javascript" src="<@util.url relativeUrl='/resource/ueditor/editor_all.js' />"></script>
    </head>
	<body class="need-margin-nav">
		<#include "menu.ftl">
		<div class="container">
			<form action="<@util.url relativeUrl='/admin/journal' />" method="post" class="form-horizontal">
				<input type="hidden" id="id" name="id" value="${journal.id!}" />
				<legend>写日志</legend>
				<div class="control-group">
              		<label class="control-label" >标题：</label>
              		<div class="controls">
                		<input type="text" id="title" name="title" placeholder="标题" value="${journal.title!}" />
              		</div>
            	</div>
            	<div class="control-group">
              		<label class="control-label" >路径：</label>
              		<div class="controls">
                		<input type="text" id="path" name="path" placeholder="路径" value="${journal.path!}" />
              		</div>
            	</div>
            	<div class="control-group">
              		<label class="control-label"  >分类：</label>
              		<div class="controls">
                		<select id="categoryId" name="categoryId">
							<#list categories as category>
							<option value="${category.id}">${category.name}</option>
							</#list>
						</select>
              		</div>
            	</div>
            	<div class="control-group">
              		<label class="control-label" >概要：</label>
              		<div class="controls">
                		<input type="text" id="summary" name="summary" placeholder="概要" value="${journal.summary!}" class="input-xxlarge" />
              		</div>
            	</div>
            	<div class="control-group">
              		<label class="control-label" >内容：</label>
              		<div class="controls">
                		<script type="text/plain" id="editor" name="content" />${journal.content!}</script>
              		</div>
            	</div>
            	<div class="control-group">
              		<label class="control-label" >标签：</label>
              		<div class="controls">
                		<input type="text" id="tagStr" name="tagStr" value="${tag!}" placeholder="标签"/>
              		</div>
            	</div>
            	<div class="form-actions">
				  	<button type="submit" class="btn btn-primary">提交</button>
				  	<a href="<@util.url relativeUrl='/admin/journal' />" class="btn">取消</a>
				</div>				
			</form>
			<script type="text/javascript">
				//实例化编辑器
    			var ue = UE.getEditor('editor');
			</script>
		</div>
	</body>
</html>