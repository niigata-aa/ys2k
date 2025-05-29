package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entity.SakeBean;

/**
 * Servlet implementation class SakeRegistServlet
 */
@WebServlet("/SakeRegist")
public class SakeRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SakeRegistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//リクエストのエンコーディング方式を指定
		request.setCharacterEncoding("UTF-8");
		
		//リクエストパラメータの取得
//		String sakeId = request.getParameter("1000");
		
		String sakeId = "1000";
		
		String sakeName = request.getParameter("sakeName");
		String breweryId = request.getParameter("breweryId");
		String alc = request.getParameter("alc");		
		String fDrink = request.getParameter("fDrink");
		String taste = request.getParameter("taste");
		String sakeExplanation = request.getParameter("sakeExplanation");
		String sImgPath = request.getParameter("sImgPath");
		
		int iSakeId = 0;
		int iBreweryId = 0;
		double dAlc = 0;
		
		//リクエストパラメータの型を合わせる
		try {
			
			iSakeId = Integer.parseInt(sakeId);
			iBreweryId = Integer.parseInt(breweryId);
			dAlc = Double.parseDouble(alc);
			
		} catch (NumberFormatException e) {
			
			e.printStackTrace();
			
		}
		
		System.err.println(dAlc);
		
			
		//遷移先URLの設定
		String url = "sakeRegistConfirm.jsp";
		
		//リクエストスコープへの属性の設定
		SakeBean sake = new SakeBean();
		
		sake.setSakeId(iSakeId);
		sake.setSakeName(sakeName);
		sake.setBreweryId(iBreweryId);
		sake.setAlc(dAlc);
		sake.setfDrink(fDrink);
		sake.setTaste(taste);
		sake.setSakeExplanation(sakeExplanation);
		sake.setsImgPath(sImgPath);
		
		request.setAttribute("sake", sake);
		
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
