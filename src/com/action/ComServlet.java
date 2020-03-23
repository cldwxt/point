package com.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.ComBean;
import com.util.Constant;

public class ComServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ComServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType(Constant.CONTENTTYPE);
		request.setCharacterEncoding(Constant.CHARACTERENCODING);
		HttpSession session = request.getSession();
		ComBean cBean = new ComBean();
		String date2=new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		String method = request.getParameter("method");
		if(method.equals("addhy")){ 
			//String xm="";String xb="";String cs="";String fm="";String zz="";String dh="";String yx="";
			 String xm = request.getParameter("xm");
			 String xb = request.getParameter("xb");
			 String cs = request.getParameter("cs");
			 String fm = request.getParameter("fm");
			 String zz = request.getParameter("zz");
			 String dh = request.getParameter("dh");
			 String yx = request.getParameter("yx");
				String sql="insert into hy( xm, xb, cs, fm, zz, dh, yx) values('"+xm+"', '"+xb+"', '"+cs+"', '"+fm+"', '"+zz+"', '"+dh+"', '"+yx+"')";
				int flag = cBean.comUp(sql);
				if(flag == Constant.SUCCESS){  
					request.setAttribute("message", "操作成功！");
					request.getRequestDispatcher("admin/hy/index.jsp").forward(request, response); 
				}
				else { 
					request.setAttribute("message", "操作失败！");
					request.getRequestDispatcher("admin/hy/index.jsp").forward(request, response); 
				}
			 
			
		}
		else if(method.equals("uphy")){ ///update  
			String id = request.getParameter("id"); 
			String xm = request.getParameter("xm");
			 String xb = request.getParameter("xb");
			 String cs = request.getParameter("cs");
			 String fm = request.getParameter("fm");
			 String zz = request.getParameter("zz");
			 String dh = request.getParameter("dh");
			 String yx = request.getParameter("yx");
				String sql="update hy set xm='"+xm+"', xb='"+xb+"', cs='"+cs+"', fm='"+fm+"', zz='"+zz+"', dh='"+dh+"', yx='"+yx+"' where id='"+id+"'";
				int flag = cBean.comUp(sql);
				if(flag == Constant.SUCCESS){  
					request.setAttribute("message", "操作成功！");
					request.getRequestDispatcher("admin/hy/index.jsp").forward(request, response); 
				}
				else { 
					request.setAttribute("message", "操作失败！");
					request.getRequestDispatcher("admin/hy/index.jsp").forward(request, response); 
				}
		}
		else if(method.equals("delhy")){ ///update  
			String id = request.getParameter("id"); 
				String sql="delete from hy where id='"+id+"'";
				int flag = cBean.comUp(sql);
				if(flag == Constant.SUCCESS){  
					request.setAttribute("message", "操作成功！");
					request.getRequestDispatcher("admin/hy/index.jsp").forward(request, response); 
				}
				else { 
					request.setAttribute("message", "操作失败！");
					request.getRequestDispatcher("admin/hy/index.jsp").forward(request, response); 
				}
		}
		else if(method.equals("addhyk")){ 
			//String kh="";String xm="";String rq="";String je=""; 
			 String kh = request.getParameter("kh");
			 String xm = request.getParameter("xm");
			 String rq = request.getParameter("rq");
			 String je = request.getParameter("je"); 
			 String str=cBean.getString("select id from hyk where kh='"+kh+"'");
			 if(str==null){
				 String sql="insert into hyk( kh, xm, rq, je) values('"+kh+"', '"+xm+"', '"+rq+"', '"+je+"' )";
					int flag = cBean.comUp(sql);
					if(flag == Constant.SUCCESS){  
						request.setAttribute("message", "操作成功！");
						request.getRequestDispatcher("admin/hyk/index.jsp").forward(request, response); 
					}
					else { 
						request.setAttribute("message", "操作失败！");
						request.getRequestDispatcher("admin/hyk/index.jsp").forward(request, response); 
					}
			 }
			 else{
				 request.setAttribute("message", "该卡号已存在！");
					request.getRequestDispatcher("admin/hyk/index.jsp").forward(request, response); 
			 }
		}
		else if(method.equals("uphyk")){ ///update  
			String id = request.getParameter("id"); 
			 String kh = request.getParameter("kh");
			 String xm = request.getParameter("xm");
			 String rq = request.getParameter("rq");
			 String je = request.getParameter("je"); 
			 String str=cBean.getString("select id from hyk where kh='"+kh+"' and id!='"+id+"'");
			 if(str==null){
				String sql="update hyk set kh='"+kh+"', xm='"+xm+"', rq='"+rq+"', je='"+je+"'  where id='"+id+"'";
				int flag = cBean.comUp(sql);
				if(flag == Constant.SUCCESS){  
					request.setAttribute("message", "操作成功！");
					request.getRequestDispatcher("admin/hyk/index.jsp").forward(request, response); 
				}
				else { 
					request.setAttribute("message", "操作失败！");
					request.getRequestDispatcher("admin/hyk/index.jsp").forward(request, response); 
				}
			 }
			 else{
				 request.setAttribute("message", "该卡号已存在！");
					request.getRequestDispatcher("admin/hyk/index.jsp").forward(request, response); 
			 }
		}
		else if(method.equals("delhyk")){ ///update  
			String id = request.getParameter("id"); 
				String sql="delete from hyk where id='"+id+"'";
				int flag = cBean.comUp(sql);
				if(flag == Constant.SUCCESS){  
					request.setAttribute("message", "操作成功！");
					request.getRequestDispatcher("admin/hyk/index.jsp").forward(request, response); 
				}
				else { 
					request.setAttribute("message", "操作失败！");
					request.getRequestDispatcher("admin/hyk/index.jsp").forward(request, response); 
				}
		}
		else if(method.equals("sdhyk")){ ///update  
			String id = request.getParameter("id"); 
			String str=cBean.getString("select sd from hyk where id='"+id+"'");
			if(str.equals("未")){
				String sql="update hyk  set sd='已' where id='"+id+"'";
				int flag = cBean.comUp(sql);
				if(flag == Constant.SUCCESS){  
					request.setAttribute("message", "操作成功！");
					request.getRequestDispatcher("admin/hyk/index.jsp").forward(request, response); 
				}
				else { 
					request.setAttribute("message", "操作失败！");
					request.getRequestDispatcher("admin/hyk/index.jsp").forward(request, response); 
				}
			}
			else{
				String sql="update hyk  set sd='未' where id='"+id+"'";
				int flag = cBean.comUp(sql);
				if(flag == Constant.SUCCESS){  
					request.setAttribute("message", "操作成功！");
					request.getRequestDispatcher("admin/hyk/index.jsp").forward(request, response); 
				}
				else { 
					request.setAttribute("message", "操作失败！");
					request.getRequestDispatcher("admin/hyk/index.jsp").forward(request, response); 
				}
			}
		}
		else if(method.equals("gshyk")){ ///update  
			String id = request.getParameter("id"); 
			String str=cBean.getString("select gs from hyk where id='"+id+"'");
			if(str.equals("未")){
				String sql="update hyk  set gs='已' where id='"+id+"'";
				int flag = cBean.comUp(sql);
				if(flag == Constant.SUCCESS){  
					request.setAttribute("message", "操作成功！");
					request.getRequestDispatcher("admin/hyk/index.jsp").forward(request, response); 
				}
				else { 
					request.setAttribute("message", "操作失败！");
					request.getRequestDispatcher("admin/hyk/index.jsp").forward(request, response); 
				}
			}
			else{
				String sql="update hyk  set gs='未' where id='"+id+"'";
				int flag = cBean.comUp(sql);
				if(flag == Constant.SUCCESS){  
					request.setAttribute("message", "操作成功！");
					request.getRequestDispatcher("admin/hyk/index.jsp").forward(request, response); 
				}
				else { 
					request.setAttribute("message", "操作失败！");
					request.getRequestDispatcher("admin/hyk/index.jsp").forward(request, response); 
				}
			}
		}
		else if(method.equals("czhyk")){ ///update  
			String hykid = request.getParameter("hykid"); 
			String je = request.getParameter("je"); 
			 
			String sql="insert into cz(hykid,je,sj) values('"+hykid+"','"+je+"','"+date2+"')";
			int flag = cBean.comUp(sql);
			if(flag == Constant.SUCCESS){  
				cBean.comUp("update hyk set je=je+'"+je+"' where id='"+hykid+"'");
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/hyk/cz.jsp").forward(request, response); 
			}
			else { 
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("admin/hyk/cz.jsp").forward(request, response); 
			}
			 
		}
		else if(method.equals("delcz")){ ///update  
			String id = request.getParameter("id"); 
			String je=cBean.getString("select je from cz where id='"+id+"'");
			String hykid=cBean.getString("select hykid from cz where id='"+id+"'");
				String sql="delete from cz where id='"+id+"'";
				int flag = cBean.comUp(sql);
				if(flag == Constant.SUCCESS){  
					cBean.comUp("update hyk set je=je-'"+je+"' where id='"+hykid+"'");
					request.setAttribute("message", "操作成功！");
					request.getRequestDispatcher("admin/hyk/index.jsp").forward(request, response); 
				}
				else { 
					request.setAttribute("message", "操作失败！");
					request.getRequestDispatcher("admin/hyk/index.jsp").forward(request, response); 
				}
		}
		
		
		
		else if(method.equals("addsp")){ 
			//String mc="";String js="";String jg="";String jf="";String bj="";  
			 String mc = request.getParameter("mc");
			 String js = request.getParameter("js");
			 String jg = request.getParameter("jg");
			 String jf = request.getParameter("jf");
			 String bj = request.getParameter("bj"); 
				String sql="insert into sp( mc, js, jg, jf,bj) values('"+mc+"', '"+js+"', '"+jg+"', '"+jf+"', '"+bj+"')";
				int flag = cBean.comUp(sql);
				if(flag == Constant.SUCCESS){  
					request.setAttribute("message", "操作成功！");
					request.getRequestDispatcher("admin/sp/index.jsp").forward(request, response); 
				}
				else { 
					request.setAttribute("message", "操作失败！");
					request.getRequestDispatcher("admin/sp/index.jsp").forward(request, response); 
				}
			 
			
		}
		else if(method.equals("upsp")){ ///update  
			String id = request.getParameter("id"); 
			String mc = request.getParameter("mc");
			 String js = request.getParameter("js");
			 String jg = request.getParameter("jg");
			 String jf = request.getParameter("jf");
			 String bj = request.getParameter("bj"); 
				String sql="update sp set mc='"+mc+"', js='"+js+"', jg='"+jg+"', jf='"+jf+"', bj='"+bj+"'  where id='"+id+"'";
				int flag = cBean.comUp(sql);
				if(flag == Constant.SUCCESS){  
					request.setAttribute("message", "操作成功！");
					request.getRequestDispatcher("admin/sp/index.jsp").forward(request, response); 
				}
				else { 
					request.setAttribute("message", "操作失败！");
					request.getRequestDispatcher("admin/sp/index.jsp").forward(request, response); 
				}
		}
		else if(method.equals("delsp")){ ///update  
			String id = request.getParameter("id"); 
				String sql="delete from sp where id='"+id+"'";
				int flag = cBean.comUp(sql);
				if(flag == Constant.SUCCESS){  
					request.setAttribute("message", "操作成功！");
					request.getRequestDispatcher("admin/sp/index.jsp").forward(request, response); 
				}
				else { 
					request.setAttribute("message", "操作失败！");
					request.getRequestDispatcher("admin/sp/index.jsp").forward(request, response); 
				}
		}
		
		
		
		else if(method.equals("xssp")){ ///update  
			String spid = request.getParameter("spid"); 
			String hykid = request.getParameter("hykid");
			 String sl = request.getParameter("sl"); 
			 
			 int spsl=cBean.getCount("select js from sp where id='"+spid+"'");
			 int xssl=cBean.getCount("select sum(sl) from xs where spid='"+spid+"'");
			 if((spsl-xssl)>=Integer.parseInt(sl)){
				 float hykje= cBean.getFloat("select je from hyk where id='"+hykid+"'" );
				 float xsje= cBean.getFloat("select sum(je) from xs where hykid='"+hykid+"'" );
				 
				 float je=Integer.parseInt(sl)*cBean.getFloat("select jg from sp where id='"+spid+"'");
				 
				 float jf=Integer.parseInt(sl)*cBean.getFloat("select jf from sp where id='"+spid+"'");
				 
				 if((hykje-xsje)>=je){
					 String sql="insert into xs(spid,hykid,sl,je,jf,sj) values('"+spid+"','"+hykid+"','"+sl+"','"+je+"','"+jf+"','"+date2+"')";
						int flag = cBean.comUp(sql);
						if(flag == Constant.SUCCESS){  
							request.setAttribute("message", "操作成功！");
							request.getRequestDispatcher("admin/xs/index.jsp").forward(request, response); 
						}
						else { 
							request.setAttribute("message", "操作失败！");
							request.getRequestDispatcher("admin/xs/index.jsp").forward(request, response); 
						}
				 }
				 else{
					 request.setAttribute("message", "会员卡余额不足！");
					 request.getRequestDispatcher("admin/xs/add.jsp").forward(request, response); 
				 }
				 
			 }
			 else{
				 request.setAttribute("message", "商品库存不足！");
				 request.getRequestDispatcher("admin/xs/add.jsp").forward(request, response); 
			 }
		}
		else if(method.equals("delxs")){ ///update  
			String id = request.getParameter("id"); 
				String sql="delete from xs where id='"+id+"'";
				int flag = cBean.comUp(sql);
				if(flag == Constant.SUCCESS){  
					request.setAttribute("message", "操作成功！");
					request.getRequestDispatcher("admin/xs/index.jsp").forward(request, response); 
				}
				else { 
					request.setAttribute("message", "操作失败！");
					request.getRequestDispatcher("admin/xs/index.jsp").forward(request, response); 
				}
		}
		
		else if(method.equals("adddhsp")){ ///update  
			String mc = request.getParameter("mc"); 
			String jf = request.getParameter("jf"); 
				String sql="insert into dhsp(mc,jf) values('"+mc+"','"+jf+"')";
				int flag = cBean.comUp(sql);
				if(flag == Constant.SUCCESS){  
					request.setAttribute("message", "操作成功！");
					request.getRequestDispatcher("admin/jf/dhsp.jsp").forward(request, response); 
				}
				else { 
					request.setAttribute("message", "操作失败！");
					request.getRequestDispatcher("admin/jf/dhsp.jsp").forward(request, response); 
				}
		}
		else if(method.equals("updhsp")){ ///update  
			String id = request.getParameter("id");  
			String mc = request.getParameter("mc"); 
			String jf = request.getParameter("jf"); 
				String sql="update dhsp set mc='"+mc+"',jf='"+jf+"' where id='"+id+"'";
				int flag = cBean.comUp(sql);
				if(flag == Constant.SUCCESS){  
					request.setAttribute("message", "操作成功！");
					request.getRequestDispatcher("admin/jf/dhsp.jsp").forward(request, response); 
				}
				else { 
					request.setAttribute("message", "操作失败！");
					request.getRequestDispatcher("admin/jf/dhsp.jsp").forward(request, response); 
				}
		}
		else if(method.equals("deldhsp")){ ///update  
			String id = request.getParameter("id");  
				String sql="delete from dhsp where id='"+id+"'";
				int flag = cBean.comUp(sql);
				if(flag == Constant.SUCCESS){  
					request.setAttribute("message", "操作成功！");
					request.getRequestDispatcher("admin/jf/dhsp.jsp").forward(request, response); 
				}
				else { 
					request.setAttribute("message", "操作失败！");
					request.getRequestDispatcher("admin/jf/dhsp.jsp").forward(request, response); 
				}
		}
		
		else if(method.equals("jfdhsp")){ ///update  
			String dhspid = request.getParameter("dhspid"); 
			String hykid = request.getParameter("hykid"); 
			
			float hykjf=cBean.getFloat("select sum(jf) from xs where hykid='"+hykid+"'");
			float syjf=cBean.getFloat("select sum(syjf) from dh where  hykid='"+hykid+"'");
			float jf=cBean.getFloat("select jf from dhsp where id='"+dhspid+"'");
			if((hykjf-syjf)>=jf){
				String sql="insert into dh(dhspid,hykid,syjf,sj) values('"+dhspid+"','"+hykid+"','"+jf+"','"+date2+"')";
				int flag = cBean.comUp(sql);
				if(flag == Constant.SUCCESS){  
					request.setAttribute("message", "操作成功！");
					request.getRequestDispatcher("admin/jf/dhinfo.jsp").forward(request, response); 
				}
				else { 
					request.setAttribute("message", "操作失败！");
					request.getRequestDispatcher("admin/jf/dhinfo.jsp").forward(request, response); 
				}
			}
			else{
				request.setAttribute("message", "积分不够！");
				request.getRequestDispatcher("admin/jf/dhinfo.jsp").forward(request, response);
			}
				
		}
		
		
		else if(method.equals("deldhinfo")){ ///update  
			String id = request.getParameter("id");  
				String sql="delete from dh where id='"+id+"'";
				int flag = cBean.comUp(sql);
				if(flag == Constant.SUCCESS){  
					request.setAttribute("message", "操作成功！");
					request.getRequestDispatcher("admin/jf/dhinfo.jsp").forward(request, response); 
				}
				else { 
					request.setAttribute("message", "操作失败！");
					request.getRequestDispatcher("admin/jf/dhinfo.jsp").forward(request, response); 
				}
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occure
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
