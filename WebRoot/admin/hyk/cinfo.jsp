<%@ page language="java" import="java.util.*,com.util.*,java.text.*"  contentType="text/html;charset=gb2312"%>
<jsp:useBean id="sn" scope="page" class="com.bean.SystemBean" />
<jsp:useBean id="cb" scope="page" class="com.bean.ComBean" />
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String dir=sn.getDir();
%>
<HTML><HEAD><TITLE>��̨������</TITLE>
<LINK href="<%=basePath %><%=dir %>/images/Admin_Style.css" type=text/css rel=stylesheet>
<LINK href="<%=basePath %><%=dir %>/images/style.css" type=text/css rel=stylesheet>
<SCRIPT language=JavaScript src="<%=basePath %><%=dir %>/images/Common.js"></SCRIPT>
<STYLE type=text/css>
BODY {
	MARGIN-LEFT: 0px; BACKGROUND-COLOR: #ffffff
}
.STYLE1 {color: #ECE9D8}
</STYLE>
</HEAD>
 
	
<%
String message = (String)request.getAttribute("message");
	if(message == null){
		message = "";
	}
	if (!message.trim().equals("")){
		out.println("<script language='javascript'>");
		out.println("alert('"+message+"');");
		out.println("</script>");
	}
	request.removeAttribute("message");
%>
<%
	String username=(String)session.getAttribute("user");
	if(username==null){
		response.sendRedirect(path+"/error.jsp");
	}
	else{
		
%>
<BODY >
<TABLE  cellSpacing=0 cellPadding=0 width="100%" border=0>
  <TBODY>
  <TR>
    <TD align="left" vAlign=top > 
<table width='100%' cellspacing='1' cellpadding='3' bgcolor='#CCCCCC' class="tablewidth">
     <tr class="head"> 
      <td width="5%" align="center">ID </td>
      <td  align="center">��Ա������</td>
      <td  align="center">��Ա����</td>
      <td  align="center">��������</td>
      <td  align="center">��ֵ���</td> 
      <td  align="center">�����޸�</td>
      <td  align="center">ɾ��</td> 
      <td  align="center">����</td> 
      <td  align="center">��ʧ</td> 
      <td  align="center">��ֵ��¼</td> 
      <td  align="center">���</td> 
      <td  align="center">����</td> 
      <td  align="center">��ʹ�û���</td> 
    </tr>
    <%String xm=Common.toChineseAndTrim(request.getParameter("xm"));
			 
			List pagelist3 = cb.getCom("select * from hyk where kh like '%"+xm+"%' order by id desc",8);
			 
				if(!pagelist3.isEmpty()){
				for(int i=0;i<pagelist3.size();i++){
					List pagelist2 =(ArrayList)pagelist3.get(i);
			%> 
	<tr  class="trA" onMouseOver="this.className='trB'" onMouseOut="this.className='trA'"> 
      <td width='5%' align="center" style="border-bottom:1px dotted #ccc;"><%=i+1 %></td> 
      <td align="center" bgcolor="#FFFFFF"  style="border-bottom:1px dotted #ccc;"><%=pagelist2.get(1).toString() %> </td>
      <td align="center" bgcolor="#FFFFFF"  style="border-bottom:1px dotted #ccc;"><%=cb.getString("select xm from hy where id='"+pagelist2.get(2).toString()+"'") %></td>
      <td align="center" bgcolor="#FFFFFF"  style="border-bottom:1px dotted #ccc;"><%=pagelist2.get(3).toString() %></td> 
      <td align="center" bgcolor="#FFFFFF"  style="border-bottom:1px dotted #ccc;"><%=pagelist2.get(4).toString() %></td> 
      <td align="center" bgcolor="#FFFFFF"  style="border-bottom:1px dotted #ccc;">
      <a href="<%=basePath %>admin/hyk/add.jsp?method=uphyk&id=<%=pagelist2.get(0).toString()%>">�����޸�</a></td> 
      <td align="center" bgcolor="#FFFFFF"  style="border-bottom:1px dotted #ccc;"><a href="<%=basePath %>ComServlet?method=delhyk&id=<%=pagelist2.get(0).toString()%>">ɾ��</a></td> 
      <td align="center" bgcolor="#FFFFFF"  style="border-bottom:1px dotted #ccc;"><a href="<%=basePath %>ComServlet?method=sdhyk&id=<%=pagelist2.get(0).toString()%>"><%=pagelist2.get(5).toString() %></a></td>   
      <td align="center" bgcolor="#FFFFFF"  style="border-bottom:1px dotted #ccc;"><a href="<%=basePath %>ComServlet?method=gshyk&id=<%=pagelist2.get(0).toString()%>"><%=pagelist2.get(6).toString() %></a></td>   
      <td align="center" bgcolor="#FFFFFF"  style="border-bottom:1px dotted #ccc;"><a href="<%=basePath %>admin/hyk/czjl.jsp?id=<%=pagelist2.get(0).toString()%>">�鿴</a></td>   
      <td align="center" bgcolor="#FFFFFF"  style="border-bottom:1px dotted #ccc;"><%=Float.parseFloat(pagelist2.get(4).toString())-cb.getFloat("select sum(je) from xs where hykid='"+pagelist2.get(0).toString()+"'") %></td>  
      <td align="center" bgcolor="#FFFFFF"  style="border-bottom:1px dotted #ccc;"><%=cb.getFloat("select sum(jf) from xs where hykid='"+pagelist2.get(0).toString()+"'") %></td>    
     <td align="center" bgcolor="#FFFFFF"  style="border-bottom:1px dotted #ccc;"><%=cb.getFloat("select sum(syjf) from dh where hykid='"+pagelist2.get(0).toString()+"'") %></td>     
    </tr>
<%
	}}
%>  
  
	 </TABLE> 
	</td>
	</tr>
 	 
  </TBODY>
</TABLE>
</BODY>
<%} %>
</HTML>
