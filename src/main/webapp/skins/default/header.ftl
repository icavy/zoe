<div class="row">
	<div class="fourcol">
		<a href="<@util.url relativeUrl='/' />"><img src="<@util.url relativeUrl='/resource/images/logo.gif' />" width="232" height="74" border="0" alt="logo"></a>
	</div>
	<div class="eightcol last">
		<div class="menu">
			<ul class="nav">
				<li><a href="<@util.url relativeUrl='/' />" class="active"><span>Home</span></a></li>
				<#list menus as menu>
				<li><a href="${menu.url}">${menu.name}</a></li>
				</#list>
			</ul>		
		</div>
	</div>
</div>