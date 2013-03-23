<!DOCTYPE html>  
<html lang="zh_cn">
	 <head>
    	<#include "resource.ftl">
    </head>
	<body class="need-margin-nav">
		<#include "menu.ftl">
		<div class="container">
			<div class="row">
				<div class="span12">
					<h2>日志</h2>
					<form class="form-inline" method="post" action="<@util.url relativeUrl='/admin/journal/query' />">
						<div class="row-fluid">
							<div class="span8">							
								 <input type="text" >
								 <select class="input-small">
									<option>查看所有分类目录</option>
									<#list categories as category>
								  	<option value="${category.id}">${category.name}</option>
								  	</#list>
								</select>
								<button type="submit" class="btn">筛选</button>												
							</div>
							<div class="span4">
								<div class="btn-toolbar pull-right">
									<div class="btn-group">
										<a class="btn" href="<@util.url relativeUrl='/admin/journal/new' />"><i class="icon-pencil"></i> 写日志</a>
								  	</div>
								</div>
							</div>
						</div>
						<table class="table table-bordered table-striped table-hover">
							<thead>
			                	<tr>
			                		<th class="check-column"><input type="checkbox" /></th>
			                  		<th>标题</th>
			                  		<th>路径</th>
			                  		<th>创建日期</th>
			                  		<th>分类</th>
			                	</tr>
			                 </thead>
			            	<tbody>
				            	<#list journals.dataList as journal>
			            		<tr>
			            			<td class="check-column"><input type="checkbox" name="journalId" value="${journal.id}" /></td>
			                  		<td><a href="<@util.url relativeUrl='/admin/journal/${journal.path}' />">${journal.title}</a></td>
			                  		<td>${journal.path}</td>
			                  		<td> ${journal.createDate?string('yyyy-MM-dd')}</td>
			                  		<td>${journal.category.name}</td>
			                	</tr>
								</#list>
							</tbody>
						</table>
					</form>	
					<@util.doPagination dataPage=journals relativeUrl="/index" />
				</div>
			</div>
		</div>
		<script>
			$(document).ready(function(){
				var lastClicked = false, checks, first, last, checked;
				// check all checkboxes
				$('tbody').children().children('.check-column').find(':checkbox').click( function(e) {
					if ( 'undefined' == e.shiftKey ) { return true; }
					if ( e.shiftKey ) {
						if ( !lastClicked ) { return true; }
						checks = $( lastClicked ).closest( 'form' ).find( ':checkbox' );
						first = checks.index( lastClicked );
						last = checks.index( this );
						checked = $(this).prop('checked');
						if ( 0 < first && 0 < last && first != last ) {
							checks.slice( first, last ).prop( 'checked', function(){
								if ( $(this).closest('tr').is(':visible') )
									return checked;
			
								return false;
							});
						}
					}
					lastClicked = this;
			
					// toggle "check all" checkboxes
					var unchecked = $(this).closest('tbody').find(':checkbox').filter(':visible').not(':checked');
					$(this).closest('table').children('thead, tfoot').find(':checkbox').prop('checked', function() {
						return ( 0 == unchecked.length );
					});
			
					return true;
				});
			
				$('thead, tfoot').find('.check-column :checkbox').click( function(e) {
					var c = $(this).prop('checked'),
						kbtoggle = 'undefined' == typeof toggleWithKeyboard ? false : toggleWithKeyboard,
						toggle = e.shiftKey || kbtoggle;
			
					$(this).closest( 'table' ).children( 'tbody' ).filter(':visible')
					.children().children('.check-column').find(':checkbox')
					.prop('checked', function() {
						if ( $(this).closest('tr').is(':hidden') )
							return false;
						if ( toggle )
							return $(this).prop( 'checked' );
						else if (c)
							return true;
						return false;
					});
			
					$(this).closest('table').children('thead,  tfoot').filter(':visible')
					.children().children('.check-column').find(':checkbox')
					.prop('checked', function() {
						if ( toggle )
							return false;
						else if (c)
							return true;
						return false;
					});
				});
			});			
		</script>
	</body>
</html>