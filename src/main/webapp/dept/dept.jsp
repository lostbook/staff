<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>人事管理系统 ——部门管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
	<meta http-equiv="description" content="This is my page" />
	<link href="./css/css.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="./js/ligerUI/skins/Aqua/css/ligerui-dialog.css"/>
	<link href="./js/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="./js/jquery-1.11.0.js"></script>
    <script type="text/javascript" src="./js/jquery-migrate-1.2.1.js"></script>
	<script src="./js/ligerUI/js/core/base.js" type="text/javascript"></script>
	<script src="./js/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script> 
	<script src="./js/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
	<script src="./js/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>
	<link href="./css/pager.css" type="text/css" rel="stylesheet" />
	<script type="text/javascript">
		$(function(){
	 	   /** 获取上一次选中的部门数据 */
	 	   var boxs  = $("input[type='checkbox'][id^='box_']");
	 	   
	 	  /** 给全选按钮绑定点击事件  */
	    	$("#checkAll").click(function(){
	    		// this是checkAll  this.checked是true
	    		// 所有数据行的选中状态与全选的状态一致
	    		boxs.attr("checked",this.checked);
	    	})
	    	
	 	  /** 给数据行绑定鼠标覆盖以及鼠标移开事件  */
	    	$("tr[id^='data_']").hover(function(){
	    		$(this).css("backgroundColor","#eeccff");
	    	},function(){
	    		$(this).css("backgroundColor","#ffffff");
	    	})
	    	
	    	
	 	   /** 删除员工绑定点击事件 */
	 	   $("#delete").click(function(){
	 		   /** 获取到用户选中的复选框  */
	 		   var checkedBoxs = boxs.filter(":checked");
	 		   if(checkedBoxs.length < 1){
	 			   $.ligerDialog.error("请选择一个需要删除的部门！");
	 		   }else{
	 			   /** 得到用户选中的所有的需要删除的ids */
	 			   var ids = checkedBoxs.map(function(){
	 				   return this.value;
	 			   })
	 			   
	 			   $.ligerDialog.confirm("确认要删除吗?","删除部门",function(r){
	 				   if(r){
	 					   // alert("删除："+ids.get());
	 					   // 发送请求
	 					  // window.location = "${ctx }/dept/removeDept?ids=" + ids.get();
	 					  window.location="/staff/DepartmentServlet?cmd=deleteDepartment&ids="+ids.get();
	 				   }
	 			   });
	 		   }
	 	   })
	    })
	</script>
</head>
<body>
	<!-- 导航 -->
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
	  <tr><td height="10"></td></tr>
	  <tr>
	    <td width="15" height="32"><img src="./images/main_locleft.gif" width="15" height="32"></td>
		<td class="main_locbg font2"><img src="./images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：部门管理 &gt; 部门查询</td>
		<td width="15" height="32"><img src="./images/main_locright.gif" width="15" height="32"></td>
	  </tr>
	</table>
	
	<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
	  <!-- 查询区  -->
	  <form action="/staff/DepartmentServlet?cmd=searchDepartment" method="post">
	  <tr valign="top">
	    <td height="30">
		  <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
		    <tr>
			  <td class="fftd">
			  	
				    <table width="100%" border="0" cellpadding="0" cellspacing="0">
					  <tr>
					    <td class="font3">
					    	部门名称：<input type="text" name="name">
					    	 <input type="submit" value="搜索"/>
					    	<input id="delete" type="button" value="删除"/>
					    </td>
					  </tr>
					</table>
			
			  </td>
			</tr>
		  </table>
		</td>
	  </tr>
	  </form>
	  
	  
	  <!-- 数据展示区 -->
	  <tr valign="top">
	    <td height="20">
		 <table width="100%" border="1" cellpadding="5" cellspacing="0" style="border:#c2c6cc 1px solid; border-collapse:collapse;">
		    <tbody><tr class="main_trbg_tit" align="center">
			  <td><input type="checkbox" name="checkAll" id="checkAll"></td>
			  <td>部门名称</td>
			  <td>详细信息</td>
			  	
			  <td align="center">操作</td>
			  
			</tr>
			
				<c:forEach items="${departmentList }" var="department">
				<tr id="data_0" align="center" class="main_trbg" style="background-color: rgb(255, 255, 255);">
					<td><input type="checkbox" id="box_0" value="${department.id}"></td>
					 <td>${department.name}</td>
					  <td>${department.remark}</td>
					  	
					 <td align="center" width="40px;"><a href="./dept/showUpdateDept.jsp?id=${department.id}&name=${department.name}&remark=${department.remark}">
							<img title="修改" src="./images/update.gif"></a>
					  </td>
					  
				</tr>
				</c:forEach>
			
