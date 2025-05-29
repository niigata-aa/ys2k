package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.SakeDAO;
import model.entity.SakeBean;

/**
 * Servlet implementation class SakeRegistConfirmServlet
 */
@WebServlet("/SakeRegistConfirm")
public class SakeRegistConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SakeRegistConfirmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//遷移先の設定
		String url = "adminComplete.jsp";
		
		//リクエストのエンコーディング方式を指定
		request.setCharacterEncoding("UTF-8");
		
		//リクエストパラメータを受け取る
		
		String sakeId = "1000";
		
		String sakeName = request.getParameter("sakeName2");
		String alc = request.getParameter("alc2");		
		String breweryId = request.getParameter("breweryId2");
		String fDrink = request.getParameter("fDrink2");
		String taste = request.getParameter("taste2");
		String sakeExplanation = request.getParameter("sakeExplanation2");
		String sImgPath = request.getParameter("sImgPath2");	
	
		//リクエストパラメータの型を合わせる
		
		int iSakeId = 0;
		int iBreweryId = 0;
		double dAlc = 0;
		

		try {
			
			iSakeId = Integer.parseInt(sakeId);
			iBreweryId = Integer.parseInt(breweryId);
			dAlc = Double.parseDouble(alc);
			
		} catch (NumberFormatException e) {
			
			e.printStackTrace();
			
		}
		
		//Beanに値を挿入する
		SakeBean sake = new SakeBean();
		
		sake.setSakeId(iSakeId);
		sake.setSakeName(sakeName);
		sake.setBreweryId(iBreweryId);
		sake.setsImgPath(sImgPath);
		sake.setAlc(dAlc);
		sake.setfDrink(fDrink);
		sake.setTaste(taste);
		sake.setSakeExplanation(sakeExplanation);
		
		
		SakeDAO dao = new SakeDAO();
		//modo
		int processingNumber = 0;
		
		//daoの実行
		try {
			processingNumber = dao.insert(sake);
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//遷移先の変更
		if(processingNumber == 0) {//失敗した時
			url = "adminFailure.jsp";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
		
	}

}
