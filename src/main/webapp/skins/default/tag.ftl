<!DOCTYPE html>   
<html lang="zh_cn">
	 <head>
	 	<title>${tagName} | ${configComponent.getConfig('base.titile')}</title>
    	<#include "resource.ftl">
    	<!-- SEO start -->
		<link rel="canonical" href="http://${configComponent.getConfig('base.url')}/tag/${tagName}" />
		<!-- SEO end -->
    </head>
	<body>
		<div class="header">
			<div class="container">
				<#include "header.ftl">
			</div>
		</div>
		<div class="main">
		    <div class="row container">
		    	<div class="ninecol">
		    		<#list journals.dataList as journal>
						<div class="journal">
			    			<h2 class="journal-title"><a href="<@util.url relativeUrl='/post/${journal.path}' />">${journal.title}</a></h2>
			    			<div class="journal-meta">
			    				<span class="journal-date">${journal.createDate?string('yyyy年M月d日 a h:m')}</span>
			    				<span class="journal-meta-sep"> ⋅ </span>
			    				<span class="journal-comments"><span class="ds-thread-count" data-thread-key="${journal.id}"></span>条评论</span>
			    				<span class="journal-meta-sep"> ⋅ </span>
			    				<span class="journal-auto">Cavy</span>
			    			</div>
							<div class="journal-content">
								${journal.summary}
							</div>
							<div class="journal-meta-bottom">
								<span class="journal-cat">
									<span>Posted in:</span>
									<#if journal.category?exists><a href="<@util.url relativeUrl='/cat/${journal.category.path}' />" title="查看 ${journal.category.name} 中的全部文章" rel="category">${journal.category.name}</a></#if> 
								</span>
								<span class="journal-meta-sep"> ⋅ </span>
								<span class="journal-tag">
									<span>Tagged:</span> 
									<#list journal.tags as tag>
									<a href="<@util.url relativeUrl='/tags/${tag.name}' />" rel="tag">${tag.name}</a>
									<#if tag_has_next>,</#if> 
									</#list>
								</span>
							</div>
						</div>
					</#list>
					<#if (journals.dataList?size > 0)>
						<@util.doPagination dataPage=journals relativeUrl="/index" />
					</#if>			
		    	</div>
		    	<div class="threecol last">
		    		<#include "sidebar.ftl">
		    	</div>
		    </div>		    
		</div>
		<div class="footer">
			<div class="container">
				<#include "footer.ftl">
			</div>
	  	</div>	
	</body>
</html>