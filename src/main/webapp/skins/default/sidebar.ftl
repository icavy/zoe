<div class="widget">
	<div class="widget-wrap">
		<div class="search">
			<form method="get" class="searchform" action="http://www.google.com/search">
				<input type="hidden" name="domains" value="${configComponent.getConfig('base.url')}" />
				<label for="s" class="assistive-text">Search for:</label>
				<input type="text" class="field" name="q" id="q" value="Search" onfocus="if(this.value==this.defaultValue)this.value='';" onblur="if(this.value=='')this.value=this.defaultValue;">
				<input type="submit" class="submit" name="submit" id="searchsubmit" value="Search">
			</form>
		</div>
	</div>
</div>
<div class="widget">
	<div class="widget-wrap">
	  	<h3 class="widget-title">分类</h3>
	  	<ul>
	  	<#list categories as category>	
	  	<li><a href="<@util.url relativeUrl='/cat/${category.path}' />">${category.name}</a></li>
	  	</#list>	        			
	 </div>
</div>
<div class="widget">
	<div class="widget-wrap">
		<h3 class="widget-title">标签</h3>
		<#list tags as tag>
	  	<a href="<@util.url relativeUrl='/tag/${tag.name}' />">${tag.name}</a>
	  	</#list>
	 </div>
</div>