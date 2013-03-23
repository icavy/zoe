<#import "../skins/common/util.ftl" as util>
<!DOCTYPE html>  
<html lang="zh_cn">
	 <head>
    	<#include "resource.ftl">
    	<script type="text/javascript" src="<@util.url relativeUrl='/resource/fancyBox/jquery.fancybox.js' />"></script>
		<link rel="stylesheet" type="text/css" href="<@util.url relativeUrl='/resource/fancyBox/jquery.fancybox.css?v=2.0.6' />" media="screen" />
		<script>
			$(document).ready(function(){
				$('#newBtn').click(function() {
					$.fancybox.open({
						href : '<@util.url relativeUrl="/admin/config/new" />',						
						type : 'iframe',					
						afterClose : function(){
							location.reload();
						},
						width: 600,
						scrolling: 'no'
					});
				});
			});
		</script>		
    </head>
	<body class="need-margin-nav">
		<#include "menu.ftl">
		<div class="container">
			<div class="row">
				<div class="span3">
					<div class="section section-small sidebar-nav">
						<div class="section-header">
							<h5>配置分组</h5>
						</div>
						<div class="section-body">
							<ul class="nav nav-list">
								<#list groupSet as group>
									<li><a href="<@util.url relativeUrl='/admin/config/${group}' />">${group}</a></li>
								</#list>
							</ul>
						</div>
					</div>
				</div>
				<div class="span9">
					<div class="margin-bottom">
						<button id="newBtn" type="button" class="btn pull-right">新增</button>
					</div>
					<table class="table table-bordered table-striped table-hover">
						<thead>
		                	<tr>
		                  		<th>配置项</th>
		                  		<th>KEY</th>
		                  		<th>值域</th>
		                  		<th>分组</th>
		                  		<th>描述</th>
		                  		<th>操作</th>
		                	</tr>
		                 </thead>
		            	<tbody>
			            	<#list configSet as config>
		            		<tr>
		                  		<td>${config.name}</td>
		                  		<td>${config.key}</td>
		                  		<td>${config.value}</td>
		                  		<td>${config.group}</td>
		                  		<td>${config.remark}</td>
		                  		<td>
	                  				<a class="btn btn-info"><i class="icon-edit icon-white"></i>修改</a>
	                  				<a class="btn btn-danger"><i class="icon-trash icon-white"></i>删除</a>
		                  		</td>
		                	</tr>
							</#list>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</body>
</html>