package com.action;

/**
 * 
 * 
 */
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.ComBean; 
import com.util.Constant;
import com.util.SmartFile;
import com.util.SmartUpload;
public class UpServlet extends HttpServlet {

	private ServletConfig config;
	/**
	 * Constructor of the object.
	 */
	public UpServlet() {
		super();
	}

	final public void init(ServletConfig config) throws ServletException
    {
        this.config = config;  
    }

    final public ServletConfig getServletConfig()
    {
        return config;
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

		request.setCharacterEncoding(Constant.CHARACTERENCODING);
		response.setContentType(Constant.CONTENTTYPE);
		 
		HttpSession session = request.getSession();
		String date=new SimpleDateFormat("yyyy-MM-dd HH:mm").format(Calendar.getInstance().getTime());
		String date2=new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		try{
			 
			 
				 String method = null; 
				 ComBean cb=new ComBean(); 
				 SmartUpload mySmartUpload = new SmartUpload();//init
				 int count = 0;
				 try{
					 mySmartUpload.initialize(config,request,response);
		             mySmartUpload.upload(); 
		             method = mySmartUpload.getRequest().getParameter("method").trim();
		            if(method.equals("JL")){//增加 
		            	/**
		            	 * String xm = "";	String url="";String xb=""; String sfz="";String xl="";String yx="";
		String zy="";String cj="";String hj="";String sx=""; String lx=""; String dz=""; 
		            	 */ 
		            	String xm=mySmartUpload.getRequest().getParameter("xm");
		    			String xb=mySmartUpload.getRequest().getParameter("xb");  
		    			String sfz=mySmartUpload.getRequest().getParameter("sfz");  
		    			String xl=mySmartUpload.getRequest().getParameter("xl");  
		    			String yx=mySmartUpload.getRequest().getParameter("yx");  
		    			String zy=mySmartUpload.getRequest().getParameter("zy");  
		    			String cj=mySmartUpload.getRequest().getParameter("cj"); 
		    			String hj=mySmartUpload.getRequest().getParameter("hj"); 
		    			String sx=mySmartUpload.getRequest().getParameter("sx"); 
		    			String lx=mySmartUpload.getRequest().getParameter("lx"); 
		    			String dz=mySmartUpload.getRequest().getParameter("dz"); 
		    			SmartFile file = mySmartUpload.getFiles().getFile(0);
		            	String fileExt=file.getFileExt();	            
		            	String path="/pic";
	                    count = mySmartUpload.save(path);
	                    String member=(String)session.getAttribute("member");
	                    String str=cb.getString("select id from jl where member='"+member+"'");
	                    String sql="";
	                    if(str==null){
	                    	sql="insert into jl( xm ,url, xb,  sfz, xl, yx, zy, cj, hj, sx,  lx,  dz,member,flag)" +
	                   		"values('"+xm+"','"+path+"/"+file.getFileName()+"','"+xb+"' ,'"+sfz+"','"+xl+"','"+yx+"','"+zy+"','"+cj+"','"+hj+"'" +
	                   				",'"+sx+"','"+lx+"','"+dz+"','"+member+"','未审核')";
	                    }
	                    else{
	                    	sql="update jl set xm='"+xm+"',url='"+path+"/"+file.getFileName()+"',xb='"+xb+"' ,sfz='"+sfz+"',xl='"+xl+"'," +
	                    			"yx='"+yx+"',zy='"+zy+"',cj='"+cj+"',hj='"+hj+"'" +
	                   				",sx='"+sx+"',lx='"+lx+"',dz='"+dz+"' where member='"+member+"'";
	                    }
	                   int flag=cb.comUp(sql);
	        				//int flag=pb.addBOOK(id,booktype, name, author, path+"/"+file.getFileName(), isbn, price, num, intro, jyjg,addtime);
	        				if(flag==Constant.SUCCESS){
	        					request.setAttribute("message", "操作成功！");
	        					request.getRequestDispatcher("member/jl/index.jsp").forward(request, response);
	        				}
	        				else{
	        					request.setAttribute("message", "系统维护中，请稍后再试！");
	        					request.getRequestDispatcher("member/jl/index.jsp").forward(request, response);
	        				}
	        		 
		                   
						 						
		            }
		            else if(method.equals("upZT")){//修改 
		            	String id=mySmartUpload.getRequest().getParameter("id");
		            	String title=mySmartUpload.getRequest().getParameter("title");
		    			String fl=mySmartUpload.getRequest().getParameter("fl");  
		    			String content=mySmartUpload.getRequest().getParameter("infoContent");  
						SmartFile file = mySmartUpload.getFiles().getFile(0);
		            	String fileExt=file.getFileExt();	            
		            	String path="/pic";
	                    count = mySmartUpload.save(path);
	                    int flag=cb.comUp("update zt set title='"+title+"' ,url='"+path+"/"+file.getFileName()+"'," +
	                    		"fl='"+fl+"',content='"+content+"'  where id='"+id+"'");
	                    //int flag=pb.upBOOK(id, booktype, name, author, path+"/"+file.getFileName(), isbn, price, num, intro, jyjg,addtime);
	        			if(flag==Constant.SUCCESS){
	        				request.setAttribute("message", "操作成功！");
	        				request.getRequestDispatcher("admin/hzp/index.jsp").forward(request, response);
	        			}
	        			else{
	        				request.setAttribute("message", "系统维护中，请稍后再试！");
	        				request.getRequestDispatcher("admin/hzp/index.jsp").forward(request, response);
	        			}
		            }
		             
		             
		            else{
		            	request.getRequestDispatcher("error.jsp").forward(request, response);
		            }
		        }catch(Exception ex){
		        	ex.printStackTrace();
		        	//request.getRequestDispatcher("error.jsp").forward(request, response);
		        }
			 
		}catch(Exception e){
			e.printStackTrace();
			request.getRequestDispatcher("error.jsp").forward(request, response);
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
