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
		String sakeId = request.getParameter("sakeId");
		String sakeName = request.getParameter("sakeName");
		String brewaryName = request.getParameter("brewaryName");
		String alc = request.getParameter("alc");
		String fDrink = request.getParameter("fDrink");
		String taste = request.getParameter("taste");
		String sakeExplanation = request.getParameter("sakeExplanation");
		String sImgPath = request.getParameter("sImgPath");
		
		int iSakeId = 0;
		double dAlc = 0;
		
		
		//リクエストパラメータの型を合わせる
		
		try {
			iSakeId = Integer.parseInt(sakeId);
			dAlc = Double.parseDouble(alc);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		//酒蔵の名前から酒蔵のIDを持ってくる
	
		
		//遷移先URLの設定
		String url = "sakeRegistConfirmServlet.jsp";
		
		//リクエストスコープへの属性の設定
		SakeBean sake = new SakeBean();
		
		sake.setSakeId(iSakeId);
		sake.setSakeName(sakeName);
		sake.setBreweryId(iSakeId);
		sake.setSlmgPath(sImgPath);
		sake.setAlc(dAlc);
		sake.setfDrink(fDrink);
		sake.setTaste(taste);
		sake.setSakeExplanation(sakeExplanation);
		
		

		
		RequestDispatcher rd = request.getRequestDispatcher("sakeRegestConfirm.jsp");
		rd.forward(request, response);
	}

}
