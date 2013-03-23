<#macro url relativeUrl>${springMacroRequestContext.getContextUrl(relativeUrl)}</#macro>

<#macro message code>${springMacroRequestContext.getMessage(code)}</#macro>

<#macro doPagination dataPage relativeUrl>
	<#assign actionUrl = springMacroRequestContext.getContextUrl(relativeUrl) />
	<div class="pagination">
		<form action="${actionUrl}" method="post">
			<input type="hidden" name="pageIndex" value="1" />
			<input type="hidden" name="pagination" value="1" /> 		
			<ul>
				<#if dataPage.hasPretPage() >    
			    	<li class="prev"><a href="javascript:void(0)" onclick="doPagination(event,${dataPage.pageIndex - 1})">上一页</a></li>
			    <#else>
			    	 <li class="prev disabled"><a>上一页</a></li>	
				</#if>
				<#list dataPage.indexes as index>
					<#if index == dataPage.pageIndex>
						<li class="active"><a>${index}</a></li>
					<#else>
						<li><a href="javascript:void(0)" onclick="doPagination(event,${index})">${index}</a></li>
					</#if>
				</#list>
				<#if dataPage.hasNextPage() >    
			    	<li class="next"><a href="javascript:void(0)" onclick="doPagination(event,${dataPage.pageIndex + 1})">下一页</a></li>
			    <#else>
			    	<li class="next disabled"><a>下一页</a></li>
				</#if>
			</ul>
		</form>
	</div>
	<script>
		function doPagination(event,pageIndex){
			var e = window.event || event;
			var aNode = e.srcElement || e.target;
			if (aNode){
				var formNode = $(aNode).parents('form');
				formNode.find('input[name="pageIndex"]').val(pageIndex);
				formNode.submit();
			}
		}
	</script>
</#macro>