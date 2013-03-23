<!DOCTYPE html>   
<html lang="zh_cn">
	 <head>
	 	<title>${journal.title} | ${configComponent.getConfig('base.titile')}</title>
    	<#include "resource.ftl">
    	<!-- SEO start -->
		<meta name="keywords" content="<#list journal.tags as tag>${tag.name}<#if tag_has_next>,</#if></#list>">
		<link rel="canonical" href="http://${configComponent.getConfig('base.url')}/post/${journal.path}"/>
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
					<div class="journal">
		    			<h2 class="journal-title"><a href="<@util.url relativeUrl='/post/${journal.path}' />">${journal.title}</a></h2>
						<div class="journal-meta">
		    				<span class="journal-date">${journal.createDate?string('yyyy年M月d日 a h:m')}</span>
		    				<span class="journal-meta-sep"> ⋅ </span>
		    				<span class="journal-comment"><a href="<@util.url relativeUrl='/cat/${journal.category.name}' />">1条评论</a></span>
		    				<span class="journal-meta-sep"> ⋅ </span>
		    				<span class="journal-author"><a href="<@util.url relativeUrl='/cat/${journal.category.name}' />">Cavy</a></span>
		    			</div>
						<div class="journal-content">${journal.content}</div>
						<div class="journal-meta-bottom">
							<span class="journal-cat">
								<span>Posted in:</span>
								<a href="<@util.url relativeUrl='/cat/${journal.category.name}' />" title="查看 ${journal.category.name} 中的全部文章" rel="category">${journal.category.name}</a> 
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