<!-- 				<tr id="data_1" align="center" class="main_trbg" style="background-color: rgb(238, 204, 255);"> -->
<!-- 					<td><input type="checkbox" id="box_1" value="2"></td> -->
<!-- 					 <td>运营部</td> -->
<!-- 					  <td>运营部</td> -->
					  	
<!-- 					 <td align="center" width="40px;"><a href="showUpdateDept.jsp"> -->
<!-- 							<img title="修改" src="../images/update.gif"></a> -->
<!-- 					  </td> -->
					  
<!-- 				</tr> -->
			
<!-- 				<tr id="data_2" align="center" class="main_trbg"> -->
<!-- 					<td><input type="checkbox" id="box_2" value="3"></td> -->
<!-- 					 <td>财务部</td> -->
<!-- 					  <td>财务部</td> -->
					  	
<!-- 					 <td align="center" width="40px;"><a href="showUpdateDept.jsp"> -->
<!-- 							<img title="修改" src="../images/update.gif"></a> -->
<!-- 					  </td> -->
					  
<!-- 				</tr> -->
			
<!-- 				<tr id="data_3" align="center" class="main_trbg"> -->
<!-- 					<td><input type="checkbox" id="box_3" value="5"></td> -->
<!-- 					 <td>总公办</td> -->
<!-- 					  <td>总公办</td> -->
					  	
<!-- 					 <td align="center" width="40px;"><a href="showUpdateDept.jsp"> -->
<!-- 							<img title="修改" src="../images/update.gif"></a> -->
<!-- 					  </td> -->
					  
<!-- 				</tr> -->
			
		  </tbody></table>
		</td>
	  </tr>
	  <!-- 分页标签 -->
	  <tr valign="top"><td align="center" class="font3">
	  	 <table width="100%" align="center" style="font-size:13px;" class="digg"><tbody><tr><td style="COLOR: #0061de; MARGIN-RIGHT: 3px; PADDING-TOP: 2px; TEXT-DECORATION: none"><span class="disabled">上一页</span><span class="current">1</span><a href="#">2</a><a href="#">下一页</a>&nbsp;跳转到&nbsp;&nbsp;<input style="text-align: center;BORDER-RIGHT: #aaaadd 1px solid; PADDING-RIGHT: 5px; BORDER-TOP: #aaaadd 1px solid; PADDING-LEFT: 5px; PADDING-BOTTOM: 2px; MARGIN: 2px; BORDER-LEFT: #aaaadd 1px solid; COLOR: #000099; PADDING-TOP: 2px; BORDER-BOTTOM: #aaaadd 1px solid; TEXT-DECORATION: none" type="text" size="2" id="pager_jump_page_size">&nbsp;<input type="button" style="text-align: center;BORDER-RIGHT: #dedfde 1px solid; PADDING-RIGHT: 6px; BACKGROUND-POSITION: 50% bottom; BORDER-TOP: #dedfde 1px solid; PADDING-LEFT: 6px; PADDING-BOTTOM: 2px; BORDER-LEFT: #dedfde 1px solid; COLOR: #0061de; MARGIN-RIGHT: 3px; PADDING-TOP: 2px; BORDER-BOTTOM: #dedfde 1px solid; TEXT-DECORATION: none" value="确定" id="pager_jump_btn"></td></tr><tr align="center"><td style="font-size:13px;"></td></tr><tr><td style="COLOR: #0061de; MARGIN-RIGHT: 3px; PADDING-TOP: 2px; TEXT-DECORATION: none">总共<font color="red">6</font>条记录，当前显示1-4条记录。</td></tr></tbody></table>
	  </td></tr>
	</table>
	<div style="height:10px;"></div>
</body>
</html